package AG;

import java.util.ArrayList;

import Parser.County;

public class GenethicAlgorithm {
	private ArrayList<County> cidades;
	private int popSize;
	private double empProb;
	private double mutProb;
	private int generations;
	private int nTribunais; 
	
	private ArrayList<Gene> population;
	private ArrayList<Gene> newPopulation;
	
	public GenethicAlgorithm(ArrayList<County> cidades, int popSize, double empProb, double mutProb, int generations, int nTribunais){
		this.cidades = cidades;
		this.popSize = popSize;
		this.empProb = empProb;
		this.mutProb = mutProb;
		this.generations = generations;
		this.population = new ArrayList<Gene>();
		this.nTribunais = nTribunais;
		
		for (int i = 0; i < popSize; i++){
			population.add(new Gene(cidades, nTribunais));
		}
	}
	
	
	//-------------------------
	//GETTERS AND SETTERS
	//-------------------------
	public int getnTribunais() {
		return nTribunais;
	}
	public void setnTribunais(int nTribunais) {
		this.nTribunais = nTribunais;
	}
	public ArrayList<County> getCidades() {
		return cidades;
	}
	public void setCidades(ArrayList<County> cidades) {
		this.cidades = cidades;
	}
	public int getPopSize() {
		return popSize;
	}
	public void setPopSize(int popSize) {
		this.popSize = popSize;
	}
	public double getEmpProb() {
		return empProb;
	}
	public void setEmpProb(double empProb) {
		this.empProb = empProb;
	}
	public double getMutProb() {
		return mutProb;
	}
	public void setMutProb(double mutProb) {
		this.mutProb = mutProb;
	}
	public int getGenerations() {
		return generations;
	}
	public void setGenerations(int generations) {
		this.generations = generations;
	}
	public ArrayList<Gene> getPopulation() {
		return population;
	}
	public void setPopulation(ArrayList<Gene> population) {
		this.population = population;
	}
	public ArrayList<Gene> getNewPopulation() {
		return newPopulation;
	}
	public void setNewPopulation(ArrayList<Gene> newPopulation) {
		this.newPopulation = newPopulation;
	}

}
