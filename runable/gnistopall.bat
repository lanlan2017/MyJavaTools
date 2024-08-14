@echo off
setlocal EnableExtensions
@REM 进入当前盘符
%~d0
@REM 进入当前文件所在目录
cd %~dp0
@REM 执行命令，遍历命令的输出结果
for /f "skip=1 tokens=1" %%i in ('adb devices -l') do (
    if %%i == jjqsqst4aim7f675 (
        echo %%i the main phone does not turn off
    ) else (
        echo gnirehtet stop %%i
        gnirehtet stop %%i
    )
)
@REM pause
timeout 10