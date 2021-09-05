@echo off
:: java -jar "%~dp0CommandTools.jar" h %1 %2 %3
@REM java -Dfile.encoding=utf-8 -jar "%~dp0CommandTools.jar" html %1 %2 %3
@REM java -Dfile.encoding=utf-8 -jar "%~dp0Commands.jar" html %1 %2 %3
java -Dfile.encoding=utf-8 -jar "%~dp0Commands.jar" html %*
:: pause