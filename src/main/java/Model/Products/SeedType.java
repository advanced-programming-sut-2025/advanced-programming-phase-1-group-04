package Model.Products;

import Model.Enum.Season;

import java.util.ArrayList;
import java.util.List;

public enum SeedType {
    JazzSeeds(new Seed("Jazz Seeds", new ArrayList<>(List.of(Season.Spring)), CropType.BlueJazz)),
    CarrotSeeds(new Seed("Carrot Seeds", new ArrayList<>(List.of(Season.Spring)), CropType.Carrot)),
    CauliflowerSeeds(new Seed("Cauliflower Seeds", new ArrayList<>(List.of(Season.Spring)), CropType.Cauliflower)),
    CoffeeBean(new Seed("Coffee Bean", new ArrayList<>(List.of(Season.Spring)), CropType.CoffeeBean)),
    GarlicSeeds(new Seed("Garlic Seeds", new ArrayList<>(List.of(Season.Spring)), CropType.Garlic)),
    BeanStarter(new Seed("Bean Starter", new ArrayList<>(List.of(Season.Spring)), CropType.GreenBean)),
    KaleSeeds(new Seed("Kale Seeds", new ArrayList<>(List.of(Season.Spring)), CropType.Kale)),
    ParsnipSeeds(new Seed("Parsnip Seeds", new ArrayList<>(List.of(Season.Spring)), CropType.Parsnip)),
    PotatoSeeds(new Seed("Potato Seeds", new ArrayList<>(List.of(Season.Spring)), CropType.Potato)),
    RhubarbSeeds(new Seed("Rhubarb Seeds", new ArrayList<>(List.of(Season.Spring)), CropType.Rhubarb)),
    StrawberrySeeds(new Seed("Strawberry Seeds", new ArrayList<>(List.of(Season.Spring)), CropType.Strawberry)),
    TulipBulb(new Seed("Tulip Bulb", new ArrayList<>(List.of(Season.Spring)), CropType.Tulip)),
    RiceShoot(new Seed("Rice Shoot", new ArrayList<>(List.of(Season.Spring)), CropType.UnmilledRice)),
    BlueberrySeeds(new Seed("Blueberry Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.Blueberry)),
    CornSeeds(new Seed("Corn Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.Corn)),
    HopsStarter(new Seed("Hops Starter", new ArrayList<>(List.of(Season.Summer)), CropType.Hops)),
    PepperSeeds(new Seed("Pepper Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.HotPepper)),
    MelonSeeds(new Seed("Melon Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.Melon)),
    PoppySeeds(new Seed("Poppy Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.Poppy)),
    RadishSeeds(new Seed("Radish Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.Radish)),
    RedCabbageSeeds(new Seed("Red Cabbage Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.RedCabbage)),
    StarfruitSeeds(new Seed("Starfruit Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.Starfruit)),
    SpangleSeeds(new Seed("Spangle Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.SummerSpangle)),
    SummerSquashSeeds(new Seed("Summer Squash Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.SummerSquash)),
    SunflowerSeeds(new Seed("Sunflower Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.Sunflower)),
    TomatoSeeds(new Seed("Tomato Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.Tomato)),
    WheatSeeds(new Seed("Wheat Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.Wheat)),
    AmaranthSeeds(new Seed("Amaranth Seeds", new ArrayList<>(List.of(Season.Fall)), CropType.Amaranth)),
    ArtichokeSeeds(new Seed("Artichoke Seeds", new ArrayList<>(List.of(Season.Fall)), CropType.Artichoke)),
    BeetSeeds(new Seed("Beet Seeds", new ArrayList<>(List.of(Season.Fall)), CropType.Beet)),
    BokChoySeeds(new Seed("Bok Choy Seeds", new ArrayList<>(List.of(Season.Fall)), CropType.BokChoy)),
    BroccoliSeeds(new Seed("Broccoli Seeds", new ArrayList<>(List.of(Season.Fall)), CropType.Broccoli)),
    CranberrySeeds(new Seed("Cranberry Seeds", new ArrayList<>(List.of(Season.Fall)), CropType.Cranberries)),
    EggplantSeeds(new Seed("Eggplant Seeds", new ArrayList<>(List.of(Season.Fall)), CropType.Eggplant)),
    FairySeeds(new Seed("Fairy Seeds", new ArrayList<>(List.of(Season.Fall)), CropType.FairyRose)),
    GrapeStarter(new Seed("Grape Starter", new ArrayList<>(List.of(Season.Fall)), CropType.Grape)),
    PumpkinSeeds(new Seed("Pumpkin Seeds", new ArrayList<>(List.of(Season.Fall)), CropType.Pumpkin)),
    YamSeeds(new Seed("Yam Seeds", new ArrayList<>(List.of(Season.Fall)), CropType.Yam)),
    RareSeed(new Seed("Rare Seed", new ArrayList<>(List.of(Season.Fall)), CropType.SweetGemBerry)),
    PowdermelonSeeds(new Seed("Powdermelon Seeds", new ArrayList<>(List.of(Season.Winter)), CropType.Powdermelon)),
    AncientSeeds(new Seed("Ancient Seeds", new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall)), CropType.AncientFruit)),
    MixedSeeds(new Seed("Mixed Seeds", new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)), CropType.Mixed));


    private final Seed seed;

    SeedType (Seed seed) {
        this.seed = seed;
    }

    public Seed getSeed() {
        return seed;
    }
}
