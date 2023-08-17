import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Card {
    private static final int unitPrice = 8;

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

    public double calculateBasketCost(List<Book> bookList) {

        List<Integer> series = new ArrayList<>();
        for (Book book : bookList) {
            series.add(book.getQuantity());
        }
        List<String> combinationList = new ArrayList<>();
        List<Double> combinationDiscountList = new ArrayList<>();


        List<Integer> initialSeries = series;

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


                    series.removeAll(Collections.singleton(0));
                    Collections.sort(series);
                    System.out.println(series);
                    int ite = 0;
                    if (nextNumberGrouped == 5 && series.size()>0) {
                        for (int i = series.size()-1; i >= 0; i--) {
                            tempSeries.add(series.get(i) - 1);
                        }
                        System.out.println(tempSeries);
                    }

                    if (nextNumberGrouped == 4 && series.size()>0) {
                        for (int i = series.size()-1; i >= 0; i--) {
                            tempSeries.add(series.get(i) - 1);
                            System.out.println(tempSeries.size());
                        }
                        System.out.println(tempSeries.size());
                        if (series.size()>4) {
                            tempSeries.remove(series.size()-1);
                            tempSeries.add(series.get(0));
                        }
                        System.out.println(tempSeries.size());
                    }

                    if (nextNumberGrouped == 3 && series.size()>0) {
                        for (int i = series.size()-1; i >= 0; i--) {
                            tempSeries.add(series.get(i) - 1);
                            System.out.println(tempSeries.size());
                        }
                        if (series.size()==5) {
                            tempSeries.remove(series.size()-1);
                            tempSeries.remove(series.size()-2);

                            tempSeries.add(series.get(0));
                            tempSeries.add(series.get(1));
                        }
                        if (series.size()==4) {
                            tempSeries.remove(series.size()-1);

                            tempSeries.add(series.get(0));
                        }
                        System.out.println(tempSeries.size());
                    }

                    if (nextNumberGrouped == 2 && series.size()>0) {
                        for (int i = series.size()-1; i >= 0; i--) {
                            tempSeries.add(series.get(i) - 1);
                            System.out.println(tempSeries.size());
                        }
                        if (series.size()==5) {
                            tempSeries.remove(series.size()-1);
                            tempSeries.remove(series.size()-2);
                            tempSeries.remove(series.size()-3);

                            tempSeries.add(series.get(0));
                            tempSeries.add(series.get(1));
                            tempSeries.add(series.get(2));
                        }
                        if (series.size()==4) {
                            tempSeries.remove(series.size()-1);
                            tempSeries.remove(series.size()-2);
                            tempSeries.add(series.get(0));
                            tempSeries.add(series.get(1));
                        }
                        if (series.size()==3) {
                            tempSeries.remove(series.size()-1);

                            tempSeries.add(series.get(0));
                        }
                        System.out.println(tempSeries.size());
                    }

                    tempSeries.removeAll(Collections.singleton(0));

                    System.out.println(numberGrouped);
                    discountValue += calculateDiscountedPrice(numberGrouped);
                    System.out.println(tempSeries.size());
                    System.out.println(discountValue);
                } else if (nextNumberGrouped == 1) {
                    discountValue = numberOfItems * calculateDiscountedPrice(1);
                } else {

                    discountValue += series.get(0) * calculateDiscountedPrice(1);
                }

                series = tempSeries;
                System.out.println(series);
                numberGrouped = numberGrouped > series.size() ? series.size() : nextNumberGrouped > numberGrouped ? numberGrouped : nextNumberGrouped;
                System.out.println(numberGrouped);
                combination += (numberGrouped == 1) ? "-" + numberGrouped + "(" + series.get(0) + ")" : "-" + numberGrouped;

                System.out.println(Arrays.toString(new List[]{series}));

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


        for (double discVal: combinationDiscountList) {
            System.out.println("Discount Combination  " + combinationList.get(combinationDiscountList.indexOf(discVal)) + " : " + discVal );
        }

        List<Double> backupDiscountList = combinationDiscountList;
        Collections.sort(backupDiscountList);
        System.out.println("The best discount combination is: ");
        System.out.println(combinationList.get(combinationDiscountList.indexOf(backupDiscountList.get(0))) + ": " + backupDiscountList.get(0));

        return backupDiscountList.get(0);

    }

    public static double calculateDiscountedPrice(int number) {
        double rate;
        switch (number){
            case 2 -> rate = 0.05;
            case 3 -> rate = 0.1;
            case 4 -> rate = 0.2;
            case 5 -> rate = 0.25;
            default -> rate = 0;
        }
        return number*(unitPrice - unitPrice*rate);
    }

    @Override
    public String toString() {
        return "Card{" +
                "numberOfItems=" + numberOfItems +
                ", bookList=" + bookList +
                '}';
    }
}
