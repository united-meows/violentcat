package pisi.unitedmeows.violentcat.shared.holders.shared;

import com.google.gson.annotations.Expose;

public class Role {

    @Expose private String id;
    @Expose private String name;
    @Expose private String permissions;
    @Expose private int position;
    @Expose private int color;
    @Expose private boolean hoist;
    @Expose private boolean managed;
    @Expose private boolean mentionable;


    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", permissions='" + permissions + '\'' +
                ", position=" + position +
                ", color=" + color +
                ", hoist=" + hoist +
                ", managed=" + managed +
                ", mentionable=" + mentionable +
                '}';
    }
}
