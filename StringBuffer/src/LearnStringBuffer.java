
public class LearnStringBuffer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuffer sbr1 = new StringBuffer();
		StringBuffer sbr2 = new StringBuffer(8);
		StringBuffer sbr3 = new StringBuffer("shirt");
		
		StringBuffer sbr4;
		System.out.println(sbr1);
		//Note: Without initializing a string buffer object if we try to use it then we 
		//will get an error.
//		System.out.println(sbr4);
		if(sbr1 == null)
			System.out.println(true);
		else
			System.out.println(false);
		System.out.println(sbr1.length());
		System.out.println("sbr1.capacity(): "+sbr1.capacity());
		sbr1.append("Hello");
		System.out.println("sbr1.capacity(): "+sbr1.capacity());
		System.out.println("sbr2.capacity(): "+sbr2.capacity());
		System.out.println("sbr3.capacity(): "+sbr3.capacity());
		sbr2.append("siddharth");
		System.out.println("sbr2.capacity(): "+sbr2.capacity());
		System.out.println("sbr1: "+sbr1);
		System.out.println("sbr2: "+sbr2);
		System.out.println("sbr3: "+sbr3);
		System.out.println("sbr2.reverse(): "+sbr2.reverse());
		
	}

}
