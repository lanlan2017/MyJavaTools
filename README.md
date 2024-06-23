## 项目说明
这个仓库中保存了一些我常用的工具类
## 如何使用本项目
因为本项目的模块之间存在依赖关系，无法直接使用idea直接打开克隆后的项目。为了正常使用，需要创建项目。
应该先导入被依赖的模块。然后在导入其他模块。
### 导入Tools模块
先克隆到本地，然后在idea中新建一个Java项目，然后首先把Tools模块import到项目中，然后编译运行Tools模块中的可执行Java文件，Tools模块能正常运行之后再导入其他模块。
### 导入Commands模块
导入Tools模块后，再导入Commands模块
### 导入其他模块
#### 导入CommandsUI模块
#### 导入Demo模块
CommandsUI,Demo模块都，依赖Tools模块和Commands模块
#### 导入RunableTools模块
#### 导入AdbTools模块
RunableTools,AdbTools模块，依赖Tools模块。

按这样的顺导入模块之后，应该就可以正常编译运行模块中的程序了。
这种模块之间的依赖方式使用效果不好，后续会改进，目前先这样用。
后面后时间的话，把Tools模块打包成Tools.jar文件，其他模块只需要依赖这个Tools.jar文件即可。