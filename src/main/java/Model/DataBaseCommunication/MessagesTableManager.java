package Model.DataBaseCommunication;

import Model.Message;
import Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessagesTableManager extends ATableManager {
    private UsersTableManager usersTable;

    public MessagesTableManager(){
        usersTable = new UsersTableManager();
    }

    public void Create(Message message) {
        connect(); //connect to database
        //create user - sql command
        String sql = "INSERT INTO messages(messageID,senderID,receiverID,message,seen) VALUES(?,?,?,?,?)";

        int nextID = getNextID();
        //try to create user
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, nextID);
            pstmt.setString(2, message.getSender().getUsername());
            pstmt.setString(3, message.getReceiver().getUsername());
            pstmt.setString(4, message.getText());
            pstmt.setInt(5, 0);
            pstmt.executeUpdate();
            message.setMessageID(nextID);
        }
        catch (SQLException e){
            //closeConnection(); //close connection to datebase
            e.printStackTrace();
        }
        closeConnection(); //close connection
    }

    public void UpdateAsSeen(Message message) {
        connect(); //Connect to databse
        //SQL commend
        String sql = "UPDATE messages SET senderID = ? , "
                + "receiverID = ? , "
                + "message = ? , "
                + "seen = ? "
                + "WHERE messageID = ?";

        try {
            //Run sql commend
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            pstmt.setString(1, message.getSender().getUsername());
            pstmt.setString(2, message.getReceiver().getUsername());
            pstmt.setString(3, message.getText());

            int seen = 0;
            if(message.isSeen())
                seen = 1;
            pstmt.setInt(4, seen);
            pstmt.setInt(5, message.getMessageID());
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(); //disconnect from databse
    }

    public List<Message> ReadAllMessages(String username) {
        connect(); //connect to database

        //sql commend
        String sql = "SELECT messageID, senderID, receiverID, message, seen FROM messages WHERE receiverID = ?";

        List<Message> messages = new ArrayList<Message>(); //list of similar messages
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the value
            pstmt.setString(1, username);
            //
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {

                String senderName = rs.getString("senderID");
                User sender = usersTable.ReadWithOutConnection(senderName);

                String receiverName = rs.getString("receiverID");
                User receiver = usersTable.ReadWithOutConnection(receiverName);

                int seen = rs.getInt("seen");
                boolean isSeen = false;
                if(seen == 1)
                    isSeen = true;

                Message curr = new Message(sender,receiver,rs.getString("message"),isSeen);
                curr.setMessageID(rs.getInt("messageID"));
                messages.add(curr);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection(); //disconnect from database

        return messages;
    }

    public int countUnseenMessages(String username) {
        connect();
        String sql = "SELECT COUNT(messageID) AS amount FROM messages WHERE seen = ? AND receiverID = ?";
        int amount = 0;
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,0);
            pstmt.setString(2,username);
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                amount = rs.getInt("amount");
                break;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        closeConnection();
        return amount;
    }

    private int getNextID() {
        String sql = "SELECT MAX(messageID) AS max_id FROM messages";
        int nextID = -1;
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                nextID = rs.getInt("max_id");
                break;
            }
            nextID++;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return nextID;
    }
}
