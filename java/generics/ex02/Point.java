package generics.ex02;

public class Point implements Comparable<Point> {

    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("Point (x = %d, y = %d)", x, y);
    }

    @Override
    public int compareTo(Point p) {
        return this.x != p.x ? x - p.x : y - p.y;
    }

}
