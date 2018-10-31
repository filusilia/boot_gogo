# NS_Repository
ns游戏搜索下载

#### 2018.10.25 ver.0.0.1.BETA
第一版框架搭建
使用springboot2.0
jpa进行数据库同步
tk.mybatis进行数据库操作

* idea控制台乱码解决方案：
    1. idea->help->edit custom vm option ,添加`-Dfile.encoding=UTF-8`
    2. tomcat->vm option ,添加`-Dfile.encoding=UTF-8`
* thymeleafsecurity5为了安全需要给密码添加加密方式,否则会报
  `java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"`
* jpa需要添加
 `database-platform: org.hibernate.dialect.MySQL5InnoDBDialect`来创建默认引擎为InnoDB的数据库表，否则默认为MyISAM