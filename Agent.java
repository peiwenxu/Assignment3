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

	  final static int EAST   = 0;
	  final static int NORTH  = 1;
	  final static int WEST   = 2;
	  final static int SOUTH  = 3;
	  private boolean have_axe     = false;
	  private boolean have_key     = false;
	  private boolean have_treasure= false;
	  private boolean have_raft    = false;
	  private boolean on_raft      = false;
	  private boolean off_map      = false;
	  private int num_stones_held = 0;
	  
	  
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

  void print_view( char view[][] )
  {
    int i,j;

    System.out.println("\n+-----+");
    for( i=0; i < 5; i++ ) {
      System.out.print("|");
      for( j=0; j < 5; j++ ) {
        if(( i == 2 )&&( j == 2 )) {
          System.out.print('^');
        }
        else {
          System.out.print( view[i][j] );
        }
      }
      System.out.println("|");
    }
    System.out.println("+-----+");
  }

  public static void main( String[] args )
  {
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
    ViewedMap updateView = new ViewedMap();
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
        				case 'a':
        					agent.have_axe = true;
        					curRow -= 1;
        				case '$':
        					agent.have_treasure = true;
        					curRow -= 1;
        				case 'o':
        					agent.num_stones_held++;
        					curRow -= 1;
        				default:
        					curRow -= 1;
        			}
        		} else if(direction == SOUTH) {
        			switch(viewedMap[curRow+1][curCol]) {
	    				case '*':case'T':case'-':
	    					break;
	    				case 'k':
	    					agent.have_key = true;
	    					curRow += 1;
	    				case 'a':
	    					agent.have_axe = true;
	    					curRow += 1;
	    				case '$':
	    					agent.have_treasure = true;
	    					curRow += 1;
	    				case 'o':
	    					agent.num_stones_held++;
	    					curRow += 1;
    					default:
    						curRow += 1;
        			}
        		} else if(direction == EAST) {
        			switch(viewedMap[curRow][curCol+1]) {
	    				case '*':case'T':case'-':
	    					break;
	    				case 'k':
	    					agent.have_key = true;
	    					curCol += 1;
	    				case 'a':
	    					agent.have_axe = true;
	    					curCol += 1;
	    				case '$':
	    					agent.have_treasure = true;
	    					curCol += 1;
	    				case 'o':
	    					agent.num_stones_held++;
	    					curCol += 1;
    					default:
    						curCol += 1;
        			}
        		} else if(direction == WEST) {
        			switch(viewedMap[curRow][curCol-1]) {
	    				case '*':case'T':case'-':
	    					break;
	    				case 'k':
	    					agent.have_key = true;
	    					curCol -= 1;
	    				case 'a':
	    					agent.have_axe = true;
	    					curCol -= 1;
	    				case '$':
	    					agent.have_treasure = true;
	    					curCol -= 1;
	    				case 'o':
	    					agent.num_stones_held++;
	    					curCol -= 1;
    					default:
    						curCol -= 1;
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
      catch( IOException e ) {}
    }
  }
}
