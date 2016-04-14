package AG;

import java.util.ArrayList;
import java.util.Random;

import Parser.City;

public class Gene {
	ArrayList<Integer> gene;
	
	public Gene(ArrayList<City> cidades, int nTribunais) {
		gene = new ArrayList<Integer>();
		generate(cidades, nTribunais);
	}
	
	public void generate(ArrayList<City> cidades, int nTribunais){
		for (City c : cidades){
			if (nTribunais == 0){
				c.setHasTribunal(false);
				gene.add(0);
			}
			else{
				Random gerador = new Random();
				int v = gerador.nextInt(2);
				if (v == 1){
					c.setHasTribunal(true);
					nTribunais--;
					gene.add(1);
				}
				else{
					c.setHasTribunal(false);
					gene.add(0);
				}
			}
		}
	}

	public ArrayList<Integer> getGene() {
		return gene;
	}

	public void setGene(ArrayList<Integer> gene) {
		this.gene = gene;
	}
}
