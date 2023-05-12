use rcttc;

select *
from performance
where date > '2021-07-01' and date < '2021-09-30';

select distinct
concat(first_name,' ',last_name) as name
from customer;

select *
from customer
where email_address like '%.com';

select *
from performance 
order by price asc
limit 3;

select 
t.ticket_id,
concat(c.first_name,' ',c.last_name) as name,
p.name,
t.seat,
p.date
from performance p
inner join ticket t on p.performance_id = t.performance_id
inner join customer c on t.customer_id = c.customer_id;

select
c.customer_id,
concat(c.first_name,' ', c.last_name) as name,
p.name,
p.theater_name,
t.seat
from customer c
inner join ticket t on c.customer_id = t.customer_id
inner join performance p on t.performance_id = p.performance_id;

select *
from customer
where address = 0;

select
c.first_name as customer_first,
c.last_name as customer_last,
c.email_address as customer_email,
c.phone_number as customer_phone,
c.address as customer_address,
t.seat,
p.name as `show`,
p.price as price,
p.date as `date`,
th.theater_name as theater,
th.theater_address as theater_address,
th.phone_number as theater_phone,
th.email_address as theater_email
from customer c 
inner join ticket t on c.customer_id = t.customer_id
inner join performance p on t.performance_id = p.performance_id
inner join theater th on p.theater_name = th.theater_name;

select
concat(c.first_name,' ',c.last_name) as name,
count(ticket_id) Tickets_bought
from customer c 
inner join ticket t on c.customer_id = t.customer_id
group by c.customer_id;

select 
p.name,
count(t.ticket_id) as Tickets_sold,
sum(p.price)Total
from performance p
inner join ticket t on p.performance_id = t.performance_id
group by p.name;

select
p.theater_name,
count(t.ticket_id) as Tickets_sold,
sum(p.price)Total
from performance p
inner join ticket t on p.performance_id = t.performance_id
group by p.theater_name;

select
c.customer_id,
concat(c.first_name,' ',c.last_name),
count(t.ticket_id) as Tickets_bought,
sum(p.price)Total_support
from performance p 
inner join ticket t on p.performance_id = t.performance_id
inner join customer c on t.customer_id = c.customer_id
group by customer_id
order by Total_support desc;