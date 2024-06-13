@echo off
:: 进入当前盘符
%~d0
:: 进入当前文件所在目录
cd %~dp0
:: 显示当前工作路径
@REM echo Current working directory:%cd%
java -Dfile.encoding=utf-8 -jar "%~dp0MyAdb.jar" %*
:: pause