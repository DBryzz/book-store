import java.util.*;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static final Scanner scanner = new Scanner(System.in);
    private static final int unitPrice = 8;
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

        ArrayList<Integer> series = new ArrayList<>();
        ArrayList<String> combinationList = new ArrayList<>();
        ArrayList<Double> combinationDiscountList = new ArrayList<>();

        for (Book book : card.getBookList()) {
            if (book.getQuantity() != 0) {
                series.add(book.getQuantity());
            }
        }

        ArrayList<Integer> initialSeries = series;
//        for (Integer integer1 : series) {
//            numberOfItems = numberOfItems + integer1;
//        }
        System.out.println("number of items: " + numberOfItems);

        int nextNumberGrouped = series.size();
        int numberGrouped = series.size();
        int initialNumberOfSeriesType  = series.size();
        int numberOfSeriesTypeConsidered = series.size();
        double discountValue = 0;

        while (numberOfSeriesTypeConsidered >= 1) {
            System.out.println("GROUP BY " + numberGrouped);
            String combination = String.valueOf(numberGrouped);

            while (numberGrouped > 0) {
                ArrayList<Integer> tempSeries = new ArrayList<>();

                if (numberGrouped != 1) {

                    Collections.sort(series);
                    System.out.println(series);
                    int ite = 0;
                    for (int i = series.size()-1; i >= 0; i--) {
                            if (ite < numberGrouped && series.get(i) > 1) {
                                tempSeries.add(series.get(i) - 1);
                            }
                        System.out.println(tempSeries);
                            if (ite >= numberGrouped) {
//                            System.out.println("ite = " + ite);
//                                System.out.println("tempSeries size = " + tempSeries.size());

                                if ( ite >= tempSeries.size() ) {
                                    switch(ite) {
                                        case 4:
                                            if (series.size() > 4) {
                                                tempSeries.add(series.get(0));
                                            }
                                            break;
                                        case 3:
                                            if (series.size() > 3) {
                                                tempSeries.add(series.get(0));
                                                tempSeries.add(series.get(1));
                                            }
                                            break;
                                        case 2:
                                            if (series.size() > 3) {
                                                tempSeries.add(series.get(0));
                                                tempSeries.add(series.get(1));
                                                tempSeries.add(series.get(2));
                                            }
                                        default:
                                            break;
                                    }

                                }

                                break;
                            }

                        ite++;
                        }

                    discountValue += calculateDiscountedPrice(numberGrouped);
                } else if (nextNumberGrouped == 1) {
                    discountValue = numberOfItems * calculateDiscountedPrice(1);
                } else {

                    discountValue += series.get(0) * calculateDiscountedPrice(1);
                }

                series = tempSeries;
                numberGrouped = numberGrouped > series.size() ? series.size() : nextNumberGrouped;
                combination += "-" + numberGrouped;

            System.out.println(Arrays.toString(new ArrayList[]{series}));

            }
            System.out.println(discountValue);
            combinationList.add(combination);
            combinationDiscountList.add(discountValue);
            discountValue = 0;

            numberOfSeriesTypeConsidered = numberOfSeriesTypeConsidered-1;
            series = initialSeries;

            numberGrouped = numberOfSeriesTypeConsidered;
            nextNumberGrouped = numberOfSeriesTypeConsidered;


        }

//        for (String disc: combinationList) {
//            System.out.println("Discount Combination:  " + disc );
//        }

        for (double discVal: combinationDiscountList) {
            System.out.println("Discount Combination  " + combinationList.get(combinationDiscountList.indexOf(discVal)) + " : " + discVal );
        }

        ArrayList<Double> backupDiscountList = combinationDiscountList;
        Collections.sort(backupDiscountList);
        System.out.println("The best discount combination is: ");
        System.out.println(combinationList.get(combinationDiscountList.indexOf(backupDiscountList.get(0))) + ": " + backupDiscountList.get(0));

    }


    public static double calculateDiscountedPrice(int number) {
        double rate;
        switch (number){
            case 2:
                rate = 0.05;
                break;
            case 3:
                rate = 0.1;
                break;
            case 4:
                rate = 0.2;
                break;
            case 5:
                rate = 0.25;
                break;
            default:
                rate = 0;
                break;
    }
    double discount = number*(unitPrice - unitPrice*rate);
        return discount;
    }
}