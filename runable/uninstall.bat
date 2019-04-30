@echo off
::
::
:: ### 获取当前文件(install.bat)所在的目录的绝对路径 ####
set thispath=%~dp0
set "thispath=%thispath:~0,-1%"
echo   当前文件所在的目录的绝对路径:%thispath%
:: ###################################################
setlocal enabledelayedexpansion 
set remain=%path%
::待删除字符串
set toDel=%thispath%
:loop
for /f "tokens=1* delims=;" %%a in ("%remain%") do (
	if not "%toDel%"=="%%a" (
		::如果mypath没有定义的话就直接赋值,赋值之后就只需要追加
		if not defined mypath ( set mypath=%%a) else (set mypath=%mypath%;%%a)
	)
	rem 将截取剩下的部分赋给变量remain，其实这里可以使用延迟变量开关
	set remain=%%b
)
::如果还有剩余,则继续分割
if defined remain goto :loop
echo 删除之前的path环境变量
echo %path%
echo.
echo 删除之后的path环境变量
echo %mypath%
echo 正在修改系统path环境变量...
setx /m "path" "%mypath%"
echo 修改完毕...
pause