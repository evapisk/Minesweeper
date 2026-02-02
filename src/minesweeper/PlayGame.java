package minesweeper;

import java.util.Scanner;

public class PlayGame{
	
	boolean playing = true;
	Scanner input = new Scanner(System.in);
	String letterString = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String cellGuessed;
	Board initialBoard = new Board(); 
	Cell [][] secretBoard = initialBoard.getSecretBoard();
	Cell [][] playerBoard = initialBoard.getPlayerBoard();
	int safeCells = playerBoard.length * playerBoard.length - initialBoard.getMines();
	int cellsRevealed = 0;
	
	
	void playGame() {
		
		initialBoard.printBoard(secretBoard);
	
		while (playing) {
			
			initialBoard.printBoard(playerBoard);
			String action = askAction();
			
			if (action.equals("FLAG")) {
				
				String cellGuessed = takeInput();
				

				int row = (int)(cellGuessed.charAt(1)-48);
				int col = (int)(cellGuessed.charAt(0)-65);
				
				playerBoard[row][col] = new Cell('+');
			
			}
			
			else if (action.equals("GUESS")) {
				
				String cellGuessed = takeInput();
				
				int row = (int)(cellGuessed.charAt(1)-48);
				int col = (int)(cellGuessed.charAt(0)-65);
				
				if(checkIfMine(cellGuessed)) {
					playing = false;
					System.out.println("Game Over! You Lose!");
				} else {
					floodReveal(row, col);
					checkIfWon();
				}
				
			}
			
			else {
				
				System.out.println("Enter either flag or guess");
				
			}
			
		}
		
	}
		
	String askAction() {
			
		System.out.println("Would you like to place a flag or take a guess?");
		String action = (input.nextLine().toUpperCase());
		return action;
	
	}
	
	String takeInput() {
		
		System.out.println("In what cell?");
		cellGuessed = (input.nextLine().toUpperCase());
		return cellGuessed;
	}
	
	boolean checkIfMine(String cellGuessed) {
		
		int row = (int)(cellGuessed.charAt(1)-48);
		int col = (int)(cellGuessed.charAt(0)-65);
		
		if (secretBoard[row][col].isMine()) {
			
			System.out.println("IT'S A MINE");
			return true;
		
		}
			
		else {
		
			return false;
			
		}
		
	}
	
	public void checkIfWon() {
		
		if(safeCells == cellsRevealed) {
			
			System.out.println("You won!");
			
			playing = false;
		}
		
	}
	
	// boolean zeroExists = false;
	
	public void floodReveal(int row, int col) {

		// checking boundaries

		if (row < 0 || row >= playerBoard.length || col < 0 || col >= playerBoard.length) {
			return;
		}

		if (playerBoard[row][col].isOpen) {
			return;
		}

		if (playerBoard[row][col].isFlagged) {
			return;
		}

		if (secretBoard[row][col].isMine()) {
			return;
		}

		int secretNum = secretBoard[row][col].getNum();
		playerBoard[row][col].revealNumber(secretNum);
		cellsRevealed++;
		
		if (secretNum != 0) {
			return;
		}

		for (int rOffset = -1; rOffset <= 1; rOffset++) {
			for (int cOffset = -1; cOffset <= 1; cOffset++) {
				if (rOffset == 0 && cOffset == 0) continue;
				floodReveal(row + rOffset, col + cOffset);
			}
		}
}

	
}