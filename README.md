# Adv-Software-Project

POPSQL Tables

NOTE: REMEMBER TO RUN EACH TABLE SEPARATELY!!!!!!!!
#################################

CREATE TABLE guest (
    guest_ID INT AUTO_INCREMENT,
    firstname VARCHAR(20),
    lastname VARCHAR(20),
    username VARCHAR(10) UNIQUE,
    passw VARCHAR(10),
    email VARCHAR (30),
    phone_number VARCHAR(8),
    PRIMARY KEY (guest_ID)
);
CREATE TABLE admin (
    admin_ID INT AUTO_INCREMENT,
    firstname VARCHAR(20),
    lastname VARCHAR(20),
    username VARCHAR(10) UNIQUE,
    passw VARCHAR(10),
    PRIMARY KEY (admin_ID) 
);

CREATE TABLE room (
    room_ID INT PRIMARY KEY,
    room_type VARCHAR(6),
    price INT,
    available BOOLEAN,
    occupied_by VARCHAR(10)
);
CREATE TABLE booking (
    booking_ID INT AUTO_INCREMENT,
    guest_username VARCHAR(10),
    room_ID INT,
    number_of_days INT,
    start_date DATE,
    end_date DATE,
    PRIMARY KEY (booking_ID)
);

CREATE TABLE staff (
    staff_ID INT AUTO_INCREMENT,
    firstname VARCHAR(20),
    lastname VARCHAR(20),
    username VARCHAR(10) UNIQUE,
    passw VARCHAR(10),
    room_assigned INT,
    rooms_cleaned_today INT,
    service_assigned VARCHAR(20),
    available BOOLEAN,
    PRIMARY KEY (staff_ID) 
);

CREATE TABLE service (
service_ID INT AUTO_INCREMENT,
service_name VARCHAR(20),
service_cost INT,
PRIMARY KEY (service_ID)
);

CREATE TABLE fees (
    receipt_ID INT AUTO_INCREMENT,
    guest_username VARCHAR(10),
    price_of_booking INT,
    days_car_rented INT,
    total_car_rental_price INT,
    price_of_requested_services INT,
    total INT,
    paid BOOLEAN,
    PRIMARY KEY (receipt_ID)
);

CREATE TABLE reviews (
    review_ID INT AUTO_INCREMENT,
    guest_username VARCHAR(10),
    rating_out_of_five INT,
    review VARCHAR(1000),
    PRIMARY KEY (review_ID)
);
CREATE TABLE requestedHousekeeping (
    request_ID INT auto_increment,
    room_ID INT,
    done BOOLEAN,
    PRIMARY KEY (request_ID)
);
CREATE TABLE requestedServices (
    request_ID INT auto_increment,
    room_ID INT,
    service_name VARCHAR(20),
    done BOOLEAN,
    PRIMARY KEY (request_ID)
);

CREATE TABLE marketingStrategy (
    strategy_ID INT AUTO_INCREMENT,
    strategy_name VARCHAR(20),
    strategy_description VARCHAR(2000),
    strategy_feedback VARCHAR(2000),
    PRIMARY KEY (strategy_ID)
);
CREATE TABLE checking (
    checking_ID INT AUTO_INCREMENT,
    room_ID INT,
    checked BOOLEAN,
    PRIMARY KEY (checking_ID)
);
