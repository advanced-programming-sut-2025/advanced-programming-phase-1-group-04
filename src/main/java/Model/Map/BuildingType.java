package Model.Map;


public enum BuildingType {
    GreenHouse(5, 7),
    House(4, 5),

    Barn(4, 7),
    BigBarn(4 , 7),
    DeluxeBarn(4 , 7),
    Coop(6 , 3),
    BigCoop(6 , 3),
    DeluxeCoop(6 , 3),
    Well( 3 , 3),
    ShippingBin(1, 1),

    Blacksmith(4 , 6),
    JojaMart(3 , 7),
    PierresGeneralStore(5 , 8),
    CarpentersShop(4 , 6),
    FishShop(4 , 5),
    MarniesRanch(3 , 5),
    TheStarDropSaloon(4, 7);

    private int w;
    private int l;
    // اینا طول و عرضی که از بیرون معلومه

    BuildingType(int w, int l) {
        this.w = w;
        this.l = l;
    }
}
