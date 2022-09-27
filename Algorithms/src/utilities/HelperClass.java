package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import test_algorithms.test_sorting_algorithms.HeapSort_Test;

public class HelperClass {
	
	static final Logger log = LogManager.getLogger(HelperClass.class.getName());
	
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
		log.info("Displaying array: ");
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i=0;i<arr.length;i++) {
			sb.append(arr[i]);
			if(i!=arr.length-1) sb.append(", ");
		}
		sb.append("]");
		log.info(sb);
	}
	
	public static int max(int i, int j) {
		if(i>j) {
			return i;
		}
		else {
			return j;
		}
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
