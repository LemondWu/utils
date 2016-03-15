# utils
Some Best Practice(I Think) Utils

**构建方式**

在项目根目录 `./gradlew build`

####目前包括的工具

### 1. ConfigUtil
1. 此工具在程序部署后将本地资源文件夹读取 `base_config.properties` 作为基础配置文件，从JVM参数中读取 `sys_external_config` 参数作为外部配置文件来源，存在则加载，不存在则忽略。 外部配置文件中相同的配置项优先级高于基础配置文件。
2. 提供3种获取配置文件属性的方法：

```java
String getProperty(String key)
List<String> getPropertyList(String key) - 支持中文
boolean getBoolean(String key)
```
