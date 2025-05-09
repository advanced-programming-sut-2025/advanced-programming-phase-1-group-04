package Model.Animals;

import Model.Enum.Season;

public enum FishType {
    Salmon(75 , Season.Fall),
    Sardine(40 , Season.Fall),
    Shad(60 , Season.Fall),
    BlueDiscus(120 , Season.Fall),
    MidnightCarp(150 , Season.Winter),
    Squid(80 , Season.Winter),
    Tuna(100 , Season.Winter),
    Perch(55 , Season.Winter),
    Flounder(100 , Season.Spring),
    Lionfish(100 , Season.Spring),
    Herring(30 , Season.Spring),
    GhostFish(45 , Season.Spring),
    Tilapia(75 , Season.Summer),
    Dorado(100 , Season.Summer),
    Sunfish(30 , Season.Summer),
    RainbowTrout(65 , Season.Summer),
    Legend(5000 , Season.Spring),
    GlacierFish(1000 , Season.Winter),
    Angler(900 , Season.Fall),
    CrimsonFish(1500 , Season.Summer);

    private final int basePrice;
    private final Season season;

    FishType(int basePrice, Season season) {
        this.basePrice = basePrice;
        this.season = season;
    }
}
