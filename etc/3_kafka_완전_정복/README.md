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


## ch 03. Apache Kafka 구성 및 관리
## ch 04. Apache Kafka Connect 개념 및 이해
## ch 05. Confluent Scheuma Registry 개념 및 이해
## ch 06. Kafka Stream와 ksqlDB 개념 및 이해
## ch 07. [부록] Confluent Certification 소개 및 팁들
