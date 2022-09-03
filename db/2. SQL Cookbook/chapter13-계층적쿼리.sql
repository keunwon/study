# 13 계층적 쿼리

# 13.1 상위-하위 관계 표현하기
select concat(a.ename, ' works for', b.ename) as emps_end_mgrs
from emp a, emp b
where a.mgr = b.empno;

select a.ename,
       (select b.ename from emp b where b.empno = a.mgr) as mgr
from emp a;

# 13.2 자식-부모-조부모 관계 표현하기
select ename, empno, mgr
from emp
where ename in ('KING', 'CLARK', 'MILLER');

with recursive x (tree, mgr, depth) as
(
    select ename, mgr, 0
    from emp
    where ename = 'MILLER'
    union all 
    select cast(concat(x.tree, '-->', e.ename) as char(100)),
           e.mgr,
           x.depth + 1
    from emp e, x
    where x.mgr = e.empno
)

select tree leaf__branch__root
from x
where depth = 2;

# 13.3 테이블의 계층 뷰 생성하기
with recursive x (ename, empno) as
(
    select cast(ename as char(100)), empno
    from emp
    where mgr is null
    union all
    select cast(concat(x.ename, ' - ', e.ename) as char(100)),
           e.empno
    from emp e, x
    where e.mgr = x.empno
)
select ename as emp_tree
from x
order by 1;

# 13.5 리프, 분기, 루트 노드 행 확인하기
select e.ename,
       (select sign(count(*)) from emp d
           where 0 = (select count(*) from emp f where f.mgr = e.empno)) as is_leaf,
       (select sign(count(*)) from emp d where d.mgr = e.empno
           and e.mgr is not null) as is_branch,
       (select sign(count(*)) from emp d where d.deptno = e.empno
           and d.mgr is null) as is_root
from emp e
order by 4 desc, 3 desc;
