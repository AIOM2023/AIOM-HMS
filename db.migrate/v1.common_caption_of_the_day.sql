CREATE TABLE `common_caption_of_the_day` (
 `caption_of_the_day_id` int NOT NULL AUTO_INCREMENT,
 `caption_of_the_day_code` varchar(55) DEFAULT NULL,
 `caption_of_the_day_value` varchar(55) DEFAULT NULL,
 `caption_of_the_day_description` varchar(55) DEFAULT NULL,
 PRIMARY KEY (`caption_of_the_day_id`)
 );