package hr.com.vgv.cityhub.core.places;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.cactoos.iterable.IterableOf;
import org.cactoos.list.ListOf;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class BsonPoisTest {

    @Test
    public void bsonPois()
    {
        String id = "666f6f2d6261722d71757578";
        Document doc = new Document("_id", new ObjectId(id));
        BsonPois pois = new BsonPois(new IterableOf<>(doc));
        MatcherAssert.assertThat(new ListOf<>(pois.iterator()), hasSize(1));
    }
}