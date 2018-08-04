package random.use.make_decisions;

import random.NextIntMinToMax;

public class RandomMakeDecisions
{

	public static void main(String[] args)
	{
		String[] things={
							"写代码",
							"复习期末"
						};
		int[] count=new int[things.length];
		int times=10000*1000;
		for(int i=0;i<times;i++)
		{
			count[NextIntMinToMax.randomIntMinToMax(0, things.length-1)]++;
		}
		//查找最大值：
		int maxIndex=0;
		for(int i=1;i<count.length;i++)
		{
			if(count[i]>count[maxIndex])
				maxIndex=i;//记下最大的下标
		}
		for (int i : count)
		{
			System.out.println(i);
		}
		System.out.println("你将:"+things[maxIndex]);
	}
}
