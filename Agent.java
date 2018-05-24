/*********************************************
 *  Agent.java
 *  Sample Agent for Text-Based Adventure Game
 *  COMP3411/9414/9814 Artificial Intelligence
 *  UNSW Session 1, 2018
*/

import java.util.*;
import java.io.*;
import java.net.*;

public class Agent {

	final static int EAST             			       = 0;
	final static int NORTH  						 = 1;
	final static int WEST 					          = 2;
	final static int SOUTH  					   	 = 3;
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

	public char get_action( char view[][] ) {
	
	    // REPLACE THIS CODE WITH AI TO CHOOSE ACTION
	
	    int ch=0;
	
	    System.out.print("Enter Action(s): ");
	
	    try {
	    	while ( ch != -1 ) {
	        // read character from keyboard
		        ch  = System.in.read();
	
		        switch( ch ) { // if character is a valid action, return it
		        	case 'F': case 'L': case 'R': case 'C': case 'U':
		        	case 'f': case 'l': case 'r': case 'c': case 'u':
		        	return((char) ch );
	        	}
	     	}
	    }
	    catch (IOException e) {
	    	System.out.println ("IO error:" + e );
	    }
	
		return 0;
	}
	
	void print_view( char view[][] ){
	    int i,j;
	
	    System.out.println("\n+-----+");
	    for( i=0; i < 5; i++ ) {
	  		System.out.print("|");
			for( j=0; j < 5; j++ ) {
		        if(( i == 2 )&&( j == 2 )) {
					System.out.print('^');
		        } else {
					System.out.print( view[i][j] );
		        }
	      	}
	      	System.out.println("|");
	    }
	    System.out.println("+-----+");
	}
	
