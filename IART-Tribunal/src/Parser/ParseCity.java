package Parser;

import jdk.nashorn.internal.parser.JSONParser;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Created by danny on 07/04/2016.
 */
public class ParseCity {

    private ArrayList<City> cityList;
    private String citiesURL = new String();
    private String htmlCode= new String();

    public ParseCity(String citiesURL) throws IOException {
        if(citiesURL.isEmpty())
            System.err.println("Please, choose a url");
        else {
            this.citiesURL = citiesURL;
            loadHtml();
            FileOutputStream f = new FileOutputStream("teste.txt");
            f.write(htmlCode.getBytes());
            f.close();
        }
    }

    void loadHtml() throws IOException{
        URL url = new URL(citiesURL);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputLine;
        StringBuilder builder = new StringBuilder();
        while ((inputLine = in.readLine()) != null){
            builder.append(inputLine);
        }
        htmlCode = builder.toString();
        in.close();
    }

    void loadCities(){
        System.out.println("Reading html document of cities.. Please wait.");
        URL url = null;
        try {
            url = new URL(citiesURL);
            FileInputStream is = (FileInputStream)url.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            FileOutputStream file = new FileOutputStream("teste.txt");
        } catch (MalformedURLException e) {
            System.err.println("URL constructor error");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Get URL error..");
            e.printStackTrace();
        }







        /*JSONObject obj = new JSONObject();
        JSONObject object = jsonParser.getJSONFromUrl(url);
        String content=object.getString("json key");*/
    }


}
