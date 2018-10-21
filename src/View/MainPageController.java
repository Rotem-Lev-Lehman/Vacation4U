package View;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.Observable;


public class MainPageController extends Observable {


    public TextField usernameTextField;
    public TextField passwordTextField;
    public Text NotregisteredTextField;

    public void LoginPressed(ActionEvent event){
        //String username = usernameTextField.getText();
        //String password = passwordTextField.getText();

        //search database
        setChanged();
        notifyObservers("Login Request");
    }

    public void UserExists(){

    }

    public void UserDoesNotExist(){

    }

    public void NotRegistedPressed(MouseEvent mouseEvent) {
        
    }
}
