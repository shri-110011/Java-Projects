
enum Apple2 {
	Jonathan, GoldenDel, RedDel, Winesap, Cortland 
}
public class EnumDemo2 {

	public static void main(String[] args) {
		Apple2 ap;
		
		System.out.println("Here are all the app constants:");
		
		//use values
		Apple2 allApples[] = Apple2.values();
		for(Apple2 a: allApples)
			System.out.println(a);
		
		System.out.println();
		
		//use valueOf()
		ap = Apple2.valueOf("Winesap");
		System.out.println("ap contains "+ap);
		
	}

}
