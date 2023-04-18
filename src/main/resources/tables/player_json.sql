CREATE TABLE IF NOT EXISTS `player_json` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ally_code` bigint NOT NULL,
  `json_payload` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;