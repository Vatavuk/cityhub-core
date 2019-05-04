package hr.com.vgv.cityhub.core.places;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import javax.json.Json;

public class BsonPoiTest {

    @Test
    public void id() {
        String id = "666f6f2d6261722d71757578";
        Document doc = new Document("_id", new ObjectId(id));
        MatcherAssert.assertThat(new BsonPoi(doc).id(),
                new IsEqual<>(id));
    }

    @Test
    public void json() {
        String id = "666f6f2d6261722d71757578";
        Document doc = new Document("_id", new ObjectId(id));
        MatcherAssert.assertThat(
                new BsonPoi(doc).json(),
                new IsEqual<>(Json.createObjectBuilder().add("_id",
                                Json.createObjectBuilder().add("$oid", id).build())
                        .build()
                )
        );
    }


}
