package View;

import Model.Message;
import Model.User;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MailBoxView extends AView implements Initializable {
    public TableView<Message> messagesTable;
    public TableColumn<Message, String> messageColumn;
    public TableColumn<Message, String> fromColumn;
    public TableColumn<Message, String> seenColumn;

    private ObservableList<Message> messagesObservableList;
    private volatile boolean searchMessages = true;

    //Show users that were found
    public void showMessages(List<Message> messages){
        //show results
        List<Message> reversed = new ArrayList<>();
        for (int i = messages.size()-1; i >=0; i--) {
            reversed.add(messages.get(i));
        }
        messagesObservableList = FXCollections.observableArrayList(reversed/*messages*//*tableMessages*/);
        messagesTable.setItems(messagesObservableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set columns name and set image to imageView
        //messageColumn.setCellValueFactory(new PropertyValueFactory<>("from"));
        messageColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getText()));
        fromColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSender().getUsername()));/*new PropertyValueFactory<>("receiverID")*/
        seenColumn.setCellValueFactory(new PropertyValueFactory<>("seen"));
        messagesTable.setRowFactory(tv -> {
            TableRow<Message> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                    Message clickedRow = row.getItem();
                    setChanged();
                    notifyObservers(clickedRow);
                    openMessageBox(clickedRow.getText(), clickedRow.getSender().getUsername(), clickedRow.isSeen(), clickedRow.getVacationID());
            });
            return row ;
        });

        Thread checkForNewMessages = new Thread(new Runnable() {
            @Override
            public void run() {
                while (searchMessages) {
                    setChanged();
                    notifyObservers("Get Messages");
                    try {
                        Thread.sleep(1000);
                        Stage stage = (Stage)messagesTable.getScene().getWindow();
                        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent event) {
                                searchMessages = false;
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        checkForNewMessages.start();
    }

    private void openMessageBox(String message, String from, boolean seen, int vacationID) {
        //Parent root = null;
        try {
            /*FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MessageBox.fxml"));
            AView view = loader.getController();
            root = loader.load();
            MessageBoxView messageBoxView = loader.getController();
            messageBoxView.setMessage(message, from); //set the dictionary
            Stage stage = new Stage();
            stage.setTitle("Message");
            stage.setScene(new Scene(root, 600, 320));
            stage.show();
            view.setDefaults(stage);*/
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent root = null;
            root = fxmlLoader.load(getClass().getResource("/MessageBox.fxml").openStream());

            AView view = fxmlLoader.getController();
            view.setController(controller);
            ((MessageBoxView)fxmlLoader.getController()).setMessage(message, from, vacationID);

            Stage stage = new Stage();
            stage.setTitle("Message");
            stage.setScene(new Scene(root, 600, 320));
            stage.show();

            view.setDefaults(stage);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showNoResult() {
        messagesTable.setItems(null);
    }
}
