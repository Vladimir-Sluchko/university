CREATE DATABASE base_university
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

CREATE SCHEMA university
    AUTHORIZATION postgres;

CREATE TABLE university.student
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name character varying(255) NOT NULL,
    age smallint NOT NULL,
    score double precision,
    olympic_gamer boolean,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS university.student
    OWNER to postgres;

CREATE TABLE university.t_groups
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    name_group character varying(255) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS university.t_groups
    OWNER to postgres;

CREATE TABLE university.students_in_the_group
(
    group_id bigint NOT NULL,
    student_id bigint NOT NULL
);

ALTER TABLE IF EXISTS university.students_in_the_group
    OWNER to postgres;


ALTER TABLE IF EXISTS university.students_in_the_group
    ADD CONSTRAINT fk_group FOREIGN KEY (group_id)
    REFERENCES university.t_groups (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS university.students_in_the_group
    ADD CONSTRAINT fk_student FOREIGN KEY (student_id)
    REFERENCES university.student (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

