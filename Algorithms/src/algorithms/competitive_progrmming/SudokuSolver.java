package algorithms.competitive_progrmming;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

enum ComponentType {
	ROW, COLUMN, BLOCK, BLOCK_ROW, BLOCK_COLUMN
}

class Info {
	private int noOfVoids;
	private ComponentType ct;
	private List<Integer> indicesList;
	
	public Info(int noOfVoids, ComponentType ct,List<Integer> indicesList) {
		this.noOfVoids = noOfVoids;
		this.ct = ct;
		this.indicesList = indicesList;
	}
	
	public int getNoOfVoids() {
		return noOfVoids;
	}

	public ComponentType getCt() {
		return ct;
	}

	public List<Integer> getIndicesList() {
		return indicesList;
	}

	@Override
	public String toString() {
		return "Info [noOfVoids=" + noOfVoids + ", ct=" + ct + ", indicesList=" + indicesList + "]";
	}
}

public class SudokuSolver {
	
	static final Logger log = LogManager.getLogger(SudokuSolver.class.getName());
	
	ArrayList<ArrayList<Integer>> rows = new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<Integer>> cols = new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<Integer>> blocks = new ArrayList<ArrayList<Integer>>();
    ArrayList<ArrayList<ArrayList<Integer>>> notes = new ArrayList<ArrayList<ArrayList<Integer>>>();
    
    private char[][] board;
    private int emptyCells;
    
    private void loadBoardInfo() {
        for(int i=0; i<9; i++) {
        	rows.add(new ArrayList<Integer>());
        	cols.add(new ArrayList<Integer>());
        	blocks.add(new ArrayList<Integer>());
        }
        
        int blockNumber;
        for(int i=0; i<9; i++) {
        	notes.add(new ArrayList<ArrayList<Integer>>());
	        for(int j=0; j<9; j++) {
        		/* We can find the block numbers using the row and
        		 * column indices i.e. using i, j.
        		 * blockNumber = 3*(i/3)+j/3
        		 */
	        	blockNumber = 3*(i/3)+j/3;
	    	    if(board[i][j]!='.') {
	    	    	rows.get(i).add(board[i][j]-48);
	    	    	blocks.get(blockNumber).add(board[i][j]-48);
	    	    }
	    	    else {
	    	    	emptyCells++;
	    	    }
	    	    
	        	if(board[j][i]!='.') {
	        		cols.get(i).add(board[j][i]-48);
	        	}
	        	notes.get(i).add(new ArrayList<Integer>());
	        }
        }
        log.info("Printing rows:");
        print2D(rows);
        log.info("\nPrinting columns:");
        print2D(cols);
        log.info("\nPrinting blocks:");
        print2D(blocks);
        
        // Now we get the notes for each empty cell in the board.
        for(int i=0; i<9; i++) {
	        for(int j=0; j<9; j++) {
	        	if(board[i][j]=='.') {
	        		// Found an empty cell
	        		List<Integer> missingNos = getMissingNos(rows.get(i));
	        		List<Integer> obtainedNotes = getNotes(missingNos, i, j, ComponentType.ROW);
	        		for(int num: obtainedNotes) {
	        			notes.get(i).get(j).add(num);
	        		}
	        	}
	        }
        }
        printNotes();
        printBoard(board);
    }
    
	/* 
	 * We need to store information about what all numbers are to 
	 * come in each row, each column and in each 3*3 block.
	 * 
	 * Instead of starting from top-left cell and going left to 
	 * right and then top to bottom; we choose that row or column 
	 * or block that has the least number of numbers to be filled.
	 * 
	 * If that happens to be a:
	 * 
	 * Row:
	 * 1. Check from left to right. 
	 * 2. If an empty cell is found.
	 * 3. Check what all numbers can come in that cell by looking
	 * what all numbers are to come in that row.
	 * 4. a) Choose one such number and check if that number appears
	 * in that corresponding column or block.
	 * b) If it doesn't then there is a possibility that the chosen
	 * number can come in that cell.
	 * c) Repeat 4 a) and 4 b) for all numbers that come in that 
	 * row.
	 * d) If there is only one number that can appear in that cell
	 * out of all the numbers that are to come in that corresponding 
	 * row then we place that number in that cell. 
	 * 
	 * 
	 */
	public void solveSudoku(char[][] board) {
		
		this.board = board;
        
		loadBoardInfo();
		
		log.info("Empty cells: "+emptyCells);
		
		/* Look for naked singles or last digit in a component in the
		 * board and insert that naked single or the last digit.
		 */
		log.info("naked singles found: "+nakedSinglesFinder());
		
		printNotes();
		
        PriorityQueue<Info> bestComponentsInfo = loadBestComponentsInfo();
        int curProgress;
        log.info("Empty cells: "+emptyCells);
        log.info("bestComponentsInfo size: "+bestComponentsInfo.size());
        log.info("bestComponentsInfo: "+bestComponentsInfo);
        do {
        	if(bestComponentsInfo.size() == 0) {
        		bestComponentsInfo = loadBestComponentsInfo();
        		log.info("bestComponentsInfo: "+bestComponentsInfo);
        	}
        	
        	log.info("bestComponentsInfo size: "+bestComponentsInfo.size());
        	Info bestAmongBestComponents = getBestAmongBestComponents(bestComponentsInfo);
        	log.info(bestAmongBestComponents);
            
        	curProgress = 0;
            
            if(bestAmongBestComponents.getCt() == ComponentType.ROW) {
            	curProgress = processRow(bestAmongBestComponents, board);
            }
            else if(bestAmongBestComponents.getCt() == ComponentType.COLUMN) {
            	curProgress = processColumn(bestAmongBestComponents, board);
            }
            else if(bestAmongBestComponents.getCt() == ComponentType.BLOCK) {
            	curProgress = processBlock(bestAmongBestComponents, board);
            }
            
            log.info("curProgress: "+curProgress);
            
            if(curProgress == 0 && bestComponentsInfo.size() == 0) {
            	curProgress = nakedSinglesFinder();
            	log.info("curProgress after running nakedSinglesFinder(): "+curProgress);
            	log.info("Empty cells: "+emptyCells);
            }
            
            if(curProgress == 0 && bestComponentsInfo.size() == 0) {
            	curProgress = lockedCandidatesOfType1Finder();
            	log.info("curProgress after runnning lockedCandidatesOfType1Finder(): "+curProgress);
            	log.info("Empty cells: "+emptyCells);
            }
 
            if(curProgress == 0 && bestComponentsInfo.size() == 0) {
            	curProgress = lockedCandidatesOfType2Finder();
            	log.info("curProgress after runnning lockedCandidatesOfType2Finder(): "+curProgress);
            	log.info("Empty cells: "+emptyCells);
            }
            
            if(curProgress == 0 && bestComponentsInfo.size() == 0) {
            	curProgress = nakedPairsFinder();
            	log.info("curProgress after runnning nakedPairsFinder(): "+curProgress);
            	log.info("Empty cells: "+emptyCells);
            }
            
        } while(emptyCells>0 && (curProgress>0 || bestComponentsInfo.size()>0));
        
        printNotes();
        log.info("\n\n");
        log.info("Empty cells: "+emptyCells);
        printBoard(board);
    }
	
