package Model;

import java.util.Date;

public class PaymentTransaction {
    private Vacation vacation;
    private User payer;
    private User receiver;
    private String creditCardNumber;
    private Date validDate;
    private String cvv;
    private int amount;
    private Date paymentDate;

    public PaymentTransaction(Vacation vacation, User payer, User receiver, String creditCardNumber, Date validDate, String cvv, int amount, Date paymentDate) {
        this.vacation = vacation;
        this.payer = payer;
        this.receiver = receiver;
        this.creditCardNumber = creditCardNumber;
        this.validDate = validDate;
        this.cvv = cvv;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public Vacation getVacation() {
        return vacation;
    }

    public void setVacation(Vacation vacation) {
        this.vacation = vacation;
    }

    public User getPayer() {
        return payer;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
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

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
