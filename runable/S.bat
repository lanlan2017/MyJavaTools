@echo off
::java -jar "%~dp0CommandTools.jar" s %1 %2 %3
@REM java -Dfile.encoding=utf-8 -jar "%~dp0CommandTools.jar" s %1 %2 %3
@REM java -Dfile.encoding=utf-8 -jar "%~dp0Commands.jar" s %1 %2 %3
java -Dfile.encoding=utf-8 -jar "%~dp0Commands.jar" s %*
:: pause