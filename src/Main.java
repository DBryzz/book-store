import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        BookStore.Book s1 = new BookStore.Book("B1","JAVA for beginners", 0);
        BookStore.Book s2 = new BookStore.Book("B2","JVM: Explore the Java Virtual Machine in depth", 0);
        BookStore.Book s3 = new BookStore.Book("B3","Lambda Functions with Java 8", 0);
        BookStore.Book s4 = new BookStore.Book("B4","Developing RESTful API with JAVA", 0);
        BookStore.Book s5 = new BookStore.Book("B5","Spring vs Vertx vs Micronaut", 0);

        System.out.println("Enter item to card");
        System.out.println("How many copies do you want of:");
        System.out.print(s1.series() + ": ");
        int quantity = scanner.nextInt();
        s1 = s1.withQuantity(quantity);
        System.out.print(s2.series() + ": ");
        quantity = scanner.nextInt();
        s2 = s2.withQuantity(quantity);
        System.out.print(s3.series() + ": ");
        quantity = scanner.nextInt();
        s3 = s3.withQuantity(quantity);
        System.out.print(s4.series() + ": ");
        quantity = scanner.nextInt();
        s4 = s4.withQuantity(quantity);
        System.out.print(s5.series() + ": ");
        quantity = scanner.nextInt();
        s5 = s5.withQuantity(quantity);

        int numberOfItems = s1.quantity() + s2.quantity() + s3.quantity() +s4.quantity() + s5.quantity();

        BookStore.Card card = new BookStore.Card(numberOfItems, Stream.of(s1, s2, s3, s4, s5).toList());

        System.out.println("\n\nBest discounted price = " + card.calculateBasketCost(card.bookList()));
}
}