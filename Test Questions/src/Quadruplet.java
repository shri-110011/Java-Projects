import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Quadruplet {
	
	public static void findMaxProduct(int arr[], int n) {
		int i = 0, j = n-1, maxProduct=0;
		ArrayList<Integer> al1 = new ArrayList<Integer>();
		while(al1.size() < 4) {
			if(Math.abs(arr[j]) >= Math.abs(arr[i])) {
				al1.add(arr[j]);
				j--;
			}
			else {
				al1.add(arr[i]);
				i++;
			}
			if(al1.size()==4) {
				maxProduct = 1;
				System.out.println(al1);
				for(int k = 0; k<4; k++) {
					maxProduct *= al1.get(k);
				}
				if(maxProduct < 0) {
					for( int l = 3; l>=0; l--) {
						if(al1.get(l) < 0) {
							al1.remove(l);
						}
					}
				}
			}
		}
		System.out.println(maxProduct);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the count of values you are going to insert");
		n = sc.nextInt();
		
		int arr[] = new int[n];
		for(int i = 0; i<n; i++)
			arr[i] = sc.nextInt();
		Arrays.sort(arr);
		for(int i = 0; i<n; i++)
			System.out.print(arr[i]+" ");
		System.out.println("\n");
		Quadruplet.findMaxProduct(arr, n);
		
	}
}
