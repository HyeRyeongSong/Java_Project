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
    private Color recColor;  //사각형 색깔상태
    private int offX;
    private int offY;  //마우스 오프셋좌표
    private Mode mode;  //현재 에디터의 모드

    enum Mode{
        Draw, SelectAndMove
    }

    public RectangleEditor()
    {
        ar = new ArrayList<>();  //사각형 속성 배열 생성

        //현재 마우스 상태 저장
        isDragged = false;
        isClicked = false;

        recColor = Color.BLUE;  //사각형 색상
        mode = Mode.Draw;  //초기 모드: 그리기

        //마우스 리스너 등록
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void changeMode(Mode m)
    {
        mode = m;
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
                g.drawRect((int)x, (int)y,
                        (int)w, (int)h);
            }
        }
        else if(mode == Mode.SelectAndMove)
        {
            //사각형 그릴 색상 설정
            g.setColor(recColor);

            //사각형 그림
            if(isClicked)
                g.fillRect(box.x,box.y,box.width,box.height);
            else
                g.drawRect(box.x, box.y, box.width, box.height);

            //사각형을 이동하기 위하여 사각형의 x,y 좌표와 사각형 내 클릭한 마우스의 좌표가 필요하다
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(mode == Mode.SelectAndMove)
        {
            if(box.contains(new Point(e.getX(), e.getY())))
            {
                if (isClicked)
                    isClicked = false;
                else
                    isClicked = true;
            }

            repaint();
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
            if(box.contains(new Point(e.getX(), e.getY()))){
                //#1 마우스 버튼 누름
                //사각형내 마우스 클릭 상대 좌표를 구함
                //현재 마우스 스크린 좌표에서 사각형 위치 좌표의 차이를 구함
                offX = e.getX() - box.x;
                offY = e.getY() - box.y;

                //드래그 시작을 표시
                if(isClicked)
                    isDragged = true;
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
            ar.add(new AttributeElement(x,y,w,h));
            System.out.print(ar.size());
        }
        else if (mode == Mode.SelectAndMove)
        {
            isDragged = false;
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
                box.x = e.getX() - offX;
                box.y = e.getY() - offY;
            }
            repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}
}
