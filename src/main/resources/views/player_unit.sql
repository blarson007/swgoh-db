CREATE OR REPLACE VIEW player_unit AS (
	SELECT
		jp.rowid as id,
		pj.json_payload ->> '$.data.ally_code' as ally_code,
		jp.name as name,
		jp.gear_level as gear_level,
		jp.power as power,
		jp.rarity as rarity
	FROM player_json pj, JSON_TABLE(
		json_payload,
		"$.units[*]" COLUMNS(
			rowid FOR ORDINALITY,
			name VARCHAR(100) PATH "$.data.name",
			gear_level INT PATH "$.data.gear_level",
			power INT PATH "$.data.power",
			rarity INT PATH "$.data.rarity"
		)
	) jp
);