use rcttc;

insert into customer (first_name, last_name, email_address, phone_number, address)
select distinct customer_first,
customer_last,
customer_email,
customer_phone,
customer_address
from temp_tiny_theater;


insert into theater(theater_name, theater_address, phone_number, email_address)
select distinct theater,
theater_address,
theater_phone,
theater_email
from temp_tiny_theater;


insert into performance(theater_name, name, date, price)
select distinct 
th.theater_name,
t.show,
t.date,
t.ticket_price
from temp_tiny_theater t
inner join theater th on t.theater = th.theater_name;




insert into ticket(performance_id, customer_id, seat)
select 
p.performance_id,
c.customer_id,
th.seat
from temp_tiny_theater th
inner join customer c on th.customer_first = c.first_name and th.customer_last = c.last_name
inner join theater t on th.theater = t.theater_name
inner join performance p on th.date = p.date 
	and p.name = th.show;
    

set sql_safe_updates = 0;

update performance set
	price = 22.25
where performance_id = 5;

update customer set
phone_number = '338-922-3547'
where first_name = 'Elicia';

update customer set
phone_number = '129-168-4725'
where first_name = 'Hashim';

update ticket set
seat = 'F1'
where performance_id = 5 and customer_id= 35 and seat = 'A1';

update ticket set
seat = 'T1'
where performance_id = 5 and customer_id = 36 and seat = 'A3';

update ticket set
seat = 'T2'
where performance_id = 5 and customer_id = 37 and seat = 'A4';

update ticket set
seat = 'T3'
where performance_id = 5 and customer_id = 38 and seat = 'C1';

update ticket set
seat = 'T4'
where performance_id = 5 and customer_id = 38 and seat = 'B4';

update ticket set
seat = 'A1'
where performance_id = 5 and customer_id = 36 and seat = 'T1';

update ticket set
seat = 'B4'
where performance_id = 5 and customer_id = 37 and seat = 'T2';

update ticket set 
seat = 'A3'
where performance_id = 5 and customer_id = 38 and seat = 'T3';

update ticket set
seat = 'A4'
where performance_id = 5 and customer_id = 38 and seat = 'T4';

update ticket set
seat = 'C1'
where performance_id = 5 and customer_id = 35 and seat = 'F1';

delete
from ticket
where customer_id = 42;

delete from customer
where customer_id = 42;

delete
from ticket
where performance_id = 6 and customer_id = 43;

delete
from ticket
where performance_id = 6 and customer_id = 45;

delete
from ticket
where performance_id = 6 and customer_id = 46;

set sql_safe_updates = 1;

drop table temp_tiny_theater;
drop table if exists temp_tiny_theater;
