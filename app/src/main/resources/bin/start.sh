#!/bin/bash

# 设置应用程序路径
APP_PATH="/data/ju"

# 进入应用程序所在目录
cd "$APP_PATH" || exit

# 查找符合条件的JAR文件并启动
for app_jar in app*.jar; do
  if [[ -f "$app_jar" ]]; then
    echo "启动应用程序: $app_jar"
    java -jar "$app_jar" &
  fi
done
