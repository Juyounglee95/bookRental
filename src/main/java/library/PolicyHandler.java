package library;

import library.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.awt.print.Book;

@Service
public class PolicyHandler{

    @Autowired
    BookRentalSystemRepository bookRentalSystemRepository;

    @StreamListener(KafkaProcessor.INPUT) //포인트 결제 완료시
    public void wheneverPointUsed_ChangeStatus(@Payload PointUsed pointUsed){
        try {
            if (pointUsed.isMe()) {
                System.out.println("##### point use completed : " + pointUsed.toJson());
                BookRentalSystem bookRentalSystem = bookRentalSystemRepository.findById(pointUsed.getBookId()).get();
                bookRentalSystem.setBookStatus("Reserved Complete");
                bookRentalSystemRepository.save(bookRentalSystem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT) //관리자가 도서 신규 등록 시
    public void wheneverRegistered_ChangeStatus(@Payload Registered registered){
        try{
            if(registered.isMe()){
                System.out.print("####book registered: " +  registered.toJson());
                BookRentalSystem bookRentalSystem = new BookRentalSystem();
                bookRentalSystem.setId(registered.getId());
                bookRentalSystem.setBookName(registered.getBookName());
                bookRentalSystem.setBookStatus(Registered.class.getSimpleName());
                bookRentalSystemRepository.save(bookRentalSystem);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
