# Part 1. Confluent 엔니어가 설ㅇ해주는 Apache Kafka (기본/심화 개념, 아키텍처와 생태계)
## ch 01. Apache Kafka 기본 개념 및 이해
### 01. Apache Kafka
#### 특징
- 이벤트 스트림을 안전하게 전송 publish & subscribe
- 이벤트 스트림을 디스크에 저장 write to disk (가장 큰 특징)
- 이벤트 스트림을 분석 및 처리
#### 사용 사례
- Messaging Sysetm
- IOT 디바이스로부터 데이터 수집
- 애플리케이션에 발생하는 로그 수집
- 이상 감지
- DB 동기화 (MSA 기반의 분리된 DB간 동기화)
- 실시간 ETL
- Spark, Flink, Storm, Hadoop과 같은 빅데이터 기술과 같이 사용
#### 산업 분야별 사용 사례
- 교통
    - 운전자-탑승자 매치
    - 도착예상 시간 업데이트
    - 실시간 차량 진단
- 금융
    - 사기 감지, 중복 거래 감지
    - 거래, 위헙 시스템
- 오락
    - 실시간 추천
    - 사기 감지
    - In-App 구매
- 온라인 마켓
    - 실시간 재고 정보
    - 대용량 주문의 안전한 처리
### 02. Topic, Parition, Segment
- producer: 메시지를 생산해서 Kafka의 topic으로 메시지를 보내는 애플리케이션
- consumer: topic의 메시지를 가져와서 소비하는 애플리케이션
- consumer group: topic의 메시지를 사용하기 위해 협력하는 consumer들의 집합
#### producer, consumer의 기본 동작 방식
- producer, consumer는 서로 알지 못함
- producer, consumer는 각 고유의 속도로 commit log에 write 및 read 수행
#### commit log
- 추가만 가능하고 변경 불가능한 데이터 스트럭처
- 데이터(event)는 항상 로그 끝에 추가되고 변경되지 않음
- offset: commit log에서 event 위치
#### Logical View
- topic: Kafka 안에서 메시지가 저장되는 장소 (논리적인 표현)
- partition: commit log, 하나의 topic은 하나 이상의 partition으로 구성 (q병령 처리를 위해서 다수의 parition사용)
- segment
    - 메시지(데이터)가 저장되는 실제 물리 File
    - segment file이 지정된 크기보다 크거나 지정된 기반보다 오래도면 새 파일이 열리고 메시지는 새 파일에 추가
#### Physical View
- Topic 생성 시 Parition 개수를 지정하고, 각 Partition은 Broker들에 분산되어 Segment File들로 구성
### 03. Broker, Zookeeper
#### Broker
- Broker는 Partition에 대한 Read, Write를 관리하는 소프트웨어
- Kafka Server라고 부름
- Topic내의 Partition 들을 분산, 유지 관리
- 각 Borker들은 id로 식별 (id는 숫자)
- Topic 일부 Paritition 포함
- Kafka Cluster: 여러 개의 Broker들로 구성
- Client 특정 Broker에 연결하면 전체 클러스터에 연결
- 최소 3대 이상의 Broker를 하나의 Cluster로 구성 (4대 이상 권장)
#### Zookeeper
- Zookeeper는 Broker를 관리
- Zookeeper는 변경 사항에 대해 Kafka에 알림 (Topic 생성/제거, Broker 추가/제거)
- Zookeeper는 홀수의 서버로 작동하게 설계 (최소 3, 권장 5)
    - Ensemble은 Zookeeper 서버의 클러스트
    - Ensemble은 Quorum 알고리즘 기반이다
        - Quorum은 합의체가 의사를 진행시키거나 의결을 하는데 필요한 최소한도의 인원수를 뜻함
