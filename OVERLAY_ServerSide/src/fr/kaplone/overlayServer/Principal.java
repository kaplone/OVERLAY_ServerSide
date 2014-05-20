package fr.kaplone.overlayServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import fr.kaplone.overlayServerUtils.Deltas;
import fr.kaplone.overlayServerUtils.OpenSocketUtil;
import fr.kaplone.overlayServerUtils.ParseFileUtils;
import fr.kaplone.overlayServerUtils.Displacement;
import fr.kaplone.sourceUtils.Point;


public class Principal {

	public static void main(String[] args) {
        
        try {
        	ArrayList<ArrayList<Integer>> myArray = OpenSocketUtil.openSocket();
        	for (int i= 0; i < myArray.size(); i++){
        		for (int j= 0; j < myArray.get(i).size(); j++){
        			System.out.print(myArray.get(i).get(j) + " ");
        		}
        		System.out.println("");
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
//			tousLesTemps = ParseFileUtils.fileToFrameNumber("/home/david/TESTS_racket/move/ref.txt");
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
