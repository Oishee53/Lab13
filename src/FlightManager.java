import java.util.ArrayList;
import java.util.List;

public class FlightManager {
    private List<Customer> listOfRegisteredCustomersInAFlight;
    private int customerIndex;
    private static int nextFlightDay = 0;
    private static final List<Flight> flightList = new ArrayList<>();


    public FlightManager(){}
    public FlightManager(String flightID) {
        this.flight = findFlightByNumber(flightID);
        this.listOfRegisteredCustomersInAFlight = new ArrayList<>();
    }
    void addNewCustomerToFlight(Customer customer) {
        this.listOfRegisteredCustomersInAFlight.add(customer);
    }




}
