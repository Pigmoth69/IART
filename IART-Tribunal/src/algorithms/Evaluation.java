package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Parser.County;

public class Evaluation {
	private County cidade;
	private double score;
	private double mostExpensive;
	
	private double distToCloserTribunal;
	private ArrayList<County> citiesOrderedByDistance;
	private ArrayList<County> cities;
	private int[][] distanceMatrix;
	
	public Evaluation(County cidade, ArrayList<County> cities, int[][] distanceMatrix){
		this.cidade = cidade;
		this.cities = cities;
		this.distanceMatrix=distanceMatrix;
		score = 0;
	}
	

	public double calculateScore(){
		score = 0;
		score += populationScore();
		sortCities();
		this.distToCloserTribunal = distanceToCloserTribunal();
		//System.out.println("....." + distToCloserTribunal);
		score += distanceScore();
		mostExpensive = getMostExpensiveValue();
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
			return cidade.getPopulation()/(distToCloserTribunal/1000);
	}
	
	private double custosTribunal() {
		if (cidade.isHasTribunal())
			return mostExpensive - cidade.getCustoConstrucao();
		else
			return 0;
	}
	
	public void sortCities(){
		citiesOrderedByDistance = new ArrayList<County>(cities);
		Collections.sort(citiesOrderedByDistance, new Comparator<County>(){
			@Override
			public int compare(County c1, County c2){
				int d1 = distanceMatrix[cidade.getCityID()][c1.getCityID()];
				int d2 = distanceMatrix[cidade.getCityID()][c2.getCityID()];

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
				return distanceMatrix[cidade.getCityID()][c.getCityID()];
		}
		
		return -1;
	}

	public double getMostExpensiveValue(){
		double max = 0;
		for (County c : cities){
			if (c.getCustoConstrucao() > max)
				max = c.getCustoConstrucao();
		}
		return max;
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