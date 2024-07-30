package generics.ex02;

public class ColoredPoint extends Point {

    public final Color color;

    public ColoredPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("ColoredPoint (x = %d, y = %d, color = %s", x, y, color);
    }

}
