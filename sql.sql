CREATE DATABASE IF NOT EXISTS `tasks`;
USE `tasks`;

CREATE TABLE IF NOT EXISTS `persons` (
    `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45) DEFAULT NULL,
    `age` INT(11) DEFAULT 0,
    `role` VARCHAR(45) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `tasks` (
    `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45) DEFAULT NULL,
    `length` INT(11) DEFAULT 0,
    `date` DATE DEFAULT '1970-01-01',
    `field` VARCHAR(45) DEFAULT NULL,
    `person_id` INT(11) NOT NULL,
    FOREIGN KEY(`person_id`) REFERENCES `persons`(`id`)
);