package housebook;

import housebook.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{

    @Autowired
    BookRepository bookRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverHouseRented_UpdateStatus(@Payload HouseRented houseRented){

        if(houseRented.isMe()){
            System.out.println("##### listener UpdateStatus : " + houseRented.toJson());

            Optional<Book> optional = bookRepository.findById(houseRented.getBookId());
            Book book = optional.get();
//            book.setStatus(houseRented.getStatus());
            book.setId(houseRented.getBookId());
            book.setStatus("RENTED");

            bookRepository.save(book);

        }
    }
//    @StreamListener(KafkaProcessor.INPUT)
//    public void wheneverHouseRentCanceled_UpdateStatus(@Payload HouseRentCanceled houseRentCanceled){
//
//        if(houseRentCanceled.isMe()){
//            System.out.println("##### listener UpdateStatus : " + houseRentCanceled.toJson());
//
//            Optional<Book> optional = bookRepository.findById(houseRentCanceled.getBookId());
//            Book book = optional.get();
////            book.setStatus(houseRentCanceled.getStatus());
//            book.setId(houseRentCanceled.getBookId());
//            book.setStatus("Cancelled");
//
//            bookRepository.save(book);
//        }
//    }

}
