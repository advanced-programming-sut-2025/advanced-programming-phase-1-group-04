package Model.Products;

import Model.Enum.Season;

import java.util.ArrayList;
import java.util.List;

public enum TreeType {
    Apricot (new Tree.TreeBuilder()
            .setName("Apricot Tree")
            .setSource(SaplingType.ApricotSapling)
            .setFruit(FruitType.Apricot)
            .setFruitHarvestCycle(1)
            .setSeasons(new ArrayList<>(List.of(Season.Spring)))
            .build()),

    Cherry (new Tree.TreeBuilder()
            .setName("Cherry Tree")
            .setSource(SaplingType.CherrySapling)
            .setFruit(FruitType.Cherry)
            .setFruitHarvestCycle(1)
            .setSeasons(new ArrayList<>(List.of(Season.Spring)))
            .build()),

    Banana (new Tree.TreeBuilder()
            .setName("Banana Tree")
            .setSource(SaplingType.BananaSapling)
            .setFruit(FruitType.Banana)
            .setFruitHarvestCycle(1)
            .setSeasons(new ArrayList<>(List.of(Season.Summer)))
            .build()),

    Mango (new Tree.TreeBuilder()
            .setName("Mango Tree")
            .setSource(SaplingType.MangoSapling)
            .setFruit(FruitType.Mango)
            .setFruitHarvestCycle(1)
            .setSeasons(new ArrayList<>(List.of(Season.Summer)))
            .build()),

    Orange (new Tree.TreeBuilder()
            .setName("Orange Tree")
            .setSource(SaplingType.OrangeSapling)
            .setFruit(FruitType.Orange)
            .setFruitHarvestCycle(1)
            .setSeasons(new ArrayList<>(List.of(Season.Summer)))
            .build()),

    Peach (new Tree.TreeBuilder()
            .setName("Peach Tree")
            .setSource(SaplingType.PeachSapling)
            .setFruit(FruitType.Peach)
            .setFruitHarvestCycle(1)
            .setSeasons(new ArrayList<>(List.of(Season.Summer)))
            .build()),

    Apple (new Tree.TreeBuilder()
            .setName("Apple Tree")
            .setSource(SaplingType.AppleSapling)
            .setFruit(FruitType.Apple)
            .setFruitHarvestCycle(1)
            .setSeasons(new ArrayList<>(List.of(Season.Fall)))
            .build()),

    Pomegranate (new Tree.TreeBuilder()
            .setName("Pomegranate Tree")
            .setSource(SaplingType.PomegranateSapling)
            .setFruit(FruitType.Pomegranate)
            .setFruitHarvestCycle(1)
            .setSeasons(new ArrayList<>(List.of(Season.Fall)))
            .build()),

    Oak (new Tree.TreeBuilder()
            .setName("Oak Tree")
            .setSource(SaplingType.Acorns)
            .setFruit(FruitType.OakResin)
            .setFruitHarvestCycle(7)
            .setSeasons(new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)))
            .build()),

    Maple (new Tree.TreeBuilder()
            .setName("Maple Tree")
            .setSource(SaplingType.MapleSeeds)
            .setFruit(FruitType.MapleSyrup)
            .setFruitHarvestCycle(9)
            .setSeasons(new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)))
            .build()),

    Pine (new Tree.TreeBuilder()
            .setName("Pine Tree")
            .setSource(SaplingType.PineCones)
            .setFruit(FruitType.PineTar)
            .setFruitHarvestCycle(5)
            .setSeasons(new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)))
            .build()),

    Mahogany (new Tree.TreeBuilder()
            .setName("Mahogany Tree")
            .setSource(SaplingType.MahoganySeeds)
            .setFruit(FruitType.Sap)
            .setFruitHarvestCycle(1)
            .setSeasons(new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)))
            .build()),

    Mushroom (new Tree.TreeBuilder()
            .setName("Mushroom Tree")
            .setSource(SaplingType.MushroomTreeSeeds)
            .setFruit(FruitType.CommonMushroom)
            .setFruitHarvestCycle(1)
            .setSeasons(new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)))
            .build()),

    Mystic (new Tree.TreeBuilder()
            .setName("Mystic Tree")
            .setSource(SaplingType.MysticTreeSeeds)
            .setFruit(FruitType.MysticSyrup)
            .setFruitHarvestCycle(7)
            .setSeasons(new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)))
            .build());

    private final Tree tree;

    TreeType (Tree tree) {
        this.tree = tree;
    }
}