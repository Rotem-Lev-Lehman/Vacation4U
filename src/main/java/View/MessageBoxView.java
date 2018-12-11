package View;

import javafx.scene.text.Text;

public class MessageBoxView extends AView {
    public Text message;
    public Text from;

    public void setMessage(String message, String from) {
        this.message.setText(message);
        this.from.setText(from);
    }
}
