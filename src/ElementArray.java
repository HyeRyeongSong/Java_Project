import java.util.ArrayList;

/**
 * Created by m2j97 on 2017-06-04.
 */
public class ElementArray
{
    private ArrayList<AttributeElement> ar;

    public ElementArray()
    {
        ar = new ArrayList<>();
    }

    public void addElement(int x, int y, int w, int h)
    {
        ar.add(new AttributeElement(x, y, w, h));
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
}
