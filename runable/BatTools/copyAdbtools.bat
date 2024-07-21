@echo off
@REM 删除原来的可执行文件
del "E:\Tools\runnable\AdbTools.jar"

@REM 设置局域网的主机的IP地址
set ip=192.168.0.102
@REM 设置本电脑中接收主机runnable目录的目标路径
set runnable=E:\Tools\runnable
@REM 本电脑另一个目标路径
set tools=E:\Tools\BatTools

@REM echo "runable=%runnable%"
@REM echo "tools=%tools%"

@REM 复制所有的jar文件
echo copy /Y "\\%ip%\runable\*.jar" %runnable%
copy /Y "\\%ip%\runable\*.jar" %runnable%

@REM 复制所有的mp3文件
echo copy /Y "\\%ip%\runable\*.mp3" %runnable%
copy /Y "\\%ip%\runable\*.mp3" %runnable%

@REM 复制所有的bat文件
echo copy /Y "\\%ip%\runable\*.bat" %runnable%
copy /Y "\\%ip%\runable\*.bat" %runnable%

@REM 复制adbtools.jar的配置文件
echo copy /Y "\\%ip%\runable\AdbTools.properties" %runnable%
copy /Y "\\%ip%\runable\AdbTools.properties" %runnable%

echo copy /Y "\\%ip%\runable\AdbTools_money_apk.properties" %runnable%
copy /Y "\\%ip%\runable\AdbTools_money_apk.properties" %runnable%

@REM 复制另个一个目录下的bat文件到本电脑的执行路径
echo copy /Y \\%ip%\BatTools\*.bat %tools%
copy /Y \\%ip%\BatTools\*.bat %tools%

echo copy /Y \\%ip%\BatTools\*.jar %tools%
copy /Y \\%ip%\BatTools\*.jar %tools%

echo copy /Y \\%ip%\BatTools\*.txt %tools%
copy /Y \\%ip%\BatTools\*.txt %tools%

@REM timeout 10
pause