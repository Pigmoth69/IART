import Parser.ParseCity;

import java.io.IOException;

/**
 * Created by danny on 07/04/2016.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("IART-Court");
        String URL = new String("https://pt.wikipedia.org/wiki/Lista_de_municípios_de_Portugal_por_população");
        try {
            ParseCity parser = new ParseCity(URL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("IART-FINAL");
    }
}
