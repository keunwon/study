# 3 다중 테이블 작업

# 3.1 행 집합을 다른 행 위에 추가하기
/*
 union all 사용하여 여러 테이블의 행을 결합 (union all: 중복 허용, union: 중복을 제거)
 모든 집합 연산과 마찬가지로 모든 SELECT 목록의 항목은 숫자와 데이터 유형이 일치해야 함
 */
select deptno
from emp
union
select deptno
from dept;

# 3.2 연관된 여러 행 결합하기
-- 동등 조인
select e.ename, d.loc
from emp e, dept d
where e.deptno = d.deptno
and e.deptno = 10;

select e.ename, d.loc,
       e.deptno as emp_deptno,
       d.deptno as dept_deptno
from emp e, dept d
where e.deptno = 10;

select e.ename, d.loc
from emp e inner join dept d
on (e.deptno = d.deptno)
where e.deptno = 10;

# 3.3 두 테이블의 공통 행 찾기
create view VV
as
select ename, job, sal
from emp
where job = 'CLERK';

select * from VV;

select e.empno, e.ename, e.job, e.sal, e.deptno
from emp e, VV v
where e.ename = v.ename
and e.job = v.job
and e.sal = v.sal;

select e.empno, e.ename, e.sal, e.deptno
from emp e join VV v
on (e.ename = v.ename
    and e.job = v.job
    and e.sal = v.sal
    );

# 3.4 한 테이블에서 다른 테이블에 졵내하지 않는 값 검색하기
select deptno
from dept
where deptno not in (select deptno from emp);

select distinct deptno
from dept
where deptno not in (select deptno from emp);

-- IN, NOT IN 연산은 본질적으로 OR 연산이며, null 값이 논리적 OR 평가에 의해 처리되는 방식 때문에 다른 결과가 도출
select *
from dept
where deptno not in (select deptno from new_dept);

select deptno
from dept
where deptno in (10, 50, null);

select deptno
from dept
where (deptno = 10 or deptno = 50 or deptno = null);

select deptno
from dept
where deptno not in (10, 50, null);

select deptno
from dept
where not (deptno = 10 or deptno = 50 or deptno = null);

select  d.deptno
from dept d
where not exists(
    select 1
    from new_dept nd
    where d.deptno = nd.deptno
    );

# 3.5 다른 텡블 행과 일치하지 않는 행 검색하기
select d.*
from dept d left outer join emp e
on (d.deptno = e.deptno)
where e.deptno is null;

select e.ename, e.deptno as emp_deptno, d.*
from dept d left join emp e
on (d.deptno = e.deptno);

# 3.6 다른 조인을 방해하지 않고 쿼리에 조인 추가하기
select * from emp_bonus;

select e.ename, d.loc, eb.received
from emp e join dept d
on (e.deptno = d.deptno)
left join emp_bonus eb
on(e.empno = eb.empno)
order by 2;

select e.ename, d.loc,
       (select eb.received from emp_bonus eb
           where eb.empno = e.empno) as received
from emp e, dept d
where e.deptno = d.deptno
order by 2;

# 3.7 두 테이블에 같은 데이터가 있는지 확인하기
create view VVV
as
select * from emp where deptno != 10
union all
select * from emp where ename = 'WARD';

select count(*)
from emp
union
select count(*)
from dept;

# 3.8 데카르트 곱 식별 및 방지하기
select e.ename, d.loc
from emp e, dept d
where e.deptno = 10
and d.deptno = e.deptno;

# 3.9 집계를 사용할 때 조인 수행하기
select x.deptno,
       sum(x.sal) as total_sal,
       sum(x.bonus) as total_bonus
from (
     select e.empno,
            e.ename,
            e.sal,
            e.deptno,
            e.sal * case when eb.type = 1 then .1
                         when eb.type = 2 then .2
                         else .3
                    end as bonus
    from emp e, emp_bonus eb
    where e.empno = eb.empno
    and e.deptno = 10
         ) x
group by deptno;

select sum(sal)
from emp
where deptno = 10;

select deptno,
       sum(distinct sal) as total_sal,
       sum(bonus) as total_bonus
from (
        select e.empno,
            e.ename,
            e.sal,
            e.deptno,
            e.sal * case when eb.type = 1 then .1
                         when eb.type = 2 then .2
                         else .3
                end as bonus
        from emp e, emp_bonus eb
        where e.empno = eb.empno
        and e.deptno = 10
         ) x
group by deptno;

select d.deptno,
       d.total_sal,
       sum(e.sal * case when eb.type = 1 then .1
                        when eb.type = 2 then .2
                        else .3
                    end) as total_bonus
from emp e,
     emp_bonus eb,
     (
        select deptno, sum(sal) as total_sal
        from  emp
        #where deptno = 10
        group by deptno
         ) d
where e.deptno = d.deptno
and e.empno = eb.empno
group by d.deptno, d.total_sal;

# 3.10 집계 시 외부 조인 수행하기
select deptno,
       sum(sal) as total_sal,
       sum(bonus) as total_bonus
from (
     select e.empno,
            e.ename,
            e.sal,
            e.deptno,
            e.sal * case when eb.type = 1 then .1
                         when eb.type = 2 then .2
                         else .3
                end as bonus
     from emp e, emp_bonus eb
     where e.empno = eb.empno
     and e.deptno = 10
         ) x
group by deptno;

select d.deptno,
       d.total_sal,
       sum(e.sal * case when eb.type = 1 then .1
                        when eb.type = 2 then .2
                        else .3
                    end) as total_bonus
from emp e,
     emp_bonus eb,
     (
     select deptno, sum(sal) as total_sal
     from emp e
     where deptno = 10
     group by deptno) d
where e.deptno = d.deptno
and e.empno = eb.empno
group by d.deptno, d.total_sal;

# 3.11 여러 테이블에서 누락된 데이터 반환하기
select d.deptno, d.dname, e.ename
from dept d left outer join emp e
on (d.deptno = e.deptno);

select d.deptno, d.dname, e.ename
from dept d right outer join emp e
on (d.deptno = e.deptno);

select d.deptno, d.dname, e.ename
from dept d right outer join emp e
on (d.deptno = e.deptno)
union
select d.deptno, d.dname, e.ename
from dept d left outer join emp e
on (d.deptno = e.deptno);

# 3.12 연산 및 비교에서 null 사용하기
select ename, comm, coalesce(comm, 0)
from emp
where coalesce(comm, 0) < (select comm from emp where ename = 'WARD');
