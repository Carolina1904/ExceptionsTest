package ExceptionsFinalTest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные в формате: Фамилия Имя Отчество дата_рождения номер_телефона пол");
        String input = scanner.nextLine().trim();

        try {
            Person person = parseInput(input);
            writeToFile(person);
            System.out.println("Данные успешно записаны в файл.");
        } catch (DataFormatException | IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Person parseInput(String input) throws DataFormatException {
        String[] parts = input.split("\\s+");
        if (parts.length != 6) {
            throw new DataFormatException("Неверное количество данных. Ожидалось 6, получено " + parts.length);
        }

        try {
            String surname = parts[0];
            String name = parts[1];
            String patronymic = parts[2];
            String dob = parts[3];
            long phoneNumber = Long.parseLong(parts[4]);
            if (phoneNumber <= 0) {
                throw new DataFormatException("Номер телефона должен быть положительным числом.");
            }
            char gender = parts[5].charAt(0);
            if (gender != 'f' && gender != 'm') {
                throw new DataFormatException("Неверное значение для пола. Используйте 'f' для женского и 'm' для мужского.");
            }
            return new Person(surname, name, patronymic, dob, phoneNumber, gender);
        } catch (NumberFormatException e) {
            throw new DataFormatException("Неверный формат номера телефона.");
        }
    }

    private static void writeToFile(Person person) throws IOException {
        String fileName = person.getSurname() + ".txt";
        Path filePath = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, Files.exists(filePath) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE)) {
            writer.write(person.toString());
            writer.newLine();
        }
    }
}
