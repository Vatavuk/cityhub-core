package hr.com.vgv.cityhub.core.pois;

import com.mongodb.client.model.Filters;
import org.bson.Document;

/**
 * Entities within specific bounds.
 *
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public class Within implements Output {

    private final Entities entities;

    private final double[] top;

    private final double[] bottom;

    public Within(final Entities collection,
        final double[] upper, final double[] lower) {
        this.entities = collection;
        this.top = upper;
        this.bottom = lower;
    }

    @Override
    public Iterable<Document> value() throws Exception {
        return this.entities.value().find(
            Filters.geoWithinBox(
                "location",
                this.bottom[0],
                this.bottom[1],
                this.top[0],
                this.top[1]
            )
        );
    }
}
