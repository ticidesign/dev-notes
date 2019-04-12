# NGINX CONFIG WITH HTTP/2 SUPPORT, CORES, A PROXY TO A NODE APP, HTTP -> HTTPS and WWW -> HTTPS redirects
#
server_tokens  off;

# prevent clickjacking attacks
add_header  X-Frame-Options SAMEORIGIN;

# disallow circumventing declared MIME types
add_header  X-Content-Type-Options nosniff;

# X-XSS-Protection
add_header  X-XSS-Protection '1; mode=block';

# HSTS (ngx_http_headers_module is required) (15768000 seconds = 6 months)
add_header  Strict-Transport-Security 'max-age=31536000; includeSubDomains;' always;

# Content Security Policy
add_header Content-Security-Policy "default-src 'self'; script-src 'self' https://ssl.google-analytics.com https://cdn.polyfill.io; img-src 'self' https://ssl.google-analytics.com; style-src 'self'; font-src 'self'; frame-src 'self'; object-src 'none'";

# CORS
add_header  'Access-Control-Allow-Origin' '*';
add_header  'Access-Control-Allow-Credentials' 'true';
add_header  'Access-Control-Allow-Methods' 'GET, POST, OPTIONS';
add_header  'Access-Control-Allow-Headers' 'DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type';

# node proxy
#
# server {
# 	listen       8080;
# 	server_name  [YOUR IP];  #CHANGE THIS
# 	access_log   /var/log/nginx/nodeApp.log;
# 	error_page   400 401 402 403 404 405 500 501 502 503 504  @error_page;

# 	# fallback page when node app is off
# 	#
# 	location     @error_page {
# 		root       /var/www/html/;
# 		internal;
# 		rewrite ^  [https://domain.tld/error.html];  #CHANGE THIS
# 		break;
# 	}

# 	location / {
# 		proxy_redirect          off;
# 		proxy_pass_header       Server;
# 		proxy_set_header        X-Real-IP $remote_addr;
# 		proxy_set_header        X-Forwarded-For $proxy_add_x_forwarded_for;
# 		proxy_set_header        X-Scheme $scheme;
# 		proxy_set_header        Host $http_host;
# 		proxy_set_header        X-NginX-Proxy true;
# 		proxy_connect_timeout   5;
# 		proxy_read_timeout      240;
# 		proxy_intercept_errors  on;

# 		proxy_pass              http://127.0.0.1:1337; #the nodeApp is listening on port 1337 internally only (make sure port 1337 is denied by ufw)
# 	}
# }

# http to https redirect
#
server {
  server_name  www.dseatondesign.com dseatondesign.com;
  root         /var/www/html/;
  return 301   https://dseatondesign.com$request_uri;
}

# www to https redirect
#
server {
  listen       443 ssl;
  listen       [::]:443 ssl;
  server_name  www.dseatondesign.com;

  ssl on;
  ssl_certificate      /etc/letsencrypt/live/www.dseatondesign.com/fullchain.pem;
  ssl_certificate_key  /etc/letsencrypt/live/www.dseatondesign.com/privkey.pem;
  ssl_session_timeout  1d;
  ssl_session_cache    shared:SSL:50m;
  ssl_session_tickets  off;
  ssl_protocols              TLSv1 TLSv1.1 TLSv1.2;
  ssl_prefer_server_ciphers  on;
  ssl_dhparam                /etc/nginx/ssl/dhparam.pem;
  ssl_ciphers 'ECDHE-ECDSA-AES256-GCM-SHA384:ECDHE-RSA-AES256-GCM-SHA384:ECDHE-ECDSA-CHACHA20-POLY1305:ECDHE-RSA-CHACHA20-POLY1305:ECDHE-ECDSA-AES128-GCM-SHA256:ECDHE-RSA-AES128-GCM-SHA256:ECDHE-ECDSA-AES256-SHA384:ECDHE-RSA-AES256-SHA384:ECDHE-ECDSA-AES128-SHA256:ECDHE-RSA-AES128-SHA256';  #generate here: https://mozilla.github.io/server-side-tls/ssl-config-generator/

  # OCSP Stapling ---
  # fetch OCSP records from URL in ssl_certificate and cache them
  ssl_stapling         on;
  ssl_stapling_verify  on;

  location ~ /\.ht {
    deny  all;
  }

  return 301  https://dseatondesign.com$request_uri;
}

# ssl and http2 config
#
server {
  listen       443 ssl http2;
  listen       [::]:443 ssl http2;
  server_name  dseatondesign.com;
  root         /var/www/html/;

  ssl on;
  ssl_certificate      /etc/letsencrypt/live/dseatondesign.com/fullchain.pem;
  ssl_certificate_key  /etc/letsencrypt/live/dseatondesign.com/privkey.pem;

  ssl_session_timeout  1d;
  ssl_session_cache    shared:SSL:50m;
  ssl_session_tickets  off;

  ssl_protocols              TLSv1 TLSv1.1 TLSv1.2;
  ssl_prefer_server_ciphers  on;
  ssl_dhparam                /etc/nginx/ssl/dhparam.pem;
  ssl_ciphers 'ECDHE-ECDSA-AES256-GCM-SHA384:ECDHE-RSA-AES256-GCM-SHA384:ECDHE-ECDSA-CHACHA20-POLY1305:ECDHE-RSA-CHACHA20-POLY1305:ECDHE-ECDSA-AES128-GCM-SHA256:ECDHE-RSA-AES128-GCM-SHA256:ECDHE-ECDSA-AES256-SHA384:ECDHE-RSA-AES256-SHA384:ECDHE-ECDSA-AES128-SHA256:ECDHE-RSA-AES128-SHA256';  #generate here: https://mozilla.github.io/server-side-tls/ssl-config-generator/

  # OCSP Stapling ---
  # fetch OCSP records from URL in ssl_certificate and cache them
  ssl_stapling         on;
  ssl_stapling_verify  on;

  # root server
  #
  location / {
    root   /var/www/html/;
    index  index.html index.htm;
  }

  # deny access to .htaccess files, if Apache's document root
  # concurs with nginx's one
  #
  location ~ /\.ht {
    deny  all;
  }
}
