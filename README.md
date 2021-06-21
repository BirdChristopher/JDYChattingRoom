# JDYChattingRoom

服务端代码存放分支，客户端代码请查看client分支。

#### 源代码：

源代码在back/src下。

#### 数据库：

MSSQL Database文件夹内是项目的SQL Server数据库的数据库迁移文件，欲使用服务端需要先导入数据库，请将导入的数据库名设置为chattingroom。

#### 运行要求：

1.JDK8

2.SDK1.8

3.MyBatis 3.5.5

4.Windows10

5.Microsoft Sql Server 数据库

#### 运行方法：

1.导入数据库文件

2.修改数据库账号：打开back/src/mapper/mybatis-config.xml,按照configuration->environments->environment找到如下区域：

![image](C:\Users\11048\Desktop\JDYChattingRoom\image.png)

修改url处的value为你本地数据库的url，

username的value修改为你数据库的账号

password的value修改为数据库密码。

3.双击Server Start.bat。

#### 注意：

为了让客户端能找到服务器，请查看当前网络下的IPv4地址（有线连接情况下和无线连接情况下的IP地址不同），并告知客户端。

#### Javadoc:

打开javadoc/index.html可查看该项目的javadoc。

##### 其他内容：

有一些Intellij IDEA的配置文件和编译后的class文件，懒得删了。

#### Jar:

本项目的jar包在out目录下。但是没什么用（可能是我不会用）

