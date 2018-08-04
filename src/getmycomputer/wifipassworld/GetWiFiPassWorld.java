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
			//    SSID 名称              :“Hello”
			if(line.contains("SSID 名称"))
			{
				wifi=line.substring(line.lastIndexOf("“")+1,line.length()-1);
				System.out.println("无线:"+wifi.trim());//trim()去掉多余的空白符
			}
			//    关键内容            : *********
			else if (line.contains("关键内容"))
			{
				passworld=line.substring(line.lastIndexOf(":")+1);
				System.out.println("密码:"+passworld.trim());//trim()去掉多余的空白符
			}
		}
	}

}
