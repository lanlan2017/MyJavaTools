@REM 等待5小时=5*60*60=18000s
timeout 18000
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