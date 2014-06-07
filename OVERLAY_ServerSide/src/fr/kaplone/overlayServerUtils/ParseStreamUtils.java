package fr.kaplone.overlayServerUtils;

import java.io.IOException;
import java.util.ArrayList;

import fr.kaplone.serverSourceUtils.Position;

public class ParseStreamUtils {
	
	public static void StreamToPositionArray (ArrayList<Position> allPositions){
	
	// catch an incoming stream returned as Integer structure.
			// parse it as a list of Positions
	    
	        try {
	        	ArrayList<ArrayList<Integer>> myArray = OpenSocketUtil.openSocket();
	        	for (int i= 0; i < myArray.size(); i++){
	        		allPositions.add(new Position(myArray.get(i).get(1),
	        				                   myArray.get(i).get(2),
	        				                   null,
	        				                   myArray.get(i).get(0),
	        				                   myArray.get(i).get(3),
	        				                   myArray.get(i).get(4)
	        				                   ));
	        	}	
	        	
			} catch (IOException e) {
				System.out.println("Error in main loop");
				e.printStackTrace();
			}
	}

}
