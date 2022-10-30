package algorithms.competitive_progrmming;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

class Coord {
	int x, y;

	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Coord [x=" + x + ", y=" + y + "]";
	}

}

public class ContainTheVirus {
	
	public int containVirus(int isInfected[][]) {
		
		int wallCount = 0, rows = isInfected.length;
		boolean visited[][] = new boolean[rows][];
		
		for(int i=0; i<rows; i++) {
			visited[i] = new boolean[isInfected[i].length];
		}
		
		printIsInfectedArray(isInfected);
		printIsVisitedArray(visited);
		
		
		/* First identify the different continuous block of infected regions.
		 * During this process do the following things:
		 * 1. Store a cell's coordinates from each continuous block of infected 
		 * regions.
		 * 2. Also find the number of uninfected cells directionally adjacent
		 * to each of these continuous block of infected regions.
		 * 3. Disinfect the region which affects the most number of directionally adjacent
		 * uninfected cells.
		 * 4. Spread the virus to the remaining regions.
		 * 5. Repeat steps 1 to 4 until you don't get anymore infected regions. 
		 *
		 */
		
		while(true) {
			LinkedHashMap<Coord, Integer> hm = getInfectedRegionsInfo(isInfected, visited);

			System.out.println("Hashmap before sorting");
			System.out.println(hm);
			
			List<Map.Entry<Coord, Integer>> list = new LinkedList<>(hm.entrySet());
			
			if(list.size() == 0) break;
			
			Collections.sort(list, (o1,  o2) -> {
				
				return o2.getValue().compareTo(o1.getValue());
			});
			hm.clear();
			
			for(Map.Entry<Coord, Integer> entry: list)
				hm.put(entry.getKey(), entry.getValue());
			
			System.out.println("Hashmap after sorting");
			System.out.println(hm);
			
			Map.Entry<Coord, Integer> entry = list.get(0);
			
			int wallRequired = buildWall(isInfected, visited, entry.getKey());
			System.out.println("Walls required to contain the region "+entry.getKey()+" is: "+wallRequired);
			
			System.out.println("After building wall for the region "+entry.getKey());
			
			printIsInfectedArray(isInfected);
			
			printIsVisitedArray(visited);
			
			System.out.println("List after removing the region "+entry.getKey());
			list.remove(entry);
			
			System.out.println(list);
			
			for(Entry<Coord, Integer> e: list) {
				spreadVirus(isInfected, visited, e.getKey());
			}
			
			System.out.println("After spreading the virus");
			
			printIsInfectedArray(isInfected);
			
			printIsVisitedArray(visited);
			
			wallCount += wallRequired;
		
			System.out.println("---------------------");
		}
		
		return wallCount;
		
	}
	
	public LinkedHashMap<Coord, Integer> getInfectedRegionsInfo(int isInfected[][], boolean visited[][]) {
		LinkedHashMap<Coord, Integer> hm = new LinkedHashMap<>();
		
		int rows = isInfected.length;
		
		for(int i=0; i<rows; i++) {
			int cols = isInfected[i].length;
			for(int j=0; j<cols; j++) {
				if(isInfected[i][j] == 1 && !visited[i][j]) {
					// Found an infected cell
					Coord coord = new Coord(i, j);
					visited[i][j] = true;
					hm.put(coord , getUninfectedCellsCount(isInfected, visited, coord));
				}
			}
		}
		
		return hm;
	}
	
	public int getUninfectedCellsCount(int isInfected[][], boolean visited[][], Coord coord) {
		int uninfectedCellsCount = 0, i = coord.x, j = coord.y, rows = isInfected.length,
				cols = isInfected[i].length;
		Stack<Coord> stack1 = new Stack<>(), stack2 = new Stack<>();
		stack1.add(new Coord(i, j));

		while(!stack1.isEmpty()) {
			Coord c = stack1.pop();
			stack2.add(c);
			
			i = c.x; j= c.y;
			
			// Checking the left cell of cell(i,j)
			if(j-1 >= 0 && !visited[i][j-1]) {
				if(isInfected[i][j-1] == 0) {
					uninfectedCellsCount++;
				}
				else if(isInfected[i][j-1] == 1) {
					stack1.add(new Coord(i, j-1));
				}
				visited[i][j-1] = true;
			}
			// Checking the right cell of cell(i,j)
			if(j+1 < cols && !visited[i][j+1]) {
				if(isInfected[i][j+1] == 0) {
					uninfectedCellsCount++;
				}
				else if(isInfected[i][j+1] == 1) {
					stack1.add(new Coord(i, j+1));
				}
				visited[i][j+1] = true;
			}
			// Checking the top cell of cell(i,j)
			if(i-1 >= 0 && !visited[i-1][j]) {
				if(isInfected[i-1][j] == 0) {
					uninfectedCellsCount++;
				}
				else if(isInfected[i-1][j] == 1) {
					stack1.add(new Coord(i-1, j));
				}
				visited[i-1][j] = true;
			}
			// Checking the bottom cell of cell(i,j)
			if(i+1 < rows && !visited[i+1][j]) {
				if(isInfected[i+1][j] == 0) {
					uninfectedCellsCount++;
				}
				else if(isInfected[i+1][j] == 1) {
					stack1.add(new Coord(i+1, j));
				}
				visited[i+1][j] = true;
			}
		}
		unmarkUnInfectedCells(isInfected, visited, stack2);
		return uninfectedCellsCount;
	}
	
