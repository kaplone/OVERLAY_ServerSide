package fr.kaplone.overlayServer;

import java.util.ArrayList;

import fr.kaplone.overlayServerUtils.Deltas;
import fr.kaplone.overlayServerUtils.Displacement;
import fr.kaplone.overlayServerUtils.ParseStreamUtils;
import fr.kaplone.serverSourceUtils.*;


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
	    
		//TODO : Corriger : la valeur x de restPoint est doublée dans la sortie
		Position restPoint = IPhoneF.restPosition();
		
		
        
        // The model is this old one :
        //
        // - Positions of touches are the only known informations.
        //   So we have to compute the Position (x, y and frame) where the move starts
        //   and the one where the move ends
        
        ArrayList<Position> PP = new ArrayList<Position>();

        for (int i = 0; i < allKeyPositions.size(); i++){
        	switch (allKeyPositions.get(i).getNextPosition()){
        	    case 0 : try {
	        	    	     Deltas d0 = Displacement.acceleration(restPoint,allKeyPositions.get(i), speedup);
	        	             Deltas d1 = Displacement.acceleration(allKeyPositions.get(i+1),restPoint,  speedup);
	        	             PP.addAll(Displacement.deplacement(d0,restPoint, allKeyPositions.get(i), restPoint, d1));
                         }catch (IndexOutOfBoundsException ioobe){
                        	 Deltas d0 = Displacement.acceleration(allKeyPositions.get(i), restPoint, speedup);
            	             Deltas d1 = Displacement.acceleration(restPoint, restPoint, speedup);
            	             PP.addAll(Displacement.deplacement(d0, allKeyPositions.get(i), restPoint, restPoint, d1));
                         }
        	             break;
        	             
        	    //TODO :  cas 1 incomplet : ne correspond pas au modele         
        	    case 1 : Deltas d = Displacement.acceleration(allKeyPositions.get(i), allKeyPositions.get(i+1), speedup);
        	             break;
        	    default : break;
        	}
        }
        
        // print the Position computed and ready to send to client
        // (Actually heavily bugged)
        
        for (Position p : PP){
			System.out.println(p.getImageNumber() + " " + p.getCoordX() + " " + p.getCoordY());
        }       
        
	}

////////////// code précédent : gardé comme note de travail /////////////////////////      
		     

//		Point p1 = new Point(0, 0);
//		Point p2 = new Point(400, 500, null, 500);
//		Point p3 = new Point(100, 800);
//		
//		
//		Deltas D0 = Displacement.acceleration(p1, p2, 4);
//		Deltas D1 = Displacement.acceleration(p2, p3, 2);
//		ArrayList<Point> PP = Displacement.deplacement(D0, p1, p2, p3, D1);
//		for (Point p : PP){
//			System.out.println(p.getImageNumber() + " " + p.getCoordX() + " " + p.getCoordY());
//		}
//		
//		ArrayList<Integer> tousLesTemps;
//		try {
//			tousLesTemps = ParseFileUtils.fileToFrameNumber("ref.txt");
//			System.out.println(tousLesTemps.get(8));
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		ArrayList<Integer> tousLesPosX;
//		
//		try {
//			tousLesPosX = ParseFileUtils.fileToPosX("/home/david/TESTS_racket/move/ref.txt");
//			System.out.println(tousLesPosX.get(8));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	}
//////////////////////////////////////////////////////////////////////////////////////////////
}
