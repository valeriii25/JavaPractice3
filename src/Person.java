import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Person {
    private String _name;
    private String _lastName;
    private String _patronymic;
    private LocalDate _dateOfBirth;
    private boolean isValidName(String name) {
        if (name.isBlank()) {
            System.out.println("Введено пустое значение");
            return false;
        }
        if (name.length() == 1) {
            System.out.println("Введено значение из одной буквы");
            return false;
        }
        if (Character.isLowerCase(name.charAt(0))) {
            System.out.println("Введенное значение должно начинаться с заглавной буквы");
            return false;
        }
        for (var character : name.toCharArray()) {
            if (!Character.isLetter(character)) {
                System.out.println("Введенное значение должно содержать только буквы");
                return false;
            }
            if (!Character.UnicodeBlock.of(character).equals(Character.UnicodeBlock.CYRILLIC)) {
                System.out.println("Введенное значение должно содержать только русские буквы");
                return false;
            }
        }
        return true;
    }
    public void setName(String type, Scanner scanner) {
        String tmp;
        do {
            System.out.println("Введите " + type + ": ");
            tmp = scanner.next().trim();
        } while (!isValidName(tmp));
        if (type.equals("имя")) _name = tmp;
        if (type.equals("фамилию")) _lastName = tmp;
        if (type.equals("отчество")) _patronymic = tmp;
    }
    private LocalDate isValidDate(String date) {
        LocalDate dateOfBirth;
        try {
            dateOfBirth = LocalDate.parse(date);
        }
        catch (DateTimeParseException e) {
            System.out.println("Дата введена не в том формате");
            return null;
        }
        if (dateOfBirth.isAfter(LocalDate.now())) {
            System.out.println("Дата еще не наступила");
            return null;
        }
        return dateOfBirth;
    }
    public void setDate(Scanner scanner) {
        LocalDate tmp;
        do {
            System.out.println("Введите дату рождения в формате гггг-мм-дд");
            tmp = isValidDate(scanner.next().trim());
        } while (tmp == null);
        _dateOfBirth = tmp;
    }
    public String getGender() {
        if (_patronymic.endsWith("ич")) return "мужской";
        return "женский";
    }
    public int getAge() {
        return Period.between(_dateOfBirth, LocalDate.now()).getYears();
    }
    public String getLastName() {
        return _lastName;
    }
    public char getNameInitial() {
        return _name.charAt(0);
    }
    public char getPatronymicInitial() {
        return _patronymic.charAt(0);
    }
}
