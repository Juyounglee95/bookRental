package library;

import library.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPointUsed_ChangeStatus(@Payload PointUsed pointUsed){

        if(pointUsed.isMe()){
            System.out.println("##### listener ChangeStatus : " + pointUsed.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRegistered_ChangeStatus(@Payload Registered registered){

        if(registered.isMe()){
            System.out.println("##### listener ChangeStatus : " + registered.toJson());
        }
    }

}
