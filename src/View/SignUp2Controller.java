package View;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUp2Controller extends AController implements Initializable {

    public ImageView airplaneImage, backImage;
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
}
