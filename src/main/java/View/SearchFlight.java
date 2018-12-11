package View;


import Model.Flight;
import Model.User;
import Model.Vacation;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.ResourceBundle;

public class SearchFlight extends AView implements Initializable  {
    public TextField TextFieldCountryOrigin, TextFieldAirline;
    public DatePicker DatePickerDepartures,DatePickerArrivals;
    public ComboBox ChoiceBoxVacationKind,ChoiceBoxTickKind;
    public CheckBox CheckBoxReturnFlight,CheckBoxBaggage, CheckBoxSleepPlace;
    public Button SearchButton;
    public TextField DestCounty;
   // public TextField TextFieldDestinationCountry;
    public TextField TextMaxPrice;
    public TextField TextMinPrice;
    public ComboBox AdultNumOfTicket;
    public ComboBox InfantNumOfTicket;
    public ComboBox ChildNumOfTicket;
    public CheckBox CheckNonStopFlight;
    public User user;
    public ComboBox ChoiceBoxBaggageType;


    public void searchVacation(){


        String originCountry = TextFieldCountryOrigin.getText();
        String destinationCountry =DestCounty.getText();

        String airline="";
        if (TextFieldAirline.getText()!= null)
            airline = TextFieldAirline.getText();

        String departureDateString="";
        LocalDate departureDate=null;
        if (DatePickerDepartures.getValue()!= null) {
            departureDateString = DatePickerDepartures.getValue().toString();
            departureDate =DatePickerArrivals.getValue();
        }

        String arrivalDateString="";
        LocalDate arrivalDate=null;
        if (DatePickerArrivals.getValue()!= null) {
            arrivalDateString = DatePickerArrivals.getValue().toString();
            arrivalDate = DatePickerArrivals.getValue();

        }

        String vacationKind="";
        if(ChoiceBoxVacationKind.getValue() != null)
            vacationKind = ChoiceBoxVacationKind.getValue().toString();

        String baggageType="";
        if(ChoiceBoxBaggageType.getValue()!=null)
            baggageType= ChoiceBoxBaggageType.getValue().toString();

        int maxPrice=0;
        String Max ="";
         if(!TextMaxPrice.getText().toString().equals("")) {
                 Max = TextMaxPrice.getText().toString();
                 maxPrice = Integer.parseInt(Max);

         }


        int minPrice=0;
        String Min ="";
        if(!TextMinPrice.getText().toString().equals("")) {
            Min = TextMinPrice.getText().toString();
            minPrice = Integer.parseInt(Min);
        }



        if((departureDate!=null && arrivalDate!= null && !legalDates(departureDate,arrivalDate)) || !legalRangPrice(minPrice,maxPrice)){
            showFillDetailsError();
            return;
        }

        int adultNumOfTicket=0;
        if(AdultNumOfTicket.getSelectionModel().getSelectedItem().toString()!=null)
            adultNumOfTicket = Integer.parseInt(AdultNumOfTicket.getSelectionModel().getSelectedItem().toString());

        int childNumOfTicket=0;
        if(ChildNumOfTicket.getSelectionModel().getSelectedItem().toString()!=null)
            childNumOfTicket = Integer.parseInt(ChildNumOfTicket.getSelectionModel().getSelectedItem().toString());

        int infantNumOfTicket=0;
        if(InfantNumOfTicket.getSelectionModel().getSelectedItem().toString()!=null)
            infantNumOfTicket = Integer.parseInt(InfantNumOfTicket.getSelectionModel().getSelectedItem().toString());



        boolean isReturnFlight = CheckBoxReturnFlight.isSelected();
        boolean isConnection = CheckNonStopFlight.isSelected();
        boolean isSleepPlace = CheckBoxSleepPlace.isSelected();

        Flight F=new Flight(airline, originCountry, destinationCountry, departureDate, arrivalDate);
        Vacation v=new Vacation( user, F, departureDateString, arrivalDateString, originCountry, destinationCountry, vacationKind, "",0,baggageType,false,adultNumOfTicket, childNumOfTicket,infantNumOfTicket,maxPrice);

        setChanged();
        notifyObservers(v);

   }

   public void show(List<Vacation> vacationList){
        //this.moveToNewScreen(575, 300, "VacationsView.fxml", "VacationsView");
       Parent root = null;
       try {
           FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("VacationsView.fxml"));
           root = loader.load();
           VacationsView vacationsView = loader.getController();
           vacationsView.setVacations(vacationList);

       } catch (IOException e) {
           e.printStackTrace();
       }
       Stage stage = new Stage();
       stage.setTitle("Vacations");
       stage.setScene(new Scene(root, 575, 300));
       stage.show();

   }

    private boolean legalRangPrice(int minPrice, int maxPrice) {
        if(maxPrice>=minPrice)
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
 //       ChoiceBoxTickKind.getItems().addAll("Adult", "Child", "Infant");
        ChoiceBoxVacationKind.getItems().addAll("Urbanic", "Exotic");
        ChoiceBoxBaggageType.getItems().addAll("None");//// ADD

        AdultNumOfTicket.getItems().addAll("0","1","2","3","4","5","6");
        ChildNumOfTicket.getItems().addAll("0","1","2","3","4","5","6");
        InfantNumOfTicket.getItems().addAll("0","1","2","3","4","5","6");
        AdultNumOfTicket.setValue("0");
        ChildNumOfTicket.setValue("0");
        InfantNumOfTicket.setValue("0");

    }


}
