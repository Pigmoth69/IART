package AG;

import java.util.ArrayList;
import java.util.Random;

import Parser.County;

public class Gene {
	ArrayList<Integer> gene;
	ArrayList<County> cidades;
	
	public Gene(ArrayList<County> cidades, int nTribunais) {
		this.cidades = cidades;
		gene = new ArrayList<Integer>();
		generate(cidades, nTribunais);
	}
	
	public void updateTribunals(){
		for (int i = 0; i < cidades.size(); i++){
			if (gene.get(i) == 1)
				cidades.get(i).setHasTribunal(true);
			else
				cidades.get(i).setHasTribunal(false);
		}
	}
	
	public void generate(ArrayList<County> cidades, int nTribunais){
		
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
			County c = cidades.get(i);
			if (tribunalIndexs.contains(i)){
				c.setHasTribunal(true);
				gene.add(1);
			}
			else{
				c.setHasTribunal(false);
				gene.add(0);
			}
		}
	}

	public ArrayList<Integer> getGene() {
		return gene;
	}

	public void setGene(ArrayList<Integer> gene) {
		this.gene = gene;
	}
	
	@Override
	public String toString(){
		String result = "";
		for (int g : gene){
			result += g;
		}
		return result;
	}
}
