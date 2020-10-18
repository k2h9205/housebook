package housebook;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Book_table")
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String status;
    private Long houseId;
    private String houseName;
    private String bookDate;
    private String bookCancelDate;
    private Double housePrice;

    @PostPersist
    public void onPostPersist(){
        Booked booked = new Booked();
        BeanUtils.copyProperties(this, booked);
        booked.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.
        String timestamp;
        SimpleDateFormat defaultSimpleDateFormat = new SimpleDateFormat("YYYYMMdd");
        timestamp = defaultSimpleDateFormat.format(new Date());

        housebook.external.Payment payment = new housebook.external.Payment();
        // mappings goes here

        payment.setBookId(booked.getId());
        payment.setHouseId(booked.getHouseId());
        payment.setStatus("BOOKED");
        payment.setHousePrice(booked.getHousePrice());
        payment.setPaymentDate(timestamp);
        BookApplication.applicationContext.getBean(housebook.external.PaymentService.class)
            .paymentRequest(payment);


    }

    @PostUpdate
    public void onPostUpdate(){
        BookCanceled bookCanceled = new BookCanceled();
        BeanUtils.copyProperties(this, bookCanceled);
        bookCanceled.publishAfterCommit();

        String timestamp;
        SimpleDateFormat defaultSimpleDateFormat = new SimpleDateFormat("YYYYMMdd");
        timestamp = defaultSimpleDateFormat.format(new Date());

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        housebook.external.Payment payment = new housebook.external.Payment();
        // mappings goes here
        payment.setBookId(bookCanceled.getId());
        payment.setHouseId(bookCanceled.getHouseId());
        payment.setStatus("BOOK_CANCELLED");
        payment.setHousePrice(bookCanceled.getHousePrice());
        payment.setPaymentCancelDate(timestamp);
        BookApplication.applicationContext.getBean(housebook.external.PaymentService.class)
                .paymentCancel(payment);
    }

//    @PreRemove
//    public void onPreRemove(){
//        BookCanceled bookCanceled = new BookCanceled();
//        BeanUtils.copyProperties(this, bookCanceled);
//        bookCanceled.publishAfterCommit();
//
//        //Following code causes dependency to external APIs
//        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.
//
//        housebook.external.Payment payment = new housebook.external.Payment();
//        // mappings goes here
//        payment.setBookId(this.getId());
//        payment.setStatus("BOOK_CANCELLED");
//        BookApplication.applicationContext.getBean(housebook.external.PaymentService.class)
//            .paymentCancel(payment);
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }
    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }
    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }
    public String getBookCancelDate() {
        return bookCancelDate;
    }

    public void setBookCancelDate(String bookCancelDate) {
        this.bookCancelDate = bookCancelDate;
    }
    public Double getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(Double housePrice) {
        this.housePrice = housePrice;
    }




}
