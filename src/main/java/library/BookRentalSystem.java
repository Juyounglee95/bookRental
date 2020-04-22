package library;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="BookRentalSystem_table")
public class BookRentalSystem {

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String bookName;
    private String bookStatus;


    //책을 예약함
//    @PostPersist
//    public void bookReserved(){
//        Reserved reserved = new Reserved();
//        BeanUtils.copyProperties(this, reserved);
//        reserved.publish();
//
//        //Following code causes dependency to external APIs
//        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.
//
//        library.external.PointSystem pointSystem = new library.external.PointSystem();
//        // mappings goes here
//        Application.applicationContext.getBean(library.external.PointSystemService.class)
//            .usePoints(pointSystem);
//
//
//
//
//
//    }

    @PostUpdate
    public void bookStatusUpdate(){

        if(this.getBookStatus().equals("Returned")){
            Returned returned = new Returned(this);
            BeanUtils.copyProperties(this, returned);
            returned.publish();
        }else if(this.getBookStatus().equals("Canceled")){

            ReservationCanceled reservationCanceled = new ReservationCanceled(this);
            BeanUtils.copyProperties(this, reservationCanceled);
            reservationCanceled.publish();
        }else if(this.getBookStatus().equals("Reserved Complete")){
            Reserved reserved = new Reserved(this);
            BeanUtils.copyProperties(this, reserved);
            reserved.publish();
        }

    }
//    @PostUpdate
//    public void onPostUpdate(){
//        Returned returned = new Returned();
//        BeanUtils.copyProperties(this, returned);
//        returned.publish();
//
//
//    }


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
