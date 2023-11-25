CREATE TABLE `common_lab_sample` (
 `lab_sample_id` int NOT NULL AUTO_INCREMENT,
 `lab_sample_code` varchar(55) DEFAULT NULL,
 `lab_sample_value` varchar(55) DEFAULT NULL,
 `lab_sample_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`lab_sample_id`)
 );