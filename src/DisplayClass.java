import java.util.List;

public interface DisplayClass {

    void displayHeaderForUsers(FlightManager flight, List<Customer> c);

    void displayRegisteredUsersForAllFlight();

    void displayRegisteredUsersForASpecificFlight(String flightNum);

    void displayHeaderForUsers(Flight flight, List<Customer> c);

    void displayFlightsRegisteredByOneUser(String userID);

}
