package View;


import Model.User;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateController extends AController implements Initializable {

    public TextField FirstNameText, LastNameText, CityText, UserNameText;
    public Button UpdateButton;
    public PasswordField password;
    public Text process_message;
    public AnchorPane anchor;
    //public PasswordField passwordCnf;

   // public String oldUsername;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image background = new Image(this.getClass().getResourceAsStream("/images/background_update.jpg"));
        BackgroundImage myBI= new BackgroundImage(new Image("/images/background_update.jpg",440,400,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        anchor.setBackground(new Background(myBI));

        if(user != null){
            //oldUsername = UserNameText.getText();
            UserNameText.setText(user.getUsername());
            FirstNameText.setText(user.getFirstName());
            LastNameText.setText(user.getLastName());
            CityText.setText(user.getCity());
            password.setText(user.getPassword());
        }
    }

    public void Update(){
        process_message.setText("");
        String username = UserNameText.getText();
        String firstname = FirstNameText.getText();
        String lastname = LastNameText.getText();
        String city = CityText.getText();
        String passwordtxt = password.getText();
        if(username == null || firstname == null || lastname == null || city == null || passwordtxt == null ||
                username.equals("") || firstname.equals("") || lastname.equals("") || city.equals("") || passwordtxt.equals("")){
            process_message.setText("Please make sure all details are filled");
            return;
        }
        if(model.Read(username) != null && !user.getUsername().equals(username)){ //if username is taken or the user didn't change his username
            process_message.setText("Username taken, Pick a different one");
            return;
        }

        User newUser =new User(username, passwordtxt , user.getBirthdate() ,firstname, lastname, city);
        model.Update(user.getUsername(), newUser);

        showSuccessAlert();
    }

    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Saved");
        alert.setHeaderText(null);
        alert.setContentText("Successful Update!");

        alert.showAndWait();
    }


    /*public void Update(){
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


    }*/

}
