CREATE SCHEMA IF NOT EXISTS `apath`;

CREATE TABLE IF NOT EXISTS `role` (
    `role_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) UNIQUE NOT NULL
);


CREATE TABLE IF NOT EXISTS `user` (
    `user_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(255) UNIQUE NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `email_address` VARCHAR(255) UNIQUE NOT NULL,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    `gender` VARCHAR(255),
    `enabled` BOOLEAN NOT NULL DEFAULT true,
    `role_id` BIGINT NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `created_by` BIGINT NOT NULL DEFAULT -1,
    `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `modified_by` BIGINT NOT NULL DEFAULT -1,
    CHECK ((`gender` is not null) or (`username` = 'system-process')),
    CONSTRAINT `fk_user_role_id` FOREIGN KEY (`role_id`) REFERENCES `role`(`role_id`),
    CONSTRAINT `fk_user_created_by` FOREIGN KEY (`created_by`) REFERENCES `user`(`user_id`),
    CONSTRAINT `fk_user_modified_by` FOREIGN KEY (`modified_by`) REFERENCES `user`(`user_id`)
);

CREATE TABLE IF NOT EXISTS `reference` (
    `reference_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `reference_type` VARCHAR(255) NOT NULL,
    `value` VARCHAR(255) NOT NULL,
    `alternate_value` VARCHAR(255),
    `parent_reference_id` BIGINT,
    `enabled` BOOLEAN NOT NULL DEFAULT true,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `created_by` BIGINT NOT NULL DEFAULT -1,
    `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `modified_by` BIGINT NOT NULL DEFAULT -1,
    CONSTRAINT `fk_reference_parent_reference_id` FOREIGN KEY (`parent_reference_id`) REFERENCES `reference`(`reference_id`),
    CONSTRAINT `fk_reference_created_by` FOREIGN KEY (`created_by`) REFERENCES `user`(`user_id`),
    CONSTRAINT `fk_reference_modified_by` FOREIGN KEY (`modified_by`) REFERENCES `user`(`user_id`)
);


CREATE TABLE IF NOT EXISTS `user_security_question` (
    `user_security_question_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT NOT NULL,
    `security_question_reference_id` BIGINT NOT NULL,
    `security_answer` VARCHAR(255) NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `created_by` BIGINT NOT NULL DEFAULT -1 REFERENCES `user`(`user_id`),
    `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `modified_by` BIGINT NOT NULL DEFAULT -1 REFERENCES `user`(`user_id`),
    CONSTRAINT `fk_user_security_question_user_id` FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`) ON DELETE CASCADE,
    constraint `fk_user_security_question_reference_id` foreign key (`security_question_reference_id`) references `reference`(`reference_id`),
    CONSTRAINT `fk_user_security_question_created_by` FOREIGN KEY (`created_by`) REFERENCES `user`(`user_id`),
    CONSTRAINT `fk_user_security_question_modified_by` FOREIGN KEY (`modified_by`) REFERENCES `user`(`user_id`)
);

