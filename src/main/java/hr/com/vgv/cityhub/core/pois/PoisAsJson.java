package hr.com.vgv.cityhub.core.pois;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import org.cactoos.Scalar;

/**
 * Places of interest in json representation.
 *
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class PoisAsJson implements Scalar<JsonObject> {

    private final Pois pois;

    public PoisAsJson(final Pois places) {
        this.pois = places;
    }

    @Override
    public JsonObject value() throws Exception {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (final GeoPoi poi: this.pois.value()) {
            builder = builder.add(poi.value());
        }
        return Json.createObjectBuilder()
            .add("places", builder.build()).build();
    }
}
