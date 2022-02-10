package MapBuilder.utils;

public sealed interface IVector permits Vector2D {
    double x();
    double y();
    default boolean isZero() {
        return x()== 0 && y()== 0;
    }
    default boolean isNaN() {
        return x()!= x() && y()!= y();
    }

    IVector gridLocation();

    IVector translate(IVector difference);
}

