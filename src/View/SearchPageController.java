package View;

import Model.User;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

public class SearchPageController extends AController {

    public TextField searchTextField;
    public ListView searchResultList;
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
                userObservableList = new SimpleListProperty<User>();
                userObservableList.addAll(users);
                searchResultList.setItems(userObservableList);

            }
        }
    }
}
