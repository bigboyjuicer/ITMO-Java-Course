package validator;

import java.util.Scanner;

/***
 * Class that validates user input.
 *
 * @author Maksim Zinin
 * @version 1.0
 */
public class InputValidator {

  private static final Scanner SCANNER = new Scanner(System.in);

  public static String stringValidator(String field) {
    System.out.printf("Введите %s: ", field);
    String str = SCANNER.nextLine().trim();
    while (str.isEmpty()) {
      System.out.println("Поле не может быть пустым");
      System.out.printf("Введите %s: ", field);
      str = SCANNER.nextLine().trim();
    }
    return str;
  }

  public static int intValidator(String field) {
    while (true) {
      try {
        System.out.printf("Введите %s: ", field);
        String str = SCANNER.nextLine().trim();
        return Integer.parseInt(str);
      } catch (NumberFormatException ex) {
        System.out.println("Поле должно быть числом");
      }
    }
  }

  public static int intValidator(String field, int min) {
    while (true) {
      try {
        System.out.printf("Введите %s: ", field);
        String str = SCANNER.nextLine().trim();
        int num = Integer.parseInt(str);
        if (num >= min) return num;
        else System.out.printf("Значение должно быть больше %d\n", min - 1);
      } catch (NumberFormatException ex) {
        System.out.println("Поле должно быть числом");
      }
    }
  }

  public static int intValidator(String field, int min, int max) {
    while (true) {
      try {
        System.out.printf("Введите %s: ", field);
        String str = SCANNER.nextLine().trim();
        int num = Integer.parseInt(str);
        if (num >= min && num <= max) return num;
        else
          System.out.printf(
              "Значение должно быть больше %d. Максимальное значение поля: %d\n", min - 1, max);
      } catch (NumberFormatException ex) {
        System.out.println("Поле должно быть числом");
      }
    }
  }

  public static float floatValidator(String field, int min) {
    while (true) {
      try {
        System.out.printf("Введите %s: ", field);
        String str = SCANNER.nextLine().trim();
        float num = Float.parseFloat(str);
        if (num >= min) return num;
        else System.out.printf("Значение должно быть больше %d\n", min - 1);
      } catch (NumberFormatException ex) {
        System.out.println("Поле должно быть числом с плавающей точкой");
      }
    }
  }

  public static long longValidator(String field) {
    while (true) {
      try {
        System.out.printf("Введите %s: ", field);
        String str = SCANNER.nextLine().trim();
        return Long.parseLong(str);
      } catch (NumberFormatException ex) {
        System.out.println("Поле должно быть числом");
      }
    }
  }
}
