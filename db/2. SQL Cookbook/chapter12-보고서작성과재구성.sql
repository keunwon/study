# 12 보고서 작성과 재구성

# 12.1 결과셋을 하나의 행으로 피벗하기
select sum(if(deptno = 10, 1, 0)) as deptno_10,
       sum(if(deptno = 20, 1, 0)) as deptno_20,
       sum(if(deptno = 30, 1, 0)) as deptno_30
from emp
order by 1;

select deptno,
       if(deptno = 10, 1, 0) as deptno_10,
       if(deptno = 20, 1, 0) as deptno_20,
       if(deptno = 30, 1, 0) as deptno_30
from emp
order by 1;

select deptno,
       sum(if(deptno = 10, 1, 0)) as deptno_10,
       sum(if(deptno = 20, 1, 0)) as deptno_20,
       sum(if(deptno = 30, 1, 0)) as deptno_30
from emp
group by deptno;

select max(if(deptno = 10, empcount, null)) as deptno_10,
       max(if(deptno = 20, empcount, null)) as deptno_20,
       max(if(deptno = 30, empcount, null)) as deptno_30
from
(
    select deptno, count(*) as empcount
    from emp
    group by deptno
) x;

# 12.2 결과셋을 여러 행으로 피벗하기
select max(if(x.job = 'CLERK', x.ename, null)) as clerks,
       max(if(x.job = 'ANALYST', x.ename, null)) as analysts,
       max(if(x.job = 'MANAGER', x.ename, null)) as mgrs,
       max(if(x.job = 'PRESIDENT', x.ename, null)) as prez,
       max(if(x.job = 'SALESMAN', x.ename, null)) as sales
from
(
    select job,
           ename,
           row_number() over (partition by job order by ename) rn
    from emp
) x
group by x.rn;

select deptno as dno,
       job,
       max(if(deptno = 10, ename, null)) as d10,
       max(if(deptno = 20, ename, null)) as d20,
       max(if(deptno = 30, ename, null)) as d30,
       max(if(deptno = 'CLERK', ename, null)) as clerks,
       max(if(deptno = 'ANALYST', ename, null)) as anals,
       max(if(deptno = 'MANAGER', ename, null)) as mgrs,
       max(if(deptno = 'PRESIDENT', ename, null)) as prez,
       max(if(deptno = 'SALESMAN', ename, null)) as sales
from
(
    select deptno,
           job,
           ename,
           row_number() over (partition by job order by ename) rn_job,
           row_number() over (partition by deptno order by ename) rn_deptno
    from emp
) x
group by deptno, job, rn_deptno, rn_job
order by 1;

# 12.3 결과셋 역피벗하기
create view emp_cnts as
(
    select sum(if(deptno = 10, 1, 0)) as deptno_10,
           sum(if(deptno = 20, 1, 0)) as deptno_20,
           sum(if(deptno = 30, 1, 0)) as deptno_30
    from emp
);

select dept.deptno,
       case dept.deptno
           when 10 then emp_cnts.deptno_10
           when 20 then emp_cnts.deptno_20
           when 30 then emp_cnts.deptno_30
        end as counts_by_dept
from emp_cnts cross join
(select deptno from dept where deptno <= 30) dept;

select dept.deptno,
       emp_cnts.deptno_10,
       emp_cnts.deptno_20,
       emp_cnts.deptno_30
from
(
    select sum(if(deptno = 10, 1, 0)) as deptno_10,
           sum(if(deptno = 20, 1, 0)) as deptno_20,
           sum(if(deptno = 30, 1, 0)) as deptno_30
    from emp
) emp_cnts,
(select deptno from dept where deptno <= 30) dept;

select dept.deptno,
       case dept.deptno
           when 10 then emp_cnts.deptno_10
           when 20 then emp_cnts.deptno_20
           when 30 then emp_cnts.deptno_30
        end
from emp_cnts
cross join (select deptno from dept where deptno <= 30) dept;

# 12.4 결과셋을 한 열로 역피벗하기
with recursive four_rows (id) as
(
    select 1
    union all
    select id + 1
    from four_rows
    where id < 4
),
x_tab (ename, job, sal, rn) as
(
    select e.ename, e.job, e.sal,
           row_number() over (partition by e.empno order by e.empno)
    from emp e
    join four_rows on 1 = 1
)

select case x.rn
            when 1 then ename
            when 2 then job
            when 3 then cast(sal as char(4))
        end
from x_tab x;

# 12.5 결과셋에서 반복값 숨기기
select if(lag(deptno) over (order by deptno) = deptno, null, deptno) as DEPTNO,
       ename
from emp;

# 12.6 행 간 계산하는 결과셋 피벗하기
select deptno, sum(sal) as sal
from emp
group by deptno;

select sum(case when deptno = 10 then sal end) as d10_sal,
       sum(case when deptno = 20 then sal end) as d20_sal,
       sum(case when deptno = 30 then sal end) as d30_sal
