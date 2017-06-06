package GUI;

import MenuController.MenuToolController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by HyeRyeongSong on 2017. 6. 6..
 */
public class ToolBar extends JToolBar
{
    MenuToolController controller;
    ToolBar(MenuToolController controller)
    {
        //FlowLayout을 가지는 "툴바[2]"
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        this.controller = controller;
        //"툴바[2]"에 부착된 'Swing 컴포넌트'
        this.add(newAction);
        this.add(openAction);
        this.add(saveAction);
        this.add(saveasAction);
        this.add(createJavaFileAction);
        this.add(closeAction);
    }

    //"툴바[2]"에 부착할 컴포넌트에 들어갈 이미지 아이콘들
    ImageIcon newIcon = new ImageIcon(
            ContentPane.class.getResource("/resource/new.png"));
    ImageIcon openIcon = new ImageIcon(
            ContentPane.class.getResource("/resource/open.png"));
    ImageIcon saveIcon = new ImageIcon(
            ContentPane.class.getResource("/resource/save.png"));
    ImageIcon saveasIcon = new ImageIcon(
            ContentPane.class.getResource("/resource/saveas.png"));
    ImageIcon createjavafileIcon = new ImageIcon(
            ContentPane.class.getResource("/resource/create_java_file.png"));
    ImageIcon closeIcon = new ImageIcon(
            ContentPane.class.getResource("/resource/close.png"));

    //각 이미지 아이콘을 눌렀을 때의 Action 이벤트들
    Action newAction = new AbstractAction("New", newIcon)
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            controller.makeNewFile();
        }
    };
    Action openAction = new AbstractAction("Open", openIcon)
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.out.println("Open File");
            controller.makeNewFile(); //충돌 방지 리셋
            //파일 불러오는 코드
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
            controller.exitProgram();
        }
    };

}
