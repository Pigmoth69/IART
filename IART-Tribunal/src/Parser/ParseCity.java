package Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
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
    private ArrayList<County> cityList = new ArrayList<County>();
    private String citiesURL = new String();
    private int[][] distanceMatrix;
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

	public ArrayList<County> getCityList() {
		return cityList;
	}

	public void setCityList(ArrayList<County> cityList) {
		this.cityList = cityList;
	}

	private void loadCities() throws IOException{
        doc = Jsoup.connect(citiesURL).get();
        Elements elements = doc.getElementsByAttributeValueMatching("style", "text-align: center;");
        System.out.println(elements.size());
        int id=0;
        for(Element e: elements){
        	if(e.childNodeSize() !=1 && e.childNodeSize() !=5){
        		String name = e.children().get(1).text();
        		if(name.contains("(Madeira)") || name.contains("(Açores)")|| name.contains("Funchal")||
        		   name.contains("Ponta Delgada")|| name.contains("Santa Cruz")||name.contains("Câmara de Lobos")||
        		   name.contains("Angra do Heroísmo")||name.contains("Ribeira Grande")||name.contains("Machico")||
        		   name.contains("Praia da Vitória")||name.contains("Horta")||name.contains("Vila Franca do Campo")||
        		   name.contains("Santana")||name.contains("Povoação")||name.contains("Madalena")||name.contains("São Vicente")||
        		   name.contains("Vila do Porto")||name.contains("Porto Santo")||name.contains("Velas")||name.contains("Nordeste")||
        		   name.contains("Lajes do Pico")||name.contains("Santa Cruz da Graciosa")|| name.contains("Calheta")||
        		   name.contains("São Roque do Pico")||name.contains("Porto Moniz")||name.contains("Santa Cruz das Flores")||
        		   name.contains("Lajes das Flores")|| name.contains("Corvo")|| name.contains("Ribeira Brava")|| name.contains("Ponta do Sol")||
        		   name.contains("Santana"))
        			continue;
        		String population = e.children().get(2).text();
        		int populationNumber;
        		try{
        			populationNumber = getInteger(population);
        			
        		}catch(NumberFormatException e1){
        			populationNumber=-1;
        		}
        		if(populationNumber !=-1){
    				cityList.add(new County(id,name,populationNumber));
    				id++;
        		}

        	}
        }
    }
    
    private void loadCoords() throws IOException, InterruptedException, JSONException{
    	int current = 1;
    	for(County c: cityList){
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
    
    public static String getKeyString() {
		return keyString;
	}

	public String getCitiesURL() {
		return citiesURL;
	}

	public int[][] getDistanceMatrix() {
		return distanceMatrix;
	}

	public Document getDoc() {
		return doc;
	}

	public void loadCityDistance() throws IOException, JSONException, InterruptedException{
    	int current = 1;
    	distanceMatrix = new int[cityList.size()][cityList.size()];
    	for(int i = 0 ; i < cityList.size();i++){
    		for(int j = 0 ; j < cityList.size();j++){
    			if(cityList.get(i).equals(cityList.get(j)))
    				continue;
    			else{
    				String c1LAT = URLEncoder.encode(String.valueOf(cityList.get(i).getCoords().getLatitude()), "utf-8");
    				String c1LNG = URLEncoder.encode(String.valueOf(cityList.get(i).getCoords().getLongitude()), "utf-8");
    				String c2LAT = URLEncoder.encode(String.valueOf(cityList.get(j).getCoords().getLatitude()), "utf-8");
    				String c2LNG = URLEncoder.encode(String.valueOf(cityList.get(j).getCoords().getLongitude()), "utf-8");
    				//Thread.sleep(100);
    				URL u = new URL("http://router.project-osrm.org/viaroute?loc="+c1LAT+","+c1LNG+"&loc="+c2LAT+","+c2LNG+"&instructions=true");
    				InputStream is = u.openConnection().getInputStream();
    				BufferedReader reader = new BufferedReader( new InputStreamReader( is )  );
    				String line = null;
    				StringBuilder sb = new StringBuilder();
    				while( ( line = reader.readLine() ) != null )  {
    				   sb.append(line);
    				}
    				JSONObject obj = new JSONObject(sb.toString());
    				JSONObject total = obj.getJSONObject("route_summary");
    				int distance = total.getInt("total_distance");
    				distanceMatrix[i][j]=distance;
    				System.out.println("Current-> "+current+" Total-> "+cityList.size()*cityList.size());current++;
    			}
    		}
    	}
    	
    }
    
    private Coords parseCityFromJSON(String jsonString) throws JSONException{
    	JSONObject obj = new JSONObject(jsonString);
        JSONObject results = (JSONObject) obj.getJSONArray("results").get(0);
        JSONObject geometry = results.getJSONObject("geometry");
        JSONObject location = geometry.getJSONObject("location");
        return new Coords(location.getDouble("lat"), location.getDouble("lng"));
    }
    
   /*private int parsecityFrom(String jsonString) throws JSONException, IOException{
	   
	   	URL u = new URL("http://router.project-osrm.org/viaroute?loc=38.7222524,-9.1393366&loc=41.1579438,-8.629105299999999&instructions=true");
		InputStream is = u.openConnection().getInputStream();
		BufferedReader reader = new BufferedReader( new InputStreamReader( is )  );
		String line = null;
		StringBuilder sb = new StringBuilder();
		while( ( line = reader.readLine() ) != null )  {
		   sb.append(line);
		}
		JSONObject obj = new JSONObject(sb.toString());
		JSONObject total = obj.getJSONObject("route_summary");
		int distance = total.getInt("total_distance");
		reader.close();
		
       return distance;
    }*/
    
    private int getInteger(String s){
        String res = "";
        for(int i = 0; i < s.length(); ++i)
            if(Character.isDigit(s.charAt(i)))
                res+=s.charAt(i);

        return Integer.parseInt(res);
    }
  
}
