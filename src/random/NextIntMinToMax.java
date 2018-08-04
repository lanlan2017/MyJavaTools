package random;

import java.util.Random;

public class NextIntMinToMax
{
	public static void main(String[] args)
	{
		Random rand = new Random();
		int Min = 0;
		int Max = 9;
		int[] count = new int[Max - Min + 1];
		for (int i = 0; i < 10000; i++)
		{
			count[randomIntMinToMax(Min, Max)]++;
		}
		System.out.println();
		char percentCh = '%';
		for (int i = 0; i < count.length; i++)
		{
			double percent = 100 * (double) count[i] / 10000;
			System.out.printf("%d���ɴ���:%-4d,ռ�ٷֱ�:%.2f%c\n", i, count[i], percent,
					percentCh);
		}
	}
	/**
	 * ��[Min,Max]����֮������������
	 * 
	 * @param Min
	 *            ��Сֵ
	 * @param Max
	 *            ���ֵ
	 * @return һ��Min��Max֮����������
	 */
	public static int randomIntMinToMax(int Min, int Max)
	{
		// �����ȣ�ֱ�ӷ��أ������ɸ�ƨ
		if (Min == Max)
		{
			return Max;
		}
		// ���Min��Max�󣬽���������ֵ���������������nextInt()�����
		if (Min > Max)
		{
			Min ^= Max;
			Max ^= Min;
			Min ^= Max;
		}
		Random rand = new Random();// nextInt()���Ǿ�̬����������ֱ������������
		return rand.nextInt(Max - Min + 1) + Min;
	}
}
