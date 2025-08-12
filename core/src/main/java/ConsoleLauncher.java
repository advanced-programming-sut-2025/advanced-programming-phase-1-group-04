
import io.Ap.StardewValley.Model.Animals.AnimalProductType;
import io.Ap.StardewValley.Model.Animals.FishType;
import io.Ap.StardewValley.Model.Cooking.FoodType;
import io.Ap.StardewValley.Model.Crafting.CraftType;
import io.Ap.StardewValley.Model.Plants.ForagingMineralType;
import io.Ap.StardewValley.Model.Plants.SaplingType;
import io.Ap.StardewValley.Model.Plants.SeedType;
import io.Ap.StardewValley.Model.Player.GiftType;
import io.Ap.StardewValley.Model.Tool.ToolType;
import io.Ap.StardewValley.View.AppView;

import java.io.IOException;

public class ConsoleLauncher {
    public static void main(String[] args) throws IOException {
        //System.out.println(ToolType.values().length);
        (new AppView()).run();
    }
}
