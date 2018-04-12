package hr.com.vgv.cityhub.core.pois;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.cactoos.Scalar;

/**
 * Database collection.
 *
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class Entities implements Scalar<MongoCollection<Document>> {

    private final String collection;

    private final MongoDatabase db;

    public Entities(final String type, final MongoDatabase database) {
        this.collection = type;
        this.db = database;
    }


    @Override
    public MongoCollection<Document> value() throws Exception {
        return this.db.getCollection(
            String.format("entities.%s", this.collection)
        );
    }
}
