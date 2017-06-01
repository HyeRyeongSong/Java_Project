import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class RectangleDraw extends JComponent implements MouseListener, MouseMotionListener
{
    Rectangle box;

    int x, y, w, h;
    boolean isDragged;

    public RectangleDraw(){

        isDragged = false;

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g)
    {
        box = new Rectangle(x,y,w,h);
        g.setColor(Color.RED);
        g.drawRect(box.x, box.y, box.width, box.height);
    }

    public static void main(String[] args)
    {
        JFrame f = new JFrame("GUI BUILDER");
        f.setBounds(0,0,800,800);
        f.add(new RectangleDraw());
        f.setVisible(true);

    }
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e)
    {
        x = e.getX();
        y = e.getY();
        System.out.println("x: " + x + ", y: " + y);
        isDragged = true;
    }

    @Override
    public void mouseReleased(MouseEvent e){
        isDragged = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if(isDragged)
        {
            w = e.getX() - x;
            h = e.getY() - y;
        }
        System.out.println("w: " + w + ", h: " + h);
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}
