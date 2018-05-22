
public class ViewedMap {
	
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
		int midCol = currentView[0].length/2;
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
	
	public void updateView(char dire, char action,char viewedMap[][]) {
		
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
