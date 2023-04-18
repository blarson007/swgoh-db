CREATE TABLE IF NOT EXISTS `stat_definitions_json` (
  `id` int NOT NULL AUTO_INCREMENT,
  `json_payload` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;