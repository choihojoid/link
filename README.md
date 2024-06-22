# 🔗 참고 링크

## 1. Java

- [`shutdownNow()` 메서드를 호출해도 `InterruptedException`이 발생하지 않는 경우](https://stackoverflow.com/questions/70433737/threadpoolexecutor-shutdownnow-not-throwing-interruptedexception-in-thread): `shutdownNow` 메서드는 `Thread.interrupt()`를 이용하는데 `readLine()` 메서드와 같은 경우 인터럽트에 응답하지 않아서 원하는 대로 동작하지 않을 수 있다.

- [`AutoCloseable`을 구현하고 있는 `ExecutorService`](https://stackoverflow.com/questions/41393417/why-does-the-executorservice-interface-not-implement-autocloseable): 자바 19부터 `ExecutorService`는 `AutoCloseable`을 구현하고 있기 때문에 `try-with-resources` 구문을 활용할 수 있다. `finally`에 `shutdown()`을 하고 있다고 볼 수 있으므로 별도로 `shutdown()` 메서드를 아래쪽에 또 호출하고 있는 것은 아닌지 주의해야 한다.

## 2. Algorithm

## 3. OS

## 4. Network

## 5. Spring

## 6. Database

## 7. JPA

## 8. Methodology

## 9. Infrastructure
