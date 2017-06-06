package Editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 * Created by m2j97 on 2017-06-01.
 */
public class RectangleEditor extends JComponent implements MouseListener, MouseMotionListener
{

    private Rectangle box; //그려질 사각형 영역
    private ArrayList<AttributeElement> ar;  //저장될 사각형들의 속성 정보

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
    private Mode mode;  //현재 에디터의 모드

    enum Mode{
        Draw, SelectAndMove, ChangeSize, Remove
    }

    public RectangleEditor()
    {
        ar = new ArrayList<>();  //사각형 속성 배열 생성

        //현재 마우스 상태 저장
        reset();

        recColor = Color.BLUE;  //사각형 색상
        mode = Mode.Draw;  //초기 모드: 그리기


        //마우스 리스너 등록
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void changeMode(Mode m)
    {
        reset();
        repaint();
        mode = m;
    }


    public void reset()
    {
        x = 0;
        y = 0;
        w = 0;
        h = 0;
        isDragged = false;
        isClicked = false;
        isDragged = false;
        isClicked = false;
        isWidDragged = false;
        ishiDragged = false;
        selected = -1;
    }

    //컴포넌트 페인팅
    public void paintComponent(Graphics g)
    {
        if(mode == Mode.Draw)
        {
            box = new Rectangle(x,y,w,h);
            g.setColor(recColor);
            g.drawRect(box.x, box.y, box.width, box.height);
            for(int i=0; i<ar.size(); i++) {
                double x = ar.get(i).getX();
                double y = ar.get(i).getY();
                double w = ar.get(i).getW();
                double h = ar.get(i).getH();
                g.drawRect((int)x, (int)y,(int)w, (int)h);
            }
        }
        else if(mode == Mode.SelectAndMove || mode == Mode.Remove || mode == Mode.ChangeSize)
        {
            //사각형 그릴 색상 설정
            g.setColor(recColor);

            //사각형 그림
            for(int i=0; i<ar.size(); i++) {
                double x = ar.get(i).getX();
                double y = ar.get(i).getY();
                double w = ar.get(i).getW();
                double h = ar.get(i).getH();
                if(i == selected)
                    g.fillRect((int)x, (int)y,(int)w, (int)h);
                else
                    g.drawRect((int)x, (int)y,(int)w, (int)h);
            }

            //사각형을 이동하기 위하여 사각형의 x,y 좌표와 사각형 내 클릭한 마우스의 좌표가 필요하다
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("Mouse - x: " + e.getX() + " y: " +e.getY());
        if(mode == Mode.SelectAndMove)
        {
            selected = -1;
            for(int i = 0; i < ar.size(); i++)
            {
                Rectangle cur = new Rectangle(ar.get(i).getX(), ar.get(i).getY(), ar.get(i).getW(), ar.get(i).getH());

                if (cur.contains(new Point(e.getX(), e.getY())))
                {
                    selected = i;
                    isClicked = true;
                }
                System.out.println("selected: " + selected);

                repaint();
            }
        }
        else if(mode == Mode.Remove)
        {
            int before = selected;
            selected = -1;
            for(int i = 0; i < ar.size(); i++)
            {
                Rectangle cur = new Rectangle(ar.get(i).getX(), ar.get(i).getY(), ar.get(i).getW(), ar.get(i).getH());

                if (cur.contains(new Point(e.getX(), e.getY())))
                {
                    selected = i;
                    isClicked = true;
                }
                System.out.println("selected: " + selected);

                if(isClicked && selected == before && selected!=-1)
                {
                    ar.remove(selected);
                    selected = -1;
                }
                repaint();
            }
        }
        else if(mode ==Mode.ChangeSize)
        {
            selected = -1;
            for(int i = 0; i < ar.size(); i++)
            {
                Rectangle cur = new Rectangle(ar.get(i).getX(), ar.get(i).getY(), ar.get(i).getW(), ar.get(i).getH());

                if (cur.contains(new Point(e.getX(), e.getY())))
                {
                    selected = i;
                    isClicked = true;
                }
                System.out.println("selected: " + selected);

                repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
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
            if(selected != -1)
            {
                Rectangle selectedRec = new Rectangle(ar.get(selected).getX(), ar.get(selected).getY(), ar.get(selected).getW(), ar.get(selected).getH());
                if (selectedRec.contains(new Point(e.getX(), e.getY())))
                {
                    //#1 마우스 버튼 누름
                    //사각형내 마우스 클릭 상대 좌표를 구함
                    //현재 마우스 스크린 좌표에서 사각형 위치 좌표의 차이를 구함
                    offX = e.getX() - selectedRec.x;
                    offY = e.getY() - selectedRec.y;

                    //드래그 시작을 표시
                    if (isClicked)
                        isDragged = true;
                }

            }
        }
        else if(mode == Mode.ChangeSize)
        {
            if(isClicked)
            {
                Rectangle selectedRec = new Rectangle(ar.get(selected).getX(), ar.get(selected).getY(), ar.get(selected).getW(), ar.get(selected).getH());

                if(e.getX() > selectedRec.getX() + selectedRec.getWidth() - 10 && e.getX() < selectedRec.getX() + selectedRec.getWidth() + 10)
                {
                    isWidDragged = true;
                    offX = e.getX() - selectedRec.x;
                }
                else if(e.getY() > selectedRec.getY() + selectedRec.getHeight() - 10 && e.getY() < selectedRec.getY() + selectedRec.getHeight() + 10)
                {
                    ishiDragged = true;
                    offY = e.getY() - selectedRec.y;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if(mode == Mode.Draw)
        {
            isDragged = false;
            repaint();
            //ar.addElement(new Editor.AttributeElement(x,y,w,h,));
            System.out.println(ar.size());
        }
        else if (mode == Mode.SelectAndMove)
        {
            isDragged = false;
        }
        else if (mode == Mode.ChangeSize)
        {
            isWidDragged = false;
            ishiDragged = false;
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if(mode == Mode.Draw)
        {
            if(isDragged)
            {
                w = e.getX() - x;
                h = e.getY() - y;
            }
            System.out.println("w: " + w + ", h: " + h);
            repaint();
        }
        else if(mode == Mode.SelectAndMove)
        {
            if(isDragged){
                ar.get(selected).setX(e.getX() - offX);
                ar.get(selected).setY(e.getY() - offY);
            }
            repaint();
        }
        else if(mode == Mode.ChangeSize)
        {
            if(isWidDragged)
            {
                Rectangle selectedRec = new Rectangle(ar.get(selected).getX(), ar.get(selected).getY(), ar.get(selected).getW(), ar.get(selected).getH());
                offX = e.getX() - selectedRec.x;
                ar.get(selected).setW(offX);
            }
            else if(ishiDragged)
            {
                Rectangle selectedRec = new Rectangle(ar.get(selected).getX(), ar.get(selected).getY(), ar.get(selected).getW(), ar.get(selected).getH());
                offY = e.getY() - selectedRec.y;
                ar.get(selected).setH(offY);
            }
            repaint();
        }
    }

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