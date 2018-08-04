package base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Base64;
import clipboard.util.SysClipboardUtil;

public class TestBase64
{
	public static void main(String[] args)
	{
		String imagePath = "C:\\Users\\lan\\Desktop\\1.png";
//		long count=0;
		File file;
		Date date;
		SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss");
		String time;
		while(true)
		{
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			file=new File("C:\\Users\\lan\\Desktop\\1.png");
			if(file.exists())
			{
				date=new Date();
				time=format.format(date);
				ImageToBase64(imagePath,time);
				file.delete();
			}
			
		}
		
	}

	/**   
	 * @param imagePath  
	 */  
	public static void ImageToBase64(String imagePath)
	{
		FileInputStream io = null;
		try
		{
			io = new FileInputStream(imagePath);
			byte[] data = null;
			data = new byte[io.available()];
			io.read(data);
			io.close();
			String MDImageBase64="![Sample Pic][test_img]\n[test_img]:data:image/png;base64,"
					+ Base64.getEncoder().encodeToString(data);
//			System.out.println("data:image/jpg;base64,"
//					+ Base64.getEncoder().encodeToString(data));
			System.out.println(MDImageBase64);
			SysClipboardUtil.setSysClipboardText(MDImageBase64);
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (io != null)
			{
				try
				{
					io.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	/**   
	 * @param imagePath  
	 */  
	public static void ImageToBase64(String imagePath,String time)
	{
		FileInputStream io = null;
		try
		{
			io = new FileInputStream(imagePath);
			byte[] data = null;
			data = new byte[io.available()];
			io.read(data);
			io.close();
			String MDImageBase64="![Õº∆¨√Ë ˆ][test_img"+time+"]\n[test_img"+time+"]:data:image/jpg;base64,"
					+ Base64.getEncoder().encodeToString(data);
//			System.out.println("data:image/jpg;base64,"
//					+ Base64.getEncoder().encodeToString(data));
//			System.out.println(MDImageBase64);
			SysClipboardUtil.setSysClipboardText(MDImageBase64);
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (io != null)
			{
				try
				{
					io.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}