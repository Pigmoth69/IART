package AG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Parser.City;

public class Evaluation {
	private City cidade;
	private double score;
	
	private double distToCloserTribunal;
	private ArrayList<City> citiesOrderedByDistance;
	private ArrayList<City> cities;
	
	public Evaluation(City cidade, ArrayList<City> cities){
		this.cidade = cidade;
		this.cities = cities;
		score = 0;
	}
	

	public double calculateScore(){
		score = 0;
		score += populationScore();
		sortCities();
		this.distToCloserTribunal = distanceToCloserTribunal();
		score += distanceScore();
		score += custosTribunal();
		
		return score;
	}
	
	public double populationScore(){
		if(cidade.isHasTribunal())
			return cidade.getPopulation();
		else
			return 0;
	}
	
	public double distanceScore(){
		if (cidade.isHasTribunal())
			return 0;
		else
			return cidade.getPopulation()/distToCloserTribunal;
	}
	
	private double custosTribunal() {
		if (cidade.isHasTribunal())
			return cidade.getCustoConstrução();
		else
			return 0;
	}
	
	public void sortCities(){
		citiesOrderedByDistance = new ArrayList<City>(cities);
		Collections.sort(citiesOrderedByDistance, new Comparator<City>(){
			@Override
			public int compare(City c1, City c2){
				double d1 = Math.sqrt(Math.pow(cidade.getCoords().getLatitude() - c1.getCoords().getLatitude(), 2) + Math.pow(cidade.getCoords().getLongitude() - c1.getCoords().getLongitude(), 2));
				double d2 = Math.sqrt(Math.pow(cidade.getCoords().getLatitude() - c2.getCoords().getLatitude(), 2) + Math.pow(cidade.getCoords().getLongitude() - c2.getCoords().getLongitude(), 2));
			
				if (d1 < d2)
					return -1;
				else if (d2 < d1)
					return 1;
				else
					return 0;
			}
		});
	
		
	}
	
	public double distanceToCloserTribunal(){
		for (City c : this.citiesOrderedByDistance){
			if (c.isHasTribunal())
				return cidade.getDistanceTo(c);
		}
		
		return -1;
	}
	
	//-------------------------
	//GETTERS AND SETTERS
	//-------------------------
	
	public double getDistToCloserTribunal() {
		return distToCloserTribunal;
	}
	public void setDistToCloserTribunal(double distToCloserTribunal) {
		this.distToCloserTribunal = distToCloserTribunal;
	}
	public ArrayList<City> getCitiesOrderedByDistance() {
		return citiesOrderedByDistance;
	}
	public void setCitiesOrderedByDistance(ArrayList<City> citiesOrderedByDistance) {
		this.citiesOrderedByDistance = citiesOrderedByDistance;
	}
	public City getCidade() {
		return cidade;
	}
	public void setCidade(City cidade) {
		this.cidade = cidade;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
}