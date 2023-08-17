import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookStore {
    private static final int unitPrice = 8;

    public record Book(String id, String series, int quantity) {
        public Book withQuantity(int quantity) {
            return new Book(id(), series(), quantity);
        }
    }

    public record Card(int numberOfItems, List<Book> bookList) {

        public double calculateBasketCost(List<Book> bookList) {

            if (numberOfItems == 0 ) {
                return 0.0;
            }

            List<Integer> finalSeries = new ArrayList<>();
            bookList.forEach(book -> finalSeries.add(book.quantity()));

            List<Integer> series = finalSeries;
            series.removeAll(Collections.singleton(0));


            List<String> combinationList = new ArrayList<>();
            List<Double> combinationDiscountList = new ArrayList<>();

            List<Integer> initialSeries = series;

            System.out.println("\nnumber of items: " + numberOfItems);

            int nextNumberGrouped = series.size();
            int numberGrouped = series.size();
            int numberOfSeriesTypeConsidered = series.size();
            double discountValue = 0;


            while (numberOfSeriesTypeConsidered >= 1) {
                if (numberGrouped > 1 ) {
                    System.out.println( "\nMAX GROUPING BY " + numberGrouped);
                }
                String combination = String.valueOf(numberGrouped);

                while (numberGrouped > 0) {
                    ArrayList<Integer> tempSeries = new ArrayList<>();

                    if (numberGrouped != 1) {
                        discountValue = getIterationDiscountValue(series, nextNumberGrouped, numberGrouped, discountValue, tempSeries);
                    } else if (nextNumberGrouped == 1) {
                        discountValue = numberOfItems * calculateDiscountedPrice(1);
                    } else {

                        discountValue += series.get(0) * calculateDiscountedPrice(1);
                    }

                    series = tempSeries;
                    numberGrouped = numberGrouped > series.size() ? series.size() : Math.min(nextNumberGrouped, numberGrouped);
                    combination += (numberGrouped == 1) ? "-" + numberGrouped + "(" + series.get(0) + ")" : "-" + numberGrouped;
                }

                combinationList.add(combination);
                combinationDiscountList.add(discountValue);
                discountValue = 0;

                numberOfSeriesTypeConsidered = numberOfSeriesTypeConsidered-1;
                series = initialSeries;

                numberGrouped = numberOfSeriesTypeConsidered;
                nextNumberGrouped = numberOfSeriesTypeConsidered;
            }

            System.out.println("");
            for (double discVal: combinationDiscountList) {
                System.out.println("Discount Combination  " + combinationList.get(combinationDiscountList.indexOf(discVal)) + " : " + discVal );
            }

            List<Double> backupDiscountList = combinationDiscountList;
            Collections.sort(backupDiscountList);
            System.out.println("\nThe best discount combination is " + combinationList.get(combinationDiscountList.indexOf(backupDiscountList.get(0))) + ": " + backupDiscountList.get(0));

            return backupDiscountList.get(0);

        }

        private static double getIterationDiscountValue(List<Integer> series, int nextNumberGrouped, int numberGrouped, double discountValue, ArrayList<Integer> tempSeries) {
            series.removeAll(Collections.singleton(0));
            Collections.sort(series);
            System.out.println(numberGrouped + " --> " + series );

            for (int i = series.size()-1; i >= 0; i--) {
                tempSeries.add(series.get(i) - 1);
            }


            if (nextNumberGrouped == 4) {
                if (series.size()>4) {
                    tempSeries.remove(series.size()-1);
                    tempSeries.add(series.get(0));
                }
            }

            if (nextNumberGrouped == 3) {
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
            }

            if (nextNumberGrouped == 2) {
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
            }

            tempSeries.removeAll(Collections.singleton(0));

            discountValue += calculateDiscountedPrice(numberGrouped);
            return discountValue;
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
    }
}
