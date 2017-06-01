import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class RectangleDraw extends JComponent implements MouseListener, MouseMotionListener
{
    Rectangle box;
    ArrayList<AttributeElement> ar;

    int x, y, w, h;
    boolean isDragged;

    public RectangleDraw(){
        ar = new ArrayList<>();
        isDragged = false;

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g)
    {
        box = new Rectangle(x,y,w,h);
        g.setColor(Color.RED);
        g.drawRect(box.x, box.y, box.width, box.height);
        for(int i=0; i<ar.size(); i++) {
            double x = ar.get(i).getX();
            double y = ar.get(i).getY();
            double w = ar.get(i).getW();
            double h = ar.get(i).getH();
            g.drawRect((int)x, (int)y,
                    (int)w, (int)h);
        }
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
        x = y = w = h = 0;
        x = e.getX();
        y = e.getY();
        System.out.println("x: " + x + ", y: " + y);
        isDragged = true;
    }

    @Override
    public void mouseReleased(MouseEvent e){
        isDragged = false;
        repaint();
        ar.add(new AttributeElement(x,y,w,h));
        System.out.print(ar.size());
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