- Zookeeper에는 Leader(Writes)가 있고 나머지는 서버는 Follwer(reads) 구성
### 04. Producer
- Kafka는 Record(데이터)를 Byte Array로 저장
- Message는 Header, Key, Value로 구성
- Producer는 serializer, Consumer는 Deserializer 사용
- Producer는 Key 존재 여부에 따라 Paritioner를 통한 메시지 처리 방식이 다름
### 05. Consumer
#### Message Ordering (순서)
- Partition이 2개 이상인 경우 모든 메시지에 대한 젠체 순서 보장 불가능
- 대부분의 경우, Key로 구분할 수 있는 메시지들의 순서 보장이 필요한 경우가 많음
- 동일한 key를 가진 메시지는 동일한 partition에만 전달되어 key 레벨의 순서 버장 가능 (멀티 partition 사용가능 = 처리량 증가)
- 운영중에 partition 개수를 변경하면 순서 보장 불가
#### Consumer Failure (Consumer Rebalancing)
- Consumer Group 내에 다른 Consumer가 실패한 Consumer를 대신하여 Partition에서 데이터를 가져와 처리
- Parition은 항상 Consumer Group내에 하나의 Consumer에 의해서만 사용
### 06. Replication
#### Broker에 장애가 발생하면?
- Partition을 복제하여 다른 Broker상에서 복제물을 만들어서 장애를 미리 대비
    - Replicas = Leader Partition, Follow Partition
- Producer는 Leader에만 Write, Consumer는 Leader로부터 Read함
    - Follwer는 Broker 장애 시 안정성을 제공하기 위해서만 존재
    - Follwer는 Lead의 commit log에서 데이터를 가져오기 요청(Fetch Request)으로 복제
#### Hot Spot 방지
- 하나의 Broker에만 Parition의 Leader들이 몰려 있다면?
- Paritition의 Leader를 절절히 Borker에 분배해야 함
#### Rack Awareness
- 동일한 Rack 혹은 Available Zone상의 Broker들에 "rack.name" 지정
- 복제본은 최대한 Rack 간 균형을 유지하여 Rack 장비 대비
- Topic 생성 시 똔느 auto Data Balancer/ Self Balacing Cluster 동작 때만 실행
### 07. In-Sync-Replicas
- In-Sync-Replicas (ISR): Leader 장애 시 Leader를 선출하는데 사용
    - High Water Mark라고 하는 지점까지 동일하게 Replicas
- Leader 장애가 발생하면, ISR중에 새 Leader를 선출
- replica.lag.time.max.ms 옵션으로 ISR 판단
#### ISR은 Leader의 브로커가 관리 (파티션 X)
- Zookeepr에서 ISR 업데이트, Controller가 Zookeeper로부터 수신
1. replica.lag.time.tax.ms 이내에 Follower가 fetch하지 않으면 ISR에서 제거 
2. ISR 목록을 zookeeper에게 알림
3. zookeeper는 Controller에게 알림
#### Controller
- Kafka Cluster내에 Broker 중 하나가 Controller가 됨
- Controller는 zookeeper를 통해 broker liveness를 모니터링
- Contorller는 leader와 replica 정보를 cluster내의 다른 broker들에 전달
- Controller가 Leader 장애 시 Leader Election을 수행
- Controller가 장애가 나면 다른 Active Broker들 중에서 재 선출
#### Consumer 관련 Position들
- Last Commit Offset(Current Offset): Consumer가 최종적으로 Commit한 Offset
- Current Custor: Consumer가 읽어간 위치 (처리 중, Commit 이전)
- High Water Mark: ISR간에 복제된 Offset (복제가 완료된 offset)

## ch 02. Apache Kafka 심화 개념 및 이해
### 01. Producer Acks, Batch, Page Cache, Flush
#### Producer Acks 
- Producer Parameter 중 하나
- acks = 0: ack가 필요하지 않음 (메세지 손실이 다소 있더라도 빠르게 메시지를 보내야 하는 경우에 사용)
- acks = 1
    - default 값
    -  Leader가 메시지를 수신하면 ack를 보냄
- acks = -1 or acks = all
    - Leader가 모든 Replica까지 Commit 되면 ack를 보냄
#### Producer Retry 
- 재전송을 위한 Parameters  
- 보통 delivery.timeout.ms 조정으로 재시도 동작을 제어
- ack = 0 옵션에서는 retry 무의미

