package GUI;

import Editor.AttributeElement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by m2j97 on 2017-06-05.
 */
public class AttributePane extends JPanel implements ActionListener
{
    private ElementArray ea;

    private JTextField x;
    private JTextField y;
    private JTextField w;
    private JTextField h;
    private JTextField text;
    private JComboBox type;
    private JTextField var;

    private JButton jb;

    public AttributePane(ElementArray ea)
    {
        //"attributePane[3]"에 부착된 'Swing 컴포넌트'
        x = new JTextField();
        y = new JTextField();
        w = new JTextField();
        h = new JTextField();
        text = new JTextField();
        type = new JComboBox();
        var = new JTextField();
        jb = new JButton("적용");
        this.ea = ea;

        setLayout(new BorderLayout());
        JPanel editor = new JPanel(new GridLayout(14, 1, 2, 5));
        add(editor,BorderLayout.CENTER);
        add(jb, BorderLayout.SOUTH);

        type.addItem("None");
        type.addItem("JLabel");
        type.addItem("JButton");

        editor.add(new JLabel("시작 x 좌표"), 0);
        editor.add(x, 1);
        editor.add(new JLabel("시작 y 좌표"), 2);
        editor.add(y, 3);
        editor.add(new JLabel("너비"), 4);
        editor.add(w, 5);
        editor.add(new JLabel("높이"), 6);
        editor.add(h, 7);
        editor.add(new JLabel("컴포넌트의 텍스트 속성값"), 8);
        editor.add(text, 9);
        editor.add(new JLabel("컴포넌트 타입"), 10);
        editor.add(type, 11);
        editor.add(new JLabel("컴포넌트 변수명"), 12);
        editor.add(var, 13);

        jb.addActionListener(this);
    }

    public void printAttribute(AttributeElement e)
    {
        x.setText(Integer.toString(e.getX()));
        y.setText(Integer.toString(e.getY()));
        w.setText(Integer.toString(e.getW()));
        h.setText(Integer.toString(e.getH()));
        text.setText(e.getText());
        type.setSelectedItem(e.getType());
        var.setText(e.getVar());
    }

    public void printNoneAttribute()
    {
        x.setText("0");
        y.setText("0");
        w.setText("0");
        h.setText("0");
        text.setText("");
        type.setSelectedItem("None");
        var.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //변경된 속성 적용
        int X = Integer.parseInt(x.getText());
        int Y = Integer.parseInt(y.getText());
        int W = Integer.parseInt(w.getText());
        int H = Integer.parseInt(h.getText());
        ea.changeElement(X, Y, W, H, text.getText(), (String)type.getSelectedItem(), var.getText());

    }
}
