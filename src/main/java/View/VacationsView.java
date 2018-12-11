package View;

import Model.Model;
import javafx.event.ActionEvent;
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

    public Button btn_next;
    public Button btn_prev;
    public int currIndex;
    public List<Vacation> vacationListToView;
    public Vacation v1;
    public Label TVacationFrom;
    public Label TVacationTo;

    public Label filldepartureDateV1;
    public Label fillarrivalsDateV1;


    public boolean v2IsChanged;

    public void VacationView(){

    }


    public void orderVacation(ActionEvent e){
        if(e.getSource() == btnOrderNowV1 ){
            setChanged();
            notifyObservers(v1);
        }
//        if(e.getSource() == btnOrderNowV2){
//            setChanged();
//            notifyObservers(v2);
//        }
    }

    public void userExist(){
        //move to next page - or make an order, ask ido
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Order Send");
        alert.setContentText("Order Send Successfully");
        alert.show();


    }

    public void userNotExist(){
        //error
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.setContentText("Only Exist User Can Order");
        alert.show();
        return;
    }
    public void userIsSeller(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("ERROR");
        alert.setContentText("Can't Buy This Vacation");
        alert.show();
        return;
    }

    public void prevPage(){


        if(currIndex-1>=0) {
            v1 = vacationToShow(vacationListToView, currIndex - 1);
            currIndex--;
            btn_next.setDisable(false);
        }

//        if(currIndex-2 >=0){
//           if(v2 != null) {
//                currIndex = currIndex - 2;
//                v1 = vacationToShow(vacationListToView, currIndex - 2);
//                v2 = vacationToShow(vacationListToView, currIndex - 1);
//
//                btn_next.setDisable(false);
//            }
//            else
//            {
//                currIndex = currIndex - 1;
//                v1 = vacationToShow(vacationListToView, currIndex - 2);
//                v2 = vacationToShow(vacationListToView, currIndex - 1);
//                btn_next.setDisable(false);
//                v2IsChanged = false;
//            }
            show();
            if(currIndex < 1)
                btn_prev.setDisable(true);
        }



  //  }

    public void nextPage(){


        v2IsChanged=false;
        if(currIndex  < 1)
            btn_prev.setDisable(true);
        else
            btn_prev.setDisable(false);
        if(vacationListToView.size()>currIndex && 0<=currIndex) {
            v1 = vacationToShow(vacationListToView, currIndex);
            currIndex++;
        }


        show();
        if(currIndex > vacationListToView.size() - 1)
            btn_next.setDisable(true);

    }

    public void setVacations(List<Vacation> vacations){
        vacationListToView = vacations;

        currIndex = 0;
        if(vacations.size() < 1) {
            btn_next.setDisable(true);
            btn_prev.setDisable(true);
        }
        nextPage();

    }

    private void show(){

        if(v1!=null ){
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
        v2IsChanged=false;


    }

    public  Vacation vacationToShow(List<Vacation> vacationList, int indexInList){
        vacationListToView =vacationList;
        if(vacationList.size()>0 && indexInList<vacationList.size())
            return vacationList.get(indexInList);

        return null;

    }
}