	/* loadBestComponentsInfo() loads the best components into a
	 * PriorityQueue.
	 */
	private PriorityQueue<Info> loadBestComponentsInfo() {
//		ArrayList<Info> boardInfo = new ArrayList<>();
		PriorityQueue<Info> bestComponentsInfo = new PriorityQueue<>(
				(Info obj1, Info obj2)->
				obj1.getNoOfVoids()-obj2.getNoOfVoids()
			);
		bestComponentsInfo.add(getBestComponents(rows, ComponentType.ROW));
		bestComponentsInfo.add(getBestComponents(cols, ComponentType.COLUMN));
		bestComponentsInfo.add(getBestComponents(blocks, ComponentType.BLOCK));
		
		return bestComponentsInfo;
	}
	
	/* getBestComponents(ArrayList<ArrayList<Integer>> rows, ComponentType ct) 
	 * returns those components from the list of components having 
	 * the minimum number of voids.
	 * 
	 * Here the list of components contain components of same type.
	 * 
	 * A best component can be a row/column/block that has the least
	 * number of voids.
	 */
	private Info getBestComponents(ArrayList<ArrayList<Integer>> components, ComponentType ct) {
		int minVoids=9;
		ArrayList<Integer> rowIndices = new ArrayList<>();
		for(int i=0; i<9; i++) {
			if((9-components.get(i).size())<minVoids && components.get(i).size() != 9) {
				minVoids = 9-components.get(i).size();
			}
		}
		for(int i=0; i<9; i++) {
			if(9-components.get(i).size() == minVoids) {
				rowIndices.add(i);
			}
		}
		return new Info(minVoids, ct, rowIndices);
	}
	
	/* getBestAmongBestComponents(PriorityQueue<Info> boardInfo) 
	 * returns the best components from the list of best components.
	 */
	private Info getBestAmongBestComponents(PriorityQueue<Info> boardInfo) {
		return boardInfo.remove();
	}
	
	private int processRow(Info info, char[][] board) {
		List<Integer> rowIndices = info.getIndicesList();
		int overAllProgress = 0;
		
		for(int rowIdx: rowIndices) {
			
			/* missingNos contains a list of integers that
			 * are to come in this row.
			 */
			List<Integer> missingNos = getMissingNos(rows.get(rowIdx));
			
			int progress;
			
			do {
				progress = 0;
				// Scanning the row's cells from left to right.
				for(int i=0; i<9; i++) {
					if(board[rowIdx][i]=='.') {
						
						/* Here we are handling the naked singles i.e.
						 * a cell having only one possible candidate.
						 */
						if(missingNos.size() == 1) {
							insertNumber(missingNos.get(0), rowIdx, i);
							missingNos.clear();
							progress++;
							continue;
						}
			
						/* possibleNos contains a list of integers 
						 * that can come in this cell.
						 */
						List<Integer> possibleNos = notes.get(rowIdx).get(i);
						
						if(possibleNos.size() == 1) {
							// Insert that missing no in this cell
							int num = possibleNos.get(0);
							insertNumber(num, rowIdx, i);
							
							/* Remove the number that has come at this cell
							 * from missingNos list.
							 */
							missingNos.remove((Integer)num);
							
							progress++;
						}
						else {
							/* Here we have found multiple numbers that can
							 * come in this cell so we now need to look for 
							 * hidden singles.
							 */
							if(hiddenSinglesFinder(possibleNos, rowIdx, i, ComponentType.ROW))
								progress++;
						}
					}
				}
				overAllProgress += progress;
			} while(progress>0 && cols.get(rowIdx).size()<9);
		}
		return overAllProgress;
	}
	
	private int processColumn(Info info, char[][] board) {
		List<Integer> colIndices = info.getIndicesList();
		int overAllProgress = 0;
		
		for(int colIdx: colIndices) {
			
			/* missingNos contains a list of integers that
			 * are to come in this column.
			 */
			List<Integer> missingNos = getMissingNos(cols.get(colIdx));
			
			int progress;
			
			do {
				progress = 0;
				// Scanning the column's cells from top to bottom.
				for(int i=0; i<9; i++) {
					if(board[i][colIdx]=='.') {
						
						/* Here we are handling the naked singles i.e.
						 * a cell having only one possible candidate.
						 */
						if(missingNos.size() == 1) {
							insertNumber(missingNos.get(0), i, colIdx);
							missingNos.clear();
							progress++;
							continue;
						}
			
						/* possibleNos contains a list of integers 
						 * that can come in this cell.
						 */
						List<Integer> possibleNos = notes.get(i).get(colIdx);
						
						if(possibleNos.size() == 1) {
							// Insert that missing no in this cell
							int num = possibleNos.get(0);
							insertNumber(num, i, colIdx);
							
							/* Remove the number that has come at this cell
							 * from missingNos list.
							 */
							missingNos.remove((Integer)num);
							
							progress++;
						}
						else {
							/* Here we have found multiple numbers that can
							 * come in this cell so we now need to look for 
							 * hidden singles.
							 */
							if(hiddenSinglesFinder(possibleNos, i, colIdx, ComponentType.COLUMN))
								progress++;
						}
					}
				}
				overAllProgress += progress;
			} while(progress>0 && cols.get(colIdx).size()<9);
		}
		return overAllProgress;
	}
	
