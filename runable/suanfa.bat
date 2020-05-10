@echo off
::java -jar "%~dp0CommandTools.jar" suanfa %1 %2 %3
java -Dfile.encoding=utf-8 -jar "%~dp0CommandTools.jar" suanfa %1 %2 %3
:: pause