CREATE TABLE IF NOT EXISTS `student` (
    `student_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT NOT NULL,
    `english_name` VARCHAR(255),
    `is_new_student` BOOLEAN NOT NULL,
    `student_type` VARCHAR(255) NOT NULL,
    `graduates_from` VARCHAR(255),
    `major_reference_id` BIGINT,
    `custom_major` VARCHAR(255),
    `has_companion` BOOLEAN NOT NULL,
    `wechat_id` VARCHAR(255) NOT NULL,
    `cn_phone_number` VARCHAR(255),
    `us_phone_number` VARCHAR(255),
    `needs_airport_pickup` BOOLEAN NOT NULL,
    `has_flight_information` BOOLEAN,
    `arrival_flight_number` VARCHAR(255),
    `arrival_airline_reference_id` BIGINT,
    `custom_arrival_airline` VARCHAR(255),
    `arrival_datetime` DATETIME,
    `departure_flight_number` VARCHAR(255),
    `departure_airline_reference_id` BIGINT,
    `custom_departure_airline` VARCHAR(255),
    `departure_datetime` DATETIME,
    `num_lg_luggages` INTEGER,
    `num_sm_luggages` INTEGER,
    `needs_temp_housing` BOOLEAN NOT NULL,
    `num_nights` INTEGER,
    `apartment_reference_id` BIGINT,
    `custom_destination_address` VARCHAR(255),
    `contact_name` VARCHAR(255),
    `contact_phone_number` VARCHAR(255),
    `contact_email_address` VARCHAR(255),
    `student_comment` TEXT,
    `admin_comment` TEXT,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `created_by` BIGINT NOT NULL DEFAULT -1,
    `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `modified_by` BIGINT NOT NULL DEFAULT -1,
    CHECK ((major_reference_id is null) != (custom_major is null)),
    CHECK ((apartment_reference_id is null) != (custom_destination_address is null)),
    CHECK ((needs_airport_pickup is false) or (has_flight_information is not null)),
    CHECK (
        (needs_airport_pickup is false)
        or (has_flight_information is false)
        or (
            (arrival_flight_number is not null)
            and ((arrival_airline_reference_id is not null) != (custom_arrival_airline is not null))
            and (arrival_datetime is not null)
            and (departure_flight_number is not null)
            and ((departure_airline_reference_id is not null) != (custom_departure_airline is not null))
            and (departure_datetime is not null)
            and (num_lg_luggages is not null)
            and (num_sm_luggages is not null)
        )
    ),
    CHECK ((needs_temp_housing is false) or (num_nights is not null)),
    CONSTRAINT `fk_student_user_id` FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_student_major_reference_id` FOREIGN KEY (`major_reference_id`) REFERENCES `reference`(`reference_id`),
    CONSTRAINT `fk_student_apartment_reference_id` FOREIGN KEY (`apartment_reference_id`) REFERENCES `reference`(`reference_id`),
    CONSTRAINT `fk_student_arrival_airline_reference_id` FOREIGN KEY (`arrival_airline_reference_id`) REFERENCES `reference`(`reference_id`),
    CONSTRAINT `fk_student_departure_airline_reference_id` FOREIGN KEY (`departure_airline_reference_id`) REFERENCES `reference`(`reference_id`),
    CONSTRAINT `fk_student_created_by` FOREIGN KEY (`created_by`) REFERENCES `user`(`user_id`),
    CONSTRAINT `fk_student_modified_by` FOREIGN KEY (`modified_by`) REFERENCES `user`(`user_id`)
);

CREATE TABLE IF NOT EXISTS `volunteer` (
    `volunteer_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT NOT NULL,
    `affiliation` VARCHAR(255) NOT NULL,
    `primary_phone_number` VARCHAR(255) NOT NULL,
    `secondary_phone_number` VARCHAR(255),
    `wechat_id` VARCHAR(255),
    `provides_airport_pickup` BOOLEAN NOT NULL,
    `car_manufacturer` VARCHAR(255),
    `car_model` VARCHAR(255),
    `num_car_seats` INTEGER,
    `num_max_lg_luggages` INTEGER,
    `num_max_trips` INTEGER,
    `airport_pickup_comment` TEXT,
    `provides_temp_housing` BOOLEAN NOT NULL,
    `home_address` VARCHAR(255),
    `num_max_students_hosted` INTEGER,
    `temp_housing_start_date` DATE,
    `temp_housing_end_date` DATE,
    `num_double_beds` INTEGER,
    `num_single_beds` INTEGER,
    `gender_preference` VARCHAR(255),
    `provides_ride` BOOLEAN,
    `temp_housing_comment` TEXT,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `created_by` BIGINT NOT NULL DEFAULT -1,
    `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `modified_by` BIGINT NOT NULL DEFAULT -1,
    CHECK ((provides_temp_housing is false) or (home_address is not null)),
    CONSTRAINT `fk_volunteer_user_id` FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_volunteer_created_by` FOREIGN KEY (`created_by`) REFERENCES `user`(`user_id`),
    CONSTRAINT `fk_volunteer_modified_by` FOREIGN KEY (`modified_by`) REFERENCES `user`(`user_id`)
);

