package test_algorithms.test_sorting_algorithms;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import algorithms.data_structures.HEAP_TYPE;
import algorithms.sorting_algorithms.HeapSort;
import utilities.HelperClass;

public class HeapSort_Test {

	static final Logger log = LogManager.getLogger(HeapSort_Test.class.getName());
	
	@Test
	public void testHeapSortMethod() {
		// Create an array
		int inputArrays[][] = {
				{6,3,14,2,4,19,1,8,-1,5,-9,14,2},
				{6,3,14,2,4,19,1,8,-1,5,-9,14,2},
				{}
		}, count=0;;
		HEAP_TYPE heapTypes[] = {
				HEAP_TYPE.MAX_HEAP,
				HEAP_TYPE.MIN_HEAP,
				HEAP_TYPE.MIN_HEAP
		};
//		{14, 25, 14, 4, 6, 2}
//		{10, 20, 15, 12, 40, 25, 18}
		log.info("Inside testHeapSortMethod():");
		for(int inputArray[]: inputArrays) {
			log.info("Sequence number: "+(count+1));
			HeapSort hs = new HeapSort(inputArray, heapTypes[count]);
			int expectedResult[] = inputArray.clone();
//			Arrays.sort(expectedResult);
			Arrays.sort(expectedResult);
			if(heapTypes[count].equals(HEAP_TYPE.MIN_HEAP)) {
				int len = expectedResult.length;
				for(int i=0; i< len/2; i++) {
					HelperClass.swap(expectedResult, i, len-i-1);
				}
			}
			
			
			log.info("inputArray:     "+Arrays.toString(inputArray));
			log.info("Heap type: "+heapTypes[count]);
			hs.heapSort();
			
			log.info("actualResult:   "+Arrays.toString(inputArray));
			log.info("expectedResult: "+Arrays.toString(expectedResult));
			
			Assert.assertArrayEquals("Arrays expectedResult[] and actualResult[] do not contain same elements.", expectedResult, inputArray);
			log.info("");
			count++;
		}
	}
	
	@Test
	public void testHeapifyMethod() {
		// Create an array
		int inputArrays[][] = {
				{6,3,14,2,4,19,1,8,-1,5,-9,14,2},
				{6,3,14,2,4,19,1,8,-1,5,-9,14,2},
				{}
		}, expectedResults[][] = {
			{19, 8, 14, 3, 5, 14, 1, 2, -1, 4, -9, 6, 2},
			{-9, -1, 1, 2, 3, 2, 14, 8, 6, 5, 4, 14, 19},
			{}
		}, count = 0;
		
		HEAP_TYPE heapTypes[] = {
				HEAP_TYPE.MAX_HEAP,
				HEAP_TYPE.MIN_HEAP,
				HEAP_TYPE.MAX_HEAP
		};
		
		log.info("Inside testHeapifyMethod():");
		
		for(int inputArray[]: inputArrays) {
			log.info("Sequence number: "+(count+1));
			HeapSort hs = new HeapSort(inputArray, heapTypes[count]);
			
			log.info("inputArray:     "+Arrays.toString(inputArray));
			log.info("Heap type: "+heapTypes[count]);
			
			hs.heapify();
			
			log.info("actualResult:       "+Arrays.toString(inputArray));
			log.info("expectedResult:     "+Arrays.toString(expectedResults[count]));
			
			Assert.assertArrayEquals("Arrays expectedResult[] and actualResult[] do not contain same elements.", expectedResults[count], inputArray);
			log.info("");
			count++;
		}
		
	}
	
