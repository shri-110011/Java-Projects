package utilities;

import org.apache.logging.log4j.Logger;

public class HelperClass {
	
	public static int[] concatenateArrays(int a[], int b[]) {
		
		int a_len = a.length, b_len = b.length, i=0, j=0, k=0;
		int c[] = new int[a_len+b_len];
		
		while(i<a_len) {
			c[k] = a[i];
			k++; 
			i++;
		}
		while(j<b_len) {
			c[k] = b[j];
			k++; 
			j++;
		}
		return c;
	}
	
	public static void displayArray(int arr[]) {
		System.out.println("Displaying array: ");
		System.out.print("[");
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]);
			if(i!=arr.length-1) System.out.print(", ");
		}
		System.out.print("]");
		System.out.println();
	}

	public static void swap(int a[], int swap_pos1, int swap_pos2) {
		int temp = a[swap_pos1];
		a[swap_pos1] = a[swap_pos2];
		a[swap_pos2] = temp;
	}
	
	public static int generateRandomNumber(int min, int max) {
		int rn = (int)Math.floor( Math.random()*max) + min;
		return rn;
	}
	
	public static void logException(Logger log, String msg, StackTraceElement elements[]) {
		log.error("<-------------Stacktrace---------------");
		log.error(msg);
		for(StackTraceElement el: elements) {
			log.error(el);
		}
		log.error("-------------------------------------->");
	}
}
