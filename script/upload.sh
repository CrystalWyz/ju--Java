#!/usr/bin/env bash
# 将target目录下的 app.*.jar 文件 和 config 目录, 上传到服务器 ip port 上
# 将服务的jar包分成了三个模块
# app-xxx.jar 祝启动类
# lib 目录下的jar包   --- 开发需要依赖的jar
# celib 目录下的jar包 --- 开发更新的核心包
# config 项目配置文件

# 服务器IP
IP=$1
# 服务器端口
PORT=$2
# 服务器登录用户
USER=$3
# 服务器登录密码
PASSWORD=$4
# 本地文件目录
DIR=$5
# 服务器目标目录
TARGET_DIR=$6
# 删除旧文件命令
delete_old_file_command="rm -rf ${TARGET_DIR}celib ${TARGET_DIR}config ${TARGET_DIR}app*.jar"
echo $delete_old_file_command
# 重启服务命令
restart_command="sh ${TARGET_DIR}bin/wcs restart"
echo $restart_command
echc"开始准备对目标服务器 ${IP} 进行操作, 将本地的 ${DIR} 目录上传到 ${TARGET_DIR} 目录下"

# 写一个用scp命令上传文件和文件夹的方法:
# 参数: 文件目录或者文件名
function scp_upload() {
  file_path=$DIR$1
  echo " 上传文件/目录: $file_path"
  # 判断是文件还是目录
  if [ -d "$file_path" ]; then
    # 是目录
    expect -c "
    set timeout 3;
    spawn scp -r -P ${PORT} ${file_path} ${USER}@${IP}:${TARGET_DIR};
    expect {
      *assword* {
        send ${PASSWORD}\r;
      }
    }
    interact
    "
  elif [ -f "$file_path" ]; then
    # 是文件
    # 上传文件
    expect -c "
        set timeout 3;
        spawn scp -P ${PORT} ${file_path} ${USER}@${IP}:${TARGET_DIR};
        expect {
          *assword* {
            send ${PASSWORD}\r;
          }
        }
        interact
        "
  else
    echo " 上传失败, 未知的文件类型: $file_path"
  fi
}

executorCommand() {
  local comm="$1"
  echo "需要执行的命令: ${comm}"
  expect -c"
    set timeout 3;
    spawn ssh -p ${PORT} ${USER}@${IP}
    expect {
      *assword* {
            send ${PASSWORD}\r;
      }
    }
    expect \"*#\"
    send \"${comm}\r\"
    send \"exit\r\"
    interact
  "
}

echo "开始准备删除旧文件"
# 删除要更新的 config、celib、app*.jar文件和目录
# TODO 等服务稳定应该考虑备份而不是直接删除
executorCommand "${delete_old_file_command}"

echo "开始准备上传文件 ${DIR}"
# 上传文件
# 遍历 target 目录, 将所有的文件和目录打印出来
cd "$DIR" || exit

echo "Files and directories in the target directory:"
# 服务启动脚本
#scp_upload 'ju/bin'
## 上传非自己代码的依赖包
#scp_upload 'ju/lib'

scp_upload 'ju/config'
scp_upload 'ju/celib'
for file in $(ls); do
  # 如果是 *.jar 就上传服务
  if [[ $file == app*.jar ]]; then
    scp_upload $file
  fi
done

echo "上传完成!"

echo "开始准备重启服务"
executorCommand "${restart_command}"
echo "重启完成"

