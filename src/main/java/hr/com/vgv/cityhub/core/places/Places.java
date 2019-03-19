package hr.com.vgv.cityhub.core.places;

/**
 * Places.
 * @since 1.0
 */
public interface Places {

    /**
     * Get place of interest specified by id.
     * @param id Id
     * @return Poi Poi
     */
    Poi get(String type, String id);

    /**
     * Nearest places for given geolocation.
     * @param type Type of places
     * @param lat Latitude
     * @param lng Longitude
     * @return Places Places
     */
    Iterable<Poi> nearest(String type, double lat, double lng);

    /**
     * Place within bounds.
     * @param type Type of places
     * @param upper Upper bounds
     * @param lower Lower bounds
     * @return Places Places
     */
    Iterable<Poi> within(String type, double[] upper, double[] lower);
}
