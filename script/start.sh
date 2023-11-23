#!/usr/bin/env bash
#IP=$1
IP="59.110.34.78"
PORT=$2
#USER=$3
USER="root"
PASSWORD=$4
PASSWORD="Xxzs990416"
# 服务器信息
# 连接到服务器并执行脚本
echo "连接服务器 并执行重启脚本"
# 打印连接的服务器
echo "服务器信息: $USER@$IP"
#!/usr/bin/expect -f

# 服务器信息
set server_address "59.110.34.78"
set username "root"
set password "Xxzs990416"
set remote_script_path "/data/ju/bin/wcs"


expect -c"
  set timeout 3;
  spawn ssh -p 22 root@59.110.34.78
  expect {
    *assword* {
          send Xxzs990416\r;
    }
  }
  expect \"*#\"
  send \"sh /data/ju/bin/wcs restart\r\"
  send \"exit\r\"
  interact
"