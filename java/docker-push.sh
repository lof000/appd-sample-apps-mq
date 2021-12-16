#!/bin/bash
docker tag spring-mq-send leandrovo/spring-mq-send:1.0
docker tag spring-mq-recv leandrovo/spring-mq-recv:1.0

docker push leandrovo/spring-mq-send:1.0
docker push leandrovo/spring-mq-recv:1.0
