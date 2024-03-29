package fr.kaplone.overlayServerUtils;

import java.util.ArrayList;

public class Deltas {
	
	ArrayList<Double> listOfDeltas = new ArrayList<Double>();
	double stepValue;
	
	/**
	 * this is a simple generic acceleration-like representation
	 * 
	 * @param deltas       list of growing gaps
	 * @param stepValue    ratio for compute the right x or y value 
	 */
	
	public Deltas(ArrayList<Double> deltas, double stepValue) {
		super();
		this.listOfDeltas = deltas;
		this.stepValue = stepValue;
	}
	public ArrayList<Double> getListOfDeltas() {
		return listOfDeltas;
	}
	public void setListOfDeltas(ArrayList<Double> deltas) {
		this.listOfDeltas = deltas;
	}
	public double getStepValue() {
		return stepValue;
	}
}
