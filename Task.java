import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Task {

    public static void main(String[] args) {
        System.out.println("Введите данные в формате: Фамилия Имя Отчество" +
                "дата рождения номер телефона пол");
        try (Scanner sc = new Scanner(System.in)) {
            String input = sc.nextLine();
            String[] data = input.split(" ");
            if (data.length != 6) {
                System.out.println("Ошибка: введено неверное количество данных");
                System.exit(1);
            }

            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];
            Date dob = null;
            long phoneNum = 0;
            char gender = ' ';

            SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
            try {
                dob = formatDate.parse(data[3]);

            } catch (ParseException e) {
                System.out.println("Ошибка: формат даты рождения dd-MM-yyyy");
                System.exit(1);
            }

            try {
                phoneNum = Long.parseLong(data[4]);

            } catch (NumberFormatException e) {
                System.out.println("Ошибка: номер телефона должен быть целым числом");
                System.exit(1);
            }

            if (data[5].equals("m")) {
                gender = 'M';
            } else if (data[5].equals("f")) {
                gender = 'F';
            } else {
                System.out.println("Ошибка: пол должен быть указан как 'm' или 'f'");
                System.exit(1);
            }

            try {
                String fileName = lastName + ".txt";
                FileWriter writer = new FileWriter(fileName, true);
                writer.write(lastName + " " + firstName + " " + middleName + " "
                        + formatDate.format(dob) + " " + phoneNum + " " + gender);
                writer.close();
                System.out.println("Данные записаны в файл " + fileName);
            } catch (IOException e) {
                System.out.println("Ошибка: " + e.getMessage());

            }
        }
    }

}