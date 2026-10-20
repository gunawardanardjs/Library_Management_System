import model.Book;
import model.DVD;
import model.Library;
import model.LibraryItem;
import model.Member;
import model.Staff;
import model.Student;
import service.LibraryService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        LibraryService libraryService = new LibraryService(library);
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt(scanner, "Choose an option: ");

            try {
                switch (choice) {
                    case 1:
                        listItems(library);
                        break;
                    case 2:
                        addItem(scanner, library);
                        break;
                    case 3:
                        registerMember(scanner, library);
                        break;
                    case 4:
                        libraryService.borrowItem(readText(scanner, "Member ID: "),
                                readText(scanner, "Item ID: "));
                        System.out.println("Item borrowed successfully.");
                        break;
                    case 5:
                        libraryService.returnItem(readText(scanner, "Member ID: "),
                                readText(scanner, "Item ID: "));
                        System.out.println("Item returned successfully.");
                        break;
                    case 6:
                        running = false;
                        break;
                    default:
                        System.out.println("Please choose an option from 1 to 6.");
                }
            } catch (IllegalArgumentException | IllegalStateException exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\nLibrary Management System");
        System.out.println("1. List items");
        System.out.println("2. Add item");
        System.out.println("3. Register member");
        System.out.println("4. Borrow item");
        System.out.println("5. Return item");
        System.out.println("6. Exit");
    }

    private static void listItems(Library library) {
        if (library.getItems().isEmpty()) {
            System.out.println("No items in the library.");
            return;
        }
        for (LibraryItem item : library.getItems()) {
            System.out.println(item);
        }
    }

    private static void addItem(Scanner scanner, Library library) {
        int type = readInt(scanner, "Enter 1 for book or 2 for DVD: ");
        String id = readText(scanner, "Item ID: ");
        String title = readText(scanner, "Title: ");

        if (type == 1) {
            String author = readText(scanner, "Author: ");
            library.addItem(new Book(id, title, author));
        } else if (type == 2) {
            int duration = readInt(scanner, "Duration in minutes: ");
            library.addItem(new DVD(id, title, duration));
        } else {
            System.out.println("Invalid item type.");
            return;
        }
        System.out.println("Item added successfully.");
    }

    private static void registerMember(Scanner scanner, Library library) {
        int type = readInt(scanner, "Enter 1 for student or 2 for staff: ");
        String id = readText(scanner, "Member ID: ");
        String name = readText(scanner, "Name: ");

        Member member;
        if (type == 1) {
            member = new Student(id, name);
        } else if (type == 2) {
            member = new Staff(id, name);
        } else {
            System.out.println("Invalid member type.");
            return;
        }
        library.registerMember(member);
        System.out.println("Member registered successfully.");
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static String readText(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}