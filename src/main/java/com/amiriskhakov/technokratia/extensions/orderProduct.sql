create table order_products_list
(
    order_id         integer not null
        constraint fkdw4qudvr0embxj4ql2hd5t59b
            references order,
    products_list_id integer not null
        constraint fkdxcblc9ara98de5pb5ith89ea
            references product
);