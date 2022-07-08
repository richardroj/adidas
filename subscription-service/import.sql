CREATE DATABASE `adidas`;

CREATE TABLE `subscription` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `consent` bit(1) NOT NULL,
  `date_of_birth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `newsletter` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;