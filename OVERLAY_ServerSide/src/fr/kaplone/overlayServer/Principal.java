package fr.kaplone.overlayServer;

import java.io.IOException;
import java.util.ArrayList;

import fr.kaplone.overlayServerUtils.OpenSocketUtil;
import fr.kaplone.overlayServerUtils.ParseFileUtils;
import fr.kaplone.overlayServerUtils.Displacement;
import fr.kaplone.sourceUtils.*;


public class Principal {

	public static void main(String[] args) {
		
		int speedup = 20;
		
		ServerSideHands IPhoneF = new ServerSideHands(new ServerSideRightHand(1.0, new Point(1550, 440)), new ServerSideDevice(1.0 , new double [] {789.0, 451.0}, new double[] {382.0, 677.0}));
		ServerSideHands NexusH = new ServerSideHands(new ServerSideRightHand(1.0, new Point(2750, 1050)), new ServerSideDevice(1.0, new double [] {2590.0, 307.0}, new double[] {998, 1762}));
	
		ArrayList<Point> allPoints = new ArrayList<Point>();
	
    
        try {
        	ArrayList<ArrayList<Integer>> myArray = OpenSocketUtil.openSocket();
        	for (int i= 0; i < myArray.size(); i++){
        		allPoints.add(new Point(myArray.get(i).get(1), myArray.get(i).get(2), null, myArray.get(i).get(0)));
        	}	
		} catch (IOException e) {
			System.out.println("erreur dans la boucle principale");
			e.printStackTrace();
		}
        
	}

        
		     

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

}
