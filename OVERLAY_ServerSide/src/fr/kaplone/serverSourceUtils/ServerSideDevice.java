package fr.kaplone.serverSourceUtils;

public class ServerSideDevice extends ServerSideLayer {
	
	String pathString;
	double[] screenSize = new double[2];
	double[] screenPos = new double[2];
	
	/**
	 * 
	 * @param offset
	 * @param screenSize
	 */
	
	public ServerSideDevice(String pathName, double scaleValue, double[] offset, double [] screenSize){
		super(pathName, scaleValue);
		this.pathString = pathName;
		this.screenSize = screenSize;
		this.screenPos = offset;
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
	
	public double getOffsetX(){
		return this.screenPos[0];
	}

	public double getOffsetY(){
		return this.screenPos[1];
	}
	
	public ServerSideDevice scalingDevice(double standard){
		double[] scaledScreen = {this.getScreenWidth() * standard, this.getScreenHeight() * standard};
		double[] scaledOffset = {this.getOffsetX() * standard, this.getOffsetY() * standard};
		return new ServerSideDevice(this.pathString, 1.0, scaledScreen, scaledOffset);
	}
	

}
