package fr.kaplone.serverSourceUtils;

import java.io.File;

public class ServerSideRightHand extends ServerSideLayer {
	
	String pathString;
	Position edge;	
	
	/**
	 * 
	 * @param edge
	 */
	
	public ServerSideRightHand(String pathName, double scaleValue, Position edge){
		super(pathName, scaleValue);
		this.pathString = pathName;
		this.edge = edge;
	}

	public Position getEdge() {
		return edge;
	}
	
	public double edgeToTop(){
		return edge.getCoordY();
	}
	
	public ServerSideRightHand scalingRigntHand(double standard){
		Position scaledEdge = new Position(edge.getCoordX() * this.getScaleValue(), edge.getCoordY() * this.getScaleValue());
		return new ServerSideRightHand(this.pathString, 1.0, scaledEdge);
	}
	
	
}
