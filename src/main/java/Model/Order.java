package Model;

public class Order {
    private Vacation vacation;
    private User buyer;
    private OrderStatus status;

    public Order(Vacation vacation, User buyer, OrderStatus status) {
        this.vacation = vacation;
        this.buyer = buyer;
        this.status = status;
    }

    public Vacation getVacation() {
        return vacation;
    }

    public void setVacation(Vacation vacation) {
        this.vacation = vacation;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
