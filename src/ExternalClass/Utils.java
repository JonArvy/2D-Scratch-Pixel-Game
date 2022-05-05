package ExternalClass;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Utils {
    
    public static BufferedImage loadImage(String filename) {
        try {
            return ImageIO.read(new File(filename));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
            //System.exit(1);
        }
        return null;
    }
    
    public static ArrayList<String> loadFile(String filename) {
        ArrayList<String> data = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String dat;
            while ((dat = br.readLine()) != null) {
                data.add(dat);
            }
            br.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        return data;
    }
    
    public static void saveFile(String fileName, ArrayList<String> data) {
        try {
            PrintStream fileStream = new PrintStream(new File(fileName));
            //PrintStream fileStream = new PrintStream(new File("src/Levels/worldtesting.txt"));
            for (int i = 0; i < data.size(); i++) {
                fileStream.println(data.get(i));
            }
            fileStream.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
}
