#!/usr/bin/env bash

docker rm -f $(docker ps -a | grep user-service | awk '{print $1}') || echo 'Not existed'
docker run -it  --name user-service -p 8081:8081 -v /var/jenkins_home/workspace/ols-core-server-pipeline:/app -w /app java:8 /bin/bash

