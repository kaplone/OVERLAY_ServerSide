package fr.kaplone.overlayServer;

import java.io.IOException;
import java.util.ArrayList;

import fr.kaplone.overlayServerUtils.Deltas;
import fr.kaplone.overlayServerUtils.Displacement;
import fr.kaplone.overlayServerUtils.ParseStreamUtils;
import fr.kaplone.serverSourceUtils.*;
import fr.kaplone.overlayServerUtils.OpenSocketUtil;


public class PrincipalServer {

	public static void main(String[] args) throws IOException {
	
		// here we store and initialize device objects.
		// TODO : load serialized objects at startup and give an access to add/select item.
		
		
		ServerSideRightHand IPhoneF_RH = new ServerSideRightHand(new double [] {3744, 5136},
				                                                 1100,
				                                                 new Position(1550, 440));
		
		ServerSideDevice IPhoneF_Dev = new ServerSideDevice(new double [] {1920, 2000},
				                                            386,
				                                            new double [] {788.0, 451.0}, 
                                                            new double [] {382.0, 677.0});
		
		ServerSideRightHand NexusH_RH = new ServerSideRightHand(new double [] {5616, 3744},
				                                                770,
				                                                new Position(2750, 1050));
		
		ServerSideDevice NexusH_dev = new ServerSideDevice(new double [] {5616, 3744},
				                                           770 ,
				                                           new double [] {2590.0, 307.0},
				                                           new double[] {998, 1762});
		
		ServerSideBackground BG = new ServerSideBackground(new double [] {9000, 7500});
		
		ServerSideOverlay IPhoneF = new ServerSideOverlay(IPhoneF_RH, IPhoneF_Dev, BG).scaledServerSideOverlay();

		ServerSideOverlay NexusH = new ServerSideOverlay(NexusH_RH, NexusH_dev, BG).scaledServerSideOverlay();
		
		// Init fields
		
		int speedup = 20;  // greater moves faster
		
		ArrayList<Position> allKeyPositions = new ArrayList<Position>();
		
		ParseStreamUtils.StreamToPositionArray(allKeyPositions);

		Position restPoint = IPhoneF.getRestPosition();	
		ServerSideOverlay overlay = IPhoneF;
        
        // The model is a new one :
        //
        // - Positions of touches are the only known informations.
        //   So we have to compute the Position (x, y and frame) where the move starts
        //   and the one where the move ends
        
        ArrayList<Position> PP = new ArrayList<Position>();
        Position newPos;
        double [] offset = overlay.getDevice().getOffset();

        for (int i = 0; i < allKeyPositions.size(); i++){
        	
        	newPos = allKeyPositions.get(i).computeNewPosition(offset);
        	
        	switch (newPos.getPreviousPosition()){
        	     // from restPosition ...
	             case 0 : Deltas d0_ = Displacement.acceleration(restPoint, newPos, speedup);
    	    	     switch (newPos.getNextPosition()){
    	    	        // ... to restPosition
    	                case 0 : Deltas d01 = Displacement.acceleration(newPos,restPoint,  speedup);
    	                         PP.addAll(Displacement.deplacement(d0_,restPoint, newPos, restPoint, d01));
    	                         break;
    	                // ... stay over the screen
    	                case 1 : PP.addAll(Displacement.deplacement(d0_,restPoint, newPos));
                                 break;
    	    	     }
    	    	     break;
    	    	     
    	    	 // from previous position ...
   	    	     
    	         case 1 : Position newPreviousPos = allKeyPositions.get(i-1).computeNewPosition(offset);
    	        	 Deltas d1_ = Displacement.acceleration(newPreviousPos, newPos, speedup);
    	        	 switch (newPos.getNextPosition()){
    	        	    // ... to restPosition
	 	                case 0 : Deltas d10 = Displacement.acceleration(newPos,restPoint,  speedup);
	 	                         PP.addAll(Displacement.deplacement(d1_,newPreviousPos, newPos, restPoint, d10));
	 	                         break;
	 	                // ... stay on the screen
	 	                case 1 : PP.addAll(Displacement.deplacement(d1_,newPreviousPos, newPos));
	                              break;
	 	    	     }
        	}

        }
        
        // print the Position computed and ready to send to client
        
        String retour = "";
        for (Position p : PP){
			retour += Math.round(p.getImageNumber()) + " " + Math.round(p.getCoordX()) + " " + Math.round(p.getCoordY()) + "\n";
        } 
        System.out.println(retour);
        OpenSocketUtil.sendViaSocket(retour);
	}
}
