package hr.com.vgv.cityhub.core.places;

import javax.json.JsonObject;

/**
 * Place of interest.
 * @since 1.0
 */
public interface Poi extends JsonReadable {

    /**
     * Id
     * @return Id Id
     */
    String id();

    class Fake implements Poi
    {
        private final String id;
        private final JsonObject json;

        public Fake(String id, JsonObject json) {
            this.id = id;
            this.json = json;
        }

        @Override
        public String id() {
            return this.id;
        }

        @Override
        public JsonObject json() {
            return this.json;
        }
    }
}
