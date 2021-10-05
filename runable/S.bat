@echo off
::进入当前盘符
%~d0
::进入当前文件所在目录
cd %~dp0
::java -jar "%~dp0CommandTools.jar" s %1 %2 %3
@REM java -Dfile.encoding=utf-8 -jar "%~dp0CommandTools.jar" s %1 %2 %3
@REM java -Dfile.encoding=utf-8 -jar "%~dp0Commands.jar" s %1 %2 %3
java -Dfile.encoding=utf-8 -jar "%~dp0Commands.jar" s %*
:: pause