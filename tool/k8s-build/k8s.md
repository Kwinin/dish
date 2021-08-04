# [k8s](https://blog.csdn.net/shenhonglei1234/article/details/111415835)
### [视频](https://www.bilibili.com/video/BV1A5411g7pA?p=21)
## 1.基础工具
```shell
    # ssh 拷贝
    ssh-keygen
    ssh-copy-id 10.211.55.9
    ssh-copy-id 10.211.55.10
    
    sudo apt install selinux-utils
    setenforce 0
    
    # 临时关闭
    swapoff -a
    free -h
    # 永久关闭：注释swap挂载
    vim /etc/fstab
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

## 2.安装docker-ce
```shell
# (安装 Docker CE)
## 设置仓库:
### 安装软件包以允许 apt 通过 HTTPS 使用存储库
sudo apt-get update && sudo apt-get install -y \
  apt-transport-https ca-certificates curl software-properties-common gnupg2

```

```shell
### 新增 Docker 的官方 GPG 秘钥:
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
#如果上面的地址不能下载，可以使用国内镜像
### 新增 Docker 的 中国科学技术大学 GPG 秘钥:
curl -fsSL https://mirrors.ustc.edu.cn/docker-ce/linux/ubuntu/gpg  | sudo apt-key add -

```

```shell
### 添加 Docker apt 仓库:
sudo add-apt-repository \
  "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) \
  stable"

```

```shell
sudo add-apt-repository "deb [arch=amd64] http://mirrors.aliyun.com/docker-ce/linux/ubuntu $(lsb_release -cs) stable"

## 安装 Docker CE
sudo apt-get update && sudo apt-get install -y \
  containerd.io=1.2.13-2 \
  docker-ce=5:19.03.11~3-0~ubuntu-$(lsb_release -cs) \
  docker-ce-cli=5:19.03.11~3-0~ubuntu-$(lsb_release -cs)
#或者下面的命令安装也是一样的。
sudo apt-get update
sudo apt-get install -y docker-ce docker-ce-cli containerd.io  

```

```shell
#kubernetes 官方建议 docker 驱动采用 systemd，当然可以不修改，只是kubeadm init时会有warning提示
# 设置 Docker daemon
cat <<EOF | sudo tee /etc/docker/daemon.json
{
 "registry-mirrors": [
    "https://8w8o6h4k.mirror.aliyuncs.com"
  ],
  "exec-opts": ["native.cgroupdriver=systemd"],
  "log-driver": "json-file",
  "log-opts": {
    "max-size": "100m"
  },
  "storage-driver": "overlay2"
}
EOF


```

```shell
# Create /etc/systemd/system/docker.service.d
sudo mkdir -p /etc/systemd/system/docker.service.d
```

```shell
# 重启 docker.
sudo systemctl daemon-reload
sudo systemctl restart docker
# 查看信息
docker version
docker info | grep "Cgroup Driver"
```

```shell
# 开机即启动 docker 服务
sudo systemctl enable docker
```

## 3.安装 kubeadm、kubelet 和 kubectl

```shell
vi /etc/apt/sources.list
deb https://mirrors.aliyun.com/kubernetes/apt kubernetes-xenial main
```

```shell
curl https://mirrors.aliyun.com/kubernetes/apt/doc/apt-key.gpg | sudo apt-key add -

sudo apt-get update
```

```shell
sudo apt-get install -y kubelet kubeadm kubectl

kubelet --version
```

```shell
systemctl start kubelet

```

```shell
#取最新版所需镜像列表
kubeadm config images list


k8s.gcr.io/kube-apiserver:v1.21.3
k8s.gcr.io/kube-controller-manager:v1.21.3
k8s.gcr.io/kube-scheduler:v1.21.3
k8s.gcr.io/kube-proxy:v1.21.3
k8s.gcr.io/pause:3.4.1
k8s.gcr.io/etcd:3.4.13-0
k8s.gcr.io/coredns/coredns:v1.8.0
```

```shell
# 由于coredns阿里云镜像里没有，从docker.hub拉取,再tag
kubeadm config images list
docker pull coredns/coredns:1.8.0
docker tag coredns/coredns:1.8.0 registry.aliyuncs.com/google_containers/coredns:v1.8.0

