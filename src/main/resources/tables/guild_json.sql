CREATE TABLE IF NOT EXISTS `guild_json` (
  `guild_id` varchar(100) DEFAULT NULL,
  `json_payload` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `id` bigint NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;