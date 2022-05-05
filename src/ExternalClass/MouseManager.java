package ExternalClass;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {
    private int mouseX, mouseY, mouseClickX, mouseClickY, mouseRightClickX, mouseRightClickY, mouseUnClickX, mouseUnClickY;
    private boolean leftClick, rightClick, leftDrag, rightDrag;

    
    
    @Override
    public void mouseClicked(MouseEvent e) {
//        if (e.getButton() == MouseEvent.BUTTON1) {
//            leftClick = true;
//            mouseClickX = e.getX();
//            mouseClickY = e.getY();
//        } else if (e.getButton() == MouseEvent.BUTTON3) {
//            rightClick = true;
//        }
        //System.out.println(leftClick + " " + rightClick);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftClick = true;
            mouseClickX = e.getX();
            mouseClickY = e.getY();
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightClick = true;
            mouseRightClickX = e.getX();
            mouseRightClickY = e.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftClick = false;
            mouseUnClickX = e.getX();
            mouseUnClickY = e.getY();
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightClick = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.mouseUnClickX = e.getX();
        this.mouseUnClickY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public int getMouseClickX() {
        return mouseClickX;
    }

    public int getMouseClickY() {
        return mouseClickY;
    }
    
    public int getMouseRightClickX() {
        return mouseRightClickX;
    }

    public int getMouseRightClickY() {
        return mouseRightClickY;
    }

    public int getMouseUnClickX() {
        return mouseUnClickX;
    }

    public int getMouseUnClickY() {
        return mouseUnClickY;
    }

    public boolean isLeftClick() {
        return leftClick;
    }

    public boolean isRightClick() {
        return rightClick;
    }

    public boolean isLeftDrag() {
        return leftDrag;
    }

    public boolean isRightDrag() {
        return rightDrag;
    }

    
}
