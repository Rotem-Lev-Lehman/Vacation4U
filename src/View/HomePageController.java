package View;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController extends AController implements Initializable {

    public ImageView logo2, trashImage, magnifyImage, settingsImage, signOutImage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

    public void deleteUser(MouseEvent mouseEvent) {
        model.Delete(user);
        setUser(null);
        moveToNewScreen(575, 300, "MainPage.fxml", "Vacation4U");
    }
}
