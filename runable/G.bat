@echo off
::java -jar "%~dp0CommandTools.jar" g %1 %2 %3
java -jar "%~dp0CommandTools.jar" g  %*
:: pause