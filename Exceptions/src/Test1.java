class Test1
{
	String str = "a";

	void A()
	{
		try
		{
			str +="b";
			B();
		}
		catch (Exception e)
		{
			System.out.println("Flag 3");
			str += "c";
		}
	}

	void B() throws Exception
	{
		try
		{
			str += "d";
			C();
		}
		catch(Exception e)
		{
			System.out.println("Flag 2");
			throw new Exception();
		}
		finally
		{
			System.out.println("Flag 4");
			str += "e";
		}

		System.out.println("Flag 5");
		str += "f";

	}
	
	void C() throws Exception
	{
		System.out.println("Flag 1");
		throw new Exception();
	}

	void display()
	{
		System.out.println(str);
	}

	public static void main(String[] args)
	{
		Test1 object = new Test1();
		object.A();
		object.display();
	}

}
