package service;

import model.Drink;

import java.util.List;
import java.util.Optional;

public class DrinkManager {
    public Optional<Drink> findDrinkByName(List<Drink> drinks, String name) {
        return drinks.stream()
                .filter(d -> d.getName().equalsIgnoreCase(name))
                .findFirst();
    }
}
