package hr.com.vgv.cityhub.core.db;

import com.github.fakemongo.Fongo;
import com.mongodb.FongoMongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Ignore;
import org.junit.Test;

import javax.json.Json;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class MongoPlacesTest {

    @Test
    @Ignore
    public void get() throws Exception {
        MongoDatabase db = new Fongo("entities").getDatabase("db");
        final String id = "666f6f2d6261722d71757578";
        db.createCollection("entities_places");
        db.getCollection("entities_places").insertOne(new Document("_id", new ObjectId(id)));
        MongoPlaces places = new MongoPlaces(db);
        MatcherAssert.assertThat(places.get("places", id).id(),
                new IsEqual<>(id)
        );
    }

    @Test
    public void nearest() throws Exception {
    }

    @Test
    @Ignore
    public void withinTrue() throws Exception {
        MongoDatabase db = new Fongo("entities").getDatabase("db");
        final String id = "666f6f2d6261722d71757578";
        db.createCollection("entities_places");
        MongoCollection<Document> entities_places = db.getCollection("entities_places");
        List<Double> location = new ArrayList<>();
        location.add(45.8113626);
        location.add(15.976522299999942);
        entities_places.insertOne(new Document("_id", new ObjectId(id))
                .append("location", location));

        MongoPlaces places = new MongoPlaces(db);
        MatcherAssert.assertThat(places.within("places", new double[]{44.8113626, 14.976522299999942},
                new double[]{46.8113626, 16.976522299999942}).iterator().hasNext(),
                new IsEqual<>(true));
    }

    @Test
    @Ignore
    public void withinFalse() throws Exception {
        MongoDatabase db = new Fongo("entities").getDatabase("db");
        final String id = "666f6f2d6261722d71757578";
        db.createCollection("entities_places");
        MongoCollection<Document> entities_places = db.getCollection("entities_places");
        List<Double> location = new ArrayList<>();
        location.add(45.8113626);
        location.add(15.976522299999942);
        entities_places.insertOne(new Document("_id", new ObjectId(id))
                .append("location", location));

        MongoPlaces places = new MongoPlaces(db);
        MatcherAssert.assertThat(places.within("places", new double[]{48.8113626, 16.976522299999942},
                new double[]{50.8113626, 16.976522299999942}).iterator().hasNext(),
                new IsEqual<>(false));
    }
}