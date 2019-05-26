package hr.com.vgv.cityhub.core.places;

import static java.util.Locale.forLanguageTag;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

/**
 * Places of interest in json representation.
 *
 * @version $Id$
 * @since 1.0
 */
public class PoisAsJson implements JsonReadable {

    private final Iterable<Poi> places;

    public PoisAsJson(final Iterable<Poi> places) {
        this.places = places;
    }

    @Override
    public JsonObject json() {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (final Poi poi : this.places) {
            final JsonObject json = poi.json();
            builder = builder.add(
                    Json.createObjectBuilder()
                            .add("id", poi.id())
                            .add("title", json.getString("name"))
                            .add("loc", json.getJsonArray("location"))
                            .add("isOpen", this.isPoiOpen(poi))
            );
        }
        return Json.createObjectBuilder()
                .add("places", builder.build()).build();
    }

    //TODO: add unit tests
    private boolean isPoiOpen(Poi poi) {
        List<String> weeklyWorkDays = Arrays.asList(poi.json().getString("workingHours").split(";"));
        String currentDay = DayOfWeek.from(LocalDate.now()).getDisplayName(TextStyle.SHORT, new Locale("hr", "HR"));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H[.mm]");
        LocalTime currentTime = LocalTime.parse(LocalTime.now().format(dtf), dtf);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EE", forLanguageTag("hr"));
        DayOfWeek currentDayOfWeek = DayOfWeek.from(formatter.parse(currentDay));
        AtomicBoolean isOpen = new AtomicBoolean(false);

        weeklyWorkDays.forEach(workingDayGroup -> {
            String[] workingDays = workingDayGroup.split(":")[0].split("-");
            DayOfWeek firstWorkDay = DayOfWeek.from(formatter.parse(workingDays[0]));
            DayOfWeek lastWorkDay = workingDays.length > 1
                    ? DayOfWeek.from(formatter.parse(workingDays[1]))
                    : DayOfWeek.from(formatter.parse(workingDays[0]));

            if ((currentDayOfWeek.getValue() >= firstWorkDay.getValue()) && (currentDayOfWeek.getValue() <= lastWorkDay.getValue())) {
                String[] workingHours = workingDayGroup.split(":")[1].split("-");
                LocalTime startTime = LocalTime.parse(workingHours[0], dtf);
                LocalTime endTime = LocalTime.parse(workingHours[1], dtf);
                long openHoursDuration = ChronoUnit.SECONDS.between(startTime, endTime);

                LocalTime endTimeFromDuration = startTime.plus(openHoursDuration, ChronoUnit.SECONDS);
                if (currentTime.isAfter(startTime) && currentTime.isBefore(endTimeFromDuration)) {
                    isOpen.set(true);
                }
            }
        });

        return isOpen.get();
    }
}
