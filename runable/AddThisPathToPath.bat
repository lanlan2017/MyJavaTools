@echo off
::################ ���ɵ�ǰ·�� ��ʼ ########################
::
set thispath=%~dp0
set "thispath=%thispath:~0,-1%"
echo   ��ǰ�ļ����ڵ�Ŀ¼�ľ���·��:%thispath%
::�л��̷�
%~d0
::����·��
cd %~dp0
::
::################ ���ɵ�ǰ·�� ���� ########################
::
:: ################# ������������ ��ʼ #################
::
REM echo ############ ������������ mytree.bat... #####
::����д��
REM echo @echo off>mytree.bat
REM echo java -jar "%thispath%\mytree.jar" %%1 %%2 %%3>>mytree.bat
::
:: ################# ������������ ���� #################
:: 
::
::
:: ################  ����path�������� ��ʼ  ##############
echo ############# ����path�������� ��ʼ ##############
::
:: ####################################################
::��ȡpath�����������Զ��������
set remain=%path%
::�����ӵ�·���ַ���
set toAdd=%thispath%
::���,Ĭ��û���ظ�
set finded=false
:loop
for /f "tokens=1* delims=;" %%a in ("%remain%") do (
	::����ҵ���ͬ����
	if "%toAdd%"=="%%a" (
		::ֱ���˳�
		goto :isFinded
		::�ñ����,true��ʾ���ظ�����
		set finded=true
	)
	rem ����ȡʣ�µĲ��ָ�������remain����ʵ�������ʹ���ӳٱ�������
	set remain=%%b
)
::�������ʣ��,������ָ�
if defined remain goto :loop
::���û���ظ�:
if "%finded%"=="false" (
	echo �����޸�ϵͳpath��������...
	setx /m "path" "%toAdd%;%path%"
	::��������
	goto :end
)
:isFinded
echo path�����������Ѿ����˸û�������,�����ظ�����.
:end
pause
:: ################  ����path�������� ��ʼ  ##############