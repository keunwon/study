# 10 범위 관련 작업하기

# 10.1 연속 값의 범위 찾기
create view V10 (proj_id, proj_start, proj_end) as
select 1, str_to_date('01-JAN-2020', '%d-%M-%Y'), str_to_date('02-JAN-2020', '%d-%M-%Y') union all
select 2, str_to_date('02-JAN-2020', '%d-%M-%Y'), str_to_date('03-JAN-2020', '%d-%M-%Y') union all
select 3, str_to_date('03-JAN-2020', '%d-%M-%Y'), str_to_date('04-JAN-2020', '%d-%M-%Y') union all
select 4, str_to_date('04-JAN-2020', '%d-%M-%Y'), str_to_date('05-JAN-2020', '%d-%M-%Y') union all
select 5, str_to_date('06-JAN-2020', '%d-%M-%Y'), str_to_date('07-JAN-2020', '%d-%M-%Y') union all
select 6, str_to_date('16-JAN-2020', '%d-%M-%Y'), str_to_date('17-JAN-2020', '%d-%M-%Y') union all
select 7, str_to_date('17-JAN-2020', '%d-%M-%Y'), str_to_date('18-JAN-2020', '%d-%M-%Y') union all
select 8, str_to_date('18-JAN-2020', '%d-%M-%Y'), str_to_date('19-JAN-2020', '%d-%M-%Y') union all
select 9, str_to_date('19-JAN-2020', '%d-%M-%Y'), str_to_date('20-JAN-2020', '%d-%M-%Y') union all
select 10, str_to_date('21-JAN-2020', '%d-%M-%Y'), str_to_date('22-JAN-2020', '%d-%M-%Y') union all
select 11, str_to_date('26-JAN-2020', '%d-%M-%Y'), str_to_date('27-JAN-2020', '%d-%M-%Y') union all
select 12, str_to_date('27-JAN-2020', '%d-%M-%Y'), str_to_date('28-JAN-2020', '%d-%M-%Y') union all
select 13, str_to_date('28-JAN-2020', '%d-%M-%Y'), str_to_date('29-JAN-2020', '%d-%M-%Y') union all
select 14, str_to_date('29-JAN-2020', '%d-%M-%Y'), str_to_date('30-JAN-2020', '%d-%M-%Y');

select proj_id,
       date_format(proj_start, '%d-%M-%Y') as proj_start,
       date_format(proj_end, '%d-%M-%Y') as proj_end
from V10;

select proj_id, proj_start, proj_end
from (
    select proj_id, proj_start, proj_end,
           lead(proj_start) over (order by proj_id) next_proj_start
    from V10
         ) x
where next_proj_start = proj_end;

select *
from (
    select proj_id, proj_start, proj_end,
           lead(proj_start) over (order by proj_id) next_proj_start
    from V10
         ) x
where proj_id in (1, 4);

select *
from V10
where proj_id <= 5;

select proj_id, proj_start, proj_end
from (
    select proj_id, proj_start, proj_end,
           lead(proj_start) over (order by proj_id) next_start
    from V10
    where proj_id <= 5
    ) x
where proj_end = next_start;

select proj_id, proj_start, proj_end
from (
    select proj_id, proj_start, proj_end,
           lead(proj_start) over (order by proj_id) next_start,
           lag(proj_end) over (order by proj_id) last_end
    from V10
    where proj_id <= 5
         ) x
where proj_end = next_start
or proj_start = last_end;

# 10.2 같은 그룹 또는 파티션의 행 간 차이 찾기
with next_sal_tab (deptno, ename, sal, hiredate, next_sal) as
(
    select deptno, ename, sal, hiredate,
           lead(sal) over (partition by deptno order by hiredate) next_sal
    from emp
)

select deptno, ename, sal, hiredate,
       coalesce(cast(sal - next_sal as char), 'N/A') as diff
from next_sal_tab;

# 10.3 연속 값 범위의 시작과 끝 찾기
select a2.proj_grp, min(a2.proj_start), max(a2.proj_end)
from
(
    select a1.proj_id,
           a1.proj_start,
           a1.proj_end,
           sum(flag) over (order by proj_id) proj_grp
    from (
             select proj_id,
                    proj_start,
                    proj_end,
                    if(lag(proj_end) over (order by proj_id) = proj_start, 0, 1) flag
             from V10
         ) a1
) a2
group by a2.proj_grp;

select proj_id, proj_start, proj_end,
       lag(proj_end) over (order by proj_id) prior_proj_end
from V10;

select proj_id, proj_start, proj_end,
       sum(flag) over(order by proj_id) proj_grp,
       flag
from (
    select proj_id, proj_start, proj_end,
           if(lag(proj_end) over (order by proj_id) = proj_start, 0, 1) flag
    from V10) v;

# 10.4 값 범위에서 누락된 값 채우기
select y.yr, coalesce(x.cnt, 0) as cnt
from
(
    select min_year - mod(min_year, 10) + rn as yr,
           min_year,
           mod(min_year, 10)                 as mod_yr
    from (
             select (select min(extract(year from hiredate)) from emp) min_year,
                    id - 1 as                                          rn
             from t10
         ) a
) y
left join
(
    select extract(year from hiredate) as yr, count(*) as cnt
    from emp
    group by extract(year from hiredate)
) x
on (y.yr = x.cnt);
