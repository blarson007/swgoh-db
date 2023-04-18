package swgoh.dto;

import java.util.Set;

public class GuildDto {

    private final String guildId;
    private final String jsonPayload;
    private final Set<Integer> members;

    public GuildDto(String guildId, String jsonPayload, Set<Integer> members) {
        this.guildId = guildId;
        this.jsonPayload = jsonPayload;
        this.members = members;
    }

    public String getGuildId() {
        return guildId;
    }

    public String getJsonPayload() {
        return jsonPayload;
    }

    public Set<Integer> getMembers() {
        return members;
    }
}
