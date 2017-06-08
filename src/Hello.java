import javax.swing.*;
import java.awt.*;

public class Hello extends JFrame{
    Hello()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        JTextField Component0 = new JTextField();
        Component0.setBounds(112, 260, 296, 57);
        Component0.setText("");
        contentPane.add(Component0);
        JButton Component1 = new JButton();
        Component1.setBounds(237, 83, 134, 301);
        Component1.setText("");
        contentPane.add(Component1);
        JLabel Component2 = new JLabel();
        Component2.setBounds(418, 70, 118, 222);
        Component2.setText("efasfasfdsafdsagrwwerwfsdfs");
        contentPane.add(Component2);
        setSize(800, 700);
        setVisible(true);
    }
    public static void main(String[] args)
    {
        Hello newObject = new Hello();
    }
}