package Model.Animals;

import Model.Map.Item;

import java.util.ArrayList;

public class Animal {
    private final AnimalType type;
    private final String name;
    private int friendship = 0;
    private ArrayList<AnimalProduct> products = new ArrayList<>();

    public Animal(AnimalType type, String name) {
        this.type = type;
        this.name = name;
    }
}
