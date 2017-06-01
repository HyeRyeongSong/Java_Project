import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by m2j97 on 2017-06-02.
 */
public class ActionEventFrame extends Frame implements ActionListener
{
    private JButton b1 = new JButton("모드 변경");

    public ActionEventFrame(){
        this.setLayout(new FlowLayout());
        this.b1.addActionListener(this);

        this.add(b1);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object obj = e. getSource();
        if(obj == b1)
        {

        }
    }
}
