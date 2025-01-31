# 9 날짜 조작 기법

# 9.1 연도의 윤년 여부 결정하기

# 9.2 연도의 날짜 수 알아내기
select adddate(current_date, -dayofyear(current_date) + 1);

# 9.3 날짜에서 시간 단위 추출하기
select hour(current_timestamp) hr,
       minute(current_timestamp) min,
       second(current_timestamp) sec,
       day(current_timestamp) dy,
       month(current_timestamp) mth,
       year(current_timestamp) yr;

select date_format(current_timestamp, '%k') hr,
       date_format(current_timestamp, '%i') min,
       date_format(current_timestamp, '%s') sec,
       date_format(current_timestamp, '%d') dy,
       date_format(current_timestamp, '%m') mon,
       date_format(current_timestamp, '%Y') yr;

# 9.4 월의 첫 번째 요일과 마지막 요일 알아내기
select date_add(current_date, interval - day(current_date) + 1 day) first_day,
       last_day(current_date) last_day;
