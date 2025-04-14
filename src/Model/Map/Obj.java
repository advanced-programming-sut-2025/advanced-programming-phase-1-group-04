package Model.Map;

import Model.Enum.TypeObj;

public abstract class Obj {
    protected TypeObj type;

    public Obj(String name) {
        this.type = TypeObj.valueOf(name);
    }
}