import Parser.Coords;
import Parser.County;
import Parser.ParseCity;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.ProxyPipe;
import org.graphstream.ui.graphicGraph.GraphicNode;
import org.graphstream.ui.spriteManager.Sprite;
import org.graphstream.ui.spriteManager.SpriteManager;
import org.graphstream.ui.view.Viewer;
import org.json.JSONException;
/**
 * Created by danny on 07/04/2016.
 * http://graphstream-project.org/
 */
public class Main {
	private static ParseCity parser;
	
    public static void main(String[] args) throws InterruptedException, JSONException, IOException {
       System.out.println("IART-Court");
       
       String URL = new String("https://pt.wikipedia.org/wiki/Lista_de_munic√≠pios_de_Portugal_por_popula√ß√£o");
       
       
      try {
        	if(!loadData()){ // faz o load da data
        		System.out.println("Getting data.. Please wait.");
        		parser = new ParseCity(URL);
        		System.out.println("Completed!!");
        		saveData();
        	}else{
        		System.out.println("Vai comeÁar...");
        		//parser.loadCityDistance();
        		
        		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        		Graph graph = new SingleGraph("pinning");
        		graph.addAttribute("ui.stylesheet", "graph {fill-mode: image-scaled; fill-image: url('C:/img/file.png');} ");
        		//Coordenadas de controlo para manter o tamanho fixo(isto tem de se fazer
        		//devido ao layout ser din‚mico
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
        		for(int i = 0 ; i < 277; i++){
        			String s = "cenas"+i;
        			Node node = graph.addNode(s);
        			node.addAttribute("layout.frozen");
        			node.addAttribute("ui.style", "size: 6px;");
        			double x,y;
        			Coords c = parser.getCityList().get(i).getCoords();
        			//System.out.println(c.getLatitude());
        			System.out.println(parser.getCityList().get(i).getName());
        			//y=4050*Math.log(Math.tan((Math.PI/4)+c.getLatitude()/2));
        			//x=725*(-c.getLongitude());
        			x = (-c.getLongitude()-0.08)*0.998;
        			y = (c.getLatitude()-0.54)*1.0163;
        			//System.out.println("X: "+x);
        			//System.out.println("Y: "+y);
        			System.out.println(c.getLatitude());
        			System.out.println(c.getLongitude());
        			node.setAttribute("x", y);
        			node.setAttribute("y", x);
        			//System.out.println("X"+i+"= "+x);
        			//System.out.println("Y"+i+"= "+y);
        			
        			//System.in.read();
        			//graph.addNode(arg0)
        		}

        		graph.display();
        		System.in.read();
        		 

        		System.out.println("Terminou...");
        	}
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Invalid URL...");
        }
        
      
        System.out.println("IART-FINAL");
    }
    
    private static boolean loadData(){
    	 try
         {
    		File f = new File("Save/parser.ser");
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
/*https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=38.7222524,-9.1393366&destinations=41.1579438,-8.629105299999999&key=YOUR_API_KEY*/
/*Chave da API: AIzaSyDp1NfugagpzQ6cHWDD8V9rPGIfCoIxks4*/
