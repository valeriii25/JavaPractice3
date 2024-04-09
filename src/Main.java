import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var in = new Scanner(System.in);
        var person = new Person();
        person.setName("фамилию", in);
        person.setName("имя", in);
        person.setName("отчество", in);
        person.setDate(in);
        System.out.println(person.getLastName() + ' ' +
                           person.getNameInitial() + "." +
                           person.getPatronymicInitial() + '.');
        System.out.println("Пол: " + person.getGender() + "\n" + "Возраст: " + person.getAge());
    }
}