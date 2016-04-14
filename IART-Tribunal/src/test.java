import java.util.ArrayList;

import AG.Evaluation;
import Parser.City;


public class test {

	public static void main(String[] args) {
		ArrayList<City> cidades = new ArrayList<City>();
		cidades.add(new City("A", 7000, 3, 1, 1000, false));
		cidades.add(new City("B", 5000, 1, 2, 1000, false));
		cidades.add(new City("C", 12000, 3, 5, 1000, true));
		cidades.add(new City("D", 3000, 6, 4, 1000, false));
		cidades.add(new City("E", 20000, 4, 3, 1000, false));
		cidades.add(new City("F", 1500, 5, 1, 1000, false));
		
		City cidadeTeste = cidades.get(1);
		
		Evaluation eva = new Evaluation(cidadeTeste, cidades);
		eva.calculateScore();
		
		//Mostra o score da cidadeTeste
		System.out.println(eva.getScore());
		
		//Mostra as distancias às outras cidades por ordem crescente
		 
		for(City a : eva.getCitiesOrderedByDistance()){
			System.out.println("Cidade " + a.getName() + " está a " + cidadeTeste.getDistanceTo(a));
		}
		
	}

}
