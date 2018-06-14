#!/usr/bin/env bash

docker rm -f $(docker ps -a | grep user-service | awk '{print $1}') || echo 'Not existed'
docker run --name user-service -p 8081:8081 -v $(pwd):/app -w /app java:8 /bin/bash -c "java -jar /app/build/libs/user-service-0.0.1-SNAPSHOT.jar"

