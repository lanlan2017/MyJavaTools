@echo off
:: ���뵱ǰ�̷�
%~d0
:: ���뵱ǰ�ļ�����Ŀ¼
cd %~dp0
:: ��ʾ��ǰ����·��
@REM echo Current working directory:%cd%
:: ִ��java
::java -jar "%~dp0CommandTools.jar" m %1 %2 %3 %4
::java -jar "%~dp0CommandTools.jar" m %*
@REM java -Dfile.encoding=utf-8 -jar "%~dp0Desktop.jar" m %*
@REM javaw -Dfile.encoding=utf-8 -jar "%~dp0Desktop.jar"
:: ����Java���˳���ǰ����̨����
start javaw -Dfile.encording=utf-8 -jar "%~dp0Desktop.jar"