package Model;

import java.time.LocalDate;
import java.util.Date;

public class Flight {
    private String flightCompany;
    private String startCountry;
    private String destCountry;
    private LocalDate startDate;
    private LocalDate endDate;
    private Flight continueFlight;
    private Flight returnFlight;

    public Flight(String flightCompany, String startCountry, String destCountry, LocalDate startDate, LocalDate endDate) {
        this.flightCompany = flightCompany;
        this.startCountry = startCountry;
        this.destCountry = destCountry;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public String getFlightCompany() {
        return flightCompany;
    }

    public void setFlightCompany(String flightCompany) {
        this.flightCompany = flightCompany;
    }

    public String getStartCountry() {
        return startCountry;
    }

    public void setStartCountry(String startCountry) {
        this.startCountry = startCountry;
    }

    public String getDestCountry() {
        return destCountry;
    }

    public void setDestCountry(String destCountry) {
        this.destCountry = destCountry;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Flight getContinueFlight() {
        return continueFlight;
    }

    public void setContinueFlight(Flight continueFlight) {
        this.continueFlight = continueFlight;
    }

    public Flight getReturnFlight() {
        return returnFlight;
    }

    public void setReturnFlight(Flight returnFlight) {
        this.returnFlight = returnFlight;
    }

    public String getStartDateAsString(){
        return startDate.toString();
    }

    public String getEndDateAsString(){
        return endDate.toString();
    }
}
