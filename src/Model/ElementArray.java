package Model;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by m2j97 on 2017-06-04.
 */
public class ElementArray
{
    private static ArrayList<AttributeElement> ar;  //속성 정보를 저장하는 ArrayList
    public static int num;  //생성된 컴포넌트의 수를 세는 필드

    public ElementArray()
    {
        ar = new ArrayList<>();
        num = 0;
    }

    /**
     * 입력받은 속성 정보로 ElementArray에 새 배열 원소를 저장하는 메소드
     * @param x  x좌표
     * @param y  y좌표
     * @param w  너비
     * @param h  높이
     * @param text  텍스트 속성값
     * @param type  컴포넌트타입
     * @param var  변수명
     */
    public static void addElement(int x, int y, int w, int h, String text, String type, String var)
    {
        ar.add(new AttributeElement(x, y, w, h, text, type, var));
        num++;
    }

    /**
     * 입력받은 JLabel 정보로 ElementArray에 새 배열 원소를 저장하는 메소드
     * 단, 텍스트 속성값, 컴포넌트 타입, 변수명은 임의로 지정되어 저장됨
     * @param jl
     */
    public void addElement(JLabel jl)
    {
        ar.add(new AttributeElement(jl.getX(), jl.getY(), jl.getWidth(), jl.getHeight(), num));
        num++;
    }

    /**
     * ElemeneArray에서 해당 index의 원소를 리턴하는 메소드
     * @param index ElementArray 상의 index
     * @return
     */
    public static AttributeElement getElement(int index)
    {
        return ar.get(index);
    }

    /**
     * ElementArray 상에 해당 index의 원소를 삭제하는 메소드
     * @param index ElementArray 상의 index
     */
    public void removeElement(int index)
    {
        ar.remove(index);
    }

    /**
     * ElementArray 상에 해당 index의 원소 좌표값을 변경하는 메소드
     * @param index ElementArray 상의 index
     * @param x x좌표
     * @param y y좌표
     */
    public void setElementLocation(int index, int x, int y)
    {
        ar.get(index).setX(x);
        ar.get(index).setY(y);
    }

    /**
     * ElementArray 상에 해당 index의 원소 좌표값을 변경하는 메소드
     * @param index ElementArray 상의 index
     * @param w 너비
     * @param h 높이
     */
    public void setElementSize(int index, int w, int h)
    {
        ar.get(index).setW(w);
        ar.get(index).setH(h);
    }

    /**
     * ElementArray 상에 해당 index에 입력받은 원소로 변경하는 메소드
     * @param index 변경할 Array 상의 index
     * @param e Array에 들어갈 원소
     */
    public void setElement(int index, AttributeElement e)
    {
        ar.set(index, e);
    }

    /**
     * ElementArray의 크기를 리턴하는 메소드
     * @return  ElementArray의 크기
     */
    public static int getSize()
    {
        return ar.size();
    }

    /**
     * ElementArray를 모두 비우는 메소드
     */
    public void clear()
    {
        ar.clear();
    }
}
