package random.use.make_decisions;

import random.NextIntMinToMax;

public class RandomMakeDecisions
{

	public static void main(String[] args)
	{
		String[] things={
							"д����",
							"��ϰ��ĩ"
						};
		int[] count=new int[things.length];
		int times=10000*1000;
		for(int i=0;i<times;i++)
		{
			count[NextIntMinToMax.randomIntMinToMax(0, things.length-1)]++;
		}
		//�������ֵ��
		int maxIndex=0;
		for(int i=1;i<count.length;i++)
		{
			if(count[i]>count[maxIndex])
				maxIndex=i;//���������±�
		}
		for (int i : count)
		{
			System.out.println(i);
		}
		System.out.println("�㽫:"+things[maxIndex]);
	}
}
