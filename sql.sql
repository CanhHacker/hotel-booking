-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.29 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.2.0.6576
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for hotelbooking
CREATE DATABASE IF NOT EXISTS `hotelbooking` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hotelbooking`;

-- Dumping structure for table hotelbooking.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `id` bigint NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `location_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcp41ik77kf83h3cl7iaew7ksj` (`location_id`),
  CONSTRAINT `FKcp41ik77kf83h3cl7iaew7ksj` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table hotelbooking.customer: ~0 rows (approximately)
REPLACE INTO `customer` (`id`, `gender`, `email`, `name`, `phone_number`, `location_id`) VALUES
	(1, 'Nam', 'non', 'canh', '0123456789', 1);

-- Dumping structure for table hotelbooking.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table hotelbooking.hibernate_sequence: ~1 rows (approximately)
REPLACE INTO `hibernate_sequence` (`next_val`) VALUES
	(3);

-- Dumping structure for table hotelbooking.hotel
CREATE TABLE IF NOT EXISTS `hotel` (
  `id` bigint NOT NULL,
  `hotel_name` varchar(255) DEFAULT NULL,
  `location_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK67s51cnq7o3nlcjh6pm27dqxb` (`location_id`),
  CONSTRAINT `FK67s51cnq7o3nlcjh6pm27dqxb` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table hotelbooking.hotel: ~3 rows (approximately)
REPLACE INTO `hotel` (`id`, `hotel_name`, `location_id`) VALUES
	(1, 'hotel1', 1),
	(2, 'hotel2', 1),
	(3, 'hotelhn1', 2);

-- Dumping structure for table hotelbooking.location
CREATE TABLE IF NOT EXISTS `location` (
  `id` bigint NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table hotelbooking.location: ~2 rows (approximately)
REPLACE INTO `location` (`id`, `city`) VALUES
	(1, 'hcm'),
	(2, 'hn');

-- Dumping structure for table hotelbooking.reservation
CREATE TABLE IF NOT EXISTS `reservation` (
  `id` bigint NOT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `hotel_name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `hotel_id` bigint DEFAULT NULL,
  `room_id` bigint DEFAULT NULL,
  `section_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK41v6ueo0hiran65w8y1cta2c2` (`customer_id`),
  KEY `FKkrmw55mlm5an5ca5qnmrs3pbe` (`hotel_id`),
  KEY `FKm8xumi0g23038cw32oiva2ymw` (`room_id`),
  KEY `FK1jcsgjl60rgr6a7hus99lnlrb` (`section_id`),
  CONSTRAINT `FK1jcsgjl60rgr6a7hus99lnlrb` FOREIGN KEY (`section_id`) REFERENCES `section` (`id`),
  CONSTRAINT `FK41v6ueo0hiran65w8y1cta2c2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FKkrmw55mlm5an5ca5qnmrs3pbe` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`),
  CONSTRAINT `FKm8xumi0g23038cw32oiva2ymw` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table hotelbooking.reservation: ~0 rows (approximately)
REPLACE INTO `reservation` (`id`, `end_date`, `hotel_name`, `price`, `start_date`, `customer_id`, `hotel_id`, `room_id`, `section_id`) VALUES
	(2, '2022-02-16 01:35:24.000000', NULL, 3.14, '2022-02-16 01:35:24.000000', 1, 2, 1, 1);

-- Dumping structure for table hotelbooking.room
CREATE TABLE IF NOT EXISTS `room` (
  `id` bigint NOT NULL,
  `floor` int DEFAULT NULL,
  `hotel_id` bigint DEFAULT NULL,
  `section_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdosq3ww4h9m2osim6o0lugng8` (`hotel_id`),
  KEY `FKqj0v59m62ml23s8bfa6bcejsf` (`section_id`),
  CONSTRAINT `FKdosq3ww4h9m2osim6o0lugng8` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`),
  CONSTRAINT `FKqj0v59m62ml23s8bfa6bcejsf` FOREIGN KEY (`section_id`) REFERENCES `section` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table hotelbooking.room: ~0 rows (approximately)
REPLACE INTO `room` (`id`, `floor`, `hotel_id`, `section_id`) VALUES
	(1, 2, 1, 2);

-- Dumping structure for table hotelbooking.section
CREATE TABLE IF NOT EXISTS `section` (
  `id` bigint NOT NULL,
  `room_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table hotelbooking.section: ~0 rows (approximately)
REPLACE INTO `section` (`id`, `room_type`) VALUES
	(1, 'VIP'),
	(2, 'NORMAL');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
