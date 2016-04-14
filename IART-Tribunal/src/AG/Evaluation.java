package AG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Parser.City;

public class Evaluation {
	private City cidade;
	private double score;
	private boolean hasTribunal;
	private int distToCloserTribunal;
	private ArrayList<City> citiesOrderedByDistance;
	private ArrayList<City> cities;
	
	public Evaluation(City cidade, boolean hasTribunal, ArrayList<City> cities){
		this.cidade = cidade;
		this.hasTribunal = hasTribunal;
		this.cities = cities;
		score = 0;
	}
	
	
	public int getDistToCloserTribunal() {
		return distToCloserTribunal;
	}
	public void setDistToCloserTribunal(int distToCloserTribunal) {
		this.distToCloserTribunal = distToCloserTribunal;
	}
	public ArrayList<City> getCitiesOrderedByDistance() {
		return citiesOrderedByDistance;
	}
	public void setCitiesOrderedByDistance(ArrayList<City> citiesOrderedByDistance) {
		this.citiesOrderedByDistance = citiesOrderedByDistance;
	}
	public boolean getHasTribunal() {
		return hasTribunal;
	}
	public void setHasTribunal(boolean hasTribunal) {
		this.hasTribunal = hasTribunal;
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

	public double calculateScore(){
		score = 0;
		score += populationScore();
		score += distanceScore();
		score += custosTribunal();
		
		return score;
	}
	
	public double populationScore(){
		if(hasTribunal)
			return cidade.getPopulation();
		else
			return 0;
	}
	
	public double distanceScore(){
		if (hasTribunal)
			return 0;
		else
			return cidade.getPopulation()/distToCloserTribunal;
	}
	
	private double custosTribunal() {
		if (hasTribunal)
			return cidade.getCustoConstrução();
		else
			return 0;
	}
	
	public void sortCities(){
		citiesOrderedByDistance = new ArrayList<City>(cities);
		Collections.sort(citiesOrderedByDistance, new Comparator<City>(){
			@Override
			public int compare(City c1, City c2){
				double d1 = Math.sqrt(Math.pow(cidade.getLatitude() - c1.getLatitude(), 2) + Math.pow(cidade.getLongitude() - c1.getLongitude(), 2));
				double d2 = Math.sqrt(Math.pow(cidade.getLatitude() - c2.getLatitude(), 2) + Math.pow(cidade.getLongitude() - c2.getLongitude(), 2));
			
				if (d1 < d2)
					return -1;
				else if (d2 < d1)
					return 1;
				else
					return 0;
			}
		});
	
		
	}
	
}