import java.util.Arrays;
import java.util.Scanner;

public class PracticeArrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int arr1[] = new int[5];
//		Scanner sc = new Scanner(System.in);
//		
//		for(int i=0; i<5; i++) {
//			arr1[i]=sc.nextInt();
//		}
//		for(int i=0; i<5; i++) {
//			System.out.println(arr1[i]);
//		}
		
		int[][] arr2 = {{1}, {2}, {3}, {5}, {8}, {13}, {21,34,55}};
		for(int i=0; i<arr2.length; i++) {
			for(int j=0; j<arr2[i].length; j++)
				System.out.println(arr2[i][j]);
		}
		System.out.println(Arrays.deepToString(arr2));
		
		int arr3[] = {1,2,3,4,5};
		int arr4[] = new int[arr3.length];
//		arr4 = arr3.clone();
		
		System.arraycopy(arr3,0,arr4,0,arr3.length);
		
		arr4[0] = 100;
		
		for(int i=0; i<arr3.length; i++) {
			System.out.println(arr3[i]);
		}
	
		for(int i=0; i<arr4.length; i++) {
			System.out.println(arr4[i]);
		}
		
		
		

	}

}
