package fr.kaplone.overlayServerUtils;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Scanner;

public class OpenSocketUtil {
	
	static ServerSocket s;
	static MySocket sc;
	
	public static ArrayList<ArrayList<Integer>> openSocket() throws IOException {
		
		s = new ServerSocket(8095);
    	sc = new MySocket(s);
    	
        Scanner sist = new Scanner(sc.getInStream());
        ArrayList<ArrayList<Integer>> ca = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> cl = new ArrayList<Integer>();
        Scanner sist2;
		while (sist.hasNextLine()){
			sist2 = new Scanner(sist.nextLine());
			while (sist2.hasNextInt()){
				cl.add(sist2.nextInt());
			}
			ca.add(cl);
			cl = new ArrayList<Integer>();
			sist2.close();
		}
		sist.close();
		
		return ca;
    }
	
	public static void sendViaSocket(String retour) throws IOException{
		
		System.out.println("envoi du retour");

		PrintStream sco = sc.getOutStream();
		sco.print(retour);
		sco.flush();
		sco.close();
		s.close();
	}
	
	

}
