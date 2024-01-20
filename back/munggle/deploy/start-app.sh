#!/bin/bash

profiles="${ENV}, common"

base_config="classpath:/application.yaml"
mounted_configs=$(ls ./config/*.yaml | paste -sd, -)
configs="$base_config, $mounted_configs"

java -jar ./app.jar \
  --spring.profiles.active="$profiles" \
  --spring.config.location="$configs"
