package GUI;

import Editor.AttributeElement;
import Editor.RectangleEditor2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by m2j97 on 2017-06-04.
 */
public class ElementArray
{
    private String FileName;
    private static ArrayList<AttributeElement> ar;
    public static int num;

    private RectangleEditor2 re;
    private AttributePane ap;

    public ElementArray()
    {
        ar = new ArrayList<>();
        FileName = "NoNamed";
        num = 0;
    }

    public String getFileName()
    {
        return FileName;
    }

    public String setDefaultFileName()
    {
        FileName = "NoNamed";
        return FileName;
    }

    public void setPane(AttributePane ap, RectangleEditor2 re)
    {
        this.ap = ap;
        this.re = re;
        ap.printNoneAttribute();
    }

/*    public void addElement(int x, int y, int w, int h)
    {
        ar.add(new Editor.AttributeElement(x, y, w, h, num));
        num++;
    }
*/
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

    public static void setElementLocation(int index, int x, int y)
    {
        ar.get(index).setX(x);
        ar.get(index).setY(y);
    }

    public static void setElementSize(int index, int w, int h)
    {
        ar.get(index).setW(w);
        ar.get(index).setH(h);
    }

    public int getSize()
    {
        return ar.size();
    }

    public void setAttribute()
    {
        ap.printNoneAttribute();
    }

    public void setAttribute(int num)
    {
        ap.printAttribute(ar.get(num));
    }

    public void changeElement(int x, int y, int w, int h, String text, String type, String var)
    {
        String newVar = var;
        String newType = type;
        int i;

        for(i = 0; i < ar.size(); i++)
            if(ar.get(i).getVar().equals(newVar))
                break;

        if(i!=ar.size())
            newVar = ar.get(re.getSelectedNum()).getVar();

        if(newType.equals("None"))
            newType = ar.get(re.getSelectedNum()).getType();


        AttributeElement n = new AttributeElement(x, y, w, h, text, newType, newVar);
        ar.set(re.getSelectedNum(), n);
        re.getSelectedJLabel().setLocation(x,y);
        re.getSelectedJLabel().setSize(w,h);

        ap.printAttribute(ar.get(re.getSelectedNum()));
        System.out.println("배열 사이즈:" + ar.size());
    }

    public void createLabel(int x, int y, int w, int h)
    {
        JLabel jl = new JLabel();
        jl.addMouseListener(re);
        re.add(jl);
        jl.setLocation(x, y);
        jl.setSize(w, h);
        jl.setOpaque(true);
        jl.setBackground(Color.GRAY);
        addElement(jl);
        System.out.println("JLabel 생성: " + getSize());
        testPrint();
    }

    public void clear()
    {
        ar.clear();
        re.clear();
        ap.printNoneAttribute();
        num = 0;
        re.revalidate();
        re.repaint();
    }

    public void testPrint()
    {
        for(int i = 0; i < ar.size(); i++)
        {
            System.out.println(i + "번째 속성정보-");
            System.out.println("x: " + ar.get(i).getX() + " y: " + ar.get(i).getY());
            System.out.println("w: " + ar.get(i).getW() + " h: " + ar.get(i).getH());
            System.out.println("text: " + ar.get(i).getText() + " type: " + ar.get(i).getType() + " var: " + ar.get(i).getVar());
        }
    }
}