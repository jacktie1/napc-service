# The script is used to generate test data in the MySQL database for the
# purpose of testing the functionality of the web application.

import string
import mysql.connector
import argparse
import random
import datetime

def connect_to_mysql(database, username, password, hostname, port):
    try:
        # Connect to MySQL
        connection = mysql.connector.connect(
            host=hostname,  # Change this to your MySQL server host
            user=username,
            password=password,
            database=database,
            port=port
        )

        if connection.is_connected():
            print("Connected to MySQL database")
            return connection
        else:
            print("Failed to connect to MySQL database")

    except mysql.connector.Error as e:
        print(f"Error connecting to MySQL database: {e}")

    return None

def get_role_id(connection, role_name):
    try:
        cursor = connection.cursor(dictionary=True)

        # Execute SQL query
        cursor.execute("SELECT role_id FROM role WHERE name = %s", (role_name,))

        # Fetch the first row
        role_id = cursor.fetchone()

        cursor.close()

        return role_id['role_id']

    except mysql.connector.Error as e:
        print(f"Error executing SQL query: {e}")

def get_data_from_reference_table(connection):
    try:
        cursor = connection.cursor(dictionary=True)

        # Execute SQL query
        cursor.execute("SELECT * FROM reference")

        # Fetch all rows
        references = cursor.fetchall()

        cursor.close()

        return references

    except mysql.connector.Error as e:
        print(f"Error executing SQL query: {e}")

def get_references_by_type(references, reference_type):
    references_by_type = []

    for reference in references:
        if reference['reference_type'] == reference_type:
            references_by_type.append(reference)

    return references_by_type

def generate_name():
    # Generate random first name
    name_length = random.randint(3, 8)
    first_letter = random.choice(string.ascii_uppercase)
    remaining_letters = ''.join(random.choices(string.ascii_lowercase, k=name_length - 1))
    return first_letter + remaining_letters

def generate_email(first_name, last_name):
    email = first_name.lower() + '_' + last_name.lower() + '@example.com'
    return email

def generate_gender():
    return random.choice(['Male', 'Female'])

def generate_gender_preference():
    return random.choices(['Male', 'Female', 'No Preference'], weights=[2, 2, 6])[0]

def generate_phone_number():
    return ''.join(random.choices(string.digits, k=10))

def generate_student_type():
    return random.choices(['Undergraduate Student', 'Graduate Student', 'Visiting Student', 'Other'], weights=[3, 5, 1, 1])[0]

def generate_custom_major():
    return random.choice([
        'Literature',
        'History',
        'English',
        'Technology Management',
        'Chinese Language',
        'Agricultural Science',
        'Linguistics',
        'Economics',
        'Quantum Physics',
        'Astronomy',
        'Political Science',
        'Philosophy',
        'Psychology',
        'Sociology',
        'Anthropology',
        ])

def generate_custom_airline():
    return generate_name() + ' Airlines'

def generate_custom_address():
    street_number = random.randint(100, 999)
    street_name = generate_name() + ' ' + random.choice(['St', 'Rd', 'Ave', 'Blvd'])
    postal_code = random.randint(30000, 39999)
    return f'{street_number} {street_name}, Atlanta, GA {postal_code}'

def generate_quantity():
    return random.randint(1, 9)

def generate_boolean(true_weight):
    return random.choices([True, False], weights=[true_weight, 10 - true_weight])[0]

def generate_comment():
    return ''.join(random.choices(string.ascii_letters + string.digits, k=50))

