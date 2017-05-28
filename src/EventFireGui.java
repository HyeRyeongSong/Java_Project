import javax.swing.*;
import java.awt.*;

/**
 * Created by HyeRyeongSong on 2017. 5. 10..
 */
public class EventFireGui extends JFrame
{
    private static final long serialVersionUID = -711163588504124217L;

    public EventFireGui() {
        super("Event Firer");

        setBounds(100 , 100 , 300 , 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = this.getContentPane();
        JPanel pane = new JPanel();
        JButton buttonStart = new JButton("Start");
        buttonStart.setMnemonic('S');

        pane.add(buttonStart);
        contentPane.add(pane);

        setVisible(true);
    }
}
