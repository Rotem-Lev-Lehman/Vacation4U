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
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

import static java.time.temporal.ChronoUnit.YEARS;

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

        if(!legalAge()){
            processMessage.setText("You must be above 18");
            return;
        }

        processMessage.setText("");
        moveToNewScreen(400, 395, "SignUp2.fxml", "Register");
        Stage currentStage = (Stage) continueBtn.getScene().getWindow();
        currentStage.close();
    }

    private boolean legalAge() {
        LocalDate dateNow = java.time.LocalDate.now();
        Period p = Period.between(date.getValue(), dateNow);
        return p.getYears() >= 18;
    }

    /*private boolean legalAge2() {
        LocalDate dateNow = java.time.LocalDate.now();
        System.out.println(YEARS.between(date.getValue(), dateNow));
        long differnce = YEARS.between(dateNow, date.getValue());
        System.out.println(differnce >= 18);
        return YEARS.between(dateNow, date.getValue()) >= 18;
    }*/

}
