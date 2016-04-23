package AG;

import java.util.ArrayList;
import java.util.Random;

import Parser.County;

public class GenethicAlgorithm {
	private ArrayList<County> cidades;
	private int popSize;
	private double empProb;
	private double mutProb;
	private int Chromosomerations;
	private int nTribunais; 
	
	private ArrayList<Chromosome> population;
	private ArrayList<Chromosome> newPopulation;
	
	public GenethicAlgorithm(ArrayList<County> cidades, int popSize, double empProb, double mutProb, int Chromosomerations, int nTribunais){
		this.cidades = cidades;
		this.popSize = popSize;
		this.empProb = empProb;
		this.mutProb = mutProb;
		this.Chromosomerations = Chromosomerations;
		this.population = new ArrayList<Chromosome>();
		this.newPopulation = new ArrayList<Chromosome>();
		this.nTribunais = nTribunais;
		
	}
	
	public ArrayList<Chromosome> doIt(){
		this.population = initialization();
		ArrayList<Double> scores = evaluate(this.population);
		
		for (int i = 0; i < this.population.size(); i++){
			ArrayList<Chromosome> tournamentSelected = selection(scores);
			Chromosome c = crossover(tournamentSelected.get(0), tournamentSelected.get(1));
			this.newPopulation.add(c);
		}
		
		System.out.println("------------------------");
		evaluate(this.newPopulation);
		
		return population;
		
	}
	
	private ArrayList<Chromosome> initialization(){
		ArrayList<Chromosome> pop = new ArrayList<Chromosome>();
		
		for (int i = 0; i < popSize; i++){
			Chromosome c = new Chromosome(cidades, nTribunais);
			c.generate();
			pop.add(c);
		}
		
		return pop;
	}
	
	private ArrayList<Double> evaluate(ArrayList<Chromosome> pop){
		ArrayList<Double> scores = new ArrayList<Double>();
		
		for (int i = 0; i < pop.size(); i++){
			double ChromosomeScore = 0;
			pop.get(i).updateTribunals();
			
			for (County cidade : cidades){
				Evaluation eva = new Evaluation(cidade, cidades);
				double e = eva.calculateScore();
				
				// PRINT de pontuação de cada cidade
				//System.out.println(cidade.getName() + " - " + e);
				
				ChromosomeScore += e;
			}
			System.out.println(pop.get(i).toString() + " - " + ChromosomeScore);
			scores.add(ChromosomeScore);
		}
		
		return scores;
	}
	
	private ArrayList<Chromosome> selection(ArrayList<Double> scores){
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
		
		ArrayList<Chromosome> newPop = new ArrayList<Chromosome>();
		for (int i = 0; i < 2; i++){
			double random = new Random().nextDouble();
			int selected = getSelectedChromosome(random, probComutative);
			newPop.add(population.get(selected));
		}
		
		return newPop;
		
	}
	
	private Chromosome crossover(Chromosome c1, Chromosome c2){
		Chromosome c = new Chromosome(cidades, nTribunais);
		
		for (int i = 0; i < c1.getChromosome().size(); i++){
			if (Math.random() > 0.5)
				c.addGene(c1.getChromosome().get(i));
			else
				c.addGene(c2.getChromosome().get(i));
		}
		
		return c;
	}
	
	private int getSelectedChromosome(double random, ArrayList<Double> probComutative){
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
	public int getChromosomerations() {
		return Chromosomerations;
	}
	public void setChromosomerations(int Chromosomerations) {
		this.Chromosomerations = Chromosomerations;
	}
	public ArrayList<Chromosome> getPopulation() {
		return population;
	}
	public void setPopulation(ArrayList<Chromosome> population) {
		this.population = population;
	}
	public ArrayList<Chromosome> getNewPopulation() {
		return newPopulation;
	}
	public void setNewPopulation(ArrayList<Chromosome> newPopulation) {
		this.newPopulation = newPopulation;
	}

}
