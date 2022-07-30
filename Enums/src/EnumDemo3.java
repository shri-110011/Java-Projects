
// This program illustrates that Java Enumeration are Class Types.
// Because in an enum one can declare methods, instance variables, constructors or even implement interfaces.
enum Apple3 {
	Jonathan(10), GoldenDel(9), RedDel(12), Winesap(15), Cortland(8);
	
	private int price;//price of each apple
	
	//Constructor
	Apple3(int p) {price = p;}
	
	int getPrice() {return price;}
	
	
}
public class EnumDemo3 {

	public static void main(String[] args) {
		Apple ap;
		
		//Display price of Winesapp.
		System.out.println("Winesapp "+Apple3.Winesap.getPrice());
		
		System.out.println();
		
		//Display all apples and their prices.
		System.out.println("All apples price:");
		for(Apple3 a: Apple3.values()) {
			System.out.println(a+" costs "+a.getPrice()+" cents");
		}
		
		
	}

}
