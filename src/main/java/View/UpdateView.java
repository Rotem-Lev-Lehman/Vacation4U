package View;


import Model.User;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * View for update screen
 */
public class UpdateView extends AView implements Initializable {

    public TextField FirstNameText, LastNameText, CityText, UserNameText;
    public Button UpdateButton;
    public PasswordField password;
    public Text process_message;
    public AnchorPane anchor;
    public DatePicker BirthDate;
    public Circle profile_image_circle;
    private File imageFile;
    //public PasswordField passwordCnf;

   // public String oldUsername;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set background for screen
        Image background = new Image(this.getClass().getResourceAsStream("/images/background_update.jpg"));
        BackgroundImage myBI= new BackgroundImage(new Image("/images/background_update.jpg",440,400,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        anchor.setBackground(new Background(myBI));
    }

    @Override
    public void setDefaults(Stage currentStage){
        //Set the user details in matching textfields.
        if(!controller.isUserNull()){
            User user = controller.getUser();
            //oldUsername = UserNameText.getText();
            UserNameText.setText(user.getUsername());
            FirstNameText.setText(user.getFirstName());
            LastNameText.setText(user.getLastName());
            CityText.setText(user.getCity());
            password.setText(user.getPassword());
            LocalDate date = LocalDate.parse(user.getBirthdate());
            BirthDate.setValue(date);
            Image profileImage = user.getProfileImage();
            if(profileImage !=null)
                profile_image_circle.setFill(new ImagePattern(profileImage));
            else{
                Image defaultProfileImage = new Image(this.getClass().getResourceAsStream("/images/defaultProfileImage.png"));
                profile_image_circle.setFill(new ImagePattern(defaultProfileImage));
            }

        }
    }

    public void Update(){
        //Send updated information to the controller
        process_message.setText("");
        String username = UserNameText.getText();
        String firstname = FirstNameText.getText();
        String lastname = LastNameText.getText();
        String city = CityText.getText();
        String passwordtxt = password.getText();
        LocalDate birthDate = BirthDate.getValue();

        Object[] objects = new Object[7];
        objects[0] = username;
        objects[1] = firstname;
        objects[2] = lastname;
        objects[3] = city;
        objects[4] = passwordtxt;
        objects[5] = birthDate;
        objects[6] = imageFile;

        setChanged();
        notifyObservers(objects);
    }

    //Show error message in case user didn't fill all information - function is called by controller
    public void setMessageFillDetails(){
        process_message.setText("Please make sure all details are filled");
    }

    //Show error message in cause user picked a user that is taken - function is called by controller
    public void setMessageUsernameTaken(){
        process_message.setText("Username taken, Pick a different one");
    }

    //Show successful message if information was updated successfully
    public void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Saved");
        alert.setHeaderText(null);
        alert.setContentText("Successful Update!");

        alert.showAndWait();
    }

    public void openImagePicker(MouseEvent mouseEvent) {
        final FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(imageFilter);
        Stage currentStage = (Stage) profile_image_circle.getScene().getWindow();
        imageFile = fileChooser.showOpenDialog(currentStage);
        if(imageFile != null) {
            Image profileImage = new Image(imageFile.toURI().toString());
            profile_image_circle.setFill(new ImagePattern(profileImage));
        }
    }
}
