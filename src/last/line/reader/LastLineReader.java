package last.line.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

/**
 * �ı��ļ����һ�д�����.
 */
public class LastLineReader
{

	/**
	 * ��ȡ�ı��ļ����һ���е��ַ�����
	 * 
	 * @param file
	 *            Ŀ���ļ�
	 * @param charset
	 *            �ַ�����
	 * @return �ı��ļ������һ���е��ַ�����
	 */
	public static String getLastLineStr(File file, String charset)
	{
		String lastLine = null;
		RandomAccessFile raf = null;
		try
		{
			raf = new RandomAccessFile(file, "rwd");
			long lastLinePos = getLastLinePos(raf);
			long length = raf.length();
			byte[] bytes = new byte[(int) ((length - 1) - lastLinePos)];
			raf.read(bytes);

			if (charset == null)
			{
				// return new String(bytes);
				lastLine = (new String(bytes));
			} else
			{
				// return new String(bytes, charset);
				lastLine = (new String(bytes, charset));
			}

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return lastLine;
	}
	/**
	 * �����һ����ĩβ�в����ı���
	 * 
	 * @param file
	 *            Ŀ���ļ�
	 * @param newLastLine
	 *            ����������ı�
	 * @param charset
	 *            �ַ���������
	 */
	public static void insertAfterLastLine(File file, String newLastLine,
			String charset)
	{
		newLastLine = "\n" + newLastLine;
		insertInLastLine(file, newLastLine, charset);
	}
	/**
	 * �������һ���ı���
	 * 
	 * @param file
	 *            Ŀ���ļ�
	 * @param newLastLine
	 *            �滻�ı�
	 * @param charset
	 *            �ַ���������
	 */
	public static void updateLastLine(File file, String newLastLine,
			String charset)
	{
		RandomAccessFile raf = null;
		try
		{
			raf = new RandomAccessFile(file, "rwd");
			// ɾ�����һ���ı�
			deleteLastLine(raf);
			// �����һ�в����µ�һ���ı�
			insertInLastLine(newLastLine, charset, raf);
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (raf != null)
			{
				try
				{
					raf.close();
				} catch (IOException e)
				{
				}
			}
		}
	}
	/**
	 * ���ı��ļ����һ��ĩβ׷���ı���
	 * 
	 * @param newLastLine
	 *            Ҫ׷�ӵ��ı�
	 * @param charset
	 *            �ַ���������
	 * @param raf
	 *            RandomAccessFile��ʾ���ļ�
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	private static void insertInLastLine(String newLastLine, String charset,
			RandomAccessFile raf)
			throws IOException, UnsupportedEncodingException
	{
		// �ƶ�ָ�뵽���һ��
		raf.seek(raf.length());
		raf.write(newLastLine.getBytes(charset));
	}
	/**
	 * ���ı��ļ����һ��ĩβ�����ı���
	 * 
	 * @param file
	 *            Ŀ���ļ�
	 * @param newLastLine
	 *            Ҫ������ı�
	 * @param charset
	 *            �ַ���������
	 */
	public static void insertInLastLine(File file, String newLastLine,
			String charset)
	{
		RandomAccessFile raf = null;
		try
		{
			raf = new RandomAccessFile(file, "rwd");
			// �ƶ�ָ�뵽���һ��
			raf.seek(raf.length());
			raf.write(newLastLine.getBytes(charset));
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (raf != null)
			{
				try
				{
					raf.close();
				} catch (IOException e)
				{
				}
			}
		}
	}
	/**
	 * ɾ�������ļ����һ�С�
	 * 
	 * @param raf
	 * @throws IOException
	 */
	private static void deleteLastLine(RandomAccessFile raf) throws IOException
	{
		// ��ȡ���һ�е�λ��
		long lastLinePos = getLastLinePos(raf);
		// ɾ�����һ��
		raf.setLength(lastLinePos);
	}

	/**
	 * ɾ�������ļ����һ�С�
	 * 
	 * @param file
	 */
	public static void deleteLastLine(File file)
	{
		RandomAccessFile raf = null;
		try
		{
			raf = new RandomAccessFile(file, "rwd");
			// ��ȡ���һ�е�λ��
			long lastLinePos = getLastLinePos(raf);
			// ɾ�����һ��
			raf.setLength(lastLinePos);
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (raf != null)
			{
				try
				{
					raf.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

	}
	/**
	 * �������һ�е���ʼλ��,���ƶ��ļ�ָ�뵽���һ�е���ʼλ�á�
	 * 
	 * @param raf
	 *            RandomAccessFile����
	 * @return ���һ�е���ʼλ��
	 * @throws IOException
	 */
	private static long getLastLinePos(RandomAccessFile raf) throws IOException
	{
		long lastLinePos = 0L;
		// ��ȡ�ļ�ռ���ֽ���
		long len = raf.length();
		if (len > 0L)
		{
			// ��ǰ��һ���ֽ�
			long pos = len - 1;
			while (pos > 0)
			{
				pos--;
				// �ƶ�ָ��
				raf.seek(pos);
				// �ж�����ֽ��ǲ��ǻس���
				if (raf.readByte() == '\n')
				{
					// lastLinePos = pos;// ��¼��λ��
					// break;// ǰ�Ƶ����һ���س��������
					return pos;
				}

			}
		}
		return lastLinePos;
	}
	/**
	 * �����ı��ļ������һ�е���ʼλ�á�
	 * 
	 * @param file
	 *            �ı��ļ�
	 * @return ���һ�е��±�
	 */
	public static long getLastLinePos(File file)
	{
		long lastLinePos = 0L;
		RandomAccessFile raf;
		try
		{
			raf = new RandomAccessFile(file, "r");

			// ��ȡ�ļ�ռ���ֽ���
			long len = raf.length();
			if (len > 0L)
			{
				// ��ǰ��һ���ֽ�
				long pos = len - 1;
				while (pos > 0)
				{
					pos--;
					// �ƶ�ָ��
					raf.seek(pos);
					// �ж�����ֽ��ǲ��ǻس���
					if (raf.readByte() == '\n')
					{
						lastLinePos = pos;// ��¼��λ��
						break;// ǰ�Ƶ����һ���س��������
						// return pos;
					}
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return lastLinePos;
	}
}