from emp;

# 12.7 고정 크기의 데이터 버킷 생성하기
select row_number() over (order by empno) rn,
       row_number() over (order by empno) / 5.0 as division,
       ceil(row_number() over (order by empno) / 5.0) as grp,
       empno,
       ename
from emp;

# 12.8 사전 정의된 수의 버킷 생성하기
select ntile(5) over (order by empno) as grp,
       empno,
       ename
from emp;

# 12.9 수평 히스토그램 생성하기
select deptno,
       lpad('*', count(*), '*') as cnt,
       count(*) as count
from emp
group by deptno;

# 12.10 수직 히스토그램 생성하기
select max(x.deptno_10) d10,
       max(x.deptno_20) d20,
       max(x.deptno_30) d30
from
(
    select row_number() over (partition by deptno order by empno) rn,
           if(deptno = 10, '*', null) as deptno_10,
           if(deptno = 20, '*', null) as deptno_20,
           if(deptno = 30, '*', null) as deptno_30
    from emp
) x
group by rn
order by 1 desc, 2 desc, 3 desc;

# 12.11 비 GROUP BY 열 반환하기
drop table if exists empgroup;
create table empgroup (
    deptno int(2),
    ename varchar(10),
    job varchar(9),
    sal int
);
insert into empgroup values (10, 'MILLER', 'CLERK', 1300);
insert into empgroup values (10, 'CLARK', 'MANAGER', 2450);
insert into empgroup values (10, 'KING', 'PRESIDENT', 5000);
insert into empgroup values (20, 'SCOTT', 'ANALYST', 3000);
insert into empgroup values (20, 'FORD', 'ANALYST', 3000);
insert into empgroup values (20, 'SMITH', 'CLERK', 800);
insert into empgroup values (20, 'JONES', 'MANGER', 2975);
insert into empgroup values (30, 'JAMES', 'CLERK', 950);
insert into empgroup values (30, 'MARTIN', 'SALESMAN', 1250);
insert into empgroup values (30, 'WARD', 'SALESMAN', 1250);
insert into empgroup values (30, 'ALLEN', 'SALESMAN', 1600);
insert into empgroup values (30, 'BLAKE', 'MANAGER', 2850);

select deptno, ename, job, sal,
       case
           when sal = emp_sals.max_by_dept
               then 'TOP SAL IN DEPT'
           when sal = emp_sals.min_by_dept
                then 'LOW SAL IN DEPT'
        end as dept_status,
       case
            when sal = emp_sals.max_by_job
                then 'TOP SAL IN JOB'
            when sal = emp_sals.min_by_job
                then 'LOW SAL  IN JOB'
        end as job_status
from
(
    select deptno, ename, job, sal,
           max(sal) over (partition by deptno) as max_by_dept,
           max(sal) over (partition by job) as max_by_job,
           min(sal) over (partition by deptno) as min_by_dept,
           min(sal) over (partition by job) as min_by_job
    from emp
) emp_sals
where sal in (max_by_dept, max_by_job, min_by_dept, min_by_job);

# 12.12 단순 소계 계산하기
select coalesce(job, 'TOTAL') as job,
       sum(sal) as sal
from emp
group by job with rollup;

select coalesce(job, 'TOTAL'), sum(sal) as sal
from emp
group by job with rollup ;

# 12.13 가능한 모든 식 조합의 소계 계산하기
select deptno, job,
       'TOTAL BY DEPT AND JOB' as category,
       sum(sal) as sal
from emp
group by deptno, job
union all
select null, job, 'TOTAL BY JOB', sum(sal)
from emp
group by job
union all
select deptno, null, 'TOTAL BY DEPT', sum(sal)
from emp
group by deptno
union all
select null, null, 'GRAND TOTAL FOR TABLE', sum(sal)
from emp;

# 12.15 Case 표현식으로 행 플래그 지정하기
select ename,
       if(job = 'CLERK', 1, 0) as is_clerk,
       if(job = 'SALESMAN', 1, 0) as is_sales,
       if(job = 'MANAGER', 1, 0) as is_mgr,
       if(job = 'ANALYST', 1, 0) as is_analyst,
       if(job = 'PRESIDENT', 1, 0) as is_prez
from emp;

# 12.16 희소행렬 만들기
select max(case deptno when 10 then ename end) as d10,
       max(case deptno when 20 then ename end) as d20,
       max(case deptno when 30 then ename end) as d30,
       max(case job when 'CLERK' then ename end) as clerks,
       max(case job when 'MANAGER' then ename end) as mgrs,
       max(case job when 'PRESIDENT' then ename end) as prez,
       max(case job when 'ANALYST' then ename end) as anals,
       max(case job when 'SALESMAN' then ename end) sales
from
(
    select deptno,
           job,
           ename,
           row_number() over (partition by deptno order by empno) rn
    from emp
) x
group by rn;

