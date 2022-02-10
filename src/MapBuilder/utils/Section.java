package MapBuilder.utils;

public class Section {
    private final IVector pos;
    private boolean isActive;
    private int code;

    public Section(int x, int y) {
        this.pos = new Vector2D(x,y);
        this.isActive = false;
        this.code = -1;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getX() {
        return (int)pos.x();
    }

    public int getY() {
        return (int)pos.y();
    }
    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