	@Test
	public void testDeleteMethod() {
		// Create an array
		int inputArrays[][] = {
				{6,3,14,2,4,19,1,8,-1,5,-9,14,2},
				{6,3,14,2,4,19,1,8,-1,5,-9,14,2},
				{}
		}, indicesOfNodesToBeDeleted[][] = {
			{1, 5, 0},
			{3, 3, 6},
			{}
		}, count = 0;
		
		HEAP_TYPE heapTypes[] = {
				HEAP_TYPE.MAX_HEAP,
				HEAP_TYPE.MIN_HEAP,
				HEAP_TYPE.MAX_HEAP
		};
		
		log.info("Inside testHeapifyMethod():");
		
		for(int inputArray[]: inputArrays) {
			log.info("Sequence number: "+(count+1));
			HeapSort hs = new HeapSort(inputArray, heapTypes[count]);
			
			log.info("inputArray: "+Arrays.toString(inputArray));
			log.info("Heap type: "+heapTypes[count]);
			
			hs.heapify();
			log.info("inputArray after heapify: "+Arrays.toString(inputArray));
			int inputArrayLen = hs.getHeapSize();
			for(int idx: indicesOfNodesToBeDeleted[count]) {
				if(idx >= inputArrayLen || idx < 0) {
					Assert.fail("Invalid index: "+idx+" for the node to be deleted in sequence: "+(count+1)+"!");
				}
				
				log.info("Delete node index: "+idx);
				hs.delete(idx);
				hs.printHeap();
				
				Assert.assertTrue(hs.getHeapSize() == inputArrayLen-1);
				inputArrayLen = hs.getHeapSize();
			}
			count++;
			log.info("");
		}
	}
	
	@Test
	public void testDecreseKeyMethod() {
		// Create an array
		int inputArrays[][] = {
				{6,3,14,2,4,19,1,8,-1,5,-9,14,2},
				{6,3,14,2,4,19,1,8,-1,5,-9,14,2},
				{}
		}, indicesOfNodesToBeDecremented[][] = {
				{1, 5, 0},
				{3, 3},
				{}
		}, newValues[][] = {
				{-10, -15, -1},
				{-5, -6},
				{}
		}, count = 0;
		
		HEAP_TYPE heapTypes[] = {
				HEAP_TYPE.MAX_HEAP,
				HEAP_TYPE.MIN_HEAP,
				HEAP_TYPE.MAX_HEAP
		};
		
		log.info("Inside testDecreseKeyMethod():");
		
		for(int inputArray[]: inputArrays) {
			log.info("Sequence number: "+(count+1));
			HeapSort hs = new HeapSort(inputArray, heapTypes[count]);
			
			log.info("inputArray: "+Arrays.toString(inputArray));
			log.info("Heap type: "+heapTypes[count]);
			
			hs.heapify();
			log.info("inputArray after heapify: "+Arrays.toString(inputArray));
			int i=0;
			for(int idx: indicesOfNodesToBeDecremented[count]) {
				log.info("Node index for decrease key: "+idx);
				log.info("New value for decrease key: "+newValues[count][i]);
				
				int newPos = hs.decreaseKey(idx, newValues[count][i]);
				log.info("newPos: "+newPos);
				hs.printHeap();
				
				Assert.assertTrue(inputArray[newPos] == newValues[count][i]);
				i++;
			}
			count++;
			log.info("");
		}
	}
	
	@Test
	public void testInsertMethod() {
		// Create an array
		int inputArrays1[][] = {
				{6,3,14,2,4,19,1,8,-1,5,-9,14,2},
				{6,3,14,2,4,19,1,8,-1,5,-9,14,2},
				{}
		}, insertValues[][] = {
				{7, 0, 1},
				{4, 10},
				{}
		}, count = 0;
		
		int inputArrays2[][] = new int[inputArrays1.length][], i=0;
		for(int inputArray[]: inputArrays1) {
			inputArrays2[i] = Arrays.copyOf(inputArray, 17);
			System.out.println(inputArrays2[i].length);
			i++;
		}
		
		HEAP_TYPE heapTypes[] = {
				HEAP_TYPE.MAX_HEAP,
				HEAP_TYPE.MIN_HEAP,
				HEAP_TYPE.MAX_HEAP
		};
		
		log.info("Inside testInsertMethod():");
		
		for(int inputArray[]: inputArrays2) {
			log.info("Sequence number: "+(count+1));
			HeapSort hs = new HeapSort(inputArray, heapTypes[count]);
			
			log.info("inputArray: "+Arrays.toString(inputArray));
			log.info("inputArray length: "+inputArray.length);
			
			log.info("Heap type: "+heapTypes[count]);
			
			hs.heapify(inputArrays1[count].length);
			log.info("inputArray after heapify: "+Arrays.toString(inputArray));
			for(int val: insertValues[count]) {
				log.info("Insert value: "+val);
				
				log.info("Heap size: "+hs.getHeapSize());
				int insertPos = hs.insert(val);
				log.info("insertPos: "+insertPos);
				hs.printHeap();
				
				Assert.assertTrue(inputArray[insertPos] == val);
			}
			count++;
			log.info("");
		}
	}
}
