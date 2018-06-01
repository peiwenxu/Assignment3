import java.util.LinkedList;

public class Search {
	final static int EAST             			       = 0;
	final static int NORTH  						 = 1;
	final static int WEST 					          = 2;
	final static int SOUTH  					   	 = 3;
	
	
	Search() {
		
	}
	  
	
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
	
	public void makeDirection(PointState prePoint, PointState curPoint, int position) {
		int preDir = prePoint.getDirection();
		//copy the action into new point
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
	
	
	
	public LinkedList<Character> AstarSearch(int startRow, int startCol, int endRow, int endCol, char[][] viewedMap, int curDirection) {
		System.out.println("utututu");
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
					continue;
				}
			}
			if(exist1 == 0) {
				viewed.add(curr);
			}
			System.out.println("this point row: " + curr.getCurRow() + " this point Col: " + curr.getCurCol());
			if(curr.getCurRow() == endRow && curr.getCurCol() == endCol) {
				return curr.getActions();
			} else {
				toAdd.removeAll(toAdd);
				//check it is in map
				//north
				if(curr.getCurRow()-1 >= 0) {
					switch(viewedMap[curr.getCurRow()-1][curr.getCurCol()]) {
						case ' ': case 'k': case 'a': case 'o': case '$':
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
						case ' ': case 'k': case 'a': case 'o': case '$':
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
						case ' ': case 'k': case 'a': case 'o': case '$':
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
						case ' ': case 'k': case 'a': case 'o': case '$':
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