|parameter|설명|default|
|:---:|:---:|:---:|
|retries|메시지 send하기 위해 재시도하는 횟수|MAX_INT|
|retry.backoff.ms|재시도 사이에 추가되는 대기시간|100|
|request.timeout.ms|Producer가 응답을 기다리는 최대 시간|30,000(30초)|
|delivery.timeout.ms|send 후 성공 또는 실패를 보고하는 시간의 상한|120,000(2분)|
#### Producer Batch 
- 메시지를 모아서 한번에 전송
- 옵션
    - linger.ms 
        - default: 0, 즉시 보냄
        - 메시지가 함꼐 Batch 처리될 때까지 대기 시간
    - batch.size
        - default: 16KB
        - 보내기 전 batch의 최대 크기
    - 일반적으로 batch 처리 시 linger.ms = 100, batch.size = 1000000
- message send 순서 보장
    - 진행 중인 여러 요청을 재시도하면 순서가 변경될 수 있음
    - 옵션
        - max.in.flight.requests.per.count
            - default: 5
            - multiple in-flight request
        - enalbe.idempotence
            - true 변경 시 메시지 순서 보장
#### Page Cache 와 Flush
- 메시지는 Partition에 기록
- Partition은 Log Segment file로 구성 (기본 1GB마다 새로운 segment 생성)
- 성능을 위해 Log Segment는 OS Page Cache에 관리
- 로그 파일에 저장된 메시지 데이터 형식은 Broker가  Producer로 부터 수신한것, Consumer에게 보내는 것과 동일하게 동일하므로, Zero-Copy가 가능
- Page Cache 디스크로 Flush
    - Broker가 완전히 종료
    - Os background 'Flusher Thread' 실행
> Zero-Copy? CPU의 개입 없이 Page Cache와 Network Buffer 사이에서 직접 전송되는 것을 의미, 이것을 통해 Broker Heep 메모리를 절약하고 엄청한 처리량을 제공
### 02. Replica Failure
#### Controller가 새로운 Leader 선출
1. Leader Broker 장애
2. Controller가 Zookeeper session timeout을 통해 장애 감지
3. Controller가 Leader를 선출, 새로운 ISR 리스트를 Zookeeper에 기록
4. Controller가 모든 Borker에 새로운 ISR을 push
5. Client들은 메타 데이터를 요청하여 새로운 Leader 정보를 받음
### 03. Replica Recovery
#### acks = all 중요성
- follower partition에 leader 데이터가 복제가 되어야지 성공, 아니면 producer 재요청 시도를 함
#### Topic 파라미터
- unclean.leader.election.enable
    - default: false
    - ISR 리스트에 없는 Replica를 선출할 것인지에 대한 옵션
    - ISR 리스트에 Replica 하나도 없으면 서비스가 중지되지만 해당 옵션을 true로 변경하면 서비스 중단 없이 사용가능,  
    데이터 유실이 존재
- min.insync.replicas
    - default: 1
    - 최소 요구되는 ISR의 개수에 대한 옵션 (leader + follower의 개수)
    - Producer의 acks = all과 함께 사용할 때 강력한 보장 (+ min.insync.replicas = 2)
#### 가용성과 내구성 중 선택
- 데이터 유실이 없게 하려면
    - Topic: replica.factor는 2 보다 커야 함 (최소 3 이상)
    - Producer: aks = all
    - Topic: min.insync.replicas는 1 보다 커야 함
- 데이터 유실이 있더라도 가용성을 높게 하려면?
    - Topic: unclean.leader.election.enable = true
> Reader Epoch? 새로운 Leader 선출 시 Leader Epoch가 0에서 1로 증가, 장애가 발생한 전 Leader 파티션은 Leader Epoch를 기준으로 데이터를 적제, 만약 Leader Epoch 위치보다 데이터가 더 들어가있으면 추가된 데이터 삭제
### 04. Consumer Rebalance
#### Consumer의 동작 방식 (Partition에서 메시지 Polling)
- Consumer는 메시지를 가져오기 위해서 Partition에 연속적으로 Poll 함
- 가져온 위치를 나타내는 Offset 정보를 __consumer_offset Topic에 저장하여 관리
#### Partition Assignment
##### 하나의 consumer group에게 assign(할당) 할 때
- 하나의 partition은 consumer group내의 하나의 consumer 만 사용
- 동일한 key를 가진 메시지는 동일한 consumer가 사용 (partition 수를 변경하지 않는 한)
- consumer의 설정 파라미터 중 partition.assignment.strategy로 할당 방식 조정
- consumer group은 group codinartor라는 프로세스에 의해 관리
#### Consumer Group을 등록하고 Consumer 전체의 Partition 할당하는 프로세스
1. Consumer 등록 및 Group Codinator 선택
    - Group Codinator 선정 방법: hash(group.id) % offset.topic.num.partitions 수식을 사용하여    
    group.id가 저장될 __consumer_offsets의 파티션 결정
