CREATE TABLE `common_add_authorization` (
 `add_authorization_id` int NOT NULL AUTO_INCREMENT,
 `add_authorization_designation` varchar(55) DEFAULT NULL,
 `add_authorization_code` varchar(55) DEFAULT NULL,
 `add_authorization_name` varchar(55) DEFAULT NULL,
 `add_authorization_for` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`add_authorization_id`)
 );


CREATE TABLE `common_gender` (
 `gender_id` int NOT NULL AUTO_INCREMENT,
 `gender_code` varchar(55) DEFAULT NULL,
 `gender_value` varchar(55) DEFAULT NULL,
 `gender_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`gender_id`)
 );

 CREATE TABLE `common_hospital_info` (
 `hospital_info_id` int NOT NULL AUTO_INCREMENT,
 `hospital_info_code` varchar(55) DEFAULT NULL,
 `hospital_info_value` varchar(55) DEFAULT NULL,
 `hospital_info_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`hospital_info_id`)
 );

 CREATE TABLE `common_item_master` (
 `item_master_id` int NOT NULL AUTO_INCREMENT,
 `item_master_code` varchar(55) DEFAULT NULL,
 `item_master_value` varchar(55) DEFAULT NULL,
 `item_master_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`item_master_id`)
 );

 CREATE TABLE `common_lab_sample` (
 `lab_sample_id` int NOT NULL AUTO_INCREMENT,
 `lab_sample_code` varchar(55) DEFAULT NULL,
 `lab_sample_value` varchar(55) DEFAULT NULL,
 `lab_sample_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`lab_sample_id`)
 );

 CREATE TABLE `common_lab_test_name` (
 `lab_test_name_id` int NOT NULL AUTO_INCREMENT,
 `lab_test_name_code` varchar(55) DEFAULT NULL,
 `lab_test_name_value` varchar(55) DEFAULT NULL,
 `lab_test_name_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`lab_test_name_id`)
 );

 CREATE TABLE `common_medicine_type` (
 `medicine_type_id` int NOT NULL AUTO_INCREMENT,
 `medicine_type_code` varchar(55) DEFAULT NULL,
 `medicine_type_value` varchar(55) DEFAULT NULL,
 `medicine_type_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`medicine_type_id`)
 );

 CREATE TABLE `common_method` (
 `method_id` int NOT NULL AUTO_INCREMENT,
 `method_code` varchar(55) DEFAULT NULL,
 `method_value` varchar(55) DEFAULT NULL,
 `method_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`method_id`)
 );


 CREATE TABLE `common_nurse_station` (
 `nurse_station_id` int NOT NULL AUTO_INCREMENT,
 `nurse_station_code` varchar(55) DEFAULT NULL,
 `nurse_station_value` varchar(55) DEFAULT NULL,
 `nurse_station_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`nurse_station_id`)
 );

 CREATE TABLE `common_occupation` (
 `occupation_id` int NOT NULL AUTO_INCREMENT,
 `occupation_code` varchar(55) DEFAULT NULL,
 `occupation_value` varchar(55) DEFAULT NULL,
 `occupation_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`occupation_id`)
 );

 CREATE TABLE `common_patient_title` (
 `patient_title_id` int NOT NULL AUTO_INCREMENT,
 `patient_title_code` varchar(55) DEFAULT NULL,
 `patient_title_value` varchar(55) DEFAULT NULL,
 `patient_title_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`patient_title_id`)
 );

 CREATE TABLE `common_payment_type` (
 `payment_type_id` int NOT NULL AUTO_INCREMENT,
 `payment_type_code` varchar(55) DEFAULT NULL,
 `payment_type_value` varchar(55) DEFAULT NULL,
 `payment_type_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`payment_type_id`)
 );

 CREATE TABLE `common_reason_for_discount` (
 `reason_for_discount_id` int NOT NULL AUTO_INCREMENT,
 `reason_for_discount_code` varchar(55) DEFAULT NULL,
 `reason_for_discount_value` varchar(55) DEFAULT NULL,
 `reason_for_discount_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`reason_for_discount_id`)
 );

 CREATE TABLE `common_religion` (
 `religion_id` int NOT NULL AUTO_INCREMENT,
 `religion_code` varchar(55) DEFAULT NULL,
 `religion_value` varchar(55) DEFAULT NULL,
 `religion_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`religion_id`)
 );

 CREATE TABLE `common_specialization` (
 `specialization_id` int NOT NULL AUTO_INCREMENT,
 `specialization_code` varchar(55) DEFAULT NULL,
 `specialization_value` varchar(55) DEFAULT NULL,
 `specialization_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`specialization_id`)
 );

 CREATE TABLE `common_tariff` (
 `tariff_id` int NOT NULL AUTO_INCREMENT,
 `tariff_code` varchar(55) DEFAULT NULL,
 `tariff_value` varchar(55) DEFAULT NULL,
 `tariff_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`tariff_id`)
 );

 CREATE TABLE `common_test_specimen` (
 `test_specimen_id` int NOT NULL AUTO_INCREMENT,
 `test_specimen_code` varchar(55) DEFAULT NULL,
 `test_specimen_value` varchar(55) DEFAULT NULL,
 `test_specimen_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`test_specimen_id`)
 );

 CREATE TABLE `common_title_name` (
 `title_name_id` int NOT NULL AUTO_INCREMENT,
 `title_name_code` varchar(55) DEFAULT NULL,
 `title_name_value` varchar(55) DEFAULT NULL,
 `title_name_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`title_name_id`)
 );

 CREATE TABLE `common_units` (
 `units_id` int NOT NULL AUTO_INCREMENT,
 `units_code` varchar(55) DEFAULT NULL,
 `units_value` varchar(55) DEFAULT NULL,
 `units_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`units_id`)
 );

 CREATE TABLE `common_vacutainer` (
 `vacutainer_id` int NOT NULL AUTO_INCREMENT,
 `vacutainer_code` varchar(55) DEFAULT NULL,
 `vacutainer_value` varchar(55) DEFAULT NULL,
 `vacutainer_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`vacutainer_id`)
 );