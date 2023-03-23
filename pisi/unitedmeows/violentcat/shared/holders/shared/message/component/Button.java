package pisi.unitedmeows.violentcat.shared.holders.shared.message.component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import pisi.unitedmeows.violentcat.shared.stamp.OnlyLibCalls;
import pisi.unitedmeows.violentcat.utils.Jsons;

public class Button extends Component {

    protected Button() {}

    public static Button create(String _label, int _style, String _customId) {
        Button component = new Button();
        component.type = 2; /* todo: maybe enum? */
        component.label = _label;
        component.style = _style;
        component.customId = _customId;
        return component;
    }

    @OnlyLibCalls
    public JsonElement toJson() {
        return Jsons.BUILDER.toJsonTree(this).getAsJsonObject();
    }

}
