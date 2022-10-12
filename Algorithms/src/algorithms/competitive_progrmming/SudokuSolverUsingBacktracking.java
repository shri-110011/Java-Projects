package algorithms.competitive_progrmming;

import java.util.Arrays;
import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SudokuSolverUsingBacktracking {
	
	static final Logger log = LogManager.getLogger(SudokuSolverUsingBacktracking.class.getName());
	
	public boolean solveSudoku(char[][] board) {
		return startSolvingSudoku(board, 0, 0);
	}
	
	/* startSolvingSudoku(char[][] board, int rowIdx, int colIdx)
	 * takes the cell index of the top left cell i.e. cell(0,0)
	 * from where it will start solving the sudoku.
	 */
	private boolean startSolvingSudoku(char[][] board, int rowIdx, int colIdx) {
		
		// Base case
		if(rowIdx==9) {
			return true;
		}
		else if(colIdx == 9) {
			rowIdx++;
			colIdx %= colIdx/9;
			if(rowIdx==9) {
				return true;
			}
		}
		// Try to find a empty cell in the board
		while(board[rowIdx][colIdx]!='.') {
			colIdx++;
			if(colIdx==9) {
				colIdx %= colIdx/9;
				rowIdx++;
			}
			if(rowIdx==9) {
				return true;
			}
		}
		
		// Here we have found an empty cell
		
		/* Step 1: Find the possible numbers for this empty cell
		 * and store it in an array.
		 * 
		 * Step2: If the list of possible numbers for this empty 
		 * cell is empty then return false because we might 
		 * have placed a wrong number in the previous empty
		 * cell. 
		 */
		
		
		/* Step 3: Select a number from this array and place it 
		 * in this cell and also keep a track of this 
		 * number's position in the array.
		 */
		
		
		/* Step 4: Call the solveSudoku(char[][] board, int rowIdx, int colIdx+1) 
		 * recursively and if it returns false then:
		 * 
		 * Remove the number placed in the cell(rowIdx,colIdx).
		 * 
		 * Repeat steps 3-4 until the possible numbers array for 
		 * this empty cell becomes empty.
		 * 
		 * Step 5: If the last recursive call returns:
		 * false then return false
		 * true then return true
		 */
		
		int possibleNos[] = getPossibleNos(board, rowIdx, colIdx);
		
		if(possibleNos.length == 0) {
			return false;
		}
		
		int pos = 0;
		
		boolean correctValueFound = false;
		while(pos<possibleNos.length) {
			board[rowIdx][colIdx] = (char)(possibleNos[pos]+48);
			correctValueFound = startSolvingSudoku(board, rowIdx, colIdx+1);
			if(!correctValueFound) {
				board[rowIdx][colIdx] = '.';
				pos++;
			}
			else
				break;
		}
		return correctValueFound;
	}
	
	private int[] getPossibleNos(char board[][], int rowIdx, int colIdx) {
		HashSet<Integer> hs = new HashSet<>();
		
		for(int i=0; i<9; i++) {
			if(board[rowIdx][i] != '.') {
				hs.add(board[rowIdx][i]-48);
			}
			if(board[i][colIdx] != '.') {
				hs.add(board[i][colIdx]-48);
			}
		}
		int blockIdx = 3*(rowIdx/3)+colIdx/3;
		for(int i=3*(blockIdx/3); i<3*(blockIdx/3)+3; i++) {
			for(int j=3*(blockIdx%3); j<3*(blockIdx%3)+3; j++) {
				if(board[i][j] != '.') {
					hs.add(board[i][j]-48);
				}
			}
		}
		int impossibleNos[] = hs.stream().mapToInt(Integer::intValue).toArray(),
				possibleNos[] = new int[9-impossibleNos.length];
		
		Arrays.sort(impossibleNos);
		
		int startNum = 1, pos=0;
		
		for(int i=0; i<impossibleNos.length; i++) {
			for(int j=startNum; j<impossibleNos[i]; j++) {
				possibleNos[pos] = j;
				pos++;
			}
			startNum = impossibleNos[i]+1;
		}
		while(startNum<=9) {
			possibleNos[pos] = startNum;
			startNum++;
			pos++;
		}
		
		return possibleNos;
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
		SudokuSolverUsingBacktracking obj = new SudokuSolverUsingBacktracking();
//		char board[][] = obj.getBoard(str);
		char board[][] = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
		obj.printBoard(board);
		log.info("\n\n");
		obj.solveSudoku(board);
		log.info("Solved sudoku:");
		obj.printBoard(board);
			      
	}
}