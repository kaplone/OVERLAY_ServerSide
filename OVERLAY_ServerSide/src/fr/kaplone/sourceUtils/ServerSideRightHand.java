package fr.kaplone.sourceUtils;

public class ServerSideRightHand {
	
	double scaleValue;
	Point edge;	
	
	/**
	 * 
	 * @param scaleValue
	 * @param edge
	 */
	
	public ServerSideRightHand(double scaleValue, Point edge){
		this.scaleValue = scaleValue;
		this.edge = edge;
	}


	public double getScaleValue() {
		return scaleValue;
	}


	public Point getEdge() {
		return edge;
	}
	
	public ServerSideRightHand scalingRigntHand(double standard){
		Point scaledEdge = new Point(edge.getCoordX() * scaleValue, edge.getCoordY() * scaleValue);
		return new ServerSideRightHand( 0.0, scaledEdge);
	}
}
