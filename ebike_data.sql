--create type custom_role as enum('ADMIN', 'CUSTOMER', 'STAFF');

create table if not exists bikes (
                                     id serial primary key,
                                     name varchar(100) not null,
    warranty_status char(2),
    image bytea,
    model varchar(50) not null,
    price int not null,
    date_created timestamp not null default CURRENT_TIMESTAMP
    )

create table if not exists employees (
                                         id serial primary key,
                                         fullname varchar(150) not null,
    email varchar(75) not null,
    user_role custom_role not null,
    password varchar(75) not null,
    date_created timestamp not null default CURRENT_TIMESTAMP
    )

create table if not exists customer (
                                        id serial primary key,
                                        fullname  varchar(150) not null,
    email varchar(150) not null,
    password varchar(75) not null,
    user_role custom_role not null,
    date_created timestamp not null default CURRENT_TIMESTAMP
    )

create table if not exists bike_order (
                                          id serial primary key,
                                          date_created timestamp not null default CURRENT_TIMESTAMP,
                                          product_id int not null,
                                          customer_id int not null,
                                          price int not null,
                                          constraint fk_customer foreign key(customer_id) references customer(id),
    constraint fk_product foreign key(product_id) references bikes(id)
    )
