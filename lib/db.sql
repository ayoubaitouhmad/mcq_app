# DROP DATABASE db_mcq;
CREATE DATABASE IF NOT EXISTS `db_mcq`;
USE `db_mcq`;



CREATE TABLE IF NOT EXISTS `students`
(
    `id`        bigint unsigned NOT NULL AUTO_INCREMENT,
    `full_name` varchar(255) DEFAULT NULL,
    `major`     varchar(255) DEFAULT NULL,
    `grade`     varchar(255) DEFAULT NULL,
    `cin`       varchar(255)    NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `students_pk` (`cin`)
)
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `professors`
(
    `id`         bigint unsigned NOT NULL AUTO_INCREMENT,
    `full_name`  varchar(255) DEFAULT NULL,
    `speciality` varchar(255) DEFAULT NULL,
    `cin`        varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
)
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;



CREATE TABLE IF NOT EXISTS `quizzes`
(
    `id`              bigint unsigned NOT NULL AUTO_INCREMENT,
    `professor`       bigint unsigned DEFAULT NULL,
    `title`           varchar(255)    DEFAULT NULL,
    `hasMultiChoices` tinyint(1)      DEFAULT NULL,
    `targetCategory`  varchar(255)    DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `quizzes_professors_foreign` (`professor`),
    CONSTRAINT `quizzes_professors_foreign` FOREIGN KEY (`professor`) REFERENCES `professors` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
)
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;



CREATE TABLE IF NOT EXISTS `questions`
(
    `id`       bigint unsigned NOT NULL AUTO_INCREMENT,
    `question` text,
    PRIMARY KEY (`id`)
)
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `options`
(
    `id`          bigint unsigned NOT NULL AUTO_INCREMENT,
    `question_id` bigint unsigned DEFAULT NULL,
    `option`      text,
    `is_correct`  tinyint(1)      DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `options_questions_foreign` (`question_id`),
    CONSTRAINT `options_questions_foreign` FOREIGN KEY (`question_id`) REFERENCES `questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
)
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;



CREATE TABLE IF NOT EXISTS `quiz_questions`
(
    `id`       bigint unsigned NOT NULL AUTO_INCREMENT,
    `quiz`     bigint unsigned DEFAULT NULL,
    `question` bigint unsigned DEFAULT NULL,
    `position` int             DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `quiz_questions_question_foreign` (`question`),
    KEY `quiz_questions_quiz_foreign` (`quiz`),
    CONSTRAINT `quiz_questions_question_foreign` FOREIGN KEY (`question`) REFERENCES `questions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `quiz_questions_quiz_foreign` FOREIGN KEY (`quiz`) REFERENCES `quizzes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
)
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;



CREATE TABLE IF NOT EXISTS `student_attempts`
(
    `id`         bigint    NOT NULL AUTO_INCREMENT,
    `student`    bigint unsigned DEFAULT NULL,
    `quiz`       bigint unsigned DEFAULT NULL,
    `score`      int             DEFAULT NULL,
    `created_at` timestamp NULL  DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`),
    KEY `student` (`student`),
    KEY `quiz` (`quiz`),
    CONSTRAINT `student_attempts_ibfk_1` FOREIGN KEY (`student`) REFERENCES `students` (`id`)  ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `student_attempts_ibfk_2` FOREIGN KEY (`quiz`) REFERENCES `quizzes` (`id`)  ON DELETE CASCADE ON UPDATE CASCADE
)
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;
