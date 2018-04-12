package hr.com.vgv.cityhub.core.pois;

import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import org.cactoos.Scalar;

/**
 * Place of interest.
 *
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class GeoPoi implements Scalar<JsonObject> {

    private final String identifier;

    private final List<Double> location;

    public GeoPoi(final String id, final List<Double> loc) {
        this.identifier = id;
        this.location = loc;
    }

    @Override
    public JsonObject value() throws Exception {
        return Json.createObjectBuilder()
            .add("id", this.identifier)
            .add("loc",
                Json.createArrayBuilder()
                    .add(this.location.get(0))
                    .add(this.location.get(1))
            ).build();
    }
}
