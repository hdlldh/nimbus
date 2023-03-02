FROM azul/zulu-openjdk-alpine:8

RUN apk add --no-cache bash curl 
RUN curl -Ls https://git.io/sbt > /bin/sbt && chmod 0755 /bin/sbt

RUN mkdir -p /var/www/app /logs
WORKDIR /var/www/app

CMD dist/bin/-web -Dconfig.file=dist/conf/application.conf
