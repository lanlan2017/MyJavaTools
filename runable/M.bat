@echo off
::java -jar "%~dp0CommandTools.jar" m %1 %2 %3 %4
::java -jar "%~dp0CommandTools.jar" m %*
@REM java -Dfile.encoding=utf-8 -jar "%~dp0CommandTools.jar" m %*
echo ��ǰ����Ŀ¼:%cd%
::���뵱ǰ�̷�
%~d0
::���뵱ǰ�ļ�����Ŀ¼�������ļ��ڵ�ǰĿ¼�£����ֱ�������Ļ���Ĭ�Ϲ���Ŀ¼
cd %~dp0
echo �����������Ŀ¼:%cd%
echo ==========================================================
java -Dfile.encoding=utf-8 -jar "%~dp0Commands.jar" m %*
:: pause