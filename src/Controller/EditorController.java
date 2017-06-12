package Controller;

import Model.AttributeElement;
import Model.ElementArray;
import View.AttributePane;
import View.EditorPane;

import javax.swing.*;
import java.awt.*;

/**
 * Created by m2j97 on 2017-06-09.
 */
public class EditorController
{
    private EditorPane ep;
    private AttributePane ap;
    private ElementArray ear;
    private JLabel jl;  //현재 컴포넌트 저장

    public EditorController(ElementArray ear)
    {
        this.ear = ear;
    }

    /**
     * AttributePane과 EditorPane을 필드로 저장시키기 위한 메소드
     * @param ap AttributePane
     * @param ep EditorPane
     */
    public void setPanes(AttributePane ap, EditorPane ep)
    {
        this.ap = ap;
        this.ep = ep;
        setNonAttribute();
    }

    /**
     * AttributePane의 기본값 출력 메소드(컴포넌트가 선택되지 않았을 경우 사용)
     */
    public void setNonAttribute()
    {
        ap.printNoneAttribute();
    }

    /**
     * 해당 선택된 컴포넌트의 속성 정보를 AttributePane에 출력하는 메소드
     * @param num 해당 컴포넌트의 ElementArray 상의 index
     */
    public void setAttribute(int num)
    {
        ap.printAttribute(ear.getElement(num));
    }

    /**
     * 컴포넌트의 변경된 속성대로 속성 Element를 수정하는 메소드
     * @param x  x좌표
     * @param y  y좌표
     * @param w  너비
     * @param h  높이
     * @param text  텍스트 속성
     * @param type  타입
     * @param var  변수명
     */
    public void changeElement(int x, int y, int w, int h, String text, String type, String var)
    {
        String newVar = var;
        String newType = type;
        int i;

        for(i = 0; i < ear.getSize(); i++)
            if(ear.getElement(i).getVar().equals(newVar))
                break;

        if(i!=ear.getSize())
            newVar = ear.getElement(ep.getSelectedNum()).getVar();

        if(newType.equals("None"))
            newType = ear.getElement(ep.getSelectedNum()).getType();


        AttributeElement n = new AttributeElement(x, y, w, h, text, newType, newVar);
        ear.setElement(ep.getSelectedNum(), n);
        ep.getSelectedJLabel().setLocation(x,y);
        ep.getSelectedJLabel().setSize(w,h);

        ap.printAttribute(ear.getElement(ep.getSelectedNum()));
        System.out.println("배열 사이즈:" + ear.getSize());
    }

    /**
     * 해당 컴포넌트의 좌표를 변경하는 메소드
     * @param index 해당 컴포넌트의 ElementArray 상의 index
     * @param x x좌표
     * @param y y좌표
     */
    public void setElementLocation(int index, int x, int y)
    {
        ear.setElementLocation(index, x, y);
    }

    /**
     * 해당 컴포넌트의 크기를 변경하는 메소드
     * @param index 해당 컴포넌트의 ElementArray 상의 index
     * @param w 너비
     * @param h 높이
     */
    public void setElementSize(int index, int w, int h)
    {
        ear.setElementSize(index, w, h);
    }

    /**
     * 해당 컴포넌트가 삭제될 때 ElementArray 상에 저장된 속성정보 삭제하는 메소드
     * @param index 삭제된 컴포넌트가 저장된 ElementArray 상의 index
     */
    public void removeElement(int index)
    {
        ear.removeElement(index);
    }

    /**
     * EditorPane의 캔버스 상에서 컴포넌트를 그릴 때 JLabel 생성 및 속성 정보 저장 메소드
     * @param x x좌표
     * @param y y좌표
     * @param w 너비
     * @param h 높이
     */
    public void createLabel(int x, int y, int w, int h)
    {
        paintLabel(x, y, w, h);
        ear.addElement(jl);
        System.out.println("JLabel 생성: " + ear.getSize());
    }

    /**
     * 캔버스 상에서 컴포넌트를 그릴 때 JLabel 생성 메소드
     * @param x x좌표
     * @param y y좌표
     * @param w 너비
     * @param h 높이
     */
    public void paintLabel(int x, int y, int w, int h)
    {
        jl = new JLabel();
        jl.addMouseListener(ep);
        ep.getCanvas().add(jl);
        jl.setLocation(x, y);
        jl.setSize(w, h);
        jl.setOpaque(true);
        jl.setBackground(Color.GRAY);
        testPrint();
    }

    /**
     * 새 파일 작업시 현재까지 저장되어있는 Pane과 ElementArray의 정보를 모두 지우는 메소드
     */
    public void clear()
    {
        ear.clear();
        ep.clear();
        ap.printNoneAttribute();
        ElementArray.num = 0;
        ep.getCanvas().revalidate();
        ep.getCanvas().repaint();
    }

    /**
     * ElementArray에 속성정보로만 저장되어있는 컴포넌트를 모두 그리는 메소드
     */
    public void loadComponent()
    {
        for(int i = 0; i < ear.getSize(); i++)
        {
            paintLabel(ear.getElement(i).getX(), ear.getElement(i).getY(), ear.getElement(i).getW(), ear.getElement(i).getH());
        }
    }

    /**
     * 해당 컴포넌트와 ElementArray 상에 저장된 속성 정보와의 일치 여부 판단 메소드
     * @param jl 비교할 컴포넌트(JLabel)
     * @param index 비교할 ElementArray 상의 속성 정보의 index
     * @return 일치할 경우 true, 불일치할 경우 false
     */
    public boolean equals(JLabel jl, int index)
    {
        if(ear.getElement(index).getX() == jl.getX() && ear.getElement(index).getY() == jl.getY() && ear.getElement(index).getW() == jl.getWidth() && ear.getElement(index).getH() == jl.getHeight())
            return true;
        else
            return false;
    }

    /**
     * 속성 정보를 모두 출력하는 메소드(테스트용)
     */
    public void testPrint()
    {
        for(int i = 0; i < ear.getSize(); i++)
        {
            System.out.println(i + "번째 속성정보-");
            System.out.println("x: " + ear.getElement(i).getX() + " y: " + ear.getElement(i).getY());
            System.out.println("w: " + ear.getElement(i).getW() + " h: " + ear.getElement(i).getH());
            System.out.println("text: " + ear.getElement(i).getText() + " type: " + ear.getElement(i).getType() + " var: " + ear.getElement(i).getVar());
        }
        System.out.println(ear.getSize());
    }
}
