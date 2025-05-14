package Controller.SirkBozorg;

import Model.App;
import Model.Result;

public class ShopController {
    public static Result cheatAddCount(String stringCount) {
        int count = Integer.parseInt(stringCount);
        if (count < 0) {
            return new Result(false, "Count should be positive!");
        }
        App.getCurrentGame().getCurrentPlayer().addCount(count);
        return new Result(true, "Now your account has been updated to: "  + App.getCurrentGame().getCurrentPlayer().getCount());
    }
}
