package View;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUp2Controller extends AController implements Initializable {

    public ImageView airplaneImage, backImage;
    public Button register;
    public TextField userName;
    public Text process_message;
    public PasswordField password, passwordCnf;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image airplaneImg = new Image(this.getClass().getResourceAsStream("/images/airplane.png"));
        airplaneImage.setImage(airplaneImg);

        Image goBackImg = new Image(this.getClass().getResourceAsStream("/images/return_button.png"));
        backImage.setImage(goBackImg);
    }

    public void goBack(MouseEvent mouseEvent) {
        moveToNewScreen(400, 395, "SignUp1.fxml", "Register");
        Stage currentStage = (Stage) backImage.getScene().getWindow();
        currentStage.close();
    }

    public void registerUser(MouseEvent mouseEvent) {
        String usernameText = userName.getText();
        String passwordText = password.getText();
        String passwordCnfText = passwordCnf.getText();
        if(usernameText.equals("") || passwordText.equals("") || passwordCnfText.equals("") ||
                    usernameText == null || passwordText == null || passwordCnfText == null){
            process_message.setText("Please fill all details above");
            return;
        }

        if(model.Read(usernameText) != null) { //user exists
            process_message.setText("Username is taken, please pick a different name");
            return;
        }

        if(!passwordText.equals(passwordCnfText)){
            process_message.setText("Passwords don't match");
            return;
        }

        user.setUsername(usernameText);
        user.setPassword(passwordText);
        try {
            model.Create(user);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registration");
        alert.setHeaderText(null);
        alert.setContentText("Registration Successful");

        alert.showAndWait();
        Stage currentStage = (Stage) register.getScene().getWindow();
        currentStage.close();
    }
}
