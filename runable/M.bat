@echo off
:: java -jar "%~dp0CommandTools.jar" m %1 %2 %3 %4
::java -jar "%~dp0CommandTools.jar" m %*
java -Dfile.encoding=utf-8 -jar "%~dp0CommandTools.jar" m %*
:: pause