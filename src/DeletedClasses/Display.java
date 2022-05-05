package DeletedClasses;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Display implements ActionListener {
    private JFrame imageFrame, levelFrame, toolBox;
    private Canvas imageCanvas, levelCanvas;
    private String imageTitle, levelTitle;
    private int imageWidth, imageHeight, levelWidth, levelHeight;
    
    private JTextField imageField, levelField;
    private JButton imageButton, levelButton;
    
    public Display(String imageTitle, String levelTitle, int imageWidth, int imageHeight, int levelWidth, int levelHeight) {
        this.imageTitle = imageTitle;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        
        this.levelTitle = levelTitle;
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
        
        showFrame();
    }
    
    public void showFrame() {
        //Image Frame
        imageFrame = new JFrame(imageTitle);
        imageFrame.setBounds(0, 0, imageWidth, imageHeight);
        imageFrame.setResizable(false);
        imageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        imageFrame.setVisible(true);
        
        imageCanvas = new Canvas();
        imageCanvas.setSize(new Dimension(imageWidth, imageHeight));
        imageCanvas.setMaximumSize(new Dimension(imageWidth, imageHeight));
        imageCanvas.setMinimumSize(new Dimension(imageWidth, imageHeight));
        imageCanvas.setFocusable(false);
        
        imageFrame.add(imageCanvas);
        
        //Level Frame
        
        levelFrame = new JFrame(levelTitle);
        levelFrame.setBounds(imageFrame.getX() + imageWidth, 0, levelWidth, levelHeight);
        levelFrame.setResizable(false);
        levelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        levelFrame.setVisible(true);
        
        levelCanvas = new Canvas();
        levelCanvas.setSize(new Dimension(levelWidth, levelHeight));
        levelCanvas.setMaximumSize(new Dimension(levelWidth, levelHeight));
        levelCanvas.setMinimumSize(new Dimension(levelWidth, levelHeight));
        levelCanvas.setFocusable(false);
        
        levelFrame.add(levelCanvas);
        
        showToolBox();
    }
    
    public void showToolBox() {
        toolBox = new JFrame("ToolBox");
        toolBox.setBounds(0, imageFrame.getY() + imageHeight, imageWidth, 200);
        toolBox.setResizable(false);
        toolBox.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        imageField = new JTextField();
        levelField = new JTextField();
        imageField.setBounds(5, 5, imageWidth / 2, 30);
        levelField.setBounds(5, imageField.getY() + imageField.getHeight() + 5, imageWidth / 2, 30);
        
        imageButton = new JButton("Load Image");
        levelButton = new JButton("Load Level");
        imageButton.setBounds(imageField.getX() + imageField.getWidth(), 5, 100, 30);
        levelButton.setBounds(levelField.getX() + levelField.getWidth(), levelField.getY(), 100, 30);
        
        toolBox.add(imageField);
        toolBox.add(levelField);
        toolBox.add(imageButton);
        toolBox.add(levelButton);
        
        toolBox.setLayout(null);
        toolBox.setVisible(true);
        
        imageButton.addActionListener(this);
        imageButton.setActionCommand("Image");
        
        levelButton.addActionListener(this);
        levelButton.setActionCommand("Level");
    }

    public JFrame getImageFrame() {
        return imageFrame;
    }

    public JFrame getLevelFrame() {
        return levelFrame;
    }

    public Canvas getImageCanvas() {
        return imageCanvas;
    }

    public Canvas getLevelCanvas() {
        return levelCanvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Image")) {
            System.out.println("Snap");
        } else if (action.equals("Level")) {
            System.out.println("Lvl");
        }
    }
    

}
