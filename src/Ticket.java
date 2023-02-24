public class Ticket{
    int row;
    int seat;
    double price;
    Person person;
    Ticket(int row, int seat, double price,Person person) {

        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person= person;
    }
    void print(){
        System.out.println("Name = "+person.name);
        System.out.println("last name = "+person.surname);
        System.out.println("Email = "+person.email);
        System.out.println("Row = "+row);
        System.out.println("Seat = "+seat);
        System.out.println("Price = "+price);
    }
}
