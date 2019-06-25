CREATE DATABASE  IF NOT EXISTS `kassa` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `kassa`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: kassa
-- ------------------------------------------------------
-- Server version	8.0.16

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
-- Table structure for table `bestelling`
--

DROP TABLE IF EXISTS `bestelling`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bestelling` (
  `idbestelling` int(11) NOT NULL AUTO_INCREMENT,
  `idleverancier` int(11) NOT NULL,
  `idpersoneel` int(11) NOT NULL,
  `aankoopMethode` varchar(45) NOT NULL,
  `timeStamp` varchar(45) NOT NULL,
  `TotalPrice` varchar(45) NOT NULL,
  PRIMARY KEY (`idbestelling`),
  KEY `fk_leverancier_idx` (`idleverancier`),
  KEY `fk_personeel_idx` (`idpersoneel`),
  CONSTRAINT `fk_leverancier` FOREIGN KEY (`idleverancier`) REFERENCES `leverancier` (`idleverancier`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_personeel` FOREIGN KEY (`idpersoneel`) REFERENCES `personeel` (`idpersoneel`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bestelling`
--

LOCK TABLES `bestelling` WRITE;
/*!40000 ALTER TABLE `bestelling` DISABLE KEYS */;
/*!40000 ALTER TABLE `bestelling` ENABLE KEYS */;
UNLOCK TABLES;

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
  `heeftsubcategorie` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idcategorie`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorie`
--

LOCK TABLES `categorie` WRITE;
/*!40000 ALTER TABLE `categorie` DISABLE KEYS */;
INSERT INTO `categorie` VALUES (1,'testcategorie',1),(2,'testsubcategorie',0),(3,'testsubcategorie2',0),(4,'testcategorie2',0),(6,'1',2),(7,'1',2);
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
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
-- Table structure for table `groep_product`
--

DROP TABLE IF EXISTS `groep_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `groep_product` (
  `idgroep_product` int(11) NOT NULL AUTO_INCREMENT,
  `groepnaam` varchar(45) NOT NULL DEFAULT 'nieuwe groep',
  PRIMARY KEY (`idgroep_product`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groep_product`
--

LOCK TABLES `groep_product` WRITE;
/*!40000 ALTER TABLE `groep_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `groep_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `klant`
--

DROP TABLE IF EXISTS `klant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `klant` (
  `idklant` int(11) NOT NULL AUTO_INCREMENT,
  `voornaam` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `famillienaam` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(320) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `telNummer` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `adres` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `postNummer` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`idklant`),
  UNIQUE KEY `telNummer_UNIQUE` (`telNummer`),
  UNIQUE KEY `adres_UNIQUE` (`adres`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `klant`
--

LOCK TABLES `klant` WRITE;
/*!40000 ALTER TABLE `klant` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `klantenkaart`
--

LOCK TABLES `klantenkaart` WRITE;
/*!40000 ALTER TABLE `klantenkaart` DISABLE KEYS */;
/*!40000 ALTER TABLE `klantenkaart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leverancier`
--

DROP TABLE IF EXISTS `leverancier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `leverancier` (
  `idleverancier` int(11) NOT NULL AUTO_INCREMENT,
  `bedrijfNaam` varchar(320) DEFAULT NULL,
  `adres` varchar(320) DEFAULT NULL,
  `email` varchar(320) DEFAULT NULL,
  `telNr` varchar(320) DEFAULT NULL,
  `contactpersoon` varchar(320) DEFAULT NULL,
  PRIMARY KEY (`idleverancier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leverancier`
--

LOCK TABLES `leverancier` WRITE;
/*!40000 ALTER TABLE `leverancier` DISABLE KEYS */;
/*!40000 ALTER TABLE `leverancier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `login` (
  `idlogin` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `idrole` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idlogin`),
  KEY `fk_login_role_idx` (`idrole`),
  CONSTRAINT `fk_login_role` FOREIGN KEY (`idrole`) REFERENCES `login_role` (`idrole`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_role`
--

DROP TABLE IF EXISTS `login_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `login_role` (
  `idrole` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(45) COLLATE utf8_bin NOT NULL,
  `weight` int(11) NOT NULL,
  PRIMARY KEY (`idrole`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_role`
--

LOCK TABLES `login_role` WRITE;
/*!40000 ALTER TABLE `login_role` DISABLE KEYS */;
INSERT INTO `login_role` VALUES (1,'user',1),(2,'kassabeheerder',2),(3,'artikelbeheerder',3),(4,'personeelbeheerder',4),(5,'admin',5);
/*!40000 ALTER TABLE `login_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personeel`
--

DROP TABLE IF EXISTS `personeel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `personeel` (
  `idpersoneel` int(11) NOT NULL AUTO_INCREMENT,
  `voornaam` varchar(45) DEFAULT NULL,
  `familienaam` varchar(45) DEFAULT NULL,
  `adres_nr` varchar(45) DEFAULT NULL,
  `gemeente` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `idlogin` int(11) NOT NULL,
  `telNr` varchar(45) DEFAULT NULL,
  `functie` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idpersoneel`),
  KEY `fk_login_idx` (`idlogin`),
  CONSTRAINT `fk_login` FOREIGN KEY (`idlogin`) REFERENCES `login` (`idlogin`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personeel`
--

LOCK TABLES `personeel` WRITE;
/*!40000 ALTER TABLE `personeel` DISABLE KEYS */;
/*!40000 ALTER TABLE `personeel` ENABLE KEYS */;
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
  `idgroep` int(11) DEFAULT NULL,
  `geldigheidsdatum` date DEFAULT NULL,
  `vervaldatum` date DEFAULT NULL,
  PRIMARY KEY (`idproduct`),
  KEY `idcategorie_idx` (`idcategorie`),
  KEY `fk_btw_idx` (`btw`),
  KEY `idgroep_idx` (`idgroep`),
  CONSTRAINT `fk_idbtw` FOREIGN KEY (`btw`) REFERENCES `btw` (`idbtw`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idcategorie` FOREIGN KEY (`idcategorie`) REFERENCES `categorie` (`idcategorie`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idgroep` FOREIGN KEY (`idgroep`) REFERENCES `groep_product` (`idgroep_product`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (2,'testproduct2',12,4,'tweede tester','who knows','winkel1','12456781',2,NULL,NULL,NULL),(6,'nutella chocolate',4,4,'pot nutella',NULL,NULL,'80051640',1,NULL,NULL,NULL),(7,'teset',4,4,'test',NULL,NULL,'1234567',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_bestelling`
--

DROP TABLE IF EXISTS `product_bestelling`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product_bestelling` (
  `idproduct_bestelling` int(11) NOT NULL AUTO_INCREMENT,
  `idbestelling` int(11) NOT NULL,
  `idproduct` int(11) NOT NULL,
  `aantal` int(11) NOT NULL,
  `totaalPrijs` double NOT NULL,
  PRIMARY KEY (`idproduct_bestelling`),
  KEY `fk_bestelling_idx` (`idbestelling`),
  KEY `fk_product_idx` (`idproduct`),
  CONSTRAINT `fk_bestelling` FOREIGN KEY (`idbestelling`) REFERENCES `bestelling` (`idbestelling`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_product` FOREIGN KEY (`idproduct`) REFERENCES `product` (`idproduct`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_bestelling`
--

LOCK TABLES `product_bestelling` WRITE;
/*!40000 ALTER TABLE `product_bestelling` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_bestelling` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_verrichting`
--

DROP TABLE IF EXISTS `product_verrichting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product_verrichting` (
  `idproduct_verrichting` int(11) NOT NULL AUTO_INCREMENT,
  `idverrichting` int(11) NOT NULL,
  `idproduct` int(11) NOT NULL,
  `aantal` int(11) NOT NULL,
  PRIMARY KEY (`idproduct_verrichting`),
  KEY `fk_product_idx` (`idproduct`),
  KEY `fk_verrichting_idx` (`idverrichting`),
  CONSTRAINT `fk_product_verrichting` FOREIGN KEY (`idproduct`) REFERENCES `product` (`idproduct`),
  CONSTRAINT `fk_verrichting` FOREIGN KEY (`idverrichting`) REFERENCES `verrichting` (`idverrichting`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_verrichting`
--

LOCK TABLES `product_verrichting` WRITE;
/*!40000 ALTER TABLE `product_verrichting` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_verrichting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verrichting`
--

DROP TABLE IF EXISTS `verrichting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `verrichting` (
  `idverrichting` int(11) NOT NULL AUTO_INCREMENT,
  `timeStamp` datetime NOT NULL,
  `winkel` varchar(320) NOT NULL,
  `idpersoneel` int(11) NOT NULL,
  `totaalPrijs` double NOT NULL,
  `opmerking` varchar(320) DEFAULT NULL,
  `idklant` int(11) DEFAULT NULL,
  PRIMARY KEY (`idverrichting`),
  KEY `fk_verichting_klant_idx` (`idklant`),
  KEY `fk_verrichting_personeel_idx` (`idpersoneel`),
  CONSTRAINT `fk_verichting_klant` FOREIGN KEY (`idklant`) REFERENCES `klant` (`idklant`),
  CONSTRAINT `fk_verrichting_personeel` FOREIGN KEY (`idpersoneel`) REFERENCES `personeel` (`idpersoneel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verrichting`
--

LOCK TABLES `verrichting` WRITE;
/*!40000 ALTER TABLE `verrichting` DISABLE KEYS */;
/*!40000 ALTER TABLE `verrichting` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-25 18:56:00
