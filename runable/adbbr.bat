@echo off
setlocal EnableExtensions
@REM 进入当前盘符
%~d0
@REM 进入当前文件所在目录
cd %~dp0
@REM 执行命令，遍历命令的输出结果
for /f "skip=1 tokens=1" %%i in ('adb devices -l') do (
    @REM 允许USB充电
@REM     echo adb -s %%i shell dumpsys battery set usb 1
@REM     adb -s %%i shell dumpsys battery set usb 1
    @REM 恢复电池默认设置.
    echo adb -s %%i shell dumpsys battery reset
    adb -s %%i shell dumpsys battery reset
)

@REM timeout 10