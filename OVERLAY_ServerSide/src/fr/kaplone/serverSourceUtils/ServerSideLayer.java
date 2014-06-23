package fr.kaplone.serverSourceUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ServerSideLayer {
	
	private String pathString;
	private File devicePath;
	private double scaleValue;
	private double[] imageSize;
	
	public ServerSideLayer(String pathString, double scaleValue) {
		this.pathString = pathString;
		this.devicePath = new File(this.pathString);
		try {
			BufferedImage  bimg = ImageIO.read(this.devicePath);
			this.imageSize = new double[] {bimg.getWidth(), bimg.getHeight()};
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.scaleValue = scaleValue;
	}

	public String getPathString() {
		return pathString;
	}

	public File getDevicePath() {
		return devicePath;
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
