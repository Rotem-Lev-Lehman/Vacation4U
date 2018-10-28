package View;

import Model.User;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SignUp1View extends AView implements Initializable {

    public TextField firstName, lastName, city;
    public Text processMessage;
    public DatePicker date;
    public Button continueBtn;
    private boolean pressedContinue; //boolean variable to determinate rather user should be null on exist or not

    //user should be null if existed
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pressedContinue = false;
    }

    @Override
    public void setDefaults(Stage currentStage) {
        //If the user went back to this screen from second sign up screen, then load the details that were already filled
        if(!controller.isUserNull()){
            User user = controller.getUser();
            firstName.setText(user.getFirstName());
            lastName.setText(user.getLastName());
            city.setText(user.getCity());
            date.setValue(LocalDate.parse(user.getBirthdate()));
        }

        //When screen closed - if the user should be null turn into null and if not then don't
        //User should be null if the user exited the registration process
        //User should not be null if the user moves between the different signup screen
        currentStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                if(!pressedContinue)
                    controller.setUser(null);
            }
        });
    }

    //Move to the second Sign up screen and save the current filled information - done by controller
    public void ctnClicked(MouseEvent mouseEvent) {
        String firstNameText = firstName.getText();
        String lastNameText = lastName.getText();
        LocalDate localDate = date.getValue();
        String cityText = city.getText();

        Object[] objects = new Object[4];
        objects[0] = firstNameText;
        objects[1] = lastNameText;
        objects[2] = localDate;
        objects[3] = cityText;

        setChanged();
        notifyObservers(objects);
    }

    //Show error message in case user didn't fill all information - function is called by controller
    public void setMessageFillDetails(){
        processMessage.setText("Please fill all details above");
    }

    //Show error message in case user is under 18 - function is called by controller
    public void setMessageLegalAge(){
        processMessage.setText("You must be above 18");
    }

    //move to new screen if first part of registration was successfully
    public void successfullySignedUp(){
        processMessage.setText("");
        pressedContinue = true;
        moveToNewScreen(400, 395, "SignUp2.fxml", "Register");
        Stage currentStage = (Stage) continueBtn.getScene().getWindow();
        currentStage.close();
    }

    /*private boolean legalAge2() {
        LocalDate dateNow = java.time.LocalDate.now();
        System.out.println(YEARS.between(date.getValue(), dateNow));
        long differnce = YEARS.between(dateNow, date.getValue());
        System.out.println(differnce >= 18);
        return YEARS.between(dateNow, date.getValue()) >= 18;
    }*/

}
