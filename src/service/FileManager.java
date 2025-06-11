package service;

import model.Drink;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static final String FILE_NAME = "drinks.csv";

    public List<Drink> readDrinks() {
        List<Drink> drinks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                drinks.add(new Drink(parts[0], Double.parseDouble(parts[1]), Integer.parseInt(parts[2])));
            }
        } catch (IOException e) {
            System.out.println("Błąd odczytu pliku.");
        }
        return drinks;
    }

    public void saveDrinks(List<Drink> drinks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Drink d : drinks) {
                writer.write(d.getName() + "," + d.getPrice() + "," + d.getQuantity());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Błąd zapisu pliku.");
        }
    }
}
