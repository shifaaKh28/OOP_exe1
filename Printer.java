import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * The Printer class provides utility methods for printing information related to game pieces,
 * their history, kills, distances, and positions. It plays a role in visualizing and analyzing
 * various aspects of the game state for debugging, analysis, or user feedback.
 */
public class Printer {

    /**
     * Prints the step history of each game piece, including their positions and owner information.
     *
     * @param pieces The list of game pieces to be printed.
     */
    public static void printHistory(ArrayList<ConcretePiece> pieces) {
        // Sort the pieces using the custom comparator
        pieces.sort(new Comparators.HistoryComparator());

        // Iterate over the sorted pieces and print their step history
        for (ConcretePiece piece : pieces) {
            List<Position> stepHistory = piece.getHistory();
            if (!stepHistory.isEmpty()) {
                String ownerLetter = !piece.getOwner().isPlayerOne() ? "A" : piece instanceof King ? "K" : "D";
                System.out.print(ownerLetter + piece.getId() + ": ");

                // Print step history in the format (x, y)
                System.out.print("[");
                for (int i = 0; i < stepHistory.size(); i++) {
                    Position position = stepHistory.get(i);
                    System.out.print("(" + position.getX() + ", " + position.getY() + ")");
                    if (i < stepHistory.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println("]");
            }
        }
    }
    /**
     * Prints the kills made by each Pawn game piece, categorized by owner.
     *
     * @param pieces The list of game pieces to be printed.
     * @param player The player for whom the kills are being printed.
     */
    public static void printKills(ArrayList<ConcretePiece> pieces, ConcretePlayer player){
        Comparators.setWinner(player);

        pieces.sort(new Comparators.KillsComparator());

        for (ConcretePiece piece : pieces){
            if (piece instanceof Pawn) {
                int kills = ((Pawn)piece).getKills();
                if (kills != 0){
                    String ownerLetter = piece.getOwner().isPlayerOne() ? "D" : "A";
                    System.out.print(ownerLetter + piece.getId() + ": ");
                    System.out.println(kills + " kills");
                }
            }
        }

        //print separator
        for (int i = 0; i < 75; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
    /**
     * Prints the distances traveled by each game piece, categorized by owner.
     *
     * @param pieces The list of game pieces to be printed.
     * @param player The player for whom the distances are being printed.
     */
    public static void printDist(ArrayList<ConcretePiece> pieces, ConcretePlayer player){
        Comparators.setWinner(player);

        pieces.sort(new Comparators.DistComparator());

        for (ConcretePiece piece : pieces){
            int dist = piece.getDistance();
            if (dist != 0){
                String ownerLetter = !piece.getOwner().isPlayerOne() ? "A" : piece instanceof King ? "K" : "D";
                System.out.print(ownerLetter + piece.getId() + ": ");
                System.out.println(dist + " squares");
            }
        }

        //print separator
        for (int i = 0; i < 75; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
    /**
     * Prints the positions on the game board where multiple pieces occupy the same square.
     *
     * @param map A map representing positions and the corresponding pieces on those positions.
     */
    public static void printPositions(Map<Position, Set<ConcretePiece>> map) {
        List<Map.Entry<Position, Set<ConcretePiece>>> list = new ArrayList<>(map.entrySet());

        list.sort(new Comparators.PositionComparator());

        for (Map.Entry<Position, Set<ConcretePiece>> entry : list){
            int x = entry.getKey().getX();
            int y = entry.getKey().getY();
            int amount = entry.getValue().size();
            if (amount > 1) {
                System.out.print("(" + x + ", " + y + ")" + amount + " pieces");
                System.out.println();
            }
        }

        //print separator
        for (int i = 0; i < 75; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}