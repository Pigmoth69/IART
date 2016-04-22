package AG;

import java.util.ArrayList;
import java.util.Random;

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
		
	}
	
	public ArrayList<Gene> doIt(){
		this.population = initialization();
		ArrayList<Double> scores = evaluate();
		this.newPopulation = selection(scores);
		
		return population;
		
	}
	
	private ArrayList<Gene> initialization(){
		ArrayList<Gene> pop = new ArrayList<Gene>();
		
		for (int i = 0; i < popSize; i++){
			pop.add(new Gene(cidades, nTribunais));
		}
		
		return pop;
	}
	
	private ArrayList<Double> evaluate(){
		ArrayList<Double> scores = new ArrayList<Double>();
		
		for (int i = 0; i < population.size(); i++){
			double geneScore = 0;
			population.get(i).updateTribunals();
			
			for (County cidade : cidades){
				Evaluation eva = new Evaluation(cidade, cidades);
				double e = eva.calculateScore();
				
				// PRINT de pontuação de cada cidade
				//System.out.println(cidade.getName() + " - " + e);
				
				geneScore += e;
			}
			System.out.println(population.get(i).toString() + " - " + geneScore);
			scores.add(geneScore);
		}
		
		return scores;
	}
	
	private ArrayList<Gene> selection(ArrayList<Double> scores){
		double totalScore = 0;
		ArrayList<Double> prob = new ArrayList<Double>(); 
		
		for (double score : scores)
			totalScore += score;
		for (double score : scores)
			prob.add(score/totalScore);
		
		ArrayList<Double> probComutative = new ArrayList<Double>(prob); 
		for (int i = 0; i < prob.size(); i++){
			for (int j = i + 1; j < prob.size(); j++){
				double value = probComutative.get(j);
				value += prob.get(i);
				
				probComutative.set(j, value);
			}
		}
		
		ArrayList<Gene> newPop = new ArrayList<Gene>();
		for (int i = 0; i < population.size(); i++){
			double random = new Random().nextDouble();
			int selected = getSelectedGene(random, probComutative);
			newPop.add(population.get(selected));
		}
		
		return newPop;
		
	}
	
	private int getSelectedGene(double random, ArrayList<Double> probComutative){
		for (int i = 0; i < probComutative.size(); i++){
			double val = probComutative.get(i);
			if (random <= val)
				return i;
		}
		
		return -1;
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
