@echo off
chcp
@REM 获取旧的工作目录地址
set oldpath=%cd%
@REM echo  切换到当前文件所在的盘符 %~d0
%~d0
@REM echo  进入当前文件所在目录 %~dp0
cd %~dp0
java -Dfile.encoding=utf-8 -jar "%~dp0AdbInstall.jar" %*
:: pause
@REM echo  切换到原来的盘符 %oldpath:~0,2%
%oldpath:~0,2%
@REM echo 回到原来的路径 %oldpath%
cd %oldpath%
@REM timeout 10

