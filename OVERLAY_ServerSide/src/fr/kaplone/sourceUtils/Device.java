package fr.kaplone.sourceUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Device {
	
	String pathImage;
	File imageFile;
	double scaleValue;
	
	double[] imageSize = new double[2];
	double[] deviceSize = new double[2]; 
	double[] screenSize = new double[2];
	
	public Device(String pathImage, double scaleValue, double[] tailleDevice, double[] tailleEcran) throws IOException {
		this.pathImage = pathImage;
		this.imageFile = new File(pathImage);
		BufferedImage image = ImageIO.read(this.imageFile);
		this.imageSize[0] = image.getWidth();
		this.imageSize[1] = image.getHeight();
		this.scaleValue = scaleValue;
		this.deviceSize = tailleDevice;
		this.screenSize = tailleEcran;
	}
	
	public double getImageWidth(){
		return this.imageSize[0];
	}
	public double getImageHeight(){
		return this.imageSize[1];
	}
	
	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}
	
	public double[] getImageSize() {
		return imageSize;
	}

	public void setImageSize(double[] imageSize) {
		this.imageSize = imageSize;
	}

	public File getImageFile() {
		return imageFile;
	}

	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	public double[] getDeviceSize() {
		return deviceSize;
	}

	public void setDeviceSize(double[] deviceSize) {
		this.deviceSize = deviceSize;
	}

	public double getDeviceWidth(){
		return this.deviceSize[0];
	}
	public double getDeviceHeight(){
		return this.deviceSize[1];
	}
	
	public double[] getScreenSize() {
		return screenSize;
	}

	public void setScreenSize(double[] screenSize) {
		this.screenSize = screenSize;
	}
	
	public double getScreenWidth(){
		return this.screenSize[0];
	}
	public double getScreenHeight (){
		return this.screenSize[1];
	}
	
	public Device scalingDevice(double standard) throws IOException{
		double[] scaledImage = {this.getImageWidth() * standard, this.getImageHeight() * standard};
		double[] scaledDevice = {this.getDeviceWidth() * standard, this.getDeviceHeight() * standard};
		double[] scaledScreen = {this.getScreenWidth() * standard, this.getScreenHeight() * standard};
		return new Device(this.getPathImage(), 0, scaledDevice, scaledScreen);
	}
	

}
