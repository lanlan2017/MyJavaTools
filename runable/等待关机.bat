@echo off
@REM 等待5小时=5*60*60=18000s
@REM timeout 18000
echo Please select a waiting time:
echo 1 : 1 hour
echo 2 : 2 hours
echo 3 : 3 hours
echo 4 : 4 hours
echo 5 : 5 hours
set /p choice="Please enter your choice(1,2,3,4,5): "
:: 根据用户输入设置等待时间
if "%choice%"=="1" (
    set waitTime=3600
) else if "%choice%"=="2" (
    set waitTime=7200
) else if "%choice%"=="3" (
    set waitTime=10800
) else if "%choice%"=="4" (
    set waitTime=14400
) else if "%choice%"=="5" (
      set waitTime=18000
) else (
    echo 输入无效,默认等待3小时。
    set waitTime=10800
)
@REM timeout %waitTime%
@REM timeout /t %waitTime% /nobreak
timeout /t %waitTime%
@REM 调用另一个批处理文件
@REM 重置手机电池状态
call adbbr.bat
@REM 等待上面call调用的批处理执行完毕
timeout 20
@REM 等待上面call调用的批处理执行完毕
call adbrp.bat
timeout 20
@REM 立即关机
@REM echo shutdown -s -t 0
@REM shutdown -s -t 0
echo shutdown -h -t 0
shutdown /h
