DROP TABLE IF EXISTS company_entity;

CREATE TABLE company_entity
(
    company_id   BIGINT NOT NULL AUTO_INCREMENT,
    company_code VARCHAR(255),
    company_name VARCHAR(255),
    PRIMARY KEY (company_id)
);

INSERT INTO company_entity (company_id, company_code, company_name) VALUES (1, 'ready_made_company_code', 'ready_made_company_name');