def generate_datetime(later_than=None):
    # in form of 'YYYY-MM-DD HH:MM:SS'
    # an if later_than date is provided, the generated date will be later than the provided date
    if later_than:
        later_than_date = datetime.datetime.strptime(later_than, '%Y-%m-%d %H:%M:%S')
        later_than_date += datetime.timedelta(days=random.randint(1, 7))
        later_than_date += datetime.timedelta(hours=random.randint(1, 23))
        later_than_date += datetime.timedelta(minutes=random.randint(1, 59))
        later_than_date += datetime.timedelta(seconds=random.randint(1, 59))
        return later_than_date.strftime('%Y-%m-%d %H:%M:%S')
    else:
        year = 2024
        month = random.randint(7, 9)
        day = random.randint(1, 28)
        hour = random.randint(0, 23)
        minute = random.randint(0, 59)
        second = random.randint(0, 59)

        return f'{year}-{month:02d}-{day:02d} {hour:02d}:{minute:02d}:{second:02d}'

def generate_date(later_than=None):
    # in form of 'YYYY-MM-DD'
    # an if later_than date is provided, the generated date will be later than the provided date
    if later_than:
        later_than_date = datetime.datetime.strptime(later_than, '%Y-%m-%d')
        later_than_date += datetime.timedelta(days=random.randint(1, 7))
        return later_than_date.strftime('%Y-%m-%d')
    else:
        year = 2024
        month = random.randint(7, 9)
        day = random.randint(1, 28)

        return f'{year}-{month:02d}-{day:02d}'
        

def generate_major(major_references):
    major = random.choice(major_references)
    return major['reference_id']

def generate_airline(airline_references):
    airline = random.choice(airline_references)
    return airline['reference_id']

def generate_apartment(apartment_references):
    apartment = random.choice(apartment_references)
    return apartment['reference_id']

def generate_flight_number():
    prefix_length = random.randint(2,3)
    prefix = ''.join(random.choices(string.ascii_uppercase, k=prefix_length))
    suffix_length = random.randint(3,4)
    suffix = ''.join(random.choices(string.digits, k=suffix_length))
    return prefix + suffix

def generate_username():
    # 10 characters long, digits and lowercase letters
    return ''.join(random.choices(string.ascii_lowercase + string.digits, k=10))

def generate_password():
    # the password needs to be hard-coded so that we can log in as the generated user
    return '$2a$10$iYb71PFMW8turJwoXUk1FO8AAcN2dhsYXQT1q5VyRJeNxYsXTV8cW'

def generate_user(connection, role_id):
    cursor = connection.cursor()

    # User table
    username = generate_username()
    password = generate_password()
    first_name = generate_name()
    last_name = generate_name()
    email = generate_email(first_name, last_name)
    gender = generate_gender()

    cursor.execute("INSERT INTO user (username, password, first_name, last_name, gender, email_address, role_id) VALUES (%s, %s, %s, %s, %s, %s, %s)",
            (username, password, first_name, last_name, gender, email, role_id))
    
    return cursor.lastrowid


