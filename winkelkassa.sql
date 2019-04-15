CREATE DATABASE  IF NOT EXISTS `kassa` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `kassa`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: kassa
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `btw`
--

DROP TABLE IF EXISTS `btw`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `btw` (
  `idbtw` int(11) NOT NULL AUTO_INCREMENT,
  `tarief` int(11) NOT NULL,
  PRIMARY KEY (`idbtw`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `btw`
--

LOCK TABLES `btw` WRITE;
/*!40000 ALTER TABLE `btw` DISABLE KEYS */;
INSERT INTO `btw` VALUES (1,0),(2,6),(3,12),(4,21);
/*!40000 ALTER TABLE `btw` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `categorie` (
  `idcategorie` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`idcategorie`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorie`
--

LOCK TABLES `categorie` WRITE;
/*!40000 ALTER TABLE `categorie` DISABLE KEYS */;
INSERT INTO `categorie` VALUES (1,'testcategorie'),(2,'testsubcategorie'),(3,'testsubcategorie2');
/*!40000 ALTER TABLE `categorie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorie_subcategorie`
--

DROP TABLE IF EXISTS `categorie_subcategorie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `categorie_subcategorie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idcategorie` int(11) DEFAULT NULL,
  `idsubcategorie` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_idcategorie_idx` (`idcategorie`),
  KEY `fk_idsubcategorie_idx` (`idsubcategorie`),
  CONSTRAINT `fk_idcategorie` FOREIGN KEY (`idcategorie`) REFERENCES `categorie` (`idcategorie`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_idsubcategorie` FOREIGN KEY (`idsubcategorie`) REFERENCES `categorie` (`idcategorie`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorie_subcategorie`
--

LOCK TABLES `categorie_subcategorie` WRITE;
/*!40000 ALTER TABLE `categorie_subcategorie` DISABLE KEYS */;
INSERT INTO `categorie_subcategorie` VALUES (1,1,2),(2,1,3);
/*!40000 ALTER TABLE `categorie_subcategorie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `klant`
--

DROP TABLE IF EXISTS `klant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `klant` (
  `idklant` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `famillienaam` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(320) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `telNummer` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `adres` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `postNummer` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`idklant`),
  UNIQUE KEY `telNummer_UNIQUE` (`telNummer`),
  UNIQUE KEY `adres_UNIQUE` (`adres`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `klant`
--

LOCK TABLES `klant` WRITE;
/*!40000 ALTER TABLE `klant` DISABLE KEYS */;
INSERT INTO `klant` VALUES (1,'test','tester','tester@test.be','+32474699260','hoppestraat 14','8200');
/*!40000 ALTER TABLE `klant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `klantenkaart`
--

DROP TABLE IF EXISTS `klantenkaart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `klantenkaart` (
  `idklantenkaart` int(11) NOT NULL AUTO_INCREMENT,
  `punten` int(11) NOT NULL DEFAULT '0',
  `barcode` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `eigenaar` int(11) DEFAULT NULL,
  PRIMARY KEY (`idklantenkaart`),
  UNIQUE KEY `barcode_UNIQUE` (`barcode`),
  KEY `fk_eigenaar_idx` (`eigenaar`),
  CONSTRAINT `fk_eigenaar` FOREIGN KEY (`eigenaar`) REFERENCES `klant` (`idklant`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `klantenkaart`
--

LOCK TABLES `klantenkaart` WRITE;
/*!40000 ALTER TABLE `klantenkaart` DISABLE KEYS */;
INSERT INTO `klantenkaart` VALUES (1,0,'123456789',1);
/*!40000 ALTER TABLE `klantenkaart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product` (
  `idproduct` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `prijs` double NOT NULL,
  `btw` int(11) DEFAULT NULL,
  `omschrijving` varchar(320) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `locatie` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `winkel` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `barcode` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `idcategorie` int(11) DEFAULT NULL,
  PRIMARY KEY (`idproduct`),
  KEY `idcategorie_idx` (`idcategorie`),
  KEY `fk_btw_idx` (`btw`),
  CONSTRAINT `fk_idbtw` FOREIGN KEY (`btw`) REFERENCES `btw` (`idbtw`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idcategorie` FOREIGN KEY (`idcategorie`) REFERENCES `categorie` (`idcategorie`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'testproduct1',1,3,'eerste tester','who knows','winkel1','123456789',1),(2,'testproduct2',12,4,'tweede tester','who knows','winkel1','12456781',2);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'kassa'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-16  0:21:57
