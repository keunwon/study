# 11 고급 검색

# 11.1 결과셋을 페이지로 메기기
select x.sal, x.rn
from
(
    select row_number() over (order by sal) as rn,
           sal
    from emp
) x
where x.rn between 1 and 5;

# 11.2 테이블에서 n개 행 건너뛰기
select ename
from
(
    select row_number() over (order by ename) as rn,
           ename
    from emp
) x
where mod(x.rn, 2) = 1;

# 11.3 외부 조인을 사용할 때 OR 로직 통합하기
select e.ename, d.deptno, d.dname, d.loc
from dept d, emp e
where d.deptno = e.deptno
and (e.deptno = 10 or e.deptno = 20)
order by 2;

select e.ename, d.deptno, d.dname, d.loc
from dept d
left join
(
    select ename, deptno
    from emp
    where deptno in (10, 20)
) e
on (e.deptno = d.deptno)
order by 2;

# 11.4 역수 행 확인하기
create view V11 (test1, test2) as
select 20, 20 union all
select 50, 25 union all
select 20, 20 union all
select 60, 30 union all
select 70, 90 union all
select 80, 130 union all
select 90, 70 union all
select 100, 50 union all
select 110, 55 union all
select 120, 60 union all
select 130, 80 union all
select 140, 70;

select * from V11;

select v1.*
from V11 v1, V11 v2
where v1.test1 = v2.test2
and v1.test2 = v2.test1;

# 11.5 상위 n개 레코드 선택하기
select ename, sal, dr
from
(
    select ename, sal,
           dense_rank() over (order by sal desc) dr
    from emp
) x
where x.dr <= 5;

# 11.6 최댓값과 최솟값을 가진 레코드 찾기
select ename, sal,
       min(sal) over () min_sal,
       max(sal) over () max_sal
from emp;

# 11.7 이후 행 조사하기
select ename, sal, hiredate
from
(
    select ename, sal, hiredate,
           lead(sal) over (order by hiredate) next_sal
    from emp
) a
where sal < a.next_sal;

# 11.8 행 값 이동하기
select ename, sal,
       coalesce(lead(sal) over (order by sal), min(sal) over ()) forward,
       coalesce(lag(sal) over (order by sal), max(sal) over ()) rewind
from emp;

select ename, sal,
       lead(sal) over (order by sal) forward,
       lag(sal) over (order by sal) rewind
from emp;

select ename, sal,
       lead(sal, 3) over (order by sal) forward,
       lag(sal, 5) over (order by sal) rewind
from emp;

# 11.9 순위 결과
select dense_rank() over (order by sal) rnk, sal
from emp;

# 11.10 중복 방지하기
select job
from
(
    select job,
           row_number() over (partition by job order by job) rn
    from emp
) x
where rn = 1;

select distinct job
from emp;

select job
from emp
where job is not null
group by job;

select job,
       row_number() over (partition by job order by job) rn
from emp;

# 11.11 기사값 찾기
select deptno,
       ename,
       sal,
       hiredate,
       if(hiredate = max(hiredate) over (partition by deptno), sal, 0) as latest_sal
from emp;

select x.deptno,
       x.ename,
       x.sal,
       x.hiredate,
       max(x.latest_sal) over (partition by deptno) as latest_sal
from
(
    select deptno,
           ename,
           sal,
           hiredate,
           if(hiredate = max(hiredate) over (partition by deptno), sal, 0) as latest_sal
    from emp
) x
order by 1, 4 desc;
