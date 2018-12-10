package Model.DataBaseCommunication;

import Model.Flight;
import Model.User;
import Model.Vacation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class VacationsTableManager extends ATableManager {
    private FlightsTableManager flightsTable;
    private UsersTableManager usersTable;

    public VacationsTableManager(){
        flightsTable = new FlightsTableManager();
        usersTable = new UsersTableManager();
    }

    public void CreateVacation(Vacation vacation) {
        connect(); //connect to database

        int nextID = getNextID();
        vacation.setVacationID(nextID);

        //create vacation - sql command
        String sql = "INSERT INTO vacations(vacationID,sellerId,startDate,endDate,startCountry,destCountry,typeOfVacation,typeOfHotel,rankingOfHotel,typeOfLuggage,alreadySold,amountOfAdultTickets,amountOfChildTickets,amountOfInfantTickets,price) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        //try to create vacation
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, vacation.getVacationID());
            pstmt.setString(2, vacation.getSellerId().getUsername());
            pstmt.setString(3, vacation.getStartDate());
            pstmt.setString(4, vacation.getEndDate());
            pstmt.setString(5, vacation.getStartCountry());
            pstmt.setString(6, vacation.getDestCountry());
            pstmt.setString(7, vacation.getTypesOfVacation());
            pstmt.setString(8, vacation.getTypeOfHotel());
            pstmt.setInt(9, vacation.getRankingOfHotel());
            pstmt.setString(10, vacation.getTypeOfLuggage());

            int alreadySold = 0;
            if(vacation.isAlreadySold())
                alreadySold = 1;
            pstmt.setInt(11, alreadySold);
            pstmt.setInt(12, vacation.getAmountOfAdultTickets());
            pstmt.setInt(13, vacation.getAmountOfChildTickets());
            pstmt.setInt(14, vacation.getAmountOfInfantTickets());
            pstmt.setInt(15, vacation.getPrice());
            pstmt.executeUpdate();
            closeConnection(); //close connection
            flightsTable.CreateFlight(vacation.getFlight(), vacation.getVacationID());
        }
        catch (SQLException e){
            //closeConnection(); //close connection to datebase
            e.printStackTrace();
            closeConnection(); //close connection
        }

    }

    public List<Vacation> ReadSimilarVacationsNotBought(Vacation vacation, Comparator<Vacation> vacationsComparator) {
        connect(); //connect to database

        //sql commend
        String sql = "SELECT vacationID, sellerId, startDate, endDate, startCountry, destCountry, typesOfVacation, typeOfHotel, rankingOfHotel, typeOfLuggage, alreadySold, amountOfAdultTickets, amountOfChildTickets, amountOfInfantTickets, price FROM vacations WHERE startCountry LIKE ? AND destCountry LIKE ? AND alreadySold = ?";

        List<Vacation> vacations = new ArrayList<Vacation>(); //list of similar vacations
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the value
            pstmt.setString(1, "%" + vacation.getStartCountry() + "%");
            pstmt.setString(2, "%" + vacation.getDestCountry() + "%");
            pstmt.setInt(3, 0); //not bought
            //
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {

                String username = rs.getString("sellerId");
                User seller = usersTable.ReadWithOutConnection(username);

                int vacationID = rs.getInt("vacationID");

                Flight flight = flightsTable.GetFlightWithoutConnection(vacationID);

                int alreadySoldDataBase = rs.getInt("alreadySold");
                boolean alreadySold = false;
                if(alreadySoldDataBase == 1)
                    alreadySold = true;

                Vacation curr = new Vacation(seller,flight,rs.getString("startDate"),rs.getString("endDate"),rs.getString("startCountry"),rs.getString("destCountry"),
                        rs.getString("typesOfVacation"),rs.getString("typeOfHotel"),rs.getInt("rankingOfHotel"),rs.getString("typeOfLuggage"),alreadySold,rs.getInt("amountOfAdultTickets"),rs.getInt("amountOfChildTickets"),rs.getInt("amountOfInfantTickets"),rs.getInt("price"));
                curr.setVacationID(vacationID);
                vacations.add(curr);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        closeConnection(); //disconnect from database

        Collections.sort(vacations, vacationsComparator);
        return vacations;
    }

    public void UpdateVacation(Vacation vacation) {

    }

    private int getNextID() {
        String sql = "SELECT MAX(vacationID) AS max_id FROM vacations";
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
