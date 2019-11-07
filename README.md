# RestTemplate

#### 介绍
{**以下是码云平台说明，您可以替换此简介**
码云是 OSCHINA 推出的基于 Git 的代码托管平台（同时支持 SVN）。专为开发者提供稳定、高效、安全的云端软件开发协作平台
无论是个人、团队、或是企业，都能够用码云实现代码托管、项目管理、协作开发。企业项目请看 [https://gitee.com/enterprises](https://gitee.com/enterprises)}

#### 软件架构
软件架构说明


#### 安装教程

1.  更新代码下来后，使用idea打开进行运行测试
 

#### 使用说明

1.  目前使用的端口是8089(具体看配置文件的配置)。
2.  可调用的接口URL: http://localhost:8089/rest/service/match
    Header参数：
    设置请求Body数据格式：ContentType =application/xml 或者ContentType =application/xml 
    设置响应接收数据格式：Accept=application/xml 或者Accept=application/json
    
    body参数（示例）为：
     {"age": 0,"id": 0,"name": "string","sex": "string"}
     
3.  swagger ui url: http://localhost:8080/swagger-ui.html 或 http://localhost:8080/

4.  camel-context.xml配置文件中：
        direct:jsonToXmlFormatData是json数据格式转换成xml的配置
        direct:xmlToJsonFormatData是xml数据格式转换成json的配置
        
5.  其中的<choice>是根据接口的实际情况进行配置的：
         如果目标接口只能使用json/xml参数（其中之一）进行请求（或者返回数据格式只能是其一），则可以使用choice.
         如果目标接口在json/xml都可以使用则可以不需要进行数据格式转换了。


#### 参与贡献

1.  Fork 本仓库
2.  新建 master 分支
3.  提交代码
4.  新建 Pull Request


#### 码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
