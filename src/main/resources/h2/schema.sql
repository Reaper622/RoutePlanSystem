CREATE TABLE `centernode` (
  `centernode_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `centernode_name` varchar(20) NOT NULL,
  `centernode_addr` varchar(50) NOT NULL,
  `lat` double DEFAULT NULL,
  `lng` double DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`centernode_id`)
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

CREATE TABLE `servicenode` (
  `servicenode_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `servicenode_name` varchar(20) NOT NULL,
  `servicenode_addr` varchar(50) NOT NULL,
  `lat` double DEFAULT NULL,
  `lng` double DEFAULT NULL,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`servicenode_id`)
);

CREATE TABLE `solution` (
  `solution_id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `route` varchar(100) DEFAULT NULL,
  `total_dis` double DEFAULT NULL,
  `total_time` double DEFAULT NULL,
  `version` int(11) NOT NULL,
  `del_flag` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`solution_id`),
  KEY `qv` (`question_id`,`version`),
  KEY `time_index` (`total_time`),
  KEY `dis_index` (`total_dis`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

