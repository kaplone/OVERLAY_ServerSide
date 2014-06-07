package fr.kaplone.serverSourceUtils;

public class ServerSideRightHand {
	
	double scaleValue;
	Position edge;	
	
	/**
	 * 
	 * @param scaleValue
	 * @param edge
	 */
	
	public ServerSideRightHand(double scaleValue, Position edge){
		this.scaleValue = scaleValue;
		this.edge = edge;
	}


	public double getScaleValue() {
		return scaleValue;
	}


	public Position getEdge() {
		return edge;
	}
	
	public double edgeToTop(){
		return edge.getCoordX();
	}
	
	public ServerSideRightHand scalingRigntHand(double standard){
		Position scaledEdge = new Position(edge.getCoordX() * scaleValue, edge.getCoordY() * scaleValue);
		return new ServerSideRightHand( 0.0, scaledEdge);
	}
	
	
}
