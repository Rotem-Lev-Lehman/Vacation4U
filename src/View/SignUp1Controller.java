package View;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SignUp1Controller extends AController implements Initializable {

    public TextField firstName, lastName, city;
    public Text processMessage;
    public DatePicker date;
    public Button continueBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void ctnClicked(MouseEvent mouseEvent) {
        String firstNameText = firstName.getText();
        String lastNameText = lastName.getText();
        LocalDate localDate = date.getValue();
        String cityText = city.getText();

        if(firstNameText == null || lastNameText == null || localDate == null || cityText == null ||
                    firstNameText.equals("") || lastNameText.equals("") || cityText.equals("") || localDate.toString().equals("")){
            processMessage.setText("Please fill all details above");
            return;
        }
        moveToNewScreen(400, 395, "SignUp2.fxml", "Register");
        Stage currentStage = (Stage) continueBtn.getScene().getWindow();
        currentStage.close();
    }

}
