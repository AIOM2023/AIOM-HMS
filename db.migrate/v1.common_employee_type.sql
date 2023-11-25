 CREATE TABLE `common_employee_type` (
 `employee_type_id` int NOT NULL AUTO_INCREMENT,
 `employee_type_code` varchar(55) DEFAULT NULL,
 `employee_type_value` varchar(55) DEFAULT NULL,
 `employee_type_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`employee_type_id`)
 );