#!/usr/bin/env bash

date=$(date '+%Y-%m-%d')

./gradlew assembleRelease && cp ./app/build/outputs/apk/release/app-release-unsigned.apk "./app-build-${date}.apk"
