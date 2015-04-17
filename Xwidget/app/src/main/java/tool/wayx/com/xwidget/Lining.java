package tool.wayx.com.xwidget;

/**
 * Created by alex on 4/17/15.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Lining extends View{
    private Paint paint;
    private float cx=30,cy=30,radius=10,startx,stopx;

    public List<LiningInfo> infos=new ArrayList<LiningInfo>();

    public Lining(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        paint.setAntiAlias(true);
        infos.add(new LiningInfo(new Line(new Point(40,30),new Point(70,30)),new Point(30,30),Color.BLUE));

    }
    public static class LiningInfo{
       public Line line;
       public Point point;
       public int color=Color.RED;
       public int lc=Color.GREEN;
       private int n;
       public LiningInfo(Line line,Point point){
           this.line=line;
           this.point=point;

       }
        public LiningInfo(Line line,Point point,int color){
            this.line=line;
            this.point=point;
            this.color=color;
        }
    }
    @Override
    public void onDraw(Canvas canvas){
        for(int i=0;i<infos.size();i++){


            Line line=infos.get(i).line;
            paint.setColor(infos.get(i).color);
            canvas.drawCircle(infos.get(i).point.x,infos.get(i).point.y,radius,paint);

            if(i<infos.size()-1) {

                paint.setColor(Color.GREEN);
                canvas.drawLine(line.start.x, line.start.y, line.stop.x, line.stop.y, paint);
            }
        }
        super.onDraw(canvas);
    }
}
