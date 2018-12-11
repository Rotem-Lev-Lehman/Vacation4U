package View;

import Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import Model.Vacation;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class VacationsView extends AView implements Initializable {


    public Label originCountryV1;
    public Button btnOrderNowV1;
    public Label Tairline;
    public Label DestinationCountryV1;
    public Label airlineV1;
    public Label fillpriceV1;
    public Label TVacationToV2;
    public Label originCountryV2;
    public Label TVacationFromV2;
    public Button btnOrderNowV2;
    public Label TairlineV2;
    public Label DestinationCountryV2;
    public Label airlineV2;
    public Label fillpriceV2;
    public Button btn_next;
    public Button btn_prev;
    public int currIndex;
    public List<Vacation> vacationListToView;
    public Vacation v1;
    public Vacation v2;
    public Label TVacationFrom;
    public Label TVacationTo;
    public Label LPriceV2;
    public Label ArrivalsDateV2;
    public Label DeparturesDateV2;
    public Label filldepartureDateV1;
    public Label fillarrivalsDateV1;
    public Label filldepartureDateV2;
    public Label fillarrivalsDateV2;


    public void VacationView(){

    }


    public void orderVacation(){
        if(btnOrderNowV1.isPressed() ){
            setChanged();
            notifyObservers(v1);
        }
        if(btnOrderNowV2.isPressed()){
            setChanged();
            notifyObservers(v2);
        }
    }

    public void userExist(){
        //move to next page - or make an order, ask ido
        moveToNewScreen(400, 470, "OrderVacation.fxml", "OrderVacation");

    }

    public void userNotExist(){
        //error
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText("Only Exist User Can Order");
        alert.show();
        return;
    }

    public void prevPage(){
        if(vacationListToView.size()>currIndex && 0<currIndex) {
            v1 = vacationToShow(vacationListToView, currIndex);
            currIndex--;
        }
        if(vacationListToView.size()>currIndex && 0<currIndex) {
            v2 = vacationToShow(vacationListToView, currIndex);
            currIndex--;
        }

        show();
    }

    public void nextPage(){
        if(vacationListToView.size()>currIndex && 0<=currIndex) {
            v1 = vacationToShow(vacationListToView, currIndex);
            currIndex++;
        }
        if(vacationListToView.size()>currIndex && 0<=currIndex) {
            v2 = vacationToShow(vacationListToView, currIndex);
            currIndex++;
        }
        show();

    }

    public void setVacations(List<Vacation> vacations){
        vacationListToView = vacations;

        currIndex = 0;
        nextPage();
    }

    private void show(){
        if(currIndex==0)
            btn_prev.setDisable(true);
        if(currIndex>=vacationListToView.size())
            btn_next.setDisable(true);

        if (v2==null){
            TVacationToV2.setVisible(false);
            originCountryV2.setVisible(false);
            TVacationFromV2.setVisible(false);
            btnOrderNowV2.setVisible(false);
            TairlineV2.setVisible(false);
            DestinationCountryV2.setVisible(false);
            airlineV2.setVisible(false);
            fillpriceV2.setVisible(false);
            LPriceV2.setVisible(false);
            ArrivalsDateV2.setVisible(false);
            DeparturesDateV2.setVisible(false);
            filldepartureDateV2.setVisible(false);
            fillarrivalsDateV2.setVisible(false);
        }
        else if(v2!=null){
            originCountryV2.setText(v2.getStartCountry());
            DestinationCountryV2.setText(v2.getDestCountry());
            airlineV2.setText(v2.getFlight().getFlightCompany());
            fillpriceV2.setText(Integer.toString(v2.getPrice()));
            filldepartureDateV2.setText(v2.getStartDate());
            fillarrivalsDateV2.setText(v2.getEndDate());
        }
        if(v1!=null){
            originCountryV1.setText(v1.getStartCountry());
            DestinationCountryV1.setText(v1.getDestCountry());
            airlineV1.setText(v1.getFlight().getFlightCompany());
            fillpriceV1.setText(Integer.toString(v1.getPrice()));
            filldepartureDateV1.setText(v1.getStartDate());
            fillarrivalsDateV1.setText(v1.getEndDate());

        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currIndex=0;


    }

    public  Vacation vacationToShow(List<Vacation> vacationList, int indexInList){
        vacationListToView =vacationList;
        if(vacationList.size()>0 && indexInList<vacationList.size())
            return vacationList.get(indexInList);

        return null;

    }
}
