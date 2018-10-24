package View;

import Model.User;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SearchPageController extends AController implements Initializable {

    public TextField searchTextField;
    public TableView<User> searchResultTable;
    public TableColumn<User,String> usernameColumn;
    public TableColumn<User,String> firstNameColumn;
    public TableColumn<User,String> lastNameColumn;
    public TableColumn<User,String> cityColumn;
    public TableColumn<User,String> birthDateColumn;
    public ImageView magViewBox;
    private ObservableList<User> userObservableList;

    public void searchButtonClicked(MouseEvent mouseEvent) {
        String userName = searchTextField.getText();

        if(userName == null || userName.equals("")){
            //say bad input
            searchResultTable.setItems(null);
        }
        else{
            //Search for the user in the DataBase
            List<User> users = model.ReadSimilar(userName);
            if(users == null || users.size() == 0){
                //say no results
                searchResultTable.setItems(null);
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
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        Image magGlass = new Image(this.getClass().getResourceAsStream("/images/mag_glass.png"));
        magViewBox.setImage(magGlass);
    }
}
