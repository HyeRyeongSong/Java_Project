package View;

import Controller.EditorController;
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
    private EditorController controller;  //JLabel 관리할 컨트롤러

    private JButton drawButton;  //그리기모드 버튼
    private JButton selectButton;  //선택모드 버튼
    private JButton moveButton;  //이동모드 버튼
    private JButton changeSizeButton;  //크기변경모드 버튼
    private JButton removeButton;  //삭제모드 버튼
    private JPanel buttonArea;  //버튼이 위치할 구역
    private JPanel canvas;  //컴포넌트가 그려질 구역
    private JLabel modeLabel;  //현재 모드를 출력할 Label

    private JLabel jl;  //현재 선택된 컴포넌트
    private int x;  //현재 그려지는 사각형의 좌표, 크기
    private int y;
    private int w;
    private int h;
    private boolean isClicked;  //클릭 상태 여부
    private boolean isDragged;  //드래그 상태 여부
    private boolean isWidDragged;
    private boolean isheiDragged;
    private int offX;  //마우스 오프셋좌표
    private int offY;
    private int selectedNum;  //선택된 컴포넌트의 인덱스 번호
    private boolean selected;  //선택된 컴포넌트 유무 체크
    private Mode mode;  //현재 에디터의 모드
    private boolean isClickedLabel;  //Label 클릭 여부

    public enum Mode  //현재 에디터 모드
    {
        Draw, Select, Move, ChangeSize, Remove
    }

    public EditorPane(EditorController controller)
    {
        //버튼 구역과 캔버스 구역 생성 및 배치, 배경색 지정
        buttonArea = new JPanel();
        canvas = new JPanel();
        buttonArea.setOpaque(true);
        buttonArea.setBackground(Color.LIGHT_GRAY);
        canvas.setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        add(buttonArea,BorderLayout.NORTH);
        add(canvas,BorderLayout.CENTER);

        //모드 버튼 생성
        drawButton = new JButton("그리기");
        selectButton = new JButton("선택");
        moveButton = new JButton("이동");
        changeSizeButton = new JButton("크기 수정");
        removeButton = new JButton("삭제");
        modeLabel = new JLabel("Mode: Draw");

        //모드 버튼에 액션 리스너 등록
        drawButton.addActionListener(this);
        selectButton.addActionListener(this);
        moveButton.addActionListener(this);
        changeSizeButton.addActionListener(this);
        removeButton.addActionListener(this);

        //버튼 구역에 버튼 추가
        buttonArea.add(drawButton);
        buttonArea.add(selectButton);
        buttonArea.add(moveButton);
        buttonArea.add(changeSizeButton);
        buttonArea.add(removeButton);
        buttonArea.add(modeLabel);

        //컨트롤러 등록
        this.controller = controller;
        //캔버스 레이아웃 매니저를 null로 자유위치
        canvas.setLayout(null);

        //정보를 저장할 필드 초기화
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

    /**
     * 모드 변경 메소드
     * @param m 변경할 모드
     */
    public void changeMode(Mode m)
    {
        reset();
        mode = m;
        modeLabel.setText("Mode: " + m.name());
    }

    /**
     * 새 작업 및 모드 변경 시 이전의 저장된 정보 필드 초기화 메소드
     */
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

    /**
     * 캔버스와 저장된 정보를 모두 비우는 메소드 (새 작업시)
     */
    public void clear(){
        canvas.removeAll();
        reset();
        selectedNum = -1;
        selected = false;
        isClicked = false;
        isClickedLabel = false;
        jl = null;
    }

    /**
     * JLabel을 실제로 보여주는 캔버스 Panel을 리턴하는 메소드
     * @return 캔버스 JPanel
     */
    public JPanel getCanvas()
    {
        return canvas;
    }

    /**
     * 선택된 컴포넌트의 ElementArray 상의 인덱스 값을 리턴하는 메소드
     * @return 선택된 컴포넌트(JLabel)의 인덱스 값 리턴
     */
    public int getSelectedNum()
    {
        return selectedNum;
    }

    /**
     * 선택된 컴포넌트 JLabel을 리턴하는 메소드
     * @return 선택된 컴포넌트 JLabel
     */
    public JLabel getSelectedJLabel()
    {
        return jl;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
//        System.out.println("MouseClicked");
//        System.out.println("Mouse - x: " + e.getX() + " y: " +e.getY());
        if(e.getComponent() != canvas && e.getComponent() != buttonArea)  //클릭된 컴포넌트가 캔버스나 버튼 구역이 아닐 경우
        {
            JLabel cur = (JLabel) e.getComponent();  //클릭된 컴포넌트는 JLabel이므로 JLabel로 컴포넌트 정보를 가져옴

            if (mode == Mode.Select)  //현재 모드가 선택 모드일 경우
            {
                //이미 선택된 컴포넌트일 경우 선택 해제(배경색이 이미 파란색인 경우 회색으로 변경)
                if(cur.getBackground() == Color.BLUE)
                {
                    cur.setBackground(Color.GRAY);
                    canvas.revalidate();
                    canvas.repaint();
                    selected = false;
                    isClicked = false;
                }
                //현재 선택한 컴포넌트가 존재하지 않은 경우
                else if(!selected)
                {
                    //해당 클릭된 컴포넌트를 선택 상태로 변경(배경색을 파란색으로)
                    cur.setBackground(Color.BLUE);
                    canvas.revalidate();
                    canvas.repaint();
                    selected = true;
                    isClicked = true;
                    jl = cur;
                }

                //선택된 컴포넌트와 정보가 같은 AttributeElement를 Array에서 찾아 인덱스를 가져옴
                selectedNum = -1;
                if(selected)
                {
                    for (int i = 0; i < ElementArray.getSize(); i++)
                    {
                        if (controller.equals(jl,i))
                            selectedNum = i;
                    }
                }
            }
            else if(mode == Mode.Remove)  //모드가 삭제 모드일 경우
            {
                //선택되어있는 컴포넌트인 경우 삭제
                if(cur.getBackground() == Color.BLUE)
                {
                    for (int i = 0; i < ElementArray.getSize(); i++)
                    {
                        if (controller.equals(jl,i))
                            selectedNum = i;
                    }
                    controller.removeElement(selectedNum);
                    canvas.remove(jl);
                    canvas.revalidate();
                    canvas.repaint();
                    selected = false;
                    isClicked = false;
                    selectedNum = -1;
                }
            }
            //선택된 컴포넌트가 있다면 해당 컴포넌트 정보 출력
            //선택된 컴포넌트가 없다면 기본값으로 보여줌
            if(selectedNum == -1)
                controller.setNonAttribute();
            else
                controller.setAttribute(selectedNum);
        }
        else  //JLabel을 클릭하지 않은 경우 빈공간을 클릭한 것
            System.out.println("빈공간 클릭");
        System.out.println("Label selectedNum: " + selectedNum);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
//        System.out.println("MousePressed");
        if(mode == Mode.Draw)  //그리기 모드인 경우
        {
            x = y = w = h = 0;  //그려질 JLabel의 좌표 크기 정보 초기화
            //클릭된 컴포넌트의 클래스를 가져옴(JLabel 위에서 pressed인 상태인 경우를 위해)
            Class c = e.getComponent().getClass();

            if(ElementArray.getSize()>0 && c.getName().equals("javax.swing.JLabel"))  //클릭된 컴포넌트가 JLabel인 경우
            {
                JLabel tmp = (JLabel) e.getComponent();
                x = tmp.getX() + e.getX();
                y = tmp.getY() + e.getY();
                w = tmp.getX();
                h = tmp.getY();
                isClickedLabel = true;
            }
            else  //빈 캔버스 공간이 클릭된 경우
            {
                x = e.getX();
                y = e.getY();
            }
            System.out.println("x: " + x + ", y: " + y);
            isDragged = true;
        }
        else if(mode == Mode.Move)  //이동 모드인 경우
        {
            System.out.println("selectedNum: " + selectedNum);
            if(selected && e.getComponent() == jl)
            {
                if (jl.contains(new Point(e.getX(), e.getY())))
                {
                    //사각형내 마우스 클릭 상대 좌표를 구함
                    //현재 마우스 스크린 좌표에서 사각형 위치 좌표의 차이를 구함
                    offX = e.getX() - jl.getX();
                    offY = e.getY() - jl.getY();

                    //드래그 시작을 표시
                    if (isClicked)
                    {
                        isDragged = true;
                    }
                }
            }
        }
        else if(mode == Mode.ChangeSize)  //크기변경 모드인 경우
        {
            if(selected)  //선택된 컴포넌트가 있는 경우
            {
                //오른쪽 변이나 아래쪽 변 가까이를 눌렀을 경우 너비 또는 높이 변경 상태임을 체크
                //현재 마우스 스크린 좌표에서 사각형 위치 좌표의 차이를 구함
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
//        System.out.println("MouseReleased");
        if(mode == Mode.Draw)  //그리기 모드인 경우
        {
            if(isClickedLabel)  //JLabel에서부터 그려진 경우
            {
                w = w + e.getX() - x;
                h = h + e.getY() - y;
                isClickedLabel = false;
            }
            else  //빈 화면에서부터 그려진 경우
            {
                w = e.getX() - x;
                h = e.getY() - y;
            }
            controller.createLabel(x, y, w, h);
            isDragged = false;
        }
        else if (mode == Mode.Move)  //이동 모드인 경우
        {
            if (isDragged)  //드래그 상태일 때만 좌표 변경
            {
                jl.setLocation(e.getX() - offX, e.getY() - offY);
                controller.setElementLocation(selectedNum,e.getX() - offX, e.getY() - offY);
                controller.setAttribute(selectedNum);
                canvas.revalidate();
                canvas.repaint();
                isDragged = false;
            }
        }
        else if (mode == Mode.ChangeSize)  // 크기변경 모드인 경우
        {
            if(isWidDragged)  //너비변경인 경우
            {
                jl.setSize(e.getX() - jl.getX(), jl.getHeight());
                controller.setElementSize(selectedNum, e.getX() - jl.getX(), jl.getHeight());
                controller.setAttribute(selectedNum);
                canvas.revalidate();
                canvas.repaint();
                isWidDragged = false;
            }
            else if(isheiDragged)  //높이 변경인 경우
            {
                jl.setSize(jl.getWidth(), e.getY() - jl.getY());
                controller.setElementSize(selectedNum, jl.getWidth(), e.getY() - jl.getY());
                controller.setAttribute(selectedNum);
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
        //크기변경 모드인 경우 오른쪽 변이나 아래쪽 변에 마우스를 가까이 하면 커서 모양이 바뀜
        if(mode == Mode.ChangeSize)
        {
            if(isClicked && selectedNum != -1)
            {
                AttributeElement ae = ElementArray.getElement(selectedNum);
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
        //버튼 입력에 따른 모드 변경 메소드 호출
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
