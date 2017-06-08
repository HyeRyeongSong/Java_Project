package Controller;

import Model.AttributeElement;
import Model.ElementArray;
import View.AttributePane;
import View.EditorPane;

import javax.swing.*;
import java.awt.*;

/**
 * Created by m2j97 on 2017-06-09.
 */
public class EditorController
{
    private EditorPane ep;
    private AttributePane ap;
    private ElementArray ear;
    private JLabel jl;

    public EditorController(ElementArray ear)
    {
        this.ear = ear;
    }

    public void setPanes(AttributePane ap, EditorPane ep)
    {
        this.ap = ap;
        this.ep = ep;
    }

    public void setNonAttribute()
    {
        ap.printNoneAttribute();
    }

    public void setAttribute(int num)
    {
        ap.printAttribute(ear.getElement(num));
    }

    public void changeElement(int x, int y, int w, int h, String text, String type, String var)
    {
        String newVar = var;
        String newType = type;
        int i;

        for(i = 0; i < ear.getSize(); i++)
            if(ear.getElement(i).getVar().equals(newVar))
                break;

        if(i!=ear.getSize())
            newVar = ear.getElement(ep.getSelectedNum()).getVar();

        if(newType.equals("None"))
            newType = ear.getElement(ep.getSelectedNum()).getType();


        AttributeElement n = new AttributeElement(x, y, w, h, text, newType, newVar);
        ear.setElement(ep.getSelectedNum(), n);
        ep.getSelectedJLabel().setLocation(x,y);
        ep.getSelectedJLabel().setSize(w,h);

        ap.printAttribute(ear.getElement(ep.getSelectedNum()));
        System.out.println("배열 사이즈:" + ear.getSize());
    }

    public void setElementLocation(int index, int x, int y)
    {
        ear.setElementLocation(index, x, y);
    }

    public void setElementSize(int index, int w, int h)
    {
        ear.setElementSize(index, w, h);
    }

    public void removeElement(int index)
    {
        ear.removeElement(index);
    }

    public void createLabel(int x, int y, int w, int h)
    {
        paintLabel(x, y, w, h);
        ear.addElement(jl);
        System.out.println("JLabel 생성: " + ear.getSize());
    }

    public void paintLabel(int x, int y, int w, int h)
    {
        jl = new JLabel();
        jl.addMouseListener(ep);
        ep.getCanvas().add(jl);
        jl.setLocation(x, y);
        jl.setSize(w, h);
        jl.setOpaque(true);
        jl.setBackground(Color.GRAY);
        testPrint();
    }

    public void clear()
    {
        ear.clear();
        ep.clear();
        ap.printNoneAttribute();
        ElementArray.num = 0;
        ep.getCanvas().revalidate();
        ep.getCanvas().repaint();
    }

    public void loadComponent()
    {
        for(int i = 0; i < ear.getSize(); i++)
        {
            paintLabel(ear.getElement(i).getX(), ear.getElement(i).getY(), ear.getElement(i).getW(), ear.getElement(i).getH());
        }
    }

    public boolean equals(JLabel jl, int index)
    {
        if(ear.getElement(index).getX() == jl.getX() && ear.getElement(index).getY() == jl.getY() && ear.getElement(index).getW() == jl.getWidth() && ear.getElement(index).getH() == jl.getHeight())
            return true;
        else
            return false;
    }

    public void testPrint()
    {
        for(int i = 0; i < ear.getSize(); i++)
        {
            System.out.println(i + "번째 속성정보-");
            System.out.println("x: " + ear.getElement(i).getX() + " y: " + ear.getElement(i).getY());
            System.out.println("w: " + ear.getElement(i).getW() + " h: " + ear.getElement(i).getH());
            System.out.println("text: " + ear.getElement(i).getText() + " type: " + ear.getElement(i).getType() + " var: " + ear.getElement(i).getVar());
        }
        System.out.println(ear.getSize());
    }
}
