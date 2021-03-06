#### 项目目的
物流路径规划问题（简称：问题）：指对问题的表述，通常包括若干中心点，若干服务点及其服务点的需求，若干车辆，根据这些已知条件，考虑如何安排
车辆从中心点出发，对服务点进行服务。
#### 项目结构
```$xslt
└─routeplansystem
    │  │              ├─config
    │  │              ├─constant
    │  │              ├─controller
    │  │              ├─dao
    │  │              ├─pojo
    │  │              │  ├─dto
    │  │              │  ├─po
    │  │              │  └─vo
    │  │              ├─exception
    │  │              ├─service
    │  │              │  └─impl
    │  │              └─util
```
#### 项目结构说明
- config 此文件添加你需要添加或者自定义配置的java类
- constant 需要多处运用的常量以接口的形式分类出来添加到这里，避免魔法值
；比如我已经在此文件下添加了ExceptionMessage的接口
- dao 数据库访问层，映射xml文件在resources的sqlMapper文件下
- pojo 此文件下又分为dto即需要传输对象实体类，po即与数据库详对应的实体类，
vo 与前端展示相对应的实体类
- exception 自定义异常存放处，此文件下也已经存在了一个全局异常处理类，
在controller处理的异常全部在这个类下面进行处理
- service 逻辑处理层
- util 工具包，可以复用的函数存放处
#### 项目编码规范
1. 在IDEA添加Alibaba编码规范插件，尽量做到符合它的要求
2. dao层、service层，写接口函数时需要添加注释；util包下添加工具类
也需要添加相应的注释
3. 变量命令能够做到间其名知其义；希望函数能够单一责任，即一个函数只完成一个功能
4. 最好在编码过程是使用lamda表达式
5. 单元测试做完整
6. git 我们都在dev分支上进行开发，记得当你准备开始进行coding的时候，**一定切换到dev分支**
7. git的每次的提交，我不要求别的但是我希望每次的提交能够写清楚你这次的提交完成的工作
#### 可能需要去学习的技术
1. h2
2. MockIto
3. spring boot
4. restful API
5. csv文件的解析
6. logback

--------------

Fighting吧！ 希望我们早点完成










