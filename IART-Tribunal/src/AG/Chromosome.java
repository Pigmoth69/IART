package AG;

import java.util.ArrayList;
import java.util.Random;

import Parser.County;

public class Chromosome {
	ArrayList<Integer> Chromosome;
	ArrayList<County> cidades;
	int nTribunais;
	
	public Chromosome(ArrayList<County> cidades, int nTribunais) {
		this.cidades = cidades;
		this.nTribunais = nTribunais; 
		Chromosome = new ArrayList<Integer>();
	}
	
	public void updateTribunals(){
		for (int i = 0; i < cidades.size(); i++){
			if (Chromosome.get(i) == 1)
				cidades.get(i).setHasTribunal(true);
			else
				cidades.get(i).setHasTribunal(false);
		}
	}
	
	public void generate(){
		
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
				Chromosome.add(1);
			}
			else{
				c.setHasTribunal(false);
				Chromosome.add(0);
			}
		}
	}
	
	public int getNoTribunals(){
		int count = 0;
		for (int i : Chromosome){
			if (i == 1)
				count++;
		}
		return count;
			
	}
	
	public void setValue(int index, int value){
		Chromosome.set(index, value);
	}

	public ArrayList<Integer> getChromosome() {
		return Chromosome;
	}

	public void setChromosome(ArrayList<Integer> Chromosome) {
		this.Chromosome = Chromosome;
	}
	
	public void addGene(int val){
		this.Chromosome.add(val);
	}
	
	@Override
	public String toString(){
		String result = "";
		for (int g : Chromosome){
			result += g;
		}
		return result;
	}

	public ArrayList<County> getCidades() {
		return cidades;
	}

	public void setCidades(ArrayList<County> cidades) {
		this.cidades = cidades;
	}

	public void setnTribunais(int nTribunais) {
		this.nTribunais = nTribunais;
	}

}
