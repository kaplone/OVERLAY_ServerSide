package fr.kaplone.sourceUtils;

import java.io.File;

public class RightHand {
	

	String imagePath;
	File image;
	double scaleValue;
	Point edge;
	
	
	public RightHand(String imagePath, double scaleValue, Point edge) {
		this.imagePath= imagePath;
		this.image = new File(imagePath);
		this.scaleValue = scaleValue;
		this.edge = edge;
	}
	
	

}
