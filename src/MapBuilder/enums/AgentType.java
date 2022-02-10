package MapBuilder.enums;

public enum AgentType {
    GUARDS(0),
    INTRUDERS(1);

    private final int code;

    AgentType(int i) {
        this.code = i;
    }

    public int getCode() {
        return code;
    }
}
