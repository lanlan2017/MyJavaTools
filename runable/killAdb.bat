@REM call adbbr.bat
@REM taskkill /f /im scrcpy.exe
@REM call killAdbTools.bat

@REM echo NextPort=5555>E:\Tools\runnable\AdbTools_Port.properties
echo NextPort=5555>AdbTools_Port.properties
taskkill /f /im adb.exe
@REM taskkill /f /im python.exe
@REM taskkill /f /im java.exe
@REM call killAdbTools.bat
@REM echo NextPort=5555>E:\Tools\runnable\AdbTools_Port.properties
timeout 10
@REM pause
