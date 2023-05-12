drop database if exists rcttc;
create database rcttc;
use rcttc;



create table customer(
customer_id int primary key auto_increment,
first_name varchar (50) not null,
last_name varchar (50) not null,
email_address varchar (100) not null,
phone_number varchar(25) null,
address varchar (100) null
);

create table theater(
theater_name varchar(50) primary key not null,
theater_address varchar(100) not null,
phone_number varchar(50) not null,
email_address varchar(100) not null
);

create table performance(
performance_id int primary key auto_increment,
theater_name varchar(50) not null,
`name` varchar(100) not null,
`date` date not null,
price decimal (8,2) not null,
constraint fk_performance_theater_name
	foreign key (theater_name)
    references theater(theater_name)
);

create table ticket(
ticket_id int primary key auto_increment,
performance_id int not null,
customer_id int not null,
seat char(2) not null,
constraint fk_ticket_performance_id
	foreign key (performance_id)
    references performance (performance_id),
constraint fk_ticket_customer_id
	foreign key (customer_id)
    references customer(customer_id),
constraint uq_ticket_seat
	unique (seat, performance_id)
);





