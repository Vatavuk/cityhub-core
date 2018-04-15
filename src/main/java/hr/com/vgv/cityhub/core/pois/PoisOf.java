package hr.com.vgv.cityhub.core.pois;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Places of interest from output.
 *
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class PoisOf implements Pois {

    private final Output output;

    public PoisOf(final Output out) {
        this.output = out;
    }

    @Override
    public Iterable<GeoPoi> value() throws Exception {
        final Collection<GeoPoi> pois = new ArrayList<>(0);
        for (final Document document: this.output.value()) {
            pois.add(
                new GeoPoi(
                    document.get("_id", ObjectId.class).toString(),
                    document.get("location", List.class)
                )
            );
        }
        return pois;
    }
}
