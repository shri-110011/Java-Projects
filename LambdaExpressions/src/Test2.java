interface FuncInter1 {
	int operation(int a);
}
public class Test2 {

	public static void main(String[] args) {
		FuncInter1 cube = (n) -> n*n*n;
		FuncInter1 square = (n) -> n*n;
		
		System.out.println(cube.operation(2));
		System.out.println(square.operation(35));
		System.out.println(4d+'a');
	}

}
