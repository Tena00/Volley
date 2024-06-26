-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: tfg
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `acciones_ataque`
--

DROP TABLE IF EXISTS `acciones_ataque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acciones_ataque` (
  `ID_Accion_Ataque` int NOT NULL AUTO_INCREMENT,
  `ID_Estadisticas_Jugador_Partido` int DEFAULT NULL,
  `ID_Estadisticas_Zona_Campo` int DEFAULT NULL,
  `Color_Resultado_Accion` varchar(100) DEFAULT NULL,
  `ID_Partido` int DEFAULT NULL,
  `ID_Estadisticas_Zona_Ataque` int DEFAULT NULL,
  PRIMARY KEY (`ID_Accion_Ataque`),
  KEY `ID_Estadisticas_Jugador_Partido` (`ID_Estadisticas_Jugador_Partido`),
  KEY `fk_partido` (`ID_Partido`),
  KEY `acciones_ataque_estadisticas_zona_campo_FK` (`ID_Estadisticas_Zona_Campo`),
  KEY `fk_estadisticas_ataque` (`ID_Estadisticas_Zona_Ataque`),
  CONSTRAINT `acciones_ataque_estadisticas_zona_campo_FK` FOREIGN KEY (`ID_Estadisticas_Zona_Campo`) REFERENCES `estadisticas_zona_campo` (`ID_Estadisticas_Zona_Campo`),
  CONSTRAINT `acciones_ataque_ibfk_1` FOREIGN KEY (`ID_Estadisticas_Jugador_Partido`) REFERENCES `estadisticas_jugador_partido` (`ID_Estadisticas`),
  CONSTRAINT `fk_estadisticas_ataque` FOREIGN KEY (`ID_Estadisticas_Zona_Ataque`) REFERENCES `estadisticas_zona_ataque` (`ID_Estadisticas_Zona_Ataque`),
  CONSTRAINT `fk_partido` FOREIGN KEY (`ID_Partido`) REFERENCES `partidos` (`ID_Partido`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acciones_ataque`
--

LOCK TABLES `acciones_ataque` WRITE;
/*!40000 ALTER TABLE `acciones_ataque` DISABLE KEYS */;
INSERT INTO `acciones_ataque` VALUES (96,14,33,'rojo',47,32),(97,15,34,'verde',48,33),(98,16,35,'rojo',49,34),(99,16,36,'amarillo',49,35),(100,17,37,'rojo',51,36),(101,18,38,'amarillo',51,37),(102,19,39,'rojo',52,38),(103,20,40,'amarillo',52,39),(104,21,41,'rojo',53,40),(105,22,42,'rojo',54,41),(106,23,43,'verde',54,42),(107,24,44,'verde',55,43),(108,25,45,'verde',55,44),(109,24,46,'rojo',55,45),(110,26,47,'verde',56,46),(111,27,48,'verde',57,47),(112,27,48,'verde',57,48),(113,27,49,'rojo',57,49),(114,28,50,'verde',58,50),(115,28,50,'rojo',58,51),(116,28,50,'amarillo',58,51),(117,28,50,'verde',58,52),(118,28,51,'rojo',58,50);
/*!40000 ALTER TABLE `acciones_ataque` ENABLE KEYS */;
UNLOCK TABLES;

-- Table structure for table `equipos`
--

DROP TABLE IF EXISTS `equipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipos` (
  `ID_Equipo` int NOT NULL AUTO_INCREMENT,
  `Nombre_del_equipo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID_Equipo`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipos`
--

LOCK TABLES `equipos` WRITE;
/*!40000 ALTER TABLE `equipos` DISABLE KEYS */;
INSERT INTO `equipos` VALUES (10,'España'),(11,'Croacia'),(22,'Extremadura');
/*!40000 ALTER TABLE `equipos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadisticas_jugador_partido`
--

DROP TABLE IF EXISTS `estadisticas_jugador_partido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estadisticas_jugador_partido` (
  `ID_Estadisticas` int NOT NULL AUTO_INCREMENT,
  `ID_Jugador` int DEFAULT NULL,
  `ID_Partido` int DEFAULT NULL,
  `Remates_Total` int DEFAULT NULL,
  `Remates_Puntos` int DEFAULT NULL,
  `Remates_Bloqueados` int DEFAULT NULL,
  `Saques_Total` int DEFAULT NULL,
  `Saques_Puntos` int DEFAULT NULL,
  `ID_Zona_campo` int DEFAULT NULL,
  PRIMARY KEY (`ID_Estadisticas`),
  KEY `ID_Jugador` (`ID_Jugador`),
  KEY `ID_Partido` (`ID_Partido`),
  KEY `fk_Zona_Campo` (`ID_Zona_campo`),
  CONSTRAINT `estadisticas_jugador_partido_ibfk_1` FOREIGN KEY (`ID_Jugador`) REFERENCES `jugadores` (`ID_Jugador`),
  CONSTRAINT `estadisticas_jugador_partido_ibfk_2` FOREIGN KEY (`ID_Partido`) REFERENCES `partidos` (`ID_Partido`),
  CONSTRAINT `fk_Zona_Campo` FOREIGN KEY (`ID_Zona_campo`) REFERENCES `zonas_campo` (`ID_Zona_Campo`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadisticas_jugador_partido`
--

LOCK TABLES `estadisticas_jugador_partido` WRITE;
/*!40000 ALTER TABLE `estadisticas_jugador_partido` DISABLE KEYS */;
INSERT INTO `estadisticas_jugador_partido` VALUES (14,23,47,0,0,0,1,0,NULL),(15,32,48,0,0,0,1,0,NULL),(16,23,49,1,0,1,1,0,NULL),(17,32,51,0,0,0,1,0,NULL),(18,27,51,1,0,1,0,0,NULL),(19,23,52,1,0,0,0,0,NULL),(20,24,52,1,0,1,0,0,NULL),(21,23,53,0,0,0,1,0,NULL),(22,23,54,1,0,0,0,0,NULL),(23,26,54,1,1,0,0,0,NULL),(24,23,55,1,1,0,1,0,NULL),(25,25,55,1,1,0,0,0,NULL),(26,39,56,0,0,0,1,1,NULL),(27,39,57,3,2,0,0,0,1),(28,39,58,5,2,1,0,0,NULL);
/*!40000 ALTER TABLE `estadisticas_jugador_partido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadisticas_zona_ataque`
--

DROP TABLE IF EXISTS `estadisticas_zona_ataque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estadisticas_zona_ataque` (
  `ID_Estadisticas_Zona_Ataque` int NOT NULL AUTO_INCREMENT,
  `ID_Zona_Ataque` int DEFAULT NULL,
  `ID_Partido` int DEFAULT NULL,
  `Remates_Total` int DEFAULT NULL,
  `Remates_Puntos` int DEFAULT NULL,
  `Remates_Bloqueados` int DEFAULT NULL,
  `Saques_Total` int DEFAULT NULL,
  `Saques_Puntos` int DEFAULT NULL,
  PRIMARY KEY (`ID_Estadisticas_Zona_Ataque`),
  KEY `ID_Zona_Ataque` (`ID_Zona_Ataque`),
  KEY `fk_ataque_partido` (`ID_Partido`),
  CONSTRAINT `estadisticas_zona_ataque_ibfk_1` FOREIGN KEY (`ID_Zona_Ataque`) REFERENCES `zonas_ataque` (`ID_Zona_Ataque`),
  CONSTRAINT `fk_ataque_partido` FOREIGN KEY (`ID_Partido`) REFERENCES `partidos` (`ID_Partido`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadisticas_zona_ataque`
--

LOCK TABLES `estadisticas_zona_ataque` WRITE;
/*!40000 ALTER TABLE `estadisticas_zona_ataque` DISABLE KEYS */;
INSERT INTO `estadisticas_zona_ataque` VALUES (32,10,47,0,0,0,1,0),(33,10,48,0,0,0,1,0),(34,10,49,0,0,0,1,0),(35,3,49,1,0,1,0,0),(36,3,51,0,0,0,1,0),(37,5,51,1,0,1,0,0),(38,5,52,1,0,0,0,0),(39,8,52,1,0,1,0,0),(40,8,53,0,0,0,1,0),(41,2,54,1,0,0,0,0),(42,3,54,1,1,0,0,0),(43,9,55,1,1,0,0,0),(44,4,55,1,1,0,0,0),(45,2,55,0,0,0,1,0),(46,2,56,0,0,0,1,1),(47,2,57,1,1,0,0,0),(48,1,57,1,1,0,0,0),(49,4,57,1,0,0,0,0),(50,2,58,2,1,0,0,0),(51,3,58,2,0,1,0,0),(52,6,58,1,1,0,0,0);
/*!40000 ALTER TABLE `estadisticas_zona_ataque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadisticas_zona_campo`
--

DROP TABLE IF EXISTS `estadisticas_zona_campo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estadisticas_zona_campo` (
  `ID_Estadisticas_Zona_Campo` int NOT NULL AUTO_INCREMENT,
  `ID_Zona_Campo` int DEFAULT NULL,
  `Remates_Total` int DEFAULT NULL,
  `Remates_Puntos` int DEFAULT NULL,
  `Remates_Bloqueados` int DEFAULT NULL,
  `Saques_Total` int DEFAULT NULL,
  `Saques_Puntos` int DEFAULT NULL,
  `ID_Partido` int DEFAULT NULL,
  `ID_Jugador` int DEFAULT NULL,
  PRIMARY KEY (`ID_Estadisticas_Zona_Campo`),
  KEY `ID_ZonaCampo` (`ID_Zona_Campo`),
  KEY `fk_partido2` (`ID_Partido`),
  KEY `fk_Jugador` (`ID_Jugador`),
  CONSTRAINT `estadisticas_zona_campo_ibfk_1` FOREIGN KEY (`ID_Zona_Campo`) REFERENCES `zonas_campo` (`ID_Zona_Campo`),
  CONSTRAINT `fk_Jugador` FOREIGN KEY (`ID_Jugador`) REFERENCES `jugadores` (`ID_Jugador`),
  CONSTRAINT `fk_partido2` FOREIGN KEY (`ID_Partido`) REFERENCES `partidos` (`ID_Partido`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadisticas_zona_campo`
--

LOCK TABLES `estadisticas_zona_campo` WRITE;
/*!40000 ALTER TABLE `estadisticas_zona_campo` DISABLE KEYS */;
INSERT INTO `estadisticas_zona_campo` VALUES (33,10,0,0,0,1,0,47,NULL),(34,10,0,0,0,1,0,48,NULL),(35,10,0,0,0,1,0,49,NULL),(36,3,1,0,1,0,0,49,NULL),(37,10,0,0,0,1,0,51,NULL),(38,9,1,0,1,0,0,51,NULL),(39,5,1,0,0,0,0,52,NULL),(40,6,1,0,1,0,0,52,NULL),(41,10,0,0,0,1,0,53,NULL),(42,5,1,0,0,0,0,54,NULL),(43,6,1,1,0,0,0,54,NULL),(44,5,1,1,0,0,0,55,NULL),(45,6,1,1,0,0,0,55,NULL),(46,10,0,0,0,1,0,55,NULL),(47,10,0,0,0,1,1,56,NULL),(48,5,2,2,0,0,0,57,NULL),(49,1,1,0,0,0,0,57,NULL),(50,5,4,2,1,0,0,58,39),(51,4,1,0,0,0,0,58,39);
/*!40000 ALTER TABLE `estadisticas_zona_campo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jugadores`
--

DROP TABLE IF EXISTS `jugadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jugadores` (
  `ID_Jugador` int NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(25) DEFAULT NULL,
  `Dorsal` int DEFAULT NULL,
  `ID_Equipo` int DEFAULT NULL,
  `Titular` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID_Jugador`),
  KEY `fk_equipo` (`ID_Equipo`),
  CONSTRAINT `fk_equipo` FOREIGN KEY (`ID_Equipo`) REFERENCES `equipos` (`ID_Equipo`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jugadores`
--

LOCK TABLES `jugadores` WRITE;
/*!40000 ALTER TABLE `jugadores` DISABLE KEYS */;
INSERT INTO `jugadores` VALUES (22,'Rodriguez',3,10,0),(23,'Gimeno',4,10,1),(24,'Lorente',5,10,1),(25,'Ramon',6,10,1),(26,'Larrañaga',7,10,1),(27,'Lopez',9,10,1),(32,'Colito',1,10,1),(33,'Visoc',1,11,1),(39,'Carlos',10,22,1);
/*!40000 ALTER TABLE `jugadores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partidos`
--

DROP TABLE IF EXISTS `partidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partidos` (
  `ID_Partido` int NOT NULL AUTO_INCREMENT,
  `Equipo_ID` int DEFAULT NULL,
  `Resultado_del_partido` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID_Partido`),
  KEY `Equipo_ID` (`Equipo_ID`),
  CONSTRAINT `partidos_ibfk_1` FOREIGN KEY (`Equipo_ID`) REFERENCES `equipos` (`ID_Equipo`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partidos`
--

LOCK TABLES `partidos` WRITE;
/*!40000 ALTER TABLE `partidos` DISABLE KEYS */;
INSERT INTO `partidos` VALUES (47,10,NULL),(48,10,NULL),(49,10,NULL),(50,10,NULL),(51,10,NULL),(52,10,NULL),(53,10,NULL),(54,10,NULL),(55,10,NULL),(56,22,NULL),(57,22,NULL),(58,22,NULL);
/*!40000 ALTER TABLE `partidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zonas_ataque`
--

DROP TABLE IF EXISTS `zonas_ataque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zonas_ataque` (
  `ID_Zona_Ataque` int NOT NULL,
  `Nombre_de_la_zona` varchar(100) DEFAULT NULL,
  `Descripcion_de_la_zona` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`ID_Zona_Ataque`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zonas_ataque`
--

LOCK TABLES `zonas_ataque` WRITE;
/*!40000 ALTER TABLE `zonas_ataque` DISABLE KEYS */;
INSERT INTO `zonas_ataque` VALUES (1,'R1','Ataque'),(2,'R2','Ataque'),(3,'R3','Ataque'),(4,'R4','Ataque'),(5,'R5','Ataque'),(6,'R6','Ataque'),(7,'R7','Ataque'),(8,'R8','Ataque'),(9,'R9','Ataque'),(10,'R10','OUT'),(11,'R11','OUT'),(12,'R12','OUT');
/*!40000 ALTER TABLE `zonas_ataque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zonas_campo`
--

DROP TABLE IF EXISTS `zonas_campo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zonas_campo` (
  `ID_Zona_Campo` int NOT NULL,
  `Nombre_de_la_zona` varchar(100) DEFAULT NULL,
  `Descripcion_de_la_zona` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`ID_Zona_Campo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zonas_campo`
--

LOCK TABLES `zonas_campo` WRITE;
/*!40000 ALTER TABLE `zonas_campo` DISABLE KEYS */;
INSERT INTO `zonas_campo` VALUES (1,'1','Ataque'),(2,'2','Ataque'),(3,'3','Ataque'),(4,'4','Ataque'),(5,'5','Ataque'),(6,'6','Ataque'),(7,'7','Ataque'),(8,'8','Ataque'),(9,'9','Ataque'),(10,'10','Saque'),(11,'11','Saque'),(12,'12','Saque');
/*!40000 ALTER TABLE `zonas_campo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'tfg'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-17 21:55:47
