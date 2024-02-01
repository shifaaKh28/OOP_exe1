import java.util.Objects;

public class Position {
    private final int x;
    private final int y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    /**
     * Overrides the equals method to compare positions based on their x and y coordinates.
     *
     * @param o The object to compare with this position.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }
    /**
     * Overrides the hashCode method to generate a hash code based on the x and y coordinates.
     *
     * @return The hash code for this position.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}