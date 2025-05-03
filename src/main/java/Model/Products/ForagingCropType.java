package Model.Products;

import Model.Enum.Season;

import java.util.ArrayList;
import java.util.List;

public enum ForagingCropType {
    CommonMushroom (new ForagingCrop.ForagingCropBuilder()
            .setName("Common Mushroom")
            .setSeasons(new ArrayList<>(List.of(Season.Spring, Season.Summer, Season.Fall, Season.Winter)))
            .setBaseSellPrice(40)
            .setEnergy(38)
            .build()),
    Daffodil(new ForagingCrop.ForagingCropBuilder()
            .setName("Daffodil")
            .setSeasons(new ArrayList<>(List.of(Season.Spring)))
            .setBaseSellPrice(30)
            .setEnergy(0)
            .build()),
    Dandelion(new ForagingCrop.ForagingCropBuilder()
            .setName("Dandelion")
            .setSeasons(new ArrayList<>(List.of(Season.Spring)))
            .setBaseSellPrice(40)
            .setEnergy(25)
            .build()),
    Leek(new ForagingCrop.ForagingCropBuilder()
            .setName("Leek")
            .setSeasons(new ArrayList<>(List.of(Season.Spring)))
            .setBaseSellPrice(60)
            .setEnergy(40)
            .build()),
    Morel(new ForagingCrop.ForagingCropBuilder()
            .setName("Morel")
            .setSeasons(new ArrayList<>(List.of(Season.Spring)))
            .setBaseSellPrice(150)
            .setEnergy(20)
            .build()),
    Salmonberry(new ForagingCrop.ForagingCropBuilder()
            .setName("Salmonberry")
            .setSeasons(new ArrayList<>(List.of(Season.Spring)))
            .setBaseSellPrice(5)
            .setEnergy(25)
            .build()),
    SpringOnion(new ForagingCrop.ForagingCropBuilder()
            .setName("Spring Onion")
            .setSeasons(new ArrayList<>(List.of(Season.Spring)))
            .setBaseSellPrice(8)
            .setEnergy(13)
            .build()),
    WildHorseradish(new ForagingCrop.ForagingCropBuilder()
            .setName("Wild Horseradish")
            .setSeasons(new ArrayList<>(List.of(Season.Spring)))
            .setBaseSellPrice(50)
            .setEnergy(13)
            .build()),
    FiddleheadFern(new ForagingCrop.ForagingCropBuilder()
            .setName("Fiddlehead Fern")
            .setSeasons(new ArrayList<>(List.of(Season.Summer)))
            .setBaseSellPrice(90)
            .setEnergy(25)
            .build()),
    Grape(new ForagingCrop.ForagingCropBuilder()
            .setName("Grape")
            .setSeasons(new ArrayList<>(List.of(Season.Summer)))
            .setBaseSellPrice(80)
            .setEnergy(38)
            .build()),
    RedMushroom(new ForagingCrop.ForagingCropBuilder()
            .setName("Red Mushroom")
            .setSeasons(new ArrayList<>(List.of(Season.Summer)))
            .setBaseSellPrice(75)
            .setEnergy(-50)
            .build()),
    SpiceBerry(new ForagingCrop.ForagingCropBuilder()
            .setName("Spice Berry")
            .setSeasons(new ArrayList<>(List.of(Season.Summer)))
            .setBaseSellPrice(80)
            .setEnergy(25)
            .build()),
    SweetPea(new ForagingCrop.ForagingCropBuilder()
            .setName("Sweet Pea")
            .setSeasons(new ArrayList<>(List.of(Season.Summer)))
            .setBaseSellPrice(50)
            .setEnergy(0)
            .build()),
    Blackberry(new ForagingCrop.ForagingCropBuilder()
            .setName("Blackberry")
            .setSeasons(new ArrayList<>(List.of(Season.Fall)))
            .setBaseSellPrice(25)
            .setEnergy(25)
            .build()),
    Chanterelle(new ForagingCrop.ForagingCropBuilder()
            .setName("Chanterelle")
            .setSeasons(new ArrayList<>(List.of(Season.Fall)))
            .setBaseSellPrice(160)
            .setEnergy(75)
            .build()),
    Hazelnut(new ForagingCrop.ForagingCropBuilder()
            .setName("Hazelnut")
            .setSeasons(new ArrayList<>(List.of(Season.Fall)))
            .setBaseSellPrice(40)
            .setEnergy(38)
            .build()),
    PurpleMushroom(new ForagingCrop.ForagingCropBuilder()
            .setName("Purple Mushroom")
            .setSeasons(new ArrayList<>(List.of(Season.Fall)))
            .setBaseSellPrice(90)
            .setEnergy(30)
            .build()),
    WildPlum(new ForagingCrop.ForagingCropBuilder()
            .setName("Wild Plum")
            .setSeasons(new ArrayList<>(List.of(Season.Fall)))
            .setBaseSellPrice(80)
            .setEnergy(25)
            .build()),
    Crocus(new ForagingCrop.ForagingCropBuilder()
            .setName("Crocus")
            .setSeasons(new ArrayList<>(List.of(Season.Winter)))
            .setBaseSellPrice(60)
            .setEnergy(0)
            .build()),
    CrystalFruit(new ForagingCrop.ForagingCropBuilder()
            .setName("Crystal Fruit")
            .setSeasons(new ArrayList<>(List.of(Season.Winter)))
            .setBaseSellPrice(150)
            .setEnergy(63)
            .build()),
    Holly(new ForagingCrop.ForagingCropBuilder()
            .setName("Holly")
            .setSeasons(new ArrayList<>(List.of(Season.Winter)))
            .setBaseSellPrice(80)
            .setEnergy(-37)
            .build()),
    SnowYam(new ForagingCrop.ForagingCropBuilder()
            .setName("Snow Yam")
            .setSeasons(new ArrayList<>(List.of(Season.Winter)))
            .setBaseSellPrice(100)
            .setEnergy(30)
            .build()),
    WinterRoot(new ForagingCrop.ForagingCropBuilder()
            .setName("Winter Root")
            .setSeasons(new ArrayList<>(List.of(Season.Winter)))
            .setBaseSellPrice(70)
            .setEnergy(25)
            .build());

    private final ForagingCrop crop;

    ForagingCropType (ForagingCrop crop) {
        this.crop = crop;
    }

    public ForagingCrop getCrop() {
        return crop;
    }
}