2. JoinGroup 요청 순서에 따라 Consumer 나열
    - Group Codinator는 Group의 Consumers 카탈로그를 생성하기 전에 Consumers의 JoinGroup 요청에 대기 (group.initial.rebalance.delay.ms 기본: 3초)
3. Group Leader 결정 및 Partition 할당
    - Group Leader 선정 방식은 이전 단계(2)에서 JoinGroup 요청이 가장 먼저 들어온 Consumer가 선정
    - Group Codinator로 부터 Consumer Group 목록을 받으면 Group Leader에서 구성한 'parition.assignment.strategy'를 이용하여 consumer에거 파티션 할당
4. Consumer -> Partition 매핑 정보를 Group Codinator에게 전송
5. 각 Consumer에게 할당된 Partition 정보를 보냄
#### Rebalancing Trigger
- consumer가 consuemr group에 합류/탈퇴
- consumer가 topic을 변경
- consumer group은 topic 메타데이터 변경 사항을 인지 (parition 증가)
#### Rebalancing Process
1. group codinator는 heatbeats의 플래그를 사용하여 consumer에게 rebalance 신호를 보냄
2. consumer가 일시 중지하고 offset commit
3. consumer는 consumer group의 새로운 generation에 합류
4. parition 재 할당
4. consumer는 새 partition에서 다시 consume을 시작
#### Consumer Heatbeats
- consumer 장애를 인지하기 위함
- consumer는 poll()과 별개로 백그라운드 Thread에서 Heatbeat를 보냄 (heatbeat.interval.ms 기본: 3초)
- Heatbeats가 수신되지 않으면 consumer는 consumer group에서 삭제
    - session.timeout.ms (기본: 10초)
- poll()은 Heatbeats와 상관없이 주기적으로 호출되어야 함
    - max.poll.inteval.ms (기본: 5분)
#### 과도한 Rebalacing을 피하는 방법
1. consumer group 고정
    - group의 각 consumer에게 고유한 group.instance.id 할당
    - consumer는 LeaveGroupRequests를 사용하지 않아야 함
    - ReJoin(재가입)은 알려진 group.instance.id에 대한 Rebalacen를 trigger를 하지 않음
2. session.timeout.ms 튜닝
    - heatbeat.integer.ms를 session.timeout.ms의 3/1 로 설정
    - group.min.session.timeout.ms(default: 6 seconds)와 group.max.session.timeout.ms (default: 5 minutes)의 사이값
    - 장점
        - consumer가 Rejoin할 수 있는 더 많은 시간을 제공
        - consumer 장애를 감지하는데 시간이 더 오래 걸림
3. max.poll.interval.ms 튜닝
    - consumer에게 poll()한 데이터를 처리할 수 있는 충분한 시간 제공
    - 너무 크게 하면 안됨
### 05. Partition Assignment Strategy
#### RangeAssignor
- Topic별로 작동 (default)
- 동일한 key를 가지고 있는 메시지들에 대한 Topic들 간에 'co-parititioning' 하기 유리
- Topic의 Partition 개수가 동일한 경우, co-partition 가능
#### RoundRobinAssignor
- Round Robin 방식으로 consumer에게 partition을 할당
- Reassign(재할당) 후 consumer가 동일한 partition을 유지한다고 보장하기 않음
- 할당 불균형이 발생할 가능이 있음
#### StickyAssignor
- 최대한 많은 기존 paritition 할당을 유지하면서 최대 균형을 이루는 할당 보장
- Range 방식보다 Rebalancing 오버헤드를 줄임
    - 기존 partition 할당을 유지
