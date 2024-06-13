@echo off
chcp
@REM 锟斤拷取锟缴的癸拷锟斤拷目录锟斤拷址
set oldpath=%cd%
@REM echo  锟叫伙拷锟斤拷锟斤拷前锟侥硷拷锟斤拷锟节碉拷锟教凤拷 %~d0
%~d0
@REM echo  锟斤拷锟诫当前锟侥硷拷锟斤拷锟斤拷目录 %~dp0
cd %~dp0
@REM echo  锟斤拷前锟斤拷锟斤拷路锟斤拷 %cd%
@REM echo  执锟斤拷java
::java -jar "%~dp0CommandTools.jar" m %1 %2 %3 %4
::java -jar "%~dp0CommandTools.jar" m %*
java -Dfile.encoding=utf-8 -jar "%~dp0AdbPullApk.jar"
:: pause
@REM echo  锟叫伙拷锟斤拷原锟斤拷锟斤拷锟教凤拷 %oldpath:~0,2%
%oldpath:~0,2%
@REM echo 锟截碉拷原锟斤拷锟斤拷路锟斤拷 %oldpath%
cd %oldpath%
timeout 30