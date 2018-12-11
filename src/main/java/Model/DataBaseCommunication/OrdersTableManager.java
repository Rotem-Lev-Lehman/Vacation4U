package Model.DataBaseCommunication;

import Model.Order;
import Model.OrderStatus;
import Model.User;
import Model.Vacation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersTableManager extends ATableManager {

    private UsersTableManager usersTable;

    public OrdersTableManager(){
        usersTable = new UsersTableManager();
    }

    public void CreateOrder(Order order){
        connect(); //connect to database

        //create order - sql command
        String sql = "INSERT INTO orders(vacationID,buyerID,sellerID,status) VALUES(?,?,?,?)";

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
    }

    public Order Read(Vacation vacation, User buyer){
        connect(); //connect to database

        //sql commend
        String sql = "SELECT vacationID, buyerID, sellerID, status FROM orders WHERE vacationID = ? AND buyerID = ?";

        Order order = null; //order
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the value
            pstmt.setInt(1, vacation.getVacationID());
            pstmt.setString(2, buyer.getUsername());
            //
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                int intStatus = rs.getInt("status");
                OrderStatus status;
                if(intStatus == 0)
                    status = OrderStatus.Accepted;
                else if(intStatus == 1)
                    status = OrderStatus.Declined;
                else if(intStatus == 2)
                    status = OrderStatus.WaitingForApproval;
                else // 3
                    status = OrderStatus.WaitingForPayment;

                order = new Order(vacation,buyer,status);
                break;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection(); //disconnect from database
        return order;
    }

    public List<Order> ReadOrdersForVacation(Vacation vacation) {
        connect(); //connect to database

        //sql commend
        String sql = "SELECT vacationID, buyerID, sellerID, status FROM orders WHERE vacationID = ?";

        List<Order> orders = new ArrayList<Order>(); //list of similar orders
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the value
            pstmt.setInt(1, vacation.getVacationID());
            //
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                int intStatus = rs.getInt("status");
                OrderStatus status;
                if(intStatus == 0)
                    status = OrderStatus.Accepted;
                else if(intStatus == 1)
                    status = OrderStatus.Declined;
                else if(intStatus == 2)
                    status = OrderStatus.WaitingForApproval;
                else // 3
                    status = OrderStatus.WaitingForPayment;

                User buyer = usersTable.Read(rs.getString("buyerID"));

                Order curr = new Order(vacation,buyer,status);

                orders.add(curr);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection(); //disconnect from database
        return orders;
    }

    public void UpdateOrderStatus(Order order) {
        connect(); //Connect to database
        //SQL commend
        String sql = "UPDATE orders SET status = ? "
                + "WHERE vacationID = ? AND buyerID = ?";

        try {
            //Run sql commend
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
            int status;
            if(order.getStatus() == OrderStatus.Accepted)
                status = 0;
            else if(order.getStatus() == OrderStatus.Declined)
                status = 1;
            else if(order.getStatus() == OrderStatus.WaitingForApproval)
                status = 2;
            else //OrderStatus.WaitingForPayment
                status = 3;
            pstmt.setInt(1, status);
            pstmt.setInt(2, order.getVacation().getVacationID());
            pstmt.setString(3, order.getBuyer().getUsername());

            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        closeConnection(); //disconnect from database
    }
}