def generate_volunteer_data(connection, role_volunteer):
    cursor = connection.cursor()

    # User table
    user_id = generate_user(connection, role_volunteer)

    # Volunteer table
    affiliation = generate_name() + ' ' + generate_name()
    primary_phone_number = generate_phone_number()
    secondary_phone_number = generate_phone_number()
    wechat_id = generate_username()

    # Airport pickup
    provides_airport_pickup = generate_boolean(9)

    if provides_airport_pickup:
        car_manufacturer = generate_name()
        car_model = generate_name()
        num_car_seats = generate_quantity()
        num_max_lg_luggages = generate_quantity()
        num_max_trips = generate_quantity()
        airport_pickup_comment = generate_comment()
    else:
        car_manufacturer = None
        car_model = None
        num_car_seats = None
        num_max_lg_luggages = None
        num_max_trips = None
        airport_pickup_comment = None

    # Temporary housing
    provides_temp_housing = generate_boolean(5)

    if provides_temp_housing:
        home_address = generate_custom_address()
        num_max_students_hosted = generate_quantity()
        temp_housing_start_date = generate_date()
        temp_housing_end_date = generate_date(temp_housing_start_date)
        num_double_beds = generate_quantity()
        num_single_beds = generate_quantity()
        gender_preference = generate_gender_preference()
        provides_ride = generate_boolean(8)
        temp_housing_comment = generate_comment()
    else:
        home_address = None
        num_max_students_hosted = None
        temp_housing_start_date = None
        temp_housing_end_date = None
        num_double_beds = None
        num_single_beds = None
        gender_preference = None
        provides_ride = None
        temp_housing_comment = None

    cursor.execute("""INSERT INTO volunteer(
                    user_id, 
                    affiliation,
                    primary_phone_number,
                    secondary_phone_number,
                    wechat_id,
                    provides_airport_pickup,
                    car_manufacturer,
                    car_model,
                    num_car_seats,
                    num_max_lg_luggages,
                    num_max_trips,
                    airport_pickup_comment,
                    provides_temp_housing,
                    home_address,
                    num_max_students_hosted,
                    temp_housing_start_date,
                    temp_housing_end_date,
                    num_double_beds,
                    num_single_beds,
                    gender_preference,
                    provides_ride,
                    temp_housing_comment)
                        VALUES (""" + "%s, " * 21 + "%s)", (
                    user_id, 
                    affiliation,
                    primary_phone_number,
                    secondary_phone_number,
                    wechat_id,
                    provides_airport_pickup,
                    car_manufacturer,
                    car_model,
                    num_car_seats,
                    num_max_lg_luggages,
                    num_max_trips,
                    airport_pickup_comment,
                    provides_temp_housing,
                    home_address,
                    num_max_students_hosted,
                    temp_housing_start_date,
                    temp_housing_end_date,
                    num_double_beds,
                    num_single_beds,
                    gender_preference,
                    provides_ride,
                    temp_housing_comment
                        ))

