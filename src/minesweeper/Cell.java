package minesweeper;

public class Cell{
	int num;
	char flag;
	char empty;
	int [] location = new int[2];
	boolean isOpen;
	boolean isFlagged;
	boolean hasMine;
	boolean isEmpty;
	int num_mines;
	Cell() {
		this.empty = ' ';
		this.isEmpty = true;
	}
	
	Cell(int num){
		//this.setLocation();
		this.num = num;
		this.hasMine = false;
	}//hi
	
	Cell(char a){
		this.flag = '#';
		this.hasMine = true;
	}
	
	boolean isMine() {
		return this.hasMine;
	}
	
	void setNum(int val) {
		this.num = val;
	}
	
	int getNum() {
		return num;
	}
	
	char getChar() {
		return flag;
	}

	public String toString() {
		if (!isOpen) {
        	return "|   ";
    	}

		if (hasMine) {
			return "| "+flag+" ";
		}
		else if (isEmpty) {
			return "| "+empty+" ";
		}
		else {
			return "| "+num+" ";
		}
		
	}

	void revealNumber(int val) {
		this.num = val;
		this.isOpen = true;
		this.isEmpty = false;
		this.hasMine = false;
	}
}
