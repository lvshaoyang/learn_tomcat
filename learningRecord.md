阅读记录
```
2018年10月16日23:41:47
chapter1  开始一个简单的web容器吧
```
2018年10月21日23:1:21
HTTP是一种协议，允许web服务器和浏览器通过互联网来发送和接收数据。客户端请求一个文件而服务器响应请求。
```
HTTP请求
方法——统一资源定位符（URI）——协议/版本
请求的头部
主体内容

URI通常是相对服务器的根目录解释，因此，始终以斜线开头
````

2018年10月26日0:0:4
```
要使用javaEE的servlet包，就算是要构造tomcat也要使用。其实tomcat已经引入 了jsｐ和ｓervlet的相关包了。这应该就是javaEE是怎么用了。
只是提供了一个类似javaee的jar包了吧。
```
2018年10月27日6:41:53   chapter2
```
servlet有5个方法，其中init,service,destory是servlet的生命周期方法，在servlet类已经初始化后,init方法将被调用，servlet容器只调用一次，
以此表明servlet已经被加载进服务中，此方法可以重写用来执行那些只要执行一次的代码，例如加载数据库驱动，值初始化等。
```
2018年11月13日23:20:10
```
URL类，构造方法
URL就是一个WWW网上资源定位符，可以用来指向一个文件或者一个文件夹等

本例中用的构造方法
URL(String protocol, String host, String file)   使用file协议
Creates a URL from the specified protocol name, host name, and file name 
根据协议名主机名和文件名创建一个URL对象

URL(URL context, String spec) 
Creates a URL by parsing the given spec within a specified context
根据指定的上下文创建一个URL对象，spec中可以是全路径，此是context可以是Null

URLStreamHandler类介绍：
抽象类
The abstract class URLStreamHandler is the common superclass for all stream protocol handlers. 
A stream protocol handler knows how to make a connection for a particular protocol type, such as http or https
In most cases, an instance of a URLStreamHandler subclass is not created directly by an application. 
Rather, the first time a protocol name is encountered when constructing a URL, the appropriate stream protocol handler is automatically loaded.
URLSteamHandler不会通过应用程序来创建一个URLStreamHandler类或其子类的实例，而是在创建URL实例的时候，会自动加载相应的URLStreamHandler.

File类的其中一个方法，
public String getCanonicalPath()
                        throws IOException
A canonical pathname is both absolute and unique.
This method first converts this pathname to absolute form if necessary,
 as if by invoking the getAbsolutePath() method, and then maps it to its unique form in a system-dependent way
此方法转换路径名到绝对形式，如果需要的，好像调用了getAbsolutePath()方法一样，然后将这个路径以系统的形式映射到唯一形式

URLClassLoader类：
public URLClassLoader(URL[] urls)
其中urls是一个数组，这些对象指向了加载类时候查找的位置，任何以"/"结尾的URL都假设是一个目录。，否则会假定是一个jar文件下载并打开.
```


