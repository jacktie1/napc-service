ALTER TABLE `management` ADD COLUMN `week_of_welcome_start_date` DATE;

UPDATE `management` SET `week_of_welcome_start_date` = '2024-08-12';

ALTER TABLE `management` MODIFY COLUMN `week_of_welcome_start_date` DATE NOT NULL;

ALTER TABLE `student` ADD COLUMN `attends_week_of_welcome` BOOLEAN NOT NULL DEFAULT FALSE;