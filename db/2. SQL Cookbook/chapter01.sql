# 1.1 테이블의 모든 행과 열 검색하기
select * from emp;
/*
 프로그램 코드를 작성할 떄는 각 열ㅇ르 개별 지정하는 것이 좋음
 성능은 동일하지만 어떤 열을 반환하는지 명확하게 알 수 있음
 */
select e.empno, e.ename, e.job, e.mgr, e.hiredate, e.comm, e.deptno from emp as e;

# 1.2 테이블에서 행의 하위 집합 검색하기
select *
from emp
where deptno = 10;

# 1.3 여러 조건을 충족하는 행 찾기
select *
from emp
where deptno = 10
or comm is not null
or sal <= 2000 and deptno = 20;

# 1.4 테이블에서 열의 하위 집합 검색하기
select ename, deptno, sal
from emp;

# 1.5 열에 의미 있는 이름 정하기
-- 좋은 별칭을 만들면 쿼리오 ㅏ그 결과를 다른 사람이 이해하는데 큰 도움이 될 수 있음
select sal as salary, comm as commission
from emp;

# 1.6 WHERE 절에서 별칭이 지정된 열 참조하기
/*
 쿼리를 인라인 뷰로 감싸서 별칭이 지정된 열을 참조할 수 있음
 SELECT 절보다 WHERE 절이 먼저 평가, WHERE 절보다 FROM 절이 먼저 평가
 */
select *
from (
     select sal as salary, comm as commission
    from emp
         ) x
where x.salary < 5000;

# 1.7 열 값 이어 붙이기
/*
 DB2, Oracle, PostgreSQL: '||'을 연결 연산자로 사용
 Mysql: concat 함수 사용
 */
select concat(ename, ' WORKS AS A', job) as msg
from emp
where deptno = 10;

# 1.8 SELECT 문에서 조건식 사용하기
select ename, sal,
        case when sal <= 2000 then 'UNDERPAID'
             when sal >= 4000 then 'OVERPAID'
             else 'OK'
        end as status
from emp;

# 1.9 반환되는 행 수 제한하기
/**
  Mysql, PostgreSQL: limit
  Oracle: rownum
 */
select *
from emp
limit 5;

# 1.10 테이블에서 n개의 무작위 레코드 반환하기
select ename, job
from emp
order by rand() limit 5;

# 1.11 null 값 찾기
select *
from emp
where comm is null;

# 1.12 null을 실젯값으로 변환하기
select coalesce(comm, 0)
from emp;

select case
        when comm is not null then comm
        else 0
       end
from emp;

# 1.13 패턴 검색하기
select ename, job
from emp
where deptno in (10, 20);

select ename, job
from emp
where deptno in (10, 20)
and (ename like '%I%' or job like '%ER')

