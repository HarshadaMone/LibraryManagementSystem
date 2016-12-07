CREATE DATABASE  IF NOT EXISTS `library` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `library`;
-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	5.7.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `BOOK`
--

DROP TABLE IF EXISTS `BOOK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BOOK` (
  `BOOK_ID` int(11) NOT NULL AUTO_INCREMENT,
  `AUTHOR` varchar(45) NOT NULL,
  `TITLE` varchar(45) DEFAULT NULL,
  `CALL_NUMBER` int(11) DEFAULT NULL,
  `PUBLISHER` varchar(45) DEFAULT NULL,
  `YEAR_OF_PUBLICATION` int(11) DEFAULT NULL,
  `LOCATION` varchar(45) DEFAULT NULL,
  `COPIES` int(11) DEFAULT NULL,
  `STATUS` varchar(45) DEFAULT NULL,
  `KEYWORD` varchar(45) DEFAULT NULL,
  `IMAGE` longtext,
  `IMAGE_NAME` varchar(45) DEFAULT NULL,
  `SJSU_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`BOOK_ID`),
  KEY `fk_dept_id_idx` (`SJSU_ID`),
  CONSTRAINT `fk_sjsu_id` FOREIGN KEY (`SJSU_ID`) REFERENCES `USER` (`SJSU_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CHECKOUT`
--

DROP TABLE IF EXISTS `CHECKOUT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CHECKOUT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BOOK_ID` int(11) NOT NULL,
  `SJSU_ID` int(11) NOT NULL,
  `DATE` date DEFAULT NULL,
  `RETURNDATE` date DEFAULT NULL,
  `FINE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `bookid_idx` (`BOOK_ID`),
  KEY `userid_idx` (`SJSU_ID`),
  CONSTRAINT `BOOKID` FOREIGN KEY (`BOOK_ID`) REFERENCES `book` (`BOOK_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `USERID` FOREIGN KEY (`SJSU_ID`) REFERENCES `user` (`SJSU_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `USER`
--

DROP TABLE IF EXISTS `USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER` (
  `SJSU_ID` int(11) NOT NULL,
  `FIRST_NAME` varchar(45) DEFAULT NULL,
  `LAST_NAME` varchar(45) DEFAULT NULL,
  `EMAIL` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) DEFAULT NULL,
  `ROLE` varchar(45) DEFAULT NULL,
  `STATUS` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`SJSU_ID`),
  UNIQUE KEY `EMAIL_UNIQUE` (`EMAIL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-06 23:45:07
