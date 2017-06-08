package View;

import Controller.EditorController;
import Controller.MenuToolController;
import Model.ElementArray;

import javax.swing.*;
import java.awt.*;

/**
 * Created by HyeRyeongSong on 2017. 5. 27..
 */
//JFrame을 상속받아 작성한 "윈도우"
public class ContentPane extends JFrame
{
    private ElementArray ear;
    private EditorController editorController;
    private MenuToolController menuToolController;

    ContentPane()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //BorderLayout을 가지는 "최상위 컨테이너[0]"
        //"GUI.ContentPane 클래스 (최상위 컨테이너[0]) "의 ContentPane을 알아낸다
        Container contentPane = getContentPane();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setLayout(new BorderLayout());
        ear = new ElementArray();

        editorController = new EditorController(ear);
        try
        {
            menuToolController = new MenuToolController(editorController);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        setTitle("Components Maker");

        //"최상위 컨테이너[0]"의 "BorderLayout.NORTH"에 들어갈 "메뉴바[1]"
        MenuBar menuBar = new MenuBar(menuToolController);

        //"최상위 컨테이너[0]"의 "BorderLayout.CENTER"에 들어갈 "jPanel[1]"
        //BorderLayout을 가지는 "jPanel[1]"
        JPanel jPanel = new JPanel(new BorderLayout());
        //"최상위 컨테이너[0]"에 "메뉴바[1]"와 "jPanel[1]"을 부착한다
        contentPane.add(menuBar, BorderLayout.NORTH);
        contentPane.add(jPanel, BorderLayout.CENTER);


        //"jPanel[1]"의 "BorderLayout.NORTH"에 들어갈 "툴바[2]"
        ToolBar toolBar = new ToolBar(menuToolController);

        //"jPanel[1]"에 "툴바[2]"를 부착한다
        jPanel.add(toolBar, BorderLayout.NORTH);

        //"splitPane[2]"의 "newLeftComponent"에 들어갈 "attributePane[3]"
        //(7x2)의 GridLayout을 가지는 "attributePane[3]"
        AttributePane attributePane = new AttributePane(editorController);


        //"splitPane[2]"의 "newRightComponent"에 들어갈 "editorPane[3]"
        //배치관리자가 없는 "editorPane[3]"

        /////////////////////////////////////////////////////////editorPane 추가 부분
        EditorPane editorPane = new EditorPane(editorController);
        ////////////////////////////////////////////////////////////////
        editorPane.setBackground(Color.WHITE);
        editorController.setPanes(attributePane, editorPane);

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
}
