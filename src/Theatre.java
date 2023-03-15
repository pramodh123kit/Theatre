import java.io.*;
import java.util.*;
import java.util.InputMismatchException;
public class Theatre {

    // Initializing 3 arrays
    static char[] row_1 = new char[]{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'};
    static char[] row_2 = new char[]{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'};
    static char[] row_3 = new char[]{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'};
    private  static ArrayList<Ticket> tickets = new ArrayList<>();  // Initializing an ArrayList
    static Scanner input = new Scanner(System.in); // Creating a Scanner object
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int option;
        while (true) {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("\t\t\t\tWelcome to the New Theatre! \n" + "=".repeat(60));
            System.out.println("---------------------------|Menu|---------------------------");
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
            option = 0;

            try {
                System.out.print("Enter option: ");
                option = input.nextInt();
                System.out.println();
            } catch (InputMismatchException e) {
                System.out.println("Invalid option, Enter an valid option");
                main(args);
            }
            if (option == 0) {
                System.out.println("You've successfully quit..");
                break;
            }
            switch (option) {
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
        }
    }

    /*
     * Asks for the user's information. (Name,Surname,Email)
     * Makes a new Person object from the information acquired.
     * Gets the row and seat number and checks for user input validation.
     * Assign the element of the respective array and index to 'X' to mark as booked.
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
        Person person = new Person(name, surname, email);

        int row = 0;
        try {
            System.out.print("Enter row number (1 to 3): ");
            row = input.nextInt();

            if (row == 1) {
                System.out.println("\nSeats Availability (O = Available , X = Booked) :-");
                System.out.println("Row 1: " + Arrays.toString(row_1));

                System.out.print("\nEnter a seat number between 1-12: ");
                int seatNum = input.nextInt();
                if (seatNum < 0 || seatNum > 12) {
                    System.out.println("Seat doesn't exist... Enter a valid seat number!");
                }
                if (row_1[seatNum - 1] == 'X') {
                    System.out.print("Seat already occupied");
                } else {
                    row_1[seatNum - 1] = 'X';
                    System.out.println("Ticket bought successfully");
                    double price = 30;
                    Ticket ticket = new Ticket(row ,seatNum ,price ,person);
                    tickets.add(ticket);
                }

            } else if (row == 2) {
                System.out.println("\nSeats Availability (O = Available , X = Booked) :-");
                System.out.println("Row 2: " + Arrays.toString(row_2));

                System.out.print("\nEnter a seat number between 1-16: ");
                int seatNum = input.nextInt();
                if (seatNum < 0 || seatNum > 16) {
                    System.out.println("Seat doesn't exist... Enter a valid seat number!");
                }
                if (row_2[seatNum - 1] == 'X') {
                    System.out.println("Seat already occupied");
                } else {
                    row_2[seatNum - 1] = 'X';
                    System.out.println("Ticket bought successfully");
                    double price = 20;
                    Ticket ticket = new Ticket(row ,seatNum ,price ,person);
                    tickets.add(ticket);
                }

            } else if (row == 3) {
                System.out.println("\nSeats Availability (O = Available , X = Booked) :-");
                System.out.println("Row 3: " + Arrays.toString(row_3));

                System.out.print("\nEnter a seat number between 1-20: ");
                int seatNum = input.nextInt();
                if (seatNum < 0 || seatNum > 20) {
                    System.out.println("Seat doesn't exist... Enter a valid seat number!");
                }
                if (row_3[seatNum - 1] == 'X') {
                    System.out.println("Seat already occupied");
                } else {
                    row_3[seatNum - 1] = 'X';
                    System.out.println("Ticket bought successfully");
                    double price = 10;
                    Ticket ticket = new Ticket(row ,seatNum ,price ,person);
                    tickets.add(ticket);
                }
            } else {
                System.out.println("This row number does not exist. Please select 1-3");
            }
        } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid input!");
        }
    }
    private static void print_seating_area() {
        System.out.println("---------|Seating area|---------\n");
        System.out.println("\t\t*************");
        System.out.println("\t\t*   STAGE   *");
        System.out.println("\t\t*************");

        String row1 = new String(row_1);

        String row1_firstHalf = row1.substring(0, row1.length() / 2);
        String row1_secondHalf = row1.substring(row1.length() / 2);
        System.out.print("\t\t");
        System.out.println(row1_firstHalf + " " + row1_secondHalf);

        String row2 = new String(row_2);

        String row2_firstHalf = row2.substring(0, row2.length() / 2);
        String row2_secondHalf = row2.substring(row2.length() / 2);
        System.out.print("\t  ");
        System.out.println(row2_firstHalf + " " + row2_secondHalf);

        String row3 = new String(row_3);

        String row3_firstHalf = row3.substring(0, row3.length() / 2);
        String row3_secondHalf = row3.substring(row3.length() / 2);
        System.out.print("\t");
        System.out.println(row3_firstHalf + " " + row3_secondHalf);
    }
    private static void cancel_ticket() {
        try {
            System.out.println("---------|Cancel Ticket|---------\n");
            System.out.print("Enter row number (1 to 3): ");
            int row = input.nextInt();

            if (row == 1) {
                System.out.println("\nSeats Availability (O = Available , X = Booked) :-");
                System.out.println("Row 1: " + Arrays.toString(row_1));

                System.out.print("\nEnter a seat number between 1-12: ");
                int seatNum = input.nextInt();
                if (seatNum < 0 || seatNum > 12) {
                    System.out.println("Seat doesn't exist... Enter a valid seat number!");
                }
                if (row_1[seatNum - 1] == 'X') {
                    row_1[seatNum - 1] = 'O';
                    System.out.println("\nThe booking has being cancelled\nGoing back to the menu");
                    ticket_remover(row,seatNum);
                } else {
                    row_1[seatNum - 1] = 'O';
                    System.out.println("This seat hasn't being booked...");
                }

            } else if (row == 2) {
                System.out.println("\nSeats Availability (O = Available , X = Booked) :-");
                System.out.println("Row 2: " + Arrays.toString(row_2));

                System.out.print("\nEnter a seat number between 1-16: ");
                int seatNum = input.nextInt();
                if (seatNum < 0 || seatNum > 16) {
                    System.out.println("Seat doesn't exist... Enter a valid seat number!");
                }
                if (row_2[seatNum - 1] == 'X') {
                    row_2[seatNum - 1] = 'O';
                    System.out.println("\nThe booking has being cancelled\nGoing back to the menu");
                    ticket_remover(row,seatNum);
                } else {
                    row_2[seatNum - 1] = 'O';
                    System.out.println("This seat hasn't being booked...");
                }

            } else if (row == 3) {
                System.out.println("\nSeats Availability (O = Available , X = Booked) :-");
                System.out.println("Row 2: " + Arrays.toString(row_3));

                System.out.print("\nEnter a seat number between 1-20: ");
                int seatNum = input.nextInt();
                if (seatNum < 0 || seatNum > 20) {
                    System.out.println("Seat doesn't exist... Enter a valid seat number!");
                }
                if (row_3[seatNum - 1] == 'X') {
                    row_3[seatNum - 1] = 'O';
                    System.out.println("\nThe booking has being cancelled\nGoing back to the menu");
                    ticket_remover(row,seatNum);
                } else {
                    row_3[seatNum - 1] = 'X';
                    System.out.println("This seat hasn't being booked...");
                }
            } else {
                System.out.println("Enter a valid option!");
            }
        } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid input!");
        }
    }
    private  static void ticket_remover(int row,int seatNum){
        for(int i=0; i<tickets.size(); i++){
            if(tickets.get(i).row == row){
                if(tickets.get(i).seat == seatNum){
                    tickets.remove(i);
                }
            }
        }
    }
    private static void show_available() {
        System.out.print("Seats available in row 1: ");
        for (int i = 0; i < row_1.length; i++) {
            if (row_1[i] == 'O') {
                System.out.print((i + 1) + ", ");
            }
        }
        System.out.println();

        System.out.print("Seats available in row 2: ");
        for (int i = 0; i < row_2.length; i++) {
            if (row_2[i] == 'O') {
                System.out.print((i + 1) + ", ");
            }
        }
        System.out.println();

        System.out.print("Seats available in row 3: ");
        for (int i = 0; i < row_3.length; i++) {
            if (row_3[i] == 'O') {
                System.out.print((i + 1) + ", ");
            }
        }
        System.out.println();
    }
    private static void save() {
        try {
            FileWriter writer = new FileWriter("ArrayFile.txt");
            writer.write("1st Row = " + Arrays.toString(row_1));
            writer.write("\n");
            writer.write("2nd Row = " + Arrays.toString(row_2));
            writer.write("\n");
            writer.write("3rd Row = " + Arrays.toString(row_3));
            writer.write("\n");
            writer.close();
            System.out.println("Successfully saved the information into a file...\ngoing back to the main menu");
        } catch (IOException e) {
            System.out.println("Error saving data. Try again! (IOException)");
        }
    }
    private static void load() {
        try {
            File myObj = new File("ArrayFile.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error loading file! Check for missing file and try again...");
        }
    }
    private static void show_tickets_info() {
        System.out.println("---------|Ticket information|---------\n");
        double total = 0;
        for (int i = 0; i < tickets.size(); i++){
            tickets.get(i).print();
            System.out.println("-----------------------");
            total += tickets.get(i).price;
        }
        System.out.println("Total: £" + total);
    }
    private static void sort_tickets() {
        ArrayList<Ticket> sortedTickets = new ArrayList<Ticket>();
        int n = tickets.size();

        for(int i = 0; i < n-1; i++){
            for(int j = 0; j< n-1; j++){
                if(tickets.get(j).price > tickets.get(j+1).price){
                    Ticket temp = tickets.get(j);
                    tickets.set(j,tickets.get(j+1));
                    tickets.set(j+1,temp);
                }
            }
        }
        show_tickets_info();
    }
}