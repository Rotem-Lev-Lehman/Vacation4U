package View;


import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

public class SearchFlight extends AView implements Initializable {
    public TextField TextFieldCountry, TextFieldAirline;
    public DatePicker DatePickerDepartures,DatePickerArrivals;
    public ComboBox ChoiceBoxVacationKind,ChoiceBoxTickKind;
    public CheckBox CheckBoxReturnFlight,CheckBoxBaggage, CheckBoxSleepPlace;
    public Button SearchButton;
    public TextField TextFieldOriginCountry;
    public TextField TextFieldDestinationCountry;
    public TextField TextMaxPrice;
    public TextField TextMinPrice;
    public ComboBox AdultNumOfTicket;
    public ComboBox InfantNumOfTicket;
    public ComboBox ChildNumOfTicket;
    public CheckBox CheckNonStopFlight;


    public void searchVacation(){


        String originCountry = TextFieldOriginCountry.getText();
        String destinationCountry =TextFieldCountry.getText();
        String airline = TextFieldAirline.getText();
        LocalDate departureDate = DatePickerDepartures.getValue();
        LocalDate arrivalDate = DatePickerArrivals.getValue();

        String vacationKind;
        if(ChoiceBoxVacationKind.getValue() != null)
            vacationKind = ChoiceBoxVacationKind.getValue().toString();

        int minPrice =Integer.parseInt(TextMinPrice.getText());
        int maxPrice =Integer.parseInt(TextMaxPrice.getText());

        if(!legalDates(departureDate,arrivalDate) || !legalRangPrice(minPrice,maxPrice)){
            showFillDetailsError();
        }

        String adultNumOfTicket = AdultNumOfTicket.getSelectionModel().getSelectedItem().toString();
        String childNumOfTicket = ChildNumOfTicket.getSelectionModel().getSelectedItem().toString();
        String infantNumOfTicket = InfantNumOfTicket.getSelectionModel().getSelectedItem().toString();

        boolean isIncludeBaggage = CheckBoxBaggage.isSelected();
        boolean isReturnFlight = CheckBoxReturnFlight.isSelected();
        boolean isConnection = CheckNonStopFlight.isSelected();
        boolean isSleepPlace = CheckBoxSleepPlace.isSelected();



   }

    private boolean legalRangPrice(int minPrice, int maxPrice) {
        if(maxPrice>minPrice)
            return true;
        return false;
    }

    private void showFillDetailsError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText("Please Fill Correct Fields");
        alert.show();
    }
    private boolean legalDates(LocalDate departureDate, LocalDate arrivalDate) {
        LocalDate dateNow = java.time.LocalDate.now();
        Period p = Period.between(dateNow, departureDate);
        Period p1 = Period.between(dateNow, arrivalDate);
        Period p2 = Period.between(departureDate, arrivalDate);
        return !p.isNegative() && !p1.isNegative() && !p2.isNegative();
    }

    public void Country(){

    }
    public void DateDepartures(){

    }
    public void DateArrivals(){

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
    public void Baggage(){

    }
    public void SleepPlace(){

    }
    public void SearchButton(){

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChoiceBoxTickKind.getItems().addAll("Adult", "Child", "Infant");
        ChoiceBoxVacationKind.getItems().addAll("Urbanic", "Exotic");
    }


}
