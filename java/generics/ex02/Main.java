package generics.ex02;

import java.util.*;

// <? super T>에 대한 필요성을 보여주는 예시다.
public class Main {

    public static void main(String[] args) {
        List<ColoredPoint> points = Arrays.asList(
                new ColoredPoint(1, 2, Color.BLACK),
                new ColoredPoint(0, 2, Color.BLUE),
                new ColoredPoint(0, -1, Color.RED)
        );
        expectsPointOrColoredPoint(minWithoutWildcard(points));
        expectsPointOrColoredPoint(minWithWildcard(points));

        expectsPointOrColoredPoint((Point) minWithoutWildcard(points));
        expectsPointOrColoredPoint((Point) minWithWildcard(points));

        // expectsPointOrColoredPoint(Main.<Point>minWithoutWildcard(points));
        expectsPointOrColoredPoint(Main.<Point>minWithWildcard(points));
    }

    public static <T extends Comparable<? super T>> T minWithoutWildcard(Collection<T> collection) {
        Iterator<T> iterator = collection.iterator();
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("Collection is empty");
        }
        T minElement = iterator.next();
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (element.compareTo(minElement) < 0) {
                minElement = element;
            }
        }
        return minElement;
    }

    public static <T extends Comparable<? super T>> T minWithWildcard(Collection<? extends T> collection) {
        Iterator<? extends T> iterator = collection.iterator();
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("Collection is empty");
        }
        T minElement = iterator.next();
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (element.compareTo(minElement) < 0) {
                minElement = element;
            }
        }
        return minElement;
    }

    private static void expectsPointOrColoredPoint(Point p) {
        System.out.println("Overloaded for Point");
    }

    private static void expectsPointOrColoredPoint(ColoredPoint p) {
        System.out.println("Overloaded for ColoredPoint");
    }

}
