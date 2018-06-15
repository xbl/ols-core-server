#!/usr/bin/env bash

docker run --rm -v $(pwd):/app -w /app java:8 /bin/bash -c "./gradlew clean test"
