package library;

import library.external.PointSystem;
import library.external.PointSystemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

 @RestController
 public class BookRentalSystemController {
  @Autowired
  private BookRentalSystemRepository bookRentalSystemRepository;

  @Autowired
  private PointSystemService pointSystemService;

  @PutMapping("/bookRentalSystems/returned/{id}")
  public void bookReturned(@PathVariable(value = "id") Long id){


        BookRentalSystem bookRentalSystem = bookRentalSystemRepository.findById(id).get();
        bookRentalSystem.setBookStatus("Returned");
        bookRentalSystemRepository.save(bookRentalSystem);


  }

  @PostMapping("/bookRentalSystems/reserveCanceled/{id}")
  public void bookReserveCanceled(@PathVariable(value = "id")Long id){
      BookRentalSystem bookRentalSystem = bookRentalSystemRepository.findById(id).get();
      bookRentalSystem.setBookStatus("Canceled");
      bookRentalSystemRepository.save(bookRentalSystem);

  }

  @PostMapping("/bookRentalSystems/reserve/{id}")
     public void bookReserve(@PathVariable(value="id")Long id){
      PointSystem pointSystem = new PointSystem();
      pointSystem.setBookId(id);
      PointSystemService pointSystemService =  Application.applicationContext.
              getBean(library.external.PointSystemService.class);
      pointSystemService.usePoints(pointSystem);
  }

 }
