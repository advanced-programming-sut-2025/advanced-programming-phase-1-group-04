package Model.Products;

public enum FruitType {
    Apricot (new Fruit.FruitBuilder()
            .setName("Apricot")
            .setBaseSellPrice(59)
            .setEdible(true)
            .setEnergy(38)
            .setTree(TreeType.Apricot)
            .build()),

    Cherry(new Fruit.FruitBuilder()
            .setName("Cherry")
            .setBaseSellPrice(80)
            .setEdible(true)
            .setEnergy(38)
            .setTree(TreeType.Cherry)
            .build()),

    Banana(new Fruit.FruitBuilder()
            .setName("Banana")
            .setBaseSellPrice(150)
            .setEdible(true)
            .setEnergy(75)
            .setTree(TreeType.Banana)
            .build()),

    Mango(new Fruit.FruitBuilder()
            .setName("Mango")
            .setBaseSellPrice(130)
            .setEdible(true)
            .setEnergy(100)
            .setTree(TreeType.Mango)
            .build()),

    Orange(new Fruit.FruitBuilder()
            .setName("Orange")
            .setBaseSellPrice(100)
            .setEdible(true)
            .setEnergy(38)
            .setTree(TreeType.Orange)
            .build()),

    Peach(new Fruit.FruitBuilder()
            .setName("Peach")
            .setBaseSellPrice(140)
            .setEdible(true)
            .setEnergy(38)
            .setTree(TreeType.Peach)
            .build()),

    Apple(new Fruit.FruitBuilder()
            .setName("Apple")
            .setBaseSellPrice(100)
            .setEdible(true)
            .setEnergy(38)
            .setTree(TreeType.Apple)
            .build()),

    Pomegranate(new Fruit.FruitBuilder()
            .setName("Pomegranate")
            .setBaseSellPrice(140)
            .setEdible(true)
            .setEnergy(38)
            .setTree(TreeType.Pomegranate)
            .build()),

    OakResin (new Fruit.FruitBuilder()
            .setName("Oak Resin ")
            .setBaseSellPrice(150)
            .setEdible(false)
            .setEnergy(0)
            .setTree(TreeType.Oak)
            .build()),

    MapleSyrup(new Fruit.FruitBuilder()
            .setName("Maple Syrup")
            .setBaseSellPrice(200)
            .setEdible(false)
            .setEnergy(0)
            .setTree(TreeType.Maple)
            .build()),

    PineTar(new Fruit.FruitBuilder()
            .setName("Pine Tar")
            .setBaseSellPrice(100)
            .setEdible(false)
            .setEnergy(0)
            .setTree(TreeType.Pine)
            .build()),

    Sap(new Fruit.FruitBuilder()
            .setName("Sap")
            .setBaseSellPrice(2)
            .setEdible(true)
            .setEnergy(-2)
            .setTree(TreeType.Mahogany)
            .build()),

    CommonMushroom(new Fruit.FruitBuilder()
            .setName("Common Mushroom")
            .setBaseSellPrice(40)
            .setEdible(true)
            .setEnergy(38)
            .setTree(TreeType.Mushroom)
            .build()),

    MysticSyrup(new Fruit.FruitBuilder()
            .setName("Mystic Syrup")
            .setBaseSellPrice(1000)
            .setEdible(true)
            .setEnergy(500)
            .setTree(TreeType.Mystic)
            .build());

    private final Fruit fruit;

    FruitType(Fruit fruit) {
        this.fruit = fruit;
    }

    public Fruit getFruit() {
        return fruit;
    }
}