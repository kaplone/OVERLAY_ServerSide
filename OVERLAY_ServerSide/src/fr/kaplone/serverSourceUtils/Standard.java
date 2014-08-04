package fr.kaplone.serverSourceUtils;

public class Standard {
	
	public static double getStandard(ServerSideOverlay group){
		
		double standard = Math.min(group.device.getScaleValue(),
				                   group.hand.getScaleValue());
		return standard;
	}
}
