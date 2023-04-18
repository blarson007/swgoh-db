CREATE OR REPLACE VIEW guild AS (
	SELECT
		json_payload ->> '$.data.guild_id' as guild_id,
		json_payload ->> '$.data.name' as name,
		json_payload ->> '$.data.galactic_power' as galactic_power,
		json_payload ->> '$.data.member_count' as member_count,
		json_payload ->> '$.data.last_sync' as last_sync
	FROM guild_json
);