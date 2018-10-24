package View;

import Model.Model;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateController extends AController implements Initializable {

    public TextField FirstNameText;
    public TextField LastNameText;
    public TextField CityText;
    public TextField UserNameText;

    public Button UpdateButton;
    public PasswordField password;
    public Text process_message;
    public PasswordField passwordCnf;

    public String oldUsername;

    public UpdateController(TextField UserName, TextField firstNameText, TextField lastNameText, TextField cityText, Button updateButton, PasswordField password) {
        UserNameText =UserName;
        FirstNameText = firstNameText;
        LastNameText = lastNameText;
        CityText = cityText;
        UpdateButton = updateButton;
        this.password = password;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(user != null){
            oldUsername = UserNameText.getText();
            UserNameText.setText(user.getUsername());
            FirstNameText.setText(user.getFirstName());
            LastNameText.setText(user.getLastName());
            CityText.setText(user.getCity());
            password.setText(user.getPassword());
        }
    }
    public void Update(){
        String username = UserNameText.getText();
        String firstname = FirstNameText.getText();
        String lastname = LastNameText.getText();
        String city = CityText.getText();
        String passw = password.getText();
        if(username == null  ||firstname == null  || lastname == null || city == null || passw == null || password.equals("") || model.Read(username)==null){
            process_message.setText("Please enter username and password");
            return;
        }
        else if(model.Read(username)!=null){
            process_message.setText("This Username already exist! Please choose another username");
            return;
        }

        else  {
            User user =new User(username,passw,model.Read(oldUsername).getBirthdate(),firstname, lastname, city);
            model.Update(oldUsername,user);
        }


    }

}
