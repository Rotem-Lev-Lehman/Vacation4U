package View;

import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CreateVacation extends AView implements Initializable {
    public TextField TextFieldcountryName, TextFieldTickNum, TextFieldAirline;
    public DatePicker DeparturesDate, ArrivalsDate;
    public Button ButtonCreate;
    public ComboBox ChoiceBoxTickKind, ChoiceBoxVacationKind;
    public CheckBox CheckBoxReturnFlight, CheckBoxConnectionFlight,CheckBoxBaggage, CheckBoxSleepPlace;

    public void CountryName(){

    }
    public void DeparturesDate(){

    }
    public void CreateVacation(){
        String airline = TextFieldAirline.getText();
        LocalDate departureDate = DeparturesDate.getValue();
        LocalDate arrivalDate = ArrivalsDate.getValue();
        boolean isBaggage = CheckBoxBaggage.isSelected();
        String ticketNumber = TextFieldTickNum.getText();
        String destination = TextFieldcountryName.getText();
        String vacationKind = ChoiceBoxVacationKind.getValue().toString();
        String ticketKind = ChoiceBoxTickKind.getValue().toString();
        boolean isSleepPlace = CheckBoxSleepPlace.isSelected();
        boolean isConnection = CheckBoxConnectionFlight.isSelected();
        boolean isReturnFlight = CheckBoxReturnFlight.isSelected();
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
        ChoiceBoxTickKind.getItems().addAll("Adult", "Child", "Infant");
        ChoiceBoxVacationKind.getItems().addAll("Urbanic", "Exotic");
    }
}
