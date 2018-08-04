package getmycomputer.wifipassworld;

import java.io.IOException;

public class RunCMD
{
	public static void main(String[] args) throws IOException
	{
		String code="for /f \"skip=9 tokens=1,2 delims=:\" %i in ('netsh wlan show profiles') "
				+ "do  @echo %j | findstr -i -v echo | netsh wlan "
				+ "show profiles %j key=clear >>D:\\passworld1.txt";
		Runtime.getRuntime().exec(code);
	}
}
