CREATE TABLE `common_authorization` (
  `auth_id` int NOT NULL AUTO_INCREMENT,
  `auth_code` varchar(25) NOT NULL,
  `auth_name` varchar(155) NOT NULL,
  `designation` int NOT NULL,
  `reference_code` varchar(25) NOT NULL,
  `op_conces` int NOT NULL,
  `op_credit` int NOT NULL,
  `op_canc` int NOT NULL,
  `op_phar_conc` int NOT NULL,
  `op_phar_due` int NOT NULL,
  `ip_conces` int NOT NULL,
  `ip_credit` int NOT NULL,
  `ip_canc` int NOT NULL,
  `pat_bil_conv` int NOT NULL,
  `fnb_conces` int NOT NULL,
  `vouch_appr` int NOT NULL,
  `mod_appr_trans` int NOT NULL,
  `disch_wo_settl` int NOT NULL,
  `fnb_due` int NOT NULL,
  `pat_reg` int NOT NULL,
  `created_date` datetime NOT NULL,
  `created_by` varchar(155) NOT NULL,
  `modified_date` datetime NOT NULL,
  `modified_by` varchar(155) NOT NULL,
  `del_auth` int NOT NULL,
  `in_active` int NOT NULL,
  PRIMARY KEY (`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

CREATE TABLE `common_system_parameters` (
  `param_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `comm_code` varchar(50) NOT NULL,
  `comm_value` varchar(50) NOT NULL,
  `comm_desc` varchar(150) NOT NULL,
  `in_active` int(1) NOT NULL,
 PRIMARY KEY (`param_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE `company_info` (
  `cmp_id` int NOT NULL AUTO_INCREMENT,
  `company_name` varchar(150) NOT NULL,
  `company_address` varchar(450) NOT NULL,
  `company_contact_no` varchar(50) NOT NULL,
  `tin` varchar(50) NOT NULL,
  `logo` varchar(900) NOT NULL,
PRIMARY KEY (`cmp_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;