package Model;

public class Vacation{


    private int vacationID;
    private Flight flight;
    private User SellerId;
    private String StartDate;
    private String EndDate;
    private String StartCountry;
    private String DestCountry;
    private String TypesOfVacation;
    private String TypeOfHotel;
    private int rankingOfHotel;
    private String typeOfLuggage;
    private boolean alreadySold;
    private int amountOfAdultTickets;
    private int amountOfChildTickets;
    private int amountOfInfantTickets;
    private int price;

    public Vacation(User SellerId, Flight flight, String StartDate, String EndDate, String StartCountry, String DestCountry, String TypesOfVacation,
                    String TypeOfHotel, int rankingOfHotel, String typeOfLuggage,boolean alreadySold, int amountOfAdultTickets, int amountOfChildTickets,int amountOfInfantTickets, int price){
        this.SellerId=SellerId;
        this.flight=flight;
        this.EndDate=EndDate;
        this.StartDate=StartDate;
        this.StartCountry=StartCountry;
        this.DestCountry=DestCountry;
        this.TypesOfVacation=TypesOfVacation;
        this.alreadySold=alreadySold;
        this.amountOfAdultTickets=amountOfAdultTickets;
        this.amountOfChildTickets=amountOfChildTickets;
        this.amountOfInfantTickets=amountOfInfantTickets;
        this.TypeOfHotel=TypeOfHotel;
        this.rankingOfHotel=rankingOfHotel;
        this.typeOfLuggage=typeOfLuggage;
        this.price=price;


    }
    public Flight getFlight() {
        return flight;
    }
    public int getVacationID() {
        return vacationID;
    }

    public void setVacationID(int vacationID) {
        this.vacationID = vacationID;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public User getSellerId() {
        return SellerId;
    }

    public void setSellerId(User sellerId) {
        SellerId = sellerId;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getStartCountry() {
        return StartCountry;
    }

    public void setStartCountry(String startCountry) {
        StartCountry = startCountry;
    }

    public String getDestCountry() {
        return DestCountry;
    }

    public void setDestCountry(String destCountry) {
        DestCountry = destCountry;
    }

    public String getTypesOfVacation() {
        return TypesOfVacation;
    }

    public void setTypesOfVacation(String typesOfVacation) {
        TypesOfVacation = typesOfVacation;
    }

    public String getTypeOfHotel() {
        return TypeOfHotel;
    }

    public void setTypeOfHotel(String typeOfHotel) {
        TypeOfHotel = typeOfHotel;
    }

    public int getRankingOfHotel() {
        return rankingOfHotel;
    }

    public void setRankingOfHotel(int rankingOfHotel) {
        this.rankingOfHotel = rankingOfHotel;
    }

    public String getTypeOfLuggage() {
        return typeOfLuggage;
    }

    public void setTypeOfLuggage(String typeOfLuggage) {
        this.typeOfLuggage = typeOfLuggage;
    }

    public boolean isAlreadySold() {
        return alreadySold;
    }

    public void setAlreadySold(boolean alreadySold) {
        this.alreadySold = alreadySold;
    }

    public int getAmountOfAdultTickets() {
        return amountOfAdultTickets;
    }

    public void setAmountOfAdultTickets(int amountOfAdultTickets) {
        this.amountOfAdultTickets = amountOfAdultTickets;
    }

    public int getAmountOfChildTickets() {
        return amountOfChildTickets;
    }

    public void setAmountOfChildTickets(int amountOfChildTickets) {
        this.amountOfChildTickets = amountOfChildTickets;
    }

    public int getAmountOfInfantTickets() {
        return amountOfInfantTickets;
    }

    public void setAmountOfInfantTickets(int amountOfInfantTickets) {
        this.amountOfInfantTickets = amountOfInfantTickets;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
