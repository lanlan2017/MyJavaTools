@echo off
:: 进入当前盘符
%~d0
:: 进入当前文件所在目录
cd %~dp0
:: 显示当前工作路径
echo Current working directory:%cd%
:: 执行java
::java -jar "%~dp0CommandTools.jar" j  %*
@REM java -Dfile.encoding=utf-8 -jar "%~dp0CommandTools.jar" j %*
java -Dfile.encoding=utf-8 -jar "%~dp0Commands.jar" j %*
:: pause