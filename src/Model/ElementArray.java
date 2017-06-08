package Model;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by m2j97 on 2017-06-04.
 */
public class ElementArray
{
    private static ArrayList<AttributeElement> ar;
    public static int num;

    private JLabel jl;

    public ElementArray()
    {
        ar = new ArrayList<>();
        num = 0;
    }
    public static void addElement(int x, int y, int w, int h, String text, String type, String var)
    {
        ar.add(new AttributeElement(x, y, w, h, text, type, var));
        num++;
    }

    public void addElement(JLabel jl)
    {
        ar.add(new AttributeElement(jl.getX(), jl.getY(), jl.getWidth(), jl.getHeight(), num));
        num++;
    }

    public static AttributeElement getElement(int index)
    {
        return ar.get(index);
    }

    public void removeElement(int index)
    {
        ar.remove(index);
    }

    public void setElementLocation(int index, int x, int y)
    {
        ar.get(index).setX(x);
        ar.get(index).setY(y);
    }

    public void setElementSize(int index, int w, int h)
    {
        ar.get(index).setW(w);
        ar.get(index).setH(h);
    }

    public void setElement(int index, AttributeElement e)
    {
        ar.set(index, e);
    }

    public static int getSize()
    {
        return ar.size();
    }

    public void clear()
    {
        ar.clear();
    }
}
