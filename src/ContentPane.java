import javax.swing.*;
import java.awt.*;

/**
 * Created by HyeRyeongSong on 2017. 5. 10..
 */
public class ContentPane extends JFrame
{
    ContentPane()
    {
        Color color = new Color(171, 202, 220);
        color.darker();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setLayout(new BorderLayout());

        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.setBackground(color);
        jMenuBar.setLayout(new FlowLayout(FlowLayout.LEADING));
        jMenuBar.add(new JMenu("새로 만들기"));
        jMenuBar.add(new JMenu("열기"));
        jMenuBar.add(new JMenu("저장"));
        jMenuBar.add(new JMenu("다른 이름으로 저장"));
        jMenuBar.add(new JMenu(".java 파일 생성"));
        jMenuBar.add(new JMenu("닫기"));

        JPanel jPanel = new JPanel(new BorderLayout());
        contentPane.add(jMenuBar, BorderLayout.NORTH);
        contentPane.add(jPanel, BorderLayout.CENTER);

        JToolBar jToolBar = new JToolBar();
        jToolBar.setBackground(color);
        jToolBar.setLayout(new FlowLayout(FlowLayout.LEADING));
        jToolBar.add(new JButton("새로 만들기"));
        jToolBar.add(new JButton("열기"));
        jToolBar.add(new JButton("저장"));
        jToolBar.add(new JButton("다른 이름으로 저장"));
        jToolBar.add(new JButton(".java 피일 생성"));
        jToolBar.add(new JButton("닫기"));

        jPanel.add(jToolBar, BorderLayout.NORTH);

        JPanel attributePane = new JPanel(new GridLayout(7, 2, 2, 5));
        attributePane.add(new JLabel("시작 x 좌표"), 0);
        attributePane.add(new JTextField(), 1);
        attributePane.add(new JLabel("시작 y 좌표"),2);
        attributePane.add(new JTextField(), 3);
        attributePane.add(new JLabel("너비"), 4);
        attributePane.add(new JTextField(), 5);
        attributePane.add(new JLabel("높이"), 6);
        attributePane.add(new JTextField(), 7);
        attributePane.add(new JLabel("컴포넌트의 텍스트 속성값"), 8);
        attributePane.add(new JTextField(), 9);
        attributePane.add(new JLabel("컴포넌트 타입"), 10);

        JComboBox jComboBox = new JComboBox();

        attributePane.add(jComboBox, 11);

        attributePane.add(new JLabel("컴포넌트 변수명"), 12);
        attributePane.add(new JTextField(), 13);

        JPanel editorPane = new JPanel(null);
        editorPane.setBackground(Color.WHITE);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, attributePane, editorPane);
        jPanel.add(splitPane, BorderLayout.CENTER);

        setSize(900, 500);
        setVisible(true);

    }


    public static void main(String[] args)
    {
        new ContentPane();
    }
}
