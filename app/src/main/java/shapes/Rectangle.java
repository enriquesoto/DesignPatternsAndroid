package shapes;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.RelativeLayout;

import enrique.tpapatterndesign.R;

/**
 * Created by enrique on 04/08/14.
 */
public class Rectangle implements Shape {


    private Paint myColor = new Paint();

    private float width;

    Rectangle(int initColor,float width){
        this.myColor.setColor(initColor);
        this.width = width;
    }


    @Override
    public void draw(RelativeLayout mFrame, final float x, final float y, Context aContext) {

        mFrame.addView(new RectangleView(aContext,x,y));
    }
    @Override
    public void setColor(int aColor){
        this.myColor.setColor(aColor);
    }
    @Override
    public void setWidth(int width){
        this.width=(float)width;
    }

    public class RectangleView extends View implements ShapeView{

        private float mXPos, mYPos, mDx, mDy;


        public RectangleView(Context context,float x,float y) {
            super(context);

            this.mXPos = x;
            this.mYPos = y;
        }
        @Override
        public synchronized boolean intersects(float x, float y) {

            // TODO - Return true if the BubbleView intersects position (x,y)

            if(x>mXPos && x<mXPos+width*2 && y>mYPos && y<mYPos+width/2)
                return true;

            return false;
        }
        @Override
        protected synchronized void onDraw(Canvas canvas) {

            canvas.drawRect(mXPos-width,mYPos-width/2,mXPos+width,mYPos+width/2,myColor);

            //Log.i("xd","Creating Circle at: x:" + x + "xpos" + xPos + " y:"+y+"ypos" + yPos);
        }
    }
}
