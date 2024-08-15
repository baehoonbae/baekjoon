SELECT hour(datetime) as hour, count(*) 
from animal_outs
where hour(datetime) between 9 and 19
group by hour
order by hour;