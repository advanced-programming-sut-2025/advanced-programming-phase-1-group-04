package Controller.SirkBozorg;

import Model.App;
import Model.Result;

public class MapController {
    public static Result printAllMap() {
        return new Result(true, App.getCurrentGame().getMap().toString());
    }

}
