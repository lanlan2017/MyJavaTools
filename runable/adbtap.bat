@echo off
@REM 进入当前盘符
%~d0
@REM 进入当前文件所在目录
cd %~dp0
java -Dfile.encoding=utf-8 -jar "%~dp0AdbTap.jar"