def generate_student_data(connection, role_student, major_references, airline_references, apartment_references):
        cursor = connection.cursor()

        # User table
        user_id = generate_user(connection, role_student)

        english_name = generate_name()
        is_new_student = generate_boolean(8)
        student_type = generate_student_type()
        if generate_boolean(9):
            major_reference_id = generate_major(major_references)
            custom_major = None
        else:
            major_reference_id = None
            custom_major = generate_custom_major()

        has_companion = generate_boolean(2)
        wechat_id = generate_username()
        cn_phone_number = generate_phone_number()
        us_phone_number = generate_phone_number()

        # Flight information
        needs_airport_pickup = generate_boolean(9)

        if needs_airport_pickup:
            has_flight_information = generate_boolean(9)
        else:
            has_flight_information = None

        if has_flight_information:
            departure_flight_number = generate_flight_number()

            if generate_boolean(9):
                departure_airline_reference_id = generate_airline(airline_references)
                custom_departure_airline = None
            else:
                departure_airline_reference_id = None
                custom_departure_airline = generate_custom_airline()

            departure_datetime = generate_datetime()

            arrival_flight_number = generate_flight_number()

            if generate_boolean(9):
                arrival_airline_reference_id = generate_airline(airline_references)
                custom_arrival_airline = None
            else:
                arrival_airline_reference_id = None
                custom_arrival_airline = generate_custom_airline()

            arrival_datetime = generate_datetime(departure_datetime)

            num_lg_luggages = generate_quantity()
            num_sm_luggages = generate_quantity()
        else:
            departure_flight_number = None
            departure_airline_reference_id = None
            custom_departure_airline = None
            departure_datetime = None
            arrival_flight_number = None
            arrival_airline_reference_id = None
            custom_arrival_airline = None
            arrival_datetime = None
            num_lg_luggages = None
            num_sm_luggages = None

        # Housing information
        needs_temp_housing = generate_boolean(5)
        student_comment = generate_comment()

        if generate_boolean(4):
            apartment_reference_id = generate_apartment(apartment_references)
            custom_destination_address = None
        else:
            apartment_reference_id = None
            custom_destination_address = generate_custom_address()

        if needs_temp_housing:
            num_nights = random.randint(1, 5)
            contact_name = None
            contact_phone_number = None
            contact_email_address = None
        else:
            num_nights = None
            contact_name = generate_name() + ' ' + generate_name()
            contact_phone_number = generate_phone_number()
            contact_email_address = generate_email(contact_name.split()[0], contact_name.split()[1])

        cursor.execute("""INSERT INTO student(
                    user_id,
                    english_name,
                    is_new_student,
                    student_type,
                    major_reference_id,
                    custom_major,
                    has_companion,
                    wechat_id,
                    cn_phone_number,
                    us_phone_number,
                    needs_airport_pickup,
                    has_flight_information,
                    departure_flight_number,
                    departure_airline_reference_id,
                    custom_departure_airline,
                    departure_datetime,
                    arrival_flight_number,
                    arrival_airline_reference_id,
                    custom_arrival_airline,
                    arrival_datetime,
                    num_lg_luggages,
                    num_sm_luggages,
                    needs_temp_housing,
                    student_comment,
                    apartment_reference_id,
                    custom_destination_address,
                    num_nights,
                    contact_name,
                    contact_phone_number,
                    contact_email_address)
                        VALUES (""" + "%s, " * 29 + "%s)", (
                    user_id,
                    english_name,
                    is_new_student,
                    student_type,
                    major_reference_id,
                    custom_major,
                    has_companion,
                    wechat_id,
                    cn_phone_number,
                    us_phone_number,
                    needs_airport_pickup,
                    has_flight_information,
                    departure_flight_number,
                    departure_airline_reference_id,
                    custom_departure_airline,
                    departure_datetime,
                    arrival_flight_number,
                    arrival_airline_reference_id,
                    custom_arrival_airline,
                    arrival_datetime,
                    num_lg_luggages,
                    num_sm_luggages,
                    needs_temp_housing,
                    student_comment,
                    apartment_reference_id,
                    custom_destination_address,
                    num_nights,
                    contact_name,
                    contact_phone_number,
                    contact_email_address
                        ))
                       

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='Connect to MySQL database')
    parser.add_argument('--username', required=True, help='MySQL username')
    parser.add_argument('--password', required=True, help='MySQL password')
    parser.add_argument('--database', required=False, default='apath', help='MySQL database name')
    parser.add_argument('--hostname', required=False, default='localhost', help='MySQL hostname')
    parser.add_argument('--port', required=False, default=3306, help='MySQL port')
    parser.add_argument('--num_volunteers', required=False, default=100, help='Number of volunteers to generate')
    parser.add_argument('--num_students', required=False, default=100, help='Number of students to generate')
    args = parser.parse_args()
    
    db_connection = connect_to_mysql(args.database, args.username, args.password, args.hostname, args.port)

    if not db_connection:
        print("No database connection. Exiting...")
        exit(1)

    references = get_data_from_reference_table(db_connection)
    major_references = get_references_by_type(references, 'Major')
    airline_references = get_references_by_type(references, 'Airline')
    apartment_references = get_references_by_type(references, 'Apartment')

    role_volunteer = get_role_id(db_connection, 'Volunteer')
    role_student = get_role_id(db_connection, 'Student')

    try:
        print(f"Generating {args.num_volunteers} volunteers...")
        for _ in range(args.num_volunteers):
            # Generate test data for volunteers
            generate_volunteer_data(db_connection, role_volunteer)

        print('Volunteers generated')

        print(f"Generating {args.num_students} students...")
        for _ in range(args.num_students):
            # Generate test data for students
            generate_student_data(db_connection, role_student, major_references, airline_references, apartment_references)

        print('Students generated')

        db_connection.commit()
    except mysql.connector.Error as e:
        print(f"Error executing SQL query: {e}")
        db_connection.rollback()

    # Do something with the database connection
    db_connection.close()  # Close the connection when done
