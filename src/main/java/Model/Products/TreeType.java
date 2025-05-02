package Model.Products;

public enum TreeType {
    Apricot ("Apricot Sapling" , "Apricot"),
    Cherry ("Cherry Sapling" , "Cherry"),
    Banana ("Banana Sapling" , "Banana"),
    Mango ("Mango Sapling" , "Mango"),
    Orange ("Orange Sapling" , "Orange"),
    Peach ("Peach Sapling" , "Peach"),
    Apple ("Apple Sapling" , "Apple"),
    Pomegranate ("Pomegranate Sapling" , "Pomegranate"),
    Oak ("Acorns" , "Oak Resin"),
    Maple ("Maple Seeds" , "Maple Syrup"),
    Pine ("Pine Cones" , "Pine Tar"),
    Mahogany ("Mahogany Seeds" , "Sap"),
    Mushroom ("Mushroom Tree Seeds" , "Common Mushroom"),
    Mystic ("Mystic Tree Seeds" , "Mystic Syrup");

    private final String treeName;
    private final String fruitName;

    TreeType (String tree, String fruit)
    {
        this.treeName = tree;
        this.fruitName = fruit;
    }
}