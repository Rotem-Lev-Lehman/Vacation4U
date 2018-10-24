package View;

import Model.User;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SearchPageController extends AController implements Initializable {

    public TextField searchTextField;
    public TableView searchResultTable;
    private ObservableList<User> userObservableList;

    public void searchButtonClicked(ActionEvent actionEvent) {
        String userName = searchTextField.getText();

        if(userName == null || userName.equals("")){
            //say bad input
        }
        else{
            //Search for the user in the DataBase
            List<User> users = model.ReadSimilar(userName);
            if(users == null || users.size() == 0){
                //say no results
            }
            else{
                //show results
                userObservableList = FXCollections.observableArrayList(users);
                //userObservableList.addAll(users);

                searchResultTable.setItems(userObservableList);

            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
