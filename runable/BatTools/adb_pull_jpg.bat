@echo off
setlocal enabledelayedexpansion
::进入当前盘符
%~d0
::进入当前文件所在目录
cd %~dp0

@REM 获取当天的日期
for /f "tokens=1-3 delims=." %%a in ('date /t') do (
  set year=%%a
  set month=%%b
  set day=%%c
)
set today=%year%%month%%day%
echo "%today%"

@REM 只截取钱八个子串，取出尾部的空格。
set today=!today:~0,8!
@REM 获取指定格式的日期例如"20240305"
echo "%today%"
@REM 要求用输入.jpg之前，最后一个下划线之后的一串数字，
@REM 例如照片的名称为"IMG_20240305_132459.jpg",则输入"132459"
set /p userInput="IMG_20240305_:(?).jpg?="

@REM 拼接照片名称
set imgName=IMG_%today%_%userInput%.jpg
@REM 电脑上照片的存放路径，这里是当前脚本目录下的adb_pull_img\目录
set imgRoot=%~dp0adb_pull_img\

@REM 检查子目录是否存在
if not exist adb_pull_img (
    md adb_pull_img
) else (
    echo The subdirectory adb_pull_img already exists。
)

@REM 拼接adb push命令
echo adb pull /storage/emulated/0/DCIM/Camera/%imgName% %imgRoot%
adb pull /storage/emulated/0/DCIM/Camera/%imgName% %imgRoot%

@REM 打开图片（使用某人图片查看器）
echo explorer %imgRoot%%imgName%
explorer %imgRoot%%imgName%

pause