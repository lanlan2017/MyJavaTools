@echo off
::start javaw -jar "%~dp0CommandsUI.jar"
::start javaw -Dfile.encoding=utf-8 -jar "%~dp0CommandsUI.jar"
echo 当前工作目录:%cd%
::进入当前盘符
%~d0
::进入当前文件所在目录，配置文件在当前目录下，如果直接启动的话，默认工作目录
cd %~dp0
echo 进入程序所在目录:%cd%
echo ==========================================================
@REM start javaw -Dfile.encoding=GBK -jar "%~dp0CommandsUI.jar" %*
@REM -Duser.dir=%~dp0表示jar包的工作路径为当前文件的路径
start javaw -Duser.dir=%~dp0 -Dfile.encoding=GBK -jar "%~dp0CommandsUI.jar" %*
exit