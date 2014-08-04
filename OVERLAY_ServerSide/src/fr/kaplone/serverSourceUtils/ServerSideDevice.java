package fr.kaplone.serverSourceUtils;

public class ServerSideDevice extends ServerSideLayer {

	double[] screenSize = new double[2];
	double[] screenPos = new double[2];
	double scaleValue;
	
	/**
	 * 
	 * @param offset
	 * @param screenSize
	 */
	
	public ServerSideDevice(double [] imageSize, double scaleValue, double[] offset, double [] screenSize){
		super(imageSize, scaleValue);
		this.screenSize = screenSize;
		this.screenPos = offset;
		this.scaleValue = scaleValue;
	}
	
	public ServerSideDevice(double imageWidth, double imageHeight, double scaleValue, double[] offset, double [] screenSize){
		this(new double[] {imageWidth, imageHeight}, scaleValue, offset, screenSize);
	}


	public double[] getScreenSize() {
		return this.screenSize;
	}

	public double getScreenWidth(){
		return this.screenSize[0];
	}
	public double getScreenHeight (){
		return this.screenSize[1];
	}
	
	public double [] getOffset(){
		return this.screenPos;
	}
	
	public double getOffsetX(){
		return this.screenPos[0];
	}

	public double getOffsetY(){
		return this.screenPos[1];
	}
	
	public ServerSideDevice scaledDevice(double standard){
		standard /= this.scaleValue;
		double[] scaledImageSize = {this.getImageWidth() * standard, this.getImageHeight() * standard};
		double[] scaledScreen = {this.getScreenWidth() * standard, this.getScreenHeight() * standard};
		double[] scaledOffset = {this.getOffsetX() * standard, this.getOffsetY() * standard};
		//System.out.println(scaledImageSize[0] + " " + scaledImageSize[1] + " " + scaledScreen[0] + " "  + scaledScreen[1] + " " + scaledOffset[0] + " " + scaledOffset[0]);
		return new ServerSideDevice(scaledImageSize, 1.0, scaledScreen, scaledOffset);
	}
	

}
