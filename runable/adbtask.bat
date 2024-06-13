adbtask.bat@REM @echo off
@REM call addadbserial.bat

@echo off
:: 进入当前盘符
%~d0
:: 进入当前文件所在目录
cd %~dp0
:: 显示当前工作路径
echo Current working directory:%cd%
:: 执行java
::java -jar "%~dp0CommandTools.jar" m %1 %2 %3 %4
::java -jar "%~dp0CommandTools.jar" m %*
@REM java -Dfile.encoding=utf-8 -jar "%~dp0AddAdbSerial.jar" m %*
@REM java -Dfile.encoding=utf-8 -jar "%~dp0AddAdbSerial.jar"
java -Dfile.encoding=utf-8 -jar "%~dp0AdbTaskAll.jar" %*
timeout 10>nul
:: pause