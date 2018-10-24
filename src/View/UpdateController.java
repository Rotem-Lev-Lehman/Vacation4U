package View;

import Model.Model;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.security.PublicKey;
import java.util.ResourceBundle;

public class UpdateController extends AController implements Initializable {

    public TextField FirstNameText;
    public TextField LastNameText;
    public TextField CityText;
    public Button UpdateButton;
    public PasswordField password;
    public Text process_message;
    public PasswordField passwordCnf;

    public UpdateController(TextField firstNameText, TextField lastNameText, TextField cityText, Button updateButton, PasswordField password) {
        FirstNameText = firstNameText;
        LastNameText = lastNameText;
        CityText = cityText;
        UpdateButton = updateButton;
        this.password = password;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(user != null){
            FirstNameText.setText(user.getFirstName());
            LastNameText.setText(user.getLastName());
            CityText.setText(user.getCity());
            password.setText(user.getPassword());
        }
    }
    public void Update(MouseEvent event){
        String firstname = FirstNameText.getText();
        String lastname = LastNameText.getText();
        String city = CityText.getText();
        String passw = password.getText();
        if(firstname == null  || lastname == null || city == null || passw == null || password.equals("")){
            process_message.setText("Please enter username and password");
            return;
        }
        if(!password.equals(passwordCnf)){
            process_message.setText("Passwords don't match");
            return;
        }
    }

}
