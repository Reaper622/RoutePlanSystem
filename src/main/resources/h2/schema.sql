CREATE TABLE `final_solution` (
  `final_solution_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `total_dis` float NOT NULL,
  PRIMARY KEY (`final_solution_id`)
);

CREATE TABLE `distance` (
  `distance_id` int(11) NOT NULL AUTO_INCREMENT,
  `start_node_id` int(11) NOT NULL,
  `end_node_id` int(11) NOT NULL,
  `start_node_addr` varchar(100) NOT NULL,
  `end_node_addr` varchar(100) NOT NULL,
  `dis` int(11) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `question_id` int(11) NOT NULL,
  PRIMARY KEY (`distance_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `question` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_name` varchar(50) NOT NULL,
  `user_id` int(11) NOT NULL,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`question_id`),
  UNIQUE KEY `question_question_name_uindex` (`question_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `node` (
  `node_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `centernode_name` varchar(20) NOT NULL,
  `centernode_addr` varchar(50) NOT NULL,
  `lat` float DEFAULT NULL,
  `lng` float DEFAULT NULL,
  `is_center` tinyint(4) DEFAULT '0' COMMENT '0 表示不是中心点;1 表示是中心点',
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`node_id`)
);

CREATE TABLE `solution` (
  `solution_id` int(11) NOT NULL AUTO_INCREMENT,
  `route` varchar(100) DEFAULT NULL,
  `total_dis` double DEFAULT NULL,
  `total_time` double DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT '0',
  `final_solution_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`solution_id`),
  KEY `time_index` (`total_time`),
  KEY `dis_index` (`total_dis`)
);

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_user_name_uindex` (`user_name`),
  UNIQUE KEY `user_password_uindex` (`password`),
  UNIQUE KEY `user_email_uindex` (`email`)
);

CREATE TABLE `vehicle` (
  `vahicle_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `capacity` float DEFAULT NULL,
  `oil` float DEFAULT NULL,
  `date` date DEFAULT NULL,
  `price` float DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`vahicle_id`)
);

