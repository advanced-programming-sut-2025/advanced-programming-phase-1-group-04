package Model.Products;

public class Fruit {
    private final String name;
    private final int baseSellPrice;
    private final boolean isEdible;
    private final int energy;
    private final TreeType tree;

    //constructor:
    public Fruit (FruitBuilder builder) {
        this.name = builder.name;
        this.baseSellPrice = builder.baseSellPrice;
        this.isEdible = builder.isEdible;
        this.energy = builder.energy;
        this.tree = builder.tree;
    }


    //getters:
    public String getName() {
        return name;
    }

    public int getBaseSellPrice() {
        return baseSellPrice;
    }

    public boolean isEdible() {
        return isEdible;
    }

    public int getEnergy() {
        return energy;
    }

    public TreeType getTree() {
        return tree;
    }

    //builder:
    public static class FruitBuilder {
        private String name;
        private int baseSellPrice;
        private boolean isEdible;
        private int energy;
        private TreeType tree;

        public FruitBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public FruitBuilder setBaseSellPrice(int baseSellPrice) {
            this.baseSellPrice = baseSellPrice;
            return this;
        }

        public FruitBuilder setEdible(boolean edible) {
            isEdible = edible;
            return this;
        }

        public FruitBuilder setEnergy(int energy) {
            this.energy = energy;
            return this;
        }

        public FruitBuilder setTree(TreeType tree) {
            this.tree = tree;
            return this;
        }

        public Fruit build() {
            return new Fruit(this);
        }
    }
}