	public void unmarkUnInfectedCells(int isInfected[][], boolean visited[][], Stack<Coord> stack) {
		int i, j, rows=isInfected.length, cols;
		
		while(!stack.isEmpty()) {
			Coord c = stack.pop();
			i = c.x; j= c.y;
			cols = isInfected[i].length;
			
			// Checking the left cell of cell(i,j)
			if(j-1 >= 0) {
				if(isInfected[i][j-1] == 0) {
					visited[i][j-1] = false;
				}
				
			}
			// Checking the right cell of cell(i,j)
			if(j+1 < cols) {
				if(isInfected[i][j+1] == 0) {
					visited[i][j+1] = false;
				}
			}
			// Checking the top cell of cell(i,j)
			if(i-1 >= 0) {
				if(isInfected[i-1][j] == 0) {
					visited[i-1][j] = false;
				}
			}
			// Checking the bottom cell of cell(i,j)
			if(i+1 < rows) {
				if(isInfected[i+1][j] == 0) {
					visited[i+1][j] = false;
				}
			}
		}
	}
	
	public void spreadVirus(int isInfected[][], boolean visited[][], Coord coord) {
		int i=coord.x, j=coord.y, rows=isInfected.length, cols;
		Stack<Coord> stack = new Stack<>();
		stack.add(new Coord(i, j));
		visited[i][j] = false;
		
		while(!stack.isEmpty()) {
			Coord c = stack.pop();
			i = c.x; j= c.y;
			cols = isInfected[i].length;
			
			// Checking the left cell of cell(i,j)
			if(j-1 >= 0 && isInfected[i][j-1]!=2) {
				if(isInfected[i][j-1] == 0) {
					isInfected[i][j-1] = 1;
				}
				else if(visited[i][j-1]) {
					stack.add(new Coord(i, j-1));
					visited[i][j-1] = false;
				}
			}
			// Checking the right cell of cell(i,j)
			if(j+1 < cols && isInfected[i][j+1]!=2) {
				if(isInfected[i][j+1] == 0) {
					isInfected[i][j+1] = 1;
				}
				else if(visited[i][j+1]){
					stack.add(new Coord(i, j+1));
					visited[i][j+1] = false;
				}
			}
			// Checking the top cell of cell(i,j)
			if(i-1 >= 0 && isInfected[i-1][j]!=2) {
				if(isInfected[i-1][j] == 0) {
					isInfected[i-1][j] = 1;
				}
				else if(visited[i-1][j]){
					stack.add(new Coord(i-1, j));
					visited[i-1][j] = false;
				}
			}
			// Checking the bottom cell of cell(i,j)
			if(i+1 < rows && isInfected[i+1][j]!=2) {
				if(isInfected[i+1][j] == 0) {
					isInfected[i+1][j] = 1;
				}
				else if(visited[i+1][j]){
					stack.add(new Coord(i+1, j));
					visited[i+1][j] = false;
				}
			}
		}
	}
	
	/* This will return the walls used to contain the virus in 
	 * the continuous block of infected region around the cell
	 * with coordinate coord.
	 */
	public int buildWall(int isInfected[][], boolean visited[][], Coord coord) {
		int wallCount = 0, i=coord.x, j=coord.y, rows = isInfected.length,
				cols = isInfected[i].length;
		
		Stack<Coord> stack = new Stack<>();
		stack.add(new Coord(i, j));
		isInfected[i][j] = 2;
		
		while (!stack.isEmpty()) {
			Coord c = stack.pop();
			
			i = c.x; j= c.y;

			// Checking the left cell of cell(i,j)
			if(j-1 >= 0 && isInfected[i][j-1]!=2) {
				if(isInfected[i][j-1] == 0) {
					wallCount++;
				}
				else {
					stack.add(new Coord(i, j-1));
					isInfected[i][j-1] = 2;
				}
			}
			// Checking the right cell of cell(i,j)
			if(j+1 <cols && isInfected[i][j+1]!=2) {
				if(isInfected[i][j+1] == 0) {
					wallCount++;
				}
				else {
					stack.add(new Coord(i, j+1));
					isInfected[i][j+1] = 2;
				}
			}
			// Checking the top cell of cell(i,j)
			if(i-1 >= 0 && isInfected[i-1][j]!=2) {
				if(isInfected[i-1][j] == 0) {
					wallCount++;
				}
				else {
					stack.add(new Coord(i-1, j));
					isInfected[i-1][j] = 2;
				}
			}
			// Checking the bottom cell of cell(i,j)
			if(i+1 <rows && isInfected[i+1][j]!=2) {
				if(isInfected[i+1][j] == 0) {
					wallCount++;
				}
				else {
					stack.add(new Coord(i+1, j));
					isInfected[i+1][j] = 2;
				}
			}
		}
		return wallCount;
	}
	
	public void printIsInfectedArray(int isInfected[][]) {
		for(int i=0; i<isInfected.length; i++) {
			System.out.println(Arrays.toString(isInfected[i]));
		}
	}
	
	public void printIsVisitedArray(boolean visited[][]) {
		for(int i=0; i<visited.length; i++) {
			System.out.println(Arrays.toString(visited[i]));
		}
	}
	
	public static void main(String args[]) {
		
		int isInfected[][]= {{0,1,0,0,0,0,1,1}, {0,1,0,0,1,0,0,1}, {0,0,0,1,1,0,1,0}, {1,0,0,0,0,0,0,0}};
		
		ContainTheVirus obj = new ContainTheVirus();
		System.out.println("Total walls required: "+obj.containVirus(isInfected));
		
		
	}

}

/* Input: {{0,1,0,0,0,0,0,1}, {0,1,0,0,0,0,0,1}, {0,0,0,0,0,0,0,1}, {0,0,0,0,0,0,0,0}}
 * Output: 10
 * 
 * Input: {{0,1,0,0,0,0,1,1}, {0,1,0,0,1,0,0,1}, {0,0,0,1,1,0,1,0}, {1,0,0,0,0,0,0,0}}
 */
