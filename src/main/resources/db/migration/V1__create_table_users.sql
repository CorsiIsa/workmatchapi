CREATE TABLE IF NOT EXISTS users (
    id bigint auto_increment,
    name varchar(255),
    date_of_birth datetime,
    genero varchar(10),
    phone_number varchar(21),
    email varchar(255),
    password varchar(255),
    created_at datetime,
    updated_at datetime,
    primary key (id)

);