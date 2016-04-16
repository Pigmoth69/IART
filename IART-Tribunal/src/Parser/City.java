package Parser;


public class City implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1884797007619302958L;
	private String name;
    private int population;
    private Coords coords;


    public City(String name, int population){
        this.name = name;
        this.population=population;
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
