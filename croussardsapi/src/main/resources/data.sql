
INSERT INTO students (id_student,last_name,first_name,mail,pwd_hash,username) VALUES
    (1, 'JEAN', 'DUPONT', 'jean.dupont@mail.com', 'hash123', 'jdupont17'),
    (2, 'LUCAS', 'MARTIN', 'lucas.martin@mail.com', 'hash124', 'lmartin23'),
    (3, 'LOUISE', 'CARTIER', 'louise.cartier@mail.com', 'hash125', 'lcartier1'),
    (4, 'MARIA', 'DIOR', 'maria.dior@mail.com', 'hash126', 'mdior12');
ALTER TABLE STUDENTS ALTER COLUMN ID_STUDENT RESTART WITH 5;

INSERT INTO restaurants (id_resto, name, address, type_resto) VALUES
    (1, 'Le Gourmet', '12 Rue de Paris', 'Français'),
    (2, 'Sushi Zen', '45 Avenue Tokyo', 'Japonais'),
    (3, 'Pizza Roma', '78 Rue de Rome', 'Italien'),
    (4, 'Curry Palace', '10 Boulevard Delhi', 'Indien');
ALTER TABLE RESTAURANTS ALTER COLUMN ID_RESTO RESTART WITH 5;

INSERT INTO reviews (id_review, id_student, id_resto, nb_stars, comments, date_publi) VALUES
    (1, 1, 1, 5, 'Très bon restaurant, je recommande !', '2025-11-20'),
    (2, 4, 2, 4, 'Sushis corrects mais un peu chers.', '2025-11-18'),
    (3, 3, 3, 3, 'Pizza moyenne, pâte trop fine.', '2025-11-19'),
    (4, 1, 4, 5, 'Excellent curry, service impeccable.', '2025-11-20');
ALTER TABLE REVIEWS ALTER COLUMN ID_REVIEW RESTART WITH 5;
