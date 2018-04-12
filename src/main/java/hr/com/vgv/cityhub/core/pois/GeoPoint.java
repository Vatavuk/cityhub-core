package hr.com.vgv.cityhub.core.pois;

import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import org.cactoos.Scalar;

/**
 * Point on the map.
 *
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class GeoPoint implements Scalar<Point> {

    private final double latitude;

    private final double longitude;

    public GeoPoint(final double lat, final double lng) {
        this.latitude = lat;
        this.longitude = lng;
    }

    @Override
    public Point value() throws Exception {
        return new Point(new Position(this.latitude, this.longitude));
    }
}
