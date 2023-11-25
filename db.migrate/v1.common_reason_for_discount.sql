CREATE TABLE `common_reason_for_discount` (
 `reason_for_discount_id` int NOT NULL AUTO_INCREMENT,
 `reason_for_discount_code` varchar(55) DEFAULT NULL,
 `reason_for_discount_value` varchar(55) DEFAULT NULL,
 `reason_for_discount_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`reason_for_discount_id`)
 );