	private int processBlock(Info info, char[][] board) {
		List<Integer> blockIndices = info.getIndicesList();
		int overAllProgress = 0;
		
		for(int blockIdx: blockIndices) {
			
			/* missingNos contains a list of integers that
			 * are to come in this block.
			 */
			List<Integer> missingNos = getMissingNos(blocks.get(blockIdx));
			
			int progress;
			
			do {
				progress = 0;
				/* Scanning the block's cells from right to left and
				 * top to bottom.
				 */
				for(int i=3*(blockIdx/3); i<3*(blockIdx/3)+3; i++) {
					for(int j=3*(blockIdx%3); j<3*(blockIdx%3)+3; j++) {
						if(board[i][j]=='.') {
							
							/* Here we are handling the naked singles i.e.
							 * a cell having only one possible candidate.
							 */
							if(missingNos.size() == 1) {
								insertNumber(missingNos.get(0), i, j);
								missingNos.clear();
								progress++;
								continue;
							}

							/* possibleNos contains a list of integers 
							 * that can come in this cell.
							 */
							List<Integer> possibleNos = notes.get(i).get(j);
							
//							if(i==7 && j==6) {
//								log.info(i+" "+j);
//								log.info(possibleNos);
//								printBoard(board);
//							}
							
							if(possibleNos.size() == 1) {
								// Insert that missing no in this cell
								int num = possibleNos.get(0);
								insertNumber(num, i, j);
								
								/* Remove the number that has come at this cell
								 * from missingNos list.
								 */
								missingNos.remove((Integer)num);
								
								progress++;
							}
							else {
								/* Here we have found multiple numbers that can
								 * come in this cell so we now need to look for 
								 * hidden singles.
								 */
								if(hiddenSinglesFinder(possibleNos, i, j, ComponentType.BLOCK))
									progress++;
							}
						}
					}
				}
				overAllProgress += progress;
			} while(progress>0 && blocks.get(blockIdx).size()<9);
		}
		return overAllProgress;
	}
	
	/* getMissingNos(ArrayList<Integer> al) will return a list of
	 * integers which are not there in ArrayList al.
	 */
	private List<Integer> getMissingNos(ArrayList<Integer> al) {
		List<Integer> missingNos = new ArrayList<>();
		for(int i=1; i<=9; i++) {
			if(!al.contains(i)) {
				missingNos.add(i);
			}
		}
		return missingNos;
	}
	
	/* getNotes(List<Integer> missingNos, int rowIdx, int colIdx, ComponentType ct)
	 * will return a list of possible integers which can come at
	 * the cell(rowIdx, colIdx) by checking any two of the 
	 * following:
	 * Corresponding row, column and block of that cell.
	 * To check just the two correct components we have the 
	 * parameter ComponentType ct.
	 * If ct == ComponentType.ROW this means that the list missingNos
	 * contain numbers that are missing in that component i.e.
	 * the row.
	 * So we don't need to check this row in order to find the 
	 * possible numbers for this cell because this cell is a part
	 * of that component. 
	 */
	private List<Integer> getNotes(List<Integer> missingNos, int rowIdx, int colIdx, ComponentType ct) {
		List<Integer> possibleNos = new ArrayList<>();
		int blockNumber = 3*(rowIdx/3)+colIdx/3;
		for(int missingNo: missingNos) {
			if(ct != ComponentType.ROW) {
				if(rows.get(rowIdx).contains(missingNo)) {
					continue;
				}
			}
			if(ct != ComponentType.COLUMN) {
				if(cols.get(colIdx).contains(missingNo)) {
					continue;
				}
			}
			if(ct != ComponentType.BLOCK) {
				if(blocks.get(blockNumber).contains(missingNo)) {
					continue;
				}
			}
			possibleNos.add(missingNo);
		}
		return possibleNos;
 	}
	
