import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * Created by m2j97 on 2017-06-01.
 */
public class RectangleEditor2 extends JPanel implements MouseListener, MouseMotionListener
{

    private Rectangle box; //그려질 사각형 영역
    private ArrayList<AttributeElement> ar;  //저장될 사각형들의 속성 정보

    private JLabel jl;

    private int x;
    private int y;
    private int w;
    private int h; //현재 그려지는 사각형의 좌표, 크기
    private boolean isDragged;  //드래그 상태 여부
    private boolean isClicked;  //클릭 상태 여부
    private boolean isWidDragged;
    private boolean ishiDragged;
    private Color recColor;  //사각형 색깔상태
    private int offX;
    private int offY;  //마우스 오프셋좌표
    private int selectedNum;
    private int before;
    private boolean selected;
    private Mode mode;  //현재 에디터의 모드

    enum Mode{
        Draw, SelectAndMove, ChangeSize, Remove
    }

    public RectangleEditor2()
    {
        ar = new ArrayList<>();  //사각형 속성 배열 생성
        setLayout(null);

        //현재 마우스 상태 저장
        reset();
        selectedNum = -1;
        selected = false;
        isClicked = false;

        recColor = Color.GRAY;  //사각형 색상
        mode = Mode.Draw;  //초기 모드: 그리기

        //마우스 리스너 등록
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void changeMode(Mode m)
    {
        reset();
        mode = m;
    }

    public void reset()
    {
        x = 0;
        y = 0;
        w = 0;
        h = 0;
        isDragged = false;
        isWidDragged = false;
        ishiDragged = false;
    }

    public void createLabel()
    {
        JLabel j = new JLabel();
        j.addMouseListener(this);
        add(j);
        j.setLocation(x, y);
        j.setSize(w, h);
        j.setOpaque(true);
        j.setBackground(recColor);//이미 하나가 선택된 상태에서 그리기 모드 시작하고 다시 되돌아오면 null상태임
        ar.add(new AttributeElement(x, y, w, h));////
        System.out.println("JLabel 생성: " + ar.size());
    }

    public boolean equalElement(AttributeElement e1, AttributeElement e2)
    {
        if(e1.getX() == e2.getX() && e1.getY() == e2.getY() && e1.getW() == e2.getW() && e1.getH() == e2.getH())
            return true;
        else
            return false;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("MouseClicked");
        System.out.println("Mouse - x: " + e.getX() + " y: " +e.getY());

        if (mode != Mode.Draw)
        {
            if(e.getComponent() != this)
            {
                JLabel cur = (JLabel) e.getComponent();

                if(selected)
                {
                    if(cur.getBackground() == Color.BLUE)
                    {
                        cur.setBackground(Color.GRAY);
                        revalidate();
                        repaint();
                        selected = false;
                        isClicked = false;
                    }
                }
                else
                {
                    cur.setBackground(Color.BLUE);
                    revalidate();
                    repaint();
                    selected = true;
                    isClicked = true;
                    jl = cur;
                }
            }
            else
                System.out.println("빈공간 클릭");

            selectedNum = -1;
            AttributeElement cur;
            for(int i = 0; i < ar.size(); i++)
            {
                cur = new AttributeElement(jl.getX(), jl.getY(), jl.getWidth(), jl.getHeight());
                if (equalElement(ar.get(i), cur))
                    selectedNum = i;
            }
            System.out.println("Label selectedNum: " + selectedNum);
            System.out.println("Label before: " + before);

            if(mode == Mode.Remove && selectedNum == before && selectedNum!=-1)
            {
                ar.remove(selectedNum);////
                remove(jl);
                revalidate();
                repaint();
                selectedNum = -1;
            }

            before = selectedNum;
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        System.out.println("MousePressed");
        if(mode == Mode.Draw)
        {
            x = y = w = h = 0;
            x = e.getX();
            y = e.getY();
            System.out.println("x: " + x + ", y: " + y);
            isDragged = true;
        }
        else if(mode == Mode.SelectAndMove)
        {
            System.out.println("selectedNum: " + selectedNum);
            if(selected)
            {
                if (jl.contains(new Point(e.getX(), e.getY())))
                {
                    //#1 마우스 버튼 누름
                    //사각형내 마우스 클릭 상대 좌표를 구함
                    //현재 마우스 스크린 좌표에서 사각형 위치 좌표의 차이를 구함
                    offX = e.getX() - jl.getX();
                    offY = e.getY() - jl.getY();

                    //드래그 시작을 표시
                    if (isClicked)
                        isDragged = true;
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
                    ishiDragged = true;
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
            w = e.getX() - x;
            h = e.getY() - y;
            createLabel();
            isDragged = false;
        }
        else if (mode == Mode.SelectAndMove)
        {
            if (isDragged)
            {
                jl.setLocation(e.getX() - offX, e.getY() - offY);
                ar.get(selectedNum).setX(e.getX() - offX);////
                ar.get(selectedNum).setY(e.getY() - offY);////
                revalidate();
                repaint();
                isDragged = false;
            }
        }
        else if (mode == Mode.ChangeSize)
        {
            if(isWidDragged)
            {
                jl.setSize(e.getX() - jl.getX(), jl.getHeight());
                ar.get(selectedNum).setW(jl.getWidth());////
                revalidate();
                repaint();
                isWidDragged = false;
            }
            else if(ishiDragged)
            {
                jl.setSize(jl.getWidth(), e.getY() - jl.getY());
                ar.get(selectedNum).setH(jl.getHeight());////
                revalidate();
                repaint();
                ishiDragged = false;
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
                Rectangle selectedRec = new Rectangle(ar.get(selectedNum).getX(), ar.get(selectedNum).getY(), ar.get(selectedNum).getW(), ar.get(selectedNum).getH());

                if(e.getX() > selectedRec.getX() + selectedRec.getWidth() - 10 && e.getX() < selectedRec.getX() + selectedRec.getWidth() + 10)
                    setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                else if(e.getY() > selectedRec.getY() + selectedRec.getHeight() - 10 && e.getY() < selectedRec.getY() + selectedRec.getHeight() + 10)
                    setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                else
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }
}
