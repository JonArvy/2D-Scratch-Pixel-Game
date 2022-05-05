package ExternalClass;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MapGen {
    private int width, height;
    private String path;
    private int mode;
    private int tiletype;
    
    public MapGen(String path, int width, int height, int mode) {
        this.path = path;
        this.width = width;
        this.height = height;
        this.mode = mode;
        startGenerating();
    }
    
    public void startGenerating() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("src/" + path, true))) {
            pw.println("LEVEL_IMAGE Small.png\n" +
                       "LEVEL_WIDTH " + width + "\n" +
                       "LEVEL_HEIGHT " + height + "\n" +
                       "SPAWN_X 0\n" +
                       "SPAWN_Y 0\n\n" +
                       "LEVELSTART");
            for (int y = 0; y < height; y++) {
                String line = "";
                for (int x = 0; x < width; x++) {
                    if (mode == 3) {
                        tiletype = y * width + x;
                    } else if (mode == 2) {
                        tiletype = ((int) (Math.random() * 50));
                    } else {
                        tiletype = 0;
                    }
                    line += tiletype + " ";
                }
                pw.println(line);
            }
            pw.println("LEVELEND\n\n" +
                    "NPCSSTART\n" +
                    "NPCSEND");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        Scanner sp = new Scanner(System.in);
        System.out.println("Enter Width");
        int w = Integer.parseInt(sp.nextLine());
        System.out.println("Enter Height");
        int h = Integer.parseInt(sp.nextLine());
        System.out.println("Enter mode\n 1. Plain\n 2. Randomized\n 3. Counting");
        int mode = Integer.parseInt(sp.nextLine());
        System.out.println("Enter FilePath EX: Levels/text.txt");
        String path = sp.nextLine();
        new MapGen(path, w, h, mode);
    }
}
