import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FlightManager {
    Flight flight;
    private List<Customer> listOfRegisteredCustomersInAFlight;
    int customerIndex;

    private static final List<Flight> flightList = new ArrayList<>();


    public FlightManager(){}
    public FlightManager(String flightID) {
        this.flight = findFlightByNumber(flightID);
        this.listOfRegisteredCustomersInAFlight = new ArrayList<>();
    }
    private Flight findFlightByNumber(String flightNo) {
        return getFlightList().stream()
                .filter(f -> flightNo.equalsIgnoreCase(f.getFlightNumber()))
                .findFirst()
                .orElse(null);
    }
    void addNewCustomerToFlight(Customer customer) {
        this.listOfRegisteredCustomersInAFlight.add(customer);
    }

    boolean isCustomerAlreadyAdded(List<Customer> customersList, Customer customer) {
        boolean isAdded = false;
        for (Customer customer1 : customersList) {
            if (customer1.getUserID().equals(customer.getUserID())) {
                isAdded = true;
                customerIndex = customersList.indexOf(customer1);
                break;
            }
        }
        return isAdded;
    }


    void deleteFlight(String flightNumber) {
        boolean isFound = false;
        Iterator<Flight> list = flightList.iterator();
        while (list.hasNext()) {
            Flight flight = list.next();
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                isFound = true;
                break;
            }
        }
        if (isFound) {
            list.remove();
        } else {
            System.out.println("Flight with given Number not found...");
        }
        displayFlightSchedule();
    }

    public void displayFlightSchedule() {

        Iterator<Flight> flightIterator = flightList.iterator();
        System.out.println();
        System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+------------------------+\n");
        System.out.printf("| Num  | FLIGHT SCHEDULE\t\t\t   | FLIGHT NO | Available Seats  | \tFROM ====>>       | \t====>> TO\t   | \t    ARRIVAL TIME       | FLIGHT TIME |  GATE  |   DISTANCE(MILES/KMS)  |%n");
        System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+------------------------+\n");
        int i = 0;
        while (flightIterator.hasNext()) {
            i++;
            Flight f1 = flightIterator.next();
            System.out.println(f1.toString(i));
            System.out.print("+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+------------------------+\n");
        }
    }
    public void flightScheduler() {
        int numOfFlights = 15;              // decides how many unique flights to be included/display in scheduler
        RandomGenerator r1 = new RandomGenerator();
        for (int i = 0; i < numOfFlights; i++) {
            String[][] chosenDestinations = r1.randomDestinations();
            String[] distanceBetweenTheCities = flight.calculateDistance(Double.parseDouble(chosenDestinations[0][1]), Double.parseDouble(chosenDestinations[0][2]), Double.parseDouble(chosenDestinations[1][1]), Double.parseDouble(chosenDestinations[1][2]));
            String flightSchedule = flight.createNewFlightsAndTime();
            String flightNumber = r1.randomFlightNumbGen(2, 1).toUpperCase();
            int numOfSeatsInTheFlight = r1.randomNumOfSeats();
            String gate = r1.randomFlightNumbGen(1, 30);
            getFlightList().add(new Flight(flightSchedule, flightNumber, numOfSeatsInTheFlight, chosenDestinations, distanceBetweenTheCities, gate.toUpperCase()));
        }
    }


    public List<Flight> getFlightList() {
        return flightList;
    }

    public List<Customer> getListOfRegisteredCustomersInAFlight() {
        return listOfRegisteredCustomersInAFlight;
    }


}
