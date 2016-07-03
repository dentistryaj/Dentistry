-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               5.1.37-community - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for dentistry
CREATE DATABASE IF NOT EXISTS `dentistry` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `dentistry`;


-- Dumping structure for table dentistry.topic
CREATE TABLE IF NOT EXISTS `topic` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `topic_name` varchar(400) NOT NULL,
  `topic_link` varchar(400) DEFAULT NULL,
  `parent_id` smallint(6) DEFAULT NULL,
  `topic_description` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Unique` (`topic_name`),
  KEY `FK_topic_topic` (`parent_id`),
  CONSTRAINT `FK_topic_topic` FOREIGN KEY (`parent_id`) REFERENCES `topic` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- Data exporting was unselected.


-- Dumping structure for table dentistry.user_details
CREATE TABLE IF NOT EXISTS `user_details` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL DEFAULT '0',
  `user_email` varchar(50) NOT NULL DEFAULT '0',
  `password` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Unique` (`user_email`,`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
