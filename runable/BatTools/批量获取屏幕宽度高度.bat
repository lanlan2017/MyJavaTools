@echo off
setlocal EnableExtensions
@REM 进入当前盘符
%~d0
@REM 进入当前文件所在目录
cd %~dp0
@REM 执行命令，遍历命令的输出结果
for /f "skip=1 tokens=1" %%i in ('adb devices -l') do (
    @REM 恢复电池默认设置.
@REM     echo adb -s %%i shell dumpsys battery reset
@REM     adb -s %%i shell dumpsys battery reset
@REM     echo adb -s %%i shell reboot -p
@REM     adb -s %%i shell reboot -p
@REM     echo adb -s %%i shell getprop ro.product.brand
@REM     adb -s %%i shell getprop ro.product.brand
    echo adb -s %%i shell wm size
    adb -s %%i shell wm size
    timeout 1 >>nul
)
pause
@REM timeout 5