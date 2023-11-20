#!/usr/bin/env bash

######################################################################
# 本脚本适用于使用spring-boot-maven-plugin打包的项目, 并且目录遵循以下结构,
# 其中boot.jar名称可以改变, 但默认在项目目录下只能有一个.
# bin/           --- 启动脚本
# config/        --- 配置文件
# celib/         --- wcs-core 和 ce-lib 依赖
# lib/           --- 第三方依赖
# boot.jar       --- SpringBoot打包出来的jar
# logs/          --- 日志目录
#
# 本脚本非后台运行Java程序, 与Supervisor结合使用效果更佳.

# Supervisor的实例配置文件如下 (注意: 每行最前边的#和空格需要删掉)

# [program:nce-wcs]
# command=/data/nce-wcs/bin/wcs_start.sh
# autostart=true
# autorestart=true
# priority=1
# startsecs=10
# startretries=3
# stdout_logfile=/data/nce-wcs/logs/super_std.log
# stdout_logfile_maxbytes=100MB
# redirect_stderr=true
# killasgroup=true
# stopasgroup=true
#
######################################################################

APP_NAME="nce-wcs"
# 如果项目目录只有一个jar, 不需要填写. 如果有值则直接使用. 注意: 只写文件名, 不要带路径.
BOOT_JAR=""
# 远程调试端口, 不配置不启用
DEBUG_PORT=

# discover BASE_DIR
BIN_FILE=$(readlink -f ${0})
cd $(dirname "${BIN_FILE}")
BIN_DIR=$(pwd)
BASE_DIR=$(cd ..;pwd)
# echo "BASE_DIR: ${BASE_DIR}"

# 其他方式
#BASE_DIR=`dirname "$0"`/..
#BASE_DIR=`(cd "$BASE_DIR"; pwd)`
#ls -l "$0" | grep -e '->' > /dev/null 2>&1
#if [ $? = 0 ]; then
#  #this is softlink
#  _PWD=`pwd`
#  _EXEDIR=`dirname "$0"`
#  cd "$_EXEDIR"
#  _BASENAME=`basename "$0"`
#  _REALFILE=`ls -l "$_BASENAME" | sed 's/.*->\ //g'`
#   BASE_DIR=`dirname "$_REALFILE"`/..
#   BASE_DIR=`(cd "$BASE_DIR"; pwd)`
#   cd "$_PWD"
#fi

# 第三方依赖目录
THIRD_PART_LIB_DIR=${BASE_DIR}/lib
# core和ce-lib依赖目录
CE_LIB_DIR=${BASE_DIR}/celib
# -Dloader.path的值
LIB_DIR="${THIRD_PART_LIB_DIR},${CE_LIB_DIR}"
#echo "LIB_DIR: ${LIB_DIR}"

# java: 优先使用17
if [ -x "$(command -v "/usr/lib/jvm/temurin-17-jdk/bin/java" )" ]
then
  JAVA_CMD=/usr/lib/jvm/temurin-17-jdk/bin/java
else
  JAVA_CMD=java
fi

# 检查java是否安装
if ! [ -x "$(command -v $JAVA_CMD)" ]
then
  echo -e "没有找到指定的java: ${JAVA_CMD}"
  exit 1
fi

JAVA_OPTS="-server"
JAVA_OPTS="${JAVA_OPTS} -Xmx2g"
JAVA_OPTS="${JAVA_OPTS} -Xms512m"
JAVA_OPTS="${JAVA_OPTS} -XX:SurvivorRatio=8"
JAVA_OPTS="${JAVA_OPTS} -XX:MetaspaceSize=256m"
JAVA_OPTS="${JAVA_OPTS} -XX:MaxMetaspaceSize=512m"
JAVA_OPTS="${JAVA_OPTS} -XX:+DisableExplicitGC"
JAVA_OPTS="${JAVA_OPTS} -XX:+UseG1GC"
JAVA_OPTS="${JAVA_OPTS} -XX:G1HeapRegionSize=32m"
JAVA_OPTS="${JAVA_OPTS} -XX:G1ReservePercent=30"
# JAVA_OPTS="${JAVA_OPTS} -XX:MaxGCPauseMillis=250"
JAVA_OPTS="${JAVA_OPTS} -XX:SoftRefLRUPolicyMSPerMB=1000"
JAVA_OPTS="${JAVA_OPTS} -XX:StringTableSize=500000"
JAVA_OPTS="${JAVA_OPTS} -XX:-OmitStackTraceInFastThrow"
JAVA_OPTS="${JAVA_OPTS} --add-opens=java.base/java.lang=ALL-UNNAMED"
JAVA_OPTS="${JAVA_OPTS} -Dloader.path=${LIB_DIR}"
#JAVA_OPTS="${JAVA_OPTS} -Dspring.profiles.active=${PROFILES}"

if [ -n "$DEBUG_PORT" ]
then
  port_existed=`lsof -i:${DEBUG_PORT} | wc -l`
  if ! [ "$port_existed" -eq "0" ];then
    echo -e "DEBUG_PORT not available: ${DEBUG_PORT}"
    exit 1
  else
	JAVA_OPTS="${JAVA_OPTS} -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=${DEBUG_PORT}"
	JAVA_OPTS="${JAVA_OPTS} -Xdebug"
  fi
fi

JAVA_OPTS="${JAVA_OPTS} -Dapp.name=${APP_NAME}"
#JAVA_OPTS="${JAVA_OPTS} -Dapp.pid=$$"
JAVA_OPTS="${JAVA_OPTS} -Dapp.basedir=${BASE_DIR}"

start() {
	cd "${BASE_DIR}"
	if [ -z "${BOOT_JAR}" ];then
		BOOT_JAR=$(cd "${BASE_DIR}";ls *.jar)
	fi

	echo "JAVA_OPTS: ${JAVA_OPTS}"
	echo "BOOT_JAR: ${BOOT_JAR}"
	echo "JAVA_CMD: ${JAVA_CMD}"
	eval "${JAVA_CMD}" "${JAVA_OPTS}" -jar "${BOOT_JAR}"
}

start
