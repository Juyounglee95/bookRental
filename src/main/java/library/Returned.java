package library;

public class Returned extends AbstractEvent {

    private Long id;
    private String bookName;
    private String bookStatus;

    public Returned(){
        super();
    }


    public Returned(BookRentalSystem bookRentalSystem){
        this();
        this.setId(bookRentalSystem.getId());
        this.setBookStatus(bookRentalSystem.getBookStatus());
        this.setBookName(bookRentalSystem.getBookName());
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }
}
