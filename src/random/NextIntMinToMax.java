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
			System.out.printf("%d生成次数:%-4d,占百分比:%.2f%c\n", i, count[i], percent,
					percentCh);
		}
	}
	/**
	 * 求[Min,Max]区间之间的随机整数。
	 * 
	 * @param Min
	 *            最小值
	 * @param Max
	 *            最大值
	 * @return 一个Min和Max之间的随机整数
	 */
	public static int randomIntMinToMax(int Min, int Max)
	{
		// 如果相等，直接返回，还生成个屁
		if (Min == Max)
		{
			return Max;
		}
		// 如果Min比Max大，交换两个的值，如果不交换下面nextInt()会出错
		if (Min > Max)
		{
			Min ^= Max;
			Max ^= Min;
			Min ^= Max;
		}
		Random rand = new Random();// nextInt()不是静态方法，不能直接用类名调用
		return rand.nextInt(Max - Min + 1) + Min;
	}
}
