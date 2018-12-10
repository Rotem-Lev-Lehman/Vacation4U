package Model.DataBaseCommunication;

import Model.Message;
import Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessagesTableManager extends ATableManager {

    public void Create(Message message) throws SQLException {
        connect(); //connect to database
        //create user - sql command
        String sql = "INSERT INTO messages(messageID,senderID,receiverID,message,seen) VALUES(?,?,?,?,?)";

        //try to create user
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, String.valueOf(message.getMessageID()));
            pstmt.setString(2, String.valueOf(message.getSender()));
            pstmt.setString(3, String.valueOf(message.getReceiver()));
            pstmt.setString(4, message.getText());
            pstmt.setString(5, "0");
            pstmt.executeUpdate();
        }
        catch (SQLException e){
            closeConnection(); //close connection to datebase
            throw e;
        }
        closeConnection(); //close connection
    }

    public void UpdateAsSeen(Message message) {
        connect(); //Connect to databse
        //SQL commend
        String sql = "UPDATE users SET seen = ? , "
                + "senderID = ? , "
                + "receiverID = ? , "
                + "message = ? , "
                + "seen = ? , "
                + "WHERE messageID = ?";

        try {
            //Run sql commend
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, String.valueOf(message.getMessageID()));
            pstmt.setString(2, String.valueOf(message.getSender()));
            pstmt.setString(3, String.valueOf(message.getReceiver()));
            pstmt.setString(4, message.getText());
            pstmt.setString(5, "1");;
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(); //disconnect from databse
    }

    public int countUnseenMessages(String username) {
        String sql = "SELECT COUNT(messageID) AS amount FROM messages WHERE seen = ? AND receiverID = ?";
        int amount = 0;
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,0);
            pstmt.setString(2,username);
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                amount += rs.getInt("amount");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return amount;
    }
}
