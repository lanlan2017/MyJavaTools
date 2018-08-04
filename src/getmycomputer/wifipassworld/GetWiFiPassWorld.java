package getmycomputer.wifipassworld;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class GetWiFiPassWorld
{

	/**   
	 * @param args  
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		printWiFiPassWorld();
	}

	/**   
	 * @throws FileNotFoundException  
	 */  
	public static void printWiFiPassWorld() throws FileNotFoundException
	{
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(new File("D:\\passworld.txt"));
		String line;
		String wifi;
		String passworld;
		while((line=scanner.nextLine())!=null)
		{
			//    SSID ����              :��Hello��
			if(line.contains("SSID ����"))
			{
				wifi=line.substring(line.lastIndexOf("��")+1,line.length()-1);
				System.out.println("����:"+wifi.trim());//trim()ȥ������Ŀհ׷�
			}
			//    �ؼ�����            : *********
			else if (line.contains("�ؼ�����"))
			{
				passworld=line.substring(line.lastIndexOf(":")+1);
				System.out.println("����:"+passworld.trim());//trim()ȥ������Ŀհ׷�
			}
		}
	}

}
