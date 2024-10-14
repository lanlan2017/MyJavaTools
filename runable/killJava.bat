call adbbr.bat
call adb_all_home.bat
taskkill /f /im scrcpy.exe
taskkill /f /im adb.exe
taskkill /f /im python.exe
taskkill /f /im java.exe
@REM call killAdbTools.bat
echo NextPort=5555>E:\Tools\runnable\AdbTools_Port.properties
timeout 10
@REM pause
