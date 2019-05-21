package hr.com.vgv.cityhub.core.places;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

/**
 * Places of interest in json representation.
 * @version $Id$
 * @since 1.0
 */
public class PoisAsJson implements JsonReadable {

    private final Iterable<Poi> places;
    private boolean isWorking;

    public PoisAsJson(final Iterable<Poi> places) {
        this.places = places;
    }

    @Override
    public JsonObject json() {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (final Poi poi : this.places) {
            final JsonObject json = poi.json();
            builder = builder.add(
                Json.createObjectBuilder()
                    .add("id", poi.id())
                    .add("title", json.getString("name"))
                    .add("loc", json.getJsonArray("location"))
            );
        }
        return Json.createObjectBuilder()
            .add("places", builder.build()).build();
    }
}
