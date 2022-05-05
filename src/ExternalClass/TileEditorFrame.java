package ExternalClass;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class TileEditorFrame implements ActionListener {

    private JFrame frame;
    private Canvas imageCanvas, levelCanvas;
    private String title;
    private int frameWidth, frameHeight, imageScrollWidth, imageScrollHeight, levelScrollWidth, levelScrollHeight,
                imageWidth, imageHeight, levelWidth, levelHeight;
    
    private JTextField imageField, levelField;
    private JButton imageButton, levelButton;
    private ScrollPane imageScroll, levelScroll;
    
    private Handler handler;
    
    public TileEditorFrame(Handler handler, String title, int imageScrollWidth, int imageScrollHeight, int levelScrollWidth, int levelScrollHeight) {
        this.handler = handler;
        this.title = title;
        this.frameWidth = imageScrollWidth + levelScrollWidth + 20;
        this.frameHeight = Math.max(imageScrollHeight, levelScrollHeight) + 40;
        this.imageScrollWidth = imageScrollWidth;
        this.imageScrollHeight = imageScrollHeight;
        
        this.levelScrollWidth = levelScrollWidth;
        this.levelScrollHeight = levelScrollHeight;
        
        
        frame = new JFrame(title);
        
        imageField = new JTextField("src/Images/Small.png");
        levelField = new JTextField("src/Levels/world1.txt");
        
        imageButton = new JButton("Load Image");
        levelButton = new JButton("Load Level");
    }
    
    public void showFrame() {
        
        imageScroll = new ScrollPane();
        levelScroll = new ScrollPane();
        
        imageCanvas = new Canvas();
        levelCanvas = new Canvas();
        
        //Frame
        frame.setBounds(0, 0, frameWidth, frameHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        
        imageScroll.setBounds(5, 5, imageScrollWidth, imageScrollHeight);
        levelScroll.setBounds(imageScrollWidth + 10, 5, levelScrollWidth, levelScrollHeight);
        
        //Canvas
        imageCanvas.setSize(new Dimension(imageWidth, imageHeight));
        imageCanvas.setMaximumSize(new Dimension(imageWidth, imageHeight));
        imageCanvas.setMinimumSize(new Dimension(imageWidth, imageHeight));
        imageCanvas.setFocusable(false);
        levelCanvas.setSize(new Dimension(levelWidth, levelHeight));
        levelCanvas.setMaximumSize(new Dimension(levelWidth, levelHeight));
        levelCanvas.setMinimumSize(new Dimension(levelWidth, levelHeight));
        levelCanvas.setFocusable(false);
        
        imageField.setBounds(5, imageScroll.getY() + imageScroll.getHeight() + 5, imageScroll.getWidth() / 2 - 30, 30);
        levelField.setBounds(5, imageField.getY() + imageField.getHeight() + 5, imageScroll.getWidth() / 2 - 30, 30);
        
        imageButton.setBounds(imageField.getX() + imageField.getWidth() + 5, imageField.getY(), 100, 30);
        levelButton.setBounds(levelField.getX() + levelField.getWidth() + 5, levelField.getY(), 100, 30);
        
        frame.add(imageScroll);
        frame.add(levelScroll);
        imageScroll.add(imageCanvas);
        levelScroll.add(levelCanvas);
        frame.add(imageField);
        frame.add(levelField);
        frame.add(imageButton);
        frame.add(levelButton);
        
        frame.setVisible(true);
        
        imageButton.addActionListener(this);
        imageButton.setActionCommand("Image");
        
        levelButton.addActionListener(this);
        levelButton.setActionCommand("Level");
    }

    public JFrame getFrame() {
        return frame;
    }

    public Canvas getImageCanvas() {
        return imageCanvas;
    }

    public Canvas getLevelCanvas() {
        return levelCanvas;
    }

    public ScrollPane getImageScroll() {
        return imageScroll;
    }

    public ScrollPane getLevelScroll() {
        return levelScroll;
    }
    
    public void setImageWidthAndHeight(int width, int height) {
        this.imageWidth = width;
        this.imageHeight = height;
    }
    
    public void setLevelWidthAndHeight(int width, int height) {
        this.levelWidth = width;
        this.levelHeight = height;
    }
    
    public String getImageFileName() {
        return imageField.getText();
    }
    
    public String getLevelFileName() {
        return levelField.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
//        if (action.equals("Image")) {
//            handler.getTileEditor().init();
//        } else if (action.equals("Level")) {
//            handler.getTileEditor().init();
//        }
        handler.getTileEditor().init();
    }
    
}
