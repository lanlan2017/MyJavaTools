@echo off
:: 进入当前盘符
%~d0
:: 进入当前文件所在目录
cd %~dp0
:: 显示当前工作路径
echo Current working directory:%cd%
:: 执行java
start javaw -Dfile.encoding=GBK -jar "%~dp0CommandsUI.jar" %*
@REM java -Dfile.encoding=GBK -jar "%~dp0CommandsUI.jar" %*
@REM exit