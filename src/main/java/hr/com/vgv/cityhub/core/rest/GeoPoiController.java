package hr.com.vgv.cityhub.core.rest;

import com.mongodb.client.MongoDatabase;
import hr.com.vgv.cityhub.core.CmMongo;
import hr.com.vgv.cityhub.core.pois.Entities;
import hr.com.vgv.cityhub.core.pois.GeoPoint;
import hr.com.vgv.cityhub.core.pois.Nearest;
import hr.com.vgv.cityhub.core.pois.PoisAsJson;
import hr.com.vgv.cityhub.core.pois.PoisOf;
import org.cactoos.scalar.UncheckedScalar;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Places of interest.
 *
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@RestController
public class GeoPoiController {

    private static final MongoDatabase MONGO = new UncheckedScalar<>(
        new CmMongo()
    ).value();

    @RequestMapping(value = "/api/v1/pois", method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public String nearestPois(@RequestParam(value = "type") String type,
        @RequestParam(value = "lat") double lat,
        @RequestParam(value = "lng") double lng) throws Exception {
        return
            new PoisAsJson(
                new PoisOf(
                    new Nearest(
                        new Entities(type, MONGO),
                        new GeoPoint(lat, lng)
                    )
                )
            ).value().toString();
    }
}
