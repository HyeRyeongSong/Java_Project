import javax.swing.*;
import java.awt.*;

/**
 * Created by m2j97 on 2017-06-05.
 */
public class AttributePane extends JPanel
{
    private RectangleEditor2 re;
    private JTextField x;
    private JTextField y;
    private JTextField w;
    private JTextField h;
    private JTextField text;
    private JComboBox type;
    private JTextField var;

    public AttributePane()
    {
        //"attributePane[3]"에 부착된 'Swing 컴포넌트'
        x = new JTextField();
        y = new JTextField();
        w = new JTextField();
        h = new JTextField();
        text = new JTextField();
        type = new JComboBox();
        var = new JTextField();

        setLayout(new GridLayout(7, 2, 2, 5));
        add(new JLabel("시작 x 좌표"), 0);
        add(x, 1);
        add(new JLabel("시작 y 좌표"), 2);
        add(y, 3);
        add(new JLabel("너비"), 4);
        add(w, 5);
        add(new JLabel("높이"), 6);
        add(h, 7);
        add(new JLabel("컴포넌트의 텍스트 속성값"), 8);
        add(text, 9);
        add(new JLabel("컴포넌트 타입"), 10);
        add(type, 11);
        add(new JLabel("컴포넌트 변수명"), 12);
        add(var, 13);
    }

    public void setEditorPane(RectangleEditor2 e)
    {
        re = e;
    }

    public void setAttribute(AttributeElement e)
    {
        x.setText(Integer.toString(e.getX()));
        y.setText(Integer.toString(e.getY()));
        w.setText(Integer.toString(e.getW()));
        h.setText(Integer.toString(e.getH()));
        text.setText("text");
        var.setText("var");
    }
}
