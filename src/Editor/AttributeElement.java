package Editor;

/**
 * Created by m2j97 on 2017-06-01.
 */
public class AttributeElement
{
    private int x;
    private int y;
    private int w;
    private int h;
    private String text;
    private String type;
    private String var;

    public AttributeElement(int x, int y, int w, int h, int index)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        text = "";
        this.type = "JLabel";
        var = "Component" + Integer.toString(index);
    }

    public AttributeElement(int x, int y, int w, int h, String text, String type, String var)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.text = text;
        this.type = type;
        this.var = var;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getW()
    {
        return w;
    }

    public int getH()
    {
        return h;
    }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y;}

    public void setW(int w) { this.w = w; }

    public void setH(int h) { this.h = h;}

    public String getText()
    {
        return text;
    }

    public String getType()
    {
        return type;
    }


    public String getVar()
    {
        return var;
    }
}
