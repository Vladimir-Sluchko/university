CREATE DATABASE base_university
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

CREATE SCHEMA university
    AUTHORIZATION postgres;

CREATE TABLE university.student
(
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    age smallint NOT NULL,
    score smallint,
    olympic_gamer boolean,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS university.student
    OWNER to postgres;

CREATE TABLE university."group"
(
    id bigint NOT NULL,
    name character varying(255) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS university."group"
    OWNER to postgres;

CREATE TABLE university.student_in_the_group
(
    group_id bigint NOT NULL,
    student_id bigint NOT NULL
);

ALTER TABLE IF EXISTS university.student_in_the_group
    OWNER to postgres;


ALTER TABLE IF EXISTS university.student_in_the_group
    ADD CONSTRAINT fk_group FOREIGN KEY (group_id)
    REFERENCES university."group" (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS university.student_in_the_group
    ADD CONSTRAINT fk_student FOREIGN KEY (student_id)
    REFERENCES university.student (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

