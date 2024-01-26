#!/bin/bash

profiles="${ENV}, common"

#base_config="classpath:/application.yaml"
#mounted_configs=$(ls ./config/*.yaml | paste -sd, -)
#configs="$base_config, $mounted_configs"

java -jar ./app.jar \
#  --spring.config.location="$configs" \
  --spring.profiles.active="$profiles"

