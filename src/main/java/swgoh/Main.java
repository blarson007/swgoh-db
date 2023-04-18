package swgoh;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import swgoh.api.SwgohApiClient;
import swgoh.dto.GuildDto;
import swgoh.dto.PlayerDto;
import swgoh.repository.SwgohRepository;

@Component
public class Main implements CommandLineRunner {

    private final SwgohRepository swgohRepository;
    private final SwgohApiClient swgohApiClient;

    public Main(final SwgohRepository swgohRepository) {
        this.swgohRepository = swgohRepository;
        this.swgohApiClient = new SwgohApiClient();
    }

    @Override
    public void run(String... args) throws Exception {
        swgohRepository.buildTables();
        swgohRepository.buildViews();

        if (args != null && args.length > 0) {
            String guildIdentifier = args[0];
            System.out.println("Populating tables for guild: " + guildIdentifier);
            GuildDto guildDto = swgohApiClient.getMembershipForGuild(guildIdentifier);

            for (int allyCode : guildDto.getMembers()) {
                PlayerDto playerDto = swgohApiClient.getPlayerInformation(allyCode);
                swgohRepository.insertPlayer(playerDto);
            }

            swgohRepository.insertGuild(guildDto);

            String statDefinitions = swgohApiClient.getStatDefinitions();
            swgohRepository.insertStatDefinitions(statDefinitions);
        }
    }
}
