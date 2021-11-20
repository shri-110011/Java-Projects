public class LearnStringBuilder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder("shri");
		System.out.println("sb1.capacity(): "+sb1.capacity());
		System.out.println("sb2.capacity(): "+sb2.capacity());
		sb1.append("siddhu");
		System.out.println(sb1);
		System.out.println(sb2);
		System.out.println("sb1.capacity(): "+sb1.capacity());
		System.out.println("sb2.capacity(): "+sb2.capacity());
		StringBuilder sb3 = sb1.reverse();
		System.out.println("sb1: "+sb1);
		System.out.println("sb3: "+sb3);
		System.out.println("sb2.reverse(): "+sb2.reverse());
		sb1.reverse();
		System.out.println("sb1.reverse(): "+sb1);
		if(sb3 == sb1) {
			System.out.println("sb3 is same as sb1");
		}
		else {
			System.out.println("sb3 and sb1 are different");
		}
		StringBuilder sb4 = new StringBuilder("lion");
//		sb4.append(null);
		String str1 = sb4.toString();
		String str2 = sb4.toString();
		System.out.println(str1.equals(str2));
		System.out.println(sb4);
	}

}
