public class Book {
    private String id;
    private String series;
    private int quantity;

    public Book(String id, String series, int quantity) {
        this.id = id;
        this.series = series;
        this.quantity = quantity;
    }

    public Book(String id, String series) {
        this.id = id;
        this.series = series;
    }

    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                "series='" + series + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
