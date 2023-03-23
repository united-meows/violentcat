package pisi.unitedmeows.violentcat.shared.holders.shared.message.embed;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Embed {

    private static final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    protected Embed() {}

    public static Embed rich() {
        final Embed embed = new Embed();
        embed.embedType = EmbedType.RICH;
        embed.typeName = embed.embedType.code();
        return embed;
    }

    @Expose
    private String title;

    @Expose
    @SerializedName("type")
    private String typeName;

    private volatile EmbedType embedType;

    @Expose
    private String description;

    @Expose
    private int color;

    @Expose
    private Footer footer;

    @Expose
    private EmbedMedia image;

    @Expose
    private EmbedMedia thumbnail;

    @Expose
    private EmbedMedia video;

    @Expose
    private EmbedProvider provider;

    @Expose
    private EmbedAuthor author;


    @Expose
    private List<EmbedField> fields;
    // todo add timestamp ISO8601 timestamp

    public Embed footer(Consumer<Footer> _footer) {
        if (footer == null) footer = new Footer();
        _footer.accept(footer);
        return this;
    }

    public Embed image(Consumer<EmbedMedia> _image) {
        if (image == null) image = new EmbedMedia();
        _image.accept(image);
        return this;
    }

    public Embed thumbnail(Consumer<EmbedMedia> _thumbnail) {
        if (thumbnail == null) thumbnail = new EmbedMedia();
        _thumbnail.accept(thumbnail);
        return this;
    }

    public Embed video(Consumer<EmbedMedia> _video) {
        if (video == null) video = new EmbedMedia();
        _video.accept(video);
        return this;
    }

    public Embed provider(Consumer<EmbedProvider> _provider) {
        if (provider == null) provider = new EmbedProvider();
        _provider.accept(provider);
        return this;
    }
    public Embed author(Consumer<EmbedAuthor> _author) {
        if (author == null) author = new EmbedAuthor();
        _author.accept(author);
        return this;
    }

    public Embed title(String _title) {
        title = _title;
        return this;
    }

    public Embed description(String _description) {
        description = _description;
        return this;
    }

    public Embed color(int _color) {
        color = _color;
        return this;
    }

    public Embed addField(Consumer<EmbedField> _field) {
        if (fields == null)
            fields = new ArrayList<>();
        EmbedField embedField = new EmbedField();
        _field.accept(embedField);
        fields.add(embedField);
        return this;
    }

    public List<EmbedField> fields() {
        return fields;
    }

    @Override
    public String toString() {
        return gson.toJson(this);
    }

    public JsonObject toObject() {
        return gson.toJsonTree(this).getAsJsonObject();
    }
}
