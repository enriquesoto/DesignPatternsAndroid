package shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by enrique on 04/08/14.
 */
public class Triangle implements Shape{


    private Paint myColor = new Paint();
    private float width;


    Triangle(int initColor, float width){
        this.myColor.setColor(initColor);
        //this.myColor.setAntiAlias(true);
        myColor.setStyle(Paint.Style.FILL_AND_STROKE);
        this.width =(float) width;
    }


    @Override
    public void draw(RelativeLayout mFrame, final float x, final float y, Context aContext) {

        mFrame.addView(new TriangleView(aContext,x,y));

    }
    @Override
    public void setColor(int aColor){
        this.myColor.setColor(aColor);
    }
    @Override
    public void setWidth(int width){
        this.width = (float) width;
    }

    public class TriangleView extends View implements ShapeView{

        private float mXPos, mYPos, mDx, mDy;


        public TriangleView(Context context, float x, float y) {
            super(context);
            this.mXPos = x;
            this.mYPos = y;
        }
        @Override
        public synchronized boolean intersects(float x, float y) {

            // TODO - Return true if the BubbleView intersects position (x,y)

            if(x>mXPos && x<mXPos+width*2 && y>mYPos && y<mYPos+width*2)
                return true;

            return false;
        }
        @Override
        protected synchronized void onDraw(Canvas canvas) {

            //Log.i("xd","Creating Circle at: x:" + x + "xpos" + xPos + " y:"+y+"ypos" + yPos);

            Point a = new Point((int)(mXPos-width/2),(int)(mYPos-width/2));
            Point b = new Point((int)(mXPos-width/2),(int) (mYPos+width/2));
            Point c = new Point((int)(mXPos+width/2), (int)(mYPos+width/2));

            //canvas.drawBitmap(mBitmap,x-widthRect/2,y-widthRect/2,mPainter);
            //canvas.drawCircle(x - width, y + width / 2, width/2, mPainter);

            Path path = new Path();
            path.setFillType(Path.FillType.EVEN_ODD);
            path.lineTo(b.x, b.y);
            path.lineTo(c.x, c.y);
            path.lineTo(a.x, a.y);
            path.lineTo(b.x, b.y);
            path.close();

            canvas.drawPath(path, myColor);

        }
    }
}
