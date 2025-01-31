create database if not exists sql_cookbook;

drop table if exists dept;
drop table if exists emp;
drop table if exists new_dept;
drop table if exists emp_bonus;
drop table if exists t10;

create table dept(
     deptno int(2),
     dname varchar(14),
     loc varchar(13),
     constraint pk_dept primary key (deptno)
);

create table emp(
    empno int(4),
    ename varchar(10),
    job varchar(9),
    mgr int(4),
    hiredate date,
    sal int(4),
    comm int(4),
    deptno int(2),
    primary key (empno),
    foreign key (deptno) references dept (deptno)
);

insert into dept (deptno, dname, loc)
values(10, 'ACCOUNTING', 'NEW YORK');

insert into dept
values(20, 'RESEARCH', 'DALLAS');

insert into dept
values(30, 'SALES', 'CHICAGO');

insert into dept
values(40, 'OPERATIONS', 'BOSTON');

insert into emp
values(
          7839, 'KING', 'PRESIDENT', null,
          str_to_date('17-11-1981','%d-%m-%Y'),
          5000, null, 10
      );

insert into emp
values(
          7698, 'BLAKE', 'MANAGER', 7839,
          str_to_date('1-5-1981','%d-%m-%Y'),
          2850, null, 30
      );


insert into emp
values(
          7782, 'CLARK', 'MANAGER', 7839,
          str_to_date('9-6-1981','%d-%m-%Y'),
          2450, null, 10
      );

insert into emp
values(
          7566, 'JONES', 'MANAGER', 7839,
          str_to_date('2-4-1981','%d-%m-%Y'),
          2975, null, 20
      );


select *
from emp;

insert into emp
values(
          7788, 'SCOTT', 'ANALYST', 7566,
          date_sub(str_to_date('13-JUL-87','%d-%M-%Y'), interval 85 day),
          3000, null, 20
      );

insert into emp
values(
          7902, 'FORD', 'ANALYST', 7566,
          str_to_date('3-12-1981','%d-%m-%Y'),
          3000, null, 20
      );


insert into emp
values(
          7369, 'SMITH', 'CLERK', 7902,
          str_to_date('17-12-1980','%d-%m-%Y'),
          800, null, 20
      );

insert into emp
values(
          7499, 'ALLEN', 'SALESMAN', 7698,
          str_to_date('20-2-1981','%d-%m-%Y'),
          1600, 300, 30
      );

insert into emp
values(
          7521, 'WARD', 'SALESMAN', 7698,
          str_to_date('22-2-1981','%d-%m-%Y'),
          1250, 500, 30
      );

insert into emp
values(
          7654, 'MARTIN', 'SALESMAN', 7698,
          str_to_date('28-9-1981','%d-%m-%Y'),
          1250, 1400, 30
      );

insert into emp
values(
          7844, 'TURNER', 'SALESMAN', 7698,
          str_to_date('8-9-1981','%d-%m-%Y'),
          1500, 0, 30
      );

insert into emp
values(
          7876, 'ADAMS', 'CLERK', 7788,
          date_sub(str_to_date('13-JUL-87', '%d-%M-%Y'), interval 51 day),
          1100, null, 20
      );

insert into emp
values(
          7900, 'JAMES', 'CLERK', 7698,
          str_to_date('3-12-1981','%d-%m-%Y'),
          950, null, 30
      );

select *
from emp;

insert into emp
values(
          7934, 'MILLER', 'CLERK', 7782,
          str_to_date('23-1-1982','%d-%m-%Y'),
          1300, null, 10
      );

# 예제 진행 중 추가되는 테이블 및 데이터
-- new_dept
create table new_dept(deptno int);
insert into new_dept values (10);
insert into new_dept values (50);
insert into new_dept values (null);

-- emp_bonus
create table emp_bonus (
    empno int(4),
    received varchar(11),
    type char(1)
);
delete from emp_bonus;

insert emp_bonus values(7369, '14-MAR-2005', 1);
insert emp_bonus values(7900, '14-MAR-2005', 2);
insert emp_bonus values(7788, '14-MAR-2005', 3);

insert emp_bonus values(7934, '17-MAR-2005', 1);
insert emp_bonus values(7934, '17-MAR-2005', 2);
insert emp_bonus values(7839, '17-MAR-2005', 3);
insert emp_bonus values(7782, '17-MAR-2005', 1);

insert emp_bonus values(7934, '17-MAR-2005', 1);
insert emp_bonus values(7934, '17-MAR-2005', 2);

-- 6. 문자열 작업 테이블(데이터) 추가
create table t10 (
     id int(2),
     primary key (id)
);
insert into t10 values (1), (2), (3), (4), (5), (6), (7), (8), (9), (10);
