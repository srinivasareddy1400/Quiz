-- MySQL dump 10.13  Distrib 9.2.0, for macos15.2 (arm64)
--
-- Host: localhost    Database: quizapp
-- ------------------------------------------------------
-- Server version	9.2.0

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
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quiz_id` int DEFAULT NULL,
  `question_text` text,
  `option_a` varchar(100) DEFAULT NULL,
  `option_b` varchar(100) DEFAULT NULL,
  `option_c` varchar(100) DEFAULT NULL,
  `option_d` varchar(100) DEFAULT NULL,
  `correct_option` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `questions_ibfk_1` (`quiz_id`),
  CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`quiz_id`) REFERENCES `quizzes` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,1,'is java oops language?','yes','no','both a and b','nothing','A'),(2,1,'What is the size of int in Java?','8 bits','16 bits','32 bits','64 bits','C'),(3,1,'Which of the following is not a Java keyword?','static','Boolean','void','private','B'),(4,1,'What is the default value of a local variable?','0','null','Depends on type','no default value','D'),(5,1,'What is the result of: 3 + 4 * 2?','14','11','10','9','B'),(6,1,'Which loop is guaranteed to execute at least once?','for','while','do-while','for each','C'),(7,1,'Which of these is not a principle of OOP?','inheritance','encapsulation','compilation','polymorphism','C'),(8,1,'Which keyword is used to inherit a class?','implement','extends','inherit','override','B'),(9,1,'What does method overloading mean?','Redefining method in child class','Define multiple methods with same and  different parameters','inheriting from multiple classes','none','B'),(10,1,'Which of the following is a marker interface?','Runnable','Serializable','Cloneable','Both B and C','D'),(11,1,'Which keyword is used to handle exceptions?','throw','catch ','try ','All of the above','D'),(12,1,'Which collection class allows you to grow or shrink its size and provides indexed access to its elements?','Array','ArrayList','HashMap','TreeSet','B'),(13,1,'What is the output of: List<Integer> list = new ArrayList<>(); list.add(5); list.get(0);?','0','5','Compilation error','Null','B'),(14,1,'Which interface does HashSet implement?','List','Map','Set','Collection','C'),(15,1,'Which method is used to start a thread?','run()','start','excute()','init()','B'),(16,1,'Which class is used to create threads in Java?','Runnable','Thread','Executor','Callable','B'),(17,2,'What is the output of print(type([]))?','<class \'list\'>','<type \'list\'>','list','[]','A'),(18,2,'Which of the following is used to define a block of code in Python?',' Curly braces {}','Parentheses ()','Indentation',' Quotes \"\"','C'),(19,2,'What is the output of bool(0)?','True','False','0','Error','B'),(20,2,'What is the output of 5 // 2 in Python?',' 2.5','2','3','2.0','B'),(21,2,'Which keyword is used for a loop in Python?','loop','iterate','for','repeat','C'),(22,2,'for i in range(3):    print(i)',' 1 2 3','0 1 2','0 1 2 3','None','B'),(23,2,'What is the correct syntax to define a function in Python?','function myFunc():','def myFunc:','def myFunc():','func myFunc()','C'),(24,2,'What is the output of len({\"a\":1, \"b\":2, \"c\":3})?','1','2','3','Error','C'),(25,2,'Which data type is immutable in Python?','List','Dictionary','Set','Tuple','D'),(26,2,'Which keyword is used to handle exceptions?','catch','except','throw','error','B'),(27,2,'What does open(\"file.txt\", \"w\") do?',' Opens file in read mode','Opens file in write mode, creates new file if not exists','Appends to file','Only reads header','B'),(28,2,'What does lambda x: x**2 return?','A function','A value','An error','A class','A'),(29,2,'Which of the following is a generator function?','A function with yield','A function with return','A class function','A lambda function','A'),(30,2,'What is the output of \"Python\"[::-1]?','Python','nohtyP',' Error','P','B'),(31,2,'What is the result of [1, 2] + [3, 4]?','[1, 2, 3, 4]','[1, 2, [3, 4]]','[1, 2, 7]',' Error','A');
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quizzes`
--

DROP TABLE IF EXISTS `quizzes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quizzes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quizzes`
--

