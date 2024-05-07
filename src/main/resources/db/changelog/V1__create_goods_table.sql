--liquibase formatted sql

--changeset rstd:1
create table if not exists goods
(
    id          bigserial primary key,
    name        varchar(255) not null,
    description varchar(4096),
    price       decimal      not null default (0),
    in_stock    bool         not null default (false)
);