	/* hiddenSinglesFinder(List<Integer> possibleNos, int rowIdx, int colIdx, ComponentType ct)
	 * takes the co-ordinates of a cell, the list of possibleNos
	 * that can come in that cell and the ComponentType ct to which
	 * we are applying exhaustiveChecker.
	 * 
	 * This hiddenSinglesFinder basically tries to finds a number 
	 * from the list of possibleNos which can't appear in the 
	 * other cells of that component apart from the current cell 
	 * whose co-ordinates we have given to this method.
	 * 
	 * If it succeeds in finding such a number then it return true
	 * otherwise false.
	 */
	private boolean hiddenSinglesFinder(List<Integer> possibleNos, int rowIdx, int colIdx, ComponentType ct) {
		boolean cellFilled = false;
		if(ct == ComponentType.ROW) {
			for(int possibleNo: possibleNos) {
				int i;
				for(i=0; i<9; i++) {
					int blockNumber = 3*(rowIdx/3)+i/3;
					if(board[rowIdx][i] == '.' && i!=colIdx) {
						if(cols.get(i).contains(possibleNo)) {
							continue;
						}
						if(blocks.get(blockNumber).contains(possibleNo)) {
							continue;
						}
						break;
					}
				}
				if(i==9) {
					/* Here we are sure in the the other cells in 
					 * this row apart from the cell(rowIdx, colIdx)
					 * , this possibleNo can't appear.
					 * 
					 * So we insert this possibleNo in the 
					 * cell(rowIdx, colIdx).
					 */
					insertNumber(possibleNo, rowIdx, colIdx);
					cellFilled = true;
					break;
				}
			}
		}
		else if(ct == ComponentType.COLUMN) {
			for(int possibleNo: possibleNos) {
				int i;
				for(i=0; i<9; i++) {
					int blockNumber = 3*(i/3)+colIdx/3;
					if(board[i][colIdx] == '.' && i!=rowIdx) {
						if(rows.get(i).contains(possibleNo)) {
							continue;
						}
						if(blocks.get(blockNumber).contains(possibleNo)) {
							continue;
						}
						break;
					}
				}
				if(i==9) {
					/* Here we are sure in the the other cells in 
					 * this columns apart from the 
					 * cell(rowIdx, colIdx), this possibleNo 
					 * can't appear.
					 * 
					 * So we insert this possibleNo in the 
					 * cell(rowIdx, colIdx).
					 */
					insertNumber(possibleNo, rowIdx, colIdx);
					cellFilled = true;
					break;
				}
			}
		}
		else if(ct == ComponentType.BLOCK) {
			for(int possibleNo: possibleNos) {
				int i, j=-1, blockNumber = 3*(rowIdx/3)+colIdx/3;
				boolean flag = false;
				for(i=3*(blockNumber/3); i<3*(blockNumber/3)+3; i++) {
					for(j=3*(blockNumber%3); j<3*(blockNumber%3)+3; j++) {

						/* Here we check if the possibleNo can come in
						 * this cell(i,j) or not. If it can come then
						 * this possibleNo can't be a hidden single
						 * for the cell(rowIdx,colIdx).
						 * 
						 */
						if(board[i][j] == '.' && (i!=rowIdx || j!= colIdx)) {
							if(rows.get(i).contains(possibleNo)) {
								continue;
							}
							if(cols.get(j).contains(possibleNo)) {
								continue;
							}
							flag = true;
							break;
						}
					}
				}
				if(!flag) {
					/* Here we are sure in the the other cells in 
					 * this block apart from the 
					 * cell(rowIdx, colIdx), this possibleNo 
					 * can't appear.
					 * 
					 * So we insert this possibleNo in the 
					 * cell(rowIdx, colIdx).
					 */
					insertNumber(possibleNo, rowIdx, colIdx);
					cellFilled = true;
					break;
				}
			}
		}
		
		return cellFilled;
	}
	
