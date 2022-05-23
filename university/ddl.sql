CREATE SCHEMA IF NOT EXISTS university
    AUTHORIZATION postgres;

CREATE TABLE university.groups
(
    id bigserial NOT NULL,
    name character varying(255) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS university.groups
    OWNER to postgres;

CREATE TABLE university.students
(
    id bigserial NOT NULL,
    name character varying(255) NOT NULL,
    age smallint NOT NULL,
    score double precision,
    olympic_gamer boolean,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS university.students
    OWNER to postgres;

CREATE TABLE university.student_in_the_group
(
    group_id bigserial NOT NULL,
    student_id bigserial NOT NULL
);

ALTER TABLE IF EXISTS university.student_in_the_group
    OWNER to postgres;

ALTER TABLE IF EXISTS university.student_in_the_group
    ADD CONSTRAINT fk_group FOREIGN KEY (group_id)
    REFERENCES university.groups (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

ALTER TABLE IF EXISTS university.student_in_the_group
    ADD CONSTRAINT fk_student FOREIGN KEY (student_id)
    REFERENCES university.students (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;