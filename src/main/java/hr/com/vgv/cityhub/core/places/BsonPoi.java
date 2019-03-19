package hr.com.vgv.cityhub.core.places;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Place of interest from bson.
 * @version $Id$
 * @since 1.0
 */
public class BsonPoi implements Poi {

    private final Document document;

    public BsonPoi(final Document document) {
        this.document = document;
    }

    @Override
    public String id() {
        return this.document.get("_id", ObjectId.class).toString();
    }

    @Override
    public JsonObject json() {
        try(JsonReader reader = Json.createReader(
            new StringReader(this.document.toJson()))
        ) {
            return reader.readObject();
        }
    }
}
