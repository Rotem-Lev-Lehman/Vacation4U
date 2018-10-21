package View;

import Model.AModel;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.Observable;


public class MainPageController {


    public TextField usernameTextField;
    public TextField passwordTextField;
    public Text NotregisteredTextField;
    public AModel model;
    public MainPageController(AModel model){
        this.model = model;
    }
    
    public void LoginPressed(ActionEvent event){
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if (username!=null && password!=null)
        {
            //search database
            String[] str = new String[2];
            str[0] = username;
            str[1]=password;

            if (model.Read(username).getPassword()
        }

    }

    public void UserExists(){

    }

    public void UserDoesNotExist(){

    }

    public void NotRegistedPressed(MouseEvent mouseEvent) {
        
    }
}
