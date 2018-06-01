import java.util.LinkedList;

public class AgentState {
	final static int EAST             			       = 0;
	final static int NORTH  						 = 1;
	final static int WEST 					          = 2;
	final static int SOUTH  					   	 = 3;
	private int initialed = 0;
	private boolean have_axe    				 = false;
	private boolean have_key     				 = false;
	private boolean have_treasure			   = false;
	private boolean have_raft    				  = false;
	private boolean on_raft      					= false;
	private boolean off_map      			      = false;
	private int num_stones_held			        = 0;
	private LinkedList<Item> TreeList          = new LinkedList<Item>();
	private LinkedList<Item> KeyList           = new LinkedList<Item>();
	private LinkedList<Item> AxeList          = new LinkedList<Item>();
	private LinkedList<Item> StoneList       = new LinkedList<Item>();
	private LinkedList<Item> DoorList         = new LinkedList<Item>();
	private LinkedList<Item> TreasureList   = new LinkedList<Item>();
	private LinkedList<Coordinate> ViewedPlace = new LinkedList<Coordinate>();
	private LinkedList<Character> pendingMove = new LinkedList<Character>();
	private LinkedList<Item> exploreView = new LinkedList<Item>();
	private char viewedMap[][] = new char[80][80];
	private int startCol = viewedMap.length/2;
	private int startRow = viewedMap.length/2;
	private int curRow = startRow;
	private int curCol = startCol;
	private int direction = NORTH;
	private boolean needkey = false;
	private boolean needaxe = false;
	
	
	public AgentState() {
		
	}


	public boolean isHave_axe() {
		return have_axe;
	}


	public void setHave_axe(boolean have_axe) {
		this.have_axe = have_axe;
	}


	public boolean isHave_key() {
		return have_key;
	}


	public void setHave_key(boolean have_key) {
		this.have_key = have_key;
	}


	public boolean isHave_treasure() {
		return have_treasure;
	}


	public void setHave_treasure(boolean have_treasure) {
		this.have_treasure = have_treasure;
	}


	public boolean isHave_raft() {
		return have_raft;
	}


	public void setHave_raft(boolean have_raft) {
		this.have_raft = have_raft;
	}


	public boolean isOn_raft() {
		return on_raft;
	}


	public void setOn_raft(boolean on_raft) {
		this.on_raft = on_raft;
	}


	public boolean isOff_map() {
		return off_map;
	}


	public void setOff_map(boolean off_map) {
		this.off_map = off_map;
	}


	public int getNum_stones_held() {
		return num_stones_held;
	}


	public void setNum_stones_held(int num_stones_held) {
		this.num_stones_held = num_stones_held;
	}


	public LinkedList<Item> getTreeList() {
		return TreeList;
	}


	public void setTreeList(LinkedList<Item> treeList) {
		TreeList = treeList;
	}


	public LinkedList<Item> getKeyList() {
		return KeyList;
	}


	public void setKeyList(LinkedList<Item> keyList) {
		KeyList = keyList;
	}


	public LinkedList<Item> getAxeList() {
		return AxeList;
	}


	public void setAxeList(LinkedList<Item> axeList) {
		AxeList = axeList;
	}


	public LinkedList<Item> getStoneList() {
		return StoneList;
	}


	public void setStoneList(LinkedList<Item> stoneList) {
		StoneList = stoneList;
	}


	public LinkedList<Item> getDoorList() {
		return DoorList;
	}


	public void setDoorList(LinkedList<Item> doorList) {
		DoorList = doorList;
	}


	public LinkedList<Item> getTreasureList() {
		return TreasureList;
	}


	public void setTreasureList(LinkedList<Item> treasureList) {
		TreasureList = treasureList;
	}


	public LinkedList<Coordinate> getViewedPlace() {
		return ViewedPlace;
	}


	public void setViewedPlace(LinkedList<Coordinate> viewedPlace) {
		ViewedPlace = viewedPlace;
	}


	public LinkedList<Character> getPendingMove() {
		return pendingMove;
	}


	public void setPendingMove(LinkedList<Character> pendingMove) {
		this.pendingMove = pendingMove;
	}


	public LinkedList<Item> getExploreView() {
		return exploreView;
	}


	public void setExploreView(LinkedList<Item> exploreView) {
		this.exploreView = exploreView;
	}


	public char[][] getViewedMap() {
		return viewedMap;
	}


	public void setViewedMap(char[][] viewedMap) {
		this.viewedMap = viewedMap;
	}


	public int getStartCol() {
		return startCol;
	}


	public void setStartCol(int startCol) {
		this.startCol = startCol;
	}


	public int getStartRow() {
		return startRow;
	}


	public void setStartRow(int startRow) {
		this.startRow = startRow;
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


	public int getDirection() {
		return direction;
	}


	public void setDirection(int direction) {
		this.direction = direction;
	}


	public int getInitialed() {
		return initialed;
	}


	public void setInitialed(int initialed) {
		this.initialed = initialed;
	}
	
	
	
	
	
	
}
