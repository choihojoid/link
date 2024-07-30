package generics.ex03;

import java.io.Serializable;
import java.util.function.Consumer;
import java.util.function.Function;

// intersection 타입에 대한 예시인데 상당히 까다롭다.
// 이렇게 할 수도 있다는 정도로만 생각하고 추후 숙련도가 많이 쌓이면 깊게 공부하자.
public class IntersectionType {

    interface Hello extends Function {
        default void hello() {
            System.out.println("Hello");
        }
    }

    interface Hi {
        default void hi() {
            System.out.println("Hi");
        }
    }

    interface DelegateTo<T> {
        T delegate();
    }

    interface DelegateHello extends DelegateTo<String> {
        default void hello() {
            System.out.println("Hello " + delegate());
        }
    }

    interface UpperCase extends DelegateTo<String> {
        default void upperCase() {
            System.out.println(delegate().toUpperCase());
        }
    }

    interface Pair<T> {
        T getFirst();
        T getSecond();
        void setFirst(T first);
        void setSecond(T second);
    }

    static class Name implements Pair<String> {
        String firstName;
        String lastName;
        public Name(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
        @Override
        public String getFirst() {
            return this.firstName;
        }
        @Override
        public String getSecond() {
            return this.lastName;
        }
        @Override
        public void setFirst(String first) {
            this.firstName = first;
        }
        @Override
        public void setSecond(String second) {
            this.lastName = second;
        }
    }

    interface ForwardingPair<T> extends DelegateTo<Pair<T>>, Pair<T> {
        default T getFirst() {
            return delegate().getFirst();
        }
        default T getSecond() {
            return delegate().getSecond();
        }
        default void setFirst(T first) {
            delegate().setFirst(first);
        }
        default void setSecond(T second) {
            delegate().setSecond(second);
        }
    }

    interface Convertible<T> extends DelegateTo<Pair<T>> {
        default void convert(Function<T, T> mapper) {
            Pair<T> pair = delegate();
            pair.setFirst(mapper.apply(pair.getFirst()));
            pair.setSecond(mapper.apply(pair.getSecond()));
        }
    }

    interface Printable<T> extends DelegateTo<Pair<T>> {
        default void print() {
            System.out.println(delegate().getFirst() + " " + delegate().getSecond());
        }
    }

    public static void main(String[] args) {
        // Serializable과 같이 아무런 메서드를 가지고 있지 않는 인터페이스를 마커 인터페이스라고 한다.
        // 리플렉션을 사용하지 않고 타입만 가지고도 직렬화 여부를 확인할 수 있다는 장점이 있다.
        // 여기서 람다식을 캐스팅할 수 있는 이유는 3개 타입을 조합했을 때 최종적인 인터페이스의 추상 메서드 개수가 1개이므로 함수형 인터페이스 조건에 위배되지 않기 때문이다.
        hello((Function & Serializable & Cloneable) s -> s);

        hello2((Function & Hello & Hi) s -> s);

        hello3((Function & Hello & Hi) s -> s, o -> {
            o.hello();
            o.hi();
        });

        hello4((DelegateTo<String> & DelegateHello) () -> "Intersection Type Study", o -> {
           o.hello();
        });

        // 스칼라의 trait과 비슷한 느낌이라고 생각할 수 있다.
        hello4((DelegateTo<String> & DelegateHello & UpperCase) () -> "Intersection Type Study", o -> {
            o.hello();
            o.upperCase();
        });

        Pair<String> name = new Name("Hojo", "Choi");
        hello4((ForwardingPair<String>) () -> name, o -> {
            System.out.println(o.getFirst());
            System.out.println(o.getSecond());
        });

        hello4((ForwardingPair<String> & Convertible<String>) () -> name, o -> {
            print(o);
            o.convert(s -> s.toUpperCase());
            print(o);
            o.convert(s -> s.substring(0, 2));
            print(o);
        });

        hello4((ForwardingPair<String> & Convertible<String> & Printable<String>) () -> name, o -> {
            o.print();
            o.convert(s -> s.toUpperCase());
            o.print();
            o.convert(s -> s.substring(0, 2));
            o.print();
        });
    }

    private static <T extends Function & Serializable & Cloneable> void hello(T t) {

    }

    private static <T extends Function & Hello & Hi> void hello2(T t) {
        t.hello();
        t.hi();
    }

    private static <T extends Function> void hello3(T t, Consumer<T> consumer) {
        consumer.accept(t);
    }

    private static <T extends DelegateTo<S>, S> void hello4(T t, Consumer<T> consumer) {
        consumer.accept(t);
    }

    private static <T> void print(Pair<T> pair) {
        System.out.println(pair.getFirst() + " " + pair.getSecond());
    }

}
