
CREATE DATABASE IF NOT EXISTS grading_system;
USE grading_system;


DROP TABLE IF EXISTS `Credential`;
CREATE TABLE `Credential` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255),
  `password` varchar(255)
);


DROP TABLE IF EXISTS `Professor`;
CREATE TABLE `Professor` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `first_name` varchar(255),
  `last_name` varchar(255),
  `credentialId` int
);


DROP TABLE IF EXISTS `Enrollment`;
CREATE TABLE `Enrollment` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `studentId` int,
  `courseId` int,
  `isActive` bit,
  `status` int,
  `comment` varchar(255),
  `courseSectionId` int
);


DROP TABLE IF EXISTS `Student`;
CREATE TABLE `Student` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `first_name` varchar(255),
  `last_name` varchar(255),
  `email` varchar(255),
  `buid` varchar(255),
  `comment` varchar(255),
  `created_at` timestamp
);


DROP TABLE IF EXISTS `Course`;
CREATE TABLE `Course` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `code` varchar(255),
  `name` varchar(255),
  `created_at` timestamp,
  `professorId` int,
  `isDeleted` boolean
);


DROP TABLE IF EXISTS `CourseSection`;
CREATE TABLE `CourseSection` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `courseId` int
);

DROP TABLE IF EXISTS `Task`;
CREATE TABLE `Task` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `courseId` int,
  `weight` double,
  `created_at` timestamp,
  `isDeleted` boolean
);

DROP TABLE IF EXISTS `SubTask`;
CREATE TABLE `SubTask` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `taskId` int,
  `weight` double,
  `name` varchar(255),
  `maxScore` int,
  `scoreType` int,
  `created_at` timestamp,
  `releasedDate` datetime,
  `dueDate` datetime,
  `isGroupWork` bit,
  `isDeleted` boolean
);




DROP TABLE IF EXISTS `Grade`;
CREATE TABLE `Grade` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `subTaskId` int,
  `enrolmentId` int,
  `score` double,
  `bonusScore` double,
  `comment` varchar(255),
  `created_at` timestamp,
  `isDeleted` boolean
);

DROP TABLE IF EXISTS `TemplateCourse`;
CREATE TABLE `TemplateCourse` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `code` varchar(255),
  `created_at` timestamp
);


DROP TABLE IF EXISTS `TemplateTask`;
CREATE TABLE `TemplateTask` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `courseId` int,
  `weight` double,
  `created_at` timestamp
);


DROP TABLE IF EXISTS `TemplateSubTask`;
CREATE TABLE `TemplateSubTask` (
  `taskId` int PRIMARY KEY AUTO_INCREMENT,
  `weight` double,
  `name` varchar(255),
  `maxScore` int,
  `scoreType` int,
  `created_at` timestamp,
  `releasedDate` datetime,
  `dueDate` datetime,
  `isGroupWork` bit
);

ALTER TABLE `Enrollment` ADD FOREIGN KEY (`id`) REFERENCES `Grade` (`enrolmentId`);

ALTER TABLE `Course` ADD FOREIGN KEY (`id`) REFERENCES `Task` (`courseId`);

ALTER TABLE `Task` ADD FOREIGN KEY (`id`) REFERENCES `SubTask` (`taskId`);

ALTER TABLE `SubTask` ADD FOREIGN KEY (`id`) REFERENCES `Grade` (`subTaskId`);

ALTER TABLE `Student` ADD FOREIGN KEY (`id`) REFERENCES `Enrollment` (`studentId`);

ALTER TABLE `Professor` ADD FOREIGN KEY (`id`) REFERENCES `Credential` (`id`);

ALTER TABLE `Course` ADD FOREIGN KEY (`professorId`) REFERENCES `Professor` (`id`);

ALTER TABLE `CourseSection` ADD FOREIGN KEY (`courseId`) REFERENCES `Course` (`id`);

ALTER TABLE `Enrollment` ADD FOREIGN KEY (`courseSectionId`) REFERENCES `CourseSection` (`id`);

ALTER TABLE `TemplateCourse` ADD FOREIGN KEY (`id`) REFERENCES `TemplateTask` (`courseId`);

ALTER TABLE `TemplateTask` ADD FOREIGN KEY (`id`) REFERENCES `TemplateSubTask` (`taskId`);
