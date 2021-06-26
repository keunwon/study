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

## Garbage Collector 종류
### Serial Collector
- Young/Old Generation 모두 Serial로 Single CPU를 사용 (즉 1개의 Thread를 가지고 GC를 수행)
- 현재 거의 사용하지 않는 Collector이다
### Parallel Collector
- throughput collector로 알려진 방식
- 이 방식의 목표는 다른 CPU가 대기 상태로 남아있는 것을 최소화하는 것
- Young 영역에서의 GC를 병렬(Parallel)로 처리 (많은 CPU를 사용하는 대신 GC의 부하를 줄이고 애플리케이션 처리량을 증가)
- Old Area은 Mark-Sweep-Compact Collection 알고리즘 사용, Single Thread 방식
- Young Generatino Collection 알고리즘: Parallel Copy
    - Eden, Survivor 영역의 Live Object Copy 작업을 여러 Thread가 동시 수행 (Suspend 발생)
    - 사용하는 Resource에 따라 Suspend Time을 단축 가능
    - PLAB(Parallel Allocation Buffer)
        - Memory 특성 상 같은 공간을 여러 Thread가 접근 하면 Corruption 발생,  
        이 같은 문제를 해결하기 위해서 PLAB (Parallel Allocation Buffer) 사용
        - Promotion Buffer 이다
            - Promotino: Young 영역의 참조 객체들을 Old 영역으로 승격
        - GC Thread가 Promotion시 Thread마다 Old Generation의 일정부분을 할당하고 다 사용하면 다시 Buffer를 재할당함
### TLAB, PLAB 비교
- TLAB은 Young Generation의 Fast Allocation을 위한 것
- PLAB은 Promotion 과정 중 동기화 문제를(Old Area) 회피하기 위한 것
- TLAB는 4K, PLAB 1K 단위로 할당
### CMS Collector
- low-latency collector로 알려져 있음
- 힙 메모리 영역의 크기가 클 때 적합
- 자원이 여유 있는 상태에서 GC의 Pause Time을 줄이기 위한 목적
- Size가 큰 Long Lived Object가 있는 경우 적합
- CMS Collector는 Suspend Time을 분산하여 응답시간 개선
- Young Area에는 Parallel Copy 알고리즘, Old Area는 Concurrent Mark-Sweep 알고리즘 사용
- [Floating Garbage](#floating-garbage) 문제가 있음
### Old Area의 Concurrent Mark-Sweep 알고르즘
- Initial Mark Phase 단계
    - Single Thread 사용
    - 애플리케이션에서 직접 Reference되는 Live Object만 구별 (Suspend 상태지만 빠름)
- Concurrent Mark Phase 단계
    - Single Thread 사용
    - GC Thread 외 Working Thread는 애플리케이션 수행이 가능
    - Initial Mark Pause에서 선별된 Live Object가 Reference하고 있는 Object를 추적해 Live 여부를 구별
- Remark Phase 단계
    - Multi Thread 사용되며 애플리케이션이 중지
    - 이미 Marking 된 Object를 다시 추적, Live여부 확정, 모든 Resource 투입
- Concurrent Sweep Phase 단계
    - Single Thread 사용
    - 최종 Live Object를 제외하고 모든 Object를 지움
    - Sweep 작업만 하고 Compaction 작업은 수행하지 않음
    - Compaction 작업은 Heep의 Suspend를 전제로 반복된 Sweep은 단편화를 유발 ([Free List](#free-list)를 사용하여 단편화를 줄이는 노력을 함)
### Parallel Compaction 알고리즘: Old Area
- Old Area에 새로운 알고리즘 사용 (Mark and Compact -> Parallel Compacting),  
Old Area의 Collection 시간을 감소시켜 효율이 증가
- Multi CPU에서 유리
### Parallel Compaction 알고리즘
- Mark Phause 단계: 살아있는 객체를 식별하는 단계
- Summary phase 단계: 이전에 GC를 수행하여 컴팩션된 영역에 살아있는 객체으 위치를 조사
- Compact Phase 단계: 컴팩션을 수행하는 단계로 수행 이후에는 컴팩션된 영역과 비어있는 영역으로 나누어짐
### Garbage First Collector
- 
#### 기본적인 GC 메커니즘
- Young Area의 Region들의 Object를 Survivor Region으로 Copy
- Promotion의 대상 Object는 Old Generation으로 Copy
- 기존 Young Generation Region은 Garbage로 간주해 Region 단위로 할당을 해지
- Young Area GC가 끝나면 바로 Old Area GC를 시작함
    - Heep 전체에 대한 GC는 아니며 Region단위임
    - GC로 인한 Suspend 현상도 해당 Region을 사용하는 Thread에 국한
> G1 Collector가 Region 내에 Reference를 관리하는 방법은 Remember set을 이용함(Heep의 5%미만 공간을 각 Region의 참조정보를 저장하는 Remember set으로 할당), Remember set은 Region 외부에서 들어오는 참조 정보를 가지고 있어 Marking 장업 시 trace 일량을 줄여줘 GC효율을 높임
#### GC 작업 단계
G1 Garbage Collection은 4단계, 세부적으로 6단계이다
1. Young GC
    - Minor GC와 동일한 개념
    - Suspend Time이 존재, Multi-Thread 작업
    - Live Object는 Age에 맞게 Survivor Region, Old Region으로 Copy되며 기존 공간은 해지
    - 이후 새로운 Object가 할당되는 Young Region은 Survivor Region과 그 근처 비어있는 Region이 됨
2. Concurrent Mark phase (Mark -> Remark): Old Area GC 시작
    - Marking 단계
        - Single-Thread, 전체적으로 Concurrent
        - 이전에 변경된 정보를 바탕으로 Initial Mark를 빠르게 수행
    - Remarking 단계
        - Suspend 발생, 전체 Thread가 동시 작업
        - 각 Region마다 Reachable Object의 Density를 계산, Garbage Region은 다음 단계로 안넘아가고 바로 해지
3. Old Region reclaim phase (Remark -> Evacuation)
    - Remark 단계
        - Concurrent 작업, Multi-Thread 방식
        - GC를 위해 Live Object의 비율이 낮은 몇 개의 Region을 골라냄
    - Evacuation Phase 단계
4. Compaction Phase
    - Concurrent 작업을 수행
    - 많은 Region을 균등하게 조금씩 사영하게 되는 부작용을 방지
### Free List
- Young Area에서 승격된 Object와 크기가 비슷한 Old Area의 Free Space를 Free List에서 탐색
- 승격되는 Object Size를 계속 통계화하여 Free Memory 블록들을 붙이거나 쪼개서 적적할 Size로 Chunk에 Object를 할당  
(이러한 작업은 Young Area에 부담을 줌, Free List에서 적절한 Chunk 크기를 찾아 Allocation 해야하기 때문에 시간도 오래 걸림)
### Floating Garbage
- Garbage면서 수거되지 않고 붕 뚠 Garbage
