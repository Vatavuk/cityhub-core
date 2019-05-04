package hr.com.vgv.cityhub.core.db;

import com.github.fakemongo.Fongo;
import com.mongodb.FongoMongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Ignore;
import org.junit.Test;

public class MongoPlacesTest {

    @Test
    @Ignore
    public void get() throws Exception {
        Fongo fongo = new Fongo("inMemoryDb");
        FongoMongoDatabase db = new FongoMongoDatabase("inMemoryDb", fongo);
        db.createCollection("places");
        MongoCollection collection = db.getCollection("places");
        String id = "666f6f2d6261722d71757578";
        collection.insertOne(new Document("_id", new ObjectId(id)));
        MongoPlaces places = new MongoPlaces(db);
        MatcherAssert.assertThat(places.get("places", id).id(),
                new IsEqual<>(id)
        );
    }

    @Test
    public void nearest() throws Exception {
    }

    @Test
    public void within() throws Exception {
    }
}