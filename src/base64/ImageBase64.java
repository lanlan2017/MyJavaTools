package base64;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
//import sun.misc.BASE64Decoder;  
//import sun.misc.BASE64Encoder;  

public class ImageBase64
{
	public static String GetImageStr(String imgFile)  
    {//��ͼƬ�ļ�ת��Ϊ�ֽ������ַ��������������Base64���봦��  
        InputStream in = null;  
        byte[] data = null;  
        //��ȡͼƬ�ֽ�����  
        try   
        {  
            in = new FileInputStream(imgFile);          
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
        //���ֽ�����Base64����  
//        BASE64Encoder encoder = new BASE64Encoder();  
//        return encoder.encode(data);//����Base64��������ֽ������ַ���  
		return imgFile;
    }  	
}
