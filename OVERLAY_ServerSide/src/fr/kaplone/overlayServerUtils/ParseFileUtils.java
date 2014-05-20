package fr.kaplone.overlayServerUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ParseFileUtils {
	
	
	public static ArrayList<String> fileToLines (String cheminDuFichier) throws IOException{
		ArrayList<String> toutesLesLignes = new ArrayList<String>();
		
        Path path = Paths.get(cheminDuFichier);
        Scanner scanner = new Scanner(path);
         
        //read file line by line
        scanner.useDelimiter(System.getProperty("line.separator"));
        while(scanner.hasNext()){
        	toutesLesLignes.add(scanner.next());
        }
        scanner.close();
		return toutesLesLignes;
	}
	
	public static ArrayList<Integer> lignesVersEntiers (ArrayList<String> lignes){
		ArrayList<Integer> tousLesChamps = new ArrayList<Integer>();
		for (int i = 0; i < lignes.size(); i++){
			for (int j = 0; j < lignes.get(i).split(" ").length; j++){
				tousLesChamps.add(Integer.valueOf(lignes.get(i).split(" ")[j]));
			}
		}
		return tousLesChamps;
	}
	
	public static ArrayList<Integer> fileToInteger (String fichier) throws IOException{
		return lignesVersEntiers(fileToLines( fichier));
	}
	
	public static ArrayList<Integer> fileToFrameNumber(String fichier) throws IOException{
		ArrayList<Integer> tousLesTemps = new ArrayList<Integer>();
		ArrayList<Integer> tousLesChamps = fileToInteger(fichier);
		for (int i = 0; i < tousLesChamps.size(); i++){
			if (i%3 == 0){
				tousLesTemps.add(Integer.valueOf(tousLesChamps.get(i)));
			}
		}
		return tousLesTemps;
	}

	public static ArrayList<Integer> fileToPosX(String fichier) throws IOException{
		ArrayList<Integer> tousLesPosX = new ArrayList<Integer>();
		ArrayList<Integer> tousLesChamps = fileToInteger(fichier);
		for (int i = 0; i < tousLesChamps.size(); i++){
			if (i%3 == 1){
				tousLesPosX.add(Integer.valueOf(tousLesChamps.get(i)));
			}
		}
		return tousLesPosX;
	}
	
	public static ArrayList<Integer> fileToPosY(String fichier) throws IOException{
		ArrayList<Integer> tousLesPosY = new ArrayList<Integer>();
		ArrayList<Integer> tousLesChamps = fileToInteger(fichier);
		for (int i = 0; i < tousLesChamps.size(); i++){
			if (i%3 == 2){
				tousLesPosY.add(Integer.valueOf(tousLesChamps.get(i)));
			}
		}
		return tousLesPosY;
	}


}
