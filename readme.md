一个坑：创建spring boot的项目时，启动类 必须和自建的controller类放到同一级目录下。
也就是说：当启动类放在 main/FirstTryApplication.java 然后controller类都放在 controller/IndexController.java 这样会找不到的
比如把FirstTryApplication.java移到和controller文件夹平级才行。
当然你可以通过使用其他的annotation去解决。



打包的时候要注意：需要在pom.xml里面额外加上：
<properties>
  <start-class>com.xiaosq.FirstTryApplication</start-class>
</properties>