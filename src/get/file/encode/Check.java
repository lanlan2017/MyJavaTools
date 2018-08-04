package get.file.encode;

public class Check
{
	public static void main(String[] args)
	{
		System.out.print((byte)Integer.parseInt("EF", 16)+",");
		System.out.print((byte)Integer.parseInt("BB", 16)+",");
		System.out.print((byte)Integer.parseInt("BF", 16));
	}

}