	public static void main( String[] args ){
		InputStream in  = null;
		OutputStream out= null;
		Socket socket   = null;
		Agent  agent    = new Agent();
		char   view[][] = new char[5][5];
		char viewedMap[][] = new char[80][80];
		char   action   = 0;
		int port;
		int ch;
		int i,j;
		int initialed = 0;
		int startCol = viewedMap.length/2;
		int startRow = viewedMap.length/2;
		int curRow = startRow;
		int curCol = startCol;
		int direction = NORTH;
		ViewedMap updateView = new ViewedMap(agent);
		if( args.length < 2 ) {
			System.out.println("Usage: java Agent -p <port>\n");
			System.exit(-1);
		}
	
	    port = Integer.parseInt( args[1] );
	    try { // open socket to Game Engine
			socket = new Socket( "localhost", port );
			in  = socket.getInputStream();
			out = socket.getOutputStream();
	    }
	    catch( IOException e ) {
			System.out.println("Could not bind to port: "+port);
			System.exit(-1);
	    }
	
	    try { // scan 5-by-5 wintow around current location
	      	while( true ) {
		        for( i=0; i < 5; i++ ) {
			        for( j=0; j < 5; j++ ) {
			            if( !(( i == 2 )&&( j == 2 ))) {
			              	ch = in.read();
							if( ch == -1 ) {
								System.exit(-1);
							}
			              	view[i][j] = (char) ch;
			            }
			        }
		        }
		        agent.print_view( view ); // COMMENT THIS OUT BEFORE SUBMISSION
		        updateView.updateView(direction, action, viewedMap, view, curRow, curCol);
		        //initial
		        if(initialed == 0) {
	        		updateView.initialMap(view, viewedMap);
	        		initialed = 1;
		        }
		        System.out.println("here");
		        updateView.printViewedMap(viewedMap);
		        System.out.println("CurrentRow: " +curRow);
		        System.out.println("CurrentCol: " +curCol);
		        System.out.println("Keys:");
		        for(int o = 0; o < agent.getKeyList().size(); o++) {
	        		System.out.println("Row: " +agent.getKeyList().get(o).getRow() + " Col: " +agent.getKeyList().get(o).getCol());
		        }
		        System.out.println("Trees:");
		        for(int o = 0; o < agent.getTreeList().size(); o++) {
	        		System.out.println("Row: " +agent.getTreeList().get(o).getRow() +" Col: " + agent.getTreeList().get(o).getCol());
		        }
		        System.out.println("Axes:");
		        for(int o = 0; o < agent.getAxeList().size(); o++) {
	        		System.out.println("Row: " +agent.getAxeList().get(o).getRow() +" Col: " + agent.getAxeList().get(o).getCol());
		        }
		        System.out.println("Stones:");
		        for(int o = 0; o < agent.getStoneList().size(); o++) {
	        		System.out.println("Row: " +agent.getStoneList().get(o).getRow() +" Col: " + agent.getStoneList().get(o).getCol());
		        }
		        System.out.println("Doors:");
		        for(int o = 0; o < agent.getDoorList().size(); o++) {
	        		System.out.println("Row: " +agent.getDoorList().get(o).getRow() +" Col: " + agent.getDoorList().get(o).getCol());
		        }
		        System.out.println("Treasures:");
		        for(int o = 0; o < agent.getTreasureList().size(); o++) {
	        		System.out.println("Row: " +agent.getTreasureList().get(o).getRow() +" Col: " + agent.getTreasureList().get(o).getCol());
		        }
	        	action = agent.get_action( view );
	        	if(action == 'R' || action == 'r') {
	        		if(direction == EAST) {
	        			direction = SOUTH;
	        		} else {
	        			direction -= 1;
	        		}
	        	} else if (action == 'L' || action == 'l') {
	        		if(direction == SOUTH) {
	        			direction = EAST;
	        		} else {
	        			direction += 1;
	        		}
	        	} else if(action == 'F' || action == 'f') {
	        		if(direction == NORTH) {
	        			switch(viewedMap[curRow-1][curCol]) {
	        				case '*':case'T':case'-':
	        					break;
	        				case 'k':
	        					agent.have_key = true;
	        					curRow -= 1;
	        					break;
	        				case 'a':
	        					agent.have_axe = true;
	        					curRow -= 1;
	        					break;
	        				case '$':
	        					agent.have_treasure = true;
	        					curRow -= 1;
	        					break;
	        				case 'o':
	        					agent.num_stones_held++;
	        					curRow -= 1;
	        					break;
	        				default:
	        					curRow -= 1;
	        					break;
	        			}
	        		} else if(direction == SOUTH) {
	        			switch(viewedMap[curRow+1][curCol]) {
		    				case '*':case'T':case'-':
		    					break;
		    				case 'k':
		    					agent.have_key = true;
		    					curRow += 1;
		    					break;
		    				case 'a':
		    					agent.have_axe = true;
		    					curRow += 1;
		    					break;
		    				case '$':
		    					agent.have_treasure = true;
		    					curRow += 1;
		    					break;
		    				case 'o':
		    					agent.num_stones_held++;
		    					curRow += 1;
		    					break;
	    					default:
	    						curRow += 1;
	    						break;
	        			}
	        		} else if(direction == EAST) {
	        			switch(viewedMap[curRow][curCol+1]) {
		    				case '*':case'T':case'-':
		    					break;
		    				case 'k':
		    					agent.have_key = true;
		    					curCol += 1;
		    					break;
		    				case 'a':
		    					agent.have_axe = true;
		    					curCol += 1;
		    					break;
		    				case '$':
		    					agent.have_treasure = true;
		    					curCol += 1;
		    					break;
		    				case 'o':
		    					agent.num_stones_held++;
		    					curCol += 1;
		    					break;
	    					default:
	    						curCol += 1;
	    						break;
	        			}
	        		} else if(direction == WEST) {
	        			switch(viewedMap[curRow][curCol-1]) {
		    				case '*':case'T':case'-':
		    					break;
		    				case 'k':
		    					agent.have_key = true;
		    					curCol -= 1;
		    					break;
		    				case 'a':
		    					agent.have_axe = true;
		    					curCol -= 1;
		    					break;
		    				case '$':
		    					agent.have_treasure = true;
		    					curCol -= 1;
		    					break;
		    				case 'o':
		    					agent.num_stones_held++;
		    					curCol -= 1;
		    					break;
	    					default:
	    						curCol -= 1;
	    						break;
	        			}
	        		}
	        	} else if (action == 'C' || action == 'c' || action == 'U' || action == 'u') {
	
	        	}
	        	out.write( action );
	      	}
	    }
	    catch( IOException e ) {
			System.out.println("Lost connection to port: "+ port );
			System.exit(-1);
	    }
	    finally {
			try {
				socket.close();
			}
	      	catch( IOException e ) {
	    	}
	  	}
	}
}
