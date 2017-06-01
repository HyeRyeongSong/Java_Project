import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by m2j97 on 2017-06-01.
 */
public class MainFrame extends JFrame implements ActionListener
{
    Container contentPane;
    JButton b1;
    JButton b2;
    RectangleEditor re;

    MainFrame()
    {
        setTitle("연습");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = getContentPane();

        re = new RectangleEditor();
        b1 = new JButton("선택");
        b2 = new JButton("그리기");
        b1.addActionListener(this);
        b2.addActionListener(this);
        contentPane.add(b1,BorderLayout.NORTH);
        contentPane.add(b2,BorderLayout.SOUTH);
        contentPane.add(re,BorderLayout.CENTER);

        setSize(800, 800);
        setVisible(true);
    }

    public static void main(String[] args){
        new MainFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object obj = e. getSource();

        if(obj == b1)
        {
            re.changeMode(RectangleEditor.Mode.SelectAndMove);
        }
        else if(obj == b2)
        {
            re.changeMode(RectangleEditor.Mode.Draw);
        }
    }
}
