package View;

import Control.AController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;

public abstract class AView extends Observable implements IDefaultSettable {
    protected AController controller;

    //Set controller to a view
    public void setController(AController controller){
        this.controller = controller;
        this.addObserver(this.controller);
    }

    @Override
    public void setDefaults(Stage currentStage) {
        //Do nothing, only the ones who need to set defaults will implement it
    }

    //Move to a new screen - define its width, height and title
    protected void moveToNewScreen(int width, int height, String fxml, String title){
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = null;
        try {
            root = fxmlLoader.load(getClass().getResource("/" + fxml).openStream());

            AView view = fxmlLoader.getController();
            view.setController(controller);

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, width, height));
            stage.show();

            view.setDefaults(stage);

        } catch (IOException e) {
            System.out.println(e.toString());
        }


    }
}
