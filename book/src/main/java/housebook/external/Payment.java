package housebook.external;

public class Payment {

    private Long id;
    private String status;
    private Long bookId;
    private Long houseId;
    private String houseName;
    private String paymentDate;
    private String paymentCancelDate;
    private Double housePrice;

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

    public Long getBookId() {
        return bookId;
    }
    public void setBookId(Long bookId) {
        this.bookId = bookId;
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

    public String getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentCancelDate() {
        return paymentCancelDate;
    }
    public void setPaymentCancelDate(String paymentCancelDate) {
        this.paymentCancelDate = paymentCancelDate;
    }

    public Double getHousePrice() {
        return housePrice;
    }
    public void setHousePrice(Double housePrice) {
        this.housePrice = housePrice;
    }

}
