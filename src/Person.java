public class Person {
    String name;
    String surname;
    String email;

    Person(String name,String surname,String email){
        this.name =name;
        this.surname=surname;
        this.email=email;
    }

    public String toString(){
        return name + "\n" + surname + "\n" + email;
    }
}
