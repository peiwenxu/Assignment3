import java.util.LinkedList;

public class AiAction {
	private AgentState agentState;
	final static int EAST             			       = 0;
	final static int NORTH  						 = 1;
	final static int WEST 					          = 2;
	final static int SOUTH  					   	 = 3;

	AiAction(AgentState agentState) {
		this.agentState = agentState;
	}


	public AgentState getAgentState() {
		return agentState;
	}


	public void setAgentState(AgentState agentState) {
		this.agentState = agentState;
	}
	
	public boolean checkHaveTreasure() {
		return agentState.isHave_treasure();
	}
	
	
	
	public boolean treasureInView() {
		for(int x = 0; x < agentState.getViewedMap().length; x++) {
			for(int x2 = 0; x2 < agentState.getViewedMap()[0].length; x2++) {
				if(agentState.getViewedMap()[x][x2] == '$') {
					return true;
				}
			}
		}
		return false;
	}
	
	
	public Character doAction() {
		if(!agentState.getPendingMove().isEmpty()) {
			return agentState.getPendingMove().getFirst();
		}
		return 0;
	}
	
	public int predictDirection(int currentDirection, LinkedList<Character> actions) {
		int afterDir = currentDirection;
		for(Character ch: actions) {
			if(afterDir == NORTH) {
				if(ch == 'r' || ch == 'R') {
					afterDir = EAST;
				} else if (ch == 'l' || ch == 'L' ) {
					afterDir = WEST;
				}
			} else if (afterDir == EAST) {
				if(ch == 'r' || ch == 'R') {
					afterDir = SOUTH;
				} else if (ch == 'l' || ch == 'L' ) {
					afterDir = NORTH;
				}
			} else if (afterDir == SOUTH) {
				if(ch == 'r' || ch == 'R') {
					afterDir = WEST;
				} else if (ch == 'l' || ch == 'L' ) {
					afterDir = EAST;
				}
			} else if (afterDir == WEST) {
				if(ch == 'r' || ch == 'R') {
					afterDir = NORTH;
				} else if (ch == 'l' || ch == 'L' ) {
					afterDir = SOUTH;
				}
			}
		}
		return afterDir;
	}
	
	public boolean needToGo(Item thisPoint) {
		int exist = 0;
		int board = agentState.getViewedMap().length;
		for(Coordinate c:agentState.getViewedPlace()) {
			if(c.getRow() == thisPoint.getRow() && c.getCol() == thisPoint.getCol()) {
				exist = 1;
			}
		}
		if(exist == 1) {
			System.out.println("This point we viewed");
			return false;
		}
		
		
		
		
		int viewedNum = 0;
		int startRow = thisPoint.getRow()-2;
		int startCol = thisPoint.getCol()-2;
		for(int i = startRow; i < startRow + 5; i++) {
			if(i < board) {
				for(int i2 = startCol; i2 < startCol + 5; i2++) {
					if(i2 < board) {
						if(agentState.getViewedMap()[i][i2] != 0) {
							viewedNum++;
						}
					}
				}
			}
		}
		System.out.println("this point have see " + viewedNum);
		if(viewedNum == 25) {
			return false;
		}
		return true;
	}
	
