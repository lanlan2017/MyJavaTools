@echo off
:: ���뵱ǰ�̷�
%~d0
:: ���뵱ǰ�ļ�����Ŀ¼
cd %~dp0
:: ����Java���˳���ǰ����̨����
java -Dfile.encording=utf-8 -jar "%~dp0killAdbTools.jar"