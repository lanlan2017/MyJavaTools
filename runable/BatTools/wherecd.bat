@echo off

@REM 获取第一个命令行输入作为程序名称。例如gnirehtet.exe
set "pName=%1"

echo where "%pName%"
@REM 执行where命令，查询程序的路径
for /f "delims=" %%i in ('where "%pName%"') do (
    set "pPath=%%i"
)

@REM 输出程序的路径
echo %pPath%
@REM echo 如果你想进入%pName%所在的目录，可执行如下命令:
echo.
echo Use this command to enter %pName%'s directory:
java -Dfile.encoding=utf-8 -jar "%~dp0WhereCd.jar" %pPath%