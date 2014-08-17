package tools;

import android.graphics.Color;

/**
 * Created by enrique on 04/08/14.
 */
public class Generator {

    public static int generateWidth() {

        //return (int) (Math.random() * 150);
        return 100;
    }

    public static int generateColor() {
        int r = (int) (Math.random() * 255);
        int g = (int) (Math.random() * 255);
        int b = (int) (Math.random() * 255);
        return Color.rgb(r, g, b);
    }
}
