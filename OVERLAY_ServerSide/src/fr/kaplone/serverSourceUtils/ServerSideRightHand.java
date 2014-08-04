package fr.kaplone.serverSourceUtils;

public class ServerSideRightHand extends ServerSideLayer {

	Position edge;	
	double scaleValue;
	
	/**
	 * 
	 * @param edge
	 */
	
	public ServerSideRightHand(double [] imageSize, double scaleValue, Position edge){
		super(imageSize, scaleValue);
		this.edge = edge;
		this.scaleValue = scaleValue;
	}
	
	public ServerSideRightHand(double imageWidth, double imageHeight , double scaleValue, Position edge){
		this(new double [] {imageWidth, imageHeight}, scaleValue, edge);
	}

	public Position getEdge() {
		return edge;
	}
	
	public double edgeToTop(){
		return edge.getCoordY();
	}
	
	public ServerSideRightHand scaledRigntHand(double standard){
		standard /= this.scaleValue;
		double[] scaledImageSize = {this.getImageWidth() * standard, this.getImageHeight() * standard};
		Position scaledEdge = new Position(edge.getCoordX() * standard, edge.getCoordY() * standard);
		//System.out.println(scaledImageSize[0] + " " + scaledImageSize[0] + " " + scaledEdge.toString());
		return new ServerSideRightHand(scaledImageSize, 1.0, scaledEdge);
	}
	
	
}
