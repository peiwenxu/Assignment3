import java.util.LinkedList;

public class PointState {
	private int curRow;
	private int curCol;
	private int direction;
	private LinkedList<Character> actions;
	private double gCost;
	private double hCost;
	private double fCost;
	
	PointState(){
		
	}
	PointState(int curRow, int curCol, LinkedList<Character> actions) {
		this.curRow = curRow;
		this.curCol = curCol;
		this.actions = actions;
	}
	PointState(int curRow, int curCol) {
		this.curRow = curRow;
		this.curCol = curCol;
	}
	public int getCurRow() {
		return curRow;
	}
	public void setCurRow(int curRow) {
		this.curRow = curRow;
	}
	public int getCurCol() {
		return curCol;
	}
	public void setCurCol(int curCol) {
		this.curCol = curCol;
	}
	public LinkedList<Character> getActions() {
		return actions;
	}
	public void setActions(LinkedList<Character> actions) {
		this.actions = actions;
	}
	public double getgCost() {
		return gCost;
	}
	public void setgCost(double gCost) {
		this.gCost = gCost;
	}
	public double gethCost() {
		return hCost;
	}
	public void sethCost(double hCost) {
		this.hCost = hCost;
	}
	public double getfCost() {
		return fCost;
	}
	public void setfCost(double fCost) {
		this.fCost = fCost;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	
	
	
}
