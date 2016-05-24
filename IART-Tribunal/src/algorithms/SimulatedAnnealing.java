package algorithms;

import java.util.ArrayList;

import Parser.County;


public class SimulatedAnnealing {
	private Chromosome solution;
	private int tries;
	private double temperature;
	private double alfa;
	private ArrayList<County> cidades;
	
	public SimulatedAnnealing(Chromosome solution, int tries, double temperature, double alfa, ArrayList<County> cidades){
		this.solution = solution;
		this.tries = tries;
		this.temperature = temperature;
		this.alfa = alfa;
		this.cidades = cidades;
	}
	
	public Chromosome doIt(){
		
		for (int t = 0; t < tries; t++, temperature*=alfa){
			double oldScore = evaluate(solution);
			Chromosome c = mutate();
			double newScore = evaluate(c);
			
			if (t == 0)
				System.out.println(t + ": " + solution.toString() + " - " + oldScore);
				
			
			if (newScore > oldScore){
				//System.out.println("COMPARE: " + newScore + " > " + oldScore + "?");
				//System.out.println("GENES: " + c.toString() + " - " + solution.toString() + "?");
				setSolution(c);
				//System.out.println("------------------------------------------");
				System.out.println("("+t+"): " + solution.toString() + " - " + newScore);
				System.out.println("------------------------------------------");
			}
			else if (newScore < oldScore){
				double scoreVarPerc = (newScore-oldScore)/oldScore;
				double prob = Math.exp(scoreVarPerc/temperature);
				
				double rand = Math.random();
				
				//System.out.println(rand + " < " + prob + "?");
				
				if (rand < prob){
					setSolution(c);
					//System.out.println("------------------------------------------");
					System.out.println("("+t+"): " + ": " + "-> " + solution.toString() + " - " + newScore);
					System.out.println("------------------------------------------");
				}
			}
		}
		
		return solution;
	}

	private double evaluate(Chromosome c){
		c.updateTribunals();
		
		double ChromosomeScore = 0;

		for (County cidade : c.getCidades()){
			Evaluation eva = new Evaluation(cidade, c.getCidades());
			double e = eva.calculateScore();
			
			ChromosomeScore += e;
		}
		
		return ChromosomeScore;
	}
	
	private Chromosome mutate(){
		Chromosome ret = new Chromosome(solution.getCidades(), solution.getNoTribunals());
		ret.setChromosome(solution.getChromosome());
		
		ArrayList<Integer> zerosIds = ret.getIndexOf(0);
		ArrayList<Integer> onesIds = ret.getIndexOf(1);
		
		int zR = (int) Math.floor(Math.random() * zerosIds.size());
		int oR = (int) Math.floor(Math.random() * onesIds.size());
		
		int z = zerosIds.get(zR);
		int o = onesIds.get(oR);
		
		ret.setValue(z, 1);
		ret.setValue(o, 0);
		
		return ret;
	}
	
	public Chromosome getSolution() {
		return solution;
	}

	public void setSolution(Chromosome solution) {
		this.solution = solution;
	}

	public int getTries() {
		return tries;
	}

	public void setTries(int tries) {
		this.tries = tries;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public ArrayList<County> getCidades() {
		return cidades;
	}

	public void setCidades(ArrayList<County> cidades) {
		this.cidades = cidades;
	}
	
}
