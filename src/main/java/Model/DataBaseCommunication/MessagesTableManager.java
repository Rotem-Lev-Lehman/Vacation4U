package Model.DataBaseCommunication;

import Model.Message;
import Model.TradingMessage;
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
        String sql = "INSERT INTO messages(messageID,senderID,receiverID,message,seen,vacationID,vacationIDToTrade) VALUES(?,?,?,?,?,?,?)";

        int nextID = getNextID();
        //try to create user
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, nextID);
            pstmt.setString(2, message.getSender().getUsername());
            pstmt.setString(3, message.getReceiver().getUsername());
            pstmt.setString(4, message.getText());
            pstmt.setInt(5, 0);
            pstmt.setInt(6, message.getVacationID());

            int vacIdToTrade = -1;
            if(message instanceof TradingMessage)
                vacIdToTrade = ((TradingMessage)message).getVacationIDToTrade();
            pstmt.setInt(7, vacIdToTrade);

            pstmt.executeUpdate();
            message.setMessageID(nextID);
        }
        catch (SQLException e){
            //closeConnection(); //close connection to database
            //e.printStackTrace();
        }
        closeConnection(); //close connection
    }

    public void UpdateAsSeen(Message message) {
        connect(); //Connect to database
        //SQL commend
        String sql = "UPDATE messages SET seen = ? "
                + "WHERE messageID = ?";

        try {
            //Run sql commend
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            int seen = 1;
            pstmt.setInt(1, seen);
            pstmt.setInt(2, message.getMessageID());
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        closeConnection(); //disconnect from databse
    }

    public List<Message> ReadAllMessages(String username) {
        connect(); //connect to database

        //sql commend
        String sql = "SELECT messageID, senderID, receiverID, message, seen, vacationID, vacationIDToTrade FROM messages WHERE receiverID = ?";

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

                int vacationID = rs.getInt("vacationID");

                int vacationIDToTrade = rs.getInt("vacationIDToTrade");

                Message curr;
                if(vacationIDToTrade == -1)
                    curr = new Message(sender,receiver,rs.getString("message"),isSeen,vacationID);
                else
                    curr = new TradingMessage(sender,receiver,rs.getString("message"),isSeen,vacationID, vacationIDToTrade);
                
                curr.setMessageID(rs.getInt("messageID"));
                messages.add(curr);
            }
        }
        catch (SQLException e) {
            //e.printStackTrace();
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
            //e.printStackTrace();
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
            //e.printStackTrace();
        }
        return nextID;
    }
}
