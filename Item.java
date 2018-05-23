
public class Item {
	private int row;
	private int col;
	private String name;
	
	public Item() {
		
	}
	public Item(int row, int col, String name) {
		this.name = name;
		this.row = row;
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
