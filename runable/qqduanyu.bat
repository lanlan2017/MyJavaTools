@echo off
@REM 进入当前盘符
%~d0
@REM 进入当前文件所在目录
cd %~dp0
@REM 显示当前工作路径
echo Current working directory:%cd%
echo ----------------------------------------------------------------------
@REM 运行程序
@REM java -Dfile.encoding=utf-8 -jar QqDuanYu.jar
java -Dfile.encoding=utf-8 -jar "%~dp0Commands.jar" qq duanyu
echo ----------------------------------------------------------------------
@REM 延时
choice /t 8 /d y /n >nul