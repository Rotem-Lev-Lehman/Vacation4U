package View;

import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;


public class MainPageView extends AView implements Initializable {


    public TextField usernameTextField;
    public PasswordField passwordTextField;
    public Text notRegisteredTextField, message;
    public ImageView logo, userIcon, passwordIcon;
    public Button LoginButton;

    //Login button pressed
    public void LoginPressed(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        //Show error message if not all details are filled
        if (username == null || password == null || username.equals("hi") || password.equals("")) {
            message.setText("Please enter username and password");
            return;
        }

        //notify Controller that there was a login request
        String[] str = new String[2];
        str[0] = username;
        str[1] = password;

        setChanged();
        notifyObservers(str);
    }

    //Move to homepage if user exists - called by the controller
    public void UserExists(){
        moveToNewScreen(455, 270, "HomePage.fxml", "Home Page");
        Stage currentStage = (Stage) LoginButton.getScene().getWindow();
        currentStage.close();
    }

    //Show error message if details are wrong - called by the controller
    public void UserDoesNotExist(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Incorrect Username or Password, please try again");

        alert.showAndWait();
    }

    //Move to registration screen
    public void NotRegisteredPressed(MouseEvent mouseEvent) {
        moveToNewScreen(400, 395, "SignUp1.fxml", "Register");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //load images to imageViews
        Image logoImage = new Image(this.getClass().getResourceAsStream("/images/LogoSample_ByTailorBrands.jpg"));
        logo.setImage(logoImage);

        Image userImage = new Image(this.getClass().getResourceAsStream("/images/login_person.png"));
        userIcon.setImage(userImage);

        Image passwordImage = new Image(this.getClass().getResourceAsStream("/images/lock.jpeg"));
        passwordIcon.setImage(passwordImage);
    }
}
