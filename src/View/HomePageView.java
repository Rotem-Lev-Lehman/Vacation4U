package View;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomePageView extends AView implements Initializable {

    public ImageView logo2, trashImage, magnifyImage, settingsImage, signOutImage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set images to ImageViews
        Image logoImage = new Image(this.getClass().getResourceAsStream("/images/vac_logo.jpg"));
        logo2.setImage(logoImage);

        Image trashImg = new Image(this.getClass().getResourceAsStream("/images/trash.png"));
        trashImage.setImage(trashImg);

        Image magnifyImg = new Image(this.getClass().getResourceAsStream("/images/mag_glass.png"));
        magnifyImage.setImage(magnifyImg);

        Image settingsImg = new Image(this.getClass().getResourceAsStream("/images/settings_icon.png"));
        settingsImage.setImage(settingsImg);

        Image logoutImg = new Image(this.getClass().getResourceAsStream("/images/log_out_icon.png"));
        signOutImage.setImage(logoutImg);
    }

    //Delete user button was pressed
    public void deleteUser(MouseEvent mouseEvent) {
        if(!controller.isUserNull()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            String s = "Are you sure you want to delete your user?"; //makes sure the user wants to delete his account
            alert.setContentText(s);

            Optional<ButtonType> result = alert.showAndWait();

            if ((result.isPresent()) && (result.get() == ButtonType.OK)) { //if user wants to delete his account
                setChanged();
                notifyObservers("Delete user"); //Notify controller that user wants to delete his account
            }
        }
    }

    //Move to settings screen
    public void goToSettings(MouseEvent mouseEvent) {
        moveToNewScreen(440, 400, "Update.fxml", "Settings");
    }

    //Sign out user - called by controller
    public void signOut(){
        moveToNewScreen(575, 300, "MainPage.fxml", "Vacation4U");
        Stage currentStage = (Stage) signOutImage.getScene().getWindow();
        currentStage.close();
    }

    //User clicked on sign out button - notify controller
    public void signOutClicked(MouseEvent mouseEvent) {
        controller.setUser(null);
        signOut();
    }

    //Move to search screen
    public void searchUser(MouseEvent mouseEvent) {
        moveToNewScreen(575, 300, "SearchPage.fxml", "Search");
    }
}
