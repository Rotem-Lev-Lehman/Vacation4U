package View;

import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SearchPageView extends AView implements Initializable {

    public TextField searchTextField;
    public TableView<User> searchResultTable;
    public TableColumn<User,String> usernameColumn;
    public TableColumn<User,String> firstNameColumn;
    public TableColumn<User,String> lastNameColumn;
    public TableColumn<User,String> cityColumn;
    public TableColumn<User,String> birthDateColumn;
    public TableColumn<User,String> passwordColumn;
    public ImageView magViewBox;

    private ObservableList<User> userObservableList;

    public void searchButtonClicked(MouseEvent mouseEvent) {
        String userName = searchTextField.getText();

        if(userName == null || userName.equals("")){
            //say bad input
            showNoResult();
        }
        else{
            //Ask the controller to send users data
            setChanged();
            notifyObservers(userName);
        }
    }

    //In case there is no results, don't show anything
    public void showNoResult(){
        searchResultTable.setItems(null);
    }

    //Show users that were found
    public void showUsers(List<User> users){
        //show results
        userObservableList = FXCollections.observableArrayList(users);
        searchResultTable.setItems(userObservableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set columns name and set image to imageView
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        Image magGlass = new Image(this.getClass().getResourceAsStream("/images/mag_glass.png"));
        magViewBox.setImage(magGlass);
    }
}
