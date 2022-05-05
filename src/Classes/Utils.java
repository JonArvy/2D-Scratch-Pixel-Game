package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Utils {
    
    public static ArrayList<String> loadFileAsString(String path) {
        //StringBuilder builder = new StringBuilder();
        ArrayList<String> arr = new ArrayList<String>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/" + path));
            String line;
            while ((line = br.readLine()) != null) {
                //builder.append(line + "\n");
                arr.add(line);
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return //builder.toString();
                arr;
    }
    
    public static int parseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
