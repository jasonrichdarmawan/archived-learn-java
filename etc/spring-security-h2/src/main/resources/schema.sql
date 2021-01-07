create table user
(
    id integer not null,
    user varchar(255) not null,
    password varchar(255) not null,
    active int not null,
    roles varchar(255) null,
    authorities varchar(255) null,
    primary key(id)
);