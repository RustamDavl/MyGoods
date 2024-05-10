--liquibase formatted sql

--changeset rstd:1
create table if not exists goods_delivery
(
    id       bigserial primary key,
    name     varchar(255) not null,
    goods_id bigint unique references goods (id),
    amount   int check ( amount > 0 )
);