CREATE TABLE IF NOT EXISTS `administrator` (
    `administrator_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT NOT NULL,
    `affiliation` VARCHAR(255) NOT NULL,
    `primary_phone_number` VARCHAR(255) NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `created_by` BIGINT NOT NULL DEFAULT -1,
    `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `modified_by` BIGINT NOT NULL DEFAULT -1,
    CONSTRAINT `fk_administrator_user_id` FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`), -- No cascade delete because it's admin
    CONSTRAINT `fk_administrator_created_by` FOREIGN KEY (`created_by`) REFERENCES `user`(`user_id`),
    CONSTRAINT `fk_administrator_modified_by` FOREIGN KEY (`modified_by`) REFERENCES `user`(`user_id`)
);

CREATE TABLE IF NOT EXISTS `airport_pickup_assignment` (
    `airport_pickup_assignment_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `volunteer_id` BIGINT NOT NULL,
    `student_id` BIGINT NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `created_by` BIGINT NOT NULL DEFAULT -1,
    `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `modified_by` BIGINT NOT NULL DEFAULT -1,
    UNIQUE(`volunteer_id`, `student_id`),
    CONSTRAINT `fk_airport_pickup_assignment_volunteer_id` FOREIGN KEY (`volunteer_id`) REFERENCES `volunteer`(`volunteer_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_airport_pickup_assignment_student_id` FOREIGN KEY (`student_id`) REFERENCES `student`(`student_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_airport_pickup_assignment_created_by` FOREIGN KEY (`created_by`) REFERENCES `user`(`user_id`),
    CONSTRAINT `fk_airport_pickup_assignment_modified_by` FOREIGN KEY (`modified_by`) REFERENCES `user`(`user_id`)
);

CREATE TABLE IF NOT EXISTS `airport_pickup_preference` (
    `airport_pickup_preference_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `volunteer_id` BIGINT NOT NULL,
    `student_id` BIGINT NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `created_by` BIGINT NOT NULL DEFAULT -1,
    `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `modified_by` BIGINT NOT NULL DEFAULT -1,
    UNIQUE(`volunteer_id`, `student_id`),
    CONSTRAINT `fk_airport_pickup_preference_volunteer_id` FOREIGN KEY (`volunteer_id`) REFERENCES `volunteer`(`volunteer_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_airport_pickup_preference_student_id` FOREIGN KEY (`student_id`) REFERENCES `student`(`student_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_airport_pickup_preference_created_by` FOREIGN KEY (`created_by`) REFERENCES `user`(`user_id`),
    CONSTRAINT `fk_airport_pickup_preference_modified_by` FOREIGN KEY (`modified_by`) REFERENCES `user`(`user_id`)
);

CREATE TABLE IF NOT EXISTS `temp_housing_assignment` (
    `temp_housing_assignment_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `volunteer_id` BIGINT NOT NULL,
    `student_id` BIGINT NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `created_by` BIGINT NOT NULL DEFAULT -1,
    `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `modified_by` BIGINT NOT NULL DEFAULT -1,
    UNIQUE(`volunteer_id`, `student_id`),
    CONSTRAINT `fk_temp_housing_assignment_volunteer_id` FOREIGN KEY (`volunteer_id`) REFERENCES `volunteer`(`volunteer_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_temp_housing_assignment_student_id` FOREIGN KEY (`student_id`) REFERENCES `student`(`student_id`) ON DELETE CASCADE,
    CONSTRAINT `fk_temp_housing_assignment_created_by` FOREIGN KEY (`created_by`) REFERENCES `user`(`user_id`),
    CONSTRAINT `fk_temp_housing_assignment_modified_by` FOREIGN KEY (`modified_by`) REFERENCES `user`(`user_id`)
);

-- Only one entry - so no id column
CREATE TABLE IF NOT EXISTS `management` (
    `management_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `does_assignment_start` BOOLEAN NOT NULL,
    `student_registration_start_date` DATE NOT NULL,
    `student_registration_end_date` DATE NOT NULL,
    `announcement` TEXT NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `created_by` BIGINT NOT NULL DEFAULT -1,
    `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `modified_by` BIGINT NOT NULL DEFAULT -1,
    CONSTRAINT `fk_management_created_by` FOREIGN KEY (`created_by`) REFERENCES `user`(`user_id`),
    CONSTRAINT `fk_management_modified_by` FOREIGN KEY (`modified_by`) REFERENCES `user`(`user_id`)
);

-- Initial Data
-- Roles
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

-- A fake user to be referenced by system processes
INSERT INTO user (user_id, username, password, email_address, first_name, last_name, enabled, role_id, created_by, modified_by)
VALUES
(
    -1,
    'system-process',
    'login-disabled',
    'non-existing-email@nodomain.com',
    'System',
    'Process',
    false,
    1,
    -1,
    -1
);

-- Management
INSERT INTO `management` (`does_assignment_start`, `student_registration_start_date`, `student_registration_end_date`, `Announcement`)
VALUES
(
    true,
    '2024-01-01',
    '2024-12-31',
    'This website is open for registration now, and is designed to help Georgia Tech NEW students from China.\n\nPLEASE DO NOT WRITE CHINESE IN ANY TEXT BOX AREA!!! IT WILL CREATE ERROR!\n\nNOTICE - There will be LIMITED temporary housing this year.'
);

-- Major Dropdowns
INSERT INTO `reference` (`reference_type`, `alternate_value`, `value`)
VALUES
( 'Major', 'AE', 'Aerospace Engineering' ),
( 'Major', 'ARCH', 'Architecture' ),
( 'Major', 'BA', 'Business Administration' ),
( 'Major', 'BINF', 'BioInformatics' ),
( 'Major', 'BIOS', 'Biological Sciences' ),
( 'Major', 'BME', 'Biomedical Engineering' ),
( 'Major', 'CE', 'Civil Engineering' ),
( 'Major', 'CS', 'Computer Science' ),
( 'Major', 'CSE', 'Computer Science and Engineering' ),
( 'Major', 'ChBE', 'Chemical and Biomolecular Engineering' ),
( 'Major', 'Chem', 'Chemistry' ),
( 'Major', 'CompE', 'Computer Engineering' ),
( 'Major', 'DM', 'Digital Media' ),
( 'Major', 'EAS', 'Earth and Atmospheric Sciences' ),
( 'Major', 'ECE', 'Electrical and Electronic Engineering' ),
( 'Major', 'ECON', 'Economics' ),
( 'Major', 'EnvironE', 'Environmental Engineering' ),
( 'Major', 'Fin', 'Finance' ),
( 'Major', 'FinE', 'Financial Engineering' ),
( 'Major', 'HCI', 'Human Computer Interaction' ),
( 'Major', 'ISYE', 'Industrial and Systems Engineering' ),
( 'Major', 'ME', 'Mechanical Engineering' ),
( 'Major', 'ML', 'Machine Learning' ),
( 'Major', 'MSE', 'Materials Science and Engineering' ),
( 'Major', 'MT', 'Music Technology' ),
( 'Major', 'Math', 'Mathematics' ),
( 'Major', 'Music', 'Music' ),
( 'Major', 'OR', 'Operations Research' ),
( 'Major', 'Physics', 'Physics' ),
( 'Major', 'QCF', 'Quantitative and Computational Finance' ),
( 'Major', 'RObl', 'Robotics' ),
( 'Major', 'SCE', 'Supply Chain Engineering' ),
( 'Major', 'STAT', 'Statistics' ),
( 'Major', 'StructE', 'Structural Engineering' );

-- Security Questions
INSERT INTO `reference` (`reference_type`, `value`)
VALUES
( 'SecurityQuestion', 'What is your mother''s last name?' ),
( 'SecurityQuestion', 'In what city were you born?' ),
( 'SecurityQuestion', 'What is the name of your first pet?' ),
( 'SecurityQuestion', 'What is your favorite color?' ),
( 'SecurityQuestion', 'What is your favorite movie?' ),
( 'SecurityQuestion', 'What is your favorite book?' ),
( 'SecurityQuestion', 'What is your favorite food?' ),
( 'SecurityQuestion', 'What is your favorite song?' ),
( 'SecurityQuestion', 'What is your favorite sport?' ),
( 'SecurityQuestion', 'What was the name of your elementary school?' ),
( 'SecurityQuestion', 'What year was your father born?' ),
( 'SecurityQuestion', 'What is your favorite TV show?' ),
( 'SecurityQuestion', 'What is your favorite animal?' ),
( 'SecurityQuestion', 'What was the name of your first childhood friend?' ),
( 'SecurityQuestion', 'What is the first name of your best friend?' ),
( 'SecurityQuestion', 'Where is your favorite place to vacation?' ),
( 'SecurityQuestion', 'Where is your dream job?' ),
( 'SecurityQuestion', 'Who is your favorite actor?' ),
( 'SecurityQuestion', 'Who is your favorite actress?' ),
( 'SecurityQuestion', 'Who is your favorite musician?' ),
( 'SecurityQuestion', 'Who is your favorite artist?' ),
( 'SecurityQuestion', 'Who is your favorite author?' ),
( 'SecurityQuestion', 'Who is your favorite scientist?' );

-- Area Dropdowns
INSERT INTO `reference` (`reference_type`, `value`)
VALUES
( 'Area', 'On Campus' ),
( 'Area', 'Off Campus' );

-- Location Dropdowns
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT 'Location', 'East', reference_id FROM reference WHERE `value` = 'On Campus';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT 'Location', 'West', reference_id FROM reference WHERE `value` = 'On Campus';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT 'Location', 'North', reference_id FROM reference WHERE `value` = 'On Campus';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT 'Location', 'South', reference_id FROM reference WHERE `value` = 'On Campus';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT 'Location', 'East', reference_id FROM reference WHERE `value` = 'Off Campus';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT 'Location', 'West', reference_id FROM reference WHERE `value` = 'Off Campus';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT 'Location', 'North', reference_id FROM reference WHERE `value` = 'Off Campus';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT 'Location', 'South', reference_id FROM reference WHERE `value` = 'Off Campus';

-- Apartment Dropdowns
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Brown-625 Techwood Dr NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'East';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Cloudman-661 Techwood Dr NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'East';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Field-711 Techwood Dr NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'East';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Glenn-118 Bobby Dodd Way', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'East';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Hanson-711 Techwood Dr NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'East';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Harrison-660 Williams St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'East';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Hopkins-711 Techwood Dr NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'East';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Matheson-711 Techwood Dr NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'East';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Perry-711 Techwood Dr NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'East';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Smith-630 Williams St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'East';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Towers-112 Bobby Dodd Way', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'East';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Stein House-733 Techwood Dr NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'East';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', '4th Street E-733 Techwood Dr NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'East';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Hayes House-733 Techwood Dr NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'East';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Goldin House-733 Techwood Dr NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'East';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Howell-640 Williams St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'East';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Harris-633 Techwood Dr NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'East';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'North Avenue East-120 North Avenue NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'East';

INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Armstrong-498 8th St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'West';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Caldwell-521 Turner Place NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'West';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Fitten-855 McMillan St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'West';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Folk-531 Turner Place NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'West';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Freeman-835 McMillan St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'West';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Hefner-510 8th St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'West';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Montag-845 McMillan St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'West';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Center Street North/South-939 Hemphill Ave NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'West';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Crecine-900 Hemphill Ave NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'West';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', '8th Street East/South/West-555 8th St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'West';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Fulmer-871 McMillan St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'West';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Maulding-501 6th St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'West';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', '6th Street-501 6th St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'West';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Undergraduate Living Center-580 Turner Place NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'West';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Woodruff North/South-890 Curran St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'West';

INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Graduate Living Center (GLC)-301 10th St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'North';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', '10th and Home-251 10th Street NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'On Campus' and r.`value` = 'North';

INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Biltmore at Midtown-855 West Peachtree St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'East';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Solace-710 Peachtree St NE', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'East';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Alexander on Ponce-144 Ponce De Leon Ave NE', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'East';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', '100 Midtown-100 10th St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'East';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Modera Midtown-95 8th St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'East';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Square on Fifth-848 Spring St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'East';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'The Byron on Peachtree-549 Peachtree St NE', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'East';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Hanover West Peachtree-1010 West Peachtree Street NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'East';

INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Arium West (formerly Tenside)-1000 Northside Dr NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'West';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', '935M-935 Marietta St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'West';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'M Street-950 Marietta St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'West';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Westmar-800 West Marietta St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'West';

INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Centennial Place-526 Centennial Olympic Park Dr', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'South';

INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', '500 Northside Circle (formerly Hartford)-500 Northside Cir NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'North';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Highland Ridge-499 Northside Cir NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'North';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Home Park', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'North';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'The Exchange-470 16th St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'North';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Element-390 17th St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'North';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', 'Alexander at the District-1750 Commerce Dr', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'North';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', '464 Bishop Apartments-464 Bishop St NW', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'North';
INSERT INTO `reference` (`reference_type`, `value`, `parent_reference_id`) SELECT'Apartment', '1016 Lofts-1016 Howell Mill Rd.', r.reference_id FROM reference r JOIN reference r_p ON r_p.reference_id = r.parent_reference_id WHERE r_p.`value` = 'Off Campus' and r.`value` = 'North';


-- Airline Dropdowns
INSERT INTO `reference` (`reference_type`, `alternate_value`, `value`)
VALUES
('Airline', 'AA', 'American Airlines'),
('Airline', 'AC', 'Air Canada'),
('Airline', 'AF', 'Air France'),
('Airline', 'AS', 'Alaska Airlines'),
('Airline', 'AY', 'Finnair'),
('Airline', 'AZ', 'Alitalia'),
('Airline', 'B6', 'JetBlue Airways'),
('Airline', 'BA', 'British Airways'),
('Airline', 'CA', 'Air China'),
('Airline', 'CI', 'China Airlines'),
('Airline', 'CX', 'Cathay Pacific'),
('Airline', 'CZ', 'China Southern Airlines'),
('Airline', 'DL', 'Delta Air Lines'),
('Airline', 'EK', 'Emirates'),
('Airline', 'ET', 'Ethiopian Airlines'),
('Airline', 'F9', 'Frontier Airlines'),
('Airline', 'JL', 'Japan Airlines'),
('Airline', 'KE', 'Korean Air'),
('Airline', 'KL', 'KLM'),
('Airline', 'LH', 'Lufthansa'),
('Airline', 'LX', 'Swiss International Air Lines'),
('Airline', 'MU', 'China Eastern Airlines'),
('Airline', 'NH', 'All Nippon Airways'),
('Airline', 'NK', 'Spirit Airlines'),
('Airline', 'NZ', 'Air New Zealand'),
('Airline', 'OS', 'Austrian Airlines'),
('Airline', 'OZ', 'Asiana Airlines'),
('Airline', 'QF', 'Qantas'),
('Airline', 'QR', 'Qatar Airways'),
('Airline', 'SK', 'Scandinavian Airlines'),
('Airline', 'SQ', 'Singapore Airlines'),
('Airline', 'TK', 'Turkish Airlines'),
('Airline', 'TP', 'TAP Air Portugal'),
('Airline', 'UA', 'United Airlines'),
('Airline', 'WN', 'Southwest Airlines');
