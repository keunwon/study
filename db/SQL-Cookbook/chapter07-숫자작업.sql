# 7 숫자 작업

# 7.1 편균 계산하기
select avg(sal) as avg_sal
from emp;

select deptno, avg(sal) as avg_sal
from emp
group by deptno;

create table t2(sal int);
insert into t2 values (10);
insert into t2 values (20);
insert into t2 values (null);

select avg(sal) from t2;
select avg(coalesce(sal, 0)) from t2;
select distinct 30/3 from t2;

# 7.2 열에서 최댓값, 최솟값 찾기
select min(sal) as min_sal,
       max(sal) as max_sal
from emp;

select deptno, min(sal) as min_sal, max(sal) as max_sal
from emp
group by deptno;

select deptno, comm
from emp
where deptno in (10, 30)
order by 1;

select min(comm), max(comm)
from emp;

select deptno, min(comm), max(comm)
from emp
group by deptno;

# 7.3 열의 값 집계하기
select sum(sal)
from emp;

select deptno, sum(sal) as total_for_dept
from emp
group by deptno;

select deptno, comm
from emp
where deptno in (10, 30)
order by 1;

select deptno, sum(comm)
from emp
where deptno in (10, 30)
group by deptno;

# 7.4 테이블의 행 수 계산하기
select count(*)
from emp;

select deptno, count(*)
from emp
group by deptno;

select deptno, count(*), count(comm), count('hello')
from emp
group by deptno;

# 7.5 열의 값 세어보기
select count(comm)
from emp;

# 7.6 누계 생성하기
select ename, sal,
       sum(sal) over (order by sal, empno) as running_total
from emp;

select ename, sal,
       sum(sal) over (order by sal, empno) as running_total1,
       sum(sal) over (order by sal) as running_total2
from emp;

# 7.7 누적곱 생성하기
select empno, ename, sal,
       exp(sum(ln(sal)) over (order by sal, empno)) as running_total
from emp
where deptno = 10;

# 7.9 최빈값 계산하기
select sal
from (
    select sal,
           dense_rank() over (order by cnt desc) as rnk
    from (
        select sal, count(*) as cnt
        from emp
        where deptno = 20
        group by sal
    ) x
) y
where rnk = 1;

# 7.11 총계에서의 백분율 알아내기
select (
    sum(case when deptno = 10 then sal end) / sum(sal)
    ) * 100 as pct
from emp;

select sum(case when deptno = 10 then sal end) as d10,
       sum(sal)
from emp;

select (
    cast(sum(case when deptno = 10 then sal end) as decimal) / sum(sal)
    ) * 100  as pct
from emp;

# 7.12 null 허용 열 집계하기
select avg(coalesce(comm, 0)) as avg_comm
from emp
where deptno = 30;

# 7.13 최댓값과 최솟값을 배제한 평균 계산하기
select avg(sal)
from emp
where sal not in (
    (select min(sal) from emp),
    (select max(sal) from emp)
);

select (sum(sal) - min(sal) - max(sal)) / (count(*) - 2)
from emp;

# 7.15 누계에서 값 변경하기
create view V7 (id, amt, trx)
as
select 1, 100, 'PR' union all
select 2, 100, 'PR' union all
select 3, 50, 'PY' union all
select 4, 100, 'PR' union all
select 5, 200, 'PY' union all
select 6, 50, 'PY';

select * from V7;

select if(trx = 'PY', 'PAYMENT', 'PURCHASE') trx_type,
       amt,
       sum(
               IF(trx = 'PY', -amt, amt)
        ) over (order by id, amt) as balance
from V7;

select if(trx = 'PY', 'PAYMENT', 'PURCHASE') trx_type,
       if (trx = 'PY', -amt, amt)
from V7;

# 7.16 중위절대편차로 특잇값 찾기
with rank_tab (sal, rank_sal) as
(
    select sal, cume_dist() over (order by sal)
    from emp
),
inter as
(
    select sal, rank_sal
    from rank_tab
    where rank_sal >= 0.5
    union
    select sal, rank_sal
    from rank_tab
    where rank_sal <= 0.5
),
medianSal (medianSal) as
(
    select (max(sal) + min(sal)) / 2
    from inter
),
deviationSal (Sal, deviationSal) as
(
    select Sal, abs(sal - medianSal)
    from emp join medianSal
    on 1 = 1
),
distDevSal (sal, deviationSal, distDeviationSal) as
(
    select sal, deviationSal, cume_dist() over (order by deviationSal)
    from deviationSal
),
DevInter (DevInter, sal) as
(
    select min(deviationSal), sal
    from distDevSal
    where distDeviationSal >= 0.5
    union
    select max(deviationSal), sal
    from distDevSal
    where distDeviationSal <= 0.5
),
MAD (MedianAbsoluteDeviance) as
(
    select abs(emp.sal - (min(DevInter) + max(DevInter)) / 2)
    from emp join DevInter on 1 = 1
)

select emp.sal, MedianAbsoluteDeviance,
       (emp.sal - deviationSal) / MedianAbsoluteDeviance
from (emp join MAD on 1 = 1)
join deviationSal on emp.sal = deviationSal.sal;

