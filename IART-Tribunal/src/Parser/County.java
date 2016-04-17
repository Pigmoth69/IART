package Parser;


public class County implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1884797007619302958L;
	private String name;
    private int population;
    private Coords coords;
	private double custoConstrução;
	private boolean hasTribunal;



	public County(String name, int population){
		this.name = name;
		this.population=population;
	}
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
	
	public County(String name, int population, double lat, double longi, double custo, boolean tribunal){
        this.name = name;
        this.population=population;
        this.coords = new Coords(lat,longi);
        this.custoConstrução = custo;
        this.hasTribunal = tribunal;
    }
	
	public double getDistanceTo(County c){
		return Math.sqrt(Math.pow(this.coords.getLatitude() - c.getCoords().getLatitude(), 2) + Math.pow(this.coords.getLongitude() - c.getCoords().getLongitude(), 2));
	}


	public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(int population) {
        this.population = population;
    }


    public int getPopulation() {
        return population;
    }

    public String getName() {
        return name;
    }


	public Coords getCoords() {
		return coords;
	}


	public void setCoords(Coords coords) {
		this.coords = coords;
	}
}
