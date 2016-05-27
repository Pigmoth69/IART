package Parser;


public class County implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1884797007619302958L;
	private int cityID;
	private String name;
    private int population;
    private Coords coords;
	private double custoConstrucao;
	private boolean hasTribunal;



	public County(int cityID,String name, int population){
		this.cityID=cityID;
		this.name = name;
		this.population=population;
	}
    
	public boolean isHasTribunal() {
		return hasTribunal;
	}

	public void setHasTribunal(boolean hasTribunal) {
		this.hasTribunal = hasTribunal;
	}
	

	public int getCityID() {
		return cityID;
	}
	public void setCityID(int cityID) {
		this.cityID = cityID;
	}
	public double getCustoConstrucao() {
		return custoConstrucao;
	}

	public void setCustoConstrucao(double custoConstrucao) {
		this.custoConstrucao = custoConstrucao;
	}
	
	public County(String name, int population, double lat, double longi, double custo, boolean tribunal){
        this.name = name;
        this.population=population;
        this.coords = new Coords(lat,longi);
        this.custoConstrucao = custo;
        this.hasTribunal = tribunal;
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
