#!/bin/bash
docker stop openldap
echo '容器已停止'

docker rm openldap
echo '容器已删除'

docker run -d \
 --name openldap \
 --restart=on-failure:3 \
 -p 389:389 \
 -p 636:636 \
 --network bridge \
 --hostname openldap-host \
 --env LDAP_ORGANISATION="example" \
 --env LDAP_DOMAIN="example.com" \
 --env LDAP_ADMIN_PASSWORD="123456" \
 osixia/openldap

echo '容器已启动'
