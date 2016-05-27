package Client;

import java.io.*;
import java.util.ArrayList;

import Parser.Coords;
import Parser.ParseCity;
import algorithms.Chromosome;
import algorithms.GenethicAlgorithm;
import algorithms.SimulatedAnnealing;
import Parser.County;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.json.JSONException;


public class Main {
	private static ParseCity parser;


	public static void main(String[] args) throws JSONException, InterruptedException {
		System.out.println("IART-Court");

		String URL = new String("https://pt.wikipedia.org/wiki/Lista_de_municípios_de_Portugal_por_população");

		try {
			if(!loadData()) { // faz o load da data
				System.out.println("Getting data.. Please wait.");
				parser = new ParseCity(URL);
				System.out.println("Completed!!");
				saveData();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Invalid URL...");
		}

		boolean elitist = true;
		int nTribunais =50;
		Chromosome ch=null;
		County porto = null;
		County lisboa = null;
		for(int i = 0; i < parser.getCityList().size();i++){
			if(parser.getCityList().get(i).getName().equals("Porto"))
				porto = parser.getCityList().get(i);
			else if(parser.getCityList().get(i).getName().equals("Lisboa"))
				lisboa = parser.getCityList().get(i);
		}

		switch(args[0]){
		case "GA":
			ch = testGA(parser.getCityList(),parser.getDistanceMatrix(), 8, 0.01, 10, nTribunais, elitist);
			break;
		case "SA":
			ch = new Chromosome(parser.getCityList(), nTribunais);
			ch.generate();
			testSA(ch, 500, 1.0, 0.95, parser.getCityList(),parser.getDistanceMatrix());
			break;
		case "GASA":
			ch = testGA(parser.getCityList(),parser.getDistanceMatrix(), 20, 0.01, 100, nTribunais, elitist);
			testSA(ch, 500, 1.0, 0.95, parser.getCityList(),parser.getDistanceMatrix());
			break;
		case "SAGA":
			break; 
		default:
			System.out.println("peido");
			break;
		}
		displayResult(ch);
		
		
	}

	public Main(String args[]) throws JSONException, InterruptedException {
		System.out.println("IART-Court");

		String URL = new String("https://pt.wikipedia.org/wiki/Lista_de_municípios_de_Portugal_por_população");

		try {
			if(!loadData()) { // faz o load da data
				System.out.println("Getting data.. Please wait.");
				parser = new ParseCity(URL);
				System.out.println("Completed!!");
				saveData();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Invalid URL...");
		}

		boolean elitist = true;
		int nTribunais =50;
		Chromosome ch=null;
		County porto = null;
		County lisboa = null;
		for(int i = 0; i < parser.getCityList().size();i++){
			if(parser.getCityList().get(i).getName().equals("Porto"))
				porto = parser.getCityList().get(i);
			else if(parser.getCityList().get(i).getName().equals("Lisboa"))
				lisboa = parser.getCityList().get(i);
		}

		switch(args[0]){
			case "GA":
				if (args[5].equals("false"))
					elitist = false;
				else
					elitist = true;
				ch = testGA(parser.getCityList(),parser.getDistanceMatrix(), Integer.parseInt(args[1]), Double.parseDouble(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), elitist);
				break;
			case "SA":
				ch = new Chromosome(parser.getCityList(), Integer.parseInt(args[4]));
				ch.generate();
				testSA(ch, Integer.parseInt(args[3]), Double.parseDouble(args[1]), Double.parseDouble(args[2]), parser.getCityList(),parser.getDistanceMatrix());
				break;
			case "GASA":
				if (args[5].equals("false"))
					elitist = false;
				else
					elitist = true;
				ch = testGA(parser.getCityList(),parser.getDistanceMatrix(), Integer.parseInt(args[1]), Double.parseDouble(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[7]), elitist);
				testSA(ch, Integer.parseInt(args[6]), Double.parseDouble(args[4]), Double.parseDouble(args[5]), parser.getCityList(),parser.getDistanceMatrix());
				break;
			case "SAGA":
				break;
			default:
				System.out.println("peido");
				break;
		}
		displayResult(ch);

	}

	private static void displayResult(Chromosome ch) {
		System.out.println("Vai come�ar...");
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		Graph graph = new SingleGraph("pinning");

		graph.addAttribute("ui.stylesheet", "graph {fill-mode: image-scaled; fill-image: url('C://Users//David//Desktop//file.png');} ");

		//Coordenadas de controlo para manter o tamanho fixo(isto tem de se fazer
		//devido ao layout ser din�mico
		Node x1 = graph.addNode("control1");
		x1.addAttribute("layout.frozen");
		x1.addAttribute("xy", 37.0199999, 9.4222945);
		x1.addAttribute("ui.style", "size: 10px;");
		Node x2 = graph.addNode("control2");
		x2.addAttribute("layout.frozen");
		x2.addAttribute("xy", 42.1143, 6.2712311);
		x2.addAttribute("ui.style", "size: 10px;");
        		/*Fim das coordenadas de controlo*/

		System.out.println(parser.getCityList().size());

        		for(int i = 0 ; i < ch.getChromosome().size(); i++){
					if(ch.getChromosome().get(i)==1) {
						String s = "cenas" + i;
						Node node = graph.addNode(s);
						node.addAttribute("layout.frozen");
						node.addAttribute("ui.style", "size: 6px;");
						double x, y;
						Coords c = ch.getCidades().get(i).getCoords();
						System.out.println(parser.getCityList().get(i).getName());
						x = (-c.getLongitude() - 0.08) * 0.998;
						y = (c.getLatitude() - 0.54) * 1.0163;
						System.out.println(c.getLatitude());
						System.out.println(c.getLongitude());
						node.setAttribute("x", y);
						node.setAttribute("y", x);
					}
        		}
		graph.display();
		System.out.println("Terminou...");
	}


	private static Chromosome testGA(ArrayList<County> cidades, int[][] distanceMatrix, int popSize, double mutProb, int Chromosomerations, int nTribunais, boolean elitist){
		
		//ChromosomethicAlgorithm(ArrayList<City> cidades, int popSize, double empProb, double mutProb, int Chromosomerations, int nTribunais){
		GenethicAlgorithm ga = new GenethicAlgorithm(cidades,distanceMatrix, popSize, mutProb, Chromosomerations, nTribunais, elitist);
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
		
		//---Testes � fun��o de avalia��o
		/*
		 
		City cidadeTeste = cidades.get(1);
		
		Evaluation eva = new Evaluation(cidadeTeste, cidades);
		eva.calculateScore();
		
		//------Mostra o score da cidadeTeste
		System.out.println(eva.getScore());
		
		//------Mostra as distancias �s outras cidades por ordem crescente
		 
		for(City a : eva.getCitiesOrderedByDistance()){
			System.out.println("Cidade " + a.getName() + " est� a " + cidadeTeste.getDistanceTo(a));
		}
		*/
	}

	private static void testSA(Chromosome c, int tries, double temperature, double alfa, ArrayList<County> cidades, int[][] distanceMatrix) {
		SimulatedAnnealing SA = new SimulatedAnnealing(c, tries, temperature, alfa, cidades,distanceMatrix);
		SA.doIt();
	
	}

	private static boolean loadData(){
		/*String workingDir = System.getProperty("user.dir");
		System.out.println("Current working directory : " + workingDir);*/
		try
		{
			File f = new File("IART-Tribunal/Save/parser.ser");
			if(f.exists())
				System.out.println("skasddas1");
			else
				System.out.println("skasddas2");
			if(!f.isFile() && !f.isDirectory())
				return false;
			FileInputStream fileIn = new FileInputStream(f);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			parser = (ParseCity) in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException i)
		{
			System.err.println("IOExcetion");
			i.printStackTrace();
			return false;
		}catch(ClassNotFoundException c)
		{
			System.err.println("ParseCity class not found");
			c.printStackTrace();
			return false;
		}
		return true;
	}
	private static boolean saveData(){
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("Save/parser.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(parser);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in Save/parser.ser");
		} catch (FileNotFoundException e) {
			System.err.println("File not found..");
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			System.err.println("IOException..");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}

