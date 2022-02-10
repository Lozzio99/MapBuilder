package MapBuilder.utils;

import static MapBuilder.gui.GridPanel.GRID_HEIGHT;
import static MapBuilder.gui.GridPanel.GRID_WIDTH;
import static java.lang.Math.abs;


public record Vector2D(@Override double x, @Override double y) implements IVector {

    @Override
    public IVector gridLocation() {
        return new Vector2D(x()/ GRID_WIDTH * GRID_WIDTH, y()/ GRID_HEIGHT * GRID_HEIGHT);
    }

    @Override
    public IVector translate(IVector difference) {
        return new Vector2D(x() + difference.x(), y()+ difference.y());
    }

    @Override
    public String toString() {
        return "{ %f, %f }".formatted(x(),y());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2D v = (Vector2D) o;
        return abs(y - v.y) < 1e-6 && abs(x - v.x) < 1e-6;
    }

}
