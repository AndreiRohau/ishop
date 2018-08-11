
DROP DATABASE IF EXISTS `shop`;

CREATE SCHEMA IF NOT EXISTS `shop` DEFAULT CHARACTER SET utf8 ;
USE `shop` ;

CREATE TABLE IF NOT EXISTS `shop`.`orders` (
  `id` bigint(20) NOT NULL,
  `user` bigint(20) NOT NULL,
  `products` varchar(500) NOT NULL,
  `address` varchar(1000) NOT NULL,
  `phone` varchar(30) NOT NULL,
  `status` varchar(30) DEFAULT NULL,
  `dateCreated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `table_name_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `shop`.`products` (
  `id` bigint(20) NOT NULL,
  `company` varchar(60) NOT NULL,
  `name` varchar(60) NOT NULL,
  `type` varchar(60) NOT NULL,
  `price` varchar(60) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `table_name_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `shop`.`reserve` (
  `id` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `productId` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `reserve_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `shop`.`users` (
  `id` bigint(20) NOT NULL,
  `login` varchar(60) NOT NULL,
  `password` varchar(60) NOT NULL,
  `role` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8;

INSERT INTO `shop`.`users` (`id`, `login`, `password`, `role`) VALUES ('1', 'admin', 'admin', 'admin');
