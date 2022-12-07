# 3. 트래픽 관리

## 3.1. A/B 테스트
- 사용자 반응을 살펴보기 위해 버전이 2개 이상인 파일이나 애플리케이션으로 사용자를 분기
- 'split_clients' 모듈을 사용해 사용자 요청을 지정된 비율에 따라 서로 다른 업스트림풀로 전달
## 3.2. GeoIP 모듈과 데이터베이스 활용
- GeoIP 데이터베이스를 설치하고 엔진엑스의 관련 내장 변수를 활성화해 엔진액스가 로그, 요청 프록시, 요청 분기 등을 수행할 때 사용자 위치를 확인 함
- 'nginx-moudle-geoip' 모듈을 제공
- geoip_country 내장 변수
    - $geoip_country_code,$geoip_country_code3, $geoip_country_name 제공
- geoip_city 내장 변수
    - $geoip_city_country_code, $geoip_city_country_code3, $geoip_city_country_name
    - $geoip_city, $geoip_latitude, $geoip_longitude, $geoip_region, $geoip_region_name
> download url: https://mailfud.org/geoip-legacy/
## 3.3. 국가 단위 접근 차단
- map 지시자를 사용해 접근을 차단하거나 허용할 국가 코드를 변수에 할당
## 3.4. 실제 사용자 IP 찾기
- 'geoip_proxy' 지시자로 프록시 서버 IP 대역을 정의하고 'geoip_proxy_recursive' 지시자로 사용자의 원래 IP 주소를 확인 함
- X-Forwarded-For 헤더값을 순차적으로 탐색해 최종 사용자의 IP를 확인
- 'geoip_proxy' 지시자를 사용해 특정 IP 대역에서 들어오는 요청에 대해 X-Forwarded-For 헤더값을 참조하도록 할 수 있음
## 3.5. 연결 제한
- 연결에 대한 지표를 저장할 공유 메모리 영역을 만들고 'limit_conn' 지시자를 사용해 연결 수를 제한
## 3.6. 요청 빈도 제한
- 'rate-limiting' 모듈을 활용해 요청 빈도를 제한
## 3.7. 전송 대역폭 제한
- 엔진엑스가 제공하는 'limit_rate'와 'limit_rate_after' 지시자를 사용해 사용자에 대한 응답 대역폭을 제한
