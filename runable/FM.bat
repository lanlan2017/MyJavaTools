@echo off
:: 进入当前盘符
%~d0
:: 进入当前文件所在目录
cd %~dp0
:: 显示当前工作路径
echo Current working directory:%cd%
:: 执行java
@REM echo %~dp0FM.jar
@REM java -jar "%~dp0FM.jar" %1 %2 %3
java -jar "%~dp0FM.jar" %1 %2 %3
@REM pause
