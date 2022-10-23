drop table if exists company_entity;

create table company_entity
(
    company_id   bigint not null auto_increment,
    company_code varchar(255),
    company_name varchar(255),
    primary key (company_id)
);

INSERT INTO test.company_entity (company_id, company_code, company_name) VALUES (1, 'ready_made_company_code', 'ready_made_company_name');