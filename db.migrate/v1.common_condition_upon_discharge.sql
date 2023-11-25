CREATE TABLE `common_condition_upon_discharge` (
 `condition_upon_discharge_id` int NOT NULL AUTO_INCREMENT,
 `condition_upon_discharge_code` varchar(55) DEFAULT NULL,
 `condition_upon_discharge_value` varchar(55) DEFAULT NULL,
 `condition_upon_discharge_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`condition_upon_discharge_id`)
 );