# 서버를 어떻게 세팅해야 할까?

## 웹 기반의 시슴템 성능 세팅
- 웹 서버 세팅
- WAS 서버 세팅
- DB 서버 세팅
- 장비 세팅

## 아파치 웹 서버의 설정
- WAS 앞에 위치해야함
- 정적인 부분은 웹 서버에서 처리
### httpd.conf 
``` properties
ThreadsPerChild 250
MaxRequestsPerChild 0
```
- ThreadsPerChild: 웹 서버가 상용하는 스레드 개수 (프로세스 1개 기준)
- MaxReqeustsPerChild: 최대 요청 개수 (0인 경우 제한을 없음)
### httpd-mpm.conf
``` xml
<IfModule mpm_worker_module>
startServers 2
MaxClients 150
MinSpareThreads 25
MAxSpareThreads 75
ThreadsPerChild 25
MaxrequestsPerChild 0
</IfModule>
```
- StartServers: 서버를 띄울 때 프로세스의 개수
- MaxClients: 최대 처리 가능한 클라이언트의 수
- MinSpareThreads: 최소 여유 스레드 수
- MaxSpareThreads: 최대여유 스레드 수
- ThreadsPerChild: 프로세스당 스레드 수
- MaxRequetsPerChild: 초대 요청 개수 (0인 경우 제한 없음)
### 더 많은 사용자 처리하기
- 서버를 늘린다: 금전적인 여유 필요
- 서비스를 튜닝한다: 서비스가 응답이 안 되는 원인을 찾고 튜닝한다 (원인을 찾는데 몇 시간 몇 달이 걸릴 수 있음)
- GC 튜닝을 한다: GC가 오래 소요되어 응답이 안되는 경우 GC튜닝을 한다
- 각종 옵션 값을 튜닝한다: 가장 간단한 방법일 수 있지만 잘못 설정하면 더 큰 문제가 발생 할 수 있다.
### 웹 서버의 Keep Alive
``` properties
keepAlive On
keepAliveTimeOut 15
```
- http.conf 설정 파일을 통해서 세팅 할수 있음
- KeepAlive: HTTP통신 시 일정 시간동안 끊지 않고 통신을 함 (많은 리소스들을 불러올때 유용함)
- keepAliveTimeOut: KeepAlive 시간 지정

## DB Connection Pool 및 스레드 개수 설정
- Connection Pool의 최소 및 최대값을 동일하게 하는게 좋음
- DB서버의 리소스가 부족하면 최소 값을 적게 해 놓는 것도 방법이다
- Connection Pool을 N개 지정하면 스레드 개수는 N + 10 정도로 지정한다