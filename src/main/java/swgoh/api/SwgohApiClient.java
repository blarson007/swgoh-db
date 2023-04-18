package swgoh.api;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import swgoh.dto.GuildDto;
import swgoh.dto.PlayerDto;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SwgohApiClient {

    public GuildDto getMembershipForGuild(String guildIdentifier) throws Exception {
        InputStream inputStream = null;
        try {
            String url = "http://api.swgoh.gg/api/guild-profile/" + guildIdentifier;

            inputStream = getConnectionNoAuth(url);
            JSONObject jsonObject = new JSONObject(new JSONTokener(inputStream));

            JSONArray membersArray = jsonObject
                    .getJSONObject("data")
                    .getJSONArray("members");

            Set<Integer> members = IntStream
                    .range(0,membersArray.length())
                    .mapToObj(i -> membersArray.getJSONObject(i).getInt("ally_code"))
                    .collect(Collectors.toSet());

            return new GuildDto(guildIdentifier, jsonObject.toString(), members);
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {}
        }
    }

    public PlayerDto getPlayerInformation(int allyCode) throws Exception {
        InputStream inputStream = null;
        try {
            String url = "http://api.swgoh.gg/api/player/" + allyCode;

            inputStream = getConnectionNoAuth(url);

            return new PlayerDto(allyCode, new String(inputStream.readAllBytes(), Charset.defaultCharset()));
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {}
        }
    }

    public String getStatDefinitions() throws Exception {
        InputStream inputStream = null;
        try {
            String url = "http://api.swgoh.gg/api/stat-definitions/";

            inputStream = getConnectionNoAuth(url);

            return new String(inputStream.readAllBytes(), Charset.defaultCharset());
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {}
        }
    }

    private InputStream getConnectionNoAuth(String url) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("GET");

        return connection.getInputStream();
    }
}
