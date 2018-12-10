package View;

import Model.Message;
import Model.User;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
        List<TableMessage> tableMessages = new ArrayList<>();
        for(Message msg : messages)
            tableMessages.add(new TableMessage(msg.getText(), msg.getSender().getUsername(),msg.isSeen()));
        messagesObservableList = FXCollections.observableArrayList(messages/*tableMessages*/);
        messagesTable.setItems(messagesObservableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set columns name and set image to imageView
        //messageColumn.setCellValueFactory(new PropertyValueFactory<>("from"));
        messageColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getText()));
        fromColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSender().getUsername().toString()));/*new PropertyValueFactory<>("receiverID")*/
        seenColumn.setCellValueFactory(new PropertyValueFactory<>("seen"));
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

    public void showNoResult() {
        messagesTable.setItems(null);
    }

    private class TableMessage{
        String m_message;
        String m_from;
        boolean m_seen;

        public TableMessage(String message, String from, boolean seen){
            m_message = message;
            m_from = from;
            m_seen = seen;
        }
    }
}
