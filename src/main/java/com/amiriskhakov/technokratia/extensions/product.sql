create table product
(
    id      serial
        constraint product_pkey
            primary key,
    articul varchar(255)     not null
        constraint uk_d2en73jto4x7mryn4actf91sk
            unique,
    cost    double precision not null,
    deleted boolean          not null,
    name    varchar(255)     not null
);

insert into product(ARTICUL, COST, DELETED, NAME)
VALUES ('aa1',10,false,'test1');
insert into product(ARTICUL, COST, DELETED, NAME)
VALUES ('aa2',11,false,'test2');
insert into product(ARTICUL, COST, DELETED, NAME)
VALUES ('aa3',12,false,'test3');
insert into product(ARTICUL, COST, DELETED, NAME)
VALUES ('aa4',13,false,'test4');
insert into product(ARTICUL, COST, DELETED, NAME)
VALUES ('aa5',14,false,'test5');