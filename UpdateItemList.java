
public class UpdateItemList {
	final static int EAST = 0;
	final static int NORTH = 1;
	final static int WEST = 2;
	final static int SOUTH = 3;
	private AgentState agentState;
	
	
	public UpdateItemList() {
		
	}
	public UpdateItemList(AgentState agentState) {
		this.agentState = agentState;
	}
	public AgentState getagentState() {
		return agentState;
	}
	public void setagentState(AgentState agentState) {
		this.agentState = agentState;
	}
	
	public void Update(int curRow, int curCol, char Item) {
		if(Item == 'k') {
			for(Item e:agentState.getKeyList()) {
				if(e.getRow() == curRow && e.getCol() == curCol) {
					agentState.getKeyList().remove(e);
					break;
				}
			}
		} else if(Item == 'a') {
			for(Item e:agentState.getAxeList()) {
				if(e.getRow() == curRow && e.getCol() == curCol) {
					agentState.getAxeList().remove(e);
					break;
				}
			}
		} else if(Item == '$') {
			for(Item e:agentState.getTreasureList()) {
				if(e.getRow() == curRow && e.getCol() == curCol) {
					agentState.getTreasureList().remove(e);
					break;
				}
			}
		} else if(Item == 'o') {
			for(Item e:agentState.getStoneList()) {
				if(e.getRow() == curRow && e.getCol() == curCol) {
					agentState.getStoneList().remove(e);
					break;
				}
			}
		}
	}
	
	public void remove_chop_unlock(int curRow, int curCol, char action, int direction) {
		if(action == 'C' || action == 'c') {
			if(direction == NORTH) {
				for(Item e:agentState.getTreeList()) {
					if(e.getRow() == curRow-1 && e.getCol() == curCol) {
						agentState.getTreeList().remove(e);
						break;
					}
				}
			} else if(direction == SOUTH) {
				for(Item e:agentState.getTreeList()) {
					if(e.getRow() == curRow+1 && e.getCol() == curCol) {
						agentState.getTreeList().remove(e);
						break;
					}
				}
			} else if(direction == WEST) {
				for(Item e:agentState.getTreeList()) {
					if(e.getRow() == curRow && e.getCol() == curCol-1) {
						agentState.getTreeList().remove(e);
						break;
					}
				}
			} else if(direction == EAST) {
				for(Item e:agentState.getTreeList()) {
					if(e.getRow() == curRow && e.getCol() == curCol+1) {
						agentState.getTreeList().remove(e);
						break;
					}
				}
			}
		} else if(action == 'U' || action == 'u') {
			if(direction == NORTH) {
				for(Item e:agentState.getDoorList()) {
					if(e.getRow() == curRow-1 && e.getCol() == curCol) {
						agentState.getDoorList().remove(e);
						break;
					}
				}
			} else if(direction == SOUTH) {
				for(Item e:agentState.getDoorList()) {
					if(e.getRow() == curRow+1 && e.getCol() == curCol) {
						agentState.getDoorList().remove(e);
						break;
					}
				}
			} else if(direction == WEST) {
				for(Item e:agentState.getDoorList()) {
					if(e.getRow() == curRow && e.getCol() == curCol-1) {
						agentState.getDoorList().remove(e);
						break;
					}
				}
			} else if(direction == EAST) {
				for(Item e:agentState.getDoorList()) {
					if(e.getRow() == curRow && e.getCol() == curCol+1) {
						agentState.getDoorList().remove(e);
						break;
					}
				}
			}
		}
	}
	
	
	
}
