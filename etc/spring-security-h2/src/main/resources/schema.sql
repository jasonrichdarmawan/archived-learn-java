create table user
(
    id integer not null,
    user varchar(255) not null,
    password varchar(255) not null,
    scopes varchar(255) not null,
    primary key(id)
);