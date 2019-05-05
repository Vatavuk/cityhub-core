package hr.com.vgv.cityhub.core.places;

import org.cactoos.iterable.IterableOf;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

public class PoisAsJsonTest {

    @Test
    public void json() {
        JsonArray location = Json.createArrayBuilder()
                .add("12.45").add("16.55")
                .build();
        JsonObject json = Json.createObjectBuilder()
                .add("name", "myPlace")
                .add("location", location)
                .build();

        PoisAsJson poi = new PoisAsJson(new IterableOf<>(
                new Poi.Fake("id", json))
        );
        MatcherAssert.assertThat(
                Json.createObjectBuilder().add("places", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("id", "id")
                                .add("title", "myPlace")
                                .add("loc", location).build()))
                        .build(),
                new IsEqual<>(poi.json())
        );
    }
}
