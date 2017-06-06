import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by m2j97 on 2017-06-04.
 */
public class ElementArray
{
    private ArrayList<AttributeElement> ar;
    private int num;

    private RectangleEditor2 re;
    private AttributePane ap;

    public ElementArray()
    {
        ar = new ArrayList<>();
        num = 0;
    }

    public void setPane(AttributePane ap, RectangleEditor2 re)
    {
        this.ap = ap;
        this.re = re;
        ap.printNoneAttribute();
    }

    public void addElement(int x, int y, int w, int h)
    {
        ar.add(new AttributeElement(x, y, w, h, num));
        num++;
    }

    public void addElement(JLabel jl)
    {
        ar.add(new AttributeElement(jl.getX(), jl.getY(), jl.getWidth(), jl.getHeight(), num));
        num++;
    }

    public AttributeElement getElement(int index)
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
        addElement(jl);////
        System.out.println("JLabel 생성: " + getSize());
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
}
