package Model.Tool;

public enum FishingPoleType {
    Starter (25, 8, 0),
    Bamboo (500, 8, 0),
    Fiberglass (1800, 6, 2),
    Iridium (7500, 4, 4);

    private final int price;
    private final int energyConsumption;
    private final int fishingSkillLevel;

    FishingPoleType (int price, int energyConsumption, int fishingSkillLevel) {
        this.price = price;
        this.energyConsumption = energyConsumption;
        this.fishingSkillLevel = fishingSkillLevel;
    }

    public int getPrice() {
        return price;
    }

    public int getEnergyConsumption() {
        return energyConsumption;
    }

    public int getFishingSkillLevel() {
        return fishingSkillLevel;
    }
}
