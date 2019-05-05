package hr.com.vgv.cityhub.core.places;

import org.bson.Document;
import org.cactoos.collection.Mapped;
import org.cactoos.iterable.IterableEnvelope;

/**
 * Places of interest from Bson.
 * @since 1.0
 */
public class BsonPois extends IterableEnvelope<Poi> {

    public BsonPois(final Iterable<Document> places) {
        super(() -> new Mapped<>(BsonPoi::new, places)
        );
    }
}
