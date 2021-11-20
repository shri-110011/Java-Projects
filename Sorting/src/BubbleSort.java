
public class BubbleSort {
	
	public Number[] sort(Number[] numbers) {
		
		int n = numbers.length; 
		
		for(int i=0; i<n-2; i++) {
			for(int j=0; j<n-i-1; j++) {
				System.out.println("i = "+i+" , j = "+j);
				if(numbers[j].getValue()<numbers[j+1].getValue()) {
					swap(numbers, j, j+1);
				}
				display(numbers);
				System.out.println("");
			}
			System.out.println("------------------");
		}
		
		return numbers;
	}

	public int[] sort(int arr[]) {
		
		int n = arr.length; 
		
		for(int i=0; i<n-2; i++) {
			for(int j=0; j<n-i-1; j++) {
				System.out.println("i = "+i+" , j = "+j);
				if(arr[j]<arr[j+1]) {
					swap(arr, j, j+1);
				}
				display(arr);
				System.out.println("");
			}
			System.out.println("------------------");
		}
		
		return arr;
	}
	
	private void swap(int arr[], int i, int j) {
		int temp;
		temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	private void swap(Number[] numbers, int i, int j) {
		Number temp;
		temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
	
	public void display(int arr[]) {
		for(int i: arr) {
			System.out.print(i+" ");
		}
	}
	
	public void display(Number[] numbers) {
		for(Number number: numbers) {
			System.out.print("("+number.getValue()+","+number.getIndex()+")"+" ");
		}
	}
	
	public static void main(String[] args) {
		
		BubbleSort bsort = new BubbleSort();
		
//		int arr[] = {23, 17, 2, 11, 8, 40, 16};
//		
//		int sortedArray[] = bsort.sort(arr);
//		
//		System.out.println("Sorted array is:");
//		bsort.display(sortedArray);
		
		// -----------------------------------
		
		Number[] numbers =  {
				new Number(23,1),
				new Number(17,2),
				new Number(17,3),
				new Number(11,4),
				new Number(8,5),
				new Number(40,6),
				new Number(16,7),
		};
		
		Number[] sortedNumbers = bsort.sort(numbers);
		
		System.out.println("Sorted Number[] is:");
		bsort.display(sortedNumbers);
		
	}
	
}
