@echo off
:: java -jar "%~dp0CommandTools.jar" m %1 %2 %3 %4
java -jar "%~dp0CommandTools.jar" m %*
:: pause