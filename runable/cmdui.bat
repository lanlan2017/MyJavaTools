@echo off
::start javaw -jar "%~dp0CommandsUI.jar"
::start javaw -Dfile.encoding=utf-8 -jar "%~dp0CommandsUI.jar"
start javaw -Dfile.encoding=GBK -jar "%~dp0CommandsUI.jar" %*
@REM java -Dfile.encoding=GBK -jar "%~dp0CommandsUI.jar" %*
@REM start javaw -Duser.dir=%~dp0 -Dfile.encoding=GBK -jar "%~dp0CommandsUI.jar" %*
exit