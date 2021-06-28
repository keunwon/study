# 3. JVM Synchronization이란?

## 개요
### Java 그리고 Thread
- WAS에서는 수많은 사용자를 처리하기 위해서 수식 ~ 수백 개의 Thread를 사용
    - 두개 이상의 Thread를 사용하면 필연적으로 경합(Contention)이 발생
    - Dead Lock 발생할 수 있음
    - Thread가 공유 자원에 접근하는 일은 빈번함
### Thread 동기화
- 여러 Thread가 공유 자원을 사용할 때 정합성을 보장하려면 동기화 장치로 한번에 하나의 Thread만 공유 자원에 접근 할 수있어야함
- Java에서는 Monitor를 이용해 Thread를 동기화함
- Java 객체에는 하나의 Monitor를 가지고있음
    - Monitor는 하나의 Thread 만 소유 가능함
    - 특정 Thread가 소유한 Monitor를 다른 Thread가 획득하려면 해당 Monitor를 소유한 Thread가 Monitor를 해제할 때까지 Wait Queue에서 대기함
### Monitor
- 동기화 문제를 해결하기 위해 사용
- Java에서의 모든 Object는 반드시 하나의 Monitor를 가지고 있음
- JAva에서 Monitor를 점유하는 유일한 방법은 Synchronized 키워드를 사용
### Java의 동기화(Synchronized) 방법
1. Synchronized Statement
```java
private int[] intArr = new int[10];

void synchBlock() {
    synchronized (this) {
        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = i;
        }
    }
}
```
- 해당 Object의 Monitor를 점유하려는 Thread들은 대기상태(BLOCKED)
- ByteCode를 보면 MONITORENTER, MONITOREXIT를 볼 수있음
- MONITORENTER가 실행되면 Stack의 Object Reference를 이용하여 Object에 대한 Lock을 획득
- Lock을 이미 얻은 상태라면 Lock Count 1 증가, Lock이 처음이면 Lock Count는 1이며 Lock을 소유
- MONITOREXIT가 실행되면 Lock Count를 1 감소 만약 값이 0에 도달하면 Lock 해제
- MONITOREXIT는 Exception을 던지기 직전에 Critical Section을 빠져나옴
- Synchronized Statement의 사용은 내부적으로 try ~ catch 절을 사용하는 효과가 있음
2. Synchronized Method
``` java
class SyncMtd {
    private int[] intArr = new int[10];

    synchronized void synchMethod() {
        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = i;
        }
    }
}
```
- Byte Code의 Lock관련 내용이 없음
- Synchronsized Method에서 Lock 사용여부는 Symbolic Reference를 Resolution하는 과정에서 결정
- Method의 내용이 Critical Section이 아니고 Method 호출 자체가 Critical Section이다
- Synchronized Statement는 런타임 Monitor Lock을 획득, Synchronsized Method는 Method 호출하기 위해 Lock 획득
- Synchronsize Method가 Instance Method라면 Method를 호출하는 this Object에 대해 Lock을 획득해야함
- Static Method라면 해당 Class의 Class Instance에 대해 Lock을 획득해야함
- Synchronsize Method가 정상 실행 여부 상관 없이 종료되면 JVM에서 Lock을 자동적으로 Release함
## Thread 상태
- NEW: Thread가 생성, 실행되지 않은 상태
- RUNNABLE: 현재 CPU를 점유하고 작업을 수행 중인 상태 (운영체제의 자원 분배로 인해 WAITING 상태가 될수 있음)
- BLOCKED: Monitor를 획득하기 위해 다른 Thread가 Lock을 해제하기를 기다리는 상태
- WAITING: wait(), join(), park() 메소드들을 이용하여 대기하고 있는 상태
- TIMED_WAITING: sleep(), wait(), join() ,park() 메소드들을 이용해 대기하고 있는 상태. WAITING 상태와의 차이점은 Mehtod의 인수로 최대 대기 시간을 명시 할수 있어 외부적인 변화뿐만 아니라 시간에 의해서도 WAITING 상태가 해제될 수 있음

## Thread Dump
