package Model.Enum.Tool;

import Model.Enum.Ability;

public enum Tools {
    Hoe (Ability.Farming),
    Pickaxe (Ability.Mining),
    Axe (Ability.Foraging),
    WateringCan (Ability.Farming),
    FishingPole (Ability.Fishing),
    Scythe (Ability.Farming),
    MilkPail (Ability.Farming),
    Shear (Ability.Farming);

    private final Ability ability;

    Tools(Ability ability) {
        this.ability = ability;
    }
}
