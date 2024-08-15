-- 코드를 입력하세요
SELECT
    mcdp_cd as `진료과 코드`,
    count(*) as `5월예약건수`
from
    appointment
where
    month(apnt_ymd) = 5
group by
    mcdp_cd
order by 
    `5월예약건수` asc,
    `진료과 코드`