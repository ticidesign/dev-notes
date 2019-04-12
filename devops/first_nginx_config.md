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

# ssl and http2 config
#
server {
  listen       80;
  listen       [::]:80;
  server_name  dseatondesign.com;
  root         /var/www/html/;

#   ssl on;
#   ssl_certificate      /etc/letsencrypt/live/dseatondesign.com/fullchain.pem;
#   ssl_certificate_key  /etc/letsencrypt/live/dseatondesign.com/privkey.pem;

#   ssl_session_timeout  1d;
#   ssl_session_cache    shared:SSL:50m;
#   ssl_session_tickets  off;

#   ssl_protocols              TLSv1 TLSv1.1 TLSv1.2;
#   ssl_prefer_server_ciphers  on;
#   ssl_dhparam                /etc/nginx/ssl/dhparam.pem;
#   ssl_ciphers 'ECDHE-ECDSA-AES256-GCM-SHA384:ECDHE-RSA-AES256-GCM-SHA384:ECDHE-ECDSA-CHACHA20-POLY1305:ECDHE-RSA-CHACHA20-POLY1305:ECDHE-ECDSA-AES128-GCM-SHA256:ECDHE-RSA-AES128-GCM-SHA256:ECDHE-ECDSA-AES256-SHA384:ECDHE-RSA-AES256-SHA384:ECDHE-ECDSA-AES128-SHA256:ECDHE-RSA-AES128-SHA256';  #generate here: https://mozilla.github.io/server-side-tls/ssl-config-generator/

#   # OCSP Stapling ---
#   # fetch OCSP records from URL in ssl_certificate and cache them
#   ssl_stapling         on;
#   ssl_stapling_verify  on;

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
