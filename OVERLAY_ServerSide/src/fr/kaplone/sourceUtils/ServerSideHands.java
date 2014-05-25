package fr.kaplone.sourceUtils;

public class ServerSideHands {
	
	ServerSideRightHand hand;
	ServerSideDevice device;
	
	public ServerSideHands(ServerSideRightHand hand, ServerSideDevice device) {
		this.hand = hand;
		this.device = device;
	}
	
	public Position restPosition(){
		return new Position(device.getOffsetX() + device.getScreenWidth()/2, device.getImageHeight()+ hand.edgeToTop());
		
	}
	
}
