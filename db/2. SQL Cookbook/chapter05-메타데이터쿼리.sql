# 5 메타 데이터 관리

# 5.1 스키마의 테이블 목록 보기
select TABLE_NAME
from information_schema.TABLES
where TABLE_SCHEMA = 'sql-cookbook';

# 5.2 테이블의 열 나열하기
select COLUMN_NAME, DATA_TYPE, ORDINAL_POSITION, IS_NULLABLE, COLUMN_KEY
from information_schema.COLUMNS
where TABLE_SCHEMA = 'sql-cookbook'
and TABLE_NAME = 'emp';

# 5.3 테이블의 인덱싱된 열 나열하기
show index from emp;
show index from dept;

# 5.4 테이블의 제약조건 나열하기
select a.TABLE_NAME,
       a.CONSTRAINT_NAME,
       b.COLUMN_NAME,
       a.CONSTRAINT_TYPE
from information_schema.TABLE_CONSTRAINTS a,
     information_schema.KEY_COLUMN_USAGE b
where a.TABLE_NAME = 'emp'
and a.TABLE_SCHEMA = 'sql-cookbook'
and a.TABLE_NAME = b.TABLE_NAME
and a.TABLE_SCHEMA = b.TABLE_SCHEMA
and a.CONSTRAINT_NAME = b.CONSTRAINT_NAME;
