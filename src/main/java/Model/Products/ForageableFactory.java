package Model.Products;

import Model.Enum.Season;

import java.util.ArrayList;

public class ForageableFactory {
    private static final ArrayList<Forageable> allForageables = new ArrayList<>();
    private static final ArrayList<Forageable> plantForageables = new ArrayList<>();
    private static final ArrayList<Forageable> springPlantForageables = new ArrayList<>();
    private static final ArrayList<Forageable> summerPlantForageables = new ArrayList<>();
    private static final ArrayList<Forageable> fallPlantForageables = new ArrayList<>();
    private static final ArrayList<Forageable> winterPlantForageables = new ArrayList<>();
    private static final ArrayList<Forageable> mineralForageables = new ArrayList<>();

//    public static ArrayList<Forageable> getAllForageables() {
//        for (ForagingCropType type : ForagingCropType.values()) {
//            ForagingCrop crop = type.getCrop();
//            allForageables.add(crop);
//        }
//        for (SeedType type : SeedType.values()) {
//            Seed seed = type.getSeed();
//            allForageables.add(seed);
//        }
//        for (SaplingType type : SaplingType.values()) {
//            Sapling sapling = type.getSapling();
//            if (sapling.isForageable()) {
//                allForageables.add(sapling);
//            }
//        }
//        for (ForagingMineralType type : ForagingMineralType.values()) {
//            ForagingMineral mineral = type.getMineral();
//            allForageables.add(mineral);
//        }
//        return allForageables;
//    }
//
//    public static ArrayList<Forageable> getPlantForageables() {
//        for (ForagingCropType type : ForagingCropType.values()) {
//            ForagingCrop crop = type.getCrop();
//            plantForageables.add(crop);
//        }
//        for (SeedType type : SeedType.values()) {
//            Seed seed = type.getSeed();
//            plantForageables.add(seed);
//        }
//        for (SaplingType type : SaplingType.values()) {
//            Sapling sapling = type.getSapling();
//            if (sapling.isForageable()) {
//                plantForageables.add(sapling);
//            }
//        }
//        return plantForageables;
//    }
//
//    public static ArrayList<Forageable> getSpringPlantForageables() {
//        for (ForagingCropType type : ForagingCropType.values()) {
//            ForagingCrop crop = type.getCrop();
//            if (crop.getSeasons().contains(Season.Spring)) {
//                springPlantForageables.add(crop);
//            }
//
//        }
//        for (SeedType type : SeedType.values()) {
//            Seed seed = type.getSeed();
//            if (seed.getSeasons().contains(Season.Spring)) {
//                springPlantForageables.add(seed);
//            }
//
//        }
//        for (SaplingType type : SaplingType.values()) {
//            Sapling sapling = type.getSapling();
//            if (sapling.isForageable() && sapling.getSeasons().contains(Season.Spring)) {
//                springPlantForageables.add(sapling);
//            }
//        }
//        return springPlantForageables;
//    }
//
//    public static ArrayList<Forageable> getSummerPlantForageables() {
//        for (ForagingCropType type : ForagingCropType.values()) {
//            ForagingCrop crop = type.getCrop();
//            if (crop.getSeasons().contains(Season.Summer)) {
//                summerPlantForageables.add(crop);
//            }
//
//        }
//        for (SeedType type : SeedType.values()) {
//            Seed seed = type.getSeed();
//            if (seed.getSeasons().contains(Season.Summer)) {
//                summerPlantForageables.add(seed);
//            }
//
//        }
//        for (SaplingType type : SaplingType.values()) {
//            Sapling sapling = type.getSapling();
//            if (sapling.isForageable() && sapling.getSeasons().contains(Season.Summer)) {
//                summerPlantForageables.add(sapling);
//            }
//        }
//        return summerPlantForageables;
//    }
//
//    public static ArrayList<Forageable> getFallPlantForageables() {
//        for (ForagingCropType type : ForagingCropType.values()) {
//            ForagingCrop crop = type.getCrop();
//            if (crop.getSeasons().contains(Season.Fall)) {
//                fallPlantForageables.add(crop);
//            }
//        }
//        for (SeedType type : SeedType.values()) {
//            Seed seed = type.getSeed();
//            if (seed.getSeasons().contains(Season.Fall)) {
//                fallPlantForageables.add(seed);
//            }
//        }
//        for (SaplingType type : SaplingType.values()) {
//            Sapling sapling = type.getSapling();
//            if (sapling.isForageable() && sapling.getSeasons().contains(Season.Fall)) {
//                fallPlantForageables.add(sapling);
//            }
//        }
//        return fallPlantForageables;
//    }
//
//    public static ArrayList<Forageable> getWinterPlantForageables() {
//        for (ForagingCropType type : ForagingCropType.values()) {
//            ForagingCrop crop = type.getCrop();
//            if (crop.getSeasons().contains(Season.Winter)) {
//                winterPlantForageables.add(crop);
//            }
//        }
//        for (SeedType type : SeedType.values()) {
//            Seed seed = type.getSeed();
//            if (seed.getSeasons().contains(Season.Winter)) {
//                winterPlantForageables.add(seed);
//            }
//        }
//        for (SaplingType type : SaplingType.values()) {
//            Sapling sapling = type.getSapling();
//            if (sapling.isForageable() && sapling.getSeasons().contains(Season.Winter)) {
//                winterPlantForageables.add(sapling);
//            }
//        }
//        return winterPlantForageables;
//    }
//
//    public static ArrayList<Forageable> getMineralForageables() {
//        for (ForagingMineralType type : ForagingMineralType.values()) {
//            ForagingMineral mineral = type.getMineral();
//            mineralForageables.add(mineral);
//        }
//        return mineralForageables;
//    }
}