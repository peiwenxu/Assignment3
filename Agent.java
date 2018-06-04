/*********************************************
 *  Agent.java 
 *  Sample Agent for Text-Based Adventure Game
 *  COMP3411/9414/9814 Artificial Intelligence
 *  UNSW Session 1, 2018
 *  Group by Peiwen Xu z5098118 and Augustine Hyunwoo Lee z5061885
 *  
 *  Question : Briefly describe how your program works, including any algorithms and data structures employed, and explain any design decisions you made along the way.
 *  
 *  Our program works with 8 other classes which contains data of the Agent, map and ai that makes the action.
 *  
 *  #1, Planning phase
 *  Originally we planned our program to use a* algorithms to find shortest path between the agent and the goal, 
 *  and meanwhile when goal is not found or path to goal is not completed, it will use greedy search to look for all possible path.
 *  	1. Land Phase
 *  		1.1 Agent will initialise it's search within the land only. This means path on water will always have higher value no matter if agent has raft or not
 *  		1.2 Agent will visit all the land nodes to achieve 5x5 sight from every single block.
 *  		1.3 If all the land nodes are visited, use stepping stone if available to check it can go over 1 block of water.
 *  	2. Water Phase
 *  		2.1 After Agent visited all the land and has raft or only axe, then cut the tree to get raft
 *  		2.2 If Agent find the gold just over the few blocks of water and it has a way back, it will start it's journey over the water.
 *  		2.3 Agent will continue to search on water and give high h values on getting back on land if there is no way back to water (Tree to get raft).
 *  		2.4 If tree is found or no more water blocks to search, it will land on other island to start step 1, land phase again.
 *  
 *  #2, Programming the agent and AI
 *  We started off with making AiAction, search, item, coordinate and AgentState classes to store important values to let AiAction to make decisions.
 *  Program will get map info from the Step as it travels. Then it store coordinates of Agent and items as cols and rows. 
 *  We thought it would be nice to implement 2D array. But we left it as 2 variables to store cols and rows.
 *  Agent will search for new block where it can travel and keeps on traveling until it runs out of the option.
 *  during the search if agent face the obstacles like door or trees, it will give higher value to those compare to normal path.
 *  Then it will use Axe or Key if available to unlock or chop it down to make a path.
 *  When we achieve enough map data to the treasure, agent will use a* search to make shortest path from agent to goal.
 *  Then also use a* back towards our starting point. 
 *  When AI is making decision it will consider Agent's current direction and set future moves based on that.
 *  For example if Agent is facing North and it needs to travel 2 block East from current position, 
 *  AI will set future moves as R -> F -> F. Our code became huge when it was completed but it was easier to maintain at the start.
 *  Then we add chop_unlock function to add extra command on future moves. To unlock and chop the tree.
 *  We struggled a lot on search on water path, so eventually it won't work on our code as we expected at planning phase.
*/

import java.util.*;
import java.io.*;
import java.net.*;

public class Agent {
	final static int EAST             			       = 0;
	final static int NORTH  						 = 1;
	final static int WEST 					          = 2;
	final static int SOUTH  					   	 = 3;
	private AiAction aiAction;
	
	
	
  public AiAction getAiAction() {
		return aiAction;
	}

	public void setAiAction(AiAction aiAction) {
		this.aiAction = aiAction;
	}

public char get_action( char[][] view ) {	  
	 char ch = this.aiAction.makeMove();
	 switch( ch ) { // if character is a valid action, return it
	 	case 'F': case 'L': case 'R': case 'C': case 'U':
	 	case 'f': case 'l': case 'r': case 'c': case 'u':
	 		return((char) ch );
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
    char   action   = 0;
    int port;
    int ch;
    int i,j;
    AgentState agentState = new AgentState();
    agent.setAiAction(new AiAction(agentState));
    ViewedMap updateView = new ViewedMap(agentState);
    Search search = new Search(agentState);
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
        updateView.updateView(agentState.getDirection(),action, agentState.getViewedMap(), view, agentState.getCurRow(), agentState.getCurCol());
        if(agentState.getInitialed() == 0) {
			updateView.initialMap(view, agentState.getViewedMap());
			agentState.setInitialed(1);
        }
//        System.out.println("here");
//        updateView.printViewedMap(agentState.getViewedMap());
//        updateView.printAgentDetail(); 
        action = agent.get_action( view);
	    //	System.out.println(action);
	    	out.write( action );
        updateView.updateAgentDetail(action);
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