package Model;

public class Order {
    private User buyer;
    private User seller;
    private OrderStatus status;

    public Order(User buyer, User seller, OrderStatus status) {
        this.buyer = buyer;
        this.seller = seller;
        this.status = status;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
