package Parser;


public class City {
    private String name;
    private int population;
    private double latitude;
    private double longitude;
	private double custoConstrução;
	private boolean hasTribunal;


    public boolean isHasTribunal() {
		return hasTribunal;
	}

	public void setHasTribunal(boolean hasTribunal) {
		this.hasTribunal = hasTribunal;
	}

	public double getCustoConstrução() {
		return custoConstrução;
	}

	public void setCustoConstrução(double custoConstrução) {
		this.custoConstrução = custoConstrução;
	}

	public City(String name, int population){
        this.name = name;
        this.population=population;
    }
	
	public City(String name, int population, double lat, double longi, double custo, boolean tribunal){
        this.name = name;
        this.population=population;
        this.latitude = lat;
        this.longitude = longi;
        this.custoConstrução = custo;
        this.hasTribunal = tribunal;
    }
	
	public double getDistanceTo(City c){
		return Math.sqrt(Math.pow(latitude - c.getLatitude(), 2) + Math.pow(longitude - c.getLongitude(), 2));
	}

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getPopulation() {
        return population;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
