import java.io.*;
import java.util.*;
import java.util.InputMismatchException;
public class Theatre {

    // Initializing 3 arrays
    static char[] row_1 = new char[]{'O', 'O', 'O', 'O',  'O', 'O', 'O', 'O',  'O', 'O', 'O', 'O'};
    static char[] row_2 = new char[]{'O', 'O', 'O', 'O',  'O', 'O', 'O', 'O',  'O', 'O', 'O', 'O',  'O', 'O', 'O', 'O'};
    static char[] row_3 = new char[]{'O', 'O', 'O', 'O',  'O', 'O', 'O', 'O',  'O', 'O', 'O', 'O' , 'O', 'O', 'O', 'O',  'O', 'O', 'O', 'O'};
    static final ArrayList<Ticket> tickets = new ArrayList<>();  // Initializing an ArrayList.
    static Scanner input = new Scanner(System.in); // Creating a Scanner object

    public static void main(String[] args) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("\t\t\t\tWelcome to the New Theatre! \n" + "=".repeat(60));

        int option;
        while (true) {
            // Adding a delay to the menu options.
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // PRINTING MENU OPTIONS.
            System.out.println("\n---------------------------|Menu|---------------------------");
            System.out.println("Please select an option:");
            System.out.println("\t 1) Buy a ticket ");
            System.out.println("\t 2) Print seating area");
            System.out.println("\t 3) Cancel ticket ");
            System.out.println("\t 4) List available seats");
            System.out.println("\t 5) Save to file ");
            System.out.println("\t 6) Load from file");
            System.out.println("\t 7) Print ticket information and total price ");
            System.out.println("\t 8) Sort tickets by price ");
            System.out.println("\t     0) Quit \n" + "-".repeat(60));

            System.out.print("Enter option: ");

            try {
                Scanner input = new Scanner(System.in);
                option = input.nextInt(); // Getting the menu option from the user.
                System.out.println();

                if (option == 0) {  // If user enters 0, it will quit the program.
                    System.out.println("You've successfully quit..");
                    break;
                }

                switch (option) {  // Cases for user's menu option
                    case 1 -> buy_ticket();
                    case 2 -> print_seating_area();
                    case 3 -> cancel_ticket();
                    case 4 -> show_available();
                    case 5 -> save();
                    case 6 -> load();
                    case 7 -> show_tickets_info();
                    case 8 -> sort_tickets();
                    default -> System.out.println("Invalid option! Try again");
                }
                // Showing errors for user input mistakes.
            } catch (InputMismatchException e) {
                System.out.println("Error! Enter an integer");
            }catch (Exception e) {
                System.out.println("An error occurred.");
            }
        }
    }

    /**
     * Asks for the user's information. (Name,Surname,Email)
     * Makes a new person object from the information acquired.
     * Gets the row and checks for user input validation.
     * Calling the seat_change method with arguments > "add" and row number.
     * Creates a ticket object with the row, seatNum, price, person attributes.
     * Adds the ticket to the Array List.
     */
    private static void buy_ticket() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("---------|Buy Ticket|---------\n");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        Person person = new Person(name, surname, email); // Making a new person object with user's information.

        boolean inputSuccess = false; // Initializing a switch to loop.
        do {
            try {
                double price; // Declaring the price variable
                // Getting the row number
                System.out.print("Enter row number (1 to 3): "); // Getting the row number
                int row = input.nextInt();
                if (row > 0 && row < 4) {
                    // Calling the seat_change method and assigning the return value to seatNum variable.
                    int seatNum = seat_change("add", row);
                    // Calling the price_selector method and assigning the return value to price variable
                    price = price_selector(row);

                    Ticket ticket = new Ticket(row, seatNum, price, person); // Creates a new ticket object.
                    tickets.add(ticket); // Adding the new ticket object.
                    inputSuccess = true;
                }else {
                    System.out.println("Row out of bounds. Please select 1-3");
                }
                // Showing errors for user's invalid inputs.
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter an integer.");
                input.nextLine();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error! Seat out of bounds. Try again.");
            }
        } while (!inputSuccess);
    }

    /**
     * Prints the 3 arrays.
     * Show seats that are bought with the character 'X'.
     * Show seats that are available with the character 'O'.
     */
    private static void print_seating_area() {
        System.out.println("--------|Seating area|--------\n");
        // Printing the stage
        System.out.println("\t\t *************");
        System.out.println("\t\t *   STAGE   *");
        System.out.println("\t\t *************");

        String row1 = new String(row_1);

        String row1_firstHalf = row1.substring(0, row1.length() / 2);  // Dividing the array into 2 parts.
        String row1_secondHalf = row1.substring(row1.length() / 2);
        System.out.print("\t\t ");
        System.out.println(row1_firstHalf + " " + row1_secondHalf);

        String row2 = new String(row_2);

        String row2_firstHalf = row2.substring(0, row2.length() / 2);
        String row2_secondHalf = row2.substring(row2.length() / 2);
        System.out.print("\t   ");
        System.out.println(row2_firstHalf + " " + row2_secondHalf);

        String row3 = new String(row_3);

        String row3_firstHalf = row3.substring(0, row3.length() / 2);
        String row3_secondHalf = row3.substring(row3.length() / 2);
        System.out.print("\t ");
        System.out.println(row3_firstHalf + " " + row3_secondHalf);
    }

    /**
     * Gets the row checks for user input validation.
     * Calling the seat_change method with arguments > "remove" and row number.
     * Calling the ticket_remover method to remove the respective ticket from the Array List.
     */
    private static void cancel_ticket() {
        System.out.println("---------|Cancel Ticket|---------\n");
        boolean inputSuccess = false; // Initializing a switch to loop.
        do {
            try {
                // Getting the row number
                System.out.print("Enter row number (1 to 3): ");
                int row = input.nextInt();
                if (row > 0 && row < 4) {
                    // Calling the seat_change method and assigning the return value to seatNum variable.
                    int seatNum = seat_change("remove", row);
                    ticket_remover(row,seatNum); // Calling the ticket_remover method.
                    inputSuccess = true;
                }else {
                    System.out.println("Row out of bounds. Please select 1-3"); // Displaying error for any other int.
                }
                // Showing errors for user's invalid inputs.
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Enter an integer.");
                input.nextLine();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error! Seat out of bounds.");
            } catch (Exception e) {
                System.out.println("An error occurred!");
            }
        } while (!inputSuccess);

    }

    /**
     * Removing tickets from the Array List.
     * @param row The row number user entered.
     * @param seatNum The seat number user entered.
     */
    private  static void ticket_remover(int row,int seatNum){
        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).row == row) {
                if (tickets.get(i).seat == seatNum) {
                    tickets.remove(i);
                }
            }
        }
    }

    // Prints the 3 rows with the available seats.
    private static void show_available() {
        System.out.print("Seats available in row 1: ");
        for (int i = 0; i < row_1.length; i++) {
            if (row_1[i] == 'O') {  // Checking if the seat is available.
                System.out.print((i + 1) + ", ");
            }
        }
        System.out.println();

        System.out.print("Seats available in row 2: ");
        for (int i = 0; i < row_2.length; i++) {
            if (row_2[i] == 'O') {  // Checking if the seat is available.
                System.out.print((i + 1) + ", ");
            }
        }
        System.out.println();

        System.out.print("Seats available in row 3: ");
        for (int i = 0; i < row_3.length; i++) {
            if (row_3[i] == 'O') {  // Checking if the seat is available.
                System.out.print((i + 1) + ", ");
            }
        }
        System.out.println();
    }

    // Saves the 3 arrays with the row’s information in a file.
    private static void save() {
        try {
            FileWriter writer = new FileWriter("ArrayFile.txt"); // Creating a file.

            // Writing the 3 arrays into the file.
            writer.write("1st Row = " + Arrays.toString(row_1));
            writer.write("\n");
            writer.write("2nd Row = " + Arrays.toString(row_2));
            writer.write("\n");
            writer.write("3rd Row = " + Arrays.toString(row_3));
            writer.write("\n");
            writer.close();
            System.out.println("Successfully saved the information into a file...\ngoing back to the main menu");
            // Showing errors.
        } catch (IOException e) {
            System.out.println("Error saving data. Try again! (IOException)");
        }
    }

    // Loads the file saved and prints the 3 arrays with the row's information.
    private static void load() {
        try {
            // Reading the file "ArrayFile.txt".
            File myObj = new File("ArrayFile.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);  // Printing the text of the file into the console.
            }
            myReader.close();
            // Showing errors.
        } catch (FileNotFoundException e) {
            System.out.println("Error loading file! File not found.");
        }
    }

    /**
     * Prints all the information for all tickets.
     * Calculates and shows the total of all tickets after the ticket's info.
     */
    private static void show_tickets_info() {
        System.out.println("---------|Ticket information|---------\n");
        double total = 0;
        for (Ticket ticket : tickets) {
            ticket.print();  // Printing the ticket.
            System.out.println("--------------------------------------");
            total += ticket.price;  // Updating the variable "Total" by adding the tickets' prices.
        }
        System.out.println("Total: £" + total);  // Printing the total price of the tickets.
    }

    // Prints a temp array list of tickets sorted by Ticket price in ascending order.
    private static void sort_tickets() {
        int n = tickets.size();
        for(int i=0; i<n-1;i++){
            for(int j=0;j<n-1; j++){
                if(tickets.get(j).price > tickets.get(j+1).price){
                    Ticket temp = tickets.get(j);
                    tickets.set(j,tickets.get(j+1));
                    tickets.set(j+1,temp);
                }
            }
        }
        show_tickets_info();
    }

    /**
     * Checks for the requirement whether "add" or "remove".
     * If the mode is "add", this will add the ticket according to the user inputs.
     * If the mode is "remove", this will remove the ticket according to the user inputs.
     * Gets the seat number checks for user input validation.
     * @param mode to differentiate between adding or removing tickets.
     * @param row as a parameter.
     * @return seatNum of the user's choice.
     */
    private static int seat_change (String mode, int row) {
        String row1_seatNo = "1-12";
        String row2_seatNo = "1-16";
        String row3_seatNo = "1-20";

        String tempRowSeat="";
        char[] tempRow = new char[]{};
        switch(row) {
            case 1 -> {
                tempRow=row_1;
                tempRowSeat = row1_seatNo;
            }
            case 2 -> {
                tempRow=row_2;
                tempRowSeat = row2_seatNo;
            }
            case 3 -> {
                tempRow=row_3;
                tempRowSeat = row3_seatNo;
            }
            default -> System.out.println("Row out of bounds. Please select 1-3");
        }
        int seatNum;
        if (mode.equals("add")) {
            System.out.println("\nSeats Availability (O = Available , X = Booked) :-");
            System.out.println("Row " + row + ": " + Arrays.toString(tempRow));//Showing  the user the availability of the row.

            System.out.print("\nEnter a seat number between " + tempRowSeat + ": ");
            seatNum = input.nextInt();
            if (tempRow[seatNum - 1] == 'X') {
                System.out.print("Seat already occupied\nGoing back to the menu");
            } else {
                tempRow[seatNum - 1] = 'X'; // Assigning the respective index to the value 'X' to mark as booked.
                System.out.println("Ticket bought successfully");
            }
        }
        else {
                System.out.println("\nSeats Availability (O = Available , X = Booked) :-");
                System.out.println("Row " + row + ": " + Arrays.toString(tempRow));//Showing  the user the availability of the row.

                System.out.print("\nEnter a seat number between " + tempRowSeat + ": ");
                seatNum = input.nextInt();
                if (tempRow[seatNum - 1] == 'X') {
                    tempRow[seatNum - 1] = 'O'; //Assigning the respective index to the value 'O' to mark as available.
                    System.out.println("\nThe booking has being cancelled\nGoing back to the menu");

                } else {
                    tempRow[seatNum - 1] = 'O'; // Display error if the seat is not booked before.
                    System.out.println("This seat hasn't being booked...\nGoing back to the menu");
                }
            }
        return seatNum;
    }

    /**
     * Assigning the price of a ticket for each row.
     * @param row as a parameter.
     * @return price for each row.
     */
    private static double price_selector(int row) {
        double price = 0;
        if (row == 1) {
            price = 30;
        }
        else if (row == 2) {
            price = 20;
        }
        else if (row==3) {
            price = 10;
        }
        return price;
    }
}