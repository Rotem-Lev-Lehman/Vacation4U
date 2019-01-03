package Model;

public class MessageTrade extends Message {

    private int vacationIDToTrade;

    public MessageTrade(User sender, User receiver, String text, boolean seen, int vacationIDToBuy, int vacationIDToTrade){
        super(sender, receiver, text, seen, vacationIDToBuy);
        this.vacationIDToTrade = vacationIDToTrade;
    }

    public int getVacationIDToTrade() {
        return vacationIDToTrade;
    }
}
