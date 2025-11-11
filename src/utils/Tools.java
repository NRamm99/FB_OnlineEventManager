package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Tools {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";

    private Tools() {
    }

    public static void clearConsole() {
        for (int n = 0; n < 20; n++) {
            System.out.println();
        }
    }

    public static void waitForUser(Scanner input) {
        System.out.println("\nPress enter to continue...");
        input.nextLine();
    }

    public static void printToConsole(String text, boolean clear) {
        if (clear) {
            clearConsole();
        }
        System.out.println(text);
    }

    public static void printToConsole(String text) {
        printToConsole(text, false);
    }

    public static void titlePrinter(String title) {
        titlePrinter(title, false);
    }

    public static void titlePrinter(String title, boolean clear) {
        printToConsole("---------- " + title + " ----------", clear);
    }

    public static int validateInt(Scanner input, String message) {
        while (true) {
            System.out.print(message + ": ");
            String userStr = input.nextLine().trim();
            try {
                return Integer.parseInt(userStr);
            } catch (NumberFormatException e) {
                System.out.println(RED + "❌ Invalid input. Please enter a whole number." + RESET);
            }
        }
    }

    public static String validateName(Scanner input, String message) {
        while (true) {
            System.out.print(message + ": ");
            String name = input.nextLine();
            if (name.matches("^[A-Za-zÆØÅæøå\\s]+$")) {
                return name;
            } else {
                Tools.printToConsole("❌ Invalid name – only letters and spaces allowed.");
            }
        }
    }

    public static double validateDouble(Scanner input, String message) {
        while (true) {
            System.out.print(message + ": ");
            String userStr = input.nextLine().trim().replace(',', '.');

            try {
                return Double.parseDouble(userStr);
            } catch (NumberFormatException e) {
                System.out.println(RED + "❌ Invalid number. Please enter a valid decimal value." + RESET);
            }
        }
    }

    public static String validateDate(Scanner input, String message) {
        while (true) {
            System.out.print(message + ": ");
            String date = input.nextLine();
            try {
                LocalDate parsed = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                return parsed.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e) {
                Tools.printToConsole("❌ Invalid date. Please enter a valid date in the format DD/MM/YYYY.");
            }

        }
    }

    public static String validateTime(Scanner input, String message) {
        while (true) {
            System.out.print(message + ": ");
            String time = input.nextLine();
            if (time.matches("^\\d{2}:\\d{2}$")) {
                return time;
            } else {
                Tools.printToConsole("❌ Invalid time. Please enter a valid time in the format HH:MM.");
            }
        }
    }

    public static String validateString(Scanner input, String message) {
        while (true) {
            System.out.print(message + ": ");
            String string = input.nextLine();
            if (string.matches("^[A-Za-zÆØÅæøå\\s]+$")) {
                return string;
            } else {
                Tools.printToConsole("❌ Invalid string. Please enter a valid string.");
            }
        }
    }
}