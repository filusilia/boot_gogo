# Let's Boot
-
构建springboot常用脚手架

## gogo-generator

- generator工具，使用1.4版本
 1.3.7与1.4的一个区别是Field构造方法发生了变化，当插件中需要自定义序列化接口时需要注意。
- 使用tk.mybatis来生成mapper
- 使用lombok注解

### 使用方式

- 在[generator](boot-generator/src/main/resources/generator/generatorConfig.xml)文件中修改数据库连接，账户与密码
- 需要更多自定义的修改需要修改项目中三个plugin类
1. 运行`mvn install`,安装generator项目，使其自动打包成1.0的jar包（在pom中有引用）
2. 运行maven的插件`generator:generator`

#### 0.1.Beta

第一版框架搭建
使用springboot2.3
jpa进行数据库同步
tk.mybatis进行数据库操作

* idea控制台乱码解决方案：
    1. idea->help->edit custom vm option ,添加`-Dfile.encoding=UTF-8`
    2. tomcat->vm option ,添加`-Dfile.encoding=UTF-8`
* thymeleafsecurity5为了安全需要给密码添加加密方式,否则会报
  `java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"`
* jpa需要添加
 `database-platform: org.hibernate.dialect.MySQL5InnoDBDialect`来创建默认引擎为InnoDB的数据库表，否则默认为MyISAM