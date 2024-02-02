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
    `enabled` BOOLEAN NOT NULL DEFAULT true,
    `role_id` BIGINT NOT NULL REFERENCES `role`(`role_id`)
);

CREATE TABLE IF NOT EXISTS `user_security_question` (
    `user_security_question_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT NOT NULL REFERENCES `user`(`user_id`),
    `security_question` VARCHAR(255) NOT NULL,
    `security_answer` VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS `student` (
    `student_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT NOT NULL REFERENCES `user`(`user_id`),
    `english_name` VARCHAR(255) NOT NULL,
    `is_new_student` BOOLEAN NOT NULL,
    `student_type` VARCHAR(255) NOT NULL,
    `graduates_from` VARCHAR(255),
    `major` VARCHAR(255) NOT NULL,
    `has_companion` BOOLEAN NOT NULL,
    `wechat_id` VARCHAR(255) NOT NULL,
    `cn_phone_number` VARCHAR(255),
    `us_phone_number` VARCHAR(255),
    `needs_airport_pickup` BOOLEAN NOT NULL,
    `has_flight_information` BOOLEAN,
    `arrival_flight_number` VARCHAR(255),
    `arrival_airline` VARCHAR(255),
    `arrival_datetime` DATETIME,
    `departure_flight_number` VARCHAR(255),
    `departure_airline` VARCHAR(255),
    `departure_datetime` DATETIME,
    `num_lg_luggages` INTEGER,
    `num_sm_luggages` INTEGER,
    `needs_temp_housing` BOOLEAN NOT NULL,
    `num_nights` INTEGER,
    `destination_area` VARCHAR(255),
    `destination_location` VARCHAR(255),
    `destination_apartment` VARCHAR(255),
    `destination_address` VARCHAR(255),
    `contact_name` VARCHAR(255),
    `contact_phone_number` VARCHAR(255),
    `contact_email_address` VARCHAR(255),
    `student_comment` TEXT,
    `admin_comment` TEXT,
    CHECK (needs_airport_pickup is false or has_flight_information is not null),
    CHECK (has_flight_information is null or has_flight_information is false or (arrival_flight_number is not null and arrival_airline is not null and arrival_datetime is not null and departure_flight_number is not null and departure_airline is not null and departure_datetime is not null and num_lg_luggages is not null and num_sm_luggages is not null)),
    CHECK (needs_temp_housing is false or num_nights is not null),
    CHECK (destination_address is null != destination_apartment is null),
    CHECK (destination_area is null = destination_location is null),
    CHECK (destination_location is null = destination_apartment is null)
);

