package shapes;

import android.view.View;

import java.util.Random;

/**
 * Created by enrique on 17/08/14.
 */
public interface ShapeView {
    public static final int REFRESH_RATE = 40;

    public boolean intersects(float x, float y);
    public void setSpeedAndDirection(Random r);
    public void deflect(float velocityX, float velocityY) ;
}