	/* nakedSinglesFinder() finds empty cells having single 
	 * candidate and insert that candidate into that cell.
	 * 
	 * And then it also removes that candidate from the candidate 
	 * list of that cell's buddy cells.
	 * 
	 */
	private int nakedSinglesFinder() {
		int nakedSinglesCount = 0;
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(notes.get(i).get(j).size() == 1) {
					insertNumber(notes.get(i).get(j).get(0), i, j);
					nakedSinglesCount++;
				}
			}
		}
		return nakedSinglesCount;
	}
	
	/* removeCandidateFromBuddyCells(int num, int rowIdx, int colIdx, int excludeBuddyCells[][], ComponentType excludeCts[], ComponentType includeCts[])
	 * removes the number num from the candidate list of the buddy
	 * cells of the cell(rowIdx, colIdx) and returns the count of cells
	 * from whose candidate list num has been removed.
	 * 
	 * There are other parameters like:
	 * excludeBuddyCells[][] which contains the cell coordinates
	 * that are to be excluded while checking for buddy cells.
	 * 
	 * excludeCts[] contains the components that are to be 
	 * excluded from the buddy cells check.
	 * 
	 * includeCts[] contains the components that are to be 
	 * included in the buddy cells check.
	 * This will be useful while finding naked pairs.
	 * 
	 * Example: 
	 * excludeCts[] = {ComponentType.COLUMN, ComponentType.Block}
	 * means that buddy cells check for the cell(rowIdx, colIdx) 
	 * should occur for the cells in this row and outside this block
	 * and should not occur for the cells in this column. 
	 * Here 'this row' refers to the row corresponding to the 
	 * cell(rowIdx, colIdx) and same logic goes for the column and 
	 * block.
	 * 
	 * excludeCts[] = {ComponentType.COLUMN, ComponentType.Block}
	 * includeCts[] = {ComponentType.BLOCK_ROW}
	 * means that buddy cells check should occur for the cells in this 
	 * row and cells in this blockRow should not occur for the cells in 
	 * this column and this block apart from the cells in the blockRow.
	 */
	private int removeCandidateFromBuddyCells(int num, int rowIdx, int colIdx, int excludeBuddyCells[][], ComponentType excludeCts[], ComponentType includeCts[]) {
		int cellCount = 0, blockNumber = 3*(rowIdx/3)+colIdx/3;;
		boolean excludeRow = false, excludeColumn = false, 
				excludeBlock = false, excludeBlockRow = false,
				excludeBlockColumn = false, includeBlockRow = false,
				includeBlockColumn = false;
		
		if(excludeCts != null) {
			for(ComponentType ct: excludeCts) {
				if(ct.equals(ComponentType.ROW)) {
					excludeRow = true;
				}
				if(ct.equals(ComponentType.COLUMN)) {
					excludeColumn = true;
				}
				if(ct.equals(ComponentType.BLOCK)) {
					excludeBlock = true;
				}
				if(ct.equals(ComponentType.BLOCK_ROW)) {
					excludeBlockRow = true;
				}
				if(ct.equals(ComponentType.BLOCK_COLUMN)) {
					excludeBlockColumn = true;
				}
			}
		}
		
		if(includeCts != null) {
			for(ComponentType ct: includeCts) {
				if(ct.equals(ComponentType.BLOCK_ROW)) {
					includeBlockRow = true;
				}
				if(ct.equals(ComponentType.BLOCK_COLUMN)) {
					includeBlockColumn = true;
				}
			}
		}
		
		for(int i=0; i<9; i++) {
			/* If the buddy cells in the block whose block index is 
			 * same as the block index of the cell(rowIdx, colIdx), are
			 * to be saved then we need to prevent the code for removing
			 * the candidates of num from buddy cells in a row or column 
			 * from executing for certain values of i.
			 */
			
			if((notes.get(rowIdx).get(i).contains(num) && i!=colIdx) && !excludeRow) {
				if(excludeBlock && 3*(rowIdx/3)+i/3 == blockNumber) continue;
				
				if(excludeCell(rowIdx, i, excludeBuddyCells)) continue;
				
				notes.get(rowIdx).get(i).remove((Integer)num);
				cellCount++;
			}
			if((notes.get(i).get(colIdx).contains(num) && i!= rowIdx) && !excludeColumn) {
				if(excludeBlock && 3*(i/3)+colIdx/3 == blockNumber) continue;
				
				if(excludeCell(i, colIdx, excludeBuddyCells)) continue;
				
				notes.get(i).get(colIdx).remove((Integer)num);
				cellCount++;
			}
		}

		if(!excludeBlock || includeBlockRow || includeBlockColumn) {
			for(int i=3*(blockNumber/3); i<3*(blockNumber/3)+3; i++) {
				if(excludeBlockRow && i == rowIdx) continue;
				for(int j=3*(blockNumber%3); j<3*(blockNumber%3)+3; j++) {
					if(excludeBlockColumn && j == colIdx) continue;
					
					if(includeBlockRow && i!=rowIdx) break;
					
					if(includeBlockColumn && j!=colIdx) continue;
					
					if(notes.get(i).get(j).contains(num) && (i!=rowIdx || j!=colIdx)) {
						
						if(excludeCell(i, j, excludeBuddyCells)) continue;
						
						notes.get(i).get(j).remove((Integer)num);
						cellCount++;
					}
				}
			}
		}
		return cellCount;
	}
	
	/* excludeCell(int rowIdx, int colIdx, int excludeBuddyCells[][]) checks
	 * if the cell(rowIdx, colIdx) is there in excludeBuddyCells[][]. If it
	 * is then it returns true so no buddy cells check will occur for it.
	 * 
	 */
	private boolean excludeCell(int rowIdx, int colIdx, int excludeBuddyCells[][]) {
		if(excludeBuddyCells != null) {
			for(int cell[]: excludeBuddyCells) {
				if(cell[0] == rowIdx && cell[1] == colIdx) return true;
			}
		}
		return false;
	}
	
	/* lockedCandidatesOfType1Finder() will help in finding the type 1 locked 
	 * candidates and may not necessarily solve the cells but might help in 
	 * reducing the possibilities in cells.
	 * 
	 * A type 1 locked candidate is a yet to come digit in a block whose candidates
	 * in that block are confined to one row or column.
	 * In such case that digit can be removed from the candidate list of
	 * other cells in the same row or column that are outside this block.
	 */
	private int lockedCandidatesOfType1Finder() {
		int lockedCandidatesCount=0, possibilitiesEliminated = 0;
		
		for(int i=0; i<9; i++) {
			for(int possibleNo: getMissingNos(blocks.get(i))) {
				boolean cellFound = false, rowConfined = false, colConfined = false
						, possibleNoEliminate = false;;
				int rowIdx = -1, colIdx = -1;
				// Traverse through each cell in block i.
				for(int j=3*(i/3); j<3*(i/3)+3; j++) {
					for(int k=3*(i%3); k<3*(i%3)+3; k++) {
						if(board[j][k] == '.') {
							if(notes.get(j).get(k).contains(possibleNo)) {
								if(!cellFound) {
									/* Here we have found first empty cell whose candidate list 
									 * contains possibleNo.
									 */
									cellFound = true;
									rowIdx = j;
									colIdx = k;
								}
								else {
									if(rowConfined) {
										/* Here we have found another empty cell whose candidate list 
										 * contains possibleNo.
										 */
										if(j!=rowIdx) {
											possibleNoEliminate = true;
											break;
										}
									}
									else if(colConfined) {
										/* Here we have found another empty cell whose candidate list 
										 * contains possibleNo.
										 */
										if(k!=colIdx) {
											possibleNoEliminate = true;
											break;
										}
									}
									else {
										/* Here we have found second empty cell whose candidate list 
										 * contains possibleNo.
										 */
										if(j==rowIdx) rowConfined = true;
										else if(k==colIdx) colConfined = true;
										else {
											possibleNoEliminate = true;
											break;
										}
									}
									
								}
							}
						}
					}
					if(possibleNoEliminate) break;
				}
				if(!possibleNoEliminate) {
					/* Here we have found a possibleNo that is either row or
					 * column confined.
					 */
					if(rowConfined) {
						/* Here we remove this possibleNo from the candidate list of
						 * all the buddy cells in row(with index rowIdx) and outside this 
						 * block( with index i).
						 */
						ComponentType excludeComponents[] = {ComponentType.BLOCK, ComponentType.COLUMN};
						int cellCount = removeCandidateFromBuddyCells(possibleNo, rowIdx, colIdx, null, excludeComponents, null);
						log.info("Type 1 Locked Candidate: "+possibleNo+", at cell("+(rowIdx+1)+","+(colIdx+1)+") removed "+cellCount+" possibilities.");
						lockedCandidatesCount++;
						possibilitiesEliminated += cellCount;
//						printNotes();
					}
					if(colConfined) {
						/* Here we remove this possibleNo from the candidate list of
						 * all the buddy cells in column(with index colIdx) and outside this 
						 * block( with index i).
						 */
						ComponentType excludeComponents[] = {ComponentType.BLOCK, ComponentType.ROW};
						int cellCount = removeCandidateFromBuddyCells(possibleNo, rowIdx, colIdx, null, excludeComponents, null);
						log.info("Type 1 Locked Candidate: "+possibleNo+", at cell("+(rowIdx+1)+","+(colIdx+1)+") removed "+cellCount+" possibilities.");
						lockedCandidatesCount++;
						possibilitiesEliminated += cellCount;
//						printNotes();
					}
				}
			}
		}
		
		log.info("lockedCandidatesCount: "+lockedCandidatesCount);
		return possibilitiesEliminated;
	}
	
	/* lockedCandidatesOfType2Finder() will help in finding the type 2 locked 
	 * candidates and may not necessarily solve the cells but might help in 
	 * reducing the possibilities in cells.
	 * 
	 * A type 2 locked candidate is a yet to come digit in a row or column 
	 * whose candidates in that row/column are confined to a block.
	 * In such case that digit can be removed from the candidate list of
	 * other cells in that block.
	 */
	private int lockedCandidatesOfType2Finder() {
		int lockedCandidatesCount=0, possibilitiesEliminated = 0;
		
		for(int i=0; i<9; i++) {
			for(int possibleNo: getMissingNos(rows.get(i))) {
				boolean cellFound = false, blockConfined = false, possibleNoEliminate = false;
				int rowIdx = -1, colIdx = -1, blockIdx = -1;
				// Traverse through each cell in row i.
				for(int j=0; j<9; j++) {
					if(board[i][j] == '.') {
						if(notes.get(i).get(j).contains(possibleNo)) {
							if(!cellFound) {
								/* Here we have found first empty cell whose candidate list 
								 * contains possibleNo.
								 */
								cellFound = true;
								rowIdx = i;
								colIdx = j;
								blockIdx = 3*(rowIdx/3)+colIdx/3;
								blockConfined = true;
							}
							else {
								if(blockConfined) {
									/* Here we have found another empty cell whose candidate list 
									 * contains possibleNo.
									 */
									if(3*(i/3)+j/3 != blockIdx) {
										possibleNoEliminate = true;
										break;
									}
								}
							}
						}
					}
				}
				if(!possibleNoEliminate) {
					/* Here we have found a possibleNo that is block confined and we remove this 
					 * possibleNo from the candidate list of all the buddy cells in 
					 * block(with index blockIdx) and outside the row(with index rowIdx).
					 */
					ComponentType excludeComponents[] = {ComponentType.ROW, ComponentType.COLUMN, ComponentType.BLOCK_ROW};
					int cellCount = removeCandidateFromBuddyCells(possibleNo, rowIdx, colIdx, null, excludeComponents, null);
					log.info("Type 2 Locked Candidate: "+possibleNo+", at cell("+(rowIdx+1)+","+(colIdx+1)+") removed "+cellCount+" possibilities.");
					lockedCandidatesCount++;
					possibilitiesEliminated += cellCount;
//					printNotes();
				}
			}
			
			for(int possibleNo: getMissingNos(cols.get(i))) {
				boolean cellFound = false, blockConfined = false, possibleNoEliminate = false;
				int rowIdx = -1, colIdx = -1, blockIdx = -1;
				// Traverse through each cell in row i.
				for(int j=0; j<9; j++) {
					if(board[j][i] == '.') {
						if(notes.get(j).get(i).contains(possibleNo)) {
							if(!cellFound) {
								/* Here we have found first empty cell whose candidate list 
								 * contains possibleNo.
								 */
								cellFound = true;
								rowIdx = j;
								colIdx = i;
								blockIdx = 3*(rowIdx/3)+colIdx/3;
								blockConfined = true;
							}
							else {
								if(blockConfined) {
									/* Here we have found another empty cell whose candidate list 
									 * contains possibleNo.
									 */
									if(3*(j/3)+i/3 != blockIdx) {
										possibleNoEliminate = true;
										break;
									}
								}
							}
						}
					}
				}
				if(!possibleNoEliminate) {
					/* Here we have found a possibleNo that is block confined and we remove this 
					 * possibleNo from the candidate list of all the buddy cells in 
					 * block(with index blockIdx) and outside the row(with index rowIdx).
					 */
					ComponentType excludeComponents[] = {ComponentType.ROW, ComponentType.COLUMN, ComponentType.BLOCK_COLUMN};
					int cellCount = removeCandidateFromBuddyCells(possibleNo, rowIdx, colIdx, null, excludeComponents, null);
					log.info("Type 2 Locked Candidate: "+possibleNo+", at cell("+(rowIdx+1)+","+(colIdx+1)+") removed "+cellCount+" possibilities.");
					lockedCandidatesCount++;
					possibilitiesEliminated += cellCount;
//					printNotes();
				}
			}
		}
		log.info("lockedCandidatesCount: "+lockedCandidatesCount);
		return possibilitiesEliminated;
	}
	
	/* nakedPairsFinder helps to find naked pairs in the board
	 * and may not necessarily solve the cells but might help in 
	 * reducing the possibilities in cells.
	 * 
	 */
	private int nakedPairsFinder() {
		int nakedPairsCount = 0, possibilitiesEliminated = 0;
		
		for(int i=0; i<9; i++) {
			for(int j=0; j<8; j++) {
				// Here we are finding naked pairs in a row
				if(board[i][j] == '.' && notes.get(i).get(j).size() == 2) {
					int nakedPair[] = {notes.get(i).get(j).get(0), notes.get(i).get(j).get(1)};
					
					for(int k=j+1; k<9; k++) {
						if(board[i][k] == '.' && notes.get(i).get(k).size() == 2) {
							boolean mismatchOccurred = false;
							for(int num: nakedPair) {
								if(!notes.get(i).get(k).contains(num)) {
									mismatchOccurred = true;
									break;
								}
							}
							if(!mismatchOccurred) {
								for(int l=0; l<2; l++) {
									int  excludeCells[][] = {{i, k}}, cellCount = 0;
									if(j/3 == k/3) {
										// Both cells are in same block
										ComponentType excludeComponents[] = {ComponentType.COLUMN};
										cellCount = removeCandidateFromBuddyCells(nakedPair[l], i, j, excludeCells, excludeComponents, null);
									}
									else {
										// Both cells are in different block
										ComponentType excludeComponents[] = {ComponentType.COLUMN, ComponentType.BLOCK},
												includeComponents[] = {ComponentType.BLOCK_ROW};
										cellCount = removeCandidateFromBuddyCells(nakedPair[l], i, j, excludeCells, excludeComponents, includeComponents);
									}
									 
									nakedPairsCount++;
									possibilitiesEliminated += cellCount;
									log.info("Naked Pair in row,  element: "+nakedPair[l]+", at cell("+(i+1)+","+(j+1)+") removed "+cellCount+" possibilities.");
								}
							}
						}
					}
				}
				// Here we are finding naked pairs in a column
				if(board[j][i] == '.' && notes.get(j).get(i).size() == 2) {
					int nakedPair[] = {notes.get(j).get(i).get(0), notes.get(j).get(i).get(1)};
					
					for(int k=j+1; k<9; k++) {
						if(board[k][i] == '.' && notes.get(k).get(i).size() == 2) {
							boolean mismatchOccurred = false;
							for(int num: nakedPair) {
								if(!notes.get(k).get(i).contains(num)) {
									mismatchOccurred = true;
									break;
								}
							}
							if(!mismatchOccurred) {
								for(int l=0; l<2; l++) {
									int  excludeCells[][] = {{k, i}}, cellCount = 0;
									if(3*(j/3) == 3*(k/3)) {
										// Both cells are in same block
										ComponentType excludeComponents[] = {ComponentType.ROW};
										cellCount = removeCandidateFromBuddyCells(nakedPair[l], j, i, excludeCells, excludeComponents, null);
									}
									else {
										// Both cells are in different block
										ComponentType excludeComponents[] = {ComponentType.ROW, ComponentType.BLOCK},
												includeComponents[] = {ComponentType.BLOCK_COLUMN};
										cellCount = removeCandidateFromBuddyCells(nakedPair[l], j, i, excludeCells, excludeComponents, includeComponents);
									}
									 
									nakedPairsCount++;
									possibilitiesEliminated += cellCount;
									log.info("Naked Pair in column, element: "+nakedPair[l]+", at cell("+(j+1)+","+(i+1)+") removed "+cellCount+" possibilities.");
								}
							}
						}
					}
				}
			}
			// Here we are finding naked pairs in a block
			for(int j=3*(i/3); j<3*(i/3)+3; j++) {
				for(int k=3*(i%3); k<3*(i%3)+3; k++) {
					if(board[j][k] == '.' && notes.get(j).get(k).size()==2) {
						int nakedPair[] = {notes.get(j).get(k).get(0), notes.get(j).get(k).get(1)};
						
						for(int l=3*(i/3); l<3*(i/3)+3; l++) {
							for(int m=k+1; m<3*(i%3)+3; m++) {
								/* Here the condition (j!=l && k!=m) looks for those cells which don't
								 * lie in the same row or column as that of the cell(j,k) because naked
								 * pairs check for such cells have already been covered before while
								 * finding naked pairs in a block or a column.
								 */
								
								if(board[l][m] == '.' && notes.get(l).get(m).size()==2 && (j!=l && k!=m)) {
									boolean mismatchOccurred = false;
									for(int num: nakedPair) {
										if(!notes.get(l).get(m).contains(num)) {
											mismatchOccurred = true;
											break;
										}
									}
									if(!mismatchOccurred) {
										for(int n=0; n<2; n++) {
											int  excludeCells[][] = {{l, m}}, cellCount = 0;
											
											// Both cells are in same block
											ComponentType excludeComponents[] = {ComponentType.ROW, ComponentType.COLUMN},
													includeComponents[] = {ComponentType.BLOCK_ROW, ComponentType.BLOCK_COLUMN};
											cellCount = removeCandidateFromBuddyCells(nakedPair[n], j, k, excludeCells, excludeComponents, includeComponents);
											
											nakedPairsCount++;
											possibilitiesEliminated += cellCount;
											log.info("Naked Pair in block, element: "+nakedPair[n]+", at cell("+(j+1)+","+(k+1)+") removed "+cellCount+" possibilities.");
										}
									}
								}
							}
						}
					}
				}
			}
		}
		log.info("nakedPairsCount: "+nakedPairsCount);
		return possibilitiesEliminated;
	}
	
	private void insertNumber(int num, int rowIdx, int colIdx) {
		board[rowIdx][colIdx] = (char)(num+48);
		
		log.info("Inserted "+num+" at cell("+(rowIdx+1)+", "+(colIdx+1)+")");
		
		// Update the rows, cols, and blocks
		cols.get(colIdx).add(num);
		rows.get(rowIdx).add(num);
		int blockNumber = 3*(rowIdx/3)+colIdx/3;
		blocks.get(blockNumber).add(num);
		
		notes.get(rowIdx).get(colIdx).clear();
		removeCandidateFromBuddyCells(num, rowIdx, colIdx, null, null, null);
		emptyCells--;
		
		printBoard(board);
		printNotes();
	}
	
	private void print2D(ArrayList<ArrayList<Integer>> lists) {
		for(ArrayList<Integer> al: lists) {
			log.info(al);
		}
	}
	
	private void printNotes() {
        log.info("Printing notes:");
		for(ArrayList<ArrayList<Integer>> rowNotes: notes) {
			log.info(rowNotes);
		}
	}
	
	private void printBoard(char board[][]) {
		StringBuilder sb = new StringBuilder(144);
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(board[i][j]!='.')
					sb.append(board[i][j]);
				else
					sb.append("-");
				if(j!=8)
					sb.append("  ");
			}
			log.info(sb.toString());
			sb.delete(0, sb.length());
		}
	
	}
	
	private char[][] getBoard(String str) {
		char charArray[] = str.toCharArray(), board[][] = new char[9][9];
		int pos = 0;
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				if(charArray[pos] == ' ') {
					j--;
					pos++;
					continue;
				}
				board[i][j] = charArray[pos];
				pos++;
			}
		}
		return board;
	}
	
	public static void main(String[] args) {
		String str =  ". 6 . . . . 3 . ."
			     + ". 7 . . 1 . . . ."
			     + "3 . 1 . . 2 . 4 ."
			     + ". 1 . . . . . . ."
			     + ". . . . . 4 . . 8"
			     + "9 . 5 . 2 . 7 . ."
			     + ". . . 9 . . . 7 ."
			     + "2 . 3 . 5 . 9 . ."
			     + "6 . . . . . . . .";
		
//		char board[][]  =  {
//				 {'.','3','4','.','7','9','.','.','.'},
//				 {'5','.','.','.','8','.','1','.','.'},
//				 {'.','.','.','6','5','4','.','7','3'},
//				 {'8','1','.','7','.','.','.','.','2'},
//				 {'9','.','.','4','.','3','.','.','8'},
//				 {'3','.','.','.','.','1','.','5','9'},
//				 {'1','6','.','5','3','7','.','.','.'},
//				 {'.','.','9','.','1','.','.','.','7'},
//				 {'.','.','.','9','4','.','5','8','.'}
//		 	};
		
		SudokuSolver obj = new SudokuSolver();
		char board[][] = obj.getBoard(str);
		obj.printBoard(board);
		obj.solveSudoku(board);
	}

}
/*
 * Test input frpm LeetCode:
 * Input: board = 
 * {
	 * {'5','3','.','.','7','.','.','.','.'},
	 * {'6','.','.','1','9','5','.','.','.'},
	 * {'.','9','8','.','.','.','.','6','.'},
	 * {'8','.','.','.','6','.','.','.','3'},
	 * {'4','.','.','8','.','3','.','.','1'},
	 * {'7','.','.','.','2','.','.','.','6'},
	 * {'.','6','.','.','.','.','2','8','.'},
	 * {'.','.','.','4','1','9','.','.','5'},
	 * {'.','.','.','.','8','.','.','7','9'}
 * }
 * Output: {{'5','3','4','6','7','8','9','1','2'},{'6','7','2','1','9','5','3','4','8'},{'1','9','8','3','4','2','5','6','7'},{'8','5','9','7','6','1','4','2','3'},{'4','2','6','8','5','3','7','9','1'},{'7','1','3','9','2','4','8','5','6'},{'9','6','1','5','3','7','2','8','4'},{'2','8','7','4','1','9','6','3','5'},{'3','4','5','2','8','6','1','7','9'}}
 * 
 * Test input from online sudoku:
 * https://www.websudoku.com/?select=1&level=1
 * 8,823,454,048 Easy
 * {
	 * {'7','.','.','.','9','.','2','.','.'},
	 * {'.','2','.','.','.','.','.','5','.'},
	 * {'.','9','.','.','.','.','.','1','7'},
	 * {'3','.','2','9','.','4','7','6','.'},
	 * {'.','1','.','7','.','6','.','9','.'},
	 * {'.','7','6','8','.','3','5','.','1'},
	 * {'4','6','.','.','.','.','.','7','.'},
	 * {'.','3','.','.','.','.','.','2','.'},
	 * {'.','.','1','.','6','.','.','.','4'}
 * }
 * 
 * 4,887,605,622 Easy
 * {
	 * {'.','3','4','.','7','9','.','.','.'},
	 * {'5','.','.','.','8','.','1','.','.'},
	 * {'.','.','.','6','5','4','.','7','3'},
	 * {'8','1','.','7','.','.','.','.','2'},
	 * {'9','.','.','4','.','3','.','.','8'},
	 * {'3','.','.','.','.','1','.','5','9'},
	 * {'1','6','.','5','3','7','.','.','.'},
	 * {'.','.','9','.','1','.','.','.','7'},
	 * {'.','.','.','9','4','.','5','8','.'}
 * }
 * 
 * 7,549,769,532 Medium
 * str = 
				  "3 5 . 9 . . 8 4 ."
				+ ". 4 9 3 2 . . . ."
				+ ". . 6 5 . . . . ."
				+ ". . 4 . . . . 8 5"
				+ ". . . . . . . . ."
				+ "5 9 . . . . 4 . ."
				+ ". . . . . 6 9 . ."
				+ ". . . . 4 8 5 1 ."
				+ ". 7 1 . . 9 . 3 8";
	
	5,286,543,392 Medium
	
str = ". . . . . . . . ."
	+ ". 2 8 1 4 . . 6 5"
	+ "6 7 1 8 . . . 3 ."
	+ ". . . . 8 9 . . ."
	+ "3 6 . . . . . 7 2"
	+ " . . . 6 3 . . . ."
	+ ". 1 . . . 5 4 9 6"
	+ "7 9 . . 6 8 3 5 ."
	+ ". . . . . . . . ."
	
	Medium 9,723,030,228
	str =   "7 1 . . 3 8 . 4 ."
	      + ". . 6 . 4 5 . . 1"
	      + "5 . . 7 . . . . ."
	      + ". 6 . . . . . 5 ."
	      + "3 9 . . 2 . . 6 7"
	      + ". 2 . . . . . 3 ."
	      + ". . . . . 2 . . 9"
	      + "9 . . 3 6 . 8 . ."
	      + ". 4 . 8 7 . . 2 5"
	
	Hard 4,332,336,724
	str =   ". . . . 5 6 3 . ."
	      + ". . . 7 . . 5 4 ."
	      + ". . . 9 8 . 1 . 7"
	      + ". 7 3 . . 5 . . ."
	      + ". . . . 4 . . . ."
	      + ". . . 6 . . 7 8 ."
	      + "7 . 5 . 1 9 . . ."
	      + ". 9 4 . . 3 . . ."
	      + ". . 2 5 7 . . . ."
	      
	Hard
	str =   ". . 1 . . 5 . 2 ."
	      + ". 4 . . 2 6 . 7 ."
	      + ". . 3 . . . . 6 ."
	      + "7 . . . . 9 . 5 ."
	      + ". . . . . . . . ."
	      + ". . 4 . . 2 . . 1"
	      + ". . 9 . . 4 . . 8"
	      + ". . . 6 3 . . . ."
	      + ". 3 . 9 . . . . 5"
	      
	Hard
	str =   "6 . . . . . . . ."
		  + ". . 7 5 . 9 . 1 ."
		  + ". . . . 7 2 . . 4"
		  + ". 9 6 . 5 . . . 2"
		  + "2 . . 3 . . . . ."
		  + "7 . . . . . 1 9 6"
		  + ". . 5 7 . . 4 . ."
		  + ". . . . . . 3 7 8"
		  + ". . . . 2 . . . ."
		  
	Evil
	str =  ". 6 . . . . 3 . ."
	     + ". 7 . . 1 . . . ."
	     + "3 . 1 . . 2 . 4 ."
	     + ". 1 . . . . . . ."
	     + ". . . . . 4 . . 8"
	     + "9 . 5 . 2 . 7 . ."
	     + ". . . 9 . . . 7 ."
	     + "2 . 3 . 5 . 9 . ."
	     + "6 . . . . . . . ."
	     
	      
 * 
 */
