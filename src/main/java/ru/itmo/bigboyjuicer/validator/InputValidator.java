package ru.itmo.bigboyjuicer.validator;

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
    System.out.printf("Write %s: ", field);
    String str = SCANNER.nextLine().trim();
    while (str.isEmpty()) {
      System.out.println("The field cannot be empty");
      System.out.printf("Write %s: ", field);
      str = SCANNER.nextLine().trim();
    }
    return str;
  }

  public static int intValidator(String field) {
    while (true) {
      try {
        System.out.printf("Write %s: ", field);
        String str = SCANNER.nextLine().trim();
        return Integer.parseInt(str);
      } catch (NumberFormatException ex) {
        System.out.println("The field must be a number");
        SCANNER.remove();
      }
    }
  }

  public static int intValidator(String field, int min) {
    while (true) {
      try {
        System.out.printf("Write %s: ", field);
        String str = SCANNER.nextLine().trim();
        int num = Integer.parseInt(str);
        if (num >= min) return num;
        else System.out.printf("The value must be greater than %d\n", min - 1);
      } catch (NumberFormatException ex) {
        System.out.println("The field must be a number");
      }
    }
  }

  public static int intValidator(String field, int min, int max) {
    while (true) {
      try {
        System.out.printf("Write %s: ", field);
        String str = SCANNER.nextLine().trim();
        int num = Integer.parseInt(str);
        if (num >= min && num <= max) return num;
        else
          System.out.printf(
              "The value must be greater than %d. Maximum field value: %d\n", min - 1, max);
      } catch (NumberFormatException ex) {
        System.out.println("The field must be a number");
      }
    }
  }

  public static float floatValidator(String field, int min) {
    while (true) {
      try {
        System.out.printf("Write %s: ", field);
        String str = SCANNER.nextLine().trim();
        float num = Float.parseFloat(str);
        if (num >= min) return num;
        else System.out.printf("The value must be greater than %d\n", min - 1);
      } catch (NumberFormatException ex) {
        System.out.println("The field must be a floating point number");
      }
    }
  }

  public static long longValidator(String field) {
    while (true) {
      try {
        System.out.printf("Write %s: ", field);
        String str = SCANNER.nextLine().trim();
        return Long.parseLong(str);
      } catch (NumberFormatException ex) {
        System.out.println("The field must be a number");
      }
    }
  }
}
