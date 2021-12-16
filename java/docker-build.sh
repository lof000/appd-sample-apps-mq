#!/bin/bash

echo "-----------------------------------------"
echo "Building ...." 
echo "-----------------------------------------"

docker build -f DockerfileSend -t spring-mq-send .
docker build -f DockerfileRecv -t spring-mq-recv .


