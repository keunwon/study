# GC 튜닝을 항상 할 필요는 없다

## GC 튜닝을 꼭 해야 할까?
- java 기반의 모든 서비스에서 GC 튜닝을 진행할 필요는 없음
- 기본 옵션
    - Xms, Mmx 옵션으로 메모리 크기 지정
    - server 옵션 포함
- GC 튜닝은 가장 마지막에 하는 작업
- Old 영역으로 넘어가는 객체의 수 최소화하기
- Full GC 시간 줄이기

## GC의 성능을 결정하는 옵션
### 메모리 크기
|구분|옵션|설명|
|:---:|:---:|:---:|
|힙 영역의 크기|-Xms|JVM 시작 시 힙 영역의 크기|
|힙 영역의 크기|-Xmx|최대 힙 영역의 크기|
|New 영역의 크기|-XX:NewRatio|New영역과 Old 영역의 비율|
|New 영역의 크기|-XX:NewSize|New 영역의 크기|
|New 영역의 크기|-XX:SurvivorRatio|Eden 영역과 Survivor 영역의 비율|
### GC 방식
|구분|옵션|
|:---:|:---|
|Serial GC|-XX:+UseSerialGC|
|Parallel GC|-XX:+UseParallelGC <br> -XX:ParallelGCThreads=value|
|Parallel Compacting GC|-XX:+UseParallelOldGC|
|CMS GC|-XX:+UseConcMarkSweepGC <br> -XX:+UseParNewGC <br> -XX:+CMSParallelRemarkEnabled <br> -XX:CMSInitialtingOccupancyFraction=value <br> -XX:+UseCMSInitiatingOccupancyOnly|
|G1|

## GC 튜닝의 절차
1. GC상황 모니터링
2. 모니터링 결과 분석 후 GC 튜닝 여부 결정
3. GC 방식/메모리 크기 지정
4. 결과 분석
5. 결과가 만족스러운 경우 서버에 반영
