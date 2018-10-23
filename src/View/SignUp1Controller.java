package View;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUp1Controller extends AController implements Initializable {

    public TextField firstName, lastName, city;
    public DatePicker date;
    public Button continueBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void ctnClicked(MouseEvent mouseEvent) {
        moveToNewScreen(400, 350, "SignUp2.fxml", "Register");
        Stage currentStage = (Stage) continueBtn.getScene().getWindow();
        currentStage.close();
    }

}
