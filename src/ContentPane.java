import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

//JFrame을 상속받아 작성한 "윈도우"
public class ContentPane extends JFrame
{
    ContentPane()
    {
        Color color = new Color(171, 202, 220);
        color.darker();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //BorderLayout을 가지는 "최상위 컨테이너[0]"
        //"ContentPane 클래스 (최상위 컨테이너[0]) "의 ContentPane을 알아낸다
        Container contentPane = getContentPane();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setLayout(new BorderLayout());


        //"최상위 컨테이너[0]"의 "BorderLayout.NORTH"에 들어갈 "메뉴바[1]"
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.setBackground(color);
        //FlowLayout을 가지는 "메뉴바[1]"
        jMenuBar.setLayout(new FlowLayout(FlowLayout.LEADING));
        //"메뉴바[1]"에 부착된 'Swing 컴포넌트'
        jMenuBar.add(new JMenu("새로 만들기"));
        jMenuBar.add(new JMenu("열기"));
        jMenuBar.add(new JMenu("저장"));
        jMenuBar.add(new JMenu("다른 이름으로 저장"));
        jMenuBar.add(new JMenu(".java 파일 생성"));
        jMenuBar.add(new JMenu("닫기"));

        //"최상위 컨테이너[0]"의 "BorderLayout.CENTER"에 들어갈 "jPanel[1]"
        //BorderLayout을 가지는 "jPanel[1]"
        JPanel jPanel = new JPanel(new BorderLayout());
        //"최상위 컨테이너[0]"에 "메뉴바[1]"와 "jPanel[1]"을 부착한다
        contentPane.add(jMenuBar, BorderLayout.NORTH);
        contentPane.add(jPanel, BorderLayout.CENTER);


        //"jPanel[1]"의 "BorderLayout.NORTH"에 들어갈 "툴바[2]"
        JToolBar jToolBar = new JToolBar();
        jToolBar.setBackground(color);
        //FlowLayout을 가지는 "툴바[2]"
        jToolBar.setLayout(new FlowLayout(FlowLayout.LEADING));
        //"툴바[2]"에 부착된 'Swing 컴포넌트'
        jToolBar.add(newAction);
        jToolBar.add(openAction);
        jToolBar.add(saveAction);
        jToolBar.add(saveasAction);
        jToolBar.add(createJavaFileAction);
        jToolBar.add(closeAction);

        //"jPanel[1]"에 "툴바[2]"를 부착한다
        jPanel.add(jToolBar, BorderLayout.NORTH);

        //"splitPane[2]"의 "newLeftComponent"에 들어갈 "attributePane[3]"
        //(7x2)의 GridLayout을 가지는 "attributePane[3]"
        JPanel attributePane = new JPanel(new GridLayout(7, 2, 2, 5));
        //"attributePane[3]"에 부착된 'Swing 컴포넌트'
        attributePane.add(new JLabel("시작 x 좌표"), 0);
        attributePane.add(new JTextField(), 1);
        attributePane.add(new JLabel("시작 y 좌표"), 2);
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

        //"splitPane[2]"의 "newRightComponent"에 들어갈 "editorPane[3]"
        //배치관리자가 없는 "editorPane[3]"
        JPanel editorPane = new JPanel(null);
        editorPane.setBackground(Color.WHITE);

        //"jPanel[1]"의 "BorderLayout.CENTER"에 들어갈 "splitPane[2]"
        //"splitPane[2]"에 "attributePane[3]"과 "editorPane[3]"을 부착한다
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, attributePane, editorPane);

        //"jPanel[1]"에 "splitPane[2]"을 부착한다
        jPanel.add(splitPane, BorderLayout.CENTER);

        setSize(900, 500);
        setVisible(true);

    }

    //"툴바[2]"에 부착할 컴포넌트에 들어갈 이미지 아이콘들
    ImageIcon newIcon = new ImageIcon(
            ContentPane.class.getResource("resource/new.png"));
    ImageIcon openIcon = new ImageIcon(
            ContentPane.class.getResource("resource/open.png"));
    ImageIcon saveIcon = new ImageIcon(
            ContentPane.class.getResource("resource/save.png"));
    ImageIcon saveasIcon = new ImageIcon(
            ContentPane.class.getResource("resource/saveas.png"));
    ImageIcon createjavafileIcon = new ImageIcon(
            ContentPane.class.getResource("resource/create_java_file.png"));
    ImageIcon closeIcon = new ImageIcon(
            ContentPane.class.getResource("resource/close.png"));

    //각 이미지 아이콘을 눌렀을 때의 Action 이벤트들
    Action newAction = new AbstractAction("New", newIcon)
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("New File");
        }
    };
    Action openAction = new AbstractAction("Open", openIcon)
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Open File");
        }
    };
    Action saveAction = new AbstractAction("Save", saveIcon)
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Save File");
        }
    };
    Action saveasAction = new AbstractAction("Save", saveasIcon)
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Save File");
        }
    };
    Action createJavaFileAction = new AbstractAction("CreateJavaFile", createjavafileIcon)
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Create Java File");
        }
    };
    Action closeAction = new AbstractAction("Close", closeIcon)
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Close");
        }
    };


    public static void main(String[] args)
    {
        new ContentPane();
    }
}
