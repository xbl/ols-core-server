#!/usr/bin/env bash


docker run --rm -v ${WORKSPACE}:/app -w /app java:8 /bin/bash -c "./gradlew clean build -x test"