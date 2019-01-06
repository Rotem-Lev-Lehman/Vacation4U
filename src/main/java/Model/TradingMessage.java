package Model;

public class TradingMessage extends Message {

    private int vacationIDToTrade;

    public TradingMessage(User sender, User receiver, String text, boolean seen, int vacationIDToBuy, int vacationIDToTrade){
        super(sender, receiver, text, seen, vacationIDToBuy);
        this.vacationIDToTrade = vacationIDToTrade;
    }

    public int getVacationIDToTrade() {
        return vacationIDToTrade;
    }
}
