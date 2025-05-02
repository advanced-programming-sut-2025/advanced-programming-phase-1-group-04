package Model.Products;

import Model.Enum.Season;

import java.util.ArrayList;
import java.util.List;

public enum SaplingType {
    ApricotSapling(new Sapling("Apricot Sapling", new ArrayList<>(List.of(Season.Spring)), TreeType.Apricot)),
    CherrySapling(new Sapling("Cherry Sapling", new ArrayList<>(List.of(Season.Spring)), TreeType.Cherry)),
    BananaSapling(new Sapling("Banana Sapling", new ArrayList<>(List.of(Season.Summer)), TreeType.Banana)),
    MangoSapling(new Sapling("Mango Sapling", new ArrayList<>(List.of(Season.Summer)), TreeType.Mango)),
    OrangeSapling(new Sapling("Orange Sapling", new ArrayList<>(List.of(Season.Summer)), TreeType.Orange)),
    PeachSapling(new Sapling("Peach Sapling", new ArrayList<>(List.of(Season.Summer)), TreeType.Peach)),
    AppleSapling(new Sapling("Apple Sapling", new ArrayList<>(List.of(Season.Fall)), TreeType.Apple)),
    PomegranateSapling(new Sapling("Pomegranate Sapling", new ArrayList<>(List.of(Season.Fall)), TreeType.Pomegranate)),
    Acorns(new Sapling("Acorns", new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)), TreeType.Oak)),
    MapleSeeds(new Sapling("Maple Seeds", new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)), TreeType.Maple)),
    PineCones(new Sapling("Pine Cones", new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)), TreeType.Pine)),
    MahoganySeeds(new Sapling("Mahogany Seeds", new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)), TreeType.Mahogany)),
    MushroomTreeSeeds(new Sapling("Mushroom Tree Seeds", new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)), TreeType.Mushroom)),
    MysticTreeSeeds(new Sapling("Mystic Tree Seeds", new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)), TreeType.Mystic));


    private final Sapling sapling;

    SaplingType (Sapling sapling) {
        this.sapling = sapling;
    }

    public Sapling getSapling() {
        return sapling;
    }
}
