# 7. 물리 메모리 관리

## 메모리 관리의 개요
### 메모리 관리의 복잡성
- 메모리 구조는 1B 크기로 나뉨
- 1B로 나뉜 각 영역은 메모리 주소로 구분하는데 보통 0번지부터 시작함
- CPU는 메모리에 있는 내용을 가져오거나 작업 결과를 메모리에 저장하기 위해 메모리 주소 레지스터를 사용
    - 메모리 주소 레지스터에 필요한 메모리 주소를 넣으면 데이터를 메모리에서 가져오거나 메모리에 데이터에 데이터를 옮길 수 있음
### 메모리 관리의 이중성
- 메모리 관리의 이중성은 프로세스 입장에서는 메모리를 독차지하려 하고, 메모리 관리자 입장에서는 되도록 관리를 효율적으로 할고 싶어 하는 것을 말함
### 컴파일러와 인터프린터의 동작
- 언어 번역 프로그램은 고급 언어로 작성한 소스코드를 컴퓨터가 실행할 수 있는 기계어로 번역하는 프로그램이며, 대표적으로 컴파일러와 인터프리터이다
- 컴파일러: 소스코드를 컴퓨터가 실행할 수 있는 기계어로 번역한 후 한꺼번에 실행 (C언어, 자바 등)
- 인터프린터: 소소코드를 한 행씩 번역하여 실행 (자바스크립트, 베이직 등)
### 컴파일러의 목적
- 오류 발견
    - 소스코드에서 오류를 발견하여 실행 시 문제가 없도록 함
    - 컴파일러는 오류를 찾기 위해 symbol table 사용
    - symbol table은 각 변수의 이름과 타입을 모하놓은 테이블로, 선언하지 않은 변수를 사용하지 않았는지, 변수에 다른 종류의 데이터를 저장하지 않았는지 알 수 있음
- 코드 최적화
    - 사용하지 않는 변수를 삭제하여 더욱 간결해져 실행 속도가 빨라짐
    - 결국 컴파일러는 실행하기 전에 코드를 점검하여 오류를 수정하고 최적하여 실행 파일을 만듬
### 컴파일 과정
1. 소소코드 작성 및 컴파일
    - 소스코드를 작성하여 컴파일하면 목적 코드가 만들어짐
    - 이때 목적코드는 컴퓨터가 인식할 수 있는 기계어
2. 목적 코드와 라이브러리 연결
    - 목적 코드가 만들어지면 라이브러리에 있는 코드를 목적 코드에 삽입하여 최종 실행 파일로 만듬
3. 동적 라이브러리를 포함하여 최종 실행
    - 동적 라이브러리: 실행 코드를 라이브러리에서 가져와 실행하는 방법
        - 윈도우에서는 DLL이라고 부름
    - 동적 라이브러리를 사용하면 함수가 변경되어도 새로 컴파일할 필요가 없음
### 메모리 관리자의 역할 
- 메모리 관리자는 MMU(Memory Manage Unit)이라는 하드웨어임
- 메모리 관리자의 작업
    - 가져오기
        - 프로세스와 데이터를 메모리로 가져오는 작업
        - 어떤 상황에서는 데이터 일부만 가져와 실행하기도 함 (용량이 큰 동영상 등)
        - 사용자의 요청이 없더라도 앞으로 필요할 것이라고 예상되는 데이터를 미리 가져옴 (prefetch)
    - 배치
        - 가져온 프로세스와 데이터를 메모리 어떤 부분에 올려놓을지 결정하는 작업
        - 배치 작업 전 메모리를 어떤 크기로 자를 것인지가 매우 중요 (paging)
    - 재배치
        - 새로운 프로세스를 가져와야 하는데 메모리가 꽉 찼다면 메모리에 있는 프로세스를 하드디스크로 옮겨놓아야 새로운 프로세스를 메모리에 가져올 수 있음
        - 오래된 프로세스를 내보내는 작업이 재배치 작업

## 메모리 주소
### 32bit, 64bit CPU의 차이
- CPU의 비트는 한 번에 다를 수 있는 데이터의 최대 크기를 의미
- CPU 내부 부품은 모드 이 비트를 기준으로 제작
    - 32bit CPU 내의 레지스터 크기는 전부 32bit, 산술 논리 연산장치도 32bit를 처리할 수 있도록 설계,  
    데이터 전송하는 각종 버스의 크기(대역폭도 32bit), 버스를 통해 한 번에 옮겨지는 데이터의 크기도 32bit
- CPU의 비트는 메모리 주소 공간의 크기와도 연관
    - 32bit CPU컴퓨터는 메모리를 최대 4GB까지 사용
### 상대주소를 절대 주소로 변환하는 과정
- 메모리 관리자가 재배치 레지스터를 이용하여 상대 주소를 절대 주소로 변환함
- 재배치 레지스터는 사용자 영역의 시작 주소값이 저장되어있음

## 단일 프로그래밍 환경에서의 메모리 할당
### 메모리 오버레이
- 프로그램의 크기가 실제 메모리보다 클 때 전체 프로그램을 메모리에 가져오는 대신 적당한 크기로 잘라서 가져오는 기법을 메모리 오버레이라고 함
- 프로그램을 몇 개의 모듈로 나누고 필요할 때마다 모듈을 메모리에 가져와 사용
- 메모리 오버레이에서 어떤 모듈을 가져오거나 내보낼지는 CPU 레지스터 중 하나인 프로그램 카운터(PC)가 결정
    - 프로그램 카운터(PC)는 실행할 명령어으 ㅣ위치를 가리키는 레지스터
### 스왑
- 메모리가 모자라서 쫒겨난 프로세스는 저장장치의 특별한 공간에 모아두는데 이러한 영역을 스왑 영역(swap area)
- 스왑 영역에서 메모리로 데이터를 가져오는 작업을 스왑인, 메모리에서 스왑 영역으로 데이터를 내보내는 작업을 스왑아웃
- 스왑 영역은 메모리 관리자가 관리하며, 스왑 영역의 크기는 메모리 크기로 인식

## 다중 프로그래밍 환경에서의 메모리 할당
### 메모리 분할방식
- 메모리에 여러 개의 프로세스를 배치하는 방법은 크게 가변 분할 방식, 고정 분할 방식으로 나뉨
- 가변 분할 방식: 프로세스의 크기에 따라 메모리를 나누는 것
- 고정 분할 방식: 프로세스의 크기와 상관없이 메모리를 같은 크기로 나누는 것
### 가변 분할 방식의 메모리 관리
- 가상 메모리 시스템에서는 가변 분할 방식을 세크먼테이션 기법이라고 함
- 프로세스 배치와 외부 단편화
    - 빈 영역이 있어도 서로 떨어져 있으면 프로세스를 배정하지 못하는 문제 발생 (단편화 또는 조각화하고 함)
    - 외부 단편화를 해결하기 위해 배치 방식이나 조각 모음을 사용
        - 메모리 배치 방식: 작은 조각이 발생하지 않도록 프로세스를 배치
        - 조각 모음: 조각이 발생하면 작은 조각들을 모아서 하나의 큰 덩어리로 만드는 작업
        - 메모리 배치 방식은 가변 분할 방식에서 선처리에 해당하고 조각 모음은 후처리에 해당
- 메모리 배치 방식
    - 가변 분할 방식의 단편화 문제를 해결하기 위한 대표적인 메모리 배치 방식으로 최초 배치, 최적 배치, 최악 배치가 있음
    - 최초 배치: 단편화를 고려하지 않고 프로세스를 메모리의 첫 번째 빈 공간에 프로세스를 배치
    - 최적 배치: 메모리의 빈 공간을 모두 확인 후 적당한 크기 가운데 가장 작은 공간에 프로세스를 배치
    - 최악 배치: 메모리의 빈 공간을 모두 확인 후 가장 큰 공간에 프로세스를 배치하는 방법
- 조각 모음
    - 이미 배치된 프로세스를 옆으로 옮겨 빈 공간들을 하나의 큰 덩어리로 만드는 작업
    - 조각 모음 순서
        1. 조각 모음을 하기 위해 이동할 프로세스의 동작을 멈춤
        2. 프로세스를 적당한 위치로 이동 (프로세스의 상대 주소값을 바꿈)
        3. 프로세스를 다시 시작
### 고정 분할 방식의 메모리 관리
- 가상 메모리 시스템에서는 고정 분할 방식을 페이징이라고 함
- 가변 분할 방식 (세그먼테이션)과 달리 프로세스의 크기에 상관없이 메모리를 같은 크기로 나누어 관리하기 때문에 관리가 편함 (프로세스가 여러 메모리의 여러 조각으로 나뉘어 저장되는 문제가 있음)
- 일정하게 나뉜 메모리의 크기보다 작은 프로세스가 배치될 경우 낭비되는 공간이 생기는데 이러한 현상을 **내부 단편화**라고 함
### 버디 시스템
- 가변 분할 방식의 단점이 외부 단편화를 완화하는 방법으로 버디 시스템이 있음
- 가변 분할 방식이지만 고정 분할 방식과 유사함
- 메모리를 분할할 때는 1/2로 나누고, 프로세스가 종료하면 하나의 덩어리로 합침 (내부 단편화 발생)