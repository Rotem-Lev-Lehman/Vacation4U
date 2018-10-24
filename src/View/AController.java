package View;

import Model.AModel;
import Model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class AController {
    protected static AModel model;
    protected static User user;

    public void setModel(AModel model){
        this.model = model;
    }

    public AModel getModel(){
        return model;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void moveToNewScreen(int width, int height, String fxml, String title){
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = null;
        try {
            root = fxmlLoader.load(getClass().getResource(fxml).openStream());
            ((AController)fxmlLoader.getController()).setModel(model);
            ((AController)fxmlLoader.getController()).setUser(user);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, width, height));
            stage.show();
        } catch (IOException e) {
            System.out.println(e.toString());
        }


    }
}
