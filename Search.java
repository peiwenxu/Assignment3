import java.util.LinkedList;

public class Search {
	final static int EAST             			       = 0;
	final static int NORTH  						 = 1;
	final static int WEST 					          = 2;
	final static int SOUTH  					   	 = 3;
	private AgentState agentState;
	
	Search(AgentState agentState) {
		this.agentState = agentState;
	}
	  
	//priority queue, return the nearest point to goal point using f cost
	public PointState priorityQueue(LinkedList<PointState> queue) {
		PointState forReturn = null;
		double lowestF = 9999;
		//find lowestf
		for(PointState e: queue) {
			if(e.getfCost() < lowestF) {
				lowestF = e.getfCost();
			}
		}
		//find the lowest point
		for(PointState e: queue) {
			if(e.getfCost() == lowestF) {
				return e;
			}
		}
		return forReturn;
	}
	
	//for moving forward part, if agent just do moving, we give the point action into its action list
	public void makeDirection(PointState prePoint, PointState curPoint, int position) {
		int preDir = prePoint.getDirection();
		//copy the pre-point action into new point
		for(Character ch: prePoint.getActions()) {
			curPoint.getActions().add(ch);
		}
		
		if(preDir == NORTH) {
			switch(position) {
				case NORTH:
					curPoint.getActions().add('f');
					curPoint.setDirection(NORTH);
					break;
				case EAST:
					curPoint.getActions().add('r');
					curPoint.getActions().add('f');
					curPoint.setDirection(EAST);
					break;
				case SOUTH:
					curPoint.getActions().add('r');
					curPoint.getActions().add('r');
					curPoint.getActions().add('f');
					curPoint.setDirection(SOUTH);
					break;
				case WEST:
					curPoint.getActions().add('l');
					curPoint.getActions().add('f');
					curPoint.setDirection(WEST);
					break;
			}
		} else if(preDir == EAST) {
			switch(position) {
				case NORTH:
					curPoint.getActions().add('l');
					curPoint.getActions().add('f');
					curPoint.setDirection(NORTH);
					break;
				case EAST:
					curPoint.getActions().add('f');
					curPoint.setDirection(EAST);
					break;
				case SOUTH:
					curPoint.getActions().add('r');
					curPoint.getActions().add('f');
					curPoint.setDirection(SOUTH);
					break;
				case WEST:
					curPoint.getActions().add('l');
					curPoint.getActions().add('l');
					curPoint.getActions().add('f');
					curPoint.setDirection(WEST);
					break;
			}
		} else if(preDir == SOUTH) {
			switch(position) {
				case NORTH:
					curPoint.getActions().add('l');
					curPoint.getActions().add('l');
					curPoint.getActions().add('f');
					curPoint.setDirection(NORTH);
					break;
				case EAST:
					curPoint.getActions().add('l');
					curPoint.getActions().add('f');
					curPoint.setDirection(EAST);
					break;
				case SOUTH:
					curPoint.getActions().add('f');
					curPoint.setDirection(SOUTH);
					break;
				case WEST:
					curPoint.getActions().add('r');
					curPoint.getActions().add('f');
					curPoint.setDirection(WEST);
					break;
			}
		} else if(preDir == WEST) {
			switch(position) {
				case NORTH:
					curPoint.getActions().add('r');
					curPoint.getActions().add('f');
					curPoint.setDirection(NORTH);
					break;
				case EAST:
					curPoint.getActions().add('l');
					curPoint.getActions().add('l');
					curPoint.getActions().add('f');
					curPoint.setDirection(EAST);
					break;
				case SOUTH:
					curPoint.getActions().add('l');
					curPoint.getActions().add('f');
					curPoint.setDirection(SOUTH);
					break;
				case WEST:
					curPoint.getActions().add('f');
					curPoint.setDirection(WEST);
					break;
			}
		}
	}
	//if point need to chop or unlock we give the point action into its action list
	public void unlock_chop(PointState prePoint, PointState curPoint, int position, char action) {
		int preDir = prePoint.getDirection();
		//copy the pre point action into new point
		for(Character ch: prePoint.getActions()) {
			curPoint.getActions().add(ch);
		}
		if(preDir == NORTH) {
			switch(position) {
			case NORTH:
				curPoint.getActions().add(action);
				curPoint.getActions().add('f');
				curPoint.setDirection(NORTH);
				break;
			case EAST:
				curPoint.getActions().add('r');
				curPoint.getActions().add(action);
				curPoint.getActions().add('f');
				curPoint.setDirection(EAST);
				break;
			case SOUTH:
				curPoint.getActions().add('r');
				curPoint.getActions().add('r');
				curPoint.getActions().add(action);
				curPoint.getActions().add('f');
				curPoint.setDirection(SOUTH);
				break;
			case WEST:
				curPoint.getActions().add('l');
				curPoint.getActions().add(action);
				curPoint.getActions().add('f');
				curPoint.setDirection(WEST);
				break;
			}
		} else if(preDir == EAST) {
			switch(position) {
				case NORTH:
					curPoint.getActions().add('l');
					curPoint.getActions().add(action);
					curPoint.getActions().add('f');
					curPoint.setDirection(NORTH);
					break;
				case EAST:
					curPoint.getActions().add(action);
					curPoint.getActions().add('f');
					curPoint.setDirection(EAST);
					break;
				case SOUTH:
					curPoint.getActions().add('r');
					curPoint.getActions().add(action);
					curPoint.getActions().add('f');
					curPoint.setDirection(SOUTH);
					break;
				case WEST:
					curPoint.getActions().add('l');
					curPoint.getActions().add('l');
					curPoint.getActions().add(action);
					curPoint.getActions().add('f');
					curPoint.setDirection(WEST);
					break;
			}
		} else if(preDir == SOUTH) {
			switch(position) {
				case NORTH:
					curPoint.getActions().add('l');
					curPoint.getActions().add('l');
					curPoint.getActions().add(action);
					curPoint.getActions().add('f');
					curPoint.setDirection(NORTH);
					break;
				case EAST:
					curPoint.getActions().add('l');
					curPoint.getActions().add(action);
					curPoint.getActions().add('f');
					curPoint.setDirection(EAST);
					break;
				case SOUTH:
					curPoint.getActions().add(action);
					curPoint.getActions().add('f');
					curPoint.setDirection(SOUTH);
					break;
				case WEST:
					curPoint.getActions().add('r');
					curPoint.getActions().add(action);
					curPoint.getActions().add('f');
					curPoint.setDirection(WEST);
					break;
			}
		} else if(preDir == WEST) {
			switch(position) {
				case NORTH:
					curPoint.getActions().add('r');
					curPoint.getActions().add(action);
					curPoint.getActions().add('f');
					curPoint.setDirection(NORTH);
					break;
				case EAST:
					curPoint.getActions().add('l');
					curPoint.getActions().add('l');
					curPoint.getActions().add(action);
					curPoint.getActions().add('f');
					curPoint.setDirection(EAST);
					break;
				case SOUTH:
					curPoint.getActions().add('l');
					curPoint.getActions().add(action);
					curPoint.getActions().add('f');
					curPoint.setDirection(SOUTH);
					break;
				case WEST:
					curPoint.getActions().add(action);
					curPoint.getActions().add('f');
					curPoint.setDirection(WEST);
					break;
			}
		}
	}
	
	
	//AstarSearch for land part
	public LinkedList<Character> AstarSearch(int startRow, int startCol, int endRow, int endCol, char[][] viewedMap, int curDirection) {
		int stone = agentState.getNum_stones_held();
		if(agentState.isHave_raft() == true && stone == 0) {
			stone = 9999;
		}
		
		System.out.println("utututu");
		System.out.println("we want to go to " + endRow + " " + endCol);
		int board = viewedMap.length;
		PointState startPoint = new PointState(startRow, startCol, new LinkedList<Character>());
		startPoint.setDirection(curDirection);
		startPoint.setfCost(0);
		startPoint.setgCost(0);
		startPoint.sethCost(0);
		LinkedList<PointState> queue = new LinkedList<PointState>();
		LinkedList<PointState> viewed = new LinkedList<PointState>();
		LinkedList<PointState> toAdd = new LinkedList<PointState>();
		queue.add(startPoint);
		while(!queue.isEmpty()) {
			PointState curr = priorityQueue(queue);
			queue.remove(curr);
			int exist1 = 0;
			for(PointState a: viewed) {
				if(curr.getCurRow() == a.getCurRow() && curr.getCurCol() == a.getCurCol()) {
					exist1 = 1;
					break;
				}
			}
			if(exist1 == 0) {
				viewed.add(curr);
			} else {
				continue;
			}
			System.out.println("this point row: " + curr.getCurRow() + " this point Col: " + curr.getCurCol());
			if(curr.getCurRow() == endRow && curr.getCurCol() == endCol) {
				if(viewedMap[curr.getCurRow()][curr.getCurCol()] == '~') {
					Item topRight = new Item(curr.getCurRow()-2,curr.getCurCol()+2,"tr");
					Item topLeft = new Item(curr.getCurRow()-2,curr.getCurCol()-2,"tl");
					Item bottomRight = new Item(curr.getCurRow()+2,curr.getCurCol()+2,"br");
					Item bottomLeft = new Item(curr.getCurRow()+2,curr.getCurCol()-2,"bl");
					this.agentState.getWaterExplore().add(topRight);
					this.agentState.getWaterExplore().add(topLeft);
					this.agentState.getWaterExplore().add(bottomLeft);
					this.agentState.getWaterExplore().add(bottomRight);
				}
				return curr.getActions();
			} else {
				toAdd.removeAll(toAdd);
				//check it is in map
				//north
				if(curr.getCurRow()-1 >= 0) {
					switch(viewedMap[curr.getCurRow()-1][curr.getCurCol()]) {
						case ' ': case 'k': case 'a': case 'o': case '$':
							System.out.println("add hee");
							PointState northPoint = new PointState(curr.getCurRow()-1,curr.getCurCol(),new LinkedList<Character>());
							toAdd.add(northPoint);
							makeDirection(curr, northPoint, NORTH);
							break;
						case '-':
							System.out.println("adding this point");
							System.out.println(agentState.isHave_key());
							if(agentState.isHave_key() == true) {
								PointState northPoint2 = new PointState(curr.getCurRow()-1,curr.getCurCol(), new LinkedList<Character>());
								toAdd.add(northPoint2);
								unlock_chop(curr, northPoint2, NORTH,'u');
							}
							break;
						case 'T':
							System.out.println(agentState.isHave_axe());
							if(agentState.isHave_axe() == true) {
								PointState northPoint2 = new PointState(curr.getCurRow()-1,curr.getCurCol(), new LinkedList<Character>());
								toAdd.add(northPoint2);
								unlock_chop(curr, northPoint2, NORTH,'c');
							}
							break;
						case '~':
							if(curr.getCurRow()-1 == endRow && curr.getCurCol() == endCol) {
								PointState north3Point = new PointState(curr.getCurRow()-1,curr.getCurCol(),new LinkedList<Character>());
								toAdd.add(north3Point);
								makeDirection(curr, north3Point, NORTH);
							}
							break;
						default:
							break;
					}
					
				}
				//east
				if(curr.getCurCol()+1 < board) {
					switch(viewedMap[curr.getCurRow()][curr.getCurCol()+1]) {
						case ' ': case 'k': case 'a': case 'o': case '$':
							PointState eastPoint = new PointState(curr.getCurRow(),curr.getCurCol()+1,new LinkedList<Character>());
							toAdd.add(eastPoint);
							makeDirection(curr, eastPoint, EAST);
							break;
						case '-':
							if(agentState.isHave_key() == true) {
								PointState east2Point = new PointState(curr.getCurRow(),curr.getCurCol()+1,new LinkedList<Character>());
								toAdd.add(east2Point);
								unlock_chop(curr, east2Point, EAST,'u');
							}
							break;
						case 'T':
							System.out.println(agentState.isHave_axe());
							if(agentState.isHave_axe() == true) {
								PointState eastPoint2 = new PointState(curr.getCurRow(),curr.getCurCol()+1, new LinkedList<Character>());
								toAdd.add(eastPoint2);
								unlock_chop(curr, eastPoint2, EAST,'c');
							}
							break;
						case '~':
							if(curr.getCurRow() == endRow && curr.getCurCol()+1 == endCol) {
								PointState east3Point = new PointState(curr.getCurRow(),curr.getCurCol()+1,new LinkedList<Character>());
								toAdd.add(east3Point);
								makeDirection(curr, east3Point, EAST);
							}
							break;
						default:
							break;
					}		
				}
				//south
				if(curr.getCurRow()+1 < board) {
					switch(viewedMap[curr.getCurRow()+1][curr.getCurCol()]) {
						case ' ': case 'k': case 'a': case 'o': case '$':
							PointState southPoint = new PointState(curr.getCurRow()+1,curr.getCurCol(),new LinkedList<Character>());
							toAdd.add(southPoint);
							makeDirection(curr, southPoint, SOUTH);
							break;
						case '-':
							if(agentState.isHave_key() == true) {
								PointState south2Point = new PointState(curr.getCurRow()+1,curr.getCurCol(),new LinkedList<Character>());
								toAdd.add(south2Point);
								unlock_chop(curr, south2Point, SOUTH,'u');
							}
							break;
						case 'T':
							System.out.println(agentState.isHave_axe());
							if(agentState.isHave_axe() == true) {
								PointState southPoint2 = new PointState(curr.getCurRow()+1,curr.getCurCol(), new LinkedList<Character>());
								toAdd.add(southPoint2);
								unlock_chop(curr, southPoint2, SOUTH,'c');
							}
							break;
						case '~':
							if(curr.getCurRow()+1 == endRow && curr.getCurCol() == endCol) {
								PointState south3Point = new PointState(curr.getCurRow()+1,curr.getCurCol(),new LinkedList<Character>());
								toAdd.add(south3Point);
								makeDirection(curr, south3Point, SOUTH);
							}
							break;
						default:
							break;
					}
				}
				//west
				if(curr.getCurCol()-1 >= 0) {
					switch(viewedMap[curr.getCurRow()][curr.getCurCol()-1]) {
						case ' ': case 'k': case 'a': case 'o': case '$':
							PointState westPoint = new PointState(curr.getCurRow(),curr.getCurCol()-1,new LinkedList<Character>());
							toAdd.add(westPoint);
							makeDirection(curr, westPoint, WEST);
							break;
						case '-':
							if(agentState.isHave_key() == true) {
								PointState west2Point = new PointState(curr.getCurRow(),curr.getCurCol()-1,new LinkedList<Character>());
								toAdd.add(west2Point);
								unlock_chop(curr, west2Point, WEST,'u');
							}
							break;
						case 'T':
							System.out.println(agentState.isHave_axe());
							if(agentState.isHave_axe() == true) {
								PointState westPoint2 = new PointState(curr.getCurRow(),curr.getCurCol()-1, new LinkedList<Character>());
								toAdd.add(westPoint2);
								unlock_chop(curr, westPoint2, WEST,'c');
							}
							break;
						case '~':
							if(curr.getCurRow() == endRow && curr.getCurCol()-1 == endCol) {
								PointState west3Point = new PointState(curr.getCurRow(),curr.getCurCol()-1,new LinkedList<Character>());
								toAdd.add(west3Point);
								makeDirection(curr, west3Point, WEST);
							}
							break;
						default:
							break;
					}
				}
				for(PointState addEle: toAdd) {
					int add = 1;
					for(PointState check: viewed) {
						if(addEle.getCurRow() == check.getCurRow() && addEle.getCurCol() == check.getCurCol()) {
							add = 0;
						}
					}
					if(add == 1) {
						addEle.setgCost(Math.sqrt(Math.pow(endRow-addEle.getCurRow(), 2) + Math.pow(endCol-addEle.getCurCol(), 2)));
						addEle.sethCost(Math.abs(endRow-addEle.getCurRow()) + Math.abs(endCol-addEle.getCurCol()));
						addEle.setfCost(addEle.getgCost());
						queue.add(addEle);
						System.out.println(addEle.getCurRow()+" " + addEle.getCurCol() + " added");
					}
				}
			}
			
		}
		System.out.println("Fail!!!!!!!!!!!!!");
		LinkedList<Character> fail = new LinkedList<Character>();
		fail.add('?');
		return fail;
	}
	
