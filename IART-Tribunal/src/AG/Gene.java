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
		
		ArrayList<Integer> tribunalIndexs = new ArrayList<Integer>();
		
		for (int i = 0; i < nTribunais; i++){
			Random gerador = new Random();
			int indexT = gerador.nextInt(cidades.size());
			
			if (!tribunalIndexs.contains(indexT))
				tribunalIndexs.add(indexT);
			else{
				i--;
				continue;
			}
		}
		
		for (int i = 0; i < cidades.size(); i++){
			City c = cidades.get(i);
			if (tribunalIndexs.contains(i)){
				c.setHasTribunal(true);
				gene.add(1);
			}
			else{
				c.setHasTribunal(false);
				gene.add(0);
			}
		}
		
		/* Este codigo não gera exatament nTribunais, mas um valor <= que nTribunais
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
		*/
	}

	public ArrayList<Integer> getGene() {
		return gene;
	}

	public void setGene(ArrayList<Integer> gene) {
		this.gene = gene;
	}
}
