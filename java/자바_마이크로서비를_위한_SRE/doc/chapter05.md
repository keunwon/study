# 5. 멀티 클라우드와 지속적 전달의 안정성
## 5.1. 플랫폼 유형
- 서비스형 인프라(IaaS)
	- 가상화 컴퓨팅 자원을 서비스로 제공
	- 전통적으로 서버, 스토리지, 네트워킹 하드웨어, 하이퍼바이저 계층을 책임지며, 이 리소들을 관리하는 API와 사용자 인터페이스를 제공
	- Iaas를 사용한다는 것은 물리적 하드웨어를 IaaS로 대체한다는 뜻
- 서비스형 컨테이너(CaaS)
	- VM이 아닌 컨테이너에 특화된 IaaS
	- 컨테이너 형태로 앱을 배포하므로 추상화 수준도 높음
	- CaaS 애플리케이션으로 배포하려면 전달 파이프라인에 컨테이너 빌드 단계를 추가로 구성해야 함
- 서비스형 플랫폼(PaaS)
	- 서비스형 플랫폼은 세부적인 인프라를 근본적인 수준에서 한층 더 추상화함
	- JAR or WAR 등의 애플리케이션 바이너리를 PaaS API를 통해 직접 업로드하면 이미지를 빌드하고 프로비저닝까지 직접 수행
	- 업체별 클라우드 중립성을 일정 정도 유지하고 싶거나 사설 및 공용 클라우드 혼성 환경에서 전달 및 관리 워크플로를 비슷하게 일치하고 싶을 때 이러한 서비스를 이용
## 5.2. 자원 유형
- 인스턴스
	- 인스턴스는 마이크로서비스의 실행 복사본
	- AWS EC2, 클라우드 파운드리 플랫폼은 '인스턴스'라는 용어를 그대로 사용
	- 쿠버네티스는 인스턴스를 파드라 지칭
- 서버 그룹
	- 서버 그룹은 한 묶음으로 관리되는 인스턴스 집합을 가리킴
	- 서버 그룹에 속한 모든 인스턴스는 동일한 코드와 설정을 탑재한다고 가정 (애플리케이션이 불변하다고 간주)
	- AWS EC2에서 서버 그룹은 오토 스케일링 그룹
- 클러스터
	- 클러스터는 여러 리전에 걸친 서버 그룹 집합
	- 리전이 같아도 서버 그룹마다 마이크로서비스 버전이 다를 때도 있음
- 애플리케이션
	- 애플리케이션은 하나의특정 리소스가 아니라 논리적 비즈니스 기능을 의미
	- 실행 중인 모든 애플리케이션 인스턴스가 애플리케이션에 속함 (여러 리전, 클러스터에 걸쳐 존재함)
- 로드 밸런서
	- 로드 밸런서는 하나 이상의 그룹에 속한 인스턴스들에 개별 요청을 할당하는 컴포넌트
	- 로드밸런서 대부분은 특정 전략 또는 알고리즘에 따라 트래픽을 할당
- 방화벽
	- 방화벽은 서버 그룹의 수신 및 송신을 제어하는 규칙을 제공
	- AWS EC2에서 방화벽은 시큐리티 그룹이라 불림
## 5.3. 전달 파이프라인
- 플랫폼 중립성
	- 전달 솔루션이 반드시 모든 플랫폼을 지원할 필요는 없음
		- 쿠버네티스의 커스텀 리소스 정의처럼 특정 플랫폼에 명시적으로 고정되는 기술도 있음
	- 일반적으로 기업의 규모가 일정 수준을 넘어서면 플랫폼을 혼성으로 구성하는 경우가 매우 많음
- 자동 트리거
	- 파이프라인인 이벤트를 기반으로 구동
	- 특히 아티팩트 입력으로 발생하는 변경 사항은 자동으로 트리거를 발동시켜야 함
- 확장성
	- 우수한 파이프라인 솔루션은 각 파이프라인의 연산적 특성과 차이를 나타냄
	- 배포 스테이지는 새로운 리소스를 프로비저닝하기 위해 플랫폼 API 앤드포인트를 실행하는 단계
	- 스크립트 실행(그레이들 작업 등) 부분은 자원적 부담이 과중되는 구간
		- 리소스 사용률이 파이프라인의 성능에 영향을 주지 않도록 컨테이너 스케줄러 등의 전담 도구에 역할을 위임하는 것이 좋음
	- 파이프라인 서비스 인스턴스 하나만으로 수천 개의 배포 스테이지를 병렬로 진행할 수 있음
