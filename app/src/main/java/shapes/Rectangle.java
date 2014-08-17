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

    Bitmap mBitmap;
    @Override
    public void draw(RelativeLayout mFrame, final float x, final float y,
                     final float width,final int color, Context aContext) {



        mFrame.addView(new View(aContext){

            final Paint mPainter = new Paint();
            public float xPos;
            public float yPos;
            @Override
            protected synchronized void onDraw(Canvas canvas) {
                //canvas.drawBitmap(mBitmap,x-widthRect/2,y-widthRect/2,mPainter);
                mPainter.setColor(color);

                mPainter.setAntiAlias(true);

                xPos = x;
                yPos=y-110;
                canvas.drawRect(xPos-width,yPos-width/2,xPos+width,yPos+width/2,mPainter);
            }
        });
    }
}
