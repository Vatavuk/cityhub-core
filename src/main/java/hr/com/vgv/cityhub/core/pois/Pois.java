package hr.com.vgv.cityhub.core.pois;

/**
 * Places of interest.
 *
 * @author Vedran Vatavuk (123vgv@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public interface Pois {

    Iterable<GeoPoi> value() throws Exception;
}
