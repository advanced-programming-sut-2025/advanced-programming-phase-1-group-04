package Model.Products;

public class Fruit {
    private final FruitType type;

    //constructor:
    public Fruit (FruitType type) {
        this.type = type;
    }

    //getters:
    public String getName() {
        return type.getName();
    }

    public int getBaseSellPrice() {
        return type.getBaseSellPrice();
    }

    public boolean isEdible() {
        return type.isEdible();
    }

    public int getEnergy() {
        return type.getEnergy();
    }

    public TreeType getTreeType() {
        return type.getTreeType();
    }
}