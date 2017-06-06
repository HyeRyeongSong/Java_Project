package GUI;

import Editor.RectangleEditor2;
import MenuController.MenuToolController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//JFrame을 상속받아 작성한 "윈도우"
public class ContentPane extends JFrame implements ActionListener
{
    private final RectangleEditor2 jp;
    private final JButton b1;
    private final JButton b2;
    private final JButton b3;
    private final JButton b4;
    private final JButton b5;
    private final ElementArray ea;
    private MenuToolController controller;
    /*private final JMenuItem openNewItem;
    private final JMenuItem openFileItem;
    private final JMenuItem saveFileItem;
    private final JMenuItem saveDifFileItem;
    private final JMenuItem saveJavaFileItem;
    private final JMenuItem closeItem;*/

    ContentPane()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //BorderLayout을 가지는 "최상위 컨테이너[0]"
        //"GUI.ContentPane 클래스 (최상위 컨테이너[0]) "의 ContentPane을 알아낸다
        Container contentPane = getContentPane();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setLayout(new BorderLayout());

        ea = new ElementArray();////////////////////////////////////////////////////
        try
        {
            controller = new MenuToolController(ea);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        setTitle(ea.getFileName());

        //"최상위 컨테이너[0]"의 "BorderLayout.NORTH"에 들어갈 "메뉴바[1]"
        MenuBar menuBar = new MenuBar(controller);

        //"최상위 컨테이너[0]"의 "BorderLayout.CENTER"에 들어갈 "jPanel[1]"
        //BorderLayout을 가지는 "jPanel[1]"
        JPanel jPanel = new JPanel(new BorderLayout());
        //"최상위 컨테이너[0]"에 "메뉴바[1]"와 "jPanel[1]"을 부착한다
        contentPane.add(menuBar, BorderLayout.NORTH);
        contentPane.add(jPanel, BorderLayout.CENTER);


        //"jPanel[1]"의 "BorderLayout.NORTH"에 들어갈 "툴바[2]"
        ToolBar toolBar = new ToolBar(controller);

        //"jPanel[1]"에 "툴바[2]"를 부착한다
        jPanel.add(toolBar, BorderLayout.NORTH);

        //"splitPane[2]"의 "newLeftComponent"에 들어갈 "attributePane[3]"
        //(7x2)의 GridLayout을 가지는 "attributePane[3]"
        AttributePane attributePane = new AttributePane(ea);


        //"splitPane[2]"의 "newRightComponent"에 들어갈 "editorPane[3]"
        //배치관리자가 없는 "editorPane[3]"

        /////////////////////////////////////////////////////////editorPane 추가 부분
        jp = new RectangleEditor2(ea);
        JPanel jp2 = new JPanel();
        JPanel editorPane = new JPanel();
        ea.setPane(attributePane, jp);
        jp2.setOpaque(true);
        jp2.setBackground(Color.LIGHT_GRAY);
        jp.setBackground(Color.WHITE);
        editorPane.setLayout(new BorderLayout());
        editorPane.add(jp2,BorderLayout.NORTH);
        editorPane.add(jp,BorderLayout.CENTER);


        b1 = new JButton("그리기");
        b2 = new JButton("선택");
        b3 = new JButton("이동");
        b4 = new JButton("크기 수정");
        b5 = new JButton("삭제");

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);

        jp2.add(b1);
        jp2.add(b2);
        jp2.add(b3);
        jp2.add(b4);
        jp2.add(b5);
        ////////////////////////////////////////////////////////////////
        editorPane.setBackground(Color.WHITE);

        //"jPanel[1]"의 "BorderLayout.CENTER"에 들어갈 "splitPane[2]"
        //"splitPane[2]"에 "attributePane[3]"과 "editorPane[3]"을 부착한다
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, attributePane, editorPane);

        //"jPanel[1]"에 "splitPane[2]"을 부착한다
        jPanel.add(splitPane, BorderLayout.CENTER);

        setSize(800, 700);
        setVisible(true);

    }



    public static void main(String[] args)
    {
        new ContentPane();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object obj = e.getSource();

        if(obj == b1)
        {
            jp.changeMode(RectangleEditor2.Mode.Draw);
        }
        else if(obj == b2)
        {
            jp.changeMode(RectangleEditor2.Mode.Select);
        }
        else if(obj == b3)
        {
            jp.changeMode(RectangleEditor2.Mode.Move);
        }
        else if(obj == b4)
        {
            jp.changeMode(RectangleEditor2.Mode.ChangeSize);
        }
        else if(obj == b5)
        {
            jp.changeMode(RectangleEditor2.Mode.Remove);
        }

    }
}