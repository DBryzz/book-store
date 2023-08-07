import java.util.ArrayList;
import java.util.List;

public class Card {
    private int numberOfItems;
    private List<Book> bookList = new ArrayList<>();

    public Card(int numberOfItems, List<Book> bookList) {
        this.numberOfItems = numberOfItems;
        this.bookList = bookList;
    }

    public Card() {
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Card{" +
                "numberOfItems=" + numberOfItems +
                ", bookList=" + bookList +
                '}';
    }
}
