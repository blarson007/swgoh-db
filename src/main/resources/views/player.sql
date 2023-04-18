CREATE OR REPLACE VIEW player AS (
	WITH guild_member AS (
		SELECT
			jp.rowid as id,
			gj.json_payload ->> '$.data.guild_id' as guild_id,
			jp.ally_code as guild_ally_code,
			jp.guild_join_time as guild_join_time,
			jp.member_level as member_level
		FROM guild_json gj, JSON_TABLE(
			json_payload,
			"$.data.members[*]" COLUMNS(
				rowid FOR ORDINALITY,
				ally_code BIGINT PATH "$.ally_code",
				guild_join_time DATETIME PATH "$.guild_join_time",
				member_level INT PATH "$.member_level"
			)
		) jp
	), player_data AS (
		SELECT
			json_payload ->> '$.data.ally_code' as player_ally_code,
			json_payload ->> '$.data.name' as name,
			json_payload ->> '$.data.galactic_power' as galactic_power,
			json_payload ->> '$.data.skill_rating' as gac_skill_rating
		FROM player_json pj
	)
	SELECT
		gm.guild_id as guild_id,
		pd.player_ally_code as ally_code,
		pd.name as name,
		pd.galactic_power as galactic_power,
		pd.gac_skill_rating as gac_skill_rating,
		gm.guild_join_time as guild_join_time,
		gm.member_level as member_level
	FROM
		guild_member gm JOIN player_data pd ON gm.guild_ally_code = pd.player_ally_code
);