package tools;

import android.graphics.Color;

import java.util.Random;

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

    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

}
