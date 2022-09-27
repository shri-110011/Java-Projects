package algorithms.subsequences_algorithms;

import java.util.ArrayList;
import java.util.Arrays;

public class LongestIncreasingSubArray {
	
	int endIndicesOfLISA[];
	/* getLISALen(int arr[]) will return the length of the
	 * longest increasing subarray.
	 * 
	 */
	public int getLISALen(int arr[]) {
		
		int arrLen = arr.length, lisaLen  = (arrLen==0) ? 0: 1,
				curISALen = lisaLen, lisaLengths[] = new int[arrLen],
				noOfLISA = 0;
		
		System.out.println("arr: "+Arrays.toString(arr));
		System.out.println("arrLen: "+arrLen);
		Arrays.fill(lisaLengths, 1);
		for(int i=1; i<arrLen; i++) {
			if(arr[i-1] < arr[i]) {
				curISALen++;
				lisaLengths[i] = curISALen;
			}
			else {
				if(lisaLen < curISALen) {
					noOfLISA = 1;
					lisaLen = curISALen;
				}
				else if(lisaLen == curISALen) {
					noOfLISA++;
				}
				curISALen = 1;
			}
		}
		/* In case the endIndex of LISA is arrLen-1 then
		 * we need to compare lisaLEn and curISALen to 
		 * get the correct value for LISA.
		 * 
		 * At this point curISALen contains the length of 
		 * increasing sub-array that ends at index 
		 * arrLen-1.
		 * 
		 */
		if(lisaLen < curISALen) {
			noOfLISA = 1;
			lisaLen = curISALen;
		}
		else if(lisaLen == curISALen && lisaLen!=0){
			noOfLISA++;
		}
		/* Here we are storing the indices in lisaLengths
		 * where the value is lisLen.
		 */
		int count = 0;
		endIndicesOfLISA = new int[noOfLISA];
		for(int i=0; i<lisaLengths.length; i++) {
			if(lisaLengths[i] == lisaLen) {
				endIndicesOfLISA[count] = i;
				count++;
			}
		}
		System.out.println("lisaLen: "+lisaLen);
		System.out.println("noOfLISA: "+noOfLISA);
		System.out.println("lisaLengths: "+Arrays.toString(lisaLengths));
		System.out.println("endIndicesOfLISA: "+Arrays.toString(endIndicesOfLISA));
		return lisaLen;
	}
	
	public ArrayList<ArrayList<Integer>> printLISA(int arr[]) {
		int lisaLen = getLISALen(arr);
		
		ArrayList<ArrayList<Integer>> lisaList = new ArrayList<>();
		if(lisaLen == 0) return lisaList;
		for(int i=0; i<endIndicesOfLISA.length; i++) {
			ArrayList<Integer> lisa = new ArrayList<>();
			int endIdxOfLISA = endIndicesOfLISA[i];
			System.out.println("LISA: "+(i+1));
			for(int j=endIdxOfLISA-lisaLen+1; j<=endIdxOfLISA; j++) {
				System.out.print(arr[j]);
				lisa.add(arr[j]);
				if(j!=endIdxOfLISA) System.out.print(" ");
			}
			System.out.println();
			lisaList.add(lisa);
		}
		return lisaList;
	}
	
	public static void main(String[] args) {
		
		int arr[] = {};
		// {5, 7, 10, 6, 9, 12, 14, -1, 0, 1, 3, 8, 9}
		// {3, 7, 10, 12, 4, 6, 8, 1}
		// {1, 2, 3, -3, -2, -1}
		// {1}
		
		LongestIncreasingSubArray lisa = new LongestIncreasingSubArray();
		
//		System.out.println(lisa.getLISALen(arr));
//		System.out.println("endIdxOfLISA: "+lisa.endIdxOfLISA);
		System.out.println(lisa.printLISA(arr));
	}

}
