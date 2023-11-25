 CREATE TABLE `common_hospital_info` (
 `hospital_info_id` int NOT NULL AUTO_INCREMENT,
 `hospital_info_name` varchar(55) DEFAULT NULL,
 `hospital_info_address` varchar(55) DEFAULT NULL,
 `hospital_info_contact_no` varchar(55) DEFAULT NULL,
 `hospital_info_tin_no` varchar(55) DEFAULT NULL,
 `hospital_logo` blob DEFAULT NULL,
 PRIMARY KEY (`hospital_info_id`)
 );

