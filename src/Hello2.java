import javax.swing.*;
import java.awt.*;

public class Hello2 extends JFrame{
    Hello2()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        JLabel Component0 = new JLabel();
        Component0.setBounds(35, 33, 140, 237);
        Component0.setText("");
        contentPane.add(Component0);
        JLabel Component1 = new JLabel();
        Component1.setBounds(237, 83, 134, 301);
        Component1.setText("");
        contentPane.add(Component1);
        JLabel Component2 = new JLabel();
        Component2.setBounds(418, 70, 118, 222);
        Component2.setText("");
        contentPane.add(Component2);
        setSize(800, 700);
        setVisible(true);
    }
    public static void main(String[] args)
    {
        Hello2 newObject = new Hello2();
    }
}