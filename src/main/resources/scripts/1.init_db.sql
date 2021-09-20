DROP DATABASE IF EXISTS homework;
CREATE DATABASE IF NOT EXISTS homework;
use homework;

CREATE TABLE developers (
                            id int NOT NULL AUTO_INCREMENT,
                            name varchar(55) NOT NULL,
                            age int(3),
                            gender varchar(10),
                            salary long not null,
                            PRIMARY KEY (id));

CREATE TABLE skills (
                        id int NOT NULL AUTO_INCREMENT,
                        language varchar(10),
                        level varchar(10),
                        PRIMARY KEY (id));

CREATE TABLE projects (
                          id int NOT NULL AUTO_INCREMENT,
                          name varchar(100) NOT NULL,
                          field varchar(20) NOT NULL,
                          cost  long NOT NULL,
                          create_date date not null,
                          PRIMARY KEY (id));

CREATE TABLE companies (
                           id int NOT NULL AUTO_INCREMENT,
                           name varchar(20) NOT NULL,
                           city varchar(20) NOT NULL,
                           PRIMARY KEY (id));

CREATE TABLE customers (
                           id int NOT NULL AUTO_INCREMENT,
                           name varchar(25) NOT NULL,
                           city varchar(20) NOT NULL,
                           industry varchar (20) NOT NULL,
                           PRIMARY KEY (id));

CREATE TABLE developers_skills (
                                   id_developer int NOT NULL,
                                   id_skill int NOT NULL,
                                   PRIMARY KEY (id_developer, id_skill),
                                   FOREIGN KEY (id_developer) REFERENCES developers (id),
                                   FOREIGN KEY (id_skill) REFERENCES skills (id));

CREATE TABLE developers_projects (
                                     id_developer int NOT NULL,
                                     id_project int NOT NULL,
                                     PRIMARY KEY (id_developer, id_project),
                                     FOREIGN KEY (id_developer) REFERENCES developers (id),
                                     FOREIGN KEY (id_project) REFERENCES projects (id));

CREATE TABLE companies_projects (
                                    id_company int NOT NULL,
                                    id_project int NOT NULL,
                                    PRIMARY KEY (id_company, id_project),
                                    KEY id_project (id_project),
                                    FOREIGN KEY (id_company) REFERENCES companies (id),
                                    FOREIGN KEY (id_project) REFERENCES projects (id));

CREATE TABLE customers_projects (
                                    id_customer int NOT NULL,
                                    id_project int NOT NULL,
                                    PRIMARY KEY (id_customer, id_project),
                                    KEY id_project (id_project),
                                    FOREIGN KEY (id_customer) REFERENCES customers (id),
                                    FOREIGN KEY (id_project) REFERENCES projects (id));