@echo off
::java -jar "%~dp0CommandTools.jar" m %1 %2 %3 %4
::java -jar "%~dp0CommandTools.jar" m %*
@REM java -Dfile.encoding=utf-8 -jar "%~dp0CommandTools.jar" m %*
echo 当前工作目录:%cd%
::进入当前盘符
%~d0
::进入当前文件所在目录，配置文件在当前目录下，如果直接启动的话，默认工作目录
cd %~dp0
echo 进入程序所在目录:%cd%
echo ==========================================================
java -Dfile.encoding=utf-8 -jar "%~dp0Commands.jar" m %*
:: pause