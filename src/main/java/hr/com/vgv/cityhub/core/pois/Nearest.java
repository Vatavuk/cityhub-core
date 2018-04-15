package hr.com.vgv.cityhub.core.pois;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.geojson.Point;
import org.bson.Document;

/**
 * Entities in a given range from the specific location.
 *
 * @author Vedran Vatavuk (123vgv@gmail.com)
 *
 * @version $Id$
 * @since 1.0
 */
public final class Nearest implements Output {

    private final Entities entities;

    private final Point point;

    private final double min;

    private final double max;

    public Nearest(final Entities collection,
        final Point location) {
        this(collection, location, 0.0, 700.0);
    }

    public Nearest(final Entities collection,
        final Point location, final double minimum,
        final double maximum) {
        this.entities = collection;
        this.point = location;
        this.min = minimum;
        this.max = maximum;
    }


    @Override
    public Iterable<Document> value() throws Exception {
        return this.entities.value().find(
            Filters.near("location",
                this.point,
                this.max,
                this.min
            )
        );
    }
}