### 06. Cooperative Sticky Assignor
#### Incremental Cooperative Rebalancing Protocol
- 전체 재조정 동안 모두 정지 상태로 있는 대신, Consumer 하나의 Partition을 취소하는 동안만 기동 중지
#### Cooperative Sticky Assignor
- Rebalancing을 두 번 수행
- JoinGroup 요청을 보내면서 시작하지만, 소유한 모든 Paritition을 보유하고, 그 정보를 Group Codinoator에게 보냄
- Group Leader는 원하는 대로 Consumer에 Partition에 할당하지만, 소유권을 이전하는 Partition들만 취소함
    - 소유권을 이전하는(revoke) partition의 정보는 Group Codinator가 알려줌
- Partition을 취소한 구성원은 그룹에 ReJoin하여 취소된 Partition을 할당할 수 있도록 두 번째 재조정을 트리거
- 1st rebalance에서 consumer는 자신의 partition 중 어느 것이 다른 곳으로 재할당되어야 하는지 알게 됨
### 07. Kafka Log File
#### Kafka Log Segment File Directory
- 각 Broker의 log.dirs 파라미터로 정의
- Kafka Log Sement File은 Data File이라고 부르기도 함
- Segment File이 생성되는 위치는 각 Broker의 server.properties 파일 안에 'log.dirs' 파라미터로 정의
#### Partition 디렉토리에 생성되는 파일들의 타입
- .log: Log Segment file (메시지와 metadata를 저장)
- .index: 각 메시지의 Offset을 Log Segment 파일의 Byte위치에 매핑
- .timeindex: 각 메시지의 timestamp를 기반으로 메시지를 검색하는 데 사용
- .leader-epoch-checkpoint: Leader Epoch과 관련된 Offset 정보를 저장
- .snapshot: IdepmpotentProducer(메시지 순서 보장)를 사용하면 생성
- .txnindex: Transaction Producer를 사용하면 생성
#### Log Segment File Rolling 파라미터
- log.segment.bytes: default 1GB
- log.roll.ms (default: 168시간 = 7일)
- log.index.size.max.bytes (default: 10MB)
- __consumer_offset (Offset Topic)의 Segment File Rolling 파라미터는 별도
    - offsets.topic.segment.bytes (default: 100MB)
#### CheckPoint File
- 각 Broke에는 2개의 Checkpoint File이 존재함
- logs.dir: 디렉토리 위치
- replication-offset-checkpoint
    - 마지막으로 commit된 메시지의 ID인 High Water Mark 시작 시 Follower가 이를 사용하여 Commit되지 않은 않은 메시지를 Truncate
- recovery-point-offset-checkpoint
    - 데이터가 디스크로 flush된 지점
    - 복구 중 Broker는 이 시점 이후의 메시지가 손실되었는지 여부 확인
### 08. Exactly Once Semantics (EOS)
- Producer가 메시지를 전송을 다시 시도하더라도 메시지가 최종 consumer에게 정확히 한 번 전달
- 메시징 시스템 자체와 메시지를 생성하고 소비하는 애플리케이션 간의 협력이 반드시 필요
#### EOS 필요성
- 데이터가 정확히 한번 처리되도록 보장해야 하는 실시간 미션 크리티컬 스트리밍 Application
- 클라이언트(Idempotent Producer)에서 생성되는 중복 메시지 방지
- Trsansaction 기능을 사용하여, 하나의 트랜잭션내의 모든 메시지가 모두 Write되었는지 또는 전혀 Write되지 않았는지 확인
- Use Cases
    - 금융 거래 처리 - 송금, 카드 결제 등
    - 과금 정산을 위한 광고 조회 수 추적
    - Billing 서비스간 메시지 전송
#### EOS 관련 파라미터
##### Idempotent Producer
- enable.idempotence: true
- Producer가 retry를 하더라도, 메시지 중복을 방지
- 성능에 영향이 별로 없음 (메시지 헤더에 id값만 추가되어서 전송)
##### Transaction
- 각 Producer에 고유한 transactional.id 선정
- Producer를 Transaction API를 사용하여 개발
- Consumer에서 isolation.level을 read_committed로 설정
> Broker 파라미터는 Transaction을 위한 Default 값이 적용되어 있음 (필요시에만 수정 필요)
### 09. Exactly Once Semantics(EOS) 2
#### Transaction 새로운 핵심 개념 도입
- Transaction Coordinator
    - Consumer Group Coordinator와 비슷하게, 각 Producer에게는 Transaction Coordinator가 할당되면, PID 할당 및 Transaction 관리의 모든 로직을 수행
