/*
 * This class is intended to be the main class for this Project. All necessary methods are getting calls from this class.
 *
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {

    // ************************************************************ Fields
    // ************************************************************

    /*
     * 2D Array to store admin credentials. Default credentials are stored on [0][0]
     * index. Max num of admins can be 10....
     */
    static String[][] adminUserNameAndPassword = new String[10][2];
    private static List<Customer> customersCollection = new ArrayList<>();

    // ************************************************************
    // Behaviours/Methods
    // ************************************************************

    public static void main(String[] args) {
        int countNumOfUsers = 1;
        RolesAndPermissions r1 = new RolesAndPermissions();
        FlightManager f1 = new FlightManager();
        FlightReservation bookingAndReserving = new FlightReservation();
        Customer c1 = new Customer();
        f1.flightScheduler();
        CustomerManager customerManager = new CustomerManager(c1);
        Scanner read = new Scanner(System.in);


        System.out.println(
                "\n\t\t\t\t\t+++++++++++++ Welcome to BAV AirLines +++++++++++++\n\nTo Further Proceed, Please enter a value.");
        System.out.println(
                "\n***** Default Username && Password is root-root ***** Using Default Credentials will restrict you to just view the list of Passengers....\n");
        displayMainMenu();
        int desiredOption = read.nextInt();
        while (desiredOption < 0 || desiredOption > 8) {
            System.out.print("ERROR!! Please enter value between 0 - 4. Enter the value again :\t");
            desiredOption = read.nextInt();
        }

        do {
            Scanner read1 = new Scanner(System.in);
            /*
             * If desiredOption is 1 then call the login method.... if default credentials
             * are used then set the permission
             * level to standard/default where the user can just view the customer's
             * data...if not found, then return -1, and if
             * data is found then show the user display menu for adding, updating, deleting
             * and searching users/customers...
             */
            if (desiredOption == 1) {

                /* Default username and password.... */
                adminUserNameAndPassword[0][0] = "root";
                adminUserNameAndPassword[0][1] = "root";

                System.out.print("\nEnter the UserName to login to the Management System :     ");
                String username = read1.nextLine();
                System.out.print("Enter the Password to login to the Management System :    ");
                String password = read1.nextLine();
                System.out.println();

                /* Checking the RolesAndPermissions...... */
                if (r1.isPrivilegedUserOrNot(username, password) == -1) {
                    System.out.printf(
                            "\n%20sERROR!!! Unable to login Cannot find user with the entered credentials.... Try Creating New Credentials or get yourself register by pressing 4....\n",
                            "");
                } else if (r1.isPrivilegedUserOrNot(username, password) == 0) {
                    System.out.println(
                            "You've standard/default privileges to access the data... You can just view customers data..."
                                    + "Can't perform any actions on them....");
                    customerManager.displayCustomersData(true);
                } else {
                    System.out.printf(
                            "%-20sLogged in Successfully as \"%s\"..... For further Proceedings, enter a value from below....",
                            "", username);

                    /*
                     * Going to Display the CRUD operations to be performed by the privileged
                     * user.....Which includes Creating, Updating
                     * Reading(Searching) and deleting a customer....
                     */
                    do {
                        System.out.printf("\n\n%-60s+++++++++ 2nd Layer Menu +++++++++%50sLogged in as \"%s\"\n", "",
                                "", username);
                        System.out.printf("%-30s (a) Enter 1 to add new Passenger....\n", "");
                        System.out.printf("%-30s (b) Enter 2 to search a Passenger....\n", "");
                        System.out.printf("%-30s (c) Enter 3 to update the Data of the Passenger....\n", "");
                        System.out.printf("%-30s (d) Enter 4 to delete a Passenger....\n", "");
                        System.out.printf("%-30s (e) Enter 5 to Display all Passengers....\n", "");
                        System.out.printf("%-30s (f) Enter 6 to Display all flights registered by a Passenger...\n",
                                "");
                        System.out.printf("%-30s (g) Enter 7 to Display all registered Passengers in a Flight....\n",
                                "");
                        System.out.printf("%-30s (h) Enter 8 to Delete a Flight....\n", "");
                        System.out.printf("%-30s (i) Enter 0 to Go back to the Main Menu/Logout....\n", "");
                        System.out.print("Enter the desired Choice :   ");
                        desiredOption = read.nextInt();
                        /* If 1 is entered by the privileged user, then add a new customer...... */
                        if (desiredOption == 1) {
                            Customer customer = readCustomerData();

                            customerManager.addNewCustomer(customer);
                        } else if (desiredOption == 2) {
                            /*
                             * If 2 is entered by the privileged user, then call the search method of the
                             * Customer class
                             */

                            customerManager.displayCustomersData(false);
                            System.out.print("Enter the CustomerID to Search :\t");
                            String customerID = read1.nextLine();
                            System.out.println();
                            customerManager.searchUser(customerID);
                        } else if (desiredOption == 3) {
                            /*
                             * If 3 is entered by the user, then call the update method of the Customer
                             * Class with required
                             * arguments.....
                             */

                            customerManager.displayCustomersData(false);
                            System.out.print("Enter the CustomerID to Update its Data :\t");
                            String customerID = read1.nextLine();
                            if (customersCollection.size() > 0) {
                                List<String> details = readCustomerInfo();
                                customerManager.editUserInfo(customerID);
                            } else {
                                System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", customerID);
                            }

                        } else if (desiredOption == 4) {
                            /*
                             * If 4 is entered, then ask the user to enter the customer id, and then delete
                             * that customer....
                             */
                            customerManager.displayCustomersData(false);
                            System.out.print("Enter the CustomerID to Delete its Data :\t");
                            String customerID = read1.nextLine();
                            if (customersCollection.size() > 0) {
                                customerManager.deleteUser(customerID);
                            } else {
                                System.out.printf("%-50sNo Customer with the ID %s Found...!!!\n", " ", customerID);
                            }
                        } else if (desiredOption == 5) {
                            /* Call the Display Method of Customer Class.... */
                            customerManager.displayCustomersData(false);
                        } else if (desiredOption == 6) {
                            customerManager.displayCustomersData(false);
                            System.out.print(
                                    "\n\nEnter the ID of the user to display all flights registered by that user...");
                            String id = read1.nextLine();
                            bookingAndReserving.displayFlightsRegisteredByOneUser(id);
                        } else if (desiredOption == 7) {
                            System.out.print(
                                    "Do you want to display Passengers of all flights or a specific flight.... 'Y/y' for displaying all flights and 'N/n' to look for a"
                                            +
                                            " specific flight.... ");
                            char choice = read1.nextLine().charAt(0);
                            if ('y' == choice || 'Y' == choice) {
                                bookingAndReserving.displayRegisteredUsersForAllFlight();
                            } else if ('n' == choice || 'N' == choice) {
                                f1.displayFlightSchedule();
                                System.out.print(
                                        "Enter the Flight Number to display the list of passengers registered in that flight... ");
                                String flightNum = read1.nextLine();
                                bookingAndReserving.displayRegisteredUsersForASpecificFlight(flightNum);
                            } else {
                                System.out.println("Invalid Choice...No Response...!");
                            }
                        } else if (desiredOption == 8) {
                            f1.displayFlightSchedule();
                            System.out.print("Enter the Flight Number to delete the flight : ");
                            String flightNum = read1.nextLine();
                            f1.deleteFlight(flightNum);

                        } else if (desiredOption == 0) {
                            ;
                            System.out.println("Thanks for Using BAV Airlines Ticketing System...!!!");

                        } else {
                            System.out.println(
                                    "Invalid Choice...Looks like you're Robot...Entering values randomly...You've Have to login again...");

                            desiredOption = 0;
                        }

                    } while (desiredOption != 0);

                }
            } else if (desiredOption == 2) {
                /*
                 * If desiredOption is 2, then call the registration method to register a
                 * user......
                 */
                System.out.print("\nEnter the UserName to Register :    ");
                String username = read1.nextLine();
                System.out.print("Enter the Password to Register :     ");
                String password = read1.nextLine();
                while (r1.isPrivilegedUserOrNot(username, password) != -1) {
                    System.out.print("ERROR!!! Admin with same UserName already exist. Enter new UserName:   ");
                    username = read1.nextLine();
                    System.out.print("Enter the Password Again:   ");
                    password = read1.nextLine();
                }

                /* Setting the credentials entered by the user..... */
                adminUserNameAndPassword[countNumOfUsers][0] = username;
                adminUserNameAndPassword[countNumOfUsers][1] = password;

                /* Incrementing the numOfUsers */
                countNumOfUsers++;
            } else if (desiredOption == 3) {
                System.out.print("\n\nEnter the Email to Login : \t");
                String userName = read1.nextLine();
                System.out.print("Enter the Password : \t");
                String password = read1.nextLine();
                String[] result = r1.isPassengerRegistered(userName, password).split("-");

                if (Integer.parseInt(result[0]) == 1) {
                    int desiredChoice;
                    System.out.printf(
                            "\n\n%-20sLogged in Successfully as \"%s\"..... For further Proceedings, enter a value from below....",
                            "", userName);
                    do {
                        System.out.printf("\n\n%-60s+++++++++ 3rd Layer Menu +++++++++%50sLogged in as \"%s\"\n", "",
                                "", userName);
                        System.out.printf("%-40s (a) Enter 1 to Book a flight....\n", "");
                        System.out.printf("%-40s (b) Enter 2 to update your Data....\n", "");
                        System.out.printf("%-40s (c) Enter 3 to delete your account....\n", "");
                        System.out.printf("%-40s (d) Enter 4 to Display Flight Schedule....\n", "");
                        System.out.printf("%-40s (e) Enter 5 to Cancel a Flight....\n", "");
                        System.out.printf("%-40s (f) Enter 6 to Display all flights registered by \"%s\"....\n", "",
                                userName);
                        System.out.printf("%-40s (g) Enter 0 to Go back to the Main Menu/Logout....\n", "");
                        System.out.print("Enter the desired Choice :   ");
                        desiredChoice = read.nextInt();
                        if (desiredChoice == 1) {
                            // bookingAndReserving.displayArtWork(1);
                            f1.displayFlightSchedule();
                            System.out.print("\nEnter the desired flight number to book :\t ");
                            String flightToBeBooked = read1.nextLine();
                            System.out.print("Enter the Number of tickets for " + flightToBeBooked + " flight :   ");
                            int numOfTickets = read.nextInt();
                            while (numOfTickets > 10) {
                                System.out.print(
                                        "ERROR!! You can't book more than 10 tickets at a time for single flight....Enter number of tickets again : ");
                                numOfTickets = read.nextInt();
                            }
                            bookingAndReserving.bookFlight(flightToBeBooked, numOfTickets, result[1]);
                        } else if (desiredChoice == 2) {
                            List<String> details = readCustomerInfo();
                            customerManager.editUserInfo(result[1]);
                        } else if (desiredChoice == 3) {
                            System.out.print(
                                    "Are you sure to delete your account...It's an irreversible action...Enter Y/y to confirm...");
                            char confirmationChar = read1.nextLine().charAt(0);
                            if (confirmationChar == 'Y' || confirmationChar == 'y') {
                                customerManager.deleteUser(result[1]);
                                System.out.printf("User %s's account deleted Successfully...!!!", userName);
                                desiredChoice = 0;
                            } else {
                                System.out.println("Action has been cancelled...");
                            }
                        } else if (desiredChoice == 4) {

                            f1.displayFlightSchedule();
                            displayMeasurementInstructions();
                        } else if (desiredChoice == 5) {

                            bookingAndReserving.cancelFlight(result[1]);
                        } else if (desiredChoice == 6) {

                            bookingAndReserving.displayFlightsRegisteredByOneUser(result[1]);
                        } else {

                            if (desiredChoice != 0) {
                                System.out.println(
                                        "Invalid Choice...Looks like you're Robot...Entering values randomly...You've Have to login again...");
                            }
                            desiredChoice = 0;
                        }
                    } while (desiredChoice != 0);

                } else {
                    System.out.printf(
                            "\n%20sERROR!!! Unable to login Cannot find user with the entered credentials.... Try Creating New Credentials or get yourself register by pressing 4....\n",
                            "");
                }
            } else if (desiredOption == 4) {
                Customer customer = readCustomerData();
                customerManager.addNewCustomer(customer);
            } else if (desiredOption == 5) {
                manualInstructions();
            }

            displayMainMenu();
            desiredOption = read1.nextInt();
            while (desiredOption < 0 || desiredOption > 8) {
                System.out.print("ERROR!! Please enter value between 0 - 4. Enter the value again :\t");
                desiredOption = read1.nextInt();
            }
        } while (desiredOption != 0);

    }

    static void displayMainMenu() {
        System.out.println("\n\n\t\t(a) Press 0 to Exit.");
        System.out.println("\t\t(b) Press 1 to Login as admin.");
        System.out.println("\t\t(c) Press 2 to Register as admin.");
        System.out.println("\t\t(d) Press 3 to Login as Passenger.");
        System.out.println("\t\t(e) Press 4 to Register as Passenger.");
        System.out.println("\t\t(f) Press 5 to Display the User Manual.");
        System.out.print("\t\tEnter the desired option:    ");
    }

    static void manualInstructions() {
        Scanner read = new Scanner(System.in);
        System.out.printf("%n%n%50s %s Welcome to BAV Airlines User Manual %s", "", "+++++++++++++++++",
                "+++++++++++++++++");
        System.out.println("\n\n\t\t(a) Press 1 to display Admin Manual.");
        System.out.println("\t\t(b) Press 2 to display User Manual.");
        System.out.print("\nEnter the desired option :    ");
        int choice = read.nextInt();
        while (choice < 1 || choice > 2) {
            System.out.print("ERROR!!! Invalid entry...Please enter a value either 1 or 2....Enter again....");
            choice = read.nextInt();
        }
        if (choice == 1) {
            System.out.println(
                    "\n\n(1) Admin have the access to all users data...Admin can delete, update, add and can perform search for any customer...\n");
            System.out.println(
                    "(2) In order to access the admin module, you've to get yourself register by pressing 2, when the main menu gets displayed...\n");
            System.out.println(
                    "(3) Provide the required details i.e., name, email, id...Once you've registered yourself, press 1 to login as an admin... \n");
            System.out.println(
                    "(4) Once you've logged in, 2nd layer menu will be displayed on the screen...From here on, you can select from variety of options...\n");
            System.out.println(
                    "(5) Pressing \"1\" will add a new Passenger, provide the program with required details to add the passenger...\n");
            System.out.println(
                    "(6) Pressing \"2\" will search for any passenger, given the admin(you) provides the ID from the table printing above....  \n");
            System.out.println(
                    "(7) Pressing \"3\" will let you update any passengers data given the user ID provided to program...\n");
            System.out.println("(8) Pressing \"4\" will let you delete any passenger given its ID provided...\n");
            System.out.println("(9) Pressing \"5\" will let you display all registered passenger...\n");
            System.out.println(
                    "(10) Pressing \"6\" will let you display all registered passengers...After selecting, program will ask, if you want to display passengers for all flights(Y/y) or a specific flight(N/n)\n");
            System.out.println(
                    "(11) Pressing \"7\" will let you delete any flight given its flight number provided...\n");
            System.out.println(
                    "(11) Pressing \"0\" will make you logged out of the program...You can login again any time you want during the program execution....\n");
        } else {
            System.out.println(
                    "\n\n(1) Local user has the access to its data only...He/She won't be able to change/update other users data...\n");
            System.out.println(
                    "(2) In order to access local users benefits, you've to get yourself register by pressing 4, when the main menu gets displayed...\n");
            System.out.println(
                    "(3) Provide the details asked by the program to add you to the users list...Once you've registered yourself, press \"3\" to login as a passenger...\n");
            System.out.println(
                    "(4) Once you've logged in, 3rd layer menu will be displayed...From here on, you embarked on the journey to fly with us...\n");
            System.out.println(
                    "(5) Pressing \"1\" will display available/scheduled list of flights...To get yourself booked for a flight, enter the flight number and number of tickets for the flight...Max num of tickets at a time is 10 ...\n");
            System.out.println(
                    "(7) Pressing \"2\" will let you update your own data...You won't be able to update other's data... \n");
            System.out.println("(8) Pressing \"3\" will delete your account... \n");
            System.out
                    .println("(9) Pressing \"4\" will display randomly designed flight schedule for this runtime...\n");
            System.out.println("(10) Pressing \"5\" will let you cancel any flight registered by you...\n");
            System.out.println("(11) Pressing \"6\" will display all flights registered by you...\n");
            System.out.println(
                    "(12) Pressing \"0\" will make you logout of the program...You can login back at anytime with your credentials...for this particular run-time... \n");
        }
    }

    // ************************************************************ Setters &
    // Getters ************************************************************

    public static List<Customer> getCustomersCollection() {
        return customersCollection;
    }
    public static Customer readCustomerData()
    {
        System.out.printf("\n\n\n%60s ++++++++++++++ Welcome to the Customer Registration Portal ++++++++++++++", "");
        Scanner read = new Scanner(System.in);
        System.out.print("\nEnter your name :\t");
        String name = read.nextLine();
        System.out.print("Enter your email address :\t");
        String email = read.nextLine();
        while (isUniqueData(email)) {
            System.out.println(
                    "ERROR!!! User with the same email already exists... Use new email or login using the previous credentials....");
            System.out.print("Enter your email address :\t");
            email = read.nextLine();
        }
        System.out.print("Enter your Password :\t");
        String password = read.nextLine();
        System.out.print("Enter your Phone number :\t");
        String phone = read.nextLine();
        System.out.print("Enter your address :\t");
        String address = read.nextLine();
        System.out.print("Enter your age :\t");
        int age = read.nextInt();
        return new Customer(name, email, password, phone, address, age);
    }
    public static boolean isUniqueData(String emailID) {
        boolean isUnique = false;
        for (Customer c : customersCollection) {
            if (emailID.equals(c.getEmail())) {
                isUnique = true;
                break;
            }
        }
        return isUnique;
    }
    public static List<String> readCustomerInfo() {
        Scanner read = new Scanner(System.in);
        List<String> details = new ArrayList<>();

        System.out.print("\nEnter the new name of the Passenger:\t");
        details.add(read.nextLine());

        System.out.print("Enter the new email address:\t");
        details.add(read.nextLine());

        System.out.print("Enter the new Phone number:\t");
        details.add(read.nextLine());

        System.out.print("Enter the new address:\t");
        details.add(read.nextLine());

        System.out.print("Enter the new age:\t");
        details.add(String.valueOf(read.nextInt()));

        read.nextLine(); // Consume leftover newline
        return details;
    }

    public static void displayMeasurementInstructions(){
        String symbols = "+---------------------------+";
        System.out.printf("\n\n %100s\n %100s", symbols, "| SOME IMPORTANT GUIDELINES |");
        System.out.printf("\n %100s\n", symbols);
        System.out.println("\n\t\t1. Distance between the destinations are based upon the Airports Coordinates(Latitudes && Longitudes) based in those cities\n");
        System.out.println("\t\t2. Actual Distance of the flight may vary from this approximation as Airlines may define their on Travel Policy that may restrict the planes to fly through specific regions...\n");
        System.out.println("\t\t3. Flight Time depends upon several factors such as Ground Speed(GS), AirCraft Design, Flight Altitude and Weather. Ground Speed for these calculations is 450 Knots...\n");
        System.out.println("\t\t4. Expect reaching your destination early or late from the Arrival Time. So, please keep a margin of ±1 hour...\n");
        System.out.println("\t\t5. The departure time is the moment that your plane pushes back from the gate, not the time it takes off. The arrival time is the moment that your plane pulls into the gate, not the time\n\t\t   it touches down on the runway...\n");
    }
}