package fr.kaplone.sourceUtils;

public class ServerSideDevice {
	
	double scaleValue;
	
	double[] imageSize = new double[2];
	double[] screenSize = new double[2];
	double[] screenPos = new double[2];
	
	/**
	 * 
	 * @param scaleValue
	 * @param offset
	 * @param screenSize
	 */
	
	public ServerSideDevice(double scaleValue, double[] offset, double [] screenSize){
		this.scaleValue = scaleValue;
		this.screenSize = screenSize;
		this.screenPos = offset;
	}
	
	public double getImageWidth(){
		return this.imageSize[0];
	}
	public double getImageHeight(){
		return this.imageSize[1];
	}
	
	public double[] getImageSize() {
		return imageSize;
	}

	public double[] getScreenSize() {
		return screenSize;
	}

	public double getScreenWidth(){
		return this.screenSize[0];
	}
	public double getScreenHeight (){
		return this.screenSize[1];
	}
	
	public double getOffsetX(){
		return this.screenPos[0];
	}

	public double getOffsetY(){
		return this.screenPos[1];
	}
	
	public ServerSideDevice scalingDevice(double standard){
		double[] scaledScreen = {this.getScreenWidth() * standard, this.getScreenHeight() * standard};
		double[] scaledOffset = {this.getOffsetX() * standard, this.getOffsetY() * standard};
		return new ServerSideDevice( 0, scaledScreen, scaledOffset);
	}
	

}
