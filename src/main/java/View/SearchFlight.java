package View;


import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SearchFlight extends AView implements Initializable {
    public TextField TextFieldCountry, TextFieldTickNum, TextFieldAirline;
    public DatePicker DatePickerDepartures,DatePickerArrivals;
    public ComboBox ChoiceBoxVacationKind,ChoiceBoxTickKind;
    public CheckBox CheckBoxReturnFlight,CheckBoxBaggage, CheckBoxSleepPlace;
    public Button SearchButton;


    public void searchVacation(){


        
        String destinationCountry =TextFieldCountry.getText();
        String ticketMum = TextFieldTickNum.getText();
        String airline = TextFieldAirline.getText();
        LocalDate departureDate = DatePickerDepartures.getValue();
        LocalDate arrivalDate = DatePickerArrivals.getValue();
        String vacationKind;
        if(ChoiceBoxVacationKind.getValue() != null)
            vacationKind = ChoiceBoxVacationKind.getValue().toString();
        String ticketKind = ChoiceBoxTickKind.getSelectionModel().getSelectedItem().toString();
        boolean isReturnFlight = CheckBoxReturnFlight.isSelected();
        boolean isConnection = CheckBoxBaggage.isSelected();
        boolean isSleepPlace = CheckBoxSleepPlace.isSelected();

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
