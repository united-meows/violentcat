package pisi.unitedmeows.violentcat.shared.holders.shared.message.component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;

import java.util.ArrayList;
import java.util.List;

public class ActionRow extends Component {

    @Expose private List<Component> components = new ArrayList<>();

    public static ActionRow create() {
        ActionRow actionRow = new ActionRow();
        actionRow.type = 1;
        return actionRow;
    }

    public ActionRow add(Component _component) {
        if (components.size() >= 5) {
            /* add warning */
            return this;
        }
        components.add(_component);
        return this;
    }

    @OnlyLibCalls
    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("type", 1);

        JsonArray array = new JsonArray();
        for (Component component : components) {
            array.add(component.toJson());
        }

        json.add("components", array);
        return json;
    }
}
