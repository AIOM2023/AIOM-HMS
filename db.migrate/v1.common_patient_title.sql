CREATE TABLE `common_patient_title` (
  `patient_title_id` int NOT NULL AUTO_INCREMENT,
  `patient_title_code` varchar(55) DEFAULT NULL,
  `patient_title_value` varchar(55) DEFAULT NULL,
  `patient_title_description` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`patient_title_id`)
  );