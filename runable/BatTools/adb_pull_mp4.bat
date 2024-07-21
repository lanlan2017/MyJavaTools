@echo off
setlocal enabledelayedexpansion
::进入当前盘符
%~d0
::进入当前文件所在目录
cd %~dp0

@REM @echo off
@REM setlocal enabledelayedexpansion

for /f "tokens=1-3 delims=." %%a in ('date /t') do (
  set year=%%a
  set month=%%b
  set day=%%c
)
@REM if %month% lss 10 set month=0%month%
@REM if %day% lss 10 set day=0%day%
set today=%year%%month%%day%
echo "%today%"
@REM 只截取钱八个子串，取出尾部的空格。
set today=!today:~0,8!
echo "%today%"
@REM
set /p userInput="name:"
@REM set imgName=IMG_%today%_%userInput%.jpg
set mp4Name=VID_%today%_%userInput%.mp4
set mp4Root=%~dp0adb_pull_mp4

@REM echo adb -s 8BN0217726006684 pull /storage/emulated/0/DCIM/Camera/IMG_20240305_115738.jpg %~dp0img
@REM echo adb -s 8BN0217726006684 pull /storage/emulated/0/DCIM/Camera/IMG_%today%_115738.jpg %~dp0img
@REM echo adb -s 8BN0217726006684 pull /storage/emulated/0/DCIM/Camera/IMG_%today%_%userInput%.jpg %~dp0img
@REM @REM 拼接adb push命令
@REM echo adb -s 8BN0217726006684 pull /storage/emulated/0/DCIM/Camera/%mp4Name% %~dp0img
@REM adb -s 8BN0217726006684 pull /storage/emulated/0/DCIM/Camera/%mp4Name% %~dp0img

@REM @REM 拼接adb push命令
@REM echo adb pull /storage/emulated/0/DCIM/Camera/%mp4Name% %~dp0img
@REM adb pull /storage/emulated/0/DCIM/Camera/%mp4Name% %~dp0img
@REM 拼接adb push命令
echo adb pull /storage/emulated/0/DCIM/Camera/%mp4Name% %mp4Root%
adb pull /storage/emulated/0/DCIM/Camera/%mp4Name% %mp4Root%

@REM @REM 打开图片（使用某人图片查看器）
@REM echo explorer %~dp0img\%mp4Name%
@REM explorer %~dp0img\%mp4Name%

@REM 打开图片（使用某人图片查看器）
echo explorer %mp4Root%\%mp4Name%
explorer %mp4Root%\%mp4Name%
pause