create table order
(
    id           serial
        constraint order_pkey
            primary key,
    create_date  timestamp,
    order_number integer      not null,
    user_email   varchar(255) not null
);
insert into order(create_date, order_number, user_email)
values ('1990-01-01 00:00:00','asd','test@io.com');