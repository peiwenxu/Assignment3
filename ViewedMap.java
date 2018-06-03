
public class ViewedMap {
	final static int EAST   = 0;
	final static int NORTH  = 1;
	final static int WEST   = 2;
	final static int SOUTH  = 3;
	private AgentState agentState;
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
	public ViewedMap(AgentState agentState) {
		this.agentState = agentState;
	}

	public void initialMap(char inView[][], char currentView[][]) {
		int midRow = currentView.length/2;
		int midCol = (currentView.length)/2;
		Coordinate Viewed = new Coordinate(midRow,midCol);
		this.agentState.getViewedPlace().add(Viewed);
		Item topRight = new Item(midRow-2,midCol+2,"tr");
		Item topLeft = new Item(midRow-2,midCol-2,"tl");
		Item bottomRight = new Item(midRow+2,midCol+2,"br");
		Item bottomLeft = new Item(midRow+2,midCol-2,"bl");
		this.agentState.getExploreView().add(topRight);
		this.agentState.getExploreView().add(topLeft);
		this.agentState.getExploreView().add(bottomLeft);
		this.agentState.getExploreView().add(bottomRight);
		int startRow = midRow-2;
		int startCol = midCol-2;
		for(int i = 0; i < 5; i++) {
			for(int i2 = 0; i2 < 5; i2++) {
				if(i2 == 2 && i == 2) {
					currentView[startRow][startCol++] = '^';
				} else {
					currentView[startRow][startCol] = inView[i][i2];
					collectItem(inView,i,i2,startRow,startCol);
					startCol++;
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
			//this can be move success or fail
			if(dire == NORTH) {
				int startRow = currentRow-2;
				int startCol = currentCol-2;
				viewedMap[currentRow+1][currentCol] = ' ';
				viewedMap[currentRow][currentCol] = '^';
				for(int i = 0; i < 5; i++) {
					viewedMap[startRow][startCol] = inView[0][i];
					collectItem(inView,0,i,startRow,startCol);
					startCol++;
				}
			} else if (dire == SOUTH) {
				int startRow = currentRow+2;
				int startCol = currentCol+2;
				viewedMap[currentRow-1][currentCol] = ' ';
				viewedMap[currentRow][currentCol] = 'v';
				for(int i = 0; i < 5; i++) {
					viewedMap[startRow][startCol] = inView[0][i];
					collectItem(inView,0,i,startRow,startCol);
					startCol--;
				}
			} else if(dire == EAST) {
				int startRow = currentRow-2;
				int startCol = currentCol+2;
				viewedMap[currentRow][currentCol-1] = ' ';
				viewedMap[currentRow][currentCol] = '>';
				for(int i = 0; i < 5; i++) {
					viewedMap[startRow][startCol] = inView[0][i];
					collectItem(inView,0,i,startRow,startCol);
					startRow++;
				}
			} else if(dire == WEST) {
				int startRow = currentRow+2;
				int startCol = currentCol-2;
				viewedMap[currentRow][currentCol+1] = ' ';
				viewedMap[currentRow][currentCol] = '<';
				for(int i = 0; i < 5; i++) {
					viewedMap[startRow][startCol] = inView[0][i];
					collectItem(inView,0,i,startRow,startCol);
					startRow--;
				}
			}
		} else if (action == 0) {
			return;
		}
	}

	public boolean chop_unlock(int dire, AgentState agent, char action, char viewedMap[][], int currentRow, int currentCol) {
		if(action == 'C' || action == 'c') {
			if(agentState.isHave_axe() == true) {
				if(dire == NORTH) {
					if(viewedMap[currentRow-1][currentCol] == 'T') {
						viewedMap[currentRow-1][currentCol] = ' ';
						agentState.setHave_raft(true);
						return true;
					}
				} else if(dire == EAST) {
					if(viewedMap[currentRow][currentCol+1] == 'T') {
						viewedMap[currentRow][currentCol+1] = ' ';
						agentState.setHave_raft(true);
						return true;
					}
				} else if(dire == SOUTH) {
					if(viewedMap[currentRow+1][currentCol] == 'T') {
						viewedMap[currentRow+1][currentCol] = ' ';
						agentState.setHave_raft(true);
						return true;
					}
				} else if(dire == WEST) {
					if(viewedMap[currentRow][currentCol-1] == 'T') {
						viewedMap[currentRow][currentCol-1] = ' ';
						agentState.setHave_raft(true);
						return true;
					}
				}
			}
		} else if (action == 'U' || action == 'u') {
			if(agentState.isHave_key() == true) {
				if(dire == NORTH) {
					if(viewedMap[currentRow-1][currentCol] == '-') {
						viewedMap[currentRow-1][currentCol] = ' ';
						//agent.setHave_raft(true);
						return true;
					}
				} else if(dire == EAST) {
					if(viewedMap[currentRow][currentCol+1] == '-') {
						viewedMap[currentRow][currentCol+1] = ' ';
						//agent.setHave_raft(true);
						return true;
					}
				} else if(dire == SOUTH) {
					if(viewedMap[currentRow+1][currentCol] == '-') {
						viewedMap[currentRow+1][currentCol] = ' ';
						//agent.setHave_raft(true);
						return true;
					}
				} else if(dire == WEST) {
					if(viewedMap[currentRow][currentCol-1] == '-') {
						viewedMap[currentRow][currentCol-1] = ' ';
						//agent.setHave_raft(true);
						return true;
					}
				}
			}
		}
		return false;
	}

	public void collectItem(char[][] inView, int row, int col,int viewRow, int viewCol) {
		if(inView[row][col] == 'T') {
			int exist = 0;
			for(int i = 0; i < this.agentState.getTreeList().size(); i++) {
				if(this.agentState.getTreeList().get(i).getRow() == viewRow &&
						this.agentState.getTreeList().get(i).getCol() == viewCol) {
					exist = 1;
				}
			}
			if(exist == 0) {
				Item Tree = new Item(viewRow,viewCol,"Tree");
				this.agentState.getTreeList().add(Tree);
			}
		} else if(inView[row][col] == 'a') {
			int exist = 0;
			for(int i = 0; i < this.agentState.getAxeList().size(); i++) {
				if(this.agentState.getAxeList().get(i).getRow() == viewRow &&
						this.agentState.getAxeList().get(i).getCol() == viewCol) {
					exist = 1;
				}
			}
			if(exist == 0) {
				Item Axe = new Item(viewRow,viewCol,"Axe");
				this.agentState.getAxeList().add(Axe);
			}
		} else if(inView[row][col] == 'k') {
			int exist = 0;
			for(int i = 0; i < this.agentState.getKeyList().size(); i++) {
				if(this.agentState.getKeyList().get(i).getRow() == viewRow &&
						this.agentState.getKeyList().get(i).getCol() == viewCol) {
					exist = 1;
				}
			}
			if(exist == 0) {
				Item Key = new Item(viewRow,viewCol,"Key");
				this.agentState.getKeyList().add(Key);
			}
		} else if(inView[row][col] == '-') {
			int exist = 0;
			for(int i = 0; i < this.agentState.getDoorList().size(); i++) {
				if(this.agentState.getDoorList().get(i).getRow() == viewRow &&
						this.agentState.getDoorList().get(i).getCol() == viewCol) {
					exist = 1;
				}
			}
			if(exist == 0) {
				Item Door = new Item(viewRow,viewCol,"Door");
				this.agentState.getDoorList().add(Door);
			}
		} else if(inView[row][col] == 'o') {
			int exist = 0;
			for(int i = 0; i < this.agentState.getStoneList().size(); i++) {
				if(this.agentState.getStoneList().get(i).getRow() == viewRow &&
						this.agentState.getStoneList().get(i).getCol() == viewCol) {
					exist = 1;
				}
			}
			if(exist == 0) {
				Item Stone = new Item(viewRow,viewCol,"Stone");
				this.agentState.getStoneList().add(Stone);
			}
		} else if(inView[row][col] == '$') {
			int exist = 0;
			for(int i = 0; i < this.agentState.getTreasureList().size(); i++) {
				if(this.agentState.getTreasureList().get(i).getRow() == viewRow &&
						this.agentState.getTreasureList().get(i).getCol() == viewCol) {
					exist = 1;
				}
			}
			if(exist == 0) {
				Item Treasure = new Item(viewRow,viewCol,"Treasure");
				this.agentState.getTreasureList().add(Treasure);
			}
		}
	}
	
	public void printAgentDetail() {
		System.out.println("CurrentRow: " +agentState.getCurRow());
        System.out.println("CurrentCol: " +agentState.getCurCol());
        System.out.println("Keys:");
        for(int o = 0; o < agentState.getKeyList().size(); o++) {
			System.out.println("Row: " +agentState.getKeyList().get(o).getRow() + " Col: " +agentState.getKeyList().get(o).getCol());
        }
        System.out.println("Trees:");
        for(int o = 0; o < agentState.getTreeList().size(); o++) {
    			System.out.println("Row: " +agentState.getTreeList().get(o).getRow() +" Col: " + agentState.getTreeList().get(o).getCol());
        }
        	System.out.println("Axes:");
        for(int o = 0; o < agentState.getAxeList().size(); o++) {
    			System.out.println("Row: " +agentState.getAxeList().get(o).getRow() +" Col: " + agentState.getAxeList().get(o).getCol());
        }
        System.out.println("Stones:");
        for(int o = 0; o < agentState.getStoneList().size(); o++) {
    			System.out.println("Row: " +agentState.getStoneList().get(o).getRow() +" Col: " + agentState.getStoneList().get(o).getCol());
        }
        System.out.println("Doors:");
        for(int o = 0; o < agentState.getDoorList().size(); o++) {
    			System.out.println("Row: " +agentState.getDoorList().get(o).getRow() +" Col: " + agentState.getDoorList().get(o).getCol());
        }
        System.out.println("Treasures:");
        for(int o = 0; o < agentState.getTreasureList().size(); o++) {
    			System.out.println("Row: " +agentState.getTreasureList().get(o).getRow() +" Col: " + agentState.getTreasureList().get(o).getCol());
        }
        System.out.println("Where we viewed?");
        for(int o = 0; o < agentState.getViewedPlace().size(); o++) {
        		System.out.println("Row: " + agentState.getViewedPlace().get(o).getRow() + " Col: " + agentState.getViewedPlace().get(o).getCol());
        }
        System.out.println("How mant stone we have: " + agentState.getNum_stones_held());
        System.out.println("Have axe? " + agentState.isHave_axe());
        System.out.println("Have key? " + agentState.isHave_key());
        System.out.println("Have raft? " + agentState.isHave_raft());
        System.out.println("Have treasure? " + agentState.isHave_treasure());
        System.out.println("On raft? " + agentState.isOn_raft());
        System.out.println("Off map? " + agentState.isOff_map());
	}

	public void updateAgentDetail(char action) {
		UpdateItemList updateList = new UpdateItemList(this.agentState);
		ViewedMap updateView = new ViewedMap(this.agentState);
		if(action == 'R' || action == 'r') {
	    		if(agentState.getDirection() == EAST) {
	    			agentState.setDirection(SOUTH);
	    		} else {
	    			agentState.setDirection(agentState.getDirection()-1);	    			
	    		}
	    	} else if (action == 'L' || action == 'l') {
	    		if(agentState.getDirection() == SOUTH) {
	    			agentState.setDirection(EAST);
	    		} else {
	    			agentState.setDirection(agentState.getDirection()+1);
	    		}
	    	} else if(action == 'F' || action == 'f') {
	    		if(agentState.getDirection() == NORTH) {
	    			switch(agentState.getViewedMap()[agentState.getCurRow()-1][agentState.getCurCol()]) {
	    				case '*':case'T':case'-':
	    					break;
	    				case 'k':
	    					agentState.setHave_key(true);
	    					agentState.setCurRow(agentState.getCurRow()-1);
	    					updateList.Update(agentState.getCurRow(),agentState.getCurCol(),'k');
	    					break;
	    				case 'a':
	    					agentState.setHave_axe(true);
	    					agentState.setCurRow(agentState.getCurRow()-1);
	    					updateList.Update(agentState.getCurRow(),agentState.getCurCol(),'a');
	    					break;
	    				case '$':
	    					agentState.setHave_treasure(true);
	    					agentState.setCurRow(agentState.getCurRow()-1);
	    					updateList.Update(agentState.getCurRow(),agentState.getCurCol(),'$');
	    					break;
	    				case 'o':
	    					agentState.setNum_stones_held(agentState.getNum_stones_held()+1);
	    					agentState.setCurRow(agentState.getCurRow()-1);
	    					updateList.Update(agentState.getCurRow(),agentState.getCurCol(),'o');
	    					break;
	    				default:
	    					agentState.setCurRow(agentState.getCurRow()-1);
	    					break;
	    			}
	    		} else if(agentState.getDirection() == SOUTH) {
	    			switch(agentState.getViewedMap()[agentState.getCurRow()+1][agentState.getCurCol()]) {
	    				case '*':case'T':case'-':
	    					break;
	    				case 'k':
	    					agentState.setHave_key(true);
	    					agentState.setCurRow(agentState.getCurRow()+1);
	    					updateList.Update(agentState.getCurRow(),agentState.getCurCol(),'k');
	    					break;
	    				case 'a':
	    					agentState.setHave_axe(true);
	    					agentState.setCurRow(agentState.getCurRow()+1);
	    					updateList.Update(agentState.getCurRow(),agentState.getCurCol(),'a');
	    					break;
	    				case '$':
	    					agentState.setHave_treasure(true);
	    					agentState.setCurRow(agentState.getCurRow()+1);
	    					updateList.Update(agentState.getCurRow(),agentState.getCurCol(),'$');
	    					break;
	    				case 'o':
	    					agentState.setNum_stones_held(agentState.getNum_stones_held()+1);
	    					agentState.setCurRow(agentState.getCurRow()+1);
	    					updateList.Update(agentState.getCurRow(),agentState.getCurCol(),'o');
	    					break;
					default:
						agentState.setCurRow(agentState.getCurRow()+1);
						break;
	    			}
	    		} else if(agentState.getDirection() == EAST) {
	    			switch(agentState.getViewedMap()[agentState.getCurRow()][agentState.getCurCol()+1]) {
	    				case '*':case'T':case'-':
	    					break;
	    				case 'k':
	    					agentState.setHave_key(true);
	    					agentState.setCurCol(agentState.getCurCol()+1);
	    					updateList.Update(agentState.getCurRow(),agentState.getCurCol(),'k');
	    					break;
	    				case 'a':
	    					agentState.setHave_axe(true);
	    					agentState.setCurCol(agentState.getCurCol()+1);
	    					updateList.Update(agentState.getCurRow(),agentState.getCurCol(),'a');
	    					break;
	    				case '$':
	    					agentState.setHave_treasure(true);
	    					agentState.setCurCol(agentState.getCurCol()+1);
	    					updateList.Update(agentState.getCurRow(),agentState.getCurCol(),'$');
	    					break;
	    				case 'o':
	    					agentState.setNum_stones_held(agentState.getNum_stones_held()+1);
	    					agentState.setCurCol(agentState.getCurCol()+1);
	    					updateList.Update(agentState.getCurRow(),agentState.getCurCol(),'o');
	    					break;
					default:
						agentState.setCurCol(agentState.getCurCol()+1);
						break;
	    			}
	    		} else if(agentState.getDirection() == WEST) {
	    			switch(agentState.getViewedMap()[agentState.getCurRow()][agentState.getCurCol()-1]) {
	    				case '*':case'T':case'-':
	    					break;
	    				case 'k':
	    					agentState.setHave_key(true);
	    					agentState.setCurCol(agentState.getCurCol()-1);
	    					updateList.Update(agentState.getCurRow(),agentState.getCurCol(),'k');
	    					break;
	    				case 'a':
	    					agentState.setHave_axe(true);
	    					agentState.setCurCol(agentState.getCurCol()-1);
	    					updateList.Update(agentState.getCurRow(),agentState.getCurCol(),'a');
	    					break;
	    				case '$':
	    					agentState.setHave_treasure(true);
	    					agentState.setCurCol(agentState.getCurCol()-1);
	    					updateList.Update(agentState.getCurRow(),agentState.getCurCol(),'$');
	    					break;
	    				case 'o':
	    					agentState.setNum_stones_held(agentState.getNum_stones_held()+1);
	    					agentState.setCurCol(agentState.getCurCol()-1);
	    					updateList.Update(agentState.getCurRow(),agentState.getCurCol(),'o');
	    					break;
					default:
						agentState.setCurCol(agentState.getCurCol()-1);
						break;
	    			}
	    		}
			int exist = 0;
			for(int y = 0; y < agentState.getViewedPlace().size(); y++) {
				if(agentState.getCurRow() == agentState.getViewedPlace().get(y).getRow()
						&& agentState.getCurCol() == agentState.getViewedPlace().get(y).getCol()) {
					exist = 1;
				}
			}
			if(exist == 0) {
				Coordinate newPlace = new Coordinate(agentState.getCurRow(),agentState.getCurCol());
				agentState.getViewedPlace().add(newPlace);
			}
	    	} else if (action == 'C' || action == 'c' || action == 'U' || action == 'u') {
	    		Boolean isChopOrUnlock = updateView.chop_unlock(agentState.getDirection(), agentState, action, agentState.getViewedMap(),agentState.getCurRow(), agentState.getCurCol());
	    		if(isChopOrUnlock == true) {
	    			updateList.remove_chop_unlock(agentState.getCurRow(),agentState.getCurCol(),action,agentState.getDirection());
	    			int curRow = -1;
	    			int curCol = -1;
	    			if(agentState.getDirection() == NORTH) {
	    				curRow = agentState.getCurRow()-1;
	    				curCol = agentState.getCurCol();
	    			} else if (agentState.getDirection() == EAST) {
	    				curRow = agentState.getCurRow();
	    				curCol = agentState.getCurCol()+1;
	    			} else if(agentState.getDirection() == SOUTH) {
	    				curRow = agentState.getCurRow()+1;
	    				curCol = agentState.getCurCol();
	    			} else if(agentState.getDirection() == WEST) {
	    				curRow = agentState.getCurRow();
	    				curCol = agentState.getCurCol()-1;
	    			}
	    			Item topRight = new Item(curRow-2,curCol+2,"tr");
	    			Item topLeft = new Item(curRow-2,curCol-2,"tl");
	    			Item bottomRight = new Item(curRow+2,curCol+2,"br");
	    			Item bottomLeft = new Item(curRow+2,curCol-2,"bl");
	    			this.agentState.getExploreView().add(topRight);
	    			this.agentState.getExploreView().add(topLeft);
	    			this.agentState.getExploreView().add(bottomLeft);
	    			this.agentState.getExploreView().add(bottomRight);
	    		}
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