LOCK TABLES `quizzes` WRITE;
/*!40000 ALTER TABLE `quizzes` DISABLE KEYS */;
INSERT INTO `quizzes` VALUES (1,'Java Basics Quiz'),(2,'Python');
/*!40000 ALTER TABLE `quizzes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `results`
--

DROP TABLE IF EXISTS `results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `results` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `quiz_id` int DEFAULT NULL,
  `score` int DEFAULT NULL,
  `timestamp` datetime DEFAULT CURRENT_TIMESTAMP,
  `attempt_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `total_questions` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `fk_results_quiz_id` (`quiz_id`),
  CONSTRAINT `fk_results_quiz_id` FOREIGN KEY (`quiz_id`) REFERENCES `quizzes` (`id`) ON DELETE CASCADE,
  CONSTRAINT `results_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `results`
--

LOCK TABLES `results` WRITE;
/*!40000 ALTER TABLE `results` DISABLE KEYS */;
INSERT INTO `results` VALUES (1,10,1,1,'2025-07-07 01:50:28','2025-07-07 01:50:28',1),(2,10,1,1,'2025-07-07 01:50:47','2025-07-07 01:50:47',1),(3,10,1,1,'2025-07-07 01:53:53','2025-07-07 01:53:53',1),(4,10,1,1,'2025-07-07 01:54:10','2025-07-07 01:54:10',1),(5,10,1,1,'2025-07-07 01:59:41','2025-07-07 01:59:41',1),(6,10,1,0,'2025-07-07 02:03:07','2025-07-07 02:03:07',1),(7,26,2,4,'2025-07-11 21:48:12','2025-07-11 21:48:12',15),(8,26,1,4,'2025-07-15 10:52:57','2025-07-15 10:52:57',16),(9,26,1,1,'2025-07-15 11:01:24','2025-07-15 11:01:24',16),(10,26,2,2,'2025-07-15 12:46:28','2025-07-15 12:46:28',15),(11,26,1,4,'2025-07-15 12:51:30','2025-07-15 12:51:30',16),(12,28,1,3,'2025-07-15 13:26:41','2025-07-15 13:26:41',16),(18,26,1,2,'2025-07-15 15:29:40','2025-07-15 15:29:40',16);
/*!40000 ALTER TABLE `results` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(64) NOT NULL,
  `role` enum('USER','ADMIN') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'srinivasareddy','srinivasreddy2002@gmail.com','e1f184a383aa7f9ed29c9234e8f8578f698b5e13c5f77a52158258e4889d531a','USER'),(2,'prabhas','prabhas @gmail.com','e1f184a383aa7f9ed29c9234e8f8578f698b5e13c5f77a52158258e4889d531a','USER'),(6,'srini','srinivasareddy2003@gmail','e1f184a383aa7f9ed29c9234e8f8578f698b5e13c5f77a52158258e4889d531a','USER'),(7,'prab','srtwf','6baad6f126fa53233f5120dd32225d4a9eeaea26dce58789f0b3b6efcdb0dadb','USER'),(8,'qwerty','adffg','3fd240c53cc2f7f694e836ea0e4ccc67925fe82a86d3f171a7d18faa11ea13f8','USER'),(10,'srinivas','dsfg','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92','USER'),(11,'admin','admin@example.com','240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9','ADMIN'),(17,'sr','sfsa','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3','USER'),(25,'srinivasareddy12','sr@gmail.com','8408c65d7ef3cc10925995675828a4c0fec40475baa701391728dc902f040819','USER'),(26,'Sreddy','qwe@1@gmail.com','3013da5e9db40dd3160a7c161d70fe087bdc4ee0f925ebd46b2608955d06df7c','USER'),(27,'srinivasa12','A@gmail.com','f9310e87c56ec02807ba8f7412822a3a880ee627e49f1b4cc5520e36a5851911','USER'),(28,'srinivas13','as@gmail.com','daa2e3e9616b329516f34f93af0a61ae55224ceac298344f3dc7ed18fc1e7e65','USER'),(29,'asd','A@gmail.com','442a4f6bc05d27afa5e97cc8847ec5d1f90f525eb6bfb3862d9c43aa14077d4b','USER'),(31,'asasax','ascsz','96166e27af5a3c431ffa7247ad4e1b2d488008311887cedc655121565721cbce','USER'),(32,'sfdcsd','dvscs','ddbfaa9f919bc06bc9090aa68d8992a7cc17efd3090c38d3fb3cef4d26d7a881','USER'),(33,'asta','as@gmail.com','bac9389cb592c92707027ecc8759620f43069e80c067453510b7bdf95065183a','USER'),(35,'asta1','sdsd','e5af4874d53cd94043d5292e3531c9597b3ef8940905c68ad9859c34a8d385dd','USER'),(36,'sfjsdvhh','xcvjsidofcssdfjaa','f4f699bbcb2bd837c52676b1e1e3c82829d142969551fe7f1915d6d357967c8b','USER'),(37,'sdfasfs','xvsdzv','731fa28e5cca2ec04979ca795674ead2566f755ab1e71badc9cf0e09a20f70d6','USER');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-15 15:42:17
