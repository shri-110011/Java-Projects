
public class LearnArrayDeclaration {

	static int Sample() {
		return (true?-1:0);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Declaring and initializing an integer array inline.
		int a[] = {11, 12, 13, 14, 15};
		int c[] = new int[] {11, 12, 13, 14, 15};
		//In Java you can't specify the size of the array while inline initialization of the array. 
//		int d[4] = {1,2,3,4};
		//Just printing the array name will print the base address of the array.
		System.out.println(a);
		//We can access any element in the array by specifying the element's position inside the [].
		System.out.println("a[0]: "+a[0]);
		//You can't access the 1st element of the array using the *(indirection operator).
//		System.out.println(*a);
		
		//Declaring an integer array of fixed size.
		//Note: While declaring an array like this the integer array gets initialized by 0.
		int b[] = new int[5];
		
		b[0]=16;
		b[3]=256;
		for(int i = 0; i<b.length; i++) {
			System.out.println("b["+i+"]: "+b[i]);
		}
		
		System.out.println(b);
		
//		for(int i = 0; i<b.length; i++) {
//			b[i] = i+21;
//		}
	
		//Note: Here we are making a reference to the array a i.e. we are just passing the base address of the array a to the array b.
		b = a;
		System.out.println(b);
		
		for(int i = 0; i<b.length; i++) {
			System.out.println("b["+i+"]: "+b[i]);
		}
		
		b[0] = 101;
		
		for(int i = 0; i<b.length; i++) {
			System.out.println("b["+i+"]: "+b[i]);
		}
		
		for(int i = 0; i<c.length; i++) {
			System.out.println("c["+i+"]: "+c[i]);
		}
		
		System.out.println(LearnArrayDeclaration.Sample());

	}

}
