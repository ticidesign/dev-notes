# Ubuntu setup with NGINX http/2 and letsencrypt

[Dom’s notes](https://gist.github.com/dominikwilkowski/435054905c3c7abc2badc92a0acff4ba)

# Intro

> This is a basic collection of things I do when setting up a new headless ubuntu machine as a webserver. Following the steps below should give you a reasonable secure server with HTTP/2 support (including ALPN in chrome) and the fast NGINX server. I am happy to add things so leave a comment.


# Basics

After creating the server (droplet on [DigitalOcean](https://m.do.co/c/6dcf64644d97)) log in with

```shell
ssh root@[IP ADDRESS]
```

Once inside the machine set a password for root:

```shell
passwd
```

Update your package manager:

```shell
apt-get update
apt-get upgrade
apt autoremove
apt-get dist-upgrade
```

Install [fail2ban](http://www.fail2ban.org/):

```shell
apt-get install fail2ban
```

Install vim:

```shell
apt-get install vim vim-scripts vim-doc vim-latexsuite vim-gui-common vim-gnome vim-gtk
```

Create a new user (uaer = deploy):

```shell
useradd deploy
mkdir /home/deploy
mkdir /home/deploy/.ssh
chmod 700 /home/deploy/.ssh
```

Give the new user a sudo password:

```shell
passwd deploy
```

Add your ssh key:

```shell
vim /home/deploy/.ssh/authorized_keys         #add your public ssh key here, copy your public ssh key from your local with: `pbcopy < ~/.ssh/id_rsa.pub`, create a new one with `ssh-keygen -t rsa`
chmod 400 /home/deploy/.ssh/authorized_keys   #permissions
chown deploy:deploy /home/deploy -R           #owner
```

And add the user to the superuser group

```shell
visudo
```

Add into the file:

```shell
root    ALL=(ALL:ALL) ALL
deploy  ALL=(ALL:ALL) ALL
```

Disable root login and password authentication

```shell
vim /etc/ssh/sshd_config
```

Edit:

```shell
PermitRootLogin no
PasswordAuthentication no
AllowUsers deploy@(your-ip) deploy@(another-ip-if-any) #you can even whitelist IPs from where you connect from (optional)
```

Restart the ssh service

```shell
service ssh restart
```

Now test your login with the new user in a new shell:

```shell
ssh deploy@[ID ADDRESS]
```


```shell
whoami
```

## If everything works with the deploy login, log out of you root session and close.


----------------------------------------------------------------------------------------------------------------------------

## NOW LOGIN WITH DEPLOY AND INSTALL EVERYTHING WITH DEPLOY

Install [unattended-upgrades](https://packages.debian.org/unattended-upgrades)

```shell
sudo apt-get install unattended-upgrades
```

Edit what is updated:

```shell
sudo vim /etc/apt/apt.conf.d/10periodic
```

Modify so you have:

```shell
APT::Periodic::Update-Package-Lists "1";
APT::Periodic::Download-Upgradeable-Packages "1";
APT::Periodic::AutocleanInterval "7";
APT::Periodic::Unattended-Upgrade "1";
```

and specify what upgrades should be done:

```shell
sudo vim /etc/apt/apt.conf.d/50unattended-upgrades
```

The default here is often fine:

```shell
Unattended-Upgrade::Allowed-Origins {
        "${distro_id}:${distro_codename}-security";
};
```

Now install [logwatch](https://packages.debian.org/search?keywords=logwatch):
_(This will typically install postfix and ask you about your mail setup)_

```shell
sudo apt-get install logwatch
```

Now edit what to do with the logs. I usually have them send to my email to sort them into a folder there:

```shell
sudo vim /etc/cron.daily/00logwatch
```

Modify so you have something like _(change `your@email.tld` to your email)_:

```shell
/usr/sbin/logwatch --output mail --mailto your@email.tld --detail high
```

I like to disable `/run/shm` read/write privileges as they are [not needed in a headless server](https://help.ubuntu.com/community/StricterDefaults):

```shell
sudo vim /etc/fstab
```

And add the following line:

```shell
none            /run/shm        tmpfs   defaults,ro              0       0
```

Install [rootkit](https://en.wikipedia.org/wiki/Rootkit) detection with RKHunter and CHKRootKit:

```shell
sudo apt-get install rkhunter chkrootkit
```

Configure CHKRootKit:

```shell
sudo vim /etc/chkrootkit.conf
```

Modify so you have:

```shell
RUN_DAILY="true"
```

To run RKHunter execute the following command as often as you update `apt-get`:


```shell
sudo rkhunter -c
```

_(This will update it’s database and performe a check)_


# bash

I like to change the shell to bash as that’s what I like.

```shell
sudo apt-get install csh
sudo chsh -s /bin/bash deploy
```

_(see after reconnect)_


# firewall

First make sure you have IPv6 enabled:

```shell
sudo vim /etc/default/ufw
```

It should say:

```shell
IPV6=yes
```

Then make sure you are not locked out:

```shell
sudo ufw allow ssh
```

I would also usually do:

```shell
sudo ufw allow https
sudo ufw allow http
sudo ufw allow ftp
```

Then enable the firewall:

```shell
sudo ufw enable
```

And to make sure you have everything enabled and no useless ports open:

```shell
sudo ufw status
```

To reload the firewall you can:

```shell
sudo ufw reload
```

And to see the log you do:

```shell
sudo grep UFW /var/log/syslog
```


# timezone

To set your correct timezone:

```shell
sudo dpkg-reconfigure tzdata
```

Install the time protocol daemon

```shell
sudo apt-get install ntp
```


# swapfile

*Now as we all have moved to SSD servers we don’t need this anymore. :)*
For legacy reasons:

```shell
get memory with `free -m`
sudo fallocate -l 1G /swapfile
sudo chmod 600 /swapfile
sudo mkswap /swapfile
sudo swapon /swapfile
sudo sh -c 'echo "/swapfile none swap sw 0 0" >> /etc/fstab'
```


# FTP

```shell
sudo apt-get install vsftpd
```

Edit the config:

```shell
sudo vim /etc/vsftpd.conf
```

Modify so you have:

```shell
anonymous_enable=NO
write_enable=YES
```

And restart your service:

```shell
sudo service vsftpd restart
```

_(connection via SFTP)_


# git

Install git if it isn’t already:

```shell
sudo apt-get install git
```

And set your account:

```shell
git config --global user.name "Your Name"
git config --global user.email "youremail@domain.com"
```


# node

Install node and NPM:

```shell
sudo apt-get install nodejs
sudo apt-get install npm
```

Because there was a name clash in the package manager we have to add a symlink to get the `node` namespace working:

```shell
sudo ln -s /usr/bin/nodejs /usr/bin/node
```


# nginx

Install your server of choice. NGINX is what I like:

```shell
sudo apt-get install nginx
```

For HTTP/2 to be supported you will need to check the version of NGINX and openSSL:

```shell
nginx -v
openssl version
```

_NGINX needs to be at least 1.9.5 or above and OpenSSL needs to be at least 1.0.2 or above ([for ALPN to work in chrome](http://blog.chromium.org/2016/02/transitioning-from-spdy-to-http2.html))._

Create a symlink to get to your html folder quickly and give permission:

```shell
sudo ln -s /var/www/html/ /www
sudo chown deploy:deploy /var/www/html/ -R
```

Rename the index file in /var/www/html/
```
mv index.nginx-debian.html index.html
````

And edit the NGINX config:

```shell
sudo vim /etc/nginx/sites-available/default
```

To delete a page in `vim` just type `:1,$d` in the command prompt. I usually write the config locally and then just past it into vim. So: copy content, open file in vim, do `:1,$d` to delete contents, type `i` to set vim into insert mode, paste.

* To setup new domain in the ngixn config do [listen 80 first](first_ngnix_congif.sh) -> then get a certificate -> then do the redirect -> disable http -> get the other certificate)

_(I’ve attached my [basic config](https://gist.github.com/dominikwilkowski/435054905c3c7abc2badc92a0acff4ba#file-default) that works for me below, note that this config assumes SSL and the cypher below)_

To create a strong cypher:

```shell
sudo mkdir /etc/nginx/ssl/
sudo openssl dhparam -out /etc/nginx/ssl/dhparam.pem 4096
```

 _(This takes some time)_

Restart the server with the new config:

```shell
sudo nginx -s reload
```


# letsencrypt

Install letsencrypts [certbot](https://certbot.eff.org/) because we want to add SSL to our website?

```shell
sudo apt-get update
sudo apt-get install software-properties-common
sudo add-apt-repository ppa:certbot/certbot
sudo apt-get update
sudo apt-get install python-certbot-nginx
```

Now let's run the certbot

```shell
sudo certbot --nginx certonly
```

Set up auto renewal. Open crontab:

```shell
sudo crontab -e
```
Crontab Guru: [0 1 8-14,22-28 * Mon](https://crontab.guru/#0_1_8-14,22-28_*_Mon)

And add the three following lines:

```shell
SHELL=/bin/bash # I like bash *** SHOULD THIS LINE BE ADDED TO THE SAME FILE? ***

# add timestamp to your log file for easier parsing
0 1 5-12,22-28 * Mon date >> /var/log/letsencrypt-renewal.log 2>&1

# runs every Monday at 1:02AM, output is saved to /var/log/letsencrypt-renewal.log
2 1 5-12,22-28 * Mon certbot renew >> /var/log/letsencrypt-renewal.log 2>&1

# restart the server 1:30AM
30 1 5-12,22-28 * Mon /etc/init.d/nginx restart >> /var/log/letsencrypt-renewal.log 2>&1
# empty line at the end so cron doesn’t ignore the last command
```

This will try to renew your certs every Monday night and restart NGINX while saving the log into `/var/log/letsencrypt-renewal.log`. _(Check your log if you have issues with `vim /var/log/letsencrypt-renewal.log`)_

Now test your SSL certs via:
* [SSLLabs](https://www.ssllabs.com/ssltest/analyze.html)
* [ALPN support](https://tools.keycdn.com/http2-test)
* [http observatory](https://mozilla.github.io/http-observatory-website/)
* [security headers](https://securityheaders.io)
* [encryption strength](https://tls.imirhil.fr/)

# CRON Job Graph



 ┌────────── minute (0 - 59)
 │ ┌──────── hour (0 - 23)
 │ │ ┌────── day of month (1 - 31)
 │ │ │ ┌──── month (1 - 12)
 │ │ │ │ ┌── day of week (0 - 6 => Sunday - Saturday, or
 │ │ │ │ │                1 - 7 => Monday - Sunday)
 ↓ ↓ ↓ ↓ ↓
 * * * * * command to be executed

# DNS clean browser cache
On Firefox type in the address bar:

```
about:config
```
look up for network.dnsCacheExpiration and set this values to `0`

On your local terminal type:

```sh
sudo dscacheutil -flushcache
sudo killall -HUP mDNSResponder
```


TODO:

End result:

	x.com -> static
	x.com/api -> node
	staging.x.com -> node

1. CRON Job - Create and renew SSL cretificate
2. Instal Keystone and Mongo (run a graphql server)
localhost: 8080 - keystone
