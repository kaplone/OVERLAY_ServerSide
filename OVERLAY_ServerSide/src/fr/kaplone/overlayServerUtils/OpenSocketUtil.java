package fr.kaplone.overlayServerUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.StringReader;

public class OpenSocketUtil {

	static ServerSocket s;
	static MySocket sc;
	static OutputStream sco;
	static BufferedReader sci;

	public static ArrayList<ArrayList<Integer>> openSocket() throws IOException {

		s = new ServerSocket(8095);
		sc = new MySocket(s);
		sci = sc.getInReader();

		int z;
		ArrayList<ArrayList<Integer>> ca = null;
		ArrayList<Integer> cl;
		ArrayList<Integer> datas = new ArrayList<Integer>();

		boolean boucle = true;
		int lecture;
		Scanner scan = null;
		ca = new ArrayList<ArrayList<Integer>>();
		cl = new ArrayList<Integer>();
		StringBuilder result = new StringBuilder(datas.size());

		while (boucle){
			lecture = sci.read();

			if(lecture != -1){
				System.out.println("debut de la lecture coté serveur : ");
				
				while (true){
					if (lecture == 65533) break;
					result.append(Character.toChars(lecture)[0]);
					lecture = sci.read();
				}
			    
				boucle = false;
				String lectureString = result.toString();
				
				BufferedReader lignes = new BufferedReader(new StringReader(lectureString));
				
				String ligne = lignes.readLine();
				while (ligne != null){
					scan = new Scanner(ligne);
					while (scan.hasNextInt()){
						z = scan.nextInt();
						cl.add(z);
					}
					ca.add(cl);   
					ligne = lignes.readLine();
					cl = new ArrayList<Integer>();
					scan.close();
				}
				//break;
			}
			System.out.println("cloture de la reception des datas sur le serveur");
			scan.close();
			boucle = false; 

		}
		return ca;  
	}

	public static void sendViaSocket(String retour) throws IOException{

		sco = sc.getSocketOutputStream();
		byte[] data = retour.getBytes();
		boolean boucle = true;

		for (int i = 0; i < data.length; i++){
			sco.write(data[i]);
		}
		sco.write(-1);
		sco.flush();
	}
}
