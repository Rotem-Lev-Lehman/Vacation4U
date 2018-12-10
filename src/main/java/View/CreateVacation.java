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

public class CreateVacation extends AView implements Initializable {
    public TextField TextFieldcountryName, TextFieldAirline, TextFieldPrice, TextFieldOrigin;
    public DatePicker DeparturesDate, ArrivalsDate;
    public Button ButtonCreate, ButtonSleepPlace;
    public ComboBox ChoiceBoxVacationKind, ComboBoxAdult, ComboBoxChild, ComboBoxInfant;
    public CheckBox CheckBoxReturnFlight, CheckBoxConnectionFlight,CheckBoxBaggage;
    public Flight flight;

    public void CountryName(){

    }
    public void DeparturesDate(){

    }
    public void CreateVacation(){
        String airline = TextFieldAirline.getText();
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

        if(airline.equals("") || price.equals("")|| !legalDates(departureDate,arrivalDate) || destination.equals("")|| OriginCountry.equals((""))){
            showFillDetailsError();
            return;
        }
        flight=new Flight(airline,OriginCountry,destination, departureDate,arrivalDate);
        Vacation v= new Vacation(controller.getUser(),flight,departureDate.toString(),arrivalDate.toString(),OriginCountry,destination,vacationKind,false,
                Integer.parseInt(adultTicketNumber),Integer.parseInt(childTicketNumber),Integer.parseInt(infantTicketNumber));
        setChanged();
        notifyObservers(v);

    }

    private void showFillDetailsError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText("Please Fill in All Fields");
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
        ChoiceBoxVacationKind.getItems().addAll("Urbanic", "Exotic");
        ComboBoxAdult.getItems().addAll("0","1","2","3","4","5","6");
        ComboBoxChild.getItems().addAll("0","1","2","3","4","5","6");
        ComboBoxInfant.getItems().addAll("0","1","2","3","4","5","6");
        ComboBoxAdult.setValue("0");
        ComboBoxChild.setValue("0");
        ComboBoxInfant.setValue("0");
        ButtonSleepPlace.setDisable(true);

    }
}
