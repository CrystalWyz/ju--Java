#!/usr/bin/env bash
# 将target目录下的 app.*.jar 文件 和 config 目录, 上传到服务器 ip port 上
IP=$1
PORT=$2
USER=$3
PASSWORD=$4
DIR=$5
TARGET_DIR=$6
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

echo "开始准备上传文件 ${DIR}"
# 上传文件
# 遍历 target 目录, 将所有的文件和目录打印出来
cd "$DIR" || exit

echo "Files and directories in the target directory:"
# 服务启动脚本
scp_upload 'ju/bin'
# 上传非自己代码的依赖包
scp_upload 'ju/lib'

scp_upload 'ju/config'
scp_upload 'ju/celib'
for file in $(ls); do
  # 如果是 *.jar 就上传服务
  if [[ $file == app*.jar ]]; then
    scp_upload $file
  fi
done

echo "上传完成!"
