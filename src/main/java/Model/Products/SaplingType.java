package Model.Products;

import Model.Enum.Season;

import java.util.ArrayList;
import java.util.List;

public enum SaplingType {
    ApricotSapling(new Sapling("Apricot Sapling", new ArrayList<>(List.of(Season.Spring)), TreeType.Apricot, false)),
    CherrySapling(new Sapling("Cherry Sapling", new ArrayList<>(List.of(Season.Spring)), TreeType.Cherry, false)),
    BananaSapling(new Sapling("Banana Sapling", new ArrayList<>(List.of(Season.Summer)), TreeType.Banana, false)),
    MangoSapling(new Sapling("Mango Sapling", new ArrayList<>(List.of(Season.Summer)), TreeType.Mango, false)),
    OrangeSapling(new Sapling("Orange Sapling", new ArrayList<>(List.of(Season.Summer)), TreeType.Orange, false)),
    PeachSapling(new Sapling("Peach Sapling", new ArrayList<>(List.of(Season.Summer)), TreeType.Peach, false)),
    AppleSapling(new Sapling("Apple Sapling", new ArrayList<>(List.of(Season.Fall)), TreeType.Apple, false)),
    PomegranateSapling(new Sapling("Pomegranate Sapling", new ArrayList<>(List.of(Season.Fall)), TreeType.Pomegranate, false)),
    Acorns(new Sapling("Acorns", new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)), TreeType.Oak, true)),
    MapleSeeds(new Sapling("Maple Seeds", new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)), TreeType.Maple, true)),
    PineCones(new Sapling("Pine Cones", new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)), TreeType.Pine, true)),
    MahoganySeeds(new Sapling("Mahogany Seeds", new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)), TreeType.Mahogany, true)),
    MushroomTreeSeeds(new Sapling("Mushroom Tree Seeds", new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)), TreeType.Mushroom, true)),
    MysticTreeSeeds(new Sapling("Mystic Tree Seeds", new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)), TreeType.Mystic, false));


    private final Sapling sapling;

    SaplingType (Sapling sapling) {
        this.sapling = sapling;
    }

    public Sapling getSapling() {
        return sapling;
    }
}