- Transaction Log
    - 새로운 Internal Kafka Topic
    - Consumer Offset Topic과 유사하게, 모든 Transaction의 영구적이고 복제된 데이터를 저장하는 Transaction Coordinator 상태 저장소
- TransactionalId
    - Producer를 고유하게 식별하기 위해 사용되면, TransactionId를 가진 Producer의 다른 인스턴스들은 어전 인스턴스에 의해 만들어진 모든 Transaction을 재개(또는 중단)할 수 있음

## ch 03. Apache Kafka 구성 및 관리
### 01. Apache Kafka and Confluent Reference Architecture
#### Eventsizer
- https://eventsizer.io
- Kafka 구성 시 필요한 하드웨어 스펙이나, 현재 하드웨어 스펙을 기준으로 얼마나 사용할 수 있는지 알려주는 사이트
### 02. Kafka in Real Environment
#### 전용 서버 권장
- Broker는 분리된 각각의 전용 서버에 분리하여 설치 구성하는 것을 권장
- N개의 Broker가 있으면, Replication Factor(RF)는 최대 N까지 사용하여 Topic 생성
- Mission Critical Topic에는 Replication Factor는 보통 3을 많이 사용
    - 3개의 Broker로 구성하고 하나의 Broker가 장애 상태시, RF 3인 Topic 생성 불가능
    - Broker는 4개 이상 하나의 Cluster로 구성하는 것을 권장
- 데이터 유실 방지를 위해서, min.sync.replicas는 2를 많이 사용
#### 처리량에 따라 Thread 관련 파라미터 튜닝 필수
- Broker는 CPU를 많이 사용하지 않으나, 처리량에 따라 Thread 파라미터 튜닝이 필요하며, Thread 증가에 따라 CPU 사용량이 증가함
- Dual 12-core Sockets를 권장 (24-core)
- Broker 파라미터
    - num.io.threads
        - 기본: 8
        - Dish 개수보다 크게 설정
    - num.network.threads
        - 기본: 3
        - TLS를 사용할 경우 두 배 이상으로 설정
    - num.recovery.threads.per.data.dir
        - 기본: 1
        - Broker 시작 시 빠른 기동을 위해, core수까지만 설정
    - num.replica.fetchers
        - 기본: 1
        - 보통 4 ~ 6개
        - source broker에서 메시지를 복제하는데 사용되는 thread 수
        - 빠르게 복제하기 위해 값을 증가
        - Broker의 CPU 사용률과 네트워크 사용률이 올라감
    - num.cleaner.threads
        - 기본: 1
        - Disk 개수 혹은 core 개수까지만 설정
#### Broker는 File을 많이 사용
- Broker는 많은 수의 Partition을 지원하므로 상대적으로 소규모 배포에서도 Open File Handler수가 기본값을 쉽게 초과할 수 있음
- 최소: ulimit -n 100000
#### Broker는 JVM Heap을 많이 사용하지 않음
- Broker의 Heep 메모리는 운영환경의 경우 대부분 6GB까지 할당
- 매우 큰 Cluster 혹은 많은 Partition이 필요한 경우 12GB 이상 사용
- Broker의 OS만을 위해서는 보통 1GB정도 할당
- Broker는 OS PAGE Cache를 많이 사용
    - OS PAGE Cache를 통해서 Zero Copy전송을 수행
    - 많을 수록 성능에 유리
