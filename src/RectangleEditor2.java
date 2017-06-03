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
    private int selected;
    private boolean selectedd;
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
        selectedd = false;
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
        selected = -1;
        isWidDragged = false;
        ishiDragged = false;

        jl = null;
    }

    public void createLabel()
    {
        jl = new JLabel();
        jl.addMouseListener(this);
        add(jl);
        jl.setLocation(x, y);
        jl.setSize(w, h);
        jl.setOpaque(true);
        jl.setBackground(recColor);
        jl = null;
        ar.add(new AttributeElement(x, y, w, h));
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
        System.out.println("Mouse - x: " + e.getX() + " y: " +e.getY());
        if (mode != Mode.Draw)
        {
            if(e.getComponent() != this)
            {
                JLabel cur = (JLabel) e.getComponent();
                if (isClicked)
                {
                    cur.setBackground(Color.GRAY);
                    revalidate();
                    repaint();
                    selectedd = false;
                    isClicked = false;
                }
                else
                {
                    if (!selectedd)
                    {
                        cur.setBackground(Color.BLUE);
                        revalidate();
                        repaint();
                        selectedd = true;
                        isClicked = true;
                        jl = cur;
                    }
                }
            }
            else
                System.out.println("빈공간 클릭");

            int before = selected;
            selected = -1;
            AttributeElement cur;
            for(int i = 0; i < ar.size(); i++)
            {
                cur = new AttributeElement(jl.getX(), jl.getY(), jl.getWidth(), jl.getHeight());
                if (equalElement(ar.get(i), cur))
                    selected = i;
            }
            System.out.println("Label selected: " + selected);
            System.out.println("Label before: " + before);

            if(mode == Mode.Remove && selected == before && selected!=-1)
            {
                ar.remove(selected);
                remove(jl);
                revalidate();
                repaint();
                selected = -1;
            }
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
            if(selectedd)
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
            if(selectedd)
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
                ar.get(selected).setX(e.getX() - offX);
                ar.get(selected).setY(e.getY() - offY);
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
                ar.get(selected).setW(jl.getWidth());
                revalidate();
                repaint();
                isWidDragged = false;
            }
            else if(ishiDragged)
            {
                jl.setSize(jl.getWidth(), e.getY() - jl.getY());
                ar.get(selected).setH(jl.getHeight());
                revalidate();
                repaint();
                ishiDragged = false;
            }
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {
        if(mode == Mode.ChangeSize)
        {
            if(isClicked && selected != -1)
            {
                Rectangle selectedRec = new Rectangle(ar.get(selected).getX(), ar.get(selected).getY(), ar.get(selected).getW(), ar.get(selected).getH());

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
