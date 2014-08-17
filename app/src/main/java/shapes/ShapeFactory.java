package shapes;

import java.util.HashMap;

/**
 * Created by enrique on 04/08/14.
 */
public class ShapeFactory {
    private static final HashMap<ShapeType,Shape> shapes = new HashMap<ShapeType,Shape>();

    public static Shape getShape(ShapeType type) {
        Shape shapeImpl = shapes.get(type);

        if (shapeImpl == null) {
            if (type.equals(ShapeType.RECTANGLE)) {
                shapeImpl = new Rectangle();
            } else if (type.equals(ShapeType.CIRCLE)) {
                shapeImpl = new Circle();
            } else if (type.equals(ShapeType.TRIANGLE)) {
                shapeImpl = new Triangle();
            }
            shapes.put(type, shapeImpl);
        }
        return shapeImpl;
    }

    public static enum ShapeType{
        RECTANGLE,CIRCLE,TRIANGLE;
    }
}
