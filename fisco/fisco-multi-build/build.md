# 一、fisco多机部署
```
docker run -d --net=bridge --name docker_bri1 ubuntu:18.04 /bin/sh -c "while true; do echo hello world; sleep 1; done"
docker run -d --net=bridge --name docker_bri2 ubuntu:18.04 /bin/sh -c "while true; do echo hello world; sleep 1; done"

# 如何使用镜像进行构建
# 构建机构A
docker run -d --net=bridge --name docker_bri3 kwinin/fisco-a:v1.0.0 /bin/sh -c "while true; do echo hello world; sleep 1; done"
# 构建机构B
docker run -d --net=bridge --name docker_bri4 kwinin/fisco-b:v1.0.0 /bin/sh -c "while true; do echo hello world; sleep 1; done"

# 构建webase平台
docker run -d --net=bridge -p 8080:80 -p 5000:5000 --name docker_bri5 kwinin/fisco-webase:v1.0.0 /bin/sh -c "while true; do echo hello world; sleep 1; done"

```
## 1.工具准备
```shell
sudo apt-get update
sudo apt install -y openssl curl
sudo apt install -y net-tools         # ifconfig
sudo apt install -y iputils-ping      # ping
sudo apt install -y vim                 
sudo apt install -y openssh-server
sudo apt install -y git
sudo apt install -y sudo
sudo apt-get install tree
sudo apt-get install lsof
```

下面3步是为了使用root用户通过ssh连接容器。
- 启动ssh并设置服务开机默认启动
```shell
    /etc/init.d/ssh start
    /etc/init.d/ssh restart
```
- 设置ssh连接可以用root用户连接，将PermitRootLogin  改成 PermitRootLogin yes
```shell
    vim  /etc/ssh/sshd_config
```
- 初始化root密码
```shell
    passwd
```
- 新增其他用户
```shell
    adduser kwinin
    usermod -aG sudo kwinin
```
安装telnet
```
sudo apt-get install openbsd-inetd
sudo apt-get install telnetd
sudo /etc/init.d/openbsd-inetd restart
sudo apt-get install telnet
```
---

