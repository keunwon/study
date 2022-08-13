# 2 쿼리 결과 정렬

# 2.1 지정한 순서대로 쿼리 결과 반환하기
select ename, job, sal
from emp
where deptno = 10
order by sal desc;

select ename, job, sal
from emp
where deptno = 10
order by 3 desc;

# 2.2 다중 필드로 정렬하기
select empno, deptno, sal, ename, job
from emp
order by deptno, sal desc;

# 2.3 부분 문자열로 정렬하기
select ename, job
from emp
order by substr(job, length(job) -1);

# 2.4 혼합 영숫자 데이터 정렬하기
create view V
as
    select concat(ename, ' ', deptno) as data
    from emp;
select * from V;

# 2.5 정렬할 때 null 처리하기
select ename, sal, comm
from (
         select ename, sal, comm,
                IF(comm is null, 0, 1) as is_null
         from emp
         ) x
order by x.is_null desc, comm;

select x.ename, x.sal, x.comm
from (
         select ename, sal, comm,
                IF(comm is null, 0, 1) as is_null
         from emp
         ) x
order by x.is_null desc, comm desc;

select x.ename, x.sal, x.comm
from (
         select ename, sal, comm,
                IF(comm is null, 0, 1) as is_null
         from emp
         ) x
order by x.is_null, comm;

select x.ename, x.sal, x.comm
from (
         select ename, sal, comm,
                IF(comm is null, 0, 1) as is_null
         from emp
         ) x
order by x.is_null, x.comm desc;

# 2.6 데이터 종속 키 기준으로 정렬하기
select ename, sal, job, comm
from emp
order by IF(job = 'SALESMAN', comm, sal);

select ename, sal, job, comm,
       IF(job = 'SALESMAN', comm, sal) as ordered
from emp
order by 5;