- 운영환경 Broker 메모리는 최소 32GB 이상 권장, 처리량에 따라서 64GB이상 사용 권장
#### Heep 메모리 설정 방법
- kafka-server-start 스크립트에 Java Heep 설정하는 옵션이 있음
#### Broker Network
- 처리량이 작은 Application인 경우, Broker의 NW은 1GB 충분
- 처리량이 큰 Application인 경우, Broker의 NW은 10GB 이상 필요
- Producer에서 압축 옵션을 사용하면 네트워크를 보다 효율적으로 사용 가능
- Internal과 External 트래픽 간 분리 가능
#### Broker Disk (Kafka 성능에 큰 영향)
- Kafka Broker의 data log용 disk는 os용 disk와 분리 권장
- Broker의 data log용으로 여러개의 Local Disk사용을 권장 (RAIO 10 권장, JBOD 사용 가능)
- SSD Disk 권장
- XFS 파일스템을 사용해야 함
- mount시에 noatime 옵션 사용
    - Linux가 각 파일에 마지막으로 엑세스한 시간을 기록하는 파일, 시스템 메타데이터를 유지 관리하는 방식을 off
- Broker 파라미터 중 log.dirs에 콤마(,)로 구분한 디렉토리들로 정의
- 하나의 Partition은 하나의 volume에서 생성
- Partition들은 log.dirs의 디렉토리에 round-robin 방식으로 분배
- NAS 사용 불가
#### Virtual Memory (Kafka 성능에 큰 영향)
- Memory swapping 최소화
    - vm.swappiness=1 (기본 60)
- Blocking Flush(synchronous) 빈도 감소
    - vm.dirty_ratio=80 (기본 20)
- Non-Blocking background flushes (aysnchronous) 빈도 증가
    - vm.dirty_background_ratio=5 (기본: 10)
- 위 파라미터들은 /etc/sysctl.conf에 설정하고 sysctl -p 로 load 함
#### Zookeeper 권장 사양
- 홀수 개로 구성
- 2core ~ 4 core
- memory 8GB
- Transaction log(dataLogDir) 512GB SSD
- Database snapshots(dataDir) 2TB SSD RAID 10
#### Zookeeper의 server.properties 내의 purge snapshopts 파라미터 설정
- autopurge.snapRetainCount: 보존할 Snapshop 개수 (권장 3)
- autopurge.purgeInterval: Purge Interval(권장 24)
- 위의 옵션은 24시간마다 3개를 제외한 모든 스냅샷을 제거하는 설정
### 03. Installaction, Cluster Configuration
### 04. Log Retention, Cleanup Policy
#### Log 파일 관리 (Log Cleanup Policy)
- Log는 Consume되어도 지우지 않음
- Broker or Topic 단위로 cleanup 정책을 설정
- log.cleanup.policy 파라미터
    - delete
    - compact
    - delete, compact
- 현재 active sgement의 log는 cleanup 대상이 아님
#### Log Segment 삭제하는 정책
- log.cleanup.policy: delete
- log.retention.ms: log 보관 주기 (기본 7일)
- log.retention.check.interval.ms: log segment를 체크하는 주기 (기본 5분)
- segment 파일에 저장된 가장 최신의 메시지가 log.retension.ms보다 오래된 segment를 삭제
#### Compact Cleanup Policy
- 각 key의 최신 value만을 유지
- compact 정책은 partition별로 특정 key의 최신 value만 유지하며 압축
#### Log comaction 설정
- log.cleaner.min.cleanable.ratio
    - 기본: 0.5
    - head 영역 데이터가 tail 영역보다 크면(기본값 50 %) clean 시작
- log.cleaner.io.max.bytes.per.second
    - 기본: 무제한
    - Log Cleaner의 Read/Write의 처리량을 제한하여 시스템 리소스 보호 가능
- 동일한 key를 갖는 메시지가 매우 많은 경우, 더 빠른 정리를 위해서 파라미터 값 변경 필요
    - log.cleaner.threads
        - 기본: 1
        - core 수 만큼 할당
    - log.cleaner.dedupe.buffer.size
        - 기본 값: 134,217,728
#### Tombstone Message (Log Compaction시 특정 key 데이터 삭제)
- compaction 사용시에 key를 가지는 메시지를 지우려면, 동일한 key에 null value를 가지는 메시지를 topic으로 보냄
- consumer는 해당 메시지가 지워지기 전에(기본 1 day) 해당 메시지를 conume 할 수 있음
    - log.clean.delete.retention.ms
        - 기본: 1 day
        - 메시지를 지우기 전 보관 기간
