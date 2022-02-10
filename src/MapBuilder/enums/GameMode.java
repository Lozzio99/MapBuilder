package MapBuilder.enums;

public enum GameMode {
    EXPLORATION(0, "Exploration code, no target to pursue.");
    private final String detail;
    private final int code;

    GameMode(int code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    public int getCode() {
        return code;
    }

    public String getDetail() {
        return detail;
    }

    public static GameMode fromCode(int code) {
        switch (code) {
            case 0 -> {
                return EXPLORATION;
            }
            default -> throw new IllegalArgumentException("No game code found with code: " + code);
        }
    }
}