CREATE TABLE IF NOT EXISTS `volunteer` (
    `volunteer_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT NOT NULL REFERENCES `user`(`user_id`),
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
    `provides_rides` BOOLEAN NOT NULL,
    `temp_housing_comment` TEXT,
    CHECK (provides_temp_housing is false or home_address is not null)
);

CREATE TABLE IF NOT EXISTS `adminstrator` (
    `adminstrator_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT NOT NULL REFERENCES `user`(`user_id`),
    `affiliation` VARCHAR(255) NOT NULL,
    `primary_phone_number` VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS `airport_pickup_assignment` (
    `airport_pickup_assignment_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `volunteer_id` BIGINT NOT NULL REFERENCES `volunteer`(`volunteer_id`),
    `student_id` BIGINT NOT NULL REFERENCES `student`(`student_id`),
    UNIQUE(`volunteer_id`, `student_id`)
);

CREATE TABLE IF NOT EXISTS `airport_pickup_preference` (
    `airport_pickup_preference_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `volunteer_id` BIGINT NOT NULL REFERENCES `volunteer`(`volunteer_id`),
    `student_id` BIGINT NOT NULL REFERENCES `student`(`student_id`),
    UNIQUE(`volunteer_id`, `student_id`)
);

CREATE TABLE IF NOT EXISTS `temp_housing_assignment` (
    `temp_housing_assignment_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `volunteer_id` BIGINT NOT NULL REFERENCES `volunteer`(`volunteer_id`),
    `student_id` BIGINT NOT NULL REFERENCES `student`(`student_id`),
    UNIQUE(`volunteer_id`, `student_id`)
);

-- Only one entry - so no id column
CREATE TABLE IF NOT EXISTS `management` (
    `does_assignment_start` BOOLEAN NOT NULL,
    `student_registration_start_date` DATE NOT NULL,
    `student_registration_end_date` DATE NOT NULL,
    `announcement` TEXT
);

CREATE TABLE IF NOT EXISTS `reference` (
    `reference_id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `reference_type` VARCHAR(255) NOT NULL,
    `key` VARCHAR(255) NOT NULL,
    `value` VARCHAR(255) NOT NULL,
    `alternate_value` TEXT,
    `parent_reference_id` BIGINT REFERENCES `reference`(`reference_id`),
    UNIQUE(`reference_type`, `key`)
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

-- Management
INSERT INTO `management` (`does_assignment_start`, `student_registration_start_date`, `student_registration_end_date`, `Announcement`)
VALUES
(
    true,
    '2024-01-01',
    '2024-12-31',
    'Welcome to Apath International'
);

-- Major Dropdowns
INSERT INTO `reference` (`reference_type`, `key`, `value`, `alternate_value`)
VALUES
( 'Major', 'AE', 'Aerospace Engineering', 'AE' ),
( 'Major', 'ARCH', 'Architecture', 'ARCH' ),
( 'Major', 'BA', 'Business Administration', 'BA' ),
( 'Major', 'BINF', 'BioInformatics', 'BINF' ),
( 'Major', 'BIOS', 'Biological Sciences', 'BIOS' ),
( 'Major', 'BME', 'Biomedical Engineering', 'BME' ),
( 'Major', 'CE', 'Civil Engineering', 'CE' ),
( 'Major', 'CS', 'Computer Science', 'CS' ),
( 'Major', 'CSE', 'Computer Science and Engineering', 'CSE' ),
( 'Major', 'ChBE', 'Chemical and Biomolecular Engineering', 'ChBE' ),
( 'Major', 'Chem', 'Chemistry', 'Chem' ),
( 'Major', 'CompE', 'Computer Engineering', 'CompE' ),
( 'Major', 'DM', 'Digital Media', 'DM' ),
( 'Major', 'EAS', 'Earth and Atmospheric Sciences', 'EAS' ),
( 'Major', 'ECE', 'Electrical and Electronic Engineering', 'ECE' ),
( 'Major', 'ECON', 'Economics', 'ECON' ),
( 'Major', 'EnvironE', 'Environmental Engineering', 'EnvironE' ),
( 'Major', 'Fin', 'Finance', 'Fin' ),
( 'Major', 'FinE', 'Financial Engineering', 'FinE' ),
( 'Major', 'HCI', 'Human Computer Interaction', 'HCI' ),
( 'Major', 'ISYE', 'Industrial and Systems Engineering', 'ISYE' ),
( 'Major', 'ME', 'Mechanical Engineering', 'ME' ),
( 'Major', 'ML', 'Machine Learning', 'ML' ),
( 'Major', 'MSE', 'Materials Science and Engineering', 'MSE' ),
( 'Major', 'MT', 'Music Technology', 'MT' ),
( 'Major', 'Math', 'Mathematics', 'Math' ),
( 'Major', 'Music', 'Music', 'Music' ),
( 'Major', 'OR', 'Operations Research', 'OR' ),
( 'Major', 'Physics', 'Physics', 'Physics' ),
( 'Major', 'QCF', 'Quantitative and Computational Finance', 'QCF' ),
( 'Major', 'RObl', 'Robotics', 'RObl' ),
( 'Major', 'SCE', 'Supply Chain Engineering', 'SCE' ),
( 'Major', 'STAT', 'Statistics', 'STAT' ),
( 'Major', 'StructE', 'Structural Engineering', 'StructE' );

-- Security Questions
INSERT INTO `reference` (`reference_type`, `key`, `value`)
VALUES
( 'SecurityQuestion', '1', 'What is your mother''s last name?' ),
( 'SecutiryQuestion', '2', 'In what city were you born?' ),
( 'SecurityQuestion', '3', 'What is the name of your first pet?' ),
( 'SecurityQuestion', '4', 'What is your favorite color?' ),
( 'SecurityQuestion', '5', 'What is your favorite movie?' ),
( 'SecurityQuestion', '6', 'What is your favorite book?' ),
( 'SecurityQuestion', '7', 'What is your favorite food?' ),
( 'SecurityQuestion', '8', 'What is your favorite song?' ),
( 'SecurityQuestion', '9', 'What is your favorite sport?' ),
( 'SecurityQuestion', '10', 'What was the name of your elementary school?' ),
( 'SecurityQuestion', '11', 'What year was your father born?' ),
( 'SecurityQuestion', '12', 'What is your favorite TV show?' ),
( 'SecurityQuestion', '13', 'What is your favorite animal?' ),
( 'SecurityQuestion', '14', 'What was the name of your first childhood friend?' ),
( 'SecurityQuestion', '15', 'What is the first name of your best friend?' ),
( 'SecurityQuestion', '16', 'Where is your favorite place to vacation?' ),
( 'SecurityQuestion', '17', 'Where is your dream job?' ),
( 'SecurityQuestion', '18', 'Who is your favorite actor?' ),
( 'SecurityQuestion', '19', 'Who is your favorite actress?' ),
( 'SecurityQuestion', '20', 'Who is your favorite musician?' ),
( 'SecurityQuestion', '21', 'Who is your favorite artist?' ),
( 'SecurityQuestion', '22', 'Who is your favorite author?' ),
( 'SecurityQuestion', '23', 'Who is your favorite scientist?' );

-- Area Dropdowns
INSERT INTO `reference` (`reference_type`, `key`, `value`)
VALUES
( 'Area', 'onCampus', 'On Campus' ),
( 'Area', 'offCampus', 'Off Campus' );

-- Location Dropdowns
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT 'Location', 'onCampusEast', 'East', reference_id FROM reference WHERE `key` = 'onCampus';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT 'Location', 'onCampusWest', 'West', reference_id FROM reference WHERE `key` = 'onCampus';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT 'Location', 'onCampusNorth', 'North', reference_id FROM reference WHERE `key` = 'onCampus';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT 'Location', 'onCampusSouth', 'South', reference_id FROM reference WHERE `key` = 'onCampus';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT 'Location', 'offCampusEast', 'East', reference_id FROM reference WHERE `key` = 'offCampus';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT 'Location', 'offCampusWest', 'West', reference_id FROM reference WHERE `key` = 'offCampus';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT 'Location', 'offCampusNorth', 'North', reference_id FROM reference WHERE `key` = 'offCampus';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT 'Location', 'offCampusSouth', 'South', reference_id FROM reference WHERE `key` = 'offCampus';

-- Apartment Dropdowns
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '1', 'Brown-625 Techwood Dr NW', reference_id FROM reference WHERE `key` = 'onCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '2', 'Cloudman-661 Techwood Dr NW', reference_id FROM reference WHERE `key` = 'onCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '3', 'Field-711 Techwood Dr NW', reference_id FROM reference WHERE `key` = 'onCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '4', 'Glenn-118 Bobby Dodd Way', reference_id FROM reference WHERE `key` = 'onCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '5', 'Hanson-711 Techwood Dr NW', reference_id FROM reference WHERE `key` = 'onCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '6', 'Harrison-660 Williams St NW', reference_id FROM reference WHERE `key` = 'onCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '7', 'Hopkins-711 Techwood Dr NW', reference_id FROM reference WHERE `key` = 'onCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '8', 'Matheson-711 Techwood Dr NW', reference_id FROM reference WHERE `key` = 'onCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '9', 'Perry-711 Techwood Dr NW', reference_id FROM reference WHERE `key` = 'onCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '10', 'Smith-630 Williams St NW', reference_id FROM reference WHERE `key` = 'onCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '11', 'Towers-112 Bobby Dodd Way', reference_id FROM reference WHERE `key` = 'onCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '12', 'Stein House-733 Techwood Dr NW', reference_id FROM reference WHERE `key` = 'onCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '13', '4th Street E-733 Techwood Dr NW', reference_id FROM reference WHERE `key` = 'onCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '14', 'Hayes House-733 Techwood Dr NW', reference_id FROM reference WHERE `key` = 'onCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '15', 'Goldin House-733 Techwood Dr NW', reference_id FROM reference WHERE `key` = 'onCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '16', 'Howell-640 Williams St NW', reference_id FROM reference WHERE `key` = 'onCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '17', 'Harris-633 Techwood Dr NW', reference_id FROM reference WHERE `key` = 'onCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '18', 'North Avenue East-120 North Avenue NW', reference_id FROM reference WHERE `key` = 'onCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '19', 'Armstrong-498 8th St NW', reference_id FROM reference WHERE `key` = 'onCampusWest';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '20', 'Caldwell-521 Turner Place NW', reference_id FROM reference WHERE `key` = 'onCampusWest';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '21', 'Fitten-855 McMillan St NW', reference_id FROM reference WHERE `key` = 'onCampusWest';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '22', 'Folk-531 Turner Place NW', reference_id FROM reference WHERE `key` = 'onCampusWest';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '23', 'Freeman-835 McMillan St NW', reference_id FROM reference WHERE `key` = 'onCampusWest';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '24', 'Hefner-510 8th St NW', reference_id FROM reference WHERE `key` = 'onCampusWest';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '25', 'Montag-845 McMillan St NW', reference_id FROM reference WHERE `key` = 'onCampusWest';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '26', 'Center Street North/South-939 Hemphill Ave NW', reference_id FROM reference WHERE `key` = 'onCampusWest';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '27', 'Crecine-900 Hemphill Ave NW', reference_id FROM reference WHERE `key` = 'onCampusWest';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '28', '8th Street East/South/West-555 8th St NW', reference_id FROM reference WHERE `key` = 'onCampusWest';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '29', 'Fulmer-871 McMillan St NW', reference_id FROM reference WHERE `key` = 'onCampusWest';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '30', 'Maulding-501 6th St NW', reference_id FROM reference WHERE `key` = 'onCampusWest';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '31', '6th Street-501 6th St NW', reference_id FROM reference WHERE `key` = 'onCampusWest';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '32', 'Undergraduate Living Center-580 Turner Place NW', reference_id FROM reference WHERE `key` = 'onCampusWest';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '33', 'Woodruff North/South-890 Curran St NW', reference_id FROM reference WHERE `key` = 'onCampusWest';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '34', 'Graduate Living Center (GLC)-301 10th St NW', reference_id FROM reference WHERE `key` = 'onCampusNorth';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '35', '10th and Home-251 10th Street NW', reference_id FROM reference WHERE `key` = 'onCampusNorth';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '36', 'Biltmore at Midtown-855 West Peachtree St NW', reference_id FROM reference WHERE `key` = 'offCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '37', 'Solace-710 Peachtree St NE', reference_id FROM reference WHERE `key` = 'offCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '38', 'Alexander on Ponce-144 Ponce De Leon Ave NE', reference_id FROM reference WHERE `key` = 'offCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '39', '100 Midtown-100 10th St NW', reference_id FROM reference WHERE `key` = 'offCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '40', 'Modera Midtown-95 8th St NW', reference_id FROM reference WHERE `key` = 'offCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '41', 'Square on Fifth-848 Spring St NW', reference_id FROM reference WHERE `key` = 'offCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '42', 'The Byron on Peachtree-549 Peachtree St NE', reference_id FROM reference WHERE `key` = 'offCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '43', 'Hanover West Peachtree-1010 West Peachtree Street NW', reference_id FROM reference WHERE `key` = 'offCampusEast';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '44', 'Arium West (formerly Tenside)-1000 Northside Dr NW', reference_id FROM reference WHERE `key` = 'offCampusWest';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '45', '935M-935 Marietta St NW', reference_id FROM reference WHERE `key` = 'offCampusWest';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '46', 'M Street-950 Marietta St NW', reference_id FROM reference WHERE `key` = 'offCampusWest';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '47', 'Westmar-800 West Marietta St NW', reference_id FROM reference WHERE `key` = 'offCampusWest';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '48', 'Centennial Place-526 Centennial Olympic Park Dr', reference_id FROM reference WHERE `key` = 'offCampusSouth';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '49', '500 Northside Circle (formerly Hartford)-500 Northside Cir NW', reference_id FROM reference WHERE `key` = 'offCampusNorth';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '50', 'Highland Ridge-499 Northside Cir NW', reference_id FROM reference WHERE `key` = 'offCampusNorth';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '51', 'Home Park', reference_id FROM reference WHERE `key` = 'offCampusNorth';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '52', 'The Exchange-470 16th St NW', reference_id FROM reference WHERE `key` = 'offCampusNorth';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '53', 'Element-390 17th St NW', reference_id FROM reference WHERE `key` = 'offCampusNorth';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '54', 'Alexander at the District-1750 Commerce Dr', reference_id FROM reference WHERE `key` = 'offCampusNorth';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '55', '464 Bishop Apartments-464 Bishop St NW', reference_id FROM reference WHERE `key` = 'offCampusNorth';
INSERT INTO `reference` (`reference_type`, `key`, `value`, `parent_reference_id`) SELECT'Apartment', '56', '1016 Lofts-1016 Howell Mill Rd.', reference_id FROM reference WHERE `key` = 'offCampusNorth';

-- Airline Dropdowns
INSERT INTO `reference` (`reference_type`, `key`, `value`, `alternate_value`)
VALUES
('Airline', 'AA', 'American Airlines', 'AAL' ),
('Airline', 'AC', 'Air Canada', 'ACA' ),
('Airline', 'AF', 'Air France', 'AFR' ),
('Airline', 'AS', 'Alaska Airlines', 'ASA' ),
('Airline', 'AY', 'Finnair', 'FIN' ),
('Airline', 'AZ', 'Alitalia', 'AZA' ),
('Airline', 'B6', 'JetBlue Airways', 'JBU' ),
('Airline', 'BA', 'British Airways', 'BAW' ),
('Airline', 'CA', 'Air China', 'CCA' ),
('Airline', 'CI', 'China Airlines', 'CAL' ),
('Airline', 'CX', 'Cathay Pacific', 'CPA' ),
('Airline', 'CZ', 'China Southern Airlines', 'CSN' ),
('Airline', 'DL', 'Delta Air Lines', 'DAL' ),
('Airline', 'EK', 'Emirates', 'UAE' ),
('Airline', 'ET', 'Ethiopian Airlines', 'ETH' ),
('Airline', 'F9', 'Frontier Airlines', 'FFT' ),
('Airline', 'JL', 'Japan Airlines', 'JAL' ),
('Airline', 'KE', 'Korean Air', 'KAL' ),
('Airline', 'KL', 'KLM', 'KLM' ),
('Airline', 'LH', 'Lufthansa', 'DLH' ),
('Airline', 'LX', 'Swiss International Air Lines', 'SWR' ),
('Airline', 'MU', 'China Eastern Airlines', 'CES' ),
('Airline', 'NH', 'All Nippon Airways', 'ANA' ),
('Airline', 'NK', 'Spirit Airlines', 'NKS' ),
('Airline', 'NZ', 'Air New Zealand', 'ANZ' ),
('Airline', 'OS', 'Austrian Airlines', 'AUA' ),
('Airline', 'OZ', 'Asiana Airlines', 'AAR' ),
('Airline', 'QF', 'Qantas', 'QFA' ),
('Airline', 'QR', 'Qatar Airways', 'QTR' ),
('Airline', 'SK', 'Scandinavian Airlines', 'SAS' ),
('Airline', 'SQ', 'Singapore Airlines', 'SIA' ),
('Airline', 'TK', 'Turkish Airlines', 'THY' ),
('Airline', 'TP', 'TAP Air Portugal', 'TAP' ),
('Airline', 'UA', 'United Airlines', 'UAL' ),
('Airline', 'WN', 'Southwest Airlines', 'SWA' );
