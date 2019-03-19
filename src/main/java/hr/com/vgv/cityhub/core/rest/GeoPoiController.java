package hr.com.vgv.cityhub.core.rest;

import hr.com.vgv.cityhub.core.db.CmMongo;
import hr.com.vgv.cityhub.core.db.MongoPlaces;
import hr.com.vgv.cityhub.core.places.Places;
import hr.com.vgv.cityhub.core.places.PoisAsJson;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Places of interest.
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@RestController
public class GeoPoiController {

    private static final Places PLACES =
        new MongoPlaces(new CmMongo().value());

    /*@RequestMapping(value = "/api/v1/pois/nearest/", method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public String nearest(@RequestParam(value = "type") final String type,
        @RequestParam(value = "lat") final double lat,
        @RequestParam(value = "lng") final double lng) throws Exception {
        return
            new PoisAsJson(
                new PoisOf(
                    new Nearest(
                        new EntitiesOf(type, MONGO),
                        new GeoPoint(lat, lng).value()
                    )
                )
            ).value().toString();
    }*/

    @RequestMapping(value = "/api/v1/pois/within/", method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public String within(@RequestParam(value = "type") final String type,
        @RequestParam(value = "upper") final double[] upper,
        @RequestParam(value = "lower") final double[] lower) throws Exception {
        return new PoisAsJson(PLACES.within(type, upper, lower))
            .json()
            .toString();
    }

    @GetMapping(value = "/api/v1/pois/{type}/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public String getById(
        @PathVariable final String type,
        @PathVariable final String id
    ) throws Exception {
        return PLACES.get(type, id).json().toString();
    }
}
