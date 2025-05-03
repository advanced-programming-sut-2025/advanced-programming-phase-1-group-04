package Model.Products;

public enum ForagingMineralType {
    Quartz (new ForagingMineral("Quartz", 25)),
    EarthCrystal (new ForagingMineral("Earth Crystal", 50)),
    FrozenTear (new ForagingMineral("Frozen Tear", 75)),
    FireQuartz (new ForagingMineral("Fire Quartz", 100)),
    Emerald (new ForagingMineral("Emerald", 250)),
    Aquamarine (new ForagingMineral("Aquamarine", 180)),
    Ruby (new ForagingMineral("Ruby", 250)),
    Amethyst (new ForagingMineral("Amethyst", 100)),
    Topaz (new ForagingMineral("Topaz", 80)),
    Jade (new ForagingMineral("Jade", 200)),
    Diamond (new ForagingMineral("Diamond", 750)),
    PrismaticShard (new ForagingMineral("Prismatic Shard", 2000)),
    Copper (new ForagingMineral("Copper", 5)),
    Iron (new ForagingMineral("Iron", 10)),
    Gold (new ForagingMineral("Gold", 25)),
    Iriduim (new ForagingMineral("Iriduim", 100)),
    Coal (new ForagingMineral("Coal", 15));

    private final ForagingMineral mineral;

    ForagingMineralType(ForagingMineral mineral) {
        this.mineral = mineral;
    }

    public ForagingMineral getMineral() {
        return mineral;
    }
}
