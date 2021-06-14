# 1. JVM 메모리 구조

## JVM이란?
### JVM 구조
- Java Source: Java 코드
- Java Compiler: Java Source를 Byte Code로 변경함
- Java Byte Code: Java Compile 결과물 (.class 파일)
- Class Loader: JVM내 .class파일들을 Load하여 Loading된 클래스들을 Runtime Data Area에 배치
- Excution Engine: Loading된 클래스의 Byte Code를 해석
- Runtime Data Area: OS에게 할당 받은 메모리 공간
### Runtime Data Area
- Method Area: 클래스, 변수, Method, static 변수, 상수 정보 등이 저장되는 영역 (모든 Thread 공유)
- Heep Area: new 명령어로 생성된 인스턴스와 객체가 저장되는 구역 (GC 발생, 모든 Thread 공유)
- Stack Area: Method 내에서 사용되는 값들이 저장, 메소드 내 메소드 실행 시 LIFO 하나씩 생성, 메소드 종료 시 LIFO 하나씩 삭제 (Thread 별로 생성)
- PC Register: CPU의 Register와 역할이 비슷함, 현재 수행중인 JVM 명령의 주소값 저장 (Thread 별로 생성)
- Native Method Area: 다른 언어 (C/C++ 등)의 메소드를 호출을 위해 할당되는 구역

## Java Heep
- Java의 자동 Memory 해제, 즉 Garbage Collection과도 연관이 있음
- Thread의 공유 정보는 Statck에 저장, Class, Method 정보, ByteCode 등은 Method Area에 저장
- Java Heep은 단지 인스턴스와 Array 객체만 저장 (모든 Thread들에 공유되는 영역)
### Hotspot JVM의 Heep구조
- Young Generation
    - Eden X 1, Suvivor X 2 영역으로 구성
    - Eden 영역은 Object가 Heep에 최초로 할당되는 장소
    - Eden 영역이 차게 되면 Object의 참조 여부를 따져 참조가 되어있는 경우 Suvivor 영역으로 이동, 참조가 끊어진 Garbage Object는 그냥 남겨 놓음
    - 참조된 Object들이 Survivor로 넘어가면 Eden 영역을 청소함
    - Surivior 영역은 참조된 Object들이 잠시 머무르는 곳
    - 참조된 Object들이 Survivor로 이동할때 하나의 Survivor 영역으로만 이동함
    - 위에 일련의 과정들을 Minor GC 라고 함
- Old Generation
    - Young Generation에서 오래 살아남아 Object들은 Old Generation으로 이동 (이때 특정 횟수 이상 참조가 기준 = Age)
    - 비교적 오랫동안 참조되어 앞으로도 계속 사용될 확률이 높은 Object들을 저장하는 영역
    - Old Generation의 메모리가 충분하지 않으면 Full GC(Major GC) 발생
- MetatSpace
    - Class, Method의 Meta정보들을 저장
    - Java8 이후부터는 Native Memory 영역으로 이동 (Native Memory는 OS레벨에서 관리하는 영역)
    - Java8 이전에는 Permanent 영역에 저장
    - MetaSpace는 필요에 따라 자동적으로 증가하기 때문에 일반적으로 크게 주의를 가질 필요는 없음