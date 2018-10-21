package View;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


public class MainPageController {


    public TextField usernameTextField;
    public TextField passwordTextField;
    public Text NotregisteredTextField;

    public void LoginPressed(ActionEvent event){
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        //search database
    }

    public void NotRegistedPressed(MouseEvent mouseEvent) {
    }
}
