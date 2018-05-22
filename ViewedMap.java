
public class ViewedMap {
	  final static int EAST   = 0;
	  final static int NORTH  = 1;
	  final static int WEST   = 2;
	  final static int SOUTH  = 3;
	
//	private char inView[][];
//	private char currentView[][];
//	private char action;
	
	
//	public ViewedMap(char inView[][], char currentView[][],char action) {
//		this.inView = inView;
//		this.currentView = currentView;
//		this.action = action;
//	}
//	
//	public ViewedMap(char inView[][], char currentView[][]) {
//		this.inView = inView;
//		this.currentView = currentView;
//	}
	public ViewedMap() {
		
	}
	
	public void initialMap(char inView[][], char currentView[][]) {
		int midRow = currentView.length/2;
		int midCol = (currentView.length)/2;
		int startRow = midRow-2;
		int startCol = midCol-2;
		for(int i = 0; i < 5; i++) {
			for(int i2 = 0; i2 < 5; i2++) {
				if(i2 == 2 && i == 2) {
					currentView[startRow][startCol++] = '^';
				} else {
					currentView[startRow][startCol++] = inView[i][i2];
				}
			}
			startCol = midCol-2;
			startRow++;
		}
	}
	
	public void printViewedMap(char viewedMap[][]) {
		for(int i = 0; i < viewedMap.length; i++) {
			for(int i2 = 0; i2 < viewedMap.length; i2++) {
				if(viewedMap[i][i2] != 0) {
					System.out.print(viewedMap[i][i2]);
				}
			}
			System.out.println();
		}
	}
	
	public void updateView(int dire, char action,char viewedMap[][], char inView[][],int currentRow, int currentCol) {
		if(action == 'R' || action == 'r') {
			if(viewedMap[currentRow][currentCol] == '^') {
				viewedMap[currentRow][currentCol] = '>';
			} else if(viewedMap[currentRow][currentCol] == '>') {
				viewedMap[currentRow][currentCol] = 'v';
			} else if(viewedMap[currentRow][currentCol] == 'v') {
				viewedMap[currentRow][currentCol] = '<';
			} else if(viewedMap[currentRow][currentCol] == '<') {
				viewedMap[currentRow][currentCol] = '^';
			}
			return;
		} else if(action == 'L' || action == 'l') {
			if(viewedMap[currentRow][currentCol] == '^') {
				viewedMap[currentRow][currentCol] = '<';
			} else if(viewedMap[currentRow][currentCol] == '>') {
				viewedMap[currentRow][currentCol] = '^';
			} else if(viewedMap[currentRow][currentCol] == 'v') {
				viewedMap[currentRow][currentCol] = '>';
			} else if(viewedMap[currentRow][currentCol] == '<') {
				viewedMap[currentRow][currentCol] = 'v';
			}
			return;
		} else if (action == 'F' || action == 'f') {
			if(dire == NORTH) {
				int startRow = currentRow-2;
				int startCol = currentCol-2;
				for(int i = 0; i < 5; i++) {
					viewedMap[startRow][startCol++] = inView[0][i];
				}
			}
		} else if (action == 0) {
			return;
		}
	}
	
//	public char getAction(char action) {
//		char 
//		
//		return action;
//	}
//	
//	public 
//	
	
	
	
}