kubeadm init --kubernetes-version=v1.21.0 --image-repository registry.aliyuncs.com/google_containers --pod-network-cidr=10.244.0.0/16

```

```
        kubeadm join 10.211.55.6:6443 --token p3ax4a.i7d62ccliw4tmg3p \
        --discovery-token-ca-cert-hash sha256:78f6a76c62a8264d004668383756401137600f2ad3ffa5a9480e9d2d7f762bab 
        
        # join 失败请执行
        kubeadm reset
```
若执行失败，可执行如下命令，清除执行 init 产生的垃圾
```shell
kubeadm reset
rm -rf /etc/kubernetes
```
安装flannel组件
```shell
kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yml

kubectl apply -f kube-flannel.yml
```
## 4.DashBoard
```shell 
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.0.0/aio/deploy/recommended.yaml

kubectl apply -f recommended.yaml 
```
service的类型是ClusterIP只能在集群内部访问，我们需要将类型修改为NodePort以便外部访问
```shell
 kubectl edit svc kubernetes-dashboard -n kubernetes-dashboard
修改 type => NodePort
```
查看这个service的端口
```shell
kubectl get pod -o wide -n kubernetes-dashboard
kubectl get svc -o wide -n kubernetes-dashboard
```
进入页面需要键入
```
thisisunsafe
```
创建dashboard用户并获取token
```shell
kubectl create serviceaccount dashboard -n default
kubectl create clusterrolebinding dashboard-admin -n default  --clusterrole=cluster-admin  --serviceaccount=default:dashboard
kubectl describe secret dashboard | awk '$1=="token:"{print $2}'
```

## 使用命令

获取加入命令
```shell
kubeadm token create --print-join-command
# kubeadm join 10.211.55.6:6443 --token 2a8fm5.sawm9lohwes7xr4n --discovery-token-ca-cert-hash sha256:78f6a76c62a8264d004668383756401137600f2ad3ffa5a9480e9d2d7f762bab 
```
只有master节点上才能执行kubectl get nodes, nodes 下如何查看的，执行以下命令
```
scp /root/.kube/config 10.211.55.9:/root/.kube/
```

---

角色打标签

```shell
root@master:~# kubectl get nodes
NAME     STATUS   ROLES                  AGE    VERSION
node1    Ready    <none>                 121m   v1.21.3
node2    Ready    <none>                 123m   v1.21.3
ubuntu   Ready    control-plane,master   16h    v1.21.3
```

```shell
kubectl label node node1 node-role.kubernetes.io/worker=worker
```
得到：
```shell
root@master:~# kubectl get nodes
NAME     STATUS   ROLES                  AGE    VERSION
node1    Ready    worker                 124m   v1.21.3
node2    Ready    <none>                 126m   v1.21.3
ubuntu   Ready    control-plane,master   16h    v1.21.3
```

---
查询：

```shell
kubectl describe deployment nginx
```

删除：
```shell
kubectl delete svc nginx
kubectl delete deployment nginx
```

## 使用事例
#### nginx-deployment.yaml
```shell
apiVersion: apps/v1
kind: Deployment
metadata:
  name: nginx-deployment
  namespace: default
  labels:
    web: nginx
spec:
  replicas: 2
  selector:
    matchLabels:
      app: nginx
  template:
    metadata:
      labels:
        app: nginx
    spec:
      containers:
      - name: nginx
        image: nginx:latest
        imagePullPolicy: IfNotPresent
        ports:
        - containerPort: 80
```

#### nginx-svc.yaml 
```shell
apiVersion: v1
kind: Service
metadata:
  name: nginx-service
  labels:
    app: nginx
spec:
  type: NodePort
  ports:
  - protocol: TCP        
    port: 3000
    targetPort: 80
    nodePort: 30001
  selector:
    app: nginx
```

#### 创建
```
kubectl create -f nginx-deployment.yaml 
kubectl create -f nginx-svc.yaml 
```
