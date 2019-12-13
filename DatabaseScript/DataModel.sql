
CREATE DATABASE IF NOT EXISTS grading_system;
USE grading_system;
DROP TABLE IF EXISTS `Grade`;
DROP TABLE IF EXISTS `Credential`;

DROP TABLE IF EXISTS `Grade`;
DROP TABLE IF EXISTS `Enrollment`;

DROP TABLE IF EXISTS `CourseSection`;
DROP TABLE IF EXISTS `SubTask`;
DROP TABLE IF EXISTS `Task`;
DROP TABLE IF EXISTS `Student`;
DROP TABLE IF EXISTS `Course`;
DROP TABLE IF EXISTS `TemplateCourse`;
DROP TABLE IF EXISTS `Professor`;

-- done

CREATE TABLE `Credential` (
                              `id` int PRIMARY KEY AUTO_INCREMENT,
                              `email` varchar(255),
                              `password` varchar(255)
);


CREATE TABLE `Professor` (
                             `id` int PRIMARY KEY AUTO_INCREMENT,
                             `first_name` varchar(255),
                             `last_name` varchar(255),
                             `email` varchar(255),
                             `credentialId` int
);



CREATE TABLE `Enrollment` (
                              `id` int PRIMARY KEY AUTO_INCREMENT,
                              `studentId` int,
                              `courseSectionId` int,
                              `isDeleted` boolean not null default 0
);



CREATE TABLE `Student` (
                           `id` int PRIMARY KEY AUTO_INCREMENT,
                           `first_name` varchar(255),
                           `last_name` varchar(255),
                           `email` varchar(255),
                           `buid` varchar(255),
                           `isWithdrawn` boolean,
                           `isDeleted` boolean not null default 0
);

CREATE TABLE `TemplateCourse` (
                                  `id` int PRIMARY KEY AUTO_INCREMENT,
                                  `name` varchar(255),
                                  `year` varchar(255),
                                  `semester` varchar(255),
                                  `isDeleted` boolean not null default 0
);

CREATE TABLE `Course` (
                          `id` int PRIMARY KEY AUTO_INCREMENT,
                          `templateCourseId` int,
                          `year` varchar(255),
                          `semester` varchar(255),
                          `name` varchar(255),
                          `professorId` int,
                          `isDeleted` boolean not null default 0
);
CREATE TABLE `CourseSection` (
                                 `id` int PRIMARY KEY AUTO_INCREMENT,
                                 `name` varchar(255),
                                 `courseId` int,
                                 `isDeleted` boolean not null default 0
);

CREATE TABLE `Task` (
                        `id` int PRIMARY KEY AUTO_INCREMENT,
                        `name` varchar(255),
                        `templateCourseId` int,
                        `weight` double,
                        `isDeleted` boolean not null default 0
);

CREATE TABLE `SubTask` (
                           `id` int PRIMARY KEY AUTO_INCREMENT,
                           `taskId` int,
                           `weight` double,
                           `name` varchar(255),
                           `totalPointsAvailable` float,
                           `releasedDate` datetime,
                           `dueDate` datetime,
                           `groupProject` bit,
                           `comment` VARCHAR(255),
                           `maxAvailableBonusPoints` float,
                           `isDeleted` boolean not null default 0
);

CREATE TABLE `Grade` (
                         `id` int PRIMARY KEY AUTO_INCREMENT,
                         `subTaskId` int,
                         `studentId` int,
                         `absolutePointsScored` float ,
                         `bonusPoints` float ,
                         `comment` varchar(255),
                         `isDeleted` boolean not null default 0
);






ALTER TABLE `Grade` ADD FOREIGN KEY (`studentId`) REFERENCES `Student` (`id`);

ALTER TABLE `Task` ADD FOREIGN KEY (`templateCourseId`) REFERENCES `TemplateCourse` (`id`);

ALTER TABLE `SubTask` ADD FOREIGN KEY (`taskId`) REFERENCES `Task` (`id`);

ALTER TABLE `Grade` ADD FOREIGN KEY (`subTaskId`) REFERENCES `SubTask` (`id`);

ALTER TABLE `Enrollment` ADD FOREIGN KEY (`studentId`) REFERENCES `Student` (`id`);

ALTER TABLE `Professor` ADD FOREIGN KEY (`credentialId`) REFERENCES `Credential` (`id`);

ALTER TABLE `Course` ADD FOREIGN KEY (`professorId`) REFERENCES `Professor` (`id`);

ALTER TABLE `Course` ADD FOREIGN KEY (`templateCourseId`) REFERENCES `TemplateCourse` (`id`);

ALTER TABLE `CourseSection` ADD FOREIGN KEY (`courseId`) REFERENCES `Course` (`id`);

ALTER TABLE `Enrollment` ADD FOREIGN KEY (`courseSectionId`) REFERENCES `CourseSection` (`id`);
