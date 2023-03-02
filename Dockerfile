FROM amazoncorretto:latest

RUN apt-get clean && apt-get update && apt-get -y dist-upgrade
RUN apt-get -y install redis awscli unzip && apt-get clean

RUN groupadd -r -g 2036 app_user && useradd -m -u 2036 -g 2036 app_user -s /bin/bash

RUN mkdir -p /var/www/app /logs
WORKDIR /var/www.app
