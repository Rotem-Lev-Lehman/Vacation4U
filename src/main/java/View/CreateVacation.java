package View;

import Model.Flight;
import Model.User;
import Model.Vacation;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class CreateVacation extends AView implements Initializable {
    public TextField TextFieldcountryName, TextFieldAirline, TextFieldPrice, TextFieldOrigin;
    public DatePicker DeparturesDate, ArrivalsDate;
    public Button ButtonCreate;
    public ToggleButton ButtonSleepPlace;
    public ComboBox ChoiceBoxVacationKind, ComboBoxAdult, ComboBoxChild, ComboBoxInfant, RankPlace, Place;
    public CheckBox CheckBoxReturnFlight, CheckBoxConnectionFlight,CheckBoxBaggage;
    public Flight flight;
    String rankPlace="";
    String place="";

    public void CountryName(){

    }
    public void DeparturesDate(){

    }
    public void PlaceToSleep(){
        if(ButtonSleepPlace.isSelected()) {
            Place.setVisible(true);
            RankPlace.setVisible(true);

        }
        else{
            Place.setVisible(false);
            RankPlace.setVisible(false);
            place="";
            rankPlace="";
        }
    }
    public void CreateVacation(){
        String airline = TextFieldAirline.getText();
        if(ButtonSleepPlace.isSelected()) {
            if(Place.getValue() == null || Place.getValue().equals("") || RankPlace.getValue() == null || RankPlace.getValue().equals("")){
                showFillDetailsError();
                return;
            }
            place = Place.getValue().toString();
            rankPlace = RankPlace.getValue().toString();
        }
        String price = TextFieldPrice.getText();
        LocalDate departureDate = DeparturesDate.getValue();
        LocalDate arrivalDate = ArrivalsDate.getValue();
        boolean isBaggage = CheckBoxBaggage.isSelected();
        String adultTicketNumber = ComboBoxAdult.getSelectionModel().getSelectedItem().toString();
        String childTicketNumber = ComboBoxChild.getSelectionModel().getSelectedItem().toString();
        String infantTicketNumber = ComboBoxInfant.getSelectionModel().getSelectedItem().toString();
        String destination = TextFieldcountryName.getText();
        String OriginCountry= TextFieldOrigin.getText();
        String vacationKind=" ";
        if(ChoiceBoxVacationKind.getValue() != null)
             vacationKind = ChoiceBoxVacationKind.getValue().toString();

        boolean isConnection = CheckBoxConnectionFlight.isSelected();
        boolean isReturnFlight = CheckBoxReturnFlight.isSelected();

        if(airline.equals("") || price.equals("")|| departureDate == null || arrivalDate == null || destination.equals("")|| OriginCountry.equals((""))){
            showFillDetailsError();
            return;
        }
        if((ButtonSleepPlace.isSelected())&&(place.equals("")||rankPlace.equals(""))){
            place="";
            rankPlace="";
            showFillDetailsError();
            return;
        }
        if(!legalDates(departureDate,arrivalDate)){
            moveToNewScreen(400, 470, "OrderVacation.fxml", "Register");
            showDateDetailsError();
            return;
        }
        if(!isInt(price)){
            showPriceError();
            return;
        }
        int priceInt = Integer.parseInt(price);
        flight=new Flight(airline,OriginCountry,destination, departureDate,arrivalDate);
        int rank = -1;
        if(!rankPlace.equals(""))
            rank = Integer.parseInt(rankPlace);
        Vacation v= new Vacation(controller.getUser(),flight,departureDate.toString(),arrivalDate.toString(),OriginCountry,destination,vacationKind,place,rank,"default",
                false,Integer.parseInt(adultTicketNumber),Integer.parseInt(childTicketNumber),Integer.parseInt(infantTicketNumber),priceInt);
        setChanged();
        notifyObservers(v);
        createIsSuccess();

    }

    private boolean isInt(String price) {
        try{
            Integer.parseInt(price);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private void showPriceError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText("Please put number input into price");
        alert.show();
        return;
    }

    private void showFillDetailsError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText("Please Fill in All Fields");
        alert.show();
        return;
    }
    private void createIsSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Create");
        alert.setContentText("Create successfully");
        alert.show();
        return;
    }
    private void showDateDetailsError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText("Illegal Dates");
        alert.show();
        return;
    }


    private boolean legalDates(LocalDate departureDate, LocalDate arrivalDate) {
        LocalDate dateNow = java.time.LocalDate.now();
        Period p = Period.between(dateNow, departureDate);
        Period p1 = Period.between(dateNow, arrivalDate);
        Period p2 = Period.between(departureDate, arrivalDate);
        return !p.isNegative() && !p1.isNegative() && !p2.isNegative();
    }



    public void TickNum(){

    }
    public void VacationKind(){

    }
    public void Airline(){

    }
    public void TickKind(){

    }
    public void ReturnFlight(){

    }
    public void ConnectionFlight(){

    }
    public void Baggage(){

    }
    public void SleepPlace(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChoiceBoxVacationKind.getItems().addAll("None","Urbanic", "Exotic");
        ComboBoxAdult.getItems().addAll("0","1","2","3","4","5","6");
        ComboBoxChild.getItems().addAll("0","1","2","3","4","5","6");
        ComboBoxInfant.getItems().addAll("0","1","2","3","4","5","6");
        ComboBoxAdult.setValue("0");
        ComboBoxChild.setValue("0");
        ComboBoxInfant.setValue("0");
        ChoiceBoxVacationKind.setValue("None");
        Place.getItems().addAll("Hotel", "rented room", "Tzimer");
        RankPlace.getItems().addAll("0", "1", "2", "3", "4", "5");
       // ButtonSleepPlace.setDisable(true);

    }
}
