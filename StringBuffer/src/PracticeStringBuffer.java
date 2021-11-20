
public class PracticeStringBuffer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringBuffer sbuf1 = new StringBuffer("Lion");
		System.out.println(sbuf1);
		sbuf1.append(" King");
		System.out.println(sbuf1);
		System.out.println(sbuf1.length());
//		System.out.println(sbuf1.reverse());
		System.out.println(sbuf1.replace(5, 9, "dates"));
		StringBuffer sbuf2 = new StringBuffer("Monkey");
		StringBuffer sbuf3 = new StringBuffer("Monkey");
		System.out.println(sbuf2.equals(sbuf3));
	}

}
