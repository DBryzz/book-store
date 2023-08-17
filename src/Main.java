import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static final Scanner scanner = new Scanner(System.in);
//    private static final int unitPrice = 8;
    public static void main(String[] args) {
        Book s1 = new Book("B1","JAVA for beginners");
        Book s2 = new Book("B2","JVM: Explore the Java Virtual Machine in depth");
        Book s3 = new Book("B3","Lambda Functions with Java 8");
        Book s4 = new Book("B4","Developing RESTful API with JAVA");
        Book s5 = new Book("B5","Spring vs Vertx vs Micronaut");

        System.out.println("Enter item to card");
        System.out.println("How many copies do you want of:");
        System.out.print(s1.getSeries() + ": ");
        int quantity = scanner.nextInt();
        s1.setQuantity(quantity);
        System.out.print(s2.getSeries() + ": ");
        quantity = scanner.nextInt();
        s2.setQuantity(quantity);
        System.out.print(s3.getSeries() + ": ");
        quantity = scanner.nextInt();
        s3.setQuantity(quantity);
        System.out.print(s4.getSeries() + ": ");
        quantity = scanner.nextInt();
        s4.setQuantity(quantity);
        System.out.print(s5.getSeries() + ": ");
        quantity = scanner.nextInt();
        s5.setQuantity(quantity);

        int numberOfItems = s1.getQuantity() + s2.getQuantity() + s3.getQuantity() +s4.getQuantity() + s5.getQuantity();

        Card card = new Card();
        card.setNumberOfItems(numberOfItems);
        List<Book> cardItem = Arrays.asList(s1, s2, s3, s4, s5);
        card.setBookList(Arrays.asList(s1, s2, s3, s4, s5));
        System.out.println(card);

        card.calculateBasketCost(card.getBookList());
}}