CREATE SCHEMA IF NOT EXISTS `apath`;

CREATE TABLE IF NOT EXISTS `role` (
    `role_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) UNIQUE NOT NULL
);

INSERT INTO `role` (`name`)
VALUES
(
    'Admin'
),
(
    'Student'
),
(
    'Volunteer'
);

CREATE TABLE IF NOT EXISTS `user` (
    `user_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(255) UNIQUE NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `email_address` VARCHAR(255) UNIQUE NOT NULL,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    `enabled` BOOLEAN NOT NULL DEFAULT true,
    `role_id` BIGINT NOT NULL REFERENCES `role`(`role_id`)
);

CREATE TABLE IF NOT EXISTS `book` (
    `book_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `author` VARCHAR(255) NOT NULL,
    `title` VARCHAR(255) NOT NULL
);

INSERT INTO `book` (`author`, `title`)
VALUES (
    'John Piper',
    'Desiring God'
    ),
    (
    'Stephen Charnock',
    'The Existence and Attributes of God'
    ),
    (
    'John Piper',
    'Don''t Waste Your Life'
    ),
    (
    'R.C. Sproul',
    'The Holiness of God'
    );

