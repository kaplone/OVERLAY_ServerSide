package fr.kaplone.serverSourceUtils;

public class ServerSideLayer {

	private double scaleValue;
	private double[] imageSize;
	
	public ServerSideLayer(double [] imageSize, double scaleValue) {
        this.imageSize = imageSize;
		this.scaleValue = scaleValue;
	}

	public double getScaleValue() {
		return scaleValue;
	}

	public double[] getImageSize() {
		return imageSize;
	}
	
	public double getImageWidth() {
		return this.getImageSize()[0];
	}

	public double getImageHeight() {
		return this.getImageSize()[1];
	}
	

}
