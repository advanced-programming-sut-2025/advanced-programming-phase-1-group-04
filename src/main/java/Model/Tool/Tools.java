package Model.Tool;

import Model.Enum.Skill;

public enum Tools {
    Hoe (Skill.Farming),
    Pickaxe (Skill.Mining),
    Axe (Skill.Foraging),
    WateringCan (Skill.Farming),
    FishingPole (Skill.Fishing),
    Scythe (Skill.Farming),
    MilkPail (Skill.Farming),
    Shear (Skill.Farming);

    private final Skill skill;

    Tools(Skill skill) {
        this.skill = skill;
    }
}
