package library;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="BookRentalSystem_table")
public class BookRentalSystem {

    private Long id;
    private String bookName;
    private String bookStatus;

    @PostPersist
    public void onPostPersist(){
        Reserved reserved = new Reserved();
        BeanUtils.copyProperties(this, reserved);
        reserved.publish();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        library.external.PointSystem pointSystem = new library.external.PointSystem();
        // mappings goes here
        Application.applicationContext.getBean(library.external.PointSystemService.class)
            .usePoints(pointSystem);


        ReservationCanceled reservationCanceled = new ReservationCanceled();
        BeanUtils.copyProperties(this, reservationCanceled);
        reservationCanceled.publish();


    }

    @PostUpdate
    public void onPostUpdate(){
        Returned returned = new Returned();
        BeanUtils.copyProperties(this, returned);
        returned.publish();


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
