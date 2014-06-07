package fr.kaplone.serverSourceUtils;

//TODO voir  : 
//             java.awt.geom.Point2D.Double
//             et java.awt.geom.Point2D

import java.lang.Math;

public class Position {
	
	private double coordX;
	private double coordY;
	private Position relativeTo;
	private int imageNumber;
	private int nextPosition;
	private int actionType;
	
	/*
	 * rootPoint is a point for the finger out of the screen
	 */
	
	final static Position rootPosition = new Position(0, 0);
	
	/**
	 * 
	 * @param coordX 
	 * @param coordY
	 * @param relatif   the position referenced by ServerSideDevice.getOffsetX()
	 *                                          and ServerSideDevice.getOffsetY()
	 *                  Used to convert the finger position in device
	 *                  to position in the overlay  
	 * @param num       the number of the current frame
	 * @param next      semi-boolean for the position between key positions 
	 *                  (0 = rootPoint, a Point out of the screen area
	 *                   1 = the next key position in the Arraylist)  
	 * 
	 * @param action    code for the action on the screen :
	 *                  1 = touch
	 */
	
	public Position(double coordX, double coordY, Position relatif, int num, int next, int action) {
		this.coordX = coordX;
		this.coordY = coordY;
		this.relativeTo = relatif;
		this.imageNumber = num;
		this.nextPosition = next;
		this.actionType = action;
	}
	
	/**
	 *  semi constructor used for the (internal) fingerRelativeToRootPoint() method 
	 * 
	 * @param coordX
	 * @param coordY
	 * @param relatif
	 * @param num
	 */
	
	public Position(double coordX, double coordY, Position relatif, int num) {
		this.coordX = coordX;
		this.coordY = coordY;
		this.relativeTo = relatif;
		this.imageNumber = num;
	}
	
	/**
	 *  semi constructor used for the (external) restPoint and the (internal) rootPosition instances 
	 * 
	 * @param coordX
	 * @param coordY
	 */

	public Position(double coordX, double coordY) {
		this(coordX, coordY, null, -1, -1, -1);
	}
	
	/**
	 * get the distance on the only X or Y axis 
	 * 
	 * @param p
	 * @return
	 */
	
	public double fullDeltaX(Position p){
		return p.getCoordX() - this.coordX;
	}
	public double fullDeltaY(Position p){
		return p.getCoordY() - this.coordY;
	}
	
	/**
	 * get the distance between two Points 
	 * 
	 * @param p
	 * @return
	 */
	
	public double distanceToPoint (Position p){
		double squareDeltaX = ( this.coordX - p.getCoordX())*  (this.coordX - p.getCoordX());
		double squareDeltaY = (this.coordY - p.getCoordY()) *  (this.coordY - p.getCoordY());
		return Math.sqrt(squareDeltaX + squareDeltaY);
	}

	/**
	 * return a new Position, with a position computed relatively to rootDevicePoint
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	
	public Position fingerRelativeToDevice(int x, int y){
		//return fingerRelativeToRootPoint(x + 788, y + 451);// TODO : update with object
		return fingerRelativeToRootPoint(x + this.relativeTo.getCoordX(), y + this.relativeTo.getCoordY());
	}
	
	/**
	 * 
	 * return a new Point, with a position computed relatively to rootPoint 
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	
	public Position fingerRelativeToRootPoint(double x, double y){
		return new Position(x - this.getCoordX( ), y - this.getCoordY(), rootPosition, this.imageNumber);
	}

	public double getCoordX() {
		return coordX;
	}

	public double getCoordY() {
		return coordY;
	}

	public Position getRelativeTo() {
		return relativeTo;
	}

	public int getImageNumber() {
		return imageNumber;
	}

	public int getNextPosition() {
		return nextPosition;
	}

	public int getActionType() {
		return actionType;
	}


}
