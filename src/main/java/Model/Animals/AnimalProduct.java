package Model.Animals;

import Model.Map.Item;

public class AnimalProduct implements Item {
    private final AnimalProductType type;

    public AnimalProduct(AnimalProductType type) {
        this.type = type;
    }


    public AnimalProductType getType() {
        return type;
    }

    @Override
    public String getName() {
        return type.getName();
    }

    @Override
    public int getPrice() {
        return type.getPrice();
    }
}
