package entities;

public class BookData {
    private int id;
    private String bookName;
    private String bookEdition;
    private float bookPrice;

    public BookData(int id, String bookName, String bookEdition, float bookPrice) {
        this.id = id;
        this.bookName = bookName;
        this.bookEdition = bookEdition;
        this.bookPrice = bookPrice;
    }

    public int getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookEdition() {
        return bookEdition;
    }

    public void setBookEdition(String bookEdition) {
        this.bookEdition = bookEdition;
    }

    public float getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(float bookPrice) {
        this.bookPrice = bookPrice;
    }
}
