package swgoh.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import swgoh.dto.GuildDto;
import swgoh.dto.PlayerDto;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class SwgohRepository {

    private final JdbcTemplate jdbcTemplate;

    public SwgohRepository(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insertGuild(GuildDto guildDto) {
        jdbcTemplate.update("DELETE FROM guild_json WHERE guild_id = ?", guildDto.getGuildId());
        jdbcTemplate.update("INSERT INTO guild_json (guild_id, json_payload) VALUES (?, ?)", guildDto.getGuildId(), guildDto.getJsonPayload());
    }

    public void insertPlayer(PlayerDto playerDto) {
        jdbcTemplate.update("DELETE FROM player_json WHERE ally_code = ?", playerDto.getAllyCode());
        jdbcTemplate.update("INSERT INTO player_json (ally_code, json_payload) VALUES (?, ?)", playerDto.getAllyCode(), playerDto.getJsonPayload());
    }

    public void insertStatDefinitions(String statDefinitions) {
        jdbcTemplate.update("TRUNCATE TABLE stat_definitions_json");
        jdbcTemplate.update("INSERT INTO stat_definitions_json (json_payload) VALUES (?)", statDefinitions);
    }

    public void buildTables() throws IOException {
        for (Path path : listFilesUsingFilesList("./src/main/resources/tables")) {
            jdbcTemplate.update(
                Files.readString(path)
            );
        }
    }

    public void buildViews() throws IOException {
        for (Path path : listFilesUsingFilesList("./src/main/resources/views")) {
            jdbcTemplate.update(
                Files.readString(path)
            );
        }
    }

    private Set<Path> listFilesUsingFilesList(String dir) throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .collect(Collectors.toSet());
        }
    }
}