# 12.17 시간 단위로 행 그룹화하기
drop table if exists trx_log;
create table trx_log (
    trx_id int(2),
    trx_date datetime,
    trx_cnt int(2)
);
insert into trx_log values (1, str_to_date('28-JUL-2020 19:03:07', '%d-%M-%Y %H:%i:%s'), 44);
insert into trx_log values (2, str_to_date('28-JUL-2020 19:03:08', '%d-%M-%Y %H:%i:%s'), 18);
insert into trx_log values (3, str_to_date('28-JUL-2020 19:03:09', '%d-%M-%Y %H:%i:%s'), 23);
insert into trx_log values (4, str_to_date('28-JUL-2020 19:03:10', '%d-%M-%Y %H:%i:%s'), 29);
insert into trx_log values (5, str_to_date('28-JUL-2020 19:03:11', '%d-%M-%Y %H:%i:%s'), 27);
insert into trx_log values (6, str_to_date('28-JUL-2020 19:03:12', '%d-%M-%Y %H:%i:%s'), 45);
insert into trx_log values (7, str_to_date('28-JUL-2020 19:03:13', '%d-%M-%Y %H:%i:%s'), 45);
insert into trx_log values (8, str_to_date('28-JUL-2020 19:03:14', '%d-%M-%Y %H:%i:%s'), 32);
insert into trx_log values (9, str_to_date('28-JUL-2020 19:03:15', '%d-%M-%Y %H:%i:%s'), 41);
insert into trx_log values (10, str_to_date('28-JUL-2020 19:03:16', '%d-%M-%Y %H:%i:%s'), 15);
insert into trx_log values (11, str_to_date('28-JUL-2020 19:03:17', '%d-%M-%Y %H:%i:%s'), 24);
insert into trx_log values (12, str_to_date('28-JUL-2020 19:03:18', '%d-%M-%Y %H:%i:%s'), 47);
insert into trx_log values (13, str_to_date('28-JUL-2020 19:03:19', '%d-%M-%Y %H:%i:%s'), 37);
insert into trx_log values (14, str_to_date('28-JUL-2020 19:03:20', '%d-%M-%Y %H:%i:%s'), 48);
insert into trx_log values (15, str_to_date('28-JUL-2020 19:03:21', '%d-%M-%Y %H:%i:%s'), 46);
insert into trx_log values (16, str_to_date('28-JUL-2020 19:03:22', '%d-%M-%Y %H:%i:%s'), 44);
insert into trx_log values (17, str_to_date('28-JUL-2020 19:03:23', '%d-%M-%Y %H:%i:%s'), 36);
insert into trx_log values (18, str_to_date('28-JUL-2020 19:03:24', '%d-%M-%Y %H:%i:%s'), 41);
insert into trx_log values (19, str_to_date('28-JUL-2020 19:03:25', '%d-%M-%Y %H:%i:%s'), 33);
insert into trx_log values (20, str_to_date('28-JUL-2020 19:03:26', '%d-%M-%Y %H:%i:%s'), 19);

select trx_id,
       trx_date,
       trx_cnt,
       trx_id / 5.0 as val,
       ceil(trx_id / 5.0) as grp
from trx_log;

select ceil(trx_id / 5.0) as grp,
       min(trx_date) as trx_start,
       max(trx_date) as trx_end,
       sum(trx_cnt) as total
from trx_log
group by ceil(trx_id / 5.0);

# 12.18 여러 그룹/파티션 집계를 동시 수행하기
select ename,
       deptno,
       count(*) over (partition by deptno) deptno_cnt,
       job,
       count(*) over (partition by job) job_cnt,
       count(*) over () total
from emp;

select deptno, count(*)
from emp
group by deptno;

# 12.19 값의 이동 범위에 대한 집계 수행하기
select hiredate,
       sal,
       sum(sal) over (order by hiredate range interval 90 day preceding) spending_pattern
from emp e;

select distinct dense_rank() over (order by e.hiredate) win,
                e.hiredate current_hiredate,
                d.hiredate hiredate_within_90_days,
                d.sal sals_used_for_sum
from emp e,
     emp d
where d.hiredate between e.hiredate - 90 and e.hiredate;

select x.current_hiredate,
       sum(x.sals_used_for_sum) spending_pattern
from
(
    select distinct dense_rank() over (order by e.hiredate) win,
                    e.hiredate                              current_hiredate,
                    d.hiredate                              hiredate_within_90_days,
                    d.sal                                   sals_used_for_sum
    from emp e,
         emp d
    where d.hiredate between e.hiredate - 90 and e.hiredate
) x
group by x.current_hiredate;

select e.hiredate,
       e.sal,
       sum(d.sal) as spending_pattern
from emp e, emp d
where d.hiredate between e.hiredate - 90 and e.hiredate
group by e.hiredate, e.sal
order by 1;
