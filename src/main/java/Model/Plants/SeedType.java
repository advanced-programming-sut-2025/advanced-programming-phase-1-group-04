package Model.Plants;

import Controller.SirkBozorg.PlantController;
import Model.Time.Season;

import java.util.ArrayList;
import java.util.List;

public enum SeedType implements Forageable, PlantType {
    JazzSeeds("Jazz Seeds", new ArrayList<>(List.of(Season.Spring)), CropType.BlueJazz),
    CarrotSeeds("Carrot Seeds", new ArrayList<>(List.of(Season.Spring)), CropType.Carrot),
    CauliflowerSeeds("Cauliflower Seeds", new ArrayList<>(List.of(Season.Spring)), CropType.Cauliflower),
    CoffeeBean("Coffee Bean", new ArrayList<>(List.of(Season.Spring)), CropType.CoffeeBean),
    GarlicSeeds("Garlic Seeds", new ArrayList<>(List.of(Season.Spring)), CropType.Garlic),
    BeanStarter("Bean Starter", new ArrayList<>(List.of(Season.Spring)), CropType.GreenBean),
    KaleSeeds("Kale Seeds", new ArrayList<>(List.of(Season.Spring)), CropType.Kale),
    ParsnipSeeds("Parsnip Seeds", new ArrayList<>(List.of(Season.Spring)), CropType.Parsnip),
    PotatoSeeds("Potato Seeds", new ArrayList<>(List.of(Season.Spring)), CropType.Potato),
    RhubarbSeeds("Rhubarb Seeds", new ArrayList<>(List.of(Season.Spring)), CropType.Rhubarb),
    StrawberrySeeds("Strawberry Seeds", new ArrayList<>(List.of(Season.Spring)), CropType.Strawberry),
    TulipBulb("Tulip Bulb", new ArrayList<>(List.of(Season.Spring)), CropType.Tulip),
    RiceShoot("Rice Shoot", new ArrayList<>(List.of(Season.Spring)), CropType.UnmilledRice),
    BlueberrySeeds("Blueberry Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.Blueberry),
    CornSeeds("Corn Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.Corn),
    HopsStarter("Hops Starter", new ArrayList<>(List.of(Season.Summer)), CropType.Hops),
    PepperSeeds("Pepper Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.HotPepper),
    MelonSeeds("Melon Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.Melon),
    PoppySeeds("Poppy Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.Poppy),
    RadishSeeds("Radish Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.Radish),
    RedCabbageSeeds("Red Cabbage Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.RedCabbage),
    StarfruitSeeds("Starfruit Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.Starfruit),
    SpangleSeeds("Spangle Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.SummerSpangle),
    SummerSquashSeeds("Summer Squash Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.SummerSquash),
    SunflowerSeeds("Sunflower Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.Sunflower),
    TomatoSeeds("Tomato Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.Tomato),
    WheatSeeds("Wheat Seeds", new ArrayList<>(List.of(Season.Summer)), CropType.Wheat),
    AmaranthSeeds("Amaranth Seeds", new ArrayList<>(List.of(Season.Fall)), CropType.Amaranth),
    ArtichokeSeeds("Artichoke Seeds", new ArrayList<>(List.of(Season.Fall)), CropType.Artichoke),
    BeetSeeds("Beet Seeds", new ArrayList<>(List.of(Season.Fall)), CropType.Beet),
    BokChoySeeds("Bok Choy Seeds", new ArrayList<>(List.of(Season.Fall)), CropType.BokChoy),
    BroccoliSeeds("Broccoli Seeds", new ArrayList<>(List.of(Season.Fall)), CropType.Broccoli),
    CranberrySeeds("Cranberry Seeds", new ArrayList<>(List.of(Season.Fall)), CropType.Cranberries),
    EggplantSeeds("Eggplant Seeds", new ArrayList<>(List.of(Season.Fall)), CropType.Eggplant),
    FairySeeds("Fairy Seeds", new ArrayList<>(List.of(Season.Fall)), CropType.FairyRose),
    GrapeStarter("Grape Starter", new ArrayList<>(List.of(Season.Fall)), CropType.Grape),
    PumpkinSeeds("Pumpkin Seeds", new ArrayList<>(List.of(Season.Fall)), CropType.Pumpkin),
    YamSeeds("Yam Seeds", new ArrayList<>(List.of(Season.Fall)), CropType.Yam),
    RareSeed("Rare Seed", new ArrayList<>(List.of(Season.Fall)), CropType.SweetGemBerry),
    PowdermelonSeeds("Powdermelon Seeds", new ArrayList<>(List.of(Season.Winter)), CropType.Powdermelon),
    AncientSeeds("Ancient Seeds", new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall)), CropType.AncientFruit),
    GrassStarter("Grass Starter", new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)), CropType.Grass),
    MixedSeeds("Mixed Seeds", new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)), CropType.Mixed);


    private final String name;
    private final ArrayList<Season> seasons;
    private CropType crop;

    SeedType (String name, ArrayList<Season> seasons, CropType crop) {
        this.name = name;
        this.seasons = seasons;
        this.crop = crop;
    }


    public String getName() {
        return name;
    }

    @Override
    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public CropType getCrop() {
        return crop;
    }

    @Override
    public boolean isForageable () {
        return true;
    }

    @Override
    public String getInformation() {
        return "seed information:\nname: " + name + "\nseason: " + PlantController.SeasonsToString(seasons);
    }
}
