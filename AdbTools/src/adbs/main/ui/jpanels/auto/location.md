# 使用adb命令获取按钮的坐标
```
adb shell uiautomator dump
```
分析结果：
```

G:\dev2\idea_workspace\MyJavaTools\runable>adb shell uiautomator dump
UI hierchary dumped to: /sdcard/window_dump.xml

G:\dev2\idea_workspace\MyJavaTools\runable>
```
## 把文件从Android导出到电脑上：
```
adb pull /sdcard/window_dump.xml D:\Desktop\test\获取activity按钮位置
```

## xml文件分析
vscode打开window_dump.xml文件，查找需要的按钮的坐标

精简xml文件，

查找正则：
```
(text=".*?") .+ (bounds=".+?")
```
替换正则
```
$1 $2
```


# 点击指定的位置
vHei,vRed,VLan
```
com.xm.freader/com.kmxs.reader.webview.ui.DefaultNewWebActivity
```
## 关闭按钮位置：
```xml
<node index="0" text="" bounds="[843,849][915,921]" />
```
### 关闭按钮中点：879.0 885.0
执行命令
```
adb shell input tap 879.0 885.0
```

## 领金币按钮位置
领金币按钮中点 897.0 1195.5
```
adb shell input tap 897.0 1195.5
```


