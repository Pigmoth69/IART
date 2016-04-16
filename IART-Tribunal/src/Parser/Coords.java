package Parser;

public class Coords implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7933678794876864696L;
	private double latitude;
	private double longitude;
	public Coords(double latitude,double longitude) {
		this.latitude=latitude;
		this.longitude=longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
