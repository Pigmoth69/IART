import java.util.ArrayList;

import algorithms.Chromosome;
import algorithms.Evaluation;
import algorithms.GenethicAlgorithm;
import algorithms.SimulatedAnnealing;
import Parser.County;


public class test {

	public static void main(String[] args) {
		ArrayList<County> cidades = new ArrayList<County>();
		cidades.add(new County("A", 7000, 1, 1, 1000, true));
		cidades.add(new County("B", 5000, 1, 3, 900, true));
		cidades.add(new County("C", 12000, 1, 9, 1500, true));
		cidades.add(new County("D", 3000, 2, 4, 500, false));
		cidades.add(new County("E", 20000, 2, 6, 700, true));
		cidades.add(new County("F", 1500, 2, 8, 1000, false));
		cidades.add(new County("G", 10000, 2, 10, 1200, false));
		cidades.add(new County("H", 5200, 3, 2, 1300, true));
		cidades.add(new County("I", 4000, 3, 3, 2000, false));
		cidades.add(new County("J", 1500, 3, 6, 3000, false));
		cidades.add(new County("K", 4000, 3, 8, 1000, false));
		cidades.add(new County("L", 200, 4, 1, 1000, false));
		cidades.add(new County("M", 2500, 4, 5, 1000, true));
		cidades.add(new County("N", 5700, 4, 9, 1000, false));
		cidades.add(new County("O", 7800, 5, 5, 1000, false));
		cidades.add(new County("P", 17000, 5, 7, 1100, true));
		cidades.add(new County("Q", 15600, 5, 9, 500, false));
		cidades.add(new County("R", 22000, 6, 3, 300, true));
		cidades.add(new County("S", 1500, 6, 7, 2500, false));
		cidades.add(new County("T", 7700, 7, 2, 1800, true));
		cidades.add(new County("U", 10500, 7, 8, 4000, true));
		cidades.add(new County("V", 13500, 7, 9, 1000, false));
		cidades.add(new County("W", 1500, 8, 1, 1000, true));
		cidades.add(new County("X", 5500, 8, 3, 1000, true));
		cidades.add(new County("Y", 8500, 8, 7, 1000, true));
		cidades.add(new County("Z", 4500, 9, 2, 1000, false));
		cidades.add(new County("AA", 2500, 9, 4, 1000, false));
		cidades.add(new County("AB", 500, 9, 6, 1000, true));
		cidades.add(new County("AC", 16000, 9, 8, 1000, false));
		cidades.add(new County("AD", 4000, 9, 10, 1000, true));
		
		int nTribunais = 15;
		boolean elitist = true;
		
		switch(args[0]){
		case "GA":
			testGA(cidades, 8, 0.01, 10, nTribunais, elitist);
			break;
		case "SA":
			Chromosome c = new Chromosome(cidades, nTribunais);
			c.generate();
			testSA(c, 150, 1.0, 0.95, cidades);
			break;
		case "GASA":
			Chromosome ch = testGA(cidades, 8, 0.01, 100, nTribunais, elitist);
			testSA(ch, 150, 1.0, 0.95, cidades);
			break;
		case "SAGA":
			break; 
		default:
			System.out.println("peido");
			break;
		}
		
		
	}

	private static Chromosome testGA(ArrayList<County> cidades, int popSize, double mutProb, int Chromosomerations, int nTribunais, boolean elitist){
		
		//ChromosomethicAlgorithm(ArrayList<City> cidades, int popSize, double empProb, double mutProb, int Chromosomerations, int nTribunais){
		GenethicAlgorithm ga = new GenethicAlgorithm(cidades, popSize, mutProb, Chromosomerations, nTribunais, elitist);
		ArrayList<Chromosome> population = ga.getPopulation();
		
		Chromosome c = ga.doIt();
		
		return c;
		/*
		for (Chromosome g : population){
			for (int i = 0; i < g.getChromosome().size(); i++){
				System.out.print(g.getChromosome().get(i));
			}
			System.out.println(".");
		}
		*/
		
		//---Testes à função de avaliação
		/*
		 
		City cidadeTeste = cidades.get(1);
		
		Evaluation eva = new Evaluation(cidadeTeste, cidades);
		eva.calculateScore();
		
		//------Mostra o score da cidadeTeste
		System.out.println(eva.getScore());
		
		//------Mostra as distancias às outras cidades por ordem crescente
		 
		for(City a : eva.getCitiesOrderedByDistance()){
			System.out.println("Cidade " + a.getName() + " está a " + cidadeTeste.getDistanceTo(a));
		}
		*/
	}

	private static void testSA(Chromosome c, int tries, double temperature, double alfa, ArrayList<County> cidades) {
		SimulatedAnnealing SA = new SimulatedAnnealing(c, tries, temperature, alfa, cidades);	
		SA.doIt();
	
	}
	
}
