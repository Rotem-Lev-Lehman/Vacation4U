package View;

import javafx.event.EventHandler;
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
import javafx.stage.WindowEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUp2View extends AView implements Initializable {

    public ImageView airplaneImage, backImage;
    public Button register;
    public TextField userName;
    public Text process_message;
    public PasswordField password, passwordCnf;
    private boolean pressedContinue;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pressedContinue = false;

        Image airplaneImg = new Image(this.getClass().getResourceAsStream("/images/airplane.png"));
        airplaneImage.setImage(airplaneImg);

        Image goBackImg = new Image(this.getClass().getResourceAsStream("/images/return_button.png"));
        backImage.setImage(goBackImg);
    }

    @Override
    public void setDefaults(Stage currentStage) {
        //Stage currentStage = (Stage) register.getScene().getWindow();
        currentStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                if(!pressedContinue)
                    controller.setUser(null);
            }
        });
    }

    public void goBack(MouseEvent mouseEvent) {
        pressedContinue = true;
        moveToNewScreen(400, 395, "SignUp1.fxml", "Register");
        Stage currentStage = (Stage) backImage.getScene().getWindow();
        currentStage.close();
    }

    public void registerUser(MouseEvent mouseEvent) {
        String usernameText = userName.getText();
        String passwordText = password.getText();
        String passwordCnfText = passwordCnf.getText();

        String[] strings = new String[3];
        strings[0] = usernameText;
        strings[1] = passwordText;
        strings[2] = passwordCnfText;

        setChanged();
        notifyObservers(strings);
    }

    public void setMessageFillDetails(){
        process_message.setText("Please fill all details above");
    }

    public void setMessageUsernameTaken(){
        process_message.setText("Username is taken, please pick a different name");
    }

    public void setMessagePasswordsDontMatch(){
        process_message.setText("Passwords don't match");
    }

    public void registrationComplete(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registration");
        alert.setHeaderText(null);
        alert.setContentText("Registration Successful");

        alert.showAndWait();
        pressedContinue = true;
        Stage currentStage = (Stage) register.getScene().getWindow();
        currentStage.close();
    }
}
