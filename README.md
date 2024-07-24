# 🔗 참고 링크

## 1. Java

- [`shutdownNow()` 메서드를 호출해도 `InterruptedException`이 발생하지 않는 경우](https://stackoverflow.com/questions/70433737/threadpoolexecutor-shutdownnow-not-throwing-interruptedexception-in-thread): `shutdownNow()` 메서드는 `Thread.interrupt()`를 이용하는데 `readLine()` 메서드와 같은 경우 인터럽트에 응답하지 않아서 원하는 대로 동작하지 않을 수 있다.

- [`AutoCloseable`을 구현하고 있는 `ExecutorService`](https://stackoverflow.com/questions/41393417/why-does-the-executorservice-interface-not-implement-autocloseable): 자바 19부터 `ExecutorService`는 `AutoCloseable`을 구현하고 있기 때문에 `try-with-resources` 구문을 활용할 수 있다. `finally`에 `shutdown()`을 하고 있다고 볼 수 있으므로 별도로 `shutdown()` 메서드를 아래쪽에 또 호출하고 있는 것은 아닌지 주의해야 한다.

- [자바의 데이터 전달 방식은 `Pass by Value`](https://mangkyu.tistory.com/107): 자바는 primitive type이든 reference type이든 `Pass by Value`로 데이터를 전달한다. primitive type은 쉽게 이해할 수 있지만 reference type도 `Pass by Value`로 동작하는 것이 맞는지 헷갈릴 수 있다. 자바에서 객체를 전달할 때 해당 객체가 저장된 주소가 복사되어 전달된다. 따라서 해당 주소를 바탕으로 필드에 접근하는 것은 가능하지만 그 객체 자체를 변경하는 것은 원본의 데이터에 영향을 주지 않는다. `Pass by Reference`는 주소를 전달하여 원본에 대한 alias를 구성함으로써 원본 그 자체에도 영향을 주는 것을 의미한다.

- [`@FunctionalInterface` 어노테이션](https://docs.oracle.com/javase/8/docs/api/java/lang/FunctionalInterface.html): 자바 API를 살펴보다 보면 functional interface에 `@FunctionalInterface` 어노테이션이 붙어있는 것을 볼 수 있다. 해당 어노테이션은 말 그대로 functional interface임을 가리키는 어노테이션이다. @FunctionalInterface로 인터페이스를 선언했지만 abstract method가 2개 이상이라면 컴파일 에러가 발생한다. 여기서 abstract method의 개수에는 부모 인터페이스의 abstract method도 포함된다. static, default method는 몇 개가 있든 상관없다. 한 가지 흥미로운 점은 Comparator를 살펴보면 abstract method가 `compare(T o1, T o2)`, `equals(Object obj)`로 2개임에도 불구하고 @FunctionalInterface 어노테이션이 붙어있다는 것이다. 오라클 공식문서를 살펴보면 Object의 public method는 abstract method 개수에 포함되지 않는다고 한다. 이유는 어떤 인터페이스의 구현체든 Object 클래스를 상속하고 있을 것이고, 따라서 Object 클래스의 `equals(Object obj)`로부터 구현부를 상속받기 때문이다. 결국 functional interface에 Object의 public method를 둔다 하더라도 해당 인터페이스의 구현체는 Object를 상속하게 될 것이므로, 구현체 상태에서는 확정으로 abstract method가 아니기 때문에 abstract method 개수에 포함되지 않는 것이다. 처음에는 인터페이스도 Object 클래스를 상속하므로 인터페이스 자체만으로도 구현부를 가지고 있기 때문이라 착각했는데 오개념이었다. 인터페이스는 Object 클래스를 상속하지 않는다. 하지만 인터페이스의 구현체를 만들면 반드시 Object 클래스를 상속하게 된다. 그렇다면 여기서 하나 드는 의문이 있다. 굳이 Comparator에 `equals(Obejct obj)`를 명시적으로 적을 필요가 있을까? Javadoc이나 [스택오버플로우](https://stackoverflow.com/questions/11013850/why-does-comparator-declare-equals) 설명을 봐도 잘 모르겠다. 심심할 때 생각해보자.

- [람다 형식 추론](https://seoarc.tistory.com/86): 모던 자바 인 액션 108쪽 내용이다. 람다로 인터페이스의 인스턴스를 만들 수 있었다. 그런데 람다 표현식 자체에는 람다가 어떤 functional interface를 구현하는지의 정보가 포함되어 있지 않다. 대신 람다가 사용되는 context를 이용해서 람다의 형식을 추론한다. 어떤 context에서 기대되는 람다 표현식의 형태를 `대상 형식 (target type)`이라고 부른다. 여기서 context란 람다가 전달될 메서드 파라미터나 람다가 할당되는 변수 등을 의미한다. 대상 형식이라는 특징 덕분에 같은 람다 표현식이더라도 호환되는 추상 메서드를 가진 다른 functional interface의 인스턴스로 사용할 수 있다. 예를 들어 `() -> 42` 람다식은 `Callable<Integer>`, `PrivilegedAction<Integer>` 둘 다에 대해 인스턴스를 만들 때 사용할 수 있다. 추가로 람다의 바디에 일반 표현식이 있으면 void를 반환하는 function descriptor와 호환된다. 예를 들어 list.add(obj)는 boolean을 반환하지만 `Consumer<T>: t -> void`에서도 사용할 수 있다. 마지막으로 메서드가 오버로딩 되어있는데 `execute(Runnable runnable)`, `execute(Action<T> action)`처럼 function descriptor가 같을 수 있다. `execute(() -> {})`라는 람다식을 사용할 경우 둘 중 누구를 가리키는지 명확하지 않다. 이런 경우 어떤 메서드의 signature가 사용되어야 하는지를 명시적으로 구분하기 위해 람다를 캐스팅해야 한다. 예시에서는 `execute((Action) () -> {})`와 같이 사용하면 된다.

- [람다 캡처링 (capturing lambda)](https://vagabond95.me/posts/lambda-with-final/): 모던 자바 인 액션 112쪽 내용이다. `람다 캡처링`은 익명 함수처럼 람다 표현식에서 `자유 변수 (free variable)`을 활용하는 동작을 말한다. 여기서 자유 변수는 파라미터로 넘겨진 변수가 아닌 외부에서 정의된 변수를 의미한다. 그런데 활용할 수 있는 자유 변수에는 제약이 있다. 우선 자유 변수 중 인스턴스 변수와 정적 변수는 자유롭게 캡처, 즉 람다 표현식의 바디에서 참조할 수 있다. 그러나 자유 변수 중 지역 변수는 `final` 혹은 `effectively final`한 경우에만 캡처할 수 있다. 여기서 지역 변수가 람다 표현식 내에서 `effectively final`은 syntactic sugar의 일종으로 `final` 키워드가 붙지 않은 변수지만 초기화 이후 값이 한 번도 변경되지 않은 것을 의미한다. 그렇다면 지역 변수에는 왜 `final` 혹은 `effectively final`이라는 제약이 있을까? 그 이유는 다음과 같다. 지역 변수는 스택 영역에 생성된다. 따라서 지역 변수가 선언된 block이 끝나면 스택에서 제거된다. 메서드 내 지역 변수를 참조하는 람다 표현식을 반환하는 메서드가 있을 경우, 메서드 block이 끝나면 지역 변수가 스택에서 제거되므로 추후 람다 표현식이 수행될 때 참조할 수 없다. 지역 변수를 관리하는 스레드와 람다 표현식이 실행되는 스레드가 다른 경우도 마찬가지다. 그래서 람다 표현식에서 사용하는 지역 변수는 `Pass by Value`로 복사본을 전달받아 사용된다. 여기까지 숙지했으면 이유를 이해할 수 있다. 지역 변수를 제어하는 스레드 A와 람다 표현식이 수행되는 스레드 B가 다를 때, 스레드 B에서 참조하는 지역 변수가 가장 최신 값으로 복사되어 전달된 건지 확신할 수가 없다. 지역 변수는 스레드 A의 스택 영역에 존재하기 때문에 다른 스레드에서 접근이 불가능하다. 이 말은 지역 변수를 스레드 간에 동기화시키는 것은 불가능하다는 것이다. 결국 확신할 수 없는 코드는 의미가 없으므로 자유 변수에 제약을 붙여 최신 값임을 보장한 것이다. 한편, 인스턴스 변수는 힙 영역에 저장되고 정적 변수는 메서드 영역에 저장되는데 두 영역 모두 스레드 간 공유하는 영역이기 때문에 제약이 없다. 물론 멀티 스레드 환경일 경우 필요하다면 동기화하는 작업은 있어야할 것이다. 참고로 자바에서 모든 데이터 전달 방식은 `Pass by Value`임을 명심하자. 위 설명을 보고 지역 변수에 한해서만 `Pass by Value`라고 이해하면 안 된다.

- [공변성 (covariance), 반공변성 (contravariance), 불공변성 (invariance)](https://learn.microsoft.com/ko-kr/dotnet/standard/generics/covariance-and-contravariance): `변성 (variance)`은 타입의 상속 계층 관계에서 서로 다른 타입 간에 어떤 관계가 있는지 나타내는 지표다. 우선 변성 개념이 왜 필요할까? 많은 프로그래밍 언어들의 타입 시스템은 서브타이핑 (subtyping) 개념을 지원한다. 리스코프 치환 원칙을 생각해보면 된다. 여기서 변성은 복잡한 타입들 간 서브타이핑이 등장했을 때 어떻게 동작할지에 관한 것이다. 예를 들어 `List<Child>`와 `List<Parent>` 사이의 관계, `Child`를 반환하는 함수와 `Parent`를 반환하는 함수 사이의 관계 등에서 말이다. 프로그래밍 언어를 설계할 때 배열, 상속, 제네릭 등과 같은 언어 기능에 대한 typing rule을 정할 때 변성을 고려한다. 이제 변성과 관련한 3가지 성질을 알아보자. 우선 공변성은 타입의 순서가 보존되는 경우다. 본래 지정된 타입보다 더 많이 파생된 타입을 사용할 수 있다. `Child`와 `Parent`의 관계처럼 `T<Child>`가 `T<Parent>`의 서브타입으로 간주되는 것이다. 다음으로 반공변성은 타입의 순서가 반대로 보존되는 경우다. 본래 지정된 타입보다 더 제네릭한 (덜 파생적인) 타입을 사용할 수 있다. `Child`와 `Parent`의 관계와는 반대로 `T<Parent>`가 `T<Child>`의 서브타입으로 간주되는 것이다. 마지막으로 반공변성은 타입 변환이 불가능한 경우다. 공변성을 가지지도, 반공변성을 가지지도 않은 것이다. 이제 자바 관점으로 들어가보자. 자바에서 배열은 공변성을 가지고 있지만 제네릭은 불공변성을 가지고 있다. 흔히 배열과 제네릭을 같이 비교하는 것이 둘이 대조되는 성질을 가지고 있기 때문이다. 추가적으로 반공변성을 처음에 공부할 때 이 성질이 가능한 건지, 실제로 쓰이긴 하는 건지 의문이 들었는데 와일드카드를 공부하고 깨닫게 되었다. 와일드카드는 설명이 길어 따로 설명하겠다.

- [배열은 공변이고 제네릭은 불공변인 이유](https://stackoverflow.com/questions/18666710/why-are-arrays-covariant-but-generics-are-invariant): 근본적으로 자바에서 배열은 왜 공변성을 가지고 제네릭은 불공변성을 가질까? 공변성을 가지면 다형성을 활용할 수 있다는 장점이 있지만 타입 안정성을 해칠 수 있다는 단점이 있다. 초기에는 제네릭에 대한 개념이 없었고 타입 안정성에 대한 단점보다는 다형성을 통해 얻는 장점이 더 크다고 판단했던 것 같다. 추후 제네릭이 등장할 때는 타입 안정성을 더 신경써서 불공변하게 설계한 것으로 보인다. 이전 버전과의 호환성을 위해 제네릭은 배열과 달리 런타임 시에 type erasure이 발생하는 것도 이유에 포함되는 것 같다. 공변하는 배열과 공변하는 제네릭이 있다고 가정했을 때 타입 안정성에 대한 부작용은 배열이 type erasure이 발생하는 제네릭이 더 크기 때문이다. 여기에 더해 와일드카드를 활용하면 제네릭의 불공변성으로 인한 단점을 보완할 수 있으므로 불공변하게 설계한 것은 적절했다고 생각한다. 지금까지의 설명을 보면 그럼 요즘 자바가 개발되었다면 배열도 불공변하게 설계되었을까 하는 의문이 들 수 있다. 이에 대해 나는 그렇다고 생각한다. 실제로 나중에 나온 코틀린은 배열도 불공변이라고 알고 있다. 제네릭과 와일드카드를 활용하면 타입 안정성을 보장하면서도 다형성을 통해 유연하게 설계할 수 있다.

- [제네릭과 와일드카드](https://stackoverflow.com/questions/176446/how-can-elements-be-added-to-a-wildcard-generic-collection): 자바에서 와일드카드를 활용하면 제네릭에 다형성을 적용할 수 있다. 보통 이를 `사용처 변성 (use-site variance)`라고 한다. 사용처 변성과 대응되는 개념으로는 `선언처 변성 (declaration-site variance)`이 있는데, 스칼라가 대표적인 선언처 변성 언어라고 한다. 본론으로 돌아와서 와일드카드에는 상한 경계를 의미하는 `<? extends U>`, 하한 경계를 의미하는 `<? super U>`, 비경계를 의미하는 `<?>` (`<? extends Object>`의 줄임 표현)가 있다. 어떤 경우에 extends를 쓰고 super를 쓰는지는 추후 이펙티브 자바 공부를 하면서 보완하겠다.

- [자바가 메모리를 할당하는 방법](https://tangoblog.tistory.com/m/14)

- [자바 제네릭 타입 소거](https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EC%A0%9C%EB%84%A4%EB%A6%AD-%ED%83%80%EC%9E%85-%EC%86%8C%EA%B1%B0-%EC%BB%B4%ED%8C%8C%EC%9D%BC-%EA%B3%BC%EC%A0%95-%EC%95%8C%EC%95%84%EB%B3%B4%EA%B8%B0)

## 2. Algorithm

## 3. OS

## 4. Network

## 5. Spring

## 6. Database

## 7. JPA

## 8. Methodology

- [자바스크립트는 `프로토타입` 기반의 객체지향 언어](https://yozm.wishket.com/magazine/detail/1396/): 자바스크립트는 클래스가 없는 함수형 언어를 기반으로 하지만 객체지향 프로그래밍 맛을 느낄 수 있는 언어다. 자바스크립트는 클래스가 존재하지 않고 오직 객체만이 존재했었다. 과거형인 이유는 ES6에 오면서 클래스가 정식 문법이 되었기 때문이다. 물론 ES6의 클래스는 프로토타입 방식을 문법적으로 클래스처럼 보이게 해준 것이다. 다시 본론으로 돌아와서 오직 객체만이 존재했던 시절의 자바스크립트는 상속을 객체 간의 위임 (delegation) 메커니즘으로 구현하였다. 여기에서 깨달을 수 있는 사실은 객체지향의 핵심이 클래스가 아니라는 점이다. 핵심은 적절한 책임을 수행하는 역할 간의 유연하고 견고한 협력 관계를 구축하는 것이다. 클래스는 협력에 참여하는 객체를 만드는 데 필요한 구현 메커니즘일 뿐이다. 클래스도 중요하긴 하지만 협력 구조와 책임을 식별하는 것에 비해 상대적으로 덜 중요하다. 객체지향의 중심에는 클래스가 아니라 객체가 위치하며, 중요한 것은 클래스들의 정적인 관계가 아니라 메시지를 주고받는 객체들의 동적인 관계다.

## 9. Infrastructure

- [`표준 스트림`과 `표준 입출력`](https://shoark7.github.io/programming/knowledge/what-is-standard-stream): `표준 스트림`은 프로그램과 그 환경 사이에 미리 연결된 입출력 채널을 의미한다. 유닉스는 하드웨어별로 입출력을 위한 설정 작업을 따로 하는 문제를 해결하기 위해 입출력 작업을 파일을 읽고 쓰는 한 가지 작업으로 통일시켰다. 그리고 그 파일에서 읽히고 나가는 데이터를 스트림이라고 정의했다. 일반적으로 프로그램은 입출력을 필요로 하는데 입력과 출력이 특정 대상로부터만 발생한다면 사용자가 지정하지 않는 이상 기본적으로 사용할 입출력 대상을 설정해놓을 수 있으면 좋을 것이다. 그래서 한 프로그램이 기본적으로 사용할 입출력 대상을 `표준 입출력`이라고 한다. 살짝 애매하지만 표준 스트림과 표준 입출력은 똑같은 의미라고 봐도 될 것 같다.

- [쉘의 `I/O Redirection`](https://shoark7.github.io/programming/shell-programming/IO-Redirection-in-Shell): 쉘을 다룰 때 대부분의 명령어는 결과를 콘솔에 출력한다. 따로 지정하지 않았거나 못했기 때문에 표준 출력인 콘솔에 내놓은 것이다. 그런데 쉘을 종료하면 사라지는 콘솔에 출력시키는 것이 아니라 영구적으로 파일에 저장하고 싶을 수 있다. 그럴 때 사용하는 것이 `I/O Redirection`이다. 서버 로그를 남길 때 콘솔에 출력하는 것이 아니라 별도의 파일에 저장하는 것을 생각해보면 된다.
