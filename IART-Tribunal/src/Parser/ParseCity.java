package Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by danny on 07/04/2016.
 */
public class ParseCity implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4112258298254720150L;
	private static String keyString = "AIzaSyDYEh5wv0aQQn-g5OXVEq0q6DeTZVSBTHY";
    private ArrayList<City> cityList = new ArrayList<City>();
    private String citiesURL = new String();
   // private int[][] distanceMatrix;
    private transient Document doc;

    public ParseCity(String citiesURL) throws IOException, InterruptedException, JSONException {
        if(citiesURL.isEmpty())
            System.err.println("Please, enter a url");
        else {
            this.citiesURL = citiesURL;
            loadCities();
            loadCoords();
            loadCityDistance();
        }
    }

	public ArrayList<City> getCityList() {
		return cityList;
	}

	public void setCityList(ArrayList<City> cityList) {
		this.cityList = cityList;
	}

	private void loadCities() throws IOException{
        doc = Jsoup.connect(citiesURL).get();
        Elements elements = doc.getElementsByAttributeValueMatching("style", "text-align: center;");
        System.out.println(elements.size());
        for(Element e: elements){
        	if(e.childNodeSize() !=1 && e.childNodeSize() !=5){
        		String name = e.children().get(1).text();
        		if(name.contains("(Madeira)") || name.contains("(Açores)"))
        			continue;
        		String population = e.children().get(2).text();
        		int populationNumber;
        		try{
        			populationNumber = getInteger(population);
        			
        		}catch(NumberFormatException e1){
        			populationNumber=-1;
        		}
        		if(populationNumber !=-1)
    				cityList.add(new City(name,populationNumber));

        	}
        }
        //distanceMatrix = new int[cityList.size()][cityList.size()];
    }
    
    private void loadCoords() throws IOException, InterruptedException, JSONException{
    	int current = 1;
    	for(City c: cityList){
    		String cityName = URLEncoder.encode(c.getName(), "utf-8");
    		System.out.println("Getting city "+current+" -->"+c.getName());current++;
    		URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + cityName +
    	               ",Portugal&key=" + keyString);
    		InputStream is = url.openConnection().getInputStream();
    		BufferedReader reader = new BufferedReader( new InputStreamReader( is )  );
    		String line = null;
    		StringBuilder sb = new StringBuilder();
    		while( ( line = reader.readLine() ) != null )  {
    		   sb.append(line);
    		}
    		reader.close();
    		c.setCoords(parseCityFromJSON(sb.toString()));
    	}
    }
    
    public void loadCityDistance() throws IOException{
    	URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=38.7222524,-9.1393366&destinations=41.1579438,-8.629105299999999&key=AIzaSyDYEh5wv0aQQn-g5OXVEq0q6DeTZVSBTHY");
    	InputStream is = url.openConnection().getInputStream();
		BufferedReader reader = new BufferedReader( new InputStreamReader( is )  );
		String line = null;
		StringBuilder sb = new StringBuilder();
		while( ( line = reader.readLine() ) != null )  {
		   sb.append(line);
		}
		System.out.println(sb.toString());
		reader.close();
		
    	/*for(City c: cityList){
    		
    	}*/
    	
    }
    
    private Coords parseCityFromJSON(String jsonString) throws JSONException{
    	JSONObject obj = new JSONObject(jsonString);
        JSONObject results = (JSONObject) obj.getJSONArray("results").get(0);
        JSONObject geometry = results.getJSONObject("geometry");
        JSONObject location = geometry.getJSONObject("location");
        return new Coords(location.getDouble("lat"), location.getDouble("lng"));
    }
    
   /* private int parsecityFrom(String jsonString) throws JSONException{
    	JSONObject obj = new JSONObject(jsonString);
        JSONObject results = (JSONObject) obj.getJSONArray("results").get(0);
        JSONObject geometry = results.getJSONObject("geometry");
        JSONObject location = geometry.getJSONObject("location");
       return -1;// return new Coords(location.getDouble("lat"), location.getDouble("lng"));
    }*/
    
    private int getInteger(String s){
        String res = "";
        for(int i = 0; i < s.length(); ++i)
            if(Character.isDigit(s.charAt(i)))
                res+=s.charAt(i);

        return Integer.parseInt(res);
    }
  
}
