package Model.Animals;

import Model.Map.Item;

public class AnimalProduct implements Item {
    private final AnimalProductType type;
    private final double quality;

    public AnimalProduct(AnimalProductType type, int quality) {
        this.type = type;
        this.quality = quality;
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
