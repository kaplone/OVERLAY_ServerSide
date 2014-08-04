package fr.kaplone.serverSourceUtils;

public class ServerSideBackground extends ServerSideLayer {

	public ServerSideBackground(double [] imageSize) {
		super(imageSize, 0.0);
	}

	public ServerSideBackground(double imageWidth, double imageHeight) {
		this(new double [] {imageWidth, imageHeight});
	}
	
	public ServerSideBackground scaledBackground(double ratio){
		double[] scaledImageSize = {this.getImageWidth() * ratio, this.getImageHeight() * ratio};
		return new ServerSideBackground(scaledImageSize);
	}

}
