# 6 문자열 작업

# 6.1 문자열 짚어보기
select ename, iter.pos
from (select ename from emp where ename = 'KING') e,
     (select id as pos from t10) iter;

select substr(e.ename, iter.pos, 1) as C
from (select ename from emp where ename = 'KING') e,
     (select id as pos from t10) iter
where iter.pos <= length(e.ename);

select substr(e.ename, iter.pos),
       substr(e.ename, length(e.ename) - iter.pos + 1) b
from (select ename from emp where ename = 'KING') e,
     (select id pos from t10) iter
where iter.pos <= length(ename);

# 6.2 문자열에 따옴표 포함하기
select 'apples core', 'apple''s score',
       if('' is null, 0, 1)
from dual;

select '''' as quote from dual;

# 6.6 문자열의 영숫자 여부 확인하기
create view VVVV as
select ename as data
from emp
where deptno = 10
union all
select concat(ename, ', $', sal, '.00') as data
from emp
#where deptno = 20
union all
select concat(ename, deptno) as data
from emp
where deptno = 30;

select * from VVVV;

select data
from VVVV
where data regexp '[^0-9a-zA-Z]' = 0;

# 6.10 테이블 행으로 구분된 목록 만들기
select deptno, ename
from emp;

select deptno,
       group_concat(ename order by empno separator ',') as emps
from emp
group by deptno;

# 6.15 IP 주소 파싱하기
select substring_index(substring_index(y.ip, '.', 1), '.', -1) a,
       substring_index(substring_index(y.ip, '.', 2), '.', -1) b,
       substring_index(substring_index(y.ip, '.', 3), '.', -1) c,
       substring_index(substring_index(y.ip, '.', 4), '.', -1) d
from (select '92.111.0.2' as ip from dual) y;

# 6.17 패턴과 일치하지 않는 텍스트 찾기
create table employee_comment (
    emp_id int(4),
    text text,
    primary key (emp_id)
);
show index from employee_comment;
insert into employee_comment values (7369, '126 Varnum, Edmore MI 48829, 989 313-5351');
insert into employee_comment values (7499, '1105 McConnell Court\nCedar Lake MI 48812\nHome: 989-387-4321\nCell: (237) 438-3333');

select emp_id, text
from employee_comment
where text regexp '[0-9]{3}[-. ][0-9]{3}[-. ][0-9]{4}';
