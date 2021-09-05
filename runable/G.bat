@echo off
::java -jar "%~dp0CommandTools.jar" g %1 %2 %3
@REM java -jar "%~dp0CommandTools.jar" g  %*
java -jar "%~dp0Commands.jar" g  %*
:: pause