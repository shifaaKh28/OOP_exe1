import java.util.*;

public abstract class ConcretePiece implements Piece{
    protected Player owner;

    // The history of positions that the piece has occupied
    private final ArrayList<Position> history;

    // collections to keep track of pieces on the board
    private static final Map<Position, Set<ConcretePiece>> piecesOnSquare = new HashMap<>();
    private static final ArrayList<ConcretePiece> attackerPieces = new ArrayList<>();
    private static final ArrayList<ConcretePiece> defenderPieces = new ArrayList<>();

    // Unique identifier for the piece
    private int id;
    private int distance = 0;

    protected ConcretePiece() {
        history = new ArrayList<>();
    }

    @Override
    public Player getOwner() {
        return this.owner;
    }
    /**
     * Updates the position of the piece, calculates the distance traveled,
     * adds the position to the history, and updates the collection of pieces on the square.
     *
     * @param p The new position of the piece.
     */
    public void updatePosition(Position p){
        calculateDist(p);
        this.history.add(p);
        updatePiecesOnSquare(p);
    }
    /**
     * Calculates the distance traveled by the piece based on its history of positions.
     *
     * @param p The new position of the piece.
     */
    private void calculateDist(Position p) {
        if (!history.isEmpty()) {
            Position previousPosition = history.get(history.size() - 1);

            int deltaX = p.getX() - previousPosition.getX();
            int deltaY = p.getY() - previousPosition.getY();

            int distance = (int) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            this.distance += distance;
        }
    }
    /**
     * Gets the history of positions that the piece has occupied.
     *
     * @return The list of positions in the piece's history.
     */
    public ArrayList<Position> getHistory(){
        return this.history;
    }

    @Override
    public abstract String getType();

    public int getId() {
        return this.id;
    }

    public void setId(int x) {
        this.id = x;
    }
    /**
     * Gets the list of pieces belonging to a specific player.
     *
     * @param player The player whose pieces are to be retrieved.
     * @return The list of pieces belonging to the specified player.
     */
    public static ArrayList<ConcretePiece> getPieces(ConcretePlayer player){
        return player.isPlayerOne() ? defenderPieces : attackerPieces;
    }
    /**
     * Adds a piece to the appropriate collection based on the player.
     *
     * @param piece The piece to be added to the collection.
     */
    public static void addPieceToArray(ConcretePiece piece){
        if (piece.getOwner().isPlayerOne()){
            defenderPieces.add(piece);
        }else {
            attackerPieces.add(piece);
        }
    }
    /**
     * Resets the collections of attacker and defender pieces, as well as the pieces on the square.
     */
    public static void resetLists() {
        attackerPieces.clear();
        defenderPieces.clear();
        piecesOnSquare.clear();
    }

    public int getDistance() {
        return this.distance;
    }
    /**
     * Gets the mapping of positions to sets of pieces on those positions.
     *
     * @return The map representing pieces on each square.
     */
    public static Map<Position, Set<ConcretePiece>> getPiecesOnSquare() {
        return piecesOnSquare;
    }
    /**
     * Updates the collection of pieces on a specific square on the board.
     *
     * @param p The position on the board where the update is to be performed.
     */
    private void updatePiecesOnSquare(Position p) {
        piecesOnSquare.computeIfAbsent(p, k -> new HashSet<>()).add(this);

    }
}