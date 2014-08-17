package shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by enrique on 04/08/14.
 */
public class Circle implements Shape {

    @Override
    public void draw(RelativeLayout mFrame,final float x, final float y,
                     final float width,final int color, Context aContext) {

        mFrame.addView(new View(aContext){
            public float xPos;
            public float yPos;
            final Paint mPainter = new Paint();
            @Override
            protected synchronized void onDraw(Canvas canvas) {
                //canvas.drawBitmap(mBitmap,x-widthRect/2,y-widthRect/2,mPainter);
                xPos = x;
                yPos=y-110;
                mPainter.setColor(color);

                mPainter.setAntiAlias(true);

                canvas.drawCircle(xPos,yPos,width/2,mPainter);
                Log.i("xd","Creating Bubble at: x:" + x + "xpos" + xPos + " y:"+y+"ypos" + yPos);
                //canvas.drawRect(x-width,y-width/2,x+width,y+width/2,mPainter);
            }
        });
    }
}
