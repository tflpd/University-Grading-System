CREATE TABLE `Credential` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255),
  `password` varchar(255)
);

CREATE TABLE `Professor` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `first_name` varchar(255),
  `last_name` varchar(255),
  `credentialId` int
);

CREATE TABLE `Enrolment` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `studentId` int,
  `courseId` int,
  `isActive` bit,
  `status` int,
  `comment` varchar(255),
  `courseSectionId` int
);

CREATE TABLE `Student` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `first_name` varchar(255),
  `last_name` varchar(255),
  `email` varchar(255),
  `buid` varchar(255),
  `comment` varchar(255),
  `created_at` timestamp
);

CREATE TABLE `Course` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `code` varchar(255),
  `name` varchar(255),
  `created_at` timestamp,
  `professorId` int,
  `isDeleted` boolean
);

CREATE TABLE `CourseSection` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `courseId` int
);

CREATE TABLE `Task` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `courseId` int,
  `weight` double,
  `created_at` timestamp,
  `isDeleted` boolean
);

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

CREATE TABLE `TemplateCourse` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `code` varchar(255),
  `created_at` timestamp
);

CREATE TABLE `TemplateTask` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `courseId` int,
  `weight` double,
  `created_at` timestamp
);

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

ALTER TABLE `Enrolment` ADD FOREIGN KEY (`id`) REFERENCES `Grade` (`enrolmentId`);

ALTER TABLE `Course` ADD FOREIGN KEY (`id`) REFERENCES `Task` (`courseId`);

ALTER TABLE `Task` ADD FOREIGN KEY (`id`) REFERENCES `SubTask` (`taskId`);

ALTER TABLE `SubTask` ADD FOREIGN KEY (`id`) REFERENCES `Grade` (`subTaskId`);

ALTER TABLE `Student` ADD FOREIGN KEY (`id`) REFERENCES `Enrolment` (`studentId`);

ALTER TABLE `Professor` ADD FOREIGN KEY (`id`) REFERENCES `Credential` (`id`);

ALTER TABLE `Course` ADD FOREIGN KEY (`professorId`) REFERENCES `Professor` (`id`);

ALTER TABLE `CourseSection` ADD FOREIGN KEY (`courseId`) REFERENCES `Course` (`id`);

ALTER TABLE `Enrolment` ADD FOREIGN KEY (`courseSectionId`) REFERENCES `CourseSection` (`id`);

ALTER TABLE `TemplateCourse` ADD FOREIGN KEY (`id`) REFERENCES `TemplateTask` (`courseId`);

ALTER TABLE `TemplateTask` ADD FOREIGN KEY (`id`) REFERENCES `TemplateSubTask` (`taskId`);
