/**
 * Represents a snapshot of the game board state after a move.
 * Contains the board state as a 2D array of ConcretePiece objects and information about the last killing pawn.
 * MoveSnapshot class serves as a container for capturing the state of the game board after a move.
 * It's designed to store crucial information about the state of the game at a specific point in time
 */
public class MoveSnapshot {
    // The 2D array representing the board state
    private final ConcretePiece[][] boardState;

    // The last killing pawn involved in the move
    private ConcretePiece lastKillingPawn;

    public MoveSnapshot(ConcretePiece[][] boardState) {
        this.boardState = boardState;
    }
    /**
     * Gets the board state represented as a 2D array of ConcretePiece objects.
     *
     * @return The board state.
     */
    public ConcretePiece[][] getBoardState() {
        return boardState;
    }

    public ConcretePiece getLastKillingPawn() {
        return lastKillingPawn;
    }

    public void setLastKillingPawn(ConcretePiece piece){
        this.lastKillingPawn = piece;
    }

}