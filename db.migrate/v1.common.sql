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
  `params_main_id` bigint NOT NULL,
  `comm_code` varchar(50) NOT NULL,
  `comm_value` varchar(50) NOT NULL,
  `comm_desc` varchar(150) NOT NULL,
  `status` int NOT NULL,
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

CREATE TABLE `tariff` (
  `tariff_id` int NOT NULL AUTO_INCREMENT,
  `tariff_code` varchar(25) NOT NULL,
  `tariff` varchar(155) NOT NULL,
  `tariff_desc` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `created_by` varchar(155) NOT NULL,
  `modified_date` datetime NOT NULL,
  `modified_by` varchar(155) NOT NULL,
  `del_tariff` int NOT NULL,
  `in_active` int NOT NULL,
PRIMARY KEY (`tariff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `nurse_station` (
  `nurse_station_id` int NOT NULL AUTO_INCREMENT,
  `nurse_station_code` varchar(100) NOT NULL,
  `nurse_station_name` varchar(155) NOT NULL,
  `description` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `created_by` varchar(155) NOT NULL,
  `modified_date` datetime NOT NULL,
  `modified_by` varchar(155) NOT NULL,
  `del_nurse_station` int NOT NULL,
  `in_active` int NOT NULL,
PRIMARY KEY (`nurse_station_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `insurance_comp` (
  `in_com_id` int NOT NULL AUTO_INCREMENT,
  `comp_id` varchar(15) NOT NULL,
  `ins_org` int NOT NULL,
  `company_name` varchar(50) NOT NULL,
  `company_address` varchar(100) NOT NULL,
  `phone_no` varchar(15) NOT NULL,
  `fax_no` varchar(25) NOT NULL,
  `gst` varchar(100) NOT NULL,
  `email_address` varchar(50) NOT NULL,
  `contact_person` varchar(100) NOT NULL,
  `contact_no_person` varchar(100) NOT NULL,
  `contact_email` varchar(50) NOT NULL,
  `notes` text NOT NULL,
  `contract_date` date NOT NULL,
  `valid_from` date NOT NULL,
  `valid_to` date NOT NULL,
  `ipp_type` varchar(255) NOT NULL,
  `ipp_disc` varchar(55) NOT NULL,
  `ipb_type` varchar(255) NOT NULL,
  `ipb_disc` varchar(55) NOT NULL,
  `created_date` datetime NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `modified_date` datetime NOT NULL,
  `modified_by` varchar(255) NOT NULL,
  `del_ins` int NOT NULL,
  `in_active` int NOT NULL,
PRIMARY KEY (`in_com_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE reg_fees (
  reg_fees_id int NOT NULL,
  reg_fee_code varchar(25) NOT NULL,
  reg_fees_name varchar(500) NOT NULL,
  reg_fees float NOT NULL,
  reg_valid date NOT NULL,
  reg_valid_to datetime NOT NULL,
  reg_fees_desc text NOT NULL,
  created_date datetime NOT NULL,
  created_by varchar(100) NOT NULL,
  modified_date datetime NOT NULL,
  modified_by varchar(100) NOT NULL,
  created_id varchar(30) NOT NULL,
  modified_id varchar(30) NOT NULL,
  del_reg_fee int NOT NULL,
  in_active int NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


 CREATE TABLE appointment_time (
  app_time_id int NOT NULL,
  doctor_code varchar(55) NOT NULL,
  appointment_minutes int NOT NULL,
  created_date datetime NOT NULL,
  created_by varchar(100) NOT NULL,
  modified_date datetime NOT NULL,
  modified_by varchar(100) NOT NULL,
  created_id varchar(30) NOT NULL,
  modified_id varchar(30) NOT NULL,
  del_appointment_time int NOT NULL,
  in_active int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

 CREATE TABLE how_did (
  how_did_id int NOT NULL,
  how_did_name varchar(500) NOT NULL,
  how_did_desc text NOT NULL,
  in_active int NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

CREATE TABLE room_group (
  room_group_id int NOT NULL,
  room_group_code varchar(100) NOT NULL,
  room_group_name varchar(155) NOT NULL,
  room_group_description varchar(255) NOT NULL,
  created_date datetime NOT NULL,
  created_by varchar(155) NOT NULL,
  modified_date datetime NOT NULL,
  modified_by varchar(155) NOT NULL,
  created_id varchar(30) NOT NULL,
  modified_id varchar(30) NOT NULL,
  del_room_group int NOT NULL,
  in_active int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE room_bed (
  room_bed_id int NOT NULL,
  room_group_code varchar(100) NOT NULL,
  room_code varchar(155) NOT NULL,
  no_of_beds int NOT NULL,
  extension_no int NOT NULL,
  room_status varchar(155) NOT NULL,
  room_block varchar(155) NOT NULL,
  room_level varchar(100) NOT NULL,
  room_wing varchar(100) NOT NULL,
  nurse_station varchar(155) NOT NULL,
  created_date datetime NOT NULL,
  created_by varchar(155) NOT NULL,
  modified_date datetime NOT NULL,
  modified_by varchar(155) NOT NULL,
  created_id varchar(30) NOT NULL,
  modified_id varchar(30) NOT NULL,
  del_room_bed int NOT NULL,
  in_active int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE bed_delete (
  bed_id int NOT NULL,
  room_bed_id int NOT NULL,
  bed_code varchar(100) NOT NULL,
  bed_status varchar(100) NOT NULL,
  is_active int NOT NULL,
  room_code varchar(155) NOT NULL,
  room_group_code varchar(100) NOT NULL,
  room_group_name varchar(155) NOT NULL,
  admission_no varchar(155) NOT NULL,
  created_date datetime NOT NULL,
  created_by varchar(155) NOT NULL,
  modified_date datetime NOT NULL,
  modified_by varchar(155) NOT NULL,
  created_id varchar(30) NOT NULL,
  modified_id varchar(30) NOT NULL,
  del_bed_details int NOT NULL,
  in_active int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

 CREATE TABLE specialization (
  specialization_id int NOT NULL,
  specialization_code varchar(255) NOT NULL,
  specialization_desc text NOT NULL,
  specialization_status int NOT NULL,
  in_active int NOT NULL,
  appr_specialization int NOT NULL,
  del_specialization int NOT NULL,
  created_date datetime NOT NULL,
  created_by varchar(200) NOT NULL,
  created_id varchar(50) NOT NULL,
  modified_date datetime NOT NULL,
  modified_by varchar(200) NOT NULL,
  modified_id varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE billing_head (
  billing_head_id int NOT NULL,
  billing_head_code varchar(25) NOT NULL,
  billing_head_name varchar(155) NOT NULL,
  service_type varchar(100) NOT NULL,
  billing_head_desc varchar(255) NOT NULL,
  priority int NOT NULL,
  created_date datetime NOT NULL,
  created_by varchar(155) NOT NULL,
  modified_date datetime NOT NULL,
  modified_by varchar(155) NOT NULL,
  created_id varchar(30) NOT NULL,
  modified_id varchar(30) NOT NULL,
  del_billing_head int NOT NULL,
  in_active int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE service_category (
  service_group_id int NOT NULL,
  department_code int NOT NULL,
  service_group_code varchar(25) NOT NULL,
  service_group_name varchar(255) NOT NULL,
  service_group_desc text NOT NULL,
  created_date datetime NOT NULL,
  created_by varchar(155) NOT NULL,
  modified_date datetime NOT NULL,
  modified_by varchar(155) NOT NULL,
  created_id varchar(30) NOT NULL,
  modified_id varchar(30) NOT NULL,
  del_service_group int NOT NULL,
  in_active int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE consult_charge (
  consult_id int NOT NULL,
  consult_code varchar(100) NOT NULL,
  tariff varchar(100) NOT NULL,
  doctor_code varchar(100) NOT NULL,
  doctor_name varchar(155) NOT NULL,
  billing_head_code varchar(100) NOT NULL,
  consult_normal_fee int NOT NULL,
  consult_emerg_fee int NOT NULL,
  consult_revisit_fee int NOT NULL,
  registration_fee int NOT NULL,
  no_of_days int NOT NULL,
  no_of_visits int NOT NULL,
  discount_op int NOT NULL,
  discount_ip int NOT NULL,
  service_tax varchar(25) NOT NULL,
  created_date datetime NOT NULL,
  created_by varchar(155) NOT NULL,
  modified_date datetime NOT NULL,
  modified_by varchar(155) NOT NULL,
  created_id varchar(30) NOT NULL,
  modified_id varchar(30) NOT NULL,
  del_consult_charge int NOT NULL,
  in_active int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE service_charge (
  service_charge_id int NOT NULL,
  service_charge_code varchar(25) NOT NULL,
  service_type varchar(155) NOT NULL,
  tariff varchar(25) NOT NULL,
  service_grp varchar(25) NOT NULL,
  service_grp_name varchar(155) NOT NULL,
  billing_head varchar(25) NOT NULL,
  service_name varchar(255) NOT NULL,
  service_code varchar(25) NOT NULL,
  charge int NOT NULL,
  applicable_for varchar(25) NOT NULL,
  service_tax varchar(25) NOT NULL,
  instructions varchar(2000) NOT NULL,
  created_date datetime NOT NULL,
  created_by varchar(155) NOT NULL,
  modified_date datetime NOT NULL,
  modified_by varchar(155) NOT NULL,
  created_id varchar(30) NOT NULL,
  modified_id varchar(30) NOT NULL,
  del_service_charge int NOT NULL,
  in_active int NOT NULL
);

 CREATE TABLE service_request (
  service_request_id int NOT NULL,
  service_request_code varchar(100) NOT NULL,
  service_request_name varchar(255) NOT NULL,
  department varchar(155) NOT NULL,
  created_date datetime NOT NULL,
  created_by varchar(155) NOT NULL,
  modified_date datetime NOT NULL,
  modified_by varchar(155) NOT NULL,
  status int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE corp_quick_entry (
  corp_quick_id int NOT NULL,
  tariff_cd varchar(50) NOT NULL,
  tariff_name varchar(255) NOT NULL,
  srv_grp_cd varchar(50) NOT NULL,
  srv_grp_name varchar(255) NOT NULL,
  service varchar(50) NOT NULL,
  ser_name varchar(255) NOT NULL,
  charge float NOT NULL,
  app_for varchar(50) NOT NULL,
  corp_service varchar(50) NOT NULL,
  corp_ser_name varchar(255) NOT NULL,
  corp_charge float NOT NULL,
  corp_appfor varchar(50) NOT NULL,
  created_date datetime NOT NULL,
  created_by varchar(100) NOT NULL,
  modified_date datetime NOT NULL,
  modified_by varchar(100) NOT NULL,
  created_id varchar(30) NOT NULL,
  modified_id varchar(30) NOT NULL,
  corp_quick_entry_status int NOT NULL,
  in_active int NOT NULL,
  general_ward varchar(100) NOT NULL,
  non_ac_rooms varchar(100) NOT NULL,
  iccu varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE lab_parameters (
  lab_id int NOT NULL,
  lab_group varchar(155) NOT NULL,
  param_id varchar(55) NOT NULL,
  param_name varchar(155) NOT NULL,
  method int NOT NULL,
  short_name varchar(55) NOT NULL,
  normal_ranges int NOT NULL,
  units_checked int NOT NULL,
  units int NOT NULL,
  critical_ranges int NOT NULL,
  default_ranges int NOT NULL,
  lab_description int NOT NULL,
  editor_desc text NOT NULL,
  mult_val int NOT NULL,
  mult_values varchar(255) NOT NULL,
  created_date datetime NOT NULL,
  created_by varchar(155) NOT NULL,
  modified_date datetime NOT NULL,
  modified_by varchar(155) NOT NULL,
  created_id varchar(30) NOT NULL,
  modified_id varchar(30) NOT NULL,
  del_lp int NOT NULL,
  in_active int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE parameter_values (
  parameter_id int NOT NULL,
  lab_parameter_id varchar(25) NOT NULL,
  gender int NOT NULL,
  min_age int NOT NULL,
  min_uom varchar(25) NOT NULL,
  max_age int NOT NULL,
  max_uom varchar(25) NOT NULL,
  descr varchar(155) NOT NULL,
  symbol int NOT NULL,
  min_range varchar(15) NOT NULL,
  max_range varchar(15) NOT NULL,
  units int NOT NULL,
  normal_range varchar(155) NOT NULL,
  in_active int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE modify_appr_transact (
  modify_id int NOT NULL,
  modify_code varchar(25) NOT NULL,
  modify_date datetime NOT NULL,
  transact_type varchar(100) NOT NULL,
  transact_from datetime NOT NULL,
  transact_to datetime NOT NULL,
  discount_no varchar(25) NOT NULL,
  modify_type varchar(25) NOT NULL,
  umr_no varchar(25) NOT NULL,
  patient_name varchar(155) NOT NULL,
  authorized_by int NOT NULL,
  remarks varchar(1000) NOT NULL,
  created_date datetime NOT NULL,
  created_by varchar(100) NOT NULL,
  modified_date datetime NOT NULL,
  modified_by varchar(100) NOT NULL,
  created_id varchar(30) NOT NULL,
  modified_id varchar(30) NOT NULL,
  del_modify int NOT NULL,
  in_active int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE change_admission_details (
  ca_id int NOT NULL,
  reg_no varchar(255) NOT NULL,
  umr_no varchar(100) NOT NULL,
  patnt_name varchar(100) NOT NULL,
  admit_no varchar(100) NOT NULL,
  refsrc varchar(100) NOT NULL,
  conslt_code varchar(100) NOT NULL,
  conslt_name varchar(100) NOT NULL,
  reff_by varchar(100) NOT NULL,
  auth_code varchar(255) NOT NULL,
  created_date datetime NOT NULL,
  created_id varchar(50) NOT NULL,
  created_by varchar(50) NOT NULL,
  modified_id varchar(50) NOT NULL,
  modified_date datetime NOT NULL,
  modified_by varchar(50) NOT NULL,
  in_active int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `patient_appointment` (
  `appointment_id` int NOT NULL,
  `appointment_number` int NOT NULL,
  `patient_id` int NOT NULL,
  `doctor_id` int NOT NULL,
  `appointment_date` datetime NOT NULL,
  `appointment_time` datetime NOT NULL,
  `appointment_reason` text NOT NULL,
  `appointment_status` varchar(255) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(255 )DEFAULT NULL,
  `created_id` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_id` varchar(255) DEFAULT NULL,
  `delete_appointment` int NOT NULL,
  `in_active` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `system_pages` (
  `system_pages_id` int NOT NULL AUTO_INCREMENT,
  `params_main_id` bigint NOT NULL,
  `page_module` varchar(50) NOT NULL,
  `page_name` varchar(50) NOT NULL,
  `page_link` varchar(150) NOT NULL,
  `status` int NOT NULL,
 PRIMARY KEY (`system_pages_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



