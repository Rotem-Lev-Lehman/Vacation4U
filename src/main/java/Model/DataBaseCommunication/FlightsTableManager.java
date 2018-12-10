package Model.DataBaseCommunication;

import Model.Flight;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    /*
    public Flight GetFlight(int vacationID){

    }
    */
}
