--liquibase formatted sql

--changeset rstd:1
create table if not exists goods_sale
(
    id             bigserial primary key,
    name           varchar(255) not null,
    goods_id       bigint unique references goods (id),
    amount         int check ( amount > 0 ),
    purchase_price decimal
);