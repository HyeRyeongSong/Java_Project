import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by m2j97 on 2017-06-01.
 */
public class MainFrame2 extends JFrame implements ActionListener
{
    Container contentPane;
    JButton b1;
    JButton b2;
    JButton b3;
    JButton b4;
    RectangleEditor2 jp;

    MainFrame2()
    {
        setTitle("연습2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = getContentPane();
        setLayout(new BorderLayout());
        jp = new RectangleEditor2();
       // add(jp, BorderLayout.CENTER);

        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        add(jp3,BorderLayout.CENTER);
        jp3.setLayout(new BorderLayout());
        jp3.add(jp2,BorderLayout.NORTH);
        jp3.add(jp,BorderLayout.CENTER);


        b1 = new JButton("이동");
        b2 = new JButton("그리기");
        b3 = new JButton("삭제");
        b4 = new JButton("크기 수정");

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
/*
        add(b1,BorderLayout.NORTH);
        add(b2,BorderLayout.SOUTH);
        add(b3,BorderLayout.EAST);
        add(b4,BorderLayout.WEST);
*/

        jp2.add(b1);
        jp2.add(b2);
        jp2.add(b3);
        jp2.add(b4);


        setSize(1000, 800);
        setVisible(true);
    }

    public static void main(String[] args){
        new MainFrame2();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object obj = e. getSource();

        if(obj == b1)
        {
            jp.changeMode(RectangleEditor2.Mode.SelectAndMove);
        }
        else if(obj == b2)
        {
            jp.changeMode(RectangleEditor2.Mode.Draw);
        }
        else if(obj == b3)
        {
            jp.changeMode(RectangleEditor2.Mode.Remove);
        }
        else if(obj == b4)
        {
            jp.changeMode(RectangleEditor2.Mode.ChangeSize);
        }
    }
}
