package Model.DataBaseCommunication;

import Model.PaymentTransaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentsTableManager extends ATableManager {

    public void CreatePaymentTransaction(PaymentTransaction paymentTransaction) {
        /*
        connect(); //connect to database

        //create order - sql command
        String sql = "INSERT INTO paymentDetails(vacationID,payerID,receiverID,creditCardNumber,validDate,cvv,amount,paymentDate) VALUES(?,?,?,?,?,?,?,?)";

        //try to create order
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, order.getVacation().getVacationID());
            pstmt.setString(2, order.getBuyer().getUsername());
            pstmt.setString(3, order.getVacation().getSellerId().getUsername());

            int status;
            if(order.getStatus() == OrderStatus.Accepted)
                status = 0;
            else if(order.getStatus() == OrderStatus.Declined)
                status = 1;
            else if(order.getStatus() == OrderStatus.WaitingForApproval)
                status = 2;
            else //OrderStatus.WaitingForPayment
                status = 3;
            pstmt.setInt(4, status);
            pstmt.executeUpdate();
        }
        catch (SQLException e){
            //closeConnection(); //close connection to datebase
            e.printStackTrace();
        }
        closeConnection(); //close connection
        */
    }
}
