package minesweeper;

import java.util.Scanner;

public class Board {
	int DIMENSION;
	String level = "none";
	int mines;
	int [][] mine_locations;
	
	Cell [][] secretBoard;
	Cell [][] playerBoard;
	
	Scanner input = new Scanner(System.in);
	final char[] letters = {
		    'A','B','C','D','E','F','G','H','I','J','K','L','M',
		    'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
		};
	
	public Board() {
		this.setLevel();
		//this.level = "easy";
		this.setDifficulty(level);
		this.setMineLocations();
		this.setSecretBoard();
		this.setPlayerBoard();
		
	}
	

	void setLevel() {
		while(!(level.equals("easy")||level.equals("medium")||level.equals("hard"))){
			System.out.println("What level would you like (easy (9 mines), medium (22 mines), or hard (45 mines))");
			level = input.nextLine().toLowerCase();
		}
	}
	
	void setDifficulty(String level) {
		if (level.equals("easy")) {
			DIMENSION = 7;
			mines = 9;
		}
		else if (level.equals("medium")) {
			DIMENSION = 10;//yo
			mines = 22;
		}
		else if (level.equals("hard")) {
			DIMENSION = 12;
			mines = 45;
		}
	}
	
	

	void setMineLocations() {
		mine_locations = new int [mines][2];
		for (int row = 0; row<mine_locations.length; row++) {
			mine_locations[row][0] = (int)(Math.random() * DIMENSION);
			mine_locations[row][1] = (int)(Math.random() * DIMENSION-1);
		}
	}
	
	int [][] getMineLocations() {
		return mine_locations;
	}
	
	int getMines() {
		return mines;
	}
	
	void setMines() {
		secretBoard = new Cell[DIMENSION][DIMENSION];
		for (int row = 0; row<DIMENSION; row++) {
			for (int col = 0; col<DIMENSION; col++) {
				secretBoard[row][col] = new Cell(0);
				for (int i = 0; i<mine_locations.length; i++) {
					if (row == mine_locations[i][0] && col == mine_locations[i][1]) {
						secretBoard[row][col] = new Cell('#');
					}
				}
			}
		}
	}
	
	void setNums() {
		for (int row = 0; row<DIMENSION; row++) {
			for (int col = 0; col<DIMENSION; col++) {
				if (secretBoard[row][col].isMine()) {
	                continue;
	            }
				int num=0;
				for (int r = -1; r<=1; r++) {
					for (int c = -1; c<=1; c++) {
						if (c == 0 && r == 0) {
							continue;
						}
						
						if (col+c<0||col+c>=DIMENSION||row+r<0||row+r>=DIMENSION) {
							continue;
						}
						
						if (secretBoard[row+r][col+c].isMine()) {
							num++;
						}
					}
				}
				secretBoard[row][col].setNum(num);
			}
		}
	}
	
	void setSecretBoard() {
		setMines();
		setNums();
		// Mark all cells as open so they display their values
		for (int row = 0; row < DIMENSION; row++) {
			for (int col = 0; col < DIMENSION; col++) {
				secretBoard[row][col].isOpen = true;
			}
		}
	}
	
	void setPlayerBoard() {
		playerBoard = new Cell [DIMENSION][DIMENSION];
		for (int row = 0; row< DIMENSION; row++) {
			for (int col = 0; col<DIMENSION; col++) {
				playerBoard[row][col] = new Cell();
			}
		}
	}

	
	public Cell[][] getSecretBoard(){
		return secretBoard;
	}
	
	public Cell[][] getPlayerBoard(){
		return playerBoard;
	}
	
	public void printBoard(Cell[][] board) {
		int i = 0;
		System.out.print(" ");
		for (int k = 0; k<DIMENSION-1; k++) {
			System.out.print("   " + letters[k]);
		}
		System.out.println();
		for (int row = 0; row<DIMENSION*2; row++) {
			if (row%2!=0) {
				if (i<=9) {
					System.out.print(i + " ");
				}
				else {
					System.out.print(i);
				}
				i++;
				for (int col = 0; col<DIMENSION-1; col++) {
					System.out.print((board[row/2][col]).toString());
				}
				System.out.println("|");
			}
			else {
				System.out.print("  ");
				for (int k = 0; k<board.length-1; k++) {
					System.out.print("+---");
				}
				System.out.println("+");
			}
		}
		System.out.print("  ");
		for (int k = 0; k<board.length-1; k++) {
			System.out.print("+---");
		}
		System.out.println("+");
		
	}

}
