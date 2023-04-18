package swgoh.dto;

public class PlayerDto {

    private final int allyCode;
    private final String jsonPayload;

    public PlayerDto(int allyCode, String jsonPayload) {
        this.allyCode = allyCode;
        this.jsonPayload = jsonPayload;
    }

    public int getAllyCode() {
        return allyCode;
    }

    public String getJsonPayload() {
        return jsonPayload;
    }
}