	public void splitPoint(Item togo) {
		if(togo.getName().equals("tr")) {
			Item add1 = new Item(togo.getRow(),togo.getCol()-1,"tr2_1");
			Item add2 = new Item(togo.getRow()+1,togo.getCol(),"tr2_2");
			agentState.getExploreView().add(add1);
			agentState.getExploreView().add(add2);
		} else if (togo.getName().equals("tl")) {
			Item add1 = new Item(togo.getRow(),togo.getCol()+1,"tl2_1");
			Item add2 = new Item(togo.getRow()+1,togo.getCol(),"tl2_2");
			agentState.getExploreView().add(add1);
			agentState.getExploreView().add(add2);
		} else if (togo.getName().equals("br")) {
			Item add1 = new Item(togo.getRow(),togo.getCol()-1,"br2_1");
			Item add2 = new Item(togo.getRow()-1,togo.getCol(),"br2_2");
			agentState.getExploreView().add(add1);
			agentState.getExploreView().add(add2);
		} else if (togo.getName().equals("bl")) {
			Item add1 = new Item(togo.getRow()-1,togo.getCol(),"bl2_1");
			Item add2 = new Item(togo.getRow(),togo.getCol()+1,"bl2_2");
			agentState.getExploreView().add(add1);
			agentState.getExploreView().add(add2);
		} else if (togo.getName().equals("tr2_1")) {
			Item add1 = new Item(togo.getRow(),togo.getCol()-1,"tr3_1");
			Item add2 = new Item(togo.getRow()+1,togo.getCol(),"tr3_2");
			agentState.getExploreView().add(add1);
			agentState.getExploreView().add(add2);
		} else if (togo.getName().equals("tr2_2")) {
			Item add1 = new Item(togo.getRow(),togo.getCol()-1,"tr3_2");
			Item add2 = new Item(togo.getRow()+1,togo.getCol(),"tr3_3");
			agentState.getExploreView().add(add1);
			agentState.getExploreView().add(add2);
		} else if (togo.getName().equals("tl2_1")) {
			Item add1 = new Item(togo.getRow(),togo.getCol()+1,"tl3_1");
			Item add2 = new Item(togo.getRow()+1,togo.getCol(),"tl3_2");
			agentState.getExploreView().add(add1);
			agentState.getExploreView().add(add2);
		} else if (togo.getName().equals("tl2_2")) {
			Item add1 = new Item(togo.getRow(),togo.getCol()+1,"tl3_2");
			Item add2 = new Item(togo.getRow()+1,togo.getCol(),"tl3_3");
			agentState.getExploreView().add(add1);
			agentState.getExploreView().add(add2);
		} else if (togo.getName().equals("br2_1")) {
			Item add1 = new Item(togo.getRow(),togo.getCol()-1,"br3_1");
			Item add2 = new Item(togo.getRow()-1,togo.getCol(),"br3_2");
			agentState.getExploreView().add(add1);
			agentState.getExploreView().add(add2);
		} else if (togo.getName().equals("br2_2")) {
			Item add1 = new Item(togo.getRow(),togo.getCol()-1,"br3_2");
			Item add2 = new Item(togo.getRow()-1,togo.getCol(),"br3_3");
			agentState.getExploreView().add(add1);
			agentState.getExploreView().add(add2);
		} else if (togo.getName().equals("bl2_1")) {
			Item add1 = new Item(togo.getRow()-1,togo.getCol(),"bl3_1");
			Item add2 = new Item(togo.getRow(),togo.getCol()+1,"bl3_2");
			agentState.getExploreView().add(add1);
			agentState.getExploreView().add(add2);
		} else if (togo.getName().equals("bl2_2")) {
			Item add1 = new Item(togo.getRow()-1,togo.getCol(),"bl3_2");
			Item add2 = new Item(togo.getRow(),togo.getCol()+1,"bl3_3");
			agentState.getExploreView().add(add1);
			agentState.getExploreView().add(add2);
		} else if (togo.getName().equals("tr3_2")) {
			Item add1 = new Item(togo.getRow(),togo.getCol()-1,"tr4_1");
			Item add2 = new Item(togo.getRow()+1,togo.getCol(),"tr4_2");
			agentState.getExploreView().add(add1);
			agentState.getExploreView().add(add2);
		} else if (togo.getName().equals("tl3_2")) {
			Item add1 = new Item(togo.getRow(),togo.getCol()+1,"tl4_1");
			Item add2 = new Item(togo.getRow()+1,togo.getCol(),"tl4_2");
			agentState.getExploreView().add(add1);
			agentState.getExploreView().add(add2);
		} else if (togo.getName().equals("br3_2")) {
			Item add1 = new Item(togo.getRow()-1,togo.getCol(),"br4_1");
			Item add2 = new Item(togo.getRow(),togo.getCol()-1,"br4_2");
			agentState.getExploreView().add(add1);
			agentState.getExploreView().add(add2);
		} else if (togo.getName().equals("bl3_2")) {
			Item add1 = new Item(togo.getRow(),togo.getCol()+1,"bl4_1");
			Item add2 = new Item(togo.getRow()-1,togo.getCol(),"bl4_2");
			agentState.getExploreView().add(add1);
			agentState.getExploreView().add(add2);
		}
	}
	
	
	public Character makeMove() {
		Search search = new Search(this.agentState);

		
		
		
		if(!agentState.getPendingMove().isEmpty()) {
			char ch = doAction();
			agentState.getPendingMove().removeFirst();
			return ch;
		}
		
		//make desision
		while(agentState.getPendingMove().isEmpty()) {
			//check if we have gold then we back
			if(agentState.isHave_treasure() == true) {
				LinkedList<Character> actions = search.AstarSearch(agentState.getCurRow(), agentState.getCurCol(), 
	        			agentState.getStartRow(), agentState.getStartCol(), agentState.getViewedMap(), agentState.getDirection());
				//now we on land decision
				if(actions.getFirst() != '?') {
					for(Character action: actions) {
						agentState.getPendingMove().add(action);
					}
					char ch = doAction();
					agentState.getPendingMove().removeFirst();
					return ch;
				}
			}
			
			//if we see gold
			if(!agentState.getTreasureList().isEmpty()) {
				System.out.println("go to gold");
				int goldRow = agentState.getTreasureList().getFirst().getRow();
				int goldCol = agentState.getTreasureList().getFirst().getCol();
				//check if we can get
				LinkedList<Character> actions = search.AstarSearch(agentState.getCurRow(), agentState.getCurCol(), 
	        			goldRow, goldCol, agentState.getViewedMap(), agentState.getDirection());
				System.out.println(actions.getFirst());
				if(actions.getFirst() != '?') {
					for(Character action: actions) {
						agentState.getPendingMove().add(action);
					}
					char ch = doAction();
					agentState.getPendingMove().removeFirst();
					return ch;
					
//					//check if we can back to startPoint
//					int afterDir = predictDirection(agentState.getDirection(),actions);
//					LinkedList<Character> actions2 = search.AstarSearch(goldRow, goldCol, 
//		        			agentState.getStartRow(), agentState.getStartCol(), agentState.getViewedMap(),afterDir);
//					if(actions2.getFirst() != '?') {
//						//We can get gold and back
//						for(Character action: actions) {
//							agentState.getPendingMove().add(action);
//						}
//						for(Character action: actions2) {
//							agentState.getPendingMove().add(action);
//						}
//						char ch = doAction();
//						agentState.getPendingMove().removeFirst();
//						return ch;
//					}
				}
			}
			//we dont have treasure and we need to explore map
			if(!agentState.getExploreView().isEmpty()) {
				System.out.println("we are here");
				Item togo = agentState.getExploreView().removeLast();
				System.out.println(togo.getName() + " " + togo.getRow() + " " + togo.getCol() + " ");
				//check this point we need to go
				if(needToGo(togo)) {
					System.out.println("this point need to go");
					//check we can get to this point
					int cango = 1;
					switch(agentState.getViewedMap()[togo.getRow()][togo.getCol()]) {
						case ' ': case 'k': case 'a': case 'o': case '$':
							cango = 1;
							break;
						default:
							cango = 0;
							break;
					}
					if(cango == 1) {
						LinkedList<Character> actions = search.AstarSearch(agentState.getCurRow(), agentState.getCurCol(), 
			        			togo.getRow(), togo.getCol(), agentState.getViewedMap(), agentState.getDirection());
						if(actions.getFirst() == '?') {
							cango = 0;
						}
						if(cango == 1) {
							Item topRight = new Item(togo.getRow()-2,togo.getCol()+2,"tr");
							Item topLeft = new Item(togo.getRow()-2,togo.getCol()-2,"tl");
							Item bottomRight = new Item(togo.getRow()+2,togo.getCol()+2,"br");
							Item bottomLeft = new Item(togo.getRow()+2,togo.getCol()-2,"bl");
							this.agentState.getExploreView().add(topRight);
							this.agentState.getExploreView().add(topLeft);
							this.agentState.getExploreView().add(bottomLeft);
							this.agentState.getExploreView().add(bottomRight);
							for(Character action: actions) {
								agentState.getPendingMove().add(action);
							}
							char ch = doAction();
							agentState.getPendingMove().removeFirst();
							return ch;
						} else {
							splitPoint(togo);
							continue;
						}
					} else {
						splitPoint(togo);
						continue;
					}
				} else {
					continue;
				}
			}
			//we dont have treasure but we can see all the view then we pick up key and axe if we can go
			//get all the keys
			if(!agentState.getKeyList().isEmpty()) {
				int keyRow = 0;
				int keyCol = 0;
				for(Item key: agentState.getKeyList()) {
					keyRow = key.getRow();
					keyCol = key.getCol();
				}
				//check we can get
				LinkedList<Character> actions = search.AstarSearch(agentState.getCurRow(), agentState.getCurCol(), 
	        			keyRow, keyCol, agentState.getViewedMap(), agentState.getDirection());
				if(actions.getFirst() != '?') {
					for(Character action: actions) {
						agentState.getPendingMove().add(action);
					}
					char ch = doAction();
					agentState.getPendingMove().removeFirst();
					return ch;
				}
			}
			//get all the axe that we can get
			if(!agentState.getAxeList().isEmpty()) {
				int axeRow = 0;
				int axeCol = 0;
				for(Item axe: agentState.getAxeList()) {
					axeRow = axe.getRow();
					axeCol = axe.getCol();
				}
				//check we can get
				LinkedList<Character> actions = search.AstarSearch(agentState.getCurRow(), agentState.getCurCol(), 
	        			axeRow, axeCol, agentState.getViewedMap(), agentState.getDirection());
				if(actions.getFirst() != '?') {
					for(Character action: actions) {
						agentState.getPendingMove().add(action);
					}
					char ch = doAction();
					agentState.getPendingMove().removeFirst();
					return ch;
				}
			}
			//open the door for explore map if we cant do anything
			if(!agentState.getDoorList().isEmpty()) {
				int doorRow = 0;
				int doorCol = 0;
				for(Item door: agentState.getDoorList()) {
					doorRow = door.getRow();
					doorCol = door.getCol();
				}
				LinkedList<Character> actions = search.AstarSearch(agentState.getCurRow(), agentState.getCurCol(), 
	        			doorRow, doorCol, agentState.getViewedMap(), agentState.getDirection());
				if(actions.getFirst() != '?') {
					for(Character action: actions) {
						agentState.getPendingMove().add(action);
					}
					char ch = doAction();
					agentState.getPendingMove().removeFirst();
					return ch;
				}
			}
			
			if(!agentState.getTreeList().isEmpty()) {
				System.out.println("we here####");
				int treeRow = 0;
				int treeCol = 0;
				for(Item tree: agentState.getTreeList()) {
					treeRow = tree.getRow();
					treeCol = tree.getCol();
				}
				LinkedList<Character> actions = search.AstarSearch(agentState.getCurRow(), agentState.getCurCol(), 
	        			treeRow, treeCol, agentState.getViewedMap(), agentState.getDirection());
				if(actions.getFirst() != '?') {
					for(Character action: actions) {
						agentState.getPendingMove().add(action);
					}
					char ch = doAction();
					agentState.getPendingMove().removeFirst();
					return ch;
				}
			}
			
		}
		
		
		return 0;
	}
	
	
	
	
	
	
	
}
