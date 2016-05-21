import java.util.ArrayList;

import AG.Evaluation;
import AG.Chromosome;
import AG.GenethicAlgorithm;
import Parser.County;


public class test {

	public static void main(String[] args) {
		ArrayList<County> cidades = new ArrayList<County>();
		cidades.add(new County("A", 7000, 3, 1, 1000, false));
		cidades.add(new County("B", 5000, 1, 2, 1000, false));
		cidades.add(new County("C", 12000, 3, 5, 1000, true));
		cidades.add(new County("D", 3000, 6, 4, 1000, false));
		cidades.add(new County("E", 20000, 4, 3, 1000, false));
		cidades.add(new County("F", 1500, 5, 1, 1000, false));
		
		//ChromosomethicAlgorithm(ArrayList<City> cidades, int popSize, double empProb, double mutProb, int Chromosomerations, int nTribunais){
		GenethicAlgorithm ga = new GenethicAlgorithm(cidades, 8, 0.5, 0.01, 10, 4);
		ArrayList<Chromosome> population = ga.getPopulation();
		
		
		ga.doIt();
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

}
