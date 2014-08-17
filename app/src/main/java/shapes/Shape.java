package shapes;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by enrique on 04/08/14.
 */
public interface Shape{

    public void draw(RelativeLayout mFrame, final float x,final float y,
                     final float width, final int Color, final Context aContext);

}
