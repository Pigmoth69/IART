package Parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.lang.model.util.Elements;
import javax.swing.text.Document;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * Created by danny on 07/04/2016.
 */
public class ParseCity {

    private ArrayList<City> cityList;
    private String citiesURL = new String();
    private String htmlCode= new String();

    public ParseCity(String citiesURL) throws IOException {
        if(citiesURL.isEmpty())
            System.err.println("Please, enter a url");
        else {
            this.citiesURL = citiesURL;
            loadHtml();
            //parseHtml();
        }
    }
    /*@Brief this functions loads a html page from the web and store it on a file.*/
    void loadHtml() throws IOException{
        URL url = new URL(citiesURL);
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputLine;
        StringBuilder builder = new StringBuilder();
        while ((inputLine = in.readLine()) != null){
            builder.append(inputLine);
        }
        htmlCode = builder.toString();
        FileOutputStream f = new FileOutputStream("merda.txt");
        f.write(htmlCode.getBytes());
        f.close();
        in.close();
    }
    void parseHtml() throws ParserConfigurationException, IOException, SAXException {
       // Document doc = Jsoup.parse(htmlCode);
       // Elements citiesHtml = doc.getElementsByAttributeValue("style", "text-align: center;");

        /*
        Elements citiesHtml = doc.getElementsByAttributeValue("style", "text-align: center;");

        for (Element aCitiesHtml : citiesHtml) {
            String name = aCitiesHtml.children().get(1).text();
            String population = aCitiesHtml.children().get(2).text();
            cities.add(new City(name, parsePopulation(population)));
        }*/

        System.out.println("Finished extracting city names and populations");
    }
    void loadCities(){
        System.out.println("Reading html document of cities.. Please wait.");
        URL url = null;
        try {
            url = new URL(citiesURL);
            FileInputStream is = (FileInputStream)url.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            FileOutputStream file = new FileOutputStream("merda.txt");
        } catch (MalformedURLException e) {
            System.err.println("URL constructor error");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Get URL error..");
            e.printStackTrace();
        }



/*
         Document doc = Jsoup.parse(html);
        Elements citiesHtml = doc.getElementsByAttributeValue("style", "text-align: center;");

        for (Element aCitiesHtml : citiesHtml) {
            String name = aCitiesHtml.children().get(1).text();
            String population = aCitiesHtml.children().get(2).text();
            cities.add(new City(name, parsePopulation(population)));
        }

        System.out.println("Finished extracting city names and populations");*/


/*
        JSONObject obj = new JSONObject();
        JSONObject object = jsonParser.getJSONFromUrl(url);
        String content=object.getString("json key");*/

    }


}
