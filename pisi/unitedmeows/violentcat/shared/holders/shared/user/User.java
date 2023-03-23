package pisi.unitedmeows.violentcat.shared.holders.shared.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @Expose protected String id;
    @Expose protected String username;
    @Expose protected String discriminator;
    @Expose protected String avatar;
    @Expose protected boolean bot;
    @Expose protected boolean system;
    @Expose @SerializedName("mfa_enabled") protected boolean mfaEnabled;
    @Expose protected String banner;
    @Expose @SerializedName("accent_color") protected String accentColor;
    @Expose protected String locale;
    @Expose protected boolean verified;
    @Expose protected String email;
    @Expose protected int flags;
    @Expose @SerializedName("premium_type") protected int nitroType;
    @Expose @SerializedName("public_flags") protected int publicFlags;


    public String id() {
        return id;
    }

    public String username() {
        return username;
    }

    public String discriminator() {
        return discriminator;
    }

    public String avatar() {
        return avatar;
    }

    public boolean isBot() {
        return bot;
    }

    public boolean isSystem() {
        return system;
    }

    public boolean isMfaEnabled() {
        return mfaEnabled;
    }

    public String banner() {
        return banner;
    }

    public String accentColor() {
        return accentColor;
    }

    public String locale() {
        return locale;
    }

    public boolean isVerified() {
        return verified;
    }

    public String email() {
        return email;
    }

    public int flags() {
        return flags;
    }

    public int nitroType() {
        return nitroType;
    }

    public int publicFlags() {
        return publicFlags;
    }
}
