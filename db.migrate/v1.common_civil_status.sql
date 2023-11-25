 CREATE TABLE `common_civil_status` (
 `civil_status_id` int NOT NULL AUTO_INCREMENT,
 `civil_status_code` varchar(55) DEFAULT NULL,
 `civil_status_value` varchar(55) DEFAULT NULL,
 `civil_status_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`civil_status_id`)
 );