#!/bin/bash
docker stop phpldapadmin
echo '容器已停止'

docker rm phpldapadmin
echo '容器已删除'

docker run -d \
 --name phpldapadmin \
 --restart=on-failure:3 \
 -p 8080:80 \
 --link openldap \
 --privileged \
 --env PHPLDAPADMIN_HTTPS=false \
 --env PHPLDAPADMIN_LDAP_HOSTS=openldap \
 osixia/phpldapadmin

echo '容器已启动'
