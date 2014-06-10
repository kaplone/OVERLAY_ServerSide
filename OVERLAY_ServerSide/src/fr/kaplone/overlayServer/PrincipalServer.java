package fr.kaplone.overlayServer;

import java.io.IOException;
import java.util.ArrayList;

import fr.kaplone.overlayServerUtils.Deltas;
import fr.kaplone.overlayServerUtils.Displacement;
import fr.kaplone.overlayServerUtils.ParseStreamUtils;
import fr.kaplone.serverSourceUtils.*;
import fr.kaplone.overlayServerUtils.OpenSocketUtil;


public class PrincipalServer {

	public static void main(String[] args) {
		
		
		
		
		// here we store and initialize device objects.
		// TODO : load serialized objects at startup and give an interface to add new items.
		
		ServerSideHands IPhoneF = new ServerSideHands(new ServerSideRightHand(1.0, new Position(1550, 440)), new ServerSideDevice(1.0 , new double [] {788.0, 451.0}, new double[] {382.0, 677.0}));
		ServerSideHands NexusH = new ServerSideHands(new ServerSideRightHand(1.0, new Position(2750, 1050)), new ServerSideDevice(1.0, new double [] {2590.0, 307.0}, new double[] {998, 1762}));
		
		// Init fields
		
		int speedup = 20;  // greater is faster
		
		ArrayList<Position> allKeyPositions = new ArrayList<Position>();
		ParseStreamUtils.StreamToPositionArray(allKeyPositions);
	    
		//TODO : Chercher/Corriger : la valeur x de restPoint est doublée dans la sortie
		Position restPoint = IPhoneF.restPosition();
		
		
        
        // The model is this old one :
        //
        // - Positions of touches are the only known informations.
        //   So we have to compute the Position (x, y and frame) where the move starts
        //   and the one where the move ends
        
        ArrayList<Position> PP = new ArrayList<Position>();

        for (int i = 0; i < allKeyPositions.size(); i++){
        	switch (allKeyPositions.get(i).getPreviousPosition()){
        	     // from restPosition ...
	             case 0 : Deltas d0_ = Displacement.acceleration(restPoint, allKeyPositions.get(i), speedup);
    	    	     switch (allKeyPositions.get(i).getNextPosition()){
    	    	        // ... to restPosition
    	                case 0 : Deltas d01 = Displacement.acceleration(allKeyPositions.get(i),restPoint,  speedup);
    	                         PP.addAll(Displacement.deplacement(d0_,restPoint, allKeyPositions.get(i), restPoint, d01));
    	                         break;
    	                // ... stay over the screen
    	                case 1 : PP.addAll(Displacement.deplacement(d0_,restPoint, allKeyPositions.get(i)));
                                 break;
    	    	     }
    	    	     break;
    	    	     
    	    	 // from previous position ...
    	         case 1 : Deltas d1_ = Displacement.acceleration(allKeyPositions.get(i-1), allKeyPositions.get(i), speedup);
    	        	 switch (allKeyPositions.get(i).getNextPosition()){
    	        	    // ... to restPosition
	 	                case 0 : Deltas d10 = Displacement.acceleration(allKeyPositions.get(i),restPoint,  speedup);
	 	                         PP.addAll(Displacement.deplacement(d1_,allKeyPositions.get(i-1), allKeyPositions.get(i), restPoint, d10));
	 	                         break;
	 	                // ... stay on the screen
	 	                case 1 : PP.addAll(Displacement.deplacement(d1_,allKeyPositions.get(i-1), allKeyPositions.get(i)));
	                              break;
	 	    	     }
        	}

        }
        
        // print the Position computed and ready to send to client
        
        String retour = "";
        for (Position p : PP){
			retour += p.getImageNumber() + " " + p.getCoordX() + " " + p.getCoordY() + "\n";
        } 
        try {
			OpenSocketUtil.sendViaSocket(retour);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
