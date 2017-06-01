import javax.swing.*;
import java.awt.*;

/**
 * Created by m2j97 on 2017-06-01.
 */
public class MainFrame extends JFrame
{
    Container contentPane;
    JButton jb;

    MainFrame()
    {
        setTitle("연습");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = getContentPane();

        jb = new JButton("모드 변경");
        contentPane.add(jb,BorderLayout.NORTH);
        contentPane.add(new RectangleMove(),BorderLayout.CENTER);

        setSize(800, 800);
        setVisible(true);
    }

    public static void main(String[] args){
        new MainFrame();
    }
}
