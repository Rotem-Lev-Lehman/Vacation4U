package Model;

import java.time.LocalDate;
import java.util.Date;

public class PaymentTransaction {
    private Order order;
    private String creditCardNumber;
    private String validDate;
    private String cvv;
    private int amount;
    private LocalDate paymentDate;

    public PaymentTransaction(Order order, String creditCardNumber, String validDate, String cvv, int amount, LocalDate paymentDate) {
        this.order = order;
        this.creditCardNumber = creditCardNumber;
        this.validDate = validDate;
        this.cvv = cvv;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }
}
