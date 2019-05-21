package hr.com.vgv.cityhub.core.rest;

import static java.util.Locale.forLanguageTag;

import hr.com.vgv.cityhub.core.db.CmMongo;
import hr.com.vgv.cityhub.core.db.MongoPlaces;
import hr.com.vgv.cityhub.core.places.Places;
import hr.com.vgv.cityhub.core.places.Poi;
import hr.com.vgv.cityhub.core.places.PoisAsJson;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        Poi poi = PLACES.get(type, id);
        List<String> weeklyWorkDays = Arrays.asList(poi.json().getString("workingHours").split(";"));
        String currentDay = DayOfWeek.from(LocalDate.now()).getDisplayName(TextStyle.SHORT, new Locale("hr", "HR"));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H[.mm]");
        LocalTime currentTime = LocalTime.parse(LocalTime.now().format(dtf), dtf);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EE", forLanguageTag("hr"));
        DayOfWeek currentDayOfWeek = DayOfWeek.from(formatter.parse(currentDay));
        AtomicBoolean isOpen = new AtomicBoolean(false);

        weeklyWorkDays.forEach(workingDayGroup -> {
            System.err.println(workingDayGroup);
            String[] workingDays = workingDayGroup.split(":")[0].split("-");
            DayOfWeek firstWorkDay = DayOfWeek.from(formatter.parse(workingDays[0]));
            DayOfWeek lastWorkDay = workingDays.length > 1
                    ? DayOfWeek.from(formatter.parse(workingDays[1]))
                    : DayOfWeek.from(formatter.parse(workingDays[0]));

            if ((currentDayOfWeek.getValue() >= firstWorkDay.getValue()) && (currentDayOfWeek.getValue() <= lastWorkDay.getValue())) {
                String[] workingHours = workingDayGroup.split(":")[1].split("-");
                LocalTime startTime = LocalTime.parse(workingHours[0], dtf);
                LocalTime endTime = LocalTime.parse(workingHours[1], dtf);

                if (currentTime.isAfter(startTime) && !currentTime.isAfter(endTime)) {
                    isOpen.set(true);
                }
            }
        });

        System.err.println("isOpen: " + isOpen);
        return poi.toString();
    }
}
