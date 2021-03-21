FROM openjdk:8-jre-alpine

WORKDIR root/myapp/

ADD target/oauth2-server-0.0.1-SNAPSHOT.jar app.jar

ENV JAVA_OPTS=" -Xmx700m -Xms700m -Xmn250m"

RUN  ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime  && echo "Asia/Shanghai" > /etc/timezone

ENTRYPOINT java $JAVA_OPTS  -jar app.jar
