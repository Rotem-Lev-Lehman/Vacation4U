package Model;

import java.util.Date;

public class Flight {
    private String flightCompany;
    private String startCountry;
    private String destCountry;
    private Date startDate;
    private Date endDate;
    private Flight continueFlight;
    private Flight returnFlight;

    public Flight(String flightCompany, String startCountry, String destCountry, Date startDate, Date endDate) {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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
}
