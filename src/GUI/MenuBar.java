package GUI;

import MenuController.MenuToolController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by HyeRyeongSong on 2017. 6. 6..
 */
class MenuBar extends JMenuBar implements ActionListener
{

    static JMenuItem openNewItem;
    static JMenuItem openFileItem;
    static JMenuItem saveFileItem;
    static JMenuItem saveDifFileItem;
    static JMenuItem saveJavaFileItem;
    static JMenuItem closeItem;
    MenuToolController controller;

    MenuBar(MenuToolController controller)
    {
        Color color = new Color(171, 202, 220);
        color.darker();

        //"최상위 컨테이너[0]"의 "BorderLayout.NORTH"에 들어갈 "메뉴바[1]"
        this.setBackground(color);
        //FlowLayout을 가지는 "메뉴바[1]"
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.controller = controller;  ////////////////////////////////////////////
        JMenu openMenu = new JMenu("열기(O)");
        openMenu.setMnemonic('O');
        //열기 메뉴 아이템
        openNewItem = new JMenuItem("새파일(N)");
        openNewItem.setMnemonic('N');
        openFileItem = new JMenuItem("파일 열기(F)");
        openFileItem.setMnemonic('F');
        openMenu.add(openNewItem);
        openMenu.add(openFileItem);

        JMenu saveMenu = new JMenu("저장(S)");
        saveMenu.setMnemonic('S');
        //저장 메뉴 아이템
        saveFileItem = new JMenuItem("저장(P)");
        saveFileItem.setMnemonic('P');
        saveDifFileItem = new JMenuItem("다른 이름으로 저장(D)");
        saveDifFileItem.setMnemonic('D');
        saveJavaFileItem = new JMenuItem("JAVA 파일로 저장(J)");
        saveJavaFileItem.setMnemonic('J');
        saveMenu.add(saveFileItem);
        saveMenu.add(saveDifFileItem);
        saveMenu.add(saveJavaFileItem);

        JMenu closeMenu = new JMenu("윈도우(W)");
        closeMenu.setMnemonic('W');
        //닫기 메뉴 아이템
        closeItem = new JMenuItem("닫기(C)");
        closeItem.setMnemonic('C');
        closeMenu.add(closeItem);

        //"메뉴바[1]"에 부착된 'Swing 컴포넌트'
        this.add(openMenu);
        this.add(saveMenu);
        this.add(closeMenu);

        openNewItem.addActionListener(this);
        openFileItem.addActionListener(this);
        saveFileItem.addActionListener(this);
        saveDifFileItem.addActionListener(this);
        saveJavaFileItem.addActionListener(this);
        closeItem.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object obj = e.getSource();

        if(obj == MenuBar.openNewItem)
        {
            controller.makeNewFile();
        }
        else if(obj == MenuBar.openFileItem)
        {
            controller.openFile();
        }
        else if(obj == MenuBar.saveFileItem)
        {
            controller.saveFile();
        }
        else if(obj == MenuBar.saveDifFileItem)
        {
            controller.saveasFile();
        }
        else if(obj == MenuBar.saveJavaFileItem)
        {

        }
        else if(obj == MenuBar.closeItem)
        {
            controller.exitProgram();
        }
    }


}