### 05. Kafka Cluster Upgrade, Expansion, Shrink
#### kafka cluster 확장
- 고유한 ID를 부여한 새로운 broker를 zookeeper Ensemble에 연결
- 자동으로 partition을 새로운 broker로 옮겨주지 않음
    - kafka-reassign-partition 도구
    - Confluent Auto Data Balancer / Self-Balancing Cluster 기능 
##### Confluent ADB(Auto Data Balancer)
- Cluster내의 전체 Broker에 대한 Partition 리밸런싱을 명령어 하나로 자동 계산 및 수행
- 한번에 하나의 리밸런싱만 수행 가능
- 시스템 자원을 많이 사용
- Cluster내의 전체 Broker가 살아있을 때만 동작
##### Confluent Self-Balancing Cluster
- 계산 및 실행을 모두 자동화
- Broker 노드 추가시, 자동으로 리밸런싱 실행

## ch 04. Apache Kafka Connect 개념 및 이해
### 01. Apache Kafka Connect
#### 용어 정리
- Kafka Connect
    - Apache Kafka 안팎으로 데이터를 스트리밍하기 위한 Framework
    - 다른 데이터 시스템을 kafka와 통합하는 과정을 표준화한 Framework
    - 통합을 위한 Connector 개발, 배포, 관리를 단순화
- Connectors: task를 관리하여 데이터 스트리밍을 조절하는 plugin(jar)
- Tasks: Kafka와 다른 시스템간의 데이터를 전송하는 방법의 구현체
- Workers: Connector, Task를 실행하는 실행 중인 프로세스
- Convets: Connect와 데이터를 보내거나 받는 시스템 간에 데이터를 변환하는 데 사용되는 Components
- Transfroms: Connector에 의해 생성되거나 Connector로 전송되는 각 메시지를 변경하는 간단한 Components

## ch 05. Confluent Scheuma Registry 개념 및 이해
### 01. Confluent Schema Registry
#### Schema
- Data Structure
- 데이터를 만드는 Producer와 데이터를 사용하는 Consumer 간의 계약으로 사용
#### AVRO
- Apache Open Source Software 프로젝트
- 데이터 Serialization 제공
- Java 포함한 많은 프로그래밍 언어에서 지원
- 데이터 구조 형식 제공
- Avro 데이터는 바이너리이므로 데이터를 효율적으로 저장
- 장점
    - 압축, 고성능, 바이너리 포멧
    - AVRO 데이터 파일이 저장되면 해당 스키마가 함께 저장되므로 나중에 모든 프로그램에서 파일 처리 가능
    - AVRO 스키마는 JSON으로 정의
    - 데이터 타입을 알 수 있음
    - Confluent Schema Registry에서 사용 가능
- 단점
    - 바이너리 형태로 serialization되기 때문에 데이터를 쉽게 해석하기 어려움
#### Schema 설계시 고려할 점
- 삭제될 가능성이 있는 필드이면 default value를 반드시 지정
- 추가되는 필드라면 default value를 지정
- 필드의 이름을 변경하지 않음
#### Confluent Schema Registry
- 모든 스키마의 버전 기록을 저장
- Avro 스키마 저장 및 검색을 위한 Restful 인터페이스 제공
- 스키마를 확인하고 데이터가 스키마와 일치하지 않은 예외를 던짐
- 호환성 설정에 따라 스키마 진화 가능
- 각 메시지와 함께 Avro 스키마를 보내는 것은 비효율적
    - 대신 Avro 스키마를 나타내는 Golbal Unique ID가 각 메시지와 함께 전송
- Schema Registry는 특별한 Kafka Topic스키마 (__schemas) 정보를 저장

## ch 06. Kafka Stream와 ksqlDB 개념 및 이해
### 01. Kafka Streams, ksqlDB
#### 실시간 이벤트 스트림 데이터 분석 및 처리
- Database에 저장 후 분석 및 처리하는 것이 아니라, Kafka에 있는 움직이는 데이터를 바로 분석 및 처리하는 것이 핵심
#### Kafka 진영에서 나온 Realtime Event Stream Processing 방법들
- Kafka Streams
    - Event Streaming용 Library (Java, Scala)
    - Fromework 아니기 때문에 별도의 Cluster 구축이 불필요
- ksqlKB
    - Event Streaming Database - RDBMS/NoSQL DB가 아님
