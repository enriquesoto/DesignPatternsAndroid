package shapes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import enrique.tpapatterndesign.MainActivity;

/**
 * Created by enrique on 04/08/14.
 */
public class Circle implements Shape {

    private Paint myColor = new Paint();
    private float width;
    // Display dimensions
    private int mDisplayWidth, mDisplayHeight;
    RelativeLayout mFrame;

    Circle(int initColor,float width){
        this.myColor.setColor(initColor);
        this.width = width;
        myColor.setAntiAlias(true);

    }



    @Override
    public void draw(RelativeLayout mFrame,final float x, final float y, Context aContext) {
        this.mFrame = mFrame;
        mDisplayHeight = mFrame.getHeight();
        mDisplayWidth = mFrame.getWidth();
        CircleView aCircleView = new CircleView(aContext,x,y);
        mFrame.addView(aCircleView);
        aCircleView.start();
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
        private long mRotate, mDRotate;
        private ScheduledFuture<?> mMoverFuture;



        public CircleView(Context context,float x,float y) {
            super(context);
            this.mXPos = x;
            this.mYPos = y;
            Random r = new Random();

            setSpeedAndDirection(r);

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
        @Override
        public void setSpeedAndDirection(Random r) {
            // Used by test cases

            // TODO - Set movement direction and speed
            // Limit movement speed in the x and y
            // direction to [-3..3].
            int max = 3;
            int min = -3;
            mDx = (r.nextInt(max-min+1)+min);
            mDy = (r.nextInt(max-min+1)+min);


        }
        // Start moving the BubbleView & updating the display
        private void start() {
            // Creates a WorkerThread
            ScheduledExecutorService executor = Executors
                    .newScheduledThreadPool(1);

            // Execute the run() in Worker Thread every REFRESH_RATE
            // milliseconds
            // Save reference to this job in mMoverFuture
            mMoverFuture = executor.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    // TODO - implement movement logic.
                    // Each time this method is run the BubbleView should
                    // move one step. If the BubbleView exits the display,
                    // stop the BubbleView's Worker Thread.
                    // Otherwise, request that the BubbleView be redrawn.

                    //mFrame.removeView(BubbleView.this);
                    if(moveWhileOnScreen())
                        postInvalidate();
                    else
                        stop(false);



                }
            }, 0, REFRESH_RATE, TimeUnit.MILLISECONDS);

        }

        private void stop(boolean popped) {
            if (null != mMoverFuture && mMoverFuture.cancel(true)) {

                // This work will be performed on the UI Thread

                mFrame.post(new Runnable() {
                    @Override
                    public void run() {

                        // TODO - Remove the BubbleView from mFrame

                        mFrame.removeView(CircleView.this);

                        //log("Bubble removed from view!");

                    }
                });
            }

        }

        private boolean moveWhileOnScreen() {
            mXPos += mDx;
            mYPos += mDy;
            if(isOutOfView()) return false;

            return true;
        }

        private boolean isOutOfView() {

            // TODO - Return true if the BubbleView has exited the screen

            if(mXPos > mDisplayWidth || mXPos+width <0)
                return true;
            if(mYPos > mDisplayHeight || mYPos+width <0)
                return true;
            return false;

        }
        @Override
        public void deflect(float velocityX, float velocityY) {
            mDx = velocityX/REFRESH_RATE;
            mDy = velocityY/REFRESH_RATE;
        }


    }

}
