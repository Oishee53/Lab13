import java.util.*;

public class Customer {

    // ************************************************************ Fields
    // ************************************************************
    CustomerManager customerManager;
    private final String userID;
    private String email;
    private String name;
    private String phone;
    private final String password;
    private String address;
    private int age;


    // ************************************************************
    // Behaviours/Methods
    // ************************************************************

    Customer() {
        this.userID = null;
        this.name = null;
        this.email = null;
        this.password = null;
        this.phone = null;
        this.address = null;
        this.age = 0;
    }

    /**
     * Registers new customer to the program. Obj of RandomGenerator(Composition) is
     * being used to assign unique userID to the newly created customer.
     *
     * @param name     name of the customer
     * @param email    customer's email
     * @param password customer's account password
     * @param phone    customer's phone-number
     * @param address  customer's address
     * @param age      customer's age
     */
    Customer(String name, String email, String password, String phone, String address, int age) {
        RandomGenerator random = new RandomGenerator();
        random.randomIDGen();
        this.name = name;
        this.userID = random.getRandomNumber();
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.age = age;

    }

    /**
     * Takes input for the new customer and adds them to programs memory.
     * isUniqueData() validates the entered email
     * and returns true if the entered email is already registered. If email is
     * already registered, program will ask the user
     * to enter new email address to get himself register.
     */





    /**
     * Returns String consisting of customers data(name, age, email etc...) for
     * displaying.
     * randomIDDisplay() adds space between the userID for easy readability.
     *
     * @param i for serial numbers.
     * @return customers data in String
     */
    public String toString(int i) {
        return String.format("%10s| %-10d | %-10s | %-32s | %-7s | %-27s | %-35s | %-23s |", "", i,
                randomIDDisplay(userID), name, age, email, address, phone);
    }

    /**
     * Searches for customer with the given ID and displays the customers' data if
     * found.
     *
     * @param ID of the searching/required customer
     */


    /**
     * Returns true if the given emailID is already registered, false otherwise
     *
     * @param emailID to be checked in the list
     */





    /**
     * Shows the customers' data in formatted way.
     *
     * @param showHeader to check if we want to print ascii art for the customers'
     *                   data.
     */

    /**
     * Shows the header for printing customers data
     */


    /**
     * Adds space between userID to increase its readability
     * <p>
     * Example:
     * </p>
     * <b>"920 191" is much more readable than "920191"</b>
     *
     * @param randomID id to add space
     * @return randomID with added space
     */
    String randomIDDisplay(String randomID) {
        StringBuilder newString = new StringBuilder();
        for (int i = 0; i <= randomID.length(); i++) {
            if (i == 3) {
                newString.append(" ").append(randomID.charAt(i));
            } else if (i < randomID.length()) {
                newString.append(randomID.charAt(i));
            }
        }
        return newString.toString();
    }

    /**
     * Associates a new flight with the specified customer
     *
     * @param f flight to associate
     */
    void addNewFlightToCustomerList(Flight f) {
        this.customerManager.flightsRegisteredByUser.add(f);
        // numOfFlights++;
    }

    /**
     * Adds numOfTickets to already booked flights
     *
     * @param index        at which flight is registered in the arraylist
     * @param numOfTickets how many tickets to add
     */
    void addExistingFlightToCustomerList(int index, int numOfTickets) {
        int newNumOfTickets = customerManager.numOfTicketsBookedByUser.get(index) + numOfTickets;
        this.customerManager.numOfTicketsBookedByUser.set(index, newNumOfTickets);
    }




    // ************************************************************ Setters &
    // Getters ************************************************************

    public List<Flight> getFlightsRegisteredByUser() {
        return customerManager.flightsRegisteredByUser;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getNumOfTicketsBookedByUser() {
        return customerManager.numOfTicketsBookedByUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }
}