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
    if %%i == jjqsqst4aim7f675 (
@REM     if %%i == 621QACQC3962X (
        echo %%i the main phone does not turn off
    ) else (
        echo adb -s %%i shell reboot -p
        adb -s %%i shell reboot -p
    )
@REM     timeout 10 > nul
    timeout 1 > nul
@REM     timeout 10
)
@REM pause
@REM timeout 10