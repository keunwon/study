# 1. 대규모 웹 서비스 개발 오리엔테이션

## 1.1. 대규모 서비스와 소규모 서비스
### 소규모 서비스와 대규모 서비스의 차이
서 몇 대 정도의 소모 서비스에는 없는, 대규모 서스에만 있는 문제나 어려움에는 어떤 점들이 있을까?
#### 확장성 확보, 부분산 필요
- 가장 먼저 떠오르는 것은 확장성과 부하분산
- 대량의 엑세스가 있는 서스에서 서버 1대로 처리할 수 없는 부하를 '스케일아웃(scale-out)'이 문제에 대한 전략의 기초
    - 즉 서버의 역할을 분담하거나 대수를 늘림으로써 시스템의 전체적인 처리능력을 높여 부하를 분산
    - 사용자로부터의 요청을 어떻게 분배할 건이가? -> 로드밸런서를 사용
    - 데이터 동기화는 어떻게 할것인가?
    - 네트워크 통신의 지연시간을 어떻게 생각해볼 수 있을까?
- 하드웨어의 성능을 높여 처리능력을 올리는 '스케일 업(scale-up)'
#### 다중성 확보
- 시스템은 다중성을 지니 구성, 즉 특정 서버가 고장 나거나 성능이 처하되더라도 서비스를 계속할 수 있는 구성으로 할 필요가 있음
- 서버가 고장 나거나 급격하게 부하가 올라갈 경우에도 견딜 수 있는 시스템을 구성할 필요가 있음
#### 효율적 운용 필요
- 서버 대수가 많지만 각 서버마다 부하는 괜찮은지, 고장난 부분은 없는지, 디스크 용량은 충분하지, 보안 설정에 미비한 점은 없는 등등 이를 모든 서버에 대해 여기저기 잘 살펴야 함
## 1.2. 계속 성장하는 서비스와 대규모화의 벽
### 웹 서비스의 어려움
- 웹 서비스가 다른 애플리케이션에 비해 어려운 또 하나의 원인은 서비스가 계속해서 성장해간다는 데 있음
- 성장함에 따라 시스템 구성을 변화시켜 갈 필요가 있음
- 보유하되는 데이터량도 커짐
    - 데이터가 늘어났다고 해서 예전 데이터를 블로그 사용자가 임의로 지워버릴 수는 없으므로 모든 데이터는 보존하고 필요한 경우 추출해낼 필요가 있음
### 시스템의 성장전략
- 서비스가 아직 소규모인 단계에서는 심플한 방법이 더 나은 경우가 많으므로 너무 이른 최적화가 좋은 방침이라고는 할 수 없음