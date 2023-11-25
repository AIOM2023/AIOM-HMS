CREATE TABLE `common_add_authorization` (
 `add_authorization_id` int NOT NULL AUTO_INCREMENT,
 `add_authorization_designation` varchar(55) DEFAULT NULL,
 `add_authorization_code` varchar(55) DEFAULT NULL,
 `add_authorization_name` varchar(55) DEFAULT NULL,
 `add_authorization_for` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`add_authorization_id`)
 );