ALTER TABLE `management` ADD COLUMN `week_of_welcome_start_date` DATE;

UPDATE `management` SET `week_of_welcome_start_date` = '2024-08-12';

ALTER TABLE `management` MODIFY COLUMN `week_of_welcome_start_date` DATE NOT NULL;

ALTER TABLE `student` ADD COLUMN `attends_week_of_welcome` BOOLEAN NOT NULL DEFAULT FALSE;

INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT 'Location', 'Hotels', reference_id FROM reference WHERE `value` = 'Off Campus';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT 'Location', 'Hotels', reference_id FROM reference WHERE `value` = 'On Campus';

INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Georgia Tech Hotel-800 Spring Street NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'Hotels';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Georgia Tech Hotel-800 Spring Street NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'Hotels';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Crowne Plaza-590 W Peachtree St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'Hotels';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Georgian Terrace-659 Peachtree St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'Hotels';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Hampton Inn-244 North Avenue NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'Hotels';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Hilton Garden Inn-97 10th Street NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'Hotels';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Renaissance Atlanta-866 W. Peachtree St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'Hotels';

ALTER TABLE `volunteer` ADD COLUMN `has_pet` BOOLEAN;
ALTER TABLE `volunteer` ADD COLUMN `pet_description` VARCHAR(255);
ALTER TABLE `volunteer` ADD CONSTRAINT `volunteer_has_pet` CHECK ((`has_pet` IS NOT TRUE) OR (`pet_description` IS NOT NULL));
