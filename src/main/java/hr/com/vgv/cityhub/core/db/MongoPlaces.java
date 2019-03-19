package hr.com.vgv.cityhub.core.db;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import hr.com.vgv.cityhub.core.places.BsonPoi;
import hr.com.vgv.cityhub.core.places.BsonPois;
import hr.com.vgv.cityhub.core.places.Places;
import hr.com.vgv.cityhub.core.places.Poi;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Places backed by mongo database.
 * @since 1.0
 */
public class MongoPlaces implements Places {

    private final MongoDatabase db;

    public MongoPlaces(final MongoDatabase db) {
        this.db = db;
    }

    @Override
    public Poi get(final String type, final String id) {
        return new BsonPoi(
            this.fetchCollection(type)
                .find(eq("_id", new ObjectId(id)))
                .first()
        );
    }

    @Override
    public Iterable<Poi> nearest(
        final String type, final double lat, final double lng
    ) {
        throw new UnsupportedOperationException(
            "Fetching of nearest places is not yet supported"
        );
    }

    @Override
    public Iterable<Poi> within(
        final String type, final double[] upper, final double[] lower
    ) {
        return new BsonPois(
            this.fetchCollection(type).find(
                Filters.geoWithinBox(
                    "location",
                    lower[0],
                    lower[1],
                    upper[0],
                    upper[1]
                )
            )
        );
    }

    private MongoCollection<Document> fetchCollection(final String type) {
        return this.db.getCollection(
            String.format("entities.%s", type)
        );
    }
}
