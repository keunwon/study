# 8 날짜 산술

# 8.1 일, 월, 연도 가감하기
select hiredate - interval 5 day as hd_minus_5D,
       hiredate + interval 5 day as hd_plus_5D,
       hiredate - interval 5 month as hd_minus_5M,
       hiredate + interval 5 month as hd_plus_5M,
       hiredate - interval 5 year as hd_minus_5Y,
       hiredate + interval 5 year as hd_plus_5Y
from emp
where deptno = 10;

# 8.2 두 날짜 사이의 일수 알아내기
select datediff(allen_hd, ward_hd)
from (
    select hiredate as ward_hd
    from emp
    where ename = 'WARD'
         ) x,
    (
    select hiredate as allen_hd
    from emp
    where ename = 'ALLEN'
        ) y;

# 8.3 두 날짜 사이의 영업일수 알아내기

# 8.4 두 날짜 사이의 월 또는 년 수 알아내기
select min(hiredate) min_hd,
       max(hiredate) max_hd
from emp;

select year(max_hd) max_yr,
       year(min_hd) min_yr,
       month(max_hd) max_mon,
       month(min_hd) min_mon
from (
    select min(hiredate)  min_hd,
           max(hiredate) max_hd
    from emp
         ) x;

# 8.5 두 날짜 사이의 시, 분, 초 알아내기
select datediff(allen_hd, ward_hd) * 24 hr,
       datediff(allen_hd, ward_hd) * 24 * 60 min,
       datediff(allen_hd, ward_hd) * 24 * 60 * 60 sec
from (
    select max(case when ename = 'WARD' then hiredate end) ward_hd,
           max(case when ename = 'ALLEN' then hiredate end) allen_hd
    from emp
         ) x;

select max(case when ename = 'WARD' then hiredate end) ward_hd,
       max(case when ename = 'ALLEN' then hiredate end) allen_hd
from emp;

# 8.6 1년 중 평일 발생 횟수 계산하기

# 8.7 현재 레코드와 다음 레코드 간의 날짜 차이 알아내기
select x.ename, x.hiredate, x.next_hd,
       datediff(x.hiredate, x.next_hd) diff
from (
    select deptno, ename, hiredate,
           lead(hiredate) over (order by hiredate) next_hd
    from emp e
         ) x
where deptno = 10;

insert into emp (empno, ename, deptno, hiredate)
values (1, 'ant', 10, str_to_date('17-NOV-2006', '%d-%M-%Y'));

insert into emp (empno, ename, deptno, hiredate)
values (2, 'joe', 10, str_to_date('17-NOV-2006', '%d-%M-%Y'));

insert into emp (empno, ename, deptno, hiredate)
values (3, 'jim', 10, str_to_date('17-NOV-2006', '%d-%M-%Y'));

insert into emp (empno, ename, deptno, hiredate)
values (4, 'choi', 10, str_to_date('17-NOV-2006', '%d-%M-%Y'));

select ename, hiredate
from emp
where deptno = 10
order by 2;

select deptno, ename, hiredate, next_hd,
       next_hd - hiredate diff
from (
         select deptno,
                ename,
                hiredate,
                lead(hiredate) over (order by hiredate) next_hd
         from emp
         where deptno = 10
     ) x;
