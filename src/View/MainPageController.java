package View;

import Model.AModel;
import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;


public class MainPageController extends AController implements Initializable {


    public TextField usernameTextField;
    public TextField passwordTextField;
    public Text notRegisteredTextField, message;
    public ImageView logo, userIcon, passwordIcon;

    public AModel model;



    public void LoginPressed(ActionEvent event){
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if(username == null  || password == null || username.equals("") || password.equals("")){
            message.setText("Please enter username and password");
            return;
        }

        //search database
        String[] str = new String[2];
        str[0] = username;
        str[1]=password;

        if (model.Read(username).getPassword()== password){
            UserExists();
        }
        else
            UserDoesNotExist();
    }

    public void UserExists(){


    }

    public void UserDoesNotExist(){

    }

    public void NotRegisteredPressed(MouseEvent mouseEvent) {
        moveToNewScreen(400, 350, "SignUp1.fxml", "Register");
        /*FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = null;
        try {
            root = fxmlLoader.load(getClass().getResource("SignUp1.fxml").openStream());
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        ((AController)fxmlLoader.getController()).setModel(model);
        Stage stage = new Stage();
        stage.setTitle("Register");
        stage.setScene(new Scene(root, 400, 350));
        stage.show();*/
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image logoImage = new Image(this.getClass().getResourceAsStream("/images/LogoSample_ByTailorBrands.jpg"));
        logo.setImage(logoImage);

        Image userImage = new Image(this.getClass().getResourceAsStream("/images/login_person.png"));
        userIcon.setImage(userImage);

        Image passwordImage = new Image(this.getClass().getResourceAsStream("/images/lock.jpeg"));
        passwordIcon.setImage(passwordImage);
    }
}
