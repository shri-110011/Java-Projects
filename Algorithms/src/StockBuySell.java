import java.util.ArrayList;
import java.util.Scanner;

public class StockBuySell {
	
	public static ArrayList<ArrayList<Integer>> stockBuySell(int a[], int n) {
		int start=0, end=0, count=0;
		ArrayList<ArrayList<Integer>> al1 = new ArrayList<ArrayList<Integer>>();
		for(int i=1; i<n; i++) {
			if(a[i] <= a[i-1]) {
				end = i-1;
				if(end != start) {
					al1.add(new ArrayList<Integer>());
					al1.get(count).add(0, start);
					al1.get(count).add(1, end);
					count++;
				}
				start=i;
			}
			else if(i == n-1 && a[i] > a[i-1]) {
				end = i;
				al1.add(new ArrayList<Integer>());
				al1.get(count).add(0, start);
				al1.get(count).add(1, end);
			}
		}
		return al1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int size;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the array size");
		size = sc.nextInt();
		if(size>0) {
			int a[] = new int[size];
			System.out.println("Enter the array");
			for(int i=0;i<size;i++) {
				a[i] = sc.nextInt();
			}
			System.out.println(stockBuySell(a, size));
		}
		else {
			System.out.println("Size of an array must be greater than 0.");
		}
		sc.close();
	}
}
//100 180 260 310 40 535 695
//4 2 2 2 4
