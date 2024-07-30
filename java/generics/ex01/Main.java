package generics.ex01;

import java.util.Arrays;
import java.util.List;

// <? super T>에 대한 필요성을 보여주는 예시다.
public class Main {

    public static void main(String[] args) {
        List<Shape> shapeList = Arrays.asList(new Shape(1), new Shape(3), new Shape(2));
        List<Circle> circleList = Arrays.asList(new Circle(1), new Circle(3), new Circle(2));

        // 와일드카드 사용 X 메서드는 Shape 리스트에서는 동작하지만 Circle 리스트에서는 컴파일 에러가 발생한다.
        System.out.println(maxWithoutWildcard(shapeList));
        // System.out.println(maxWithoutWildcard(circleList));

        // 와일드카드 사용 O 메서드는 두 리스트 모두에서 동작한다.
        System.out.println(maxWithWildcard(shapeList));
        System.out.println(maxWithWildcard(circleList));
    }

    static <T extends Comparable<T>> T maxWithoutWildcard(List<T> list) {
        return list.stream().reduce((a, b) -> a.compareTo(b) > 0 ? a : b).orElseThrow();
    }

    static <T extends Comparable<? super T>> T maxWithWildcard(List<? extends T> list) {
        return list.stream().reduce((a, b) -> a.compareTo(b) > 0 ? a : b).orElseThrow();
    }

}