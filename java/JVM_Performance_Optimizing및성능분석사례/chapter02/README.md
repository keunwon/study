# 2. Garbage Collection

## GC 소개
### GC 개요
- 할당된 Memory는 Garbage Collection에 의해 해제, 이때 Garbage는 Heep과 Method Area에서 사용되지 않는 Object를 의미
- 소스상의 close()는 사용 중지 표현이지 Memory에서 삭제하겠다는 의미가 아님
- 개발자가 System.GC()를 사용하면 Full GC가 발생
### GC 문제점
- 명시적인 Memory 해제 보다 느림
- GC 발생 시 Suspend Time으로 다양한 문제 발생
### Root Set과 Garbage
- Object의 참조 여부는 Root Set과의 관계로 판단함
- Root Set에서 Reference 관계가 있다면 Reachable Object라고 하며 이는 현재 사용하고 있는 Object로 간주 함
- Local Variable Section, Operand Stack에 Object의 Reference 정보가 있다면 Reachable Object이다
- Method Area에 로딩된 클래스 중 constant pool에 있는 Reference 정보를 토대로 Thread에서 직접 참조하진 않지만 constant pool을 통해 간접 link를 하고 있는 Object는 Reachable Object이다
- 아직 Memory에 남아 있으며 Native Method Area로 넘겨진 Object의 Reference가 JNI 형태로 참조 관계가 있는 Object는 Reachable Object이다
### Garbage Collection 목적
- 새로운 Object의 할당을 위해 한정된 Heep 공간을 재활용하기 위해서
- Free Space의 크기 보다 큰 Object에게 공간을 활용할 수 없음
## HotSpot JVM의 Garbage Collection
- Object가 모인 곳은 단펴화 발생 활률이 높다
    - Memory 할당은 기존 Object 다음 주소에서 계속 수행 (Garbage는 먼저 할당된 부분에서 많이 생성)
    - 이때 Sweep 작업을 수행하면 단편화 발생하여 Compaction 처럼 비싼 연산 작업 진행
- Garbage를 추적하는 부분은 Tracing 알고리즘 사용
- Marking 작업
    - Root Set에서 Reference 관계를 추적하고 Live Object는 Marking 함
    - Marking 작업은 Young Generation에서 사용
    - Marking 작업은 Memory Suspend 상황에서 수행
- Card Table
    - Old Generation의 Memory를 대표하는 별도의 Memory 구조 (Old Generation의 일부는 Card Table로 구성)
    - Young Generation의 Object를 참조하는 Old Generation의 Object가 있다면 Old Generation Object의 시작주소에 카드를 Dirty로 표시하고(Dirty Card) 해당 내용을 Card Table에 기록, 이후 해당 Reference가 해제되면 표시한 Dirty Card도 삭제처리
    - Minor GC 수행 중 Card Table의 Dirty Card 만 검색하면 빠르게 Reference 관계를 파악 할 수있음
- TLAB (Thread-Local Allocation Buffers)
    - GC가 발생하거나 객체가 각 영역에서 다른 영역으로 이동할 때 병목이 발생 할수 있어 이를 해결하기 위해 TLAB라는 것을 사용함
    - 각 스레드별 버퍼를 사용하면 다른 스레드에 영향을 주지않고 메모리 할당 작업이 가능
    - Eden 영역에 존재
### GC대상 및 범위
- Young Generation의 GC를 Minor GC라고 함
    - 빈번하게 GC 수행
    - 특정 회수 이상 참조된(Age) Object는 Old Area로 Promotion 됨
- Old Generation의 GC를 Full GC(=Major GC)라고 함
    - Full GC는 Suspend Time이 길다 (Minor GC보다)
### GC 관련 옵션들
- 각 영역별 메모리 사용 현황, GC 횟수, GC 소요시간 등을 보면서 적절히 조정해야함  
- Server Class기준 Default 값 동작 방식
    - Default Old영역의 크기는 Young 영역의 두배
    - Survivor 영역의 크기는 Young 영역의 1/10
    - 최초 Young 영역의 크기는 약 2M, 이후 NewRatio 값에 의해 최대값 조정
    - 전체 Heep에서 Free Size가 약 40%이하면 Heep Size가 증가, 70%이상이면 Heep Size가 감소
    - JVM 최대, 최솟값 설정을 안하면 기본 64M를 최대 Memory로 지정
- 옵션의 종류
    - Standard Option: JVM의 공통적인 옵션으로 옵션 앞에 '-'를 붙여 표기
    - Non-Standard Option: JVM마다, 버전마다 약간씩 다름
    - '-X'를 붙이는 것은 보통 Macro 한 측명제어
    - '-XX:'를 붙이는 것은 Micro한 측면제어를 위해 사용
        - Boolean, Numeric, String 표현 할수 있음
        - Boolean) -XX:+<옵션>: On, -XX:-<옵션>: Off
        - Numeric) -XX:<옵션>=<Numberic>
            - Numeric은 용량과 같은 단위
            - Default는 byte
            - 'k', 'K' KiloByte
            - 'm', 'M' MegaByte
            - 'g', 'G' GigaByte
