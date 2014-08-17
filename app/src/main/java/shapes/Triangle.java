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
    @Override
    public void draw(RelativeLayout mFrame, final float x, final float y,
                     final float width,final int color, Context aContext) {




        mFrame.addView(new View(aContext){
            public float xPos;
            public float yPos;

            final Paint mPainter = new android.graphics.Paint();
            @Override
            protected synchronized void onDraw(Canvas canvas) {
                //canvas.drawPaint(mPainter);
                mPainter.setStyle(Paint.Style.FILL_AND_STROKE);

                mPainter.setColor(color);
                mPainter.setStyle(Paint.Style.FILL_AND_STROKE);

                mPainter.setAntiAlias(true);
                xPos = x;
                yPos=y-110;
                Point a = new Point((int)(xPos-width/2),(int)(yPos-width/2));
                Point b = new Point((int)(xPos-width/2),(int) (yPos+width/2));
                Point c = new Point((int)(xPos+width/2), (int)(yPos+width/2));

                //canvas.drawBitmap(mBitmap,x-widthRect/2,y-widthRect/2,mPainter);
                //canvas.drawCircle(x - width, y + width / 2, width/2, mPainter);

                Path path = new Path();
                path.setFillType(Path.FillType.EVEN_ODD);
                path.lineTo(b.x, b.y);
                path.lineTo(c.x, c.y);
                path.lineTo(a.x, a.y);
                path.lineTo(b.x, b.y);
                path.close();

                canvas.drawPath(path, mPainter);

            }
        });

    }
}
