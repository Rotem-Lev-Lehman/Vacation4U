package Model;

public class Message {
    private int messageID;
    private User sender;
    private User receiver;
    private String text;
    private boolean seen;
    private int vacationID;

    public Message(User sender, User receiver, String text, boolean seen, int vacationID) {
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.seen = seen;
        this.vacationID = vacationID;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public int getVacationID() {
        return vacationID;
    }

    public void setVacationID(int vacationID) {
        this.vacationID = vacationID;
    }
}
