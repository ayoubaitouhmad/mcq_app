
USE `db_mcq`;



INSERT INTO db_mcq.professors (full_name, speciality, cin)VALUES ('W.Q', 'developer', 'AZ123');
INSERT INTO db_mcq.professors (full_name, speciality, cin)VALUES ('A.O.T', 'developer', 'AZ124');


INSERT INTO db_mcq.questions (question) VALUES ('Quel design pattern fournit une interface unifiée facile à utiliser pour un ensemble dinterfaces dans un sous-système ?');
INSERT INTO db_mcq.questions (question) VALUES ('Dans la méthode Merise le concept de synchronisation est relatif au :');
INSERT INTO db_mcq.questions (question) VALUES ('Quel énoncé est vrai à propos des diagrammes UML ?');
INSERT INTO db_mcq.questions (question) VALUES ('Quel énoncé est faux à propos de Web Service ?');




INSERT INTO db_mcq.options ( question_id, `option`, is_correct) VALUES ( 1, 'Prototype', 0);
INSERT INTO db_mcq.options ( question_id, `option`, is_correct) VALUES ( 1, 'Iterator', 0);
INSERT INTO db_mcq.options ( question_id, `option`, is_correct) VALUES ( 1, 'Facade', 1);
INSERT INTO db_mcq.options ( question_id, `option`, is_correct) VALUES ( 1, 'Observer', 0);
INSERT INTO db_mcq.options ( question_id, `option`, is_correct) VALUES ( 2, 'Diagramme de flux', 0);
INSERT INTO db_mcq.options ( question_id, `option`, is_correct) VALUES ( 2, 'MCT', 1);
INSERT INTO db_mcq.options ( question_id, `option`, is_correct) VALUES ( 2, 'MLD', 0);
INSERT INTO db_mcq.options ( question_id, `option`, is_correct) VALUES ( 2, 'MPT', 0);
INSERT INTO db_mcq.options ( question_id, `option`, is_correct) VALUES ( 3, 'Le diagramme de séquence rassemble les cas dutilisation', 0);
INSERT INTO db_mcq.options ( question_id, `option`, is_correct) VALUES ( 3, 'La composition est un cas particulier de lassociation', 1);
INSERT INTO db_mcq.options ( question_id, `option`, is_correct) VALUES ( 3, 'Un diagramme de cas dutilisation est un scénario de tests', 0);
INSERT INTO db_mcq.options ( question_id, `option`, is_correct) VALUES ( 3, 'Dans lagrégation', 0);
INSERT INTO db_mcq.options ( question_id, `option`, is_correct) VALUES ( 4, 'AXIAL', 0);
INSERT INTO db_mcq.options ( question_id, `option`, is_correct) VALUES ( 4, 'MDA', 0);
INSERT INTO db_mcq.options ( question_id, `option`, is_correct) VALUES ( 4, 'MERISE', 0);
INSERT INTO db_mcq.options ( question_id, `option`, is_correct) VALUES ( 4, 'OCL', 1);

INSERT INTO db_mcq.quizzes (professor, title, hasMultiChoices, targetCategory) VALUES ( 1, 'Quiz QCM Développement informatique et Système dinformation', 0, 'developer');



INSERT INTO db_mcq.quiz_questions (quiz, question, position) VALUES ( 1, 1, 0);
INSERT INTO db_mcq.quiz_questions (quiz, question, position) VALUES (1, 2, 0);
INSERT INTO db_mcq.quiz_questions (quiz, question, position) VALUES ( 1, 3, 0);
INSERT INTO db_mcq.quiz_questions (quiz, question, position) VALUES ( 1, 4, 0);


-- INSERT INTO db_mcq.options ( question_id, `option`, is_correct) VALUES ( 5, 'Il est invoqué dynamiquement par dautres services', 0);
-- INSERT INTO db_mcq.options ( question_id, `option`, is_correct) VALUES ( 5, 'Il est encapsulé dans une couche de standards dérivés du langage XML', 0);
-- INSERT INTO db_mcq.options ( question_id, `option`, is_correct) VALUES ( 5, 'Il est déployé sur nimporte quelle plate-forme', 0);
-- INSERT INTO db_mcq.options ( question_id, `option`, is_correct) VALUES ( 5, 'Un Web Service est un composant complexe implémenté dans un langage précis', 1);
