import Parser.ParseCity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.json.JSONException;

/**
 * Created by danny on 07/04/2016.
 */
public class Main {
	private static ParseCity parser;
	
    public static void main(String[] args) throws InterruptedException, JSONException {

       System.out.println("IART-Court");
       
       String URL = new String("https://pt.wikipedia.org/wiki/Lista_de_municípios_de_Portugal_por_população");
        try {
        	if(!loadData()){ // faz o load da data
        		System.out.println("Getting data.. Please wait.");
        		parser = new ParseCity(URL);
        		System.out.println("Completed!!");
        		saveData();
        	}else{
        		/*System.out.println("Data loaded!!");
        		for(City c: parser.getCityList()){
             	   System.out.println("Name: "+c.getName()+" Lat: "+c.getCoords().getLatitude()+" Lng: "+c.getCoords().getLongitude());;
                }
        		parser.loadCityDistance();*/
        		/*java.net.URL u = new URL();
        		u.getContent()*/
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
