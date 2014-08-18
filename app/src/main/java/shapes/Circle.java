package shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by enrique on 04/08/14.
 */
public class Circle implements Shape {

    private Paint myColor = new Paint();
    private float width;

    Circle(int initColor,float width){
        this.myColor.setColor(initColor);
        this.width = width;
    }



    @Override
    public void draw(RelativeLayout mFrame,final float x, final float y, Context aContext) {

        mFrame.addView(new CircleView(aContext,x,y));
    }
    @Override
    public void setColor(int aColor){
        this.myColor.setColor(aColor);
    }
    @Override
    public void setWidth(int width){
        this.width=(float) width;
    }

    private class CircleView extends View implements  ShapeView{

        private float mXPos, mYPos, mDx, mDy;


        public CircleView(Context context,float x,float y) {
            super(context);
            this.mXPos = x;
            this.mYPos = y;
        }

        public synchronized boolean intersects(float x, float y) {

            // TODO - Return true if the BubbleView intersects position (x,y)

            if(x>mXPos && x<mXPos+width*2 && y>mYPos && y<mYPos+width*2)
                return true;

            return false;
        }

        @Override
        protected synchronized void onDraw(Canvas canvas) {
            canvas.drawCircle(mXPos,mYPos,width/2,myColor);
            //Log.i("xd","Creating Circle at: x:" + x + "xpos" + xPos + " y:"+y+"ypos" + yPos);
        }
    }
}