## 2.下载fisco-bcos二进制文件
```
cd ~/generator/meta
wget https://xiaoyue-blog.oss-cn-hangzhou.aliyuncs.com/fisco-bcos.tar.gz
tar -zxf tar -zxf fisco-bcos.tar.gz
./meta/fisco-bcos -v # 检查版本
```
## 3.fisco文档
[多机部署](https://fisco-bcos-documentation.readthedocs.io/zh_CN/latest/docs/articles/7_community/group_deploy_case.html)

[新增节点](https://fisco-bcos-documentation.readthedocs.io/zh_CN/latest/docs/enterprise_tools/tutorial_one_click.html#id11)

## 4.扩容目录
```shell
cp generator-A generator-A-node2
mkdir ~/generator-A-node2/tmp_one_click_expand/agency_cert -p
cd ~/generator-A-node2/meta
cp agency.crt agency.key ca.crt ~/generator-A-node2/tmp_one_click_expand/agencyA/agency_cert
cp -r ~/generator-A/dir_chain_ca/* ~/generator-A/tmp_one_click_expand/
cp ~/generator-A/nodeA/node_172.17.0.2_30300/conf/group.1.genesis ~/generator-A/tmp_one_click_expand/
cp ~/generator-A/conf/node_deployment.ini ~/generator-A/tmp_one_click_expand/agencyA
vim peers.txt
vim node_deployment.ini
```
```
rm -rf meta/*
cp ~/fisco-bcos meta/
bash ./one_click_generator.sh -e ./tmp_one_click_expand
```
```shell
tmp_one_click_expand/
|-- agencyA
|   |-- agency_cert
|   |   |-- agency.crt
|   |   |-- agency.key
|   |   `-- ca.crt
|   `-- node_deployment.ini
|-- ca.crt
|-- ca.key
|-- ca.srl
|-- group.1.genesis
`-- peers.txt

```
# 二、控制台
## 1.下载console
```shell
./generator --download_console ./ --cdn
```
## 2.ubuntu系统安装java
```
sudo apt install -y default-jdk
```
## 3.拷贝sdk文件
```
cp -n console/conf/config-example.toml console/conf/config.toml
./generator --get_sdk_file ./sdk
cp -r sdk/* console/conf/
# 扩展节点拷贝
cp ~/generator-A-node2/tmp_one_click_expand/agencyA/sdk/*  ~/generator-A-node2/console/conf/
# 如果出现以下错误 需要addObserver 再进入console
Failed to create BcosSDK failed! Please check the node status and the console configuration, error info: create client for group 1 failed! Please check the existence of group 1 of the connected node!
```

## 4.conf 目录
```shell
conf
|-- applicationContext.xml
|-- ca.crt
|-- config-example.toml
|-- config.toml
|-- group-generate-config.toml
|-- log4j.properties
|-- node.crt
|-- node.key
|-- sdk.crt
`-- sdk.key
```
## 5.java环境变量
```shell
sudo apt install -y default-jdk
vim ~/.bashrc 
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
export PATH=$PATH:$JAVA_HOME/bin
source ~/.bashrc 
```

# 三、WeBase
节点管理端需要安装mysql-client
```shell
sudo apt-get install mysql-client //客户端
```
## 1.[WeBASE-Sign](https://webasedoc.readthedocs.io/zh_CN/latest/docs/WeBASE-Sign/install.html)
```shell
# server version
version: v1.5.0

server:
  # 本工程服务端口，端口被占用则修改
  port: 5004
  context-path: /WeBASE-Sign
  tomcat:
    max-threads: 200       #default 200
    max-connections: 10000  #default 10000

spring:
  cache:
    type: simple
  datasource:
    # 数据库连接信息
    url: jdbc:mysql://8.8.8.228:3308/webasesign?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8
    # 数据库用户名
    username: "root"
    # 数据库密码
    password: "123456"
    driver-class-name: com.mysql.cj.jdbc.Driver
    hikari:
      connection-test-query: SELECT 1 FROM DUAL
      connection-timeout: 30000
      maximum-pool-size: 20
      max-lifetime: 1800000
      minimum-idle: 5

constant:
  # aes加密key（16位）
  aesKey: EfdsW23D23d3df43
  # aes加密模式 v1.4.0+ 默认CBC（v1.4.0前默认为ECB）
  aesPattern: CBC
  keepAliveRequests: 100
  syncUsrCacheTaskFixedDelay: 10000
  # 返回值是否支持私钥传输
  supportPrivateKeyTransfer: true

mybatis:
  mapper-locations: classpath:mapper/*.xml

logging:
  config: classpath:log4j2.xml
```
## 2.[WeBASE-Front](https://webasedoc.readthedocs.io/zh_CN/latest/docs/WeBASE-Front/install.html)
```shell
# 编译
chmod +x ./gradlew && ./gradlew build -x test

cp -r conf_template conf

cp ca.crt node.crt node.key /home/kwinin/webase/WeBASE-Front/dist/conf/conf
cd /home/kwinin/webase/WeBASE-Front/dist/conf/conf
mv node.crt sdk.crt
mv node.key sdk.key

# 修改
vim ~/webase/WeBASE-Front/dist/conf/application.yml
nodePath: /fisco/nodes/127.0.0.1/node0 => nodePath: /home/kwinin/generator-A/nodeA/node_172.17.0.2_30300
```

## 3.[WeBASE-Node-Manager](https://webasedoc.readthedocs.io/zh_CN/latest/docs/WeBASE-Node-Manager/install.html)

## 4.[WeBASE-Web](https://webasedoc.readthedocs.io/zh_CN/latest/docs/WeBASE-Web/install.html)