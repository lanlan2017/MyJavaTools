@echo off
:: 进入当前盘符
%~d0
:: 进入当前文件所在目录
cd %~dp0
:: 显示当前工作路径
echo Current working directory:%cd%
:: 执行java
::java -jar "%~dp0CommandTools.jar" g %1 %2 %3
@REM java -jar "%~dp0CommandTools.jar" g  %*
java -jar "%~dp0Commands.jar" g  %*
:: pause