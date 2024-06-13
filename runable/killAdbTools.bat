@echo off
:: 进入当前盘符
%~d0
:: 进入当前文件所在目录
cd %~dp0
:: 启动Java后退出当前控制台程序
java -Dfile.encording=utf-8 -jar "%~dp0killAdbTools.jar"