# 4 삽입, 갱신, 삭제

# 4.1 새로운 레코드 삽입하기
insert into dept (deptno, dname, loc)
values (50, 'PROGRAMMING', 'BALTIMORE');

insert into dept (deptno, dname, loc)
values (1, 'A', 'B'), (2, 'B', 'C');

select * from D;

# 4.2 기본값 삽입하기
create table D (id int default 0);

insert into D values(default);
insert into D (id) values (default);
insert into D values();

select * from D;

# 4.3 null로 기본값 오버라이딩하기
insert into D values (null);
select * from D;

# 4.4 한 테이블에서 다른 테이블로 행 복사하기
create table dept_east (
    deptno int(2),
    dname varchar(14),
    loc varchar(13)
);

insert into dept_east (deptno, dname, loc)
select deptno, dname, loc
from dept
where loc in ('NEW YORK', 'BOSTON');

select * from dept_east;

# 4.5 테이블 정의 복사하기
create table dept_2
as
select *
from dept
where 1 = 0;

# 4.6 한 번에 여러 테이블에 삽입하기
-- mysql에서는 지원하지 않음

# 4.7 특정 열에 대한 삽입 차단히기
create view new_emps as
select empno, ename, job
from emp;

select * from emp;

insert into new_emps (empno, ename, job)
values (1, 'Jonathan', 'Editor');

select * from new_emps;

# 4.8 테이블에서 레코드 수정하기
select  deptno, ename, sal
from emp
where deptno = 20
order by 1, 3;

update emp
set sal = sal * 1.10
where deptno = 20;

delete from emp where deptno = 20;

select deptno,
       ename,
       sal as orig_sal,
       sal * 10 as amt_to_add,
       sal * 1.10 new_sal
from emp
where deptno = 20
order by 1, 5;

# 4.9 일차하는 행이 있을 때 업데이트하기
create table emp_bonus2 (
    empno int(2),
    enmae varchar(10)
);
insert into emp_bonus2 values(7369, 'SMITH'), (7900, 'JAMES'), (7934, 'MILLER');

update emp
set sal = sal * 1.20
where empno in (select empno from emp_bonus2);

/**
  IN 연산자가 있는 서브쿼리와는 달리, 업데이트를 구동하는 항목은 특정 값이 아닌
  서브쿼리의 WHERE 절에의해 제어되므로 가독성이 높아짐
 */
update emp
set sal = sal * 1.20
where exists (
    select null
    from emp_bonus
    where emp.empno = emp_bonus.empno);

# 4.10 다른 테이블 값으로 업데이트하기
create table new_sal (
    deptno int(2),
    sal int(4),
    primary key (deptno)
);
insert into new_sal values (10, 4000);

select *
from new_sal;

select deptno, ename, sal, comm
from emp
order by 1;

update emp e, new_sal ns
set e.sal = ns.sal,
    e.comm = ns.sal / 2
where e.deptno = ns.deptno;

# 4.11 레코드 병합하기


# 4.12 테이블에서 모든 레코드 삭제하기
delete from emp;

# 4.13 특정 레코드 삭제하기
delete from emp where deptno = 10;

# 4.14 단일 레코드 삭제하기
delete from emp where empno = 7782;

# 4.15 참조 물결성 위반 삭제하기
select *
from emp e, dept d
where e.deptno = d.deptno;

delete from emp
where not exists(
    select * from dept
    where emp.deptno = dept.deptno
    );

# 4.16 중복 레코드 삭제하기
create table dupes(id int, name varchar(10));
insert into dupes values (1, 'NAPOLEON'), (2, 'DYNAMITE'), (3, 'DYNAMITE'), (4, 'SHE SELLS');
insert into dupes values (5, 'SEA SHELLS'), (6, 'SEA SHELLS'), (7, 'SEA SHELLS');
select * from dupes order by 1;

select min(id)
from dupes
group by name;

delete from dupes
where id not in
(
    select min(id)
    from (select id, name from dupes) tmp
    group by name
    );

# 4.17 다른 테이블에서 참조되 레코드 삭제하기
drop table if exists dept_accidents;
create table dept_accidents (
    deptno int,
    accident_name varchar(20)
);
insert into dept_accidents values(10, 'BROKEN FOOT');
insert into dept_accidents values(10, 'FLESH WOUND');
insert into dept_accidents values(20, 'FIRE');
insert into dept_accidents values(20, 'FIRE');
insert into dept_accidents values(20, 'FLOOD');
insert into dept_accidents values(30, 'BRUISED GLUTE');

select * from dept_accidents;

delete from emp
where deptno in (
    select deptno
    from dept_accidents
    group by deptno
    having count(*) >= 3
    );

select * from emp where deptno = 20;
