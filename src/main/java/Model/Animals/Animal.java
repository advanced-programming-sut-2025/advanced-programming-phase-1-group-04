package Model.Animals;


import Model.Map.Coordinate;

import java.util.ArrayList;

public class Animal {
    private final AnimalType type;
    private final String name;

    private int friendship = 0;
    private boolean isPetted = false;
    private boolean isFeeded = false;

    private Coordinate coordinate;

    private ArrayList<AnimalProduct> products = new ArrayList<>();

    public Animal(AnimalType type, String name) {
        this.type = type;
        this.name = name;
    }

    public void setFriendship(int friendship) {
        this.friendship = friendship;
    }

    public void setPetted(boolean petted) {
        isPetted = petted;
    }

    public void setFeeded(boolean feeded) {
        isFeeded = feeded;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getName() {
        return name;
    }

    /*public String getProductsString() {
        for (AnimalProduct product: this.products) {

        }
    }*/

    public AnimalType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\n" +
            "Type: " + this.type.getName() + "\n" +
            "Description: " + this.type.getDescription() + "\n" +
            "FriendShip: " + this.friendship + "\n" +
            "Has the animal been petted today? " + this.isPetted + "\n" +
            "Has the animal been fed today? " + this.isFeeded + "\n" +
            "__________________________________________\n";
    }
}
