# Docker

## 安装

* 安装工具包
  `yum install -y yum-utils device-mapper-persistent-data lvm2`

* 设置yum源
  `yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo`

* 查看所有仓库中所有docker版本，并选择特定版本安装
  `yum list docker-ce --showduplicates | sort -r`

* 安装最新版的docker
  `yum -y install docker-ce`


* 启动docker
  `systemctl start docker`

* 重启守护进程
  `systemctl daemon-reload`

* 将docker开机自启动
  `systemctl enable docker`

* 卸载docker
  `yum -y remove docker-engine`


* 添加镜像地址 这个是我自己在阿里云申请的地址
  `vi /etc/docker/daemon.json`

  ```json
  {
  "registry-mirrors": ["https://3f4qcs5f.mirror.aliyuncs.com"]
  }

  ```

  ​

## Dockerfile 实战

```shell
FROM centos

MAINTAINER cool<lonely@zxc.com>

ADD apache-tomcat-8.5.37.tar.gz /usr/local

ADD jdk-8u111-linux-x64.tar.gz /usr/local

RUN yum -y install vim

ENV MYPATH /usr/local

WORKDIR $MYPATH

ENV JAVA_HOME /usr/local/jdk1.8.0_111

ENV CLASSPATH JAVA_HOME/lib/dt.jar:JAVA_HOME/lib/tools.jar

ENV CATALINA_HOME /usr/local/apache-tomcat-8.5.37

ENV CATALINA_BASE /usr/local/apache-tomcat-8.5.37

ENV PATH PATH:JAVA_HOME/bin:CATALINA_HOME/lib:CATALINA_HOME/bin

EXPOSE 8080

CMD /usr/local/apache-tomcat-8.5.37/bin/startup.sh && tail -f /usr/local/apache-tomcat-8.5.37/logs/catalina.out
```



创建并启动容器：

```shell
docker build -t mytomcat:8.5 .
```

```shell
docker run -d -p 8080:8080 --name tomcat8 \

 -v /opt/docker/tomcat/test:/usr/local/apache-tomcat-8.5.37/webapps/test \

 -v /opt/docker/tomcat/logs:/usr/local/apache-tomcat-8.5.37/logs \

 --privileged=true tomcat:8.5

```

## 备份、恢复、迁移

```shell
#创建docker 容器的快照
docker commit -p container_id myshowdoc

#将此快照备份到本地jar包
docker save -o ~/myshowdoc.tar myshowdoc

#使用docker加载备份的容器
docker load -i ~/myshowdoc.tar

#将容器运行起来
docker run -d -p 4999:80 myshowdoc
```



