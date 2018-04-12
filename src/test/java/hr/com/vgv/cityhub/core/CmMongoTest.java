package hr.com.vgv.cityhub.core;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

/**
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class CmMongoTest {

    @Test
    public void testProdDb() throws Exception {
        final MongoDatabase db = new CmMongo().value();
        final MongoCollection<Document> bars = db.getCollection(
            "entities.bars"
        );
        //bars.createIndex(Indexes.geo2dsphere("location"));
        /*Point refPoint = new Point(new Position(45.8068722, 15.9680303));

        System.out.print(new CollectionOf<>(
            notaries.find(Filters.near(
                "location",
                refPoint,
                2000.0,
                1000.0))
        ).size());*/

        /*for (Document document : notaries.find()) {
            final ObjectId id = document.get("_id", ObjectId.class);
            notaries.updateOne(new BasicDBObject("_id", id),
                new BasicDBObject(
                    "$set",
                    new BasicDBObject("location",
                        new double[]{
                            document.get("latitude", Double.class),
                            document.get("longitude", Double.class)
                        }
                    )
                )
            );
        }*/
    }
}