## 5.4. 클라우드용 패키지
### 5.4.1. IaaS 플랫폼 패키징
- IaaS 플랫폼에서 배포 불변 단위는 가상 머신 이미지 (AWS EC2에서는 이 이미지를 아마존 머신 이미지라고 부름)
- 인스턴스 프로비저닝, 시스템 의존성 설치, 스냅샷 생성 프로세스를 묶어 '베이킹'이라고 부름
### 5.4.2. 컨테이너 스케줄러용 패키징
- 쿠베네티스처럼 컨테이너 스케줄러를 지원하는 환경은 마이크로서비스 배포 준비 과정도 대체로 비슷
- 이러한 패키징 방식의 단점은 애플리케이션 컨테이너 이미지로 쓰일 기본 도커 이미지에 운영체제와 시스템 패키지 업데이트가 함께 담김
	- 기본 이미지를 변경하고 조직 전체에 전파하려면 애플리케이션 바이너리 자체를 다시 빌드해야 함
- 컨테이너 방식 워크로드에 베이크 단계를 추가하면 컨테이너 이미지 게시할 필요성이 사요라져 빌드 과정이 간소
## 5.5. delete + none 배포
- 이 배포의 기본 개념은 단순히 기존 배포를 삭제하고 새로운 배포를 진행하는 것
- 이러한 배포 방식은 단순이 이전 버전을 대체하는 작업을 뿐 불변 배포가 아님
- AWS EC2에서 아마존 머신 이미지를 변경하고 오토 스케일링 그룹을 재설할 때도 이 전략이 쓰임
## 5.6. 하이랜더
- 하이렌더는 가장 대중적인 무중단 전략 중 하나
- 신규 서비스 버전을 배포하면 이전 버전이 대체 (실행되고 있는 서비스는 항상 신규 버전)
- 하이렌더 전략은 다운타임이 없음
	- 신규 버전을 배포하며 동시에 로드 밸런서에 추가
- 롤백 작업에 걸리는 시간은 애플리케이션을 설치하고 프로세스를 초기화하는 데 걸리는 시간과 같음
## 5.7. 블루/그린 배포
- 블루/그린 배포 전략은 수행하려면 최소 두개의 마이크로서비스 복사본을 프로비저닝해야 함
- 두 서버 그룹은 각각 이전 버전과 새 버전을 실행하며 각 그룹의 활성화 여부는 상황에 따라 다름
## 5.8. 카나리 분석 자동화
- 블루/그린 배포는 보통 매우 저렴한 비용으로 상당한 안정성을 보장하는 기법 (대부분의 서비스는 이 정도 수준에 머물러도 좋음)
- 카나리 배포는 기존 버전과 신규 버전 서비스를 함께 실행하며 일부 사용자만 신규 버전에 노출시키는 것
- 카나리 전략이 모든 서비스에 적합하지는 않음
- 카나리 배포 적합성은 구버전과 신버전 서비스 수준 지표를 비교해 결정
	- SLI 중 한가지 이상에 심각한 저하가 나타날 경우 모든 트래픽이 안정적인 버전으로 라우팅되고 카나리가 중단
### 5.8.1. 케이엔타와 스피나커
- 케이엔타는 카나리 분석 자동화 서비스를 제공하는 독립형 오픈소스이며 파이프라인 스테이지 설정을 통해 스피나커와 밀접하게 통합
### 5.8.2. 전 마이크로서비스 대상 범용 카나리 지표
#### 레이턴시
- 측정 대상은 전체 요청 중 성공한 요청의 레이턴시로 제한
- 카나리 비교 분석하기 좋은 지표는 99분위 레이턴시 분포 통계
#### 에러 비중
- 유력 API 또는 전체 API의 에러 비중은 아주 중요한 비중
#### 힙 포화도
- 전체 소비량 대비 최대 힙, 할당 성능 지표를 이용하여 비교

