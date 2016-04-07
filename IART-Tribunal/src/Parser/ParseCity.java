package Parser;

import jdk.nashorn.internal.parser.JSONParser;

import java.util.ArrayList;

/**
 * Created by danny on 07/04/2016.
 */
public class ParseCity {

    private ArrayList<City> cityList;
    private String citiesURL = new String();

    ParseCity(String citiesURL){
        if(citiesURL.isEmpty())
            System.err.println("Please, choose a url");
        else {
            this.citiesURL = citiesURL;
            loadCities();
        }
    }

    void loadCities(){
        JSONParser jsonParser = new JSONParser();

        /*JSONObject obj = new JSONObject();
        JSONObject object = jsonParser.getJSONFromUrl(url);
        String content=object.getString("json key");*/
    }


}
