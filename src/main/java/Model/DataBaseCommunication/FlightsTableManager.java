package Model.DataBaseCommunication;

import Model.Flight;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class FlightsTableManager extends ATableManager {
    public void CreateFlight(Flight flight, int vacationID){
        connect(); //connect to database
        CreateFlightWithoutConnection(flight,vacationID, 0);
        closeConnection(); //close connection
    }

    public int CreateFlightWithoutConnection(Flight flight, int vacationID, int flightID){

        //create flight - sql command
        String sql = "INSERT INTO flights(vacationID,flightID,flightCompany,startCountry,destCountry,startDate,endDate,continueFlightID,returnFlightID) VALUES(?,?,?,?,?,?,?,?,?)";

        //try to create flight
        int continueFlightID = -1;
        int returnFlightID = -1;
        try {

            if(flight.getContinueFlight() != null) {
                continueFlightID = flightID + 1;
                returnFlightID = CreateFlightWithoutConnection(flight.getContinueFlight(), vacationID, continueFlightID);
            }

            if(flight.getReturnFlight() != null){
                if(returnFlightID == -1)
                    returnFlightID = flightID + 1;
                returnFlightID = CreateFlightWithoutConnection(flight.getReturnFlight(), vacationID, returnFlightID);
            }

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, vacationID);
            pstmt.setInt(2, flightID);
            pstmt.setString(3, flight.getFlightCompany());
            pstmt.setString(4, flight.getStartCountry());
            pstmt.setString(5, flight.getDestCountry());
            pstmt.setString(6, flight.getStartDateAsString());
            pstmt.setString(7, flight.getEndDateAsString());


            pstmt.setInt(8, continueFlightID);
            pstmt.setInt(9, returnFlightID);
            pstmt.executeUpdate();
        }
        catch (SQLException e){
            //closeConnection(); //close connection to datebase
            e.printStackTrace();
        }

        if(returnFlightID == -1)
            return flightID + 1;
        return returnFlightID;
    }

    public Flight GetFlight(int vacationID){
        connect();
        Flight flight = GetFlightRecursively(vacationID, 0);
        closeConnection();
        return flight;
    }

    protected Flight GetFlightWithoutConnection(int vacationID){
        return GetFlightRecursively(vacationID, 0);
    }

    public Flight GetFlightRecursively(int vacationID, int flightID){
        //Read flight - sql commend
        String sql = "SELECT vacationID, flightID, flightCompany, startCountry, destCountry, startDate, endDate, continueFlightID, returnFlightID FROM flights WHERE vacationID = ? AND flightID = ?";
        Flight flight = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the value
            pstmt.setInt(1, vacationID);
            pstmt.setInt(2, flightID);
            //
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                int continueFlightID = rs.getInt("continueFlightID");
                Flight continueFlight = null;
                if(continueFlightID != -1)
                    continueFlight = GetFlightRecursively(vacationID, continueFlightID);

                int returnFlightID = rs.getInt("returnFlightID");
                Flight returnFlight = null;
                if(returnFlightID != -1)
                    returnFlight = GetFlightRecursively(vacationID, returnFlightID);

                flight = new Flight(rs.getString("flightCompany"),rs.getString("startCountry"),rs.getString("destCountry"),LocalDate.parse(rs.getString("startDate")),LocalDate.parse(rs.getString("endDate")));
                flight.setContinueFlight(continueFlight);
                flight.setReturnFlight(returnFlight);
                break;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }

}
