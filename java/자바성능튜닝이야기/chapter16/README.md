# JVM은 도대체 어떻게 구동될까?

## HotSpot VM 
HotSpot VM은 높은 성능과 확장성을 제공한다.  
JIT 컴파일러는 자바 애플레이션이 수행되는 상황을 보고 동적으로 최적화를 수행한다.
### 주요 컴포넌트
- VM(Virtual Machine) 런타임
- JIT(Just In Time) 컴파일러
- 메모리 관리자
> JIT 컴파일러는 자바 메서드가 호출되면 바이트코드를 컴파일하고 실행 가능한 네이트브 코드로 변환한다.  
매번 JIT 컴파일 시 성능저하가 심하므로 최적화 단계를 거치게 된다.

## JIT Optimizer
- JIT 컴파일러는 Client/Server 버전으로 나누어져 있음
- 자바는 소스코드를 바이트코드로 된 class라는 파일로 변환한다

## JRockit의 JIT 컴파일 및 최적화 절차

## JVM 시작 절차
1. java 명령어 줄에 옵션 파싱
2. 자바 힙 크기 할당, JIT 컴파일러 타입 지정
3. CLASSPATH와 LD_LIBRARY_PATH 같은 환경 변수를 지정
4. 자바 Main 클래스 실행, 지정되지 않았으면 manifest 파일에서 Main 클래스 확인
5. HotSpot VM 생성
6. HotSpot VM 생성 초기화, Main 클래스가 로딩된 런처에서는 main() 메서드의 속정 정보를 읽음
7. HotSpot VM에 있는 main() 메서드 수행 (이때 자바 실행 시 Main 클래스 뒤에 있는 값들이 전달)

## JVM 종료 절차
1. HotSpot VM이 작동중인 상황에서는 단 하나의 데몬이 아닌 스레드가 수행될 때까지 대기
2. java.lang 패키지에 있는 Shutdown 클래스의 shutdown() 메서드가 수행
3. HotSpot VM의 종료를 준비, HotSpot VM의 profiler, start smaple, watcher, garbage collector 스레드 종료 이후 JVMTI 비활성처리 Singal 스레드 종료
4. HotSpot의 JavaThread::exit() 메서드 호출하여 JNI 해제한다. 스레드들을 삭제한다. 이후에는 자바 HotSpot VM에서 자바 코드를 실행이 불가하다
5. HotSpot VM 스레드 종료
6. JNI, HotSpot VM, JVMTI barrier에 있는 추적 기능을 종료
7. 네이티브 스레드에서 수행하고 있는 스레드들을 HotSpot의 "vm exited"값을 설정
8. 현재 스레드를 삭제
9. 입출력 스트립 삭제, PerfMemory 리소스 연결 해제
10. JVM 종료를 호출한 호출자로 복귀

## 클래스 로딩 절차
1. 클래스 이름으로 클래스 패시에 있는 바이너리로 된 자바 클래스를 찾는다
2. 자바 클래스를 정의
3. 해당 클래스를 생성 (java.lang 패키지의 Class 클래스의 객체를 생성)
4. 링크 작업 수행 (static 필드를 생성 및 초기화, 메서드 테이블을 할당)
5. 클래스의 초기화 진행되며, 클래스의 static 블록과 static 필드가 가장 먼저 초기화 (부모 클래스의 초기화가 먼저 이루어짐)
