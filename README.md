# 🔗 참고 링크

## 1. Java

- [`shutdownNow()` 메서드를 호출해도 `InterruptedException`이 발생하지 않는 경우](https://stackoverflow.com/questions/70433737/threadpoolexecutor-shutdownnow-not-throwing-interruptedexception-in-thread): `shutdownNow()` 메서드는 `Thread.interrupt()`를 이용하는데 `readLine()` 메서드와 같은 경우 인터럽트에 응답하지 않아서 원하는 대로 동작하지 않을 수 있다.

- [`AutoCloseable`을 구현하고 있는 `ExecutorService`](https://stackoverflow.com/questions/41393417/why-does-the-executorservice-interface-not-implement-autocloseable): 자바 19부터 `ExecutorService`는 `AutoCloseable`을 구현하고 있기 때문에 `try-with-resources` 구문을 활용할 수 있다. `finally`에 `shutdown()`을 하고 있다고 볼 수 있으므로 별도로 `shutdown()` 메서드를 아래쪽에 또 호출하고 있는 것은 아닌지 주의해야 한다.

## 2. Algorithm

## 3. OS

## 4. Network

## 5. Spring

## 6. Database

## 7. JPA

## 8. Methodology

- [자바스크립트는 `프로토타입` 기반의 객체지향 언어](https://yozm.wishket.com/magazine/detail/1396/): 자바스크립트는 클래스가 없는 함수형 언어를 기반으로 하지만 객체지향 프로그래밍 맛을 느낄 수 있는 언어다. 자바스크립트는 클래스가 존재하지 않고 오직 객체만이 존재했었다. 과거형인 이유는 ES6에 오면서 클래스가 정식 문법이 되었기 때문이다. 물론 ES6의 클래스는 프로토타입 방식을 문법적으로 클래스처럼 보이게 해준 것이다. 다시 본론으로 돌아와서 오직 객체만이 존재했던 시절의 자바스크립트는 상속을 객체 간의 위임 (delegation) 메커니즘으로 구현하였다. 여기에서 깨달을 수 있는 사실은 객체지향의 핵심이 클래스가 아니라는 점이다. 핵심은 적절한 책임을 수행하는 역할 간의 유연하고 견고한 협력 관계를 구축하는 것이다. 클래스는 협력에 참여하는 객체를 만드는 데 필요한 구현 메커니즘일 뿐이다. 클래스도 중요하긴 하지만 협력 구조와 책임을 식별하는 것에 비해 상대적으로 덜 중요하다. 객체지향의 중심에는 클래스가 아니라 객체가 위치하며, 중요한 것은 클래스들의 정적인 관계가 아니라 메시지를 주고받는 객체들의 동적인 관계다.

## 9. Infrastructure
