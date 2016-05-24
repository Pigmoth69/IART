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
		
		for (int generation = 0; generation < Chromosomerations; generation++){
			ArrayList<Double> scores = evaluate(this.population);
			
			this.newPopulation = new ArrayList<Chromosome>();
			
			int bestScoreIndex = getBestScore(scores);
			this.newPopulation.add(this.population.get(bestScoreIndex));
			
			for (int i = 1; i < this.population.size(); i++){		
				System.out.println("sel+crossover+mut (" + i + "): ");
				
				ArrayList<Chromosome> tournamentSelected = selection(scores);
				System.out.println("selected: " + tournamentSelected.get(0) + " - " + tournamentSelected.get(1));
				
				Chromosome c = crossover(tournamentSelected.get(0), tournamentSelected.get(1));
				System.out.println("b4: " + c.toString());

				Chromosome cMut = mutation(c);
				System.out.println("af: " + cMut.toString());
				
				this.newPopulation.add(cMut);
			}
			
			System.out.println("------------------------ GENERATION " + generation + " OVER");
			
			population = newPopulation;
		}
		evaluate(population);
		
		System.out.println("FINITO");
		
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
				//System.out.printl n(cidade.getName() + " - " + e);
				
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
		int a, b, tribCount = 0;
		double prob = 0.5;
		
		for (int i = 0; i < c1.getChromosome().size(); i++){
			a = c1.getChromosome().get(i);
			b = c2.getChromosome().get(i);
			
			if (a == b){
				c.addGene(a);
				if (a == 1)
					tribCount++;
			}
			else{
				prob = (double)(nTribunais - tribCount)/(c1.getChromosome().size() - i);
				//System.out.println("prob: " + prob + "|| nT/tC/size/i = " + nTribunais + "/" + tribCount + "/" + c1.getChromosome().size() + "/" + i);
				if (Math.random() > prob){
					c.addGene(0);
				}else{
					c.addGene(1);
					tribCount++;
				}
			}
			
		}
		
		return c;
	}
	
	private Chromosome mutation(Chromosome c1){
		
		int count = c1.getNoTribunals();
		Random rand = new Random();
		
		while(count != nTribunais){
			int val = rand.nextInt(c1.getChromosome().size());
			
			if (count < nTribunais){
				if (c1.getChromosome().get(val) == 0){
					c1.setValue(val, 1);
					count++;
				}
			}
			else{
				if (c1.getChromosome().get(val) == 1){
					c1.setValue(val, 0);
					count--;
				}
			}
		}
		
		if (Math.random() < mutProb){
			while(true){
				int a = rand.nextInt(c1.getChromosome().size());
				int b = rand.nextInt(c1.getChromosome().size());
				int aV = c1.getChromosome().get(a);
				int bV = c1.getChromosome().get(b);
				if (a == b || aV == bV)
					continue;
				else{
					if (aV == 0){
						c1.setValue(a, 1);
						c1.setValue(b, 0);
					}
					else{
						c1.setValue(a, 0);
						c1.setValue(b, 1);
					}
					break;	
				}
					
			}
		}
		
		return c1;
	}
	
	private int getSelectedChromosome(double random, ArrayList<Double> probComutative){
		for (int i = 0; i < probComutative.size(); i++){
			double val = probComutative.get(i);
			if (random <= val)
				return i;
		}
		
		return -1;
	}
	
	private int getBestScore(ArrayList<Double> scores){
		int i = 0, maxId = -1;
		double max = 0;	
		for (; i < scores.size(); i++){
			if (scores.get(i) > max){
				max = scores.get(i);
				maxId = i;
			}
		}
		
		return maxId;
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
