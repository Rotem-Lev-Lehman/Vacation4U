package Model.DataBaseCommunication;

import Model.PaymentTransaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentsTableManager extends ATableManager {

    public void CreatePaymentTransaction(PaymentTransaction paymentTransaction) {
        connect(); //connect to database

        //create order - sql command
        String sql = "INSERT INTO paymentDetails(vacationID,payerID,receiverID,creditCardNumber,validDate,cvv,amount,paymentDate) VALUES(?,?,?,?,?,?,?,?)";

        //try to create order
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, paymentTransaction.getOrder().getVacation().getVacationID());
            pstmt.setString(2, paymentTransaction.getOrder().getBuyer().getUsername());
            pstmt.setString(3, paymentTransaction.getOrder().getVacation().getSellerId().getUsername());
            pstmt.setString(4, paymentTransaction.getCreditCardNumber());
            pstmt.setString(5, paymentTransaction.getValidDate());
            pstmt.setString(6, paymentTransaction.getCvv());
            pstmt.setInt(7, paymentTransaction.getAmount());
            pstmt.setString(8, paymentTransaction.getPaymentDate().toString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            //closeConnection(); //close connection to database
            e.printStackTrace();
        }
        closeConnection(); //close connection
    }
}