	//AstarSearch for water part
	public LinkedList<Character> AstarSearchWater(int startRow, int startCol, int endRow, int endCol, char[][] viewedMap, int curDirection) {
		int board = viewedMap.length;
		PointState startPoint = new PointState(startRow, startCol, new LinkedList<Character>());
		startPoint.setDirection(curDirection);
		startPoint.setfCost(0);
		startPoint.setgCost(0);
		startPoint.sethCost(0);
		LinkedList<PointState> queue = new LinkedList<PointState>();
		LinkedList<PointState> viewed = new LinkedList<PointState>();
		LinkedList<PointState> toAdd = new LinkedList<PointState>();
		queue.add(startPoint);
		while(!queue.isEmpty()) {
			PointState curr = priorityQueue(queue);
			queue.remove(curr);
			int exist1 = 0;
			for(PointState a: viewed) {
				if(curr.getCurRow() == a.getCurRow() && curr.getCurCol() == a.getCurCol()) {
					exist1 = 1;
					break;
				}
			}
			if(exist1 == 0) {
				viewed.add(curr);
			} else {
				continue;
			}
			if(curr.getCurRow() == endRow && curr.getCurCol() == endCol) {
				return curr.getActions();
			} else {
				toAdd.removeAll(toAdd);
				
				//north
				if(curr.getCurRow()-1 >= 0) {
					switch(viewedMap[curr.getCurRow()-1][curr.getCurCol()]) {
						case '~':
							PointState northPoint = new PointState(curr.getCurRow()-1,curr.getCurCol(),new LinkedList<Character>());
							toAdd.add(northPoint);
							makeDirection(curr, northPoint, NORTH);
							break;
						default:
							break;
					}
				}
				//east
				if(curr.getCurCol()+1 < board) {
					switch(viewedMap[curr.getCurRow()][curr.getCurCol()+1]) {
						case '~':
							PointState eastPoint = new PointState(curr.getCurRow(),curr.getCurCol()+1,new LinkedList<Character>());
							toAdd.add(eastPoint);
							makeDirection(curr, eastPoint, EAST);
							break;
						default:
							break;
					}
				}
				//south
				if(curr.getCurRow()+1 < board) {
					switch(viewedMap[curr.getCurRow()+1][curr.getCurCol()]) {
						case '~':
							PointState southPoint = new PointState(curr.getCurRow()+1,curr.getCurCol(),new LinkedList<Character>());
							toAdd.add(southPoint);
							makeDirection(curr, southPoint, SOUTH);
							break;
						default:
							break;
					}
				}
				//west
				if(curr.getCurCol()-1 >= 0) {
					switch(viewedMap[curr.getCurRow()][curr.getCurCol()-1]) {
						case '~':
							PointState westPoint = new PointState(curr.getCurRow(),curr.getCurCol()-1,new LinkedList<Character>());
							toAdd.add(westPoint);
							makeDirection(curr, westPoint, WEST);
							break;
						default:
							break;
					}
				}
				for(PointState addEle: toAdd) {
					int add = 1;
					for(PointState check: viewed) {
						if(addEle.getCurRow() == check.getCurRow() && addEle.getCurCol() == check.getCurCol()) {
							add = 0;
						}
					}
					if(add == 1) {
						addEle.setgCost(Math.sqrt(Math.pow(endRow-addEle.getCurRow(), 2) + Math.pow(endCol-addEle.getCurCol(), 2)));
						addEle.sethCost(Math.abs(endRow-addEle.getCurRow()) + Math.abs(endCol-addEle.getCurCol()));
						addEle.setfCost(addEle.getgCost());
						queue.add(addEle);
						System.out.println(addEle.getCurRow()+" " + addEle.getCurCol() + " added");
					}
				}
			}
		
		}
		System.out.println("Fail!!!!!!!!!!!!!");
		LinkedList<Character> fail = new LinkedList<Character>();
		fail.add('?');
		return fail;
	}
}
