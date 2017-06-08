package View;

import Model.AttributeElement;
import Model.ElementArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by m2j97 on 2017-06-01.
 */
public class EditorPane extends JPanel implements MouseListener, MouseMotionListener, ActionListener
{
    private ElementArray ear;  //저장될 사각형들의 속성 정보

    private JButton drawButton;
    private JButton selectButton;
    private JButton moveButton;
    private JButton changeSizeButton;
    private JButton removeButton;
    private final JPanel canvas;
    private JPanel buttonArea;
    private JLabel modeLabel;

    private JLabel jl;
    private int x;
    private int y;
    private int w;
    private int h; //현재 그려지는 사각형의 좌표, 크기
    private boolean isClicked;  //클릭 상태 여부
    private boolean isDragged;  //드래그 상태 여부
    private boolean isWidDragged;
    private boolean isheiDragged;
    private int offX;  //마우스 오프셋좌표
    private int offY;
    private int selectedNum;
    private boolean selected;
    private Mode mode;  //현재 에디터의 모드
    private boolean isClickedLabel;  //레이블 클릭 여부

    public enum Mode  //현재 에디터 모드
    {
        Draw, Select, Move, ChangeSize, Remove
    }

    public EditorPane(ElementArray ear)
    {
        canvas = new JPanel();
        buttonArea.setOpaque(true);
        buttonArea.setBackground(Color.LIGHT_GRAY);
        canvas.setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        add(buttonArea,BorderLayout.NORTH);
        add(canvas,BorderLayout.CENTER);

        drawButton = new JButton("그리기");
        selectButton = new JButton("선택");
        moveButton = new JButton("이동");
        changeSizeButton = new JButton("크기 수정");
        removeButton = new JButton("삭제");
        modeLabel = new JLabel("Mode: Draw");

        drawButton.addActionListener(this);
        selectButton.addActionListener(this);
        moveButton.addActionListener(this);
        changeSizeButton.addActionListener(this);
        removeButton.addActionListener(this);

        buttonArea.add(drawButton);
        buttonArea.add(selectButton);
        buttonArea.add(moveButton);
        buttonArea.add(changeSizeButton);
        buttonArea.add(removeButton);
        buttonArea.add(modeLabel);

        this.ear = ear;  //사각형 속성 배열 생성
        canvas.setLayout(null);

        //현재 마우스 상태 저장
        reset();
        selectedNum = -1;
        selected = false;
        isClicked = false;
        isClickedLabel = false;
        jl = null;

        mode = Mode.Draw;  //초기 모드: 그리기
        //마우스 리스너 등록
        canvas.addMouseListener(this);
        canvas.addMouseMotionListener(this);
    }

    public void changeMode(Mode m)
    {
        reset();
        mode = m;
        modeLabel.setText("Mode: " + m.name());
    }

    public void reset()
    {
        x = 0;
        y = 0;
        w = 0;
        h = 0;
        isDragged = false;
        isWidDragged = false;
        isheiDragged = false;
    }

    public void clear(){
        canvas.removeAll();
        reset();
        selectedNum = -1;
        selected = false;
        isClicked = false;
        isClickedLabel = false;
        jl = null;
    }

    public JPanel getCanvas()
    {
        return canvas;
    }

    public int getSelectedNum()
    {
        return selectedNum;
    }

