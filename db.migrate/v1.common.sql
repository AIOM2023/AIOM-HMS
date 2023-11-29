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
  `param_id` int NOT NULL AUTO_INCREMENT,
  `comm_code` varchar(50) NOT NULL,
  `comm_value` varchar(50) NOT NULL,
  `comm_desc` varchar(150) NOT NULL,
  `in_active` int NOT NULL,
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

CREATE TABLE `department` (
  `department_id` int NOT NULL AUTO_INCREMENT,
  `dept_code` varchar(100) NOT NULL,
  `dept_name` varchar(150) NOT NULL,
  `created_date` datetime NOT NULL,
  `created_by` varchar(155) NOT NULL,
  `modified_date` datetime NOT NULL,
  `modified_by` varchar(155) NOT NULL,
 `del_dept` int NOT NULL,
  `in_active` int NOT NULL,
PRIMARY KEY (`department_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE `designation` (
  `designation_id` int NOT NULL AUTO_INCREMENT,
  `designation` varchar(50) NOT NULL,
  `description` varchar(150) NOT NULL,
 `created_date` datetime NOT NULL,
  `created_by` varchar(155) NOT NULL,
  `modified_date` datetime NOT NULL,
  `modified_by` varchar(155) NOT NULL,
  `del_desig` int NOT NULL,
  `InActive` int NOT NULL,
PRIMARY KEY (`designation_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE `discharge_summary` (
  `dischrg_id` int NOT NULL AUTO_INCREMENT,
  `dischrg_no` varchar(50) NOT NULL,
  `dischrg_date` datetime NOT NULL,
  `umr_no` varchar(100) NOT NULL,
  `reg_no` varchar(100) NOT NULL,
  `admit_no` varchar(100) NOT NULL,
  `dept` varchar(200) NOT NULL,
  `formate` varchar(200) NOT NULL,
  `summary` varchar(200) NOT NULL,
  `status` int NOT NULL,
  `appr_dis` int NOT NULL,
  `del_dis` int NOT NULL,
  `in_active` int NOT NULL,
  `created_date` datetime NOT NULL,
   `created_by` varchar(100) NOT NULL,
  `modified_date` datetime NOT NULL,
  `modified_by` varchar(100) NOT NULL,
PRIMARY KEY (`dischrg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `branches` (
  `branche_id` int NOT NULL AUTO_INCREMENT,
  `branche_code` varchar(100) NOT NULL,
  `branche_name` varchar(150) NOT NULL,
  `created_date` datetime NOT NULL,
  `created_by` varchar(155) NOT NULL,
  `modified_date` datetime NOT NULL,
  `modified_by` varchar(155) NOT NULL,
 `del_branch` int NOT NULL,
  `in_active` int NOT NULL,
PRIMARY KEY (`branche_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(15) NOT NULL,
  `department` int NOT NULL,
  `designation` int NOT NULL,
  `user_role` int NOT NULL,
  `c_type` varchar(25) NOT NULL,
  `title` int NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `middle_name` varchar(25) NOT NULL,
  `father_name` varchar(100) NOT NULL,
  `age` int NOT NULL,
  `street` varchar(50) NOT NULL,
  `subd_brgy` varchar(50) NOT NULL,
  `province` char(50) NOT NULL,
  `phone_no` varchar(15) NOT NULL,
  `mobile_no` varchar(15) NOT NULL,
  `gender` int NOT NULL,
  `civil_status` int NOT NULL,
  `birth_date` date NOT NULL,
  `birth_place` varchar(100) NOT NULL,
  `doj` date NOT NULL,
  `blood_group` int NOT NULL,
  `religion` int NOT NULL,
  `spec` int NOT NULL,
  `qual` varchar(155) NOT NULL,
  `consult_type` varchar(55) NOT NULL,
  `doctor_type` varchar(100) NOT NULL,
  `email_address` varchar(75) NOT NULL,
  `user_name` varchar(25) NOT NULL,
  `password` varchar(150) NOT NULL,
  `picture` varchar(100) NOT NULL,
  `doctor_is_in` varchar(10) NOT NULL,
  `doctor_last_in` varchar(25) NOT NULL,
  `doctor_last_out` varchar(25) NOT NULL,
  `created_date` datetime NOT NULL,
  `created_by` varchar(55) NOT NULL,
  `modified_date` datetime NOT NULL,
  `modified_by` varchar(55) NOT NULL,
  `del_emp` int NOT NULL,
  `in_active` int NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;