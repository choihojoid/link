# 🔗 참고 링크

## 1. Java

- [`shutdownNow()` 메서드를 호출해도 `InterruptedException`이 발생하지 않는 경우](https://stackoverflow.com/questions/70433737/threadpoolexecutor-shutdownnow-not-throwing-interruptedexception-in-thread): `shutdownNow()` 메서드는 `Thread.interrupt()`를 이용하는데 `readLine()` 메서드와 같은 경우 인터럽트에 응답하지 않아서 원하는 대로 동작하지 않을 수 있다.

- [`AutoCloseable`을 구현하고 있는 `ExecutorService`](https://stackoverflow.com/questions/41393417/why-does-the-executorservice-interface-not-implement-autocloseable): 자바 19부터 `ExecutorService`는 `AutoCloseable`을 구현하고 있기 때문에 `try-with-resources` 구문을 활용할 수 있다. `finally`에 `shutdown()`을 하고 있다고 볼 수 있으므로 별도로 `shutdown()` 메서드를 아래쪽에 또 호출하고 있는 것은 아닌지 주의해야 한다.

- [자바의 데이터 전달 방식은 `Pass by Value`](https://mangkyu.tistory.com/107): 자바는 primitive type이든 reference type이든 `Pass by Value`로 데이터를 전달한다. primitive type은 쉽게 이해할 수 있지만 reference type도 `Pass by Value`로 동작하는 것이 맞는지 헷갈릴 수 있다. 자바에서 객체를 전달할 때 해당 객체가 저장된 주소가 복사되어 전달된다. 따라서 해당 주소를 바탕으로 필드에 접근하는 것은 가능하지만 그 객체 자체를 변경하는 것은 원본의 데이터에 영향을 주지 않는다. `Pass by Reference`는 주소를 전달하여 원본에 대한 alias를 구성함으로써 원본 그 자체에도 영향을 주는 것을 의미한다.

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
