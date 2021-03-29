# 项目描述
该java代码可以获取当前电脑中所有连接过的WiFi的密码。
# 使用说明
打开CMD,然后使用如下命令编译该文件:
```
javac -d . GetWiFiPassWord.java
```
然后运行该java:
```
```
java wifi.GetWiFiPassWord
```
```
就会在当前路径下生成一个名为:`所有连过的WiFi密码.txt`的文本文件,打开该文件就可以看到WiFi名称和对应的密码。
如下所示:
```
无线:易烊千玺的女朋友|密码:xixihahaxixi
无线:哦|密码:xixixihhhh
无线:xiaolan|密码:123hhhxxx
无线:CMCC-EDU|
无线:LIBRARY|
```
对应那些需要**登录才能使用的公共WiFi**，不存在密码。也就无法获取,例如这里的`CMCC-EDU`。