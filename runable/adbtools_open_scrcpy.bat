@echo off
:: 进入当前盘符
%~d0
:: 进入当前文件所在目录
cd %~dp0
:: 显示当前工作路径
echo Current working directory:%cd%
:: 执行java
@REM java -Dfile.encoding=utf-8 -jar "%~dp0open_scrcpy.jar" 75aed56d OppoUSB
@REM 设置为utf-8编码
@REM chcp 65001
java -Dfile.encoding=utf-8 -jar "%~dp0adbtools_open_scrcpy.jar" %*