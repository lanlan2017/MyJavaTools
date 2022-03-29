@echo off
@REM 进入当前盘符
%~d0
@REM 进入当前文件所在目录
cd %~dp0
@REM 显示当前工作路径
echo Current working directory:%cd%
echo ----------------------------------------------------------------------
@REM 运行程序
java -Dfile.encoding=utf-8 -jar QqDuanYu.jar
echo ----------------------------------------------------------------------
@REM 延时
choice /t 8 /d y /n >nul