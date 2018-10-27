create sequence hibernate_sequence start with 1 increment by 1;

create table series_entity (id bigint not null, date integer not null, screen varchar(255), series_id varchar(255), views integer not null, primary key (id));