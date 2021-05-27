# 6. static 제대로 한번 써 보자

## static 잘 활용하기
- 자주 사용하고 절대 변하지 않는 변수는 final static으로 선언하자
- 설정 파일 정도로 static으로 관리하자
- 코드성 데이터는 DB에서 한 번만 읽자


## static과 메모리 릭
static으로 선언한 부분은 GC대상이 아니다. 만약 static으로 컬렉션을 선언하여 데이터를 쌓는다면 OOM이 발생 할수 있다.