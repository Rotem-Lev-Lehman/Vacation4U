package View;


import Model.Vacation;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class VacationTableView extends AView implements Initializable {

    public TableView<Vacation> table;
    public TableColumn<Vacation, String> departureColumn, destColumn, departure_dateColumn, arrival_dateColumn, priceColumn;

    private ObservableList<Vacation> vacationsObservableList;
    private Vacation vacationToTrade;

    public void setVacations(List<Vacation> vacations){
        //show results
        List<Vacation> reversed = new ArrayList<>();
        for (int i = vacations.size()-1; i >=0; i--) {
            reversed.add(vacations.get(i));
        }
        vacationsObservableList = FXCollections.observableArrayList(reversed);
        table.setItems(vacationsObservableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        departureColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStartCountry()));
        destColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDestCountry()));
        departure_dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStartDate()));
        arrival_dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEndDate()));
        priceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrice())));

        table.setRowFactory(tv -> {
            TableRow<Vacation> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                Vacation clickedRow = row.getItem();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Trade Vacation");
                alert.setHeaderText("Trade Vacation");
                alert.setContentText("Are you sure you want to trade this vacation?");
                //alert.show();
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){

                    Vacation[] vacNotify = new Vacation[2];
                    vacNotify[0] = vacationToTrade;
                    vacNotify[1] = clickedRow;
                    setChanged();
                    notifyObservers(vacNotify);

                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Trade Order");
                    alert1.setHeaderText("Trade Order Sent");
                    alert1.setContentText("Trade Order Sent, waiting for approval");
                    alert1.show();
                }
            });
            return row;
        });
    }

    public void setVacationToTrade(Vacation vacationToTrade) {
        this.vacationToTrade = vacationToTrade;
    }
}
