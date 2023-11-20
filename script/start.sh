#!/usr/bin/env bash
# 登录服务器, 查看服务状态, 如果服务没启动, 就启动, 如果服务已经启动了就重启
IP=$1
PORT=$2
USER=$3
PASSWORD=$4
DIR=$5
TARGET_DIR=$6


# 登录服务器
ssh -i "$PASSWORD" "USER@IP" << EOF
    # 检查服务状态

    # sh /data/ju/bin/wcs status 并接受返回值, 如果是 1就重启服务, 如果是0就启动服务

EOF