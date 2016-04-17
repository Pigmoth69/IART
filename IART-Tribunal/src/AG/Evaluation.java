package AG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Parser.County;

public class Evaluation {
	private County cidade;
	private double score;
	
	private double distToCloserTribunal;
	private ArrayList<County> citiesOrderedByDistance;
	private ArrayList<County> cities;
	
	public Evaluation(County cidade, ArrayList<County> cities){
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
		citiesOrderedByDistance = new ArrayList<County>(cities);
		Collections.sort(citiesOrderedByDistance, new Comparator<County>(){
			@Override
			public int compare(County c1, County c2){
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
		for (County c : this.citiesOrderedByDistance){
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
	public ArrayList<County> getCitiesOrderedByDistance() {
		return citiesOrderedByDistance;
	}
	public void setCitiesOrderedByDistance(ArrayList<County> citiesOrderedByDistance) {
		this.citiesOrderedByDistance = citiesOrderedByDistance;
	}
	public County getCidade() {
		return cidade;
	}
	public void setCidade(County cidade) {
		this.cidade = cidade;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
}