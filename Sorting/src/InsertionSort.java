
public class InsertionSort {
	
	public Number[] sort(Number[] numbers) {
		
		int n = numbers.length; 
		
		for(int i=1; i<n; i++) {
			for(int j=i; j>0; j--) {
				System.out.println("i = "+i+" , j = "+j);
				if(numbers[j].getValue()>numbers[j-1].getValue()) {
					swap(numbers, j, j-1);
				}
				else {
					break;
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
		
		for(int i=1; i<n; i++) {
			for(int j=i; j>0; j--) {
				System.out.println("i = "+i+" , j = "+j);
				if(arr[j]>arr[j-1]) {
					swap(arr, j, j-1);
				}
				else {
					break;
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
		
		int arr[] = {23, 17, 2, 11, 8, 40, 16};

		InsertionSort isort = new InsertionSort();
		
		int sortedArray[] = isort.sort(arr);
		
//		System.out.println("Sorted array is:");
//		isort.display(sortedArray);
		
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
		
		Number[] sortedNumbers = isort.sort(numbers);
		
		System.out.println("Sorted Number[] is:");
		isort.display(sortedNumbers);
		
	}
	
}
