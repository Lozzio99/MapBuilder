package MapBuilder.enums;

import java.awt.*;

public enum ElementType {
    EMPTY(-1,"Empty",new Color(255, 255, 255),'-'),
    WALL(0,"wall",new Color(255, 51, 51),'|'),
    SPAWN_GUARDS(1,"spawnAreaGuards",new Color(253, 214, 20),'g'),
    SPAWN_INTRUDERS( 2,"spawnAreaIntruders",new Color(61, 255, 200),'i'),
    TARGET( 3,"targetArea",new Color(20, 253, 51),'*'),
    DOOR(4,"door",new Color(146, 103, 255),'o'),
    TELEPORT(5,"teleport",new Color(224, 109, 255),'>'),
    SHADED(6,"shaded",new Color(104, 106, 107),':'),
    LANDING(7, "landing",new Color(255, 182, 239),'@');
    private final int code;
    private final String name;
    private final Color color;
    private final char character;
    ElementType(int code, String name, Color color, char character){
        this.code = code;
        this.name = name;
        this.color = color;
        this.character = character;
    }
    public int getCode() {
        return code;
    }

    public String toString(){
        return name.replace(" ","");
    }

    public String getName() {
        return name;
    }

    public static ElementType fromCode(int code){
        ElementType element;
        switch (code){
            case -1 -> element = EMPTY;
            case  0 -> element = WALL;
            case  1 -> element = SPAWN_GUARDS;
            case  2 -> element = SPAWN_INTRUDERS;
            case  3 -> element = TARGET;
            case  4 -> element = DOOR;
            case  5 -> element = TELEPORT;
            case  6 -> element = SHADED;
            case  7 -> element = LANDING;
            default -> throw new IllegalArgumentException();
        }
        return element;
    }

    public Color getColor() {
        return color;
    }

    public char getChar() {
        return character;
    }

}
