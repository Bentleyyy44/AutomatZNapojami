package ui;

import exception.InsufficientFundsException;
import model.Drink;
import model.VendingMachine;
import service.DrinkManager;
import service.FileManager;
import service.TransactionManager;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private Scanner scanner = new Scanner(System.in);
    private FileManager fileManager = new FileManager();
    private TransactionManager transactionManager = new TransactionManager();
    private List<Drink> drinks = fileManager.readDrinks();
    private VendingMachine vendingMachine = new VendingMachine(drinks, transactionManager);
    private DrinkManager drinkManager = new DrinkManager();

    public void start() {
        System.out.println("Witaj w automacie z napojami!");
        while (true) {
            System.out.println("\n1. Użytkownik\n2. Administrator\n3. Wyjście");
            switch (scanner.nextLine()) {
                case "1" -> customerMenu();
                case "2" -> adminMenu();
                case "3" -> {
                    fileManager.saveDrinks(drinks);
                    return;
                }
                default -> System.out.println("Nieznana opcja.");
            }
        }
    }

    private void customerMenu() {
        while (true) {
            System.out.println("\n--- Menu klienta ---");

            List<Drink> availableDrinks = vendingMachine.getDrinks();

            for (int i = 0; i < availableDrinks.size(); i++) {
                System.out.println((i + 1) + ". " + availableDrinks.get(i));
            }

            System.out.println("Wybierz numer napoju lub wpisz X, aby wrócić:");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("X")) break;

            try {
                int index = Integer.parseInt(choice) - 1;

                if (index < 0 || index >= availableDrinks.size()) {
                    System.out.println("Nieprawidłowy numer.");
                    continue;
                }

                Drink selectedDrink = availableDrinks.get(index);

                System.out.print("Wrzuć monety (PLN): ");
                double amount = Double.parseDouble(scanner.nextLine());

                vendingMachine.buyDrink(selectedDrink.getName(), amount);
                System.out.println("Dziękujemy za zakup!");

            } catch (NumberFormatException e) {
                System.out.println("Błąd: Wpisz numer lub X.");
            } catch (InsufficientFundsException e) {
                System.out.println("Błąd: " + e.getMessage());
            }
        }
    }



    private void adminMenu() {
        System.out.print("Podaj hasło admina: ");
        if (!scanner.nextLine().equals("admin123")) {
            System.out.println("Niepoprawne hasło.");
            return;
        }

        while (true) {
            System.out.println("\n--- Menu administratora ---");
            System.out.println("1. Dodaj napój\n2. Usuń napój\n3. Lista transakcji\n4. Powrót");
            switch (scanner.nextLine()) {
                case "1" -> {
                    System.out.print("Nazwa: ");
                    String name = scanner.nextLine();
                    System.out.print("Cena: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    System.out.print("Ilość: ");
                    int qty = Integer.parseInt(scanner.nextLine());
                    vendingMachine.addDrink(new Drink(name, price, qty));
                    System.out.println("Napój dodany.");
                }
                case "2" -> {
                    System.out.print("Podaj nazwę napoju: ");
                    vendingMachine.removeDrink(scanner.nextLine());
                    System.out.println("Napój usunięty.");
                }
                case "3" -> transactionManager.getTransactions().forEach(System.out::println);
                case "4" -> { return; }
                default -> System.out.println("Nieznana opcja.");
            }
        }
    }
}
