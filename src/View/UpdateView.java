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
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateView extends AView implements Initializable {

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
    }

    @Override
    public void setDefaults(Stage currentStage){
        if(!controller.isUserNull()){
            User user = controller.getUser();
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

        String[] strings = new String[5];
        strings[0] = username;
        strings[1] = firstname;
        strings[2] = lastname;
        strings[3] = city;
        strings[4] = passwordtxt;

        setChanged();
        notifyObservers(strings);
    }

    public void setMessageFillDetails(){
        process_message.setText("Please make sure all details are filled");
    }

    public void setMessageUsernameTaken(){
        process_message.setText("Username taken, Pick a different one");
    }

    public void showSuccessAlert() {
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