    public JLabel getSelectedJLabel()
    {
        return jl;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("MouseClicked");
        System.out.println("Mouse - x: " + e.getX() + " y: " +e.getY());
        if(e.getComponent() != canvas && e.getComponent() != buttonArea)
        {
            JLabel cur = (JLabel) e.getComponent();

            System.out.println("1");
            if (mode == Mode.Select)
            {
                System.out.println("2");

                if(cur.getBackground() == Color.BLUE)
                {
                    cur.setBackground(Color.GRAY);
                    canvas.revalidate();
                    canvas.repaint();
                    selected = false;
                    isClicked = false;
                    System.out.println("3");
                }
                else if(!selected)
                {
                    cur.setBackground(Color.BLUE);
                    canvas.revalidate();
                    canvas.repaint();
                    selected = true;
                    isClicked = true;
                    jl = cur;
                    System.out.println("4");
                }

                selectedNum = -1;
                System.out.println("5");

                if(selected)
                {
                    for (int i = 0; i < ear.getSize(); i++)
                    {
                        if (ear.getElement(i).getX() == jl.getX() && ear.getElement(i).getY() == jl.getY() && ear.getElement(i).getW() == jl.getWidth() && ear.getElement(i).getH() == jl.getHeight())
                            selectedNum = i;
                        System.out.println("6");
                    }
                }
            }
            else if(mode == Mode.Remove)
            {
                if(cur.getBackground() == Color.BLUE)
                {
                    for (int i = 0; i < ear.getSize(); i++)
                    {
                        if (ear.getElement(i).getX() == jl.getX() && ear.getElement(i).getY() == jl.getY() && ear.getElement(i).getW() == jl.getWidth() && ear.getElement(i).getH() == jl.getHeight())
                            selectedNum = i;
                        System.out.println("-");
                    }
                    ear.removeElement(selectedNum);////
                    canvas.remove(jl);
                    canvas.revalidate();
                    canvas.repaint();
                    selected = false;
                    isClicked = false;
                    selectedNum = -1;
                }
            }
            if(selectedNum == -1)
                ear.setNonAttribute();
            else
                ear.setAttribute(selectedNum);
        }
        else
            System.out.println("빈공간 클릭");
        System.out.println("Label selectedNum: " + selectedNum);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        System.out.println("MousePressed");
        if(mode == Mode.Draw)
        {
            x = y = w = h = 0;
            Class c = e.getComponent().getClass();
            System.out.println(c.getName());

            if(ear.getSize()>0 && c.getName().equals("javax.swing.JLabel"))
            {
                JLabel tmp = (JLabel) e.getComponent();
                x = tmp.getX() + e.getX();
                y = tmp.getY() + e.getY();
                w = tmp.getX();
                h = tmp.getY();
                isClickedLabel = true;
            }
            else
            {
                x = e.getX();
                y = e.getY();
            }
            System.out.println("x: " + x + ", y: " + y);
            isDragged = true;
        }
        else if(mode == Mode.Move)
        {
            System.out.println("selectedNum: " + selectedNum);
            System.out.println("7");
            if(selected && e.getComponent() == jl)
            {
                System.out.println("8");
                if (jl.contains(new Point(e.getX(), e.getY())))
                {
                    System.out.println("9");
                    //#1 마우스 버튼 누름
                    //사각형내 마우스 클릭 상대 좌표를 구함
                    //현재 마우스 스크린 좌표에서 사각형 위치 좌표의 차이를 구함
                    offX = e.getX() - jl.getX();
                    offY = e.getY() - jl.getY();

                    //드래그 시작을 표시
                    if (isClicked)
                    {
                        isDragged = true;
                        System.out.println("10");
                    }
                }
            }
        }
        else if(mode == Mode.ChangeSize)
        {
            if(selected)
            {
                if(e.getX() > jl.getX() + jl.getWidth() - 10 && e.getX() < jl.getX() + jl.getWidth() + 10)
                {
                    isWidDragged = true;
                    offX = e.getX() - jl.getX();
                }
                else if(e.getY() > jl.getY() + jl.getHeight() - 10 && e.getY() < jl.getY() + jl.getHeight() + 10)
                {
                    isheiDragged = true;
                    offY = e.getY() - jl.getY();
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        System.out.println("MouseReleased");
        if(mode == Mode.Draw)
        {
            if(isClickedLabel)
            {
                w = w + e.getX() - x;
                h = h + e.getY() - y;
                isClickedLabel = false;
            }
            else
            {
                w = e.getX() - x;
                h = e.getY() - y;
            }
            ear.createLabel(x, y, w, h);
            isDragged = false;
        }
        else if (mode == Mode.Move)
        {
            if (isDragged)
            {
                jl.setLocation(e.getX() - offX, e.getY() - offY);
                ear.setElementLocation(selectedNum,e.getX() - offX, e.getY() - offY);
                ear.setAttribute(selectedNum);
                canvas.revalidate();
                canvas.repaint();
                isDragged = false;
            }
        }
        else if (mode == Mode.ChangeSize)
        {
            if(isWidDragged)
            {
                jl.setSize(e.getX() - jl.getX(), jl.getHeight());
                ear.setElementSize(selectedNum, e.getX() - jl.getX(), jl.getHeight());
                ear.setAttribute(selectedNum);
                canvas.revalidate();
                canvas.repaint();
                isWidDragged = false;
            }
            else if(isheiDragged)
            {
                jl.setSize(jl.getWidth(), e.getY() - jl.getY());
                ear.setElementSize(selectedNum, jl.getWidth(), e.getY() - jl.getY());
                ear.setAttribute(selectedNum);
                canvas.revalidate();
                canvas.repaint();
                isheiDragged = false;
            }
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("x: "+ e.getX() + " y: " + e.getY());
    }
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {
        if(mode == Mode.ChangeSize)
        {
            if(isClicked && selectedNum != -1)
            {
                AttributeElement ae = ear.getElement(selectedNum);
                if(e.getX() > ae.getX() + ae.getW() - 10 && e.getX() < ae.getX() + ae.getW() + 10)
                    setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                else if(e.getY() > ae.getY() + ae.getH() - 10 && e.getY() < ae.getY() + ae.getH() + 10)
                    setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                else
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object obj = e.getSource();

        if(obj == drawButton)
        {
            changeMode(EditorPane.Mode.Draw);
        }
        else if(obj == selectButton)
        {
            changeMode(EditorPane.Mode.Select);
        }
        else if(obj == moveButton)
        {
            changeMode(EditorPane.Mode.Move);
        }
        else if(obj == changeSizeButton)
        {
            changeMode(EditorPane.Mode.ChangeSize);
        }
        else if(obj == removeButton)
        {
            changeMode(EditorPane.Mode.Remove);
        }
    }
}
