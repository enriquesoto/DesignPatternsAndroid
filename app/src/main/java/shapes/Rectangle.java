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
    public void draw(RelativeLayout mFrame, final float x, final float y, Context aContext) {
        final Bitmap mBitmap= BitmapFactory.decodeResource(aContext.getResources(), R.drawable.rectangle);
        final Paint mPainter = new Paint();
        mPainter.setAntiAlias(true);

        mFrame.addView(new View(aContext){
            @Override
            protected synchronized void onDraw(Canvas canvas) {
                canvas.drawBitmap(mBitmap,x,y,mPainter);
            }
        });
    }
}
