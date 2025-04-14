package Model.User;

import Model.Enum.TypeTrashCan;
import Model.Map.Obj;
import org.w3c.dom.TypeInfo;

import java.util.Map;

public class Inventory {
    private Inventory type;
    private Map<Obj, Integer> items;
    private TypeTrashCan trashCan;
}
