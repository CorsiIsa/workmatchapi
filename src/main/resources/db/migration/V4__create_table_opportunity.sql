CREATE TABLE IF NOT EXISTS opportunity(
    id bigint auto_increment,
    title varchar(225),
    description varchar(225),
    responsibilities varchar(225),
    benefits varchar(225),
    location varchar(225),
    contract_type varchar(225),
    salary varchar(225),
    company_name varchar(225),
    industry_sector varchar(225),
    created_at datetime,
    user_id bigint,
    primary key (id),
    constraint fk_user
    foreign key (user_id) references users(id)
);