package fr.kaplone.serverSourceUtils;

public class ServerSideOverlay {

	ServerSideRightHand hand;
	ServerSideDevice device;
	ServerSideBackground back;
	double standard;
	
	public ServerSideOverlay(ServerSideRightHand hand, ServerSideDevice device, ServerSideBackground back) {
		this.hand = hand;
		this.device = device;
		this.back = back;
		this.standard = Standard.getStandard(this);
	}
	
	public ServerSideOverlay scaledServerSideOverlay(){
		return new ServerSideOverlay(this.hand.scaledRigntHand(this.standard),
                                     this.device.scaledDevice(this.standard),
                                     this.back.scaledBackground(this.standard));
	}
	
	public Position getRestPosition(){
		return new Position(device.getOffsetX() + device.getScreenWidth()/2, device.getImageHeight()+ hand.edgeToTop());
		
	}

	public ServerSideRightHand getHand() {
		return hand;
	}

	public ServerSideDevice getDevice() {
		return device;
	}

	public ServerSideBackground getBack() {
		return back;
	}

	public double getStandard() {
		return standard;
	}
	


}
