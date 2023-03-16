public class Ticket{
    int row;
    int seat;
    double price;
    Person person;
    Ticket (int row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }
    void print(){
        System.out.printf("Person Name: %s\n",(this.person.name));
        System.out.printf("Person Surname: %s\n",(this.person.surname));
        System.out.printf("Person Email: %s\n",(this.person.email));
        System.out.printf("Row No: %d\n", (this.row));
        System.out.printf("Seat No: %d\n", (this.seat));
        System.out.printf("Ticket Price: £%.2f\n", (this.price));
    }
}
