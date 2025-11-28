import java.util.*;
public interface BookInterface {
    public default void lastSixBookDetails(ArrayList<Book> books){
    }
    String numBooksPerGenre(ArrayList<Book> list);
    double getTotalCost(ArrayList<Book> list);
}
/**Java principle(s) involved:
 * Abstraction is demonstrated by the creation of these methods
 */