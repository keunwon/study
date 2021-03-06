# 2. 대규모 데이터 처리 입문

## 5. 대규모 데이터 처리의 어려운점
### 대큐모 데이터는 어떤 점이 어려운가?
- 메모리 내에서 계산할 수 없음
    - 메모리에 올리지 않으면 기본적으로 디스크를 계속 읽어가면서 검색
    - 디스크의 I/O는 느림
### OS 레벨에서의 연구
- 디스크는 느리지만 OS는 이것을 어느 정도 커버하는 작용을 함
- os는 연속된 데이터를 같은 위치에 쌓음
    - 비슷한 데이터를 비슷한 곳에 두어 1번의 디스크 회전으로 읽는 데이터 수를 많게 한다
    - 메모리와의 속다차를 피할 수 있는 것은 아님
### 전송 속도, 버스의 속도차
- 메모리나 디스크 모두 CPU와 버스로 연결되어 있음
- 버스는 데이터를 디스크에서 메모리로 보내거나 메모리에서 CPU로 부내는 등 컴퓨터 내부에서 전송할 때 사용
- 데이터가 많아지면 많아질수록 디스크와 메모리의 차이도 나타나게 되믐로 전송속도에서도 디스크는 늦어짐
### Linux 단힐 호스트의 부하
#### 추측하지 말라, 계측하라 ... 단일 호스트의 성능 끌어내기
- 단일 호스트의 성능을 끌어내는 데에는 서버 리소스의 이용현황을 정확하게 파악할 필요가 있음
- 부하가 어느 정도 걸리고 있는지 조사 필요
#### Load Average 확인
- 시스템 전체의 부하 상황을 나타내는 지표 (top, uptime)
- 다만 Load Average만으로는 병목의 원인이 어딘지를 판단할 수 없음, Load Average 값을 시초로 병목지점에 대한 조사를 시작해야 함
#### CPU, I/O 중 병목 원인 조사
- Load Averager가 높은 경우, 다음으로 CPU or I/O 어느 작업이 원인이 있는지 조사
- CPU의 부하가 높은 경우
    - 사용자의 프로그램의 처리가 병목인지, 시스템 프로그램이 원인인지 확인 (top)
    - ps로 불 수 있는 프로세스의 상태나 CPU 사용시간 등을 보면서 원인이 되고 있는 프로세스를 찾음
    - 프로세스를 찾은 후 상세하게 조사할 경우는 strace 추적하거나 oprofile로 프로파일링을 해서 병목지점을 좁혀감
- IO 부하가 높은 경우
    - 프로그램으로부터 입출력이 많아서 부하가 높거나 스왑이 발생해서 디스크 액세스가 발생하고 있는 상황 중 하나일 경우 대부분
    - 메모리가 부족한 경우 메모리 증설
    - 메모리 증설로 대응할 수 없는 경우는 데이터 분산이나 캐시서버 도입을 등을 검토
#### OS 튜닝이란 부하의 원인을 알고 이것을 제거하는 것
- 튜닝의 본래 의미는 '병목이 발견되면 이를 제거하는' 작업
- 애초에 본래 하드웨어나 소프트웨어 지니고 있는 성능 이상의 성능을 내는 것은 아무리 노력해도 불가능

## 6. 규모조정의 요소
### 규모조정, 확장성
- 스케일 아웃은 웹 서비스에 적합한 형태이고 비용이 저렴하다는 점과 시스템 구성에 유연성이 있다는 점이 포인트
### 규모조정의 요소 (CPU 부하와 I/O 부하)
- 스케일 아웃은 하드웨어를 나열해서 성능을 높이는, 즉 하드웨어를 횡으로 전개해서 확장성을 확보, 이때 CPU 부하의 확장성을 확보하기는 쉬움
- DB서버 측면에서는 I/O 부하가 걸림
### 웹 애플리케이션과 부하 관계
- API 서버는 CPU 부하만 걸리므로 분산이 간단함
    - 데이터를 분산해서 갖고 있는 것이 아님 
    - 원래 있던 서버와 완전히 동일한 구성을 갖는 서버를 추가
    - 요청을 균등하게 분산하는 것은 로드밸런서 장치가 해줌
- IO/부하에는 문제가 있음
    - DB를 추가하면 서로 다른 DB 서버끼리 데이터를 어떻게 동기화할 것인가라는 문제가 생김
### DB 확장성 확보의 어려움
- DB에서는 디스크를 많이 사용므로 I/O를 많이 발생시키는 구성으로 되어 있으면 속도차 문제가 생김
- 데이터가 커지면 커질수록 메모리에서 처리 못하고 디스크상에서 처리할 수밖에 없는 요건이 늘어남
- 대규모 환경에서 I/O 부하를 부담하고 있는 서버는 애초에 분산시키기 어려운데다가 디스크 I/O가 많이 발생하면 서버가 금새 느려짐
### 두 종류의 부하와 웹 애플리케이션
- 일반적으로 AP 서버는 DB로부터 얻은 데이터를 가공해서 클라이언트로 전달하는 처리를 수행
    - 이 과정에서 대규모 I/O를 발생시키는 일은 드무
    - CPU 바운드한 서버
- DB 서버는 데이터를 디스크로부터 검색하는 것이 주된 일로, 데이터가 대규모가 되면 될수록 CPU에서의 계산시간보다 
I/O에 대한 영향이 커지는 I/O 바운드한 서버
#### Average가 보고하는 부하의 정체
- 처리를 실행하려고 해도 실행할 수 없어서 대기하고 있는 프로스세스 수
- CPU의 실행권한이 부여되기를 기다리고 있는 프로세스
- 디스크 I/O가 완료하기를 기다리고 있는 프로세스

## 7. 대규모 데이터를 다루기 위한 기초지식
### 대규모 데이터를 다루는 세 가지 급소 (프로그램을 작성할 때의 요령)
- 어떻게 하면 메모리에서 처리를 마칠 수 있을까?
    - 디스크 seek 횟수를 최소화한다는 의미로 메모리를 활요하고자 함
- 데이터량 증가에 강한 알고리즘을 사용하는 것
- 데이터 압축이나 검색기술과 같은 테크닉을 활용
    - 압축해서 데이터량을 줄일 수 있다면 읽어내는 seek 횟수도 적어지게 되므로 디스크 읽은 횟수를 최소화할 수 있다는 것
    - 메모리 캐싱이 쉬워짐
### Load Average 다음은 CPU 사용률과 I/O 대기율
#### sar로 CPU 사용률, I/O 대기율 확인
- sar은 이름 그대로 시스템 상황 리포트를 열람하기 위한 도구 (CPU, I/O 사용률)
- '%user'는 사용자 모드에서의 CPU 사용률, '%system'은 시스템 모드에서의 CPU 사용률
- '%iowait'은 I/O 대기율, Load Averager가 높고 이 값이 높은 경우는 부하의 원인이 I/O에 있음
