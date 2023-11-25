 CREATE TABLE `common_admsn_purpose` (  
 `admsn_purpose_id` int NOT NULL AUTO_INCREMENT, 
 `admsn_purpose_code` varchar(55) DEFAULT NULL,
 `admsn_purpose_value` varchar(55) DEFAULT NULL, 
 `admsn_purpose_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`admsn_purpose_id`)
 );