@echo off
::java -jar "%~dp0CommandTools.jar" j  %*
@REM java -Dfile.encoding=utf-8 -jar "%~dp0CommandTools.jar" j %*
java -Dfile.encoding=utf-8 -jar "%~dp0Commands.jar" j %*
:: pause