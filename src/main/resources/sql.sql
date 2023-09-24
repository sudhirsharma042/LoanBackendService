create database loan;
use loan;
CREATE TABLE `customer` ( `customer_id` int NOT NULL ,`first_name` VARCHAR(20) NOT NULL ,
                          `last_name` VARCHAR(20) NOT NULL , `gender` VARCHAR(20) NOT NULL ,
                          `email` VARCHAR(20) NOT NULL ,
                          PRIMARY KEY (`customer_id`));
CREATE TABLE `loan` ( `loan_id` int NOT NULL ,`lender_id` VARCHAR(20) NOT NULL ,
                      `customer_id` int not null,
                      `loan_amount` float NOT NULL , `remaining_amount` float NOT NULL ,
                      `payment_date` date NOT NULL , `interest_per_day` float NOT NULL ,
                      `due_date` date NOT NULL , `penalty_per_day` float NOT NULL ,
                      `cancel` BOOLEAN,
                      PRIMARY KEY (`loan_id`),
                      FOREIGN KEY (customer_id) REFERENCES customer(customer_id));