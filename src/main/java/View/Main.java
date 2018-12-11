package View;


import Control.AController;
import Control.Controller;
import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        AModel model = new Model(); //create model

        AController controller = new Controller();
        controller.setModel(model);

        /*User user1 = new User("1","1","1","1","1","d");
        User user2 = new User("admin","1","1","1","1","d");
        model.CreateMessage(new Message(user2, user1, "Hey! admin wants to buy vacation", false,4));*/

        //open main page
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("/MainPage.fxml").openStream());
        ((AView)fxmlLoader.getController()).setController(controller);

        primaryStage.setTitle("Vacation4U");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
/*
public class Main{
    public static void main(String[] args){
        DataBaseManager dataBaseManager = new DataBaseManager();
        /*
        User user = new User("Pazyona","123456","25/1/1994","Paz","Yona","Beer Sheva");
        try {
            dataBaseManager.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
/*
        User user = dataBaseManager.Read("Pazyona");
        System.out.println(user);
        /*
        user.setCity("Beer Sheva");
        dataBaseManager.Update(user.getUsername(), user);
        */
/*
        dataBaseManager.Delete(user);

        User user2 = dataBaseManager.Read("Pazyona");
        System.out.println(user2);

    }
}
*/