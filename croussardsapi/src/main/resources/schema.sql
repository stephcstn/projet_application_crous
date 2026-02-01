DROP TABLE IF EXISTS students;

CREATE TABLE students (
    id_student INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    last_name VARCHAR(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    mail VARCHAR(99) NOT NULL UNIQUE,
    pwd_hash VARCHAR(45) NOT NULL,
    username VARCHAR(45) UNIQUE
);

DROP TABLE IF EXISTS restaurants;

CREATE TABLE restaurants (
    id_resto INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NULL,
    type_resto VARCHAR(45) NULL
);

DROP TABLE IF EXISTS reviews;

CREATE TABLE reviews (
    id_review INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_student INT NOT NULL,
    id_resto INT NOT NULL,
    nb_stars INT NOT NULL DEFAULT 5,
    comments VARCHAR(500),
    date_publi DATE NOT NULL,

    CONSTRAINT fk_num_student
     FOREIGN KEY (id_student)
     REFERENCES students(id_student)
     ON DELETE CASCADE
     ON UPDATE NO ACTION,

    CONSTRAINT fk_id_resto
     FOREIGN KEY (id_resto)
     REFERENCES restaurants(id_resto)
     ON DELETE CASCADE
     ON UPDATE NO ACTION,

    CONSTRAINT check_stars
     CHECK (nb_stars BETWEEN 1 AND 5)
);
