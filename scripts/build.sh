#!/usr/bin/env bash


docker run --rm -v $(pwd):/app -w /app frekele/gradle:3.5.1-jdk8 /bin/bash -c "./gradlew clean build -x test"