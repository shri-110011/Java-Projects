package test_algorithms.test_data_structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import algorithms.data_structures.HEAP_TYPE;
import algorithms.data_structures.Heap;

public class Heap_Test {
	static final Logger log = LogManager.getLogger(Heap_Test.class.getName());

	@Test
	public void testHeapSortMethod() {
		// Create an array
		Integer inputArrays[][] = {
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
		for(Integer inputArray[]: inputArrays) {
			log.info("Sequence number: "+(count+1));
			ArrayList<Integer> actualList = new ArrayList<>();
			actualList.addAll(Arrays.asList(inputArray));
			Heap<Integer> hs = new Heap<>(actualList, heapTypes[count]);
			
			ArrayList<Integer> expectedList = new ArrayList<>();
			expectedList.addAll(actualList);

			// Here we are reversing the int [] expectedResult
			if(heapTypes[count].equals(HEAP_TYPE.MIN_HEAP)) {
				Collections.sort(expectedList, Collections.reverseOrder());
			}
			else {
				Collections.sort(expectedList);
			}
			
			
			log.info("inputArray:     "+Arrays.toString(inputArray));
			log.info("Heap type: "+heapTypes[count]);
			hs.heapSort();
			
			log.info("actualList:   "+actualList);
			log.info("expectedList: "+expectedList);
			
			Assert.assertTrue("Arrays expectedList and actualList do not contain same elements.", expectedList.equals(actualList));
			log.info("");
			count++;
		}
	}
	
	@Test
	public void testHeapifyMethod() {
		// Create an array
		Integer inputArrays[][] = {
				{6,3,14,2,4,19,1,8,-1,5,-9,14,2},
				{6,3,14,2,4,19,1,8,-1,5,-9,14,2},
				{}
		}, expectedResults[][] = {
			{19, 8, 14, 3, 5, 14, 1, 2, -1, 4, -9, 6, 2},
			{-9, -1, 1, 2, 3, 2, 14, 8, 6, 5, 4, 14, 19},
			{}
		};
		int count = 0;
		
		HEAP_TYPE heapTypes[] = {
				HEAP_TYPE.MAX_HEAP,
				HEAP_TYPE.MIN_HEAP,
				HEAP_TYPE.MAX_HEAP
		};
		
		log.info("Inside testHeapifyMethod():");
		
		for(Integer inputArray[]: inputArrays) {
			log.info("Sequence number: "+(count+1));
			ArrayList<Integer> actualList = new ArrayList<>();
			actualList.addAll(Arrays.asList(inputArray));
			Heap<Integer> hs = new Heap<>(actualList, heapTypes[count]);
			
			ArrayList<Integer> expectedList = new ArrayList<>();
			expectedList.addAll(Arrays.asList(expectedResults[count]));
			
			log.info("inputArray:     "+Arrays.toString(inputArray));
			log.info("Heap type: "+heapTypes[count]);
			
			hs.heapify();
			
			log.info("actualList:   "+actualList);
			log.info("expectedList: "+expectedList);
			
			Assert.assertTrue("Arrays expectedList and actualList do not contain same elements.", expectedList.equals(actualList));
			log.info("");
			count++;
		}
		
	}
	
	@Test
	public void testDeleteMethod() {
		// Create an array
		Integer inputArrays[][] = {
				{6,3,14,2,4,19,1,8,-1,5,-9,14,2},
				{6,3,14,2,4,19,1,8,-1,5,-9,14,2},
				{}
		}, indicesOfNodesToBeDeleted[][] = {
			{1, 5, 0},
			{3, 3, 6},
			{}
		};
		int count = 0;
		
		HEAP_TYPE heapTypes[] = {
				HEAP_TYPE.MAX_HEAP,
				HEAP_TYPE.MIN_HEAP,
				HEAP_TYPE.MAX_HEAP
		};
		
		log.info("Inside testHeapifyMethod():");
		
		for(Integer inputArray[]: inputArrays) {
			log.info("Sequence number: "+(count+1));
			ArrayList<Integer> actualList = new ArrayList<>();
			actualList.addAll(Arrays.asList(inputArray));
			Heap<Integer> hs = new Heap<>(actualList, heapTypes[count]);
			
			log.info("inputArray: "+Arrays.toString(inputArray));
			log.info("Heap type: "+heapTypes[count]);
			
			hs.heapify();
			log.info("actualList after heapify: "+actualList);
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
		Integer inputArrays[][] = {
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
		};
		int count = 0;
		
		HEAP_TYPE heapTypes[] = {
				HEAP_TYPE.MAX_HEAP,
				HEAP_TYPE.MIN_HEAP,
				HEAP_TYPE.MAX_HEAP
		};
		
		log.info("Inside testDecreseKeyMethod():");
		
		for(Integer inputArray[]: inputArrays) {
			log.info("Sequence number: "+(count+1));
			ArrayList<Integer> actualList = new ArrayList<>();
			actualList.addAll(Arrays.asList(inputArray));
			Heap<Integer> hs = new Heap<>(actualList, heapTypes[count]);
			
			log.info("inputArray: "+Arrays.toString(inputArray));
			log.info("Heap type: "+heapTypes[count]);
			
			hs.heapify();
			log.info("actualList after heapify: "+actualList);
			int i=0;
			for(int idx: indicesOfNodesToBeDecremented[count]) {
				log.info("Node index for decrease key: "+idx);
				log.info("New value for decrease key: "+newValues[count][i]);
				
				int newPos = hs.decreaseKey(idx, newValues[count][i]);
				log.info("newPos: "+newPos);
				hs.printHeap();
				
				Assert.assertTrue(actualList.get(newPos) == newValues[count][i]);
				i++;
			}
			count++;
			log.info("");
		}
	}
	
	@Test
	public void testInsertMethod() {
		// Create an array
		Integer inputArrays1[][] = {
				{6,3,14,2,4,19,1,8,-1,5,-9,14,2},
				{6,3,14,2,4,19,1,8,-1,5,-9,14,2},
				{}
		}, insertValues[][] = {
				{7, 0, 1},
				{4, 10},
				{}
		};
		int count = 0;
		
		HEAP_TYPE heapTypes[] = {
				HEAP_TYPE.MAX_HEAP,
				HEAP_TYPE.MIN_HEAP,
				HEAP_TYPE.MAX_HEAP
		};
		
		log.info("Inside testInsertMethod():");
		
		for(Integer inputArray[]: inputArrays1) {
			log.info("Sequence number: "+(count+1));
			ArrayList<Integer> actualList = new ArrayList<>();
			actualList.addAll(Arrays.asList(inputArray));
			Heap<Integer> hs = new Heap<>(actualList, heapTypes[count]);
			
			log.info("inputArray: "+Arrays.toString(inputArray));
			log.info("inputArray length: "+inputArray.length);
			
			log.info("Heap type: "+heapTypes[count]);
			
			hs.heapify();
			log.info("actualList after heapify: "+actualList);
			for(Integer val: insertValues[count]) {
				log.info("Insert value: "+val);
				
				log.info("Heap size: "+hs.getHeapSize());
				int insertPos = hs.insert(val);
				log.info("insertPos: "+insertPos);
				hs.printHeap();
				
				Assert.assertTrue(actualList.get(insertPos) == val);
			}
			count++;
			log.info("");
		}
	}

}
