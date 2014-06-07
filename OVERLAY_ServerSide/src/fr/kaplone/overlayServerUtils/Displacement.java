package fr.kaplone.overlayServerUtils;
import java.util.ArrayList;

import fr.kaplone.serverSourceUtils.Position;

public class Displacement {
	
	/**
	 * Provide a list of growing gaps to simulate acceleration
	 * The goal is to reach the middle of the distance
	 * 
	 * @param starting
	 * @param ending
	 * @param coef      value used to compute the acceleration 
	 * @return a Deltas instance that record the list itself and a ratio for the conversion
	 */
	
	public static Deltas acceleration (Position starting, Position ending, double coef){
		double distance = starting.distanceToPoint(ending);
		double max = distance /2;
		double done = 0;
		int mult = 1;
		ArrayList<Double> deltas = new ArrayList<Double>(); 
		while (done < max){
			deltas.add(coef * mult);
			done += coef * mult;
			mult ++;
		}
		return new Deltas(deltas, done - coef);
	}
	
	/* note temporaire :
	 * 
	 * cette fonction est celle qui produisait le plus gros du calcul pour la preuve de concept.
	 * il y aura ici beaucoup de travail pour adapter le code et le rendre plus maniable à l'avenir.
	 * 
	 * Actuellement, la série de points retournée ne contient que des valeurs fausses :
	 * - pas de conversion des positions (zoom et offset)
	 *
	 */
	
	/**
	 * 
	 * Here is the real Core that merge all the values and lists already computed
	 * 
	 * 
	 * @param deltasBefore
	 * @param starting
	 * @param touch
	 * @param ending
	 * @param deltasAfter
	 * @return
	 */
	
	public static ArrayList<Position> deplacement (Deltas deltasBefore, Position starting, Position touch, Position ending, Deltas deltasAfter){
		
		ArrayList<Position> intervals = new ArrayList<Position>();
		int numberOfIntervalsBefore = deltasBefore.getListOfDeltas().size() * 2;
		int numberOfIntervalsAfter = deltasAfter.getListOfDeltas().size() * 2;
		int numberOfStartingFrame = touch.getImageNumber() - numberOfIntervalsBefore;
		int numberRefFrame = touch.getImageNumber();
		int numberOfEndingFrame = touch.getImageNumber() + numberOfIntervalsAfter;
		int frameNumber = numberOfStartingFrame;
		double xTemporary = starting.getCoordX();
		double yTemporary = starting.getCoordY();
		
		double stepXBefore = starting.fullDeltaX(touch) / deltasBefore.getStepValue() / 2;
		double stepYBefore = starting.fullDeltaY(touch) / deltasBefore.getStepValue() / 2;
		
		double stepXAfter = touch.fullDeltaX(ending) / deltasAfter.getStepValue() / 2;
		double stepYAfter = touch.fullDeltaY(ending) / deltasAfter.getStepValue() / 2;
		
		double progres;
		
		for (int i =0; i< deltasBefore.getListOfDeltas().size(); i++){
			
			intervals.add(new Position(xTemporary, yTemporary, starting.getRelativeTo(),frameNumber));
			frameNumber++;
			progres = deltasBefore.getListOfDeltas().get(i);
			xTemporary += stepXBefore * progres;
			yTemporary += stepYBefore * progres;
		}
		
        for (int i = deltasBefore.getListOfDeltas().size() -1; i >= 0 ; i--){
			
			intervals.add(new Position(xTemporary, yTemporary, starting.getRelativeTo(),frameNumber));
			frameNumber++;
			progres = deltasBefore.getListOfDeltas().get(i);
			xTemporary += stepXBefore * progres;
			yTemporary += stepYBefore * progres;
		}
        
      intervals.add(touch);
      xTemporary = touch.getCoordX();
	  yTemporary = touch.getCoordY();
	  frameNumber = touch.getImageNumber() + 1;
      
      for (int i =0; i< deltasAfter.getListOfDeltas().size(); i++){
			
			intervals.add(new Position(xTemporary, yTemporary, starting.getRelativeTo(),frameNumber));
			frameNumber++;
			progres = deltasAfter.getListOfDeltas().get(i);
			xTemporary += stepXAfter * progres;
			yTemporary += stepYAfter * progres;
		}
		
      for (int i = deltasAfter.getListOfDeltas().size() -1; i >= 0 ; i--){
			
			intervals.add(new Position(xTemporary, yTemporary, starting.getRelativeTo(),frameNumber));
			frameNumber++;
			progres = deltasAfter.getListOfDeltas().get(i);
			xTemporary += stepXAfter * progres;
			yTemporary += stepYAfter * progres;
		}
      
      intervals.add(new Position(ending.getCoordX(), ending.getCoordY(), null, frameNumber )); 
	  return intervals;
	}

}
