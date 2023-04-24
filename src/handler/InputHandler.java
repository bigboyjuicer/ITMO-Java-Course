package handler;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {

    private static final Scanner scanner = new Scanner(System.in);

    public static String stringHandler(String field) {
        System.out.printf("Введите %s: ", field);
        String str = scanner.nextLine().trim();
        while(str.isEmpty()) {
            System.out.println("Поле не может быть пустым");
            System.out.printf("Введите %s: ", field);
            str = scanner.nextLine().trim();
        }
        return str;
    }

    public static int intHandler(String field) {
        while (true){
            try {
                System.out.printf("Введите %s: ", field);
                String str = scanner.nextLine().trim();
                return Integer.parseInt(str);
            } catch (NumberFormatException ex) {
                System.out.println("Поле должно быть числом");
            }
        }
    }

    public static int intHandler(String field, int min) {
        while (true){
            try {
                System.out.printf("Введите %s: ", field);
                String str = scanner.nextLine().trim();
                int num = Integer.parseInt(str);
                if(num >= min) return num;
                else System.out.printf("Значение должно быть больше %d\n", min - 1);
            } catch (NumberFormatException ex) {
                System.out.println("Поле должно быть числом");
            }
        }
    }

    public static int intHandler(String field, int min, int max) {
        while (true){
            try {
                System.out.printf("Введите %s: ", field);
                String str = scanner.nextLine().trim();
                int num = Integer.parseInt(str);
                if(num >= min && num <= max) return num;
                else System.out.printf("Значение должно быть больше %d. Максимальное значение поля: %d\n", min - 1, max);
            } catch (NumberFormatException ex) {
                System.out.println("Поле должно быть числом");
            }
        }
    }

    public static float floatHandler(String field) {
        while (true){
            try {
                System.out.printf("Введите %s: ", field);
                String str = scanner.nextLine().trim();
                return Float.parseFloat(str);
            } catch (NumberFormatException ex) {
                System.out.println("Поле должно быть числом с плавающей точкой");
            }
        }
    }

    public static float floatHandler(String field, int min) {
        while (true){
            try {
                System.out.printf("Введите %s: ", field);
                String str = scanner.nextLine().trim();
                float num = Float.parseFloat(str);
                if(num >= min) return num;
                else System.out.printf("Значение должно быть больше %d\n", min - 1);
            } catch (NumberFormatException ex) {
                System.out.println("Поле должно быть числом с плавающей точкой");
            }
        }
    }

    public static long longHandler(String field) {
        while (true){
            try {
                System.out.printf("Введите %s: ", field);
                String str = scanner.nextLine().trim();
                return Long.parseLong(str);
            } catch (NumberFormatException ex) {
                System.out.println("Поле должно быть числом");
            }
        }
    }

}
