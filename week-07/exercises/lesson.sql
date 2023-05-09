drop database if exists pets_test;
create database pets_test;
use pets_test;

create table pet (
    pet_id int primary key auto_increment,
    `name` varchar(50) not null,
    `type` varchar(50) null
);


delimiter //
create procedure set_known_good_state()
begin
    -- 2. Throws out all records without executing deletes.
    -- Resets the auto_increment value.
    truncate table pet;

    -- 3. Add test data.
    insert into pet
        (`name`, `type`)
    values
        ('Meep','Mouse'),
        ('Slithpers','Snake'),
        ('Noodles','Dog');
end